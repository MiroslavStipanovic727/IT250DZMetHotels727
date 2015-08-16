/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.rest;

import com.metropolitan.methotels727.dao.KorisnikDAO;
import com.metropolitan.methotels727.entities.Korisnik;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class KorisniciWebService implements KorisniciWebServiceInterface{

    @Inject 
    private KorisnikDAO koDAO;
    
    @Override
    public List<Korisnik> getAll() {
        return koDAO.getListaSvihKorisnika();
    }

    @Override
    public Korisnik getKorisnik(@PathParam("email") String email) {
        return koDAO.getKorisnikByEmail(email);
    }

    @Override
    public Response post(Korisnik korisnik) {
        koDAO.dodajKorisnika(korisnik);
        return Response.ok().entity(korisnik).build();
    }
    
}
