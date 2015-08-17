/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.dao.KorisnikDAO;
import com.metropolitan.methotels727.data.Uloga;
import com.metropolitan.methotels727.services.FacebookService;
import com.metropolitan.methotels727.services.FacebookServiceInformation;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import java.io.IOException;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

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
    
    @Inject
    private SecurityService securityService;
    @Inject
    private FacebookService facebookService;
    @SessionState
    @Property
    private FacebookServiceInformation facebookServiceInformation;
    @SessionState
    @Property
    private FacebookServiceInformation information;
    @Property
    private com.restfb.types.User userfb;
    @Property
    @ActivationRequestParameter
    private String code;
    
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
            Subject trenutniKorisnik = securityService.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(k.getEmail(),
            korisnik.getSifra());
            try {
                trenutniKorisnik.login(token);
            } catch (Exception e) {
                formalogin.recordError("Uneli ste pogrešne parametre");
            }
            if(ulogovaniKorisnik.getUloga() != Uloga.Admin)
                return Index.class;
            else {
                return AdminPanel.class;
            }
        }
        else {
            formalogin.recordError("Uneti korisnik ne postoji ili je pogrešna šifra");
            System.out.println("Neuspešno logovanje");
            return null;
        }
    }
    
    public String getFacebookAuthentificationLink() throws OAuthSystemException {
        return facebookService.getFacebookAuthentificationLink();
    }
    
    @CommitAfter
    public boolean isLoggedInFb() {
        if (facebookServiceInformation.getAccessToken() != null) {
            Korisnik fbuser;
            if(userfb.getEmail()!=null&&userfb.getFirstName()!=null&&
                    userfb.getLastName()!=null){
                fbuser = new Korisnik(userfb.getEmail(), " ", Uloga.Korisnik,
                userfb.getFirstName(), userfb.getLastName(), userfb.getId());
            } else {
                fbuser = new Korisnik((userfb.getId()+"@facebook.com"), " ", Uloga.Korisnik,
                (userfb.getName().substring(0, userfb.getName().indexOf(' '))), 
                        (userfb.getName().substring(userfb.getName().indexOf(' '))), 
                        userfb.getId());
            }
            Korisnik exist;
            System.out.println("proverava");
            exist = korisnikDAO.proveraDaLiPostojiFb(userfb.getId());
            if (exist == null) {
                korisnikDAO.registrujKorisnika(fbuser);
                ulogovaniKorisnik = fbuser;
                ulogovaniEmail = fbuser.getEmail();
                System.out.println("registruje");
            } else {
                ulogovaniKorisnik = exist;
                ulogovaniEmail = exist.getEmail();
                System.out.println("postoji");
            }
        }
    return facebookServiceInformation.getAccessToken() != null;
    }
    
    @SetupRender
    public void setup() throws IOException, OAuthSystemException,OAuthProblemException {
        if (code != null) {
            facebookService.getUserAccessToken(code,information.getAccessToken());
        }
        code = null;
        FacebookClient facebookClient = new
        DefaultFacebookClient(information.getAccessToken());
        if (information.isLoggedIn()) {
            userfb = facebookClient.fetchObject("me", com.restfb.types.User.class);
        }
    }
}
