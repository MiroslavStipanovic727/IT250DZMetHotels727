/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.rest;

import com.metropolitan.methotels727.dao.SpecijalnePonudeDAO;
import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class SpecijalnePonudeWebService implements SpecijalnePonudeWebServiceInterface{

    @Inject 
    private SpecijalnePonudeDAO spDAO;
    
    @Override
    public List<SpecijalnaPonuda> getAll() {
        return spDAO.getListaSvihSpecijalnihPonuda();
    }

    @Override
    public SpecijalnaPonuda getSpecijalnaPonuda(@PathParam("id") Integer id) {
        return spDAO.getSpecijalnaPonudaById(id);
    }

    @Override
    public Response post(SpecijalnaPonuda specijalnaPonuda) {
        spDAO.dodajSpecijalnuPonudu(specijalnaPonuda);
        return Response.ok().entity(specijalnaPonuda).build();
    }
    
}
