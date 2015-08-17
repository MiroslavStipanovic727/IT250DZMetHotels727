/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.data.Uloga;
import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.dao.KorisnikDAO;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.kaptcha.components.KaptchaImage;

/**
 *
 * @author Miroslav
 */
public class Registracija {
    
    @Property
    private Korisnik korisnik;
    @SessionState
    private Korisnik ulogovaniKorisnik;
    @SessionState
    private String ulogovaniEmail;
    @Inject
    private KorisnikDAO korisnikDAO;
    @Component
    private BeanEditForm formaregistracija;
    
    @Component
    @Property
    private KaptchaImage kaptchaImage;
    @Property
    private String cap;
    
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
    Object onSuccess(){
        if (!korisnikDAO.proveraDaLiPostojiEmail(korisnik.getEmail())) {
            String unhashPassword = korisnik.getSifra();
            korisnik.setSifra(getMD5Hash(unhashPassword));
            korisnik.setUloga(Uloga.Korisnik);
            ulogovaniKorisnik = korisnikDAO.registrujKorisnika(korisnik);
            ulogovaniEmail = ulogovaniKorisnik.getEmail();
            return Index.class;
        } else {
            formaregistracija.recordError("Uneti email ve\u0107 postoji");
            return null;
        }
    }
}
