/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.rest;

import com.metropolitan.methotels727.dao.RezervacijaDAO;
import com.metropolitan.methotels727.entities.Rezervacija;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class RezervacijeWebService implements RezervacijeWebServiceInterface{

    @Inject 
    private RezervacijaDAO reDAO;
    
    @Override
    public List<Rezervacija> getAll() {
        return reDAO.getListaSvihRezervacija();
    }

    @Override
    public Rezervacija getRezervacija(@PathParam("id") Integer id) {
        return reDAO.getRezervacijaById(id);
    }

    @Override
    public Response post(Rezervacija rezervacija) {
        reDAO.dodajRezervaciju(rezervacija);
        return Response.ok().entity(rezervacija).build();
    }
   
}
