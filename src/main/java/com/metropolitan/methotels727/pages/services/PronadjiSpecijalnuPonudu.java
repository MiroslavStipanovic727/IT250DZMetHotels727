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
public class PronadjiSpecijalnuPonudu {
    @Inject
    private Request request;
    @Property
    private List<SpecijalnaPonuda> ponude;
    @Property
    private SpecijalnaPonuda ponuda;
    @Inject
    private SpecijalnePonudeDAO spDAO;
    
    Object onActivate(@RequestParameter("naziv") String naziv) {
        if (ponude == null) {
            ponude = new ArrayList<SpecijalnaPonuda>();
        }
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
        ponude = spDAO.getListaSvihSpecijalnihPonudaPoNazivu(naziv);
        for (SpecijalnaPonuda sp : ponude) {
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
        return new TextStreamResponse("text/plain", response);
    }
}
