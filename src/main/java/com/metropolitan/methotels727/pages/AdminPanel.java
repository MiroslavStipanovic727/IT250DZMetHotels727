/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.entities.Rezervacija;
import com.metropolitan.methotels727.entities.Soba;
import com.metropolitan.methotels727.services.KorisnikDAO;
import com.metropolitan.methotels727.services.ProtectedPage;
import com.metropolitan.methotels727.services.RezervacijaDAO;
import com.metropolitan.methotels727.services.SobaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
public class AdminPanel {
    
    @Property
    private Soba soba;
    @Property
    private Soba onesoba;
    @Inject
    private SobaDAO sobaDAO;
    
    @Property
    private List<Soba> sobe;
    
    @Property
    private Korisnik korisnik;
    @Property
    private Korisnik onekorisnik;
    @Inject
    private KorisnikDAO korisnikDAO;
    
    @Property
    private List<Korisnik> korisnici;
    
    @Property
    private Rezervacija rezervacija;
    @Property
    private Rezervacija onerezervacija;
    @Inject
    private RezervacijaDAO rezervacijaDAO;
    
    @Property
    private Soba sid;
    
    @Property
    @Persist 
    private List<Soba> sobe2;
    
    public ValueEncoder getEncoder(){
        return new ValueEncoder<Soba>(){

            @Override
            public String toClient(Soba v) {
                return String.valueOf(v.getSId());
            }

            @Override
            public Soba toValue(String string) {
                Soba soba=sobaDAO.getSobaById(Integer.parseInt(string));
                return soba;
            }
            
        };
    }
    
    @Property
    private Korisnik kemail;
    
    @Property
    @Persist
    private List<Korisnik> korisnici2;
    
    public ValueEncoder getEncoder2(){
        return new ValueEncoder<Korisnik>(){

            @Override
            public String toClient(Korisnik v) {
                return v.getKEmail();
            }

            @Override
            public Korisnik toValue(String string) {
                Korisnik korisnik=korisnikDAO.getKorisnikByEmail(string);
                return korisnik;
            }
            
        };
    }
    
    @Property
    private List<Rezervacija> rezervacije;
    
    @Component
    private BeanEditForm unoskorisnika;        
    
    void onActivate(){
        soba = new Soba();
        korisnik = new Korisnik();
        rezervacija = new Rezervacija();
        if(sobe==null){
            sobe = new ArrayList<Soba>();
        }
        if(korisnici==null){
            korisnici = new ArrayList<Korisnik>();
        }
        if(rezervacije==null){
            rezervacije = new ArrayList<Rezervacija>();
        }
        sobe = sobaDAO.getListaSvihSoba();
        korisnici = korisnikDAO.getListaSvihKorisnika();
        rezervacije = rezervacijaDAO.getListaSvihRezervacija();
        sobe2 = sobaDAO.getListaSvihSoba();
        korisnici2 = korisnikDAO.getListaSvihKorisnika();

    }
    
    @CommitAfter
    Object onSuccessFromUnosSoba(){
       sobaDAO.dodajSobu(soba);
        return this;
    }
    
    @CommitAfter
    Object onActionFromDeleteSoba(int id){
        sobaDAO.obrisiSobu(id);
        return this;
    }
    
    public String getMD5Hash(String yourString) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(yourString.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
            }
    }
    
    @CommitAfter
    Object onSuccessFromUnosKorisnika(){
        if (!korisnikDAO.proveraDaLiPostojiEmail(korisnik.getKEmail())) {
            String unhashPassword = korisnik.getKSifra();
            korisnik.setKSifra(getMD5Hash(unhashPassword));
            korisnikDAO.dodajKorisnika(korisnik);
            return this;
        } else {
            unoskorisnika.recordError("Uneti email ve\u0107 postoji");
            return null;
        }
    }
    
    @CommitAfter
    Object onActionFromDeleteKorisnik(String email){
        korisnikDAO.obrisiKorisnika(email);
        return this;
    }
    
    @CommitAfter
    Object onSuccessFromUnosRezervacija(){
        rezervacija.setSId(sid);
        rezervacija.setKEmail(kemail);
        rezervacijaDAO.dodajRezervaciju(rezervacija);
        return this;
    }
    
    public String getSobaa(){
        if(onerezervacija.getSId()!=null){
            return onerezervacija.getSId().toString();
        }
        else {
            return "";
        }
        
    }
    
    public String getKorisnikk(){
        if(onerezervacija.getKEmail()!=null){
            return onerezervacija.getKEmail().getKEmail();
        }
        else {
            return "";
        }
    }
    
    @CommitAfter
    Object onActionFromDeleteRezervacija(int id){
        rezervacijaDAO.obrisiRezervaciju(id);
        return this;
    }
    
}

