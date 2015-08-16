/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages.services;

import com.metropolitan.methotels727.dao.KorisnikDAO;
import com.metropolitan.methotels727.dao.SpecijalnePonudeDAO;
import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import com.metropolitan.methotels727.pages.SpecijalnePonude;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class StraniceSPGrid {
    @Inject
    private SpecijalnePonudeDAO sp;
    private int velicina = 20;
    
    Object onActivate(@RequestParameter("strana") int strana) {
        Class<?> act = null;
        int brojSvih = sp.getBrojSvihSpecijalnihPonuda();
        List<SpecijalnaPonuda> lista = new ArrayList<SpecijalnaPonuda>();
        String response = "<table class=\"navigation\" > <th class=\"granica\">\n"
            + " Naziv Ponude\n"
            + " </th>\n"
            + " <th class=\"granica\">\n"
            + " Popust\n"
            + " </th>\n"
            + " <th class=\"granica\">\n"
            + " Broj sobe\n"
            + " </th>\n" 
            + " <th class=\"granica\">\n"
            + " Tip sobe\n"
            + " </th>\n" 
            + " ";
        lista = sp.getListaSpecijalnihPonudaOdDo(strana);
        for (SpecijalnaPonuda sp : lista) {
            response += (" <tr>\n"
                + " <td class=\"granica\"> " + sp.getNaziv()+ "</td>\n"
                + " <td class=\"granica\"> " + sp.getPopust()+ "</td>\n");
            if(sp.getSobId()!=null){
                response += (" <td class=\"granica\"> " + sp.getSobId().getId() + "</td>\n"
                        + " <td class=\"granica\"> " + sp.getSobId().getImeSobe()+ "</td>\n");
            } else { 
                response += (" <td class=\"granica\"> </td>\n"
                        + " <td class=\"granica\"> </td>\n");
            }
            response += " </tr>";
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
