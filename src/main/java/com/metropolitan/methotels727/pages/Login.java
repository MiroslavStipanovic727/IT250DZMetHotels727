/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.dao.KorisnikDAO;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav
 */
public class Login {
    
    @Property
    private Korisnik korisnik;
    @SessionState
    private Korisnik ulogovaniKorisnik;
    @SessionState
    private String ulogovaniEmail;
    @Inject
    private KorisnikDAO korisnikDAO;
    @Component
    private BeanEditForm formalogin;
    
    Object onActivate() {
        if (ulogovaniKorisnik.getEmail() != null) {
            return Index.class;
        }
        return null;
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
    
    Object onSuccess(){
        String sifra = getMD5Hash(korisnik.getSifra());
        Korisnik k = korisnikDAO.proveriKorisnika(korisnik.getEmail(), sifra);
        if(k!=null){
            ulogovaniKorisnik = k;
            ulogovaniEmail = k.getEmail();
            System.out.println("Uspešno logovanje na sistem korisnika "+ulogovaniEmail);
            return Index.class;
            
        }
        else {
            formalogin.recordError("Uneti korisnik ne postoji ili je pogrešna šifra");
            System.out.println("Neuspešno logovanje");
            return null;
        }
    }
}
