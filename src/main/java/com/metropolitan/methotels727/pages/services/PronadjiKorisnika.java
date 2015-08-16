/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages.services;

import com.metropolitan.methotels727.dao.KorisnikDAO;
import com.metropolitan.methotels727.entities.Korisnik;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class PronadjiKorisnika {
    @Inject
    private Request request;
    @Property
    private List<Korisnik> korisnici;
    @Property
    private Korisnik korisnik;
    @Inject
    private KorisnikDAO koDAO;
    
    Object onActivate(@RequestParameter("prezime") String prezime) {
        if (korisnici == null) {
            korisnici = new ArrayList<Korisnik>();
        }
        String response = "<table class=\"navigation\" > <th>\n"
            + " Email\n"
            + " </th>\n"
            + " <th>\n"
            + " Ime\n"
            + " </th>\n"
            + " <th>\n"
            + " Prezime\n"
            + " </th>\n"
            + " <th>\n"
            + " Šifra\n"
            + " </th>\n"
            + " <th>\n"
            + " Uloga\n"
            + " </th>\n" 
            + " ";
        korisnici = koDAO.getListaSvihKorisnikaPoPrezimenu(prezime);
        for (Korisnik k : korisnici) {
            response += (" <tr>\n"
                + " <td> " + k.getEmail() + "</td>\n"
                + " <td> " + k.getIme() + "</td>\n"
                + " <td> " + k.getPrezime()+ "</td>\n"
                + " <td> " + k.getSifra()+ "</td>\n"
                + " <td> " + k.getUloga() + "</td>\n"
                + " </tr>");
        }
        response += "</table>";
        return new TextStreamResponse("text/plain", response);
    }
}
