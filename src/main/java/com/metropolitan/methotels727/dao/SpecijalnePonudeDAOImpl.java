/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
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
    public List<SpecijalnaPonuda> getListaSvihSpecijalnihPonudaPoNazivu(String naziv) {
        return session.createCriteria(SpecijalnaPonuda.class)
                .add(Restrictions.ilike("naziv", naziv + "%")).list();
    }

    @Override
    public List<SpecijalnaPonuda> getListaSpecijalnihPonudaOdDo(int od){
        int strana = (od - 1) * 20;
        return session.createCriteria(SpecijalnaPonuda.class).setFirstResult(strana).setMaxResults(20)
                .addOrder(Order.asc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
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
    
    @Override
    public int getBrojSvihSpecijalnihPonuda(){
        return getListaSvihSpecijalnihPonuda().size();
    }
    
    
}
