/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.rest;

import com.metropolitan.methotels727.dao.SobaDAO;
import com.metropolitan.methotels727.entities.Soba;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class SobeWebService implements SobeWebServiceInterface{

    @Inject 
    private SobaDAO sobaDAO;
    
    @Override
    public List<Soba> getAll() {
        return sobaDAO.getListaSvihSoba();
    }

    @Override
    public Soba getSoba(@PathParam("id") Integer id) {
        return sobaDAO.getSobaById(id);
    }

    @Override
    public Response post(Soba soba) {
        sobaDAO.dodajIliIzmeniSobu(soba);
        return Response.ok().entity(soba).build();
    }
    
}
