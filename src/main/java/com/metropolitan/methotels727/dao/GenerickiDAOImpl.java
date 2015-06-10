/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.AbstraktniEntitet;
import java.util.Collections;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miroslav
 */
public class GenerickiDAOImpl<T extends AbstraktniEntitet> implements GenerickiDAO<T> {
    @Inject
    private Session session;
    
    @Override
    public List getListaSvihObjekata(Class klasa) {
        List<T> lista = session.createCriteria(klasa).setResultTransformer(
                Criteria.DISTINCT_ROOT_ENTITY).list();
        Collections.sort(lista);
        return lista;
    }

    @Override
    public T getObjekatById(Integer id, Class klasa) {
        AbstraktniEntitet ae = (AbstraktniEntitet) session.createCriteria(klasa)
                .add(Restrictions.eq("id", id)).list().get(0);
        return (T) ae; 
    }   

    @Override
    public AbstraktniEntitet dodajIliUpdatuj(AbstraktniEntitet obj) {
        return (T) session.merge(obj);
    }

    @Override
    public T obrisi(Integer id, Class klasa) {
        AbstraktniEntitet ae = (AbstraktniEntitet) session.createCriteria(klasa)
                .add(Restrictions.eq("id", id)).list().get(0);
        session.delete((T) ae);
        session.flush();
        return (T) ae;
    }
    
}
