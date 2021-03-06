/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.data.ImeSobe;
import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.entities.Rezervacija;
import com.metropolitan.methotels727.entities.Soba;
import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import com.metropolitan.methotels727.dao.KorisnikDAO;
import com.metropolitan.methotels727.services.ProtectedPage;
import com.metropolitan.methotels727.dao.RezervacijaDAO;
import com.metropolitan.methotels727.dao.SobaDAO;
import com.metropolitan.methotels727.data.Opcije;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

/**
 *
 * @author Miroslav
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
@RequiresRoles("Admin")
public class AdminPanel {
    
    @Persist
    @Property
    private Soba soba;
    @Property
    private Soba onesoba;
    @Inject
    private SobaDAO sobaDAO;
    @Property
    private List<Soba> sobe;
    @InjectComponent
    private Zone zonaFormeSoba;
    @InjectComponent
    private Zone zonaGridaSobe;
    @Inject
    private Request request;
    @Inject
    private AjaxResponseRenderer ajaxRR;
    
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
                return String.valueOf(v.getId());
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
                return v.getEmail();
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
        sobaDAO.dodajIliIzmeniSobu(soba);
        sobe = sobaDAO.getListaSvihSoba();
        soba = new Soba();
        if(request.isXHR()){
            ajaxRR.addRender(zonaGridaSobe).addRender(zonaFormeSoba);
        }
        return request.isXHR() ? zonaGridaSobe.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromEditSoba(Soba sobaZaMenjanje){
        soba = sobaZaMenjanje;
        return request.isXHR() ? zonaFormeSoba.getBody() : null;
    }
    
    @CommitAfter
    Object onActionFromDeleteSoba(int id){
        sobaDAO.obrisiSobu(id);
        sobe = sobaDAO.getListaSvihSoba();
        return request.isXHR() ? zonaGridaSobe.getBody() : null;
    }
    
    @CommitAfter
    @OnEvent(component = "imeSobe", value = InPlaceEditor.SAVE_EVENT)
    void setImeSobe(Long id, ImeSobe value) {
        Soba sobaIzmena = sobaDAO.getSobaById(id.intValue());
        sobaIzmena.setImeSobe(value);
        sobaDAO.dodajIliIzmeniSobu(sobaIzmena);
    }
    
    @CommitAfter
    @OnEvent(component = "sprat", value = InPlaceEditor.SAVE_EVENT)
    void setSprat(Long id, int value) {
        Soba sobaIzmena = sobaDAO.getSobaById(id.intValue());
        sobaIzmena.setSprat(value);
        sobaDAO.dodajIliIzmeniSobu(sobaIzmena);
    }
    
    @CommitAfter
    @OnEvent(component = "tv", value = InPlaceEditor.SAVE_EVENT)
    void setTv(Long id, Opcije value) {
        Soba sobaIzmena = sobaDAO.getSobaById(id.intValue());
        sobaIzmena.setTv(value);
        sobaDAO.dodajIliIzmeniSobu(sobaIzmena);
    }
    
    @CommitAfter
    @OnEvent(component = "internet", value = InPlaceEditor.SAVE_EVENT)
    void setInternet(Long id, Opcije value) {
        Soba sobaIzmena = sobaDAO.getSobaById(id.intValue());
        sobaIzmena.setInternet(value);
        sobaDAO.dodajIliIzmeniSobu(sobaIzmena);
    }
    
    @CommitAfter
    @OnEvent(component = "djakuzi", value = InPlaceEditor.SAVE_EVENT)
    void setDjakuzi(Long id, Opcije value) {
        Soba sobaIzmena = sobaDAO.getSobaById(id.intValue());
        sobaIzmena.setDjakuzi(value);
        sobaDAO.dodajIliIzmeniSobu(sobaIzmena);
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
        if (!korisnikDAO.proveraDaLiPostojiEmail(korisnik.getEmail())) {
            String unhashPassword = korisnik.getSifra();
            korisnik.setSifra(getMD5Hash(unhashPassword));
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
        rezervacija.setSobId(sid);/*setSId(sid);*/
        rezervacija.setKorId(kemail);/*.setKEmail(kemail);*/
        rezervacijaDAO.dodajRezervaciju(rezervacija);
        return this;
    }
    
    public String getSobaa(){
        if(onerezervacija.getSobId()!=null){
            return onerezervacija.getSobId().toString();
        }
        else {
            return "";
        }
        
    }
    
    public String getKorisnikk(){
        if(onerezervacija.getKorId()!=null){
            return onerezervacija.getKorId().getEmail();
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
    
    public Date getDatump(){
        if(onerezervacija.getDatumPrijave()!=null){
            return onerezervacija.getDatumPrijave();
        }
        else {
            return null;
        }
    }
    
    public Date getDatumo(){
        if(onerezervacija.getDatumOdjave()!=null){
            return onerezervacija.getDatumOdjave();
        }
        else {
            return null;
        }
    }
    
    public double getPopust(){
        double popust=0.0;
        if(onerezervacija.getSobId()!=null&&onerezervacija.getSobId().getSpecijalnaPonudaList()!=null){
            for(SpecijalnaPonuda sp:onerezervacija.getSobId().getSpecijalnaPonudaList()){
                popust+= sp.getPopust();
            }
        }
        if(popust>=50.0)
            return 50.0;
        return popust;
    }
    
    public double getCpd(){
        if(onerezervacija.getSobId()!=null){
            if(onerezervacija.getSobId().getImeSobe().equals(ImeSobe.Jednokrevetna)){
                return 10.0;
            } else if(onerezervacija.getSobId().getImeSobe().equals(ImeSobe.Luksuzna)){
                return 15.0;
            } else if(onerezervacija.getSobId().getImeSobe().equals(ImeSobe.Dvokrevetna)){
                return 20.0;
            } else if(onerezervacija.getSobId().getImeSobe().equals(ImeSobe.Bracna)){
                return 25.0;
            } else if(onerezervacija.getSobId().getImeSobe().equals(ImeSobe.Trokrevetna)){
                return 30.0; 
            }
            else{
                return 0.0;
            }
        }
        else {
            return 0.0;
        }
    }
    
    
}

