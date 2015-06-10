/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miroslav
 */
public class SpecijalnePonudeDAOImpl implements SpecijalnePonudeDAO{
    
    @Inject
    private Session session;

    @Override
    public List<SpecijalnaPonuda> getListaSvihSpecijalnihPonuda() {
        return session.createCriteria(SpecijalnaPonuda.class).list();
    }

    @Override
    public SpecijalnaPonuda getSpecijalnaPonudaById(Integer id) {
        return (SpecijalnaPonuda) session.createCriteria(SpecijalnaPonuda.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajSpecijalnuPonudu(SpecijalnaPonuda specijalnaPonuda) {
        session.persist(specijalnaPonuda);
    }
        
    @Override
    public void obrisiSpecijalnuPonudu(Integer id) {
        SpecijalnaPonuda sp = getSpecijalnaPonudaById(id);
        session.delete(sp);
    }
    
    
    
}
