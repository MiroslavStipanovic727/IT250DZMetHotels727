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
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class StraniceGrid {
    @Inject
    private KorisnikDAO koDAO;
    private int velicina = 20;
    
    Object onActivate(@RequestParameter("strana") int strana) {
        Class<?> act = null;
        int brojSvih = koDAO.getBrojSvihKorisnika();
        List<Korisnik> lista = new ArrayList<Korisnik>();
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
        lista = koDAO.getListaKorisnikaOdDo(strana);
        for (Korisnik k : lista) {
            response += (" <tr>\n"
                + " <td> " + k.getEmail() + "</td>\n"
                + " <td> " + k.getIme() + "</td>\n"
                + " <td> " + k.getPrezime()+ "</td>\n"
                + " <td> " + k.getSifra()+ "</td>\n"
                + " <td> " + k.getUloga() + "</td>\n"
                + " </tr>");
        }
        response += "</table>";
        float ceil = (float) brojSvih / (float) 20;
        int totalneVelicineStrana = (int) Math.ceil(ceil);
        response += "<ul class=\"pagination\">";
        for (int i = 1; i <= totalneVelicineStrana; i++) {
            if (strana == i) {
                response += ("<li class=\"callservice active\"><a href=\"#\">" + i + "</a></li>\n");
            } else {
                response += ("<li class=\"callservice\"><a href=\"#\">" + i + "</a></li>\n");
            }
        }
        response += "</ul>";
        return new TextStreamResponse("text/plain", response);
    }
}
