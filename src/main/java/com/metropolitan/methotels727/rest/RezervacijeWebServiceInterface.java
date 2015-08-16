/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.rest;

import com.metropolitan.methotels727.entities.Rezervacija;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import javax.ws.rs.core.Response;
/**
 *
 * @author Miroslav Stipanović 727
 */
@Path("/rezervacijeservice")
public interface RezervacijeWebServiceInterface {

    @GET
    @Produces({"application/json"})
    public List<Rezervacija> getAll();
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Rezervacija getRezervacija(@PathParam("id") Integer id);
    @POST
    @CommitAfter
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response post(Rezervacija rezervacija);
}

