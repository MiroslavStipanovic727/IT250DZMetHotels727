/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.services;

import com.metropolitan.methotels727.entities.Soba;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miroslav
 */
public class SobaDAOImpl implements SobaDAO {
    @Inject
    private Session session;

    @Override
    public List<Soba> getListaSvihSoba() {
        return session.createCriteria(Soba.class).list();
    }

    @Override
    public Soba getSobaById(Integer id) {
        return (Soba) session.createCriteria(Soba.class).add(Restrictions.eq("sId", id)).uniqueResult();
    }

    @Override
    public void dodajSobu(Soba soba) {
        session.persist(soba);
    }

    @Override
    public void obrisiSobu(Integer id) {
        Soba soba = getSobaById(id);
        session.delete(soba);
    }
    
}
