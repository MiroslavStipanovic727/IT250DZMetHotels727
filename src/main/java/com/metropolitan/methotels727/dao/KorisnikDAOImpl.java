/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.Korisnik;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miroslav
 */
public class KorisnikDAOImpl implements KorisnikDAO {
    @Inject
    private Session session;

    @Override
    public List<Korisnik> getListaSvihKorisnika() {
        return session.createCriteria(Korisnik.class).list();
    }
    
    @Override
    public List<Korisnik> getListaSvihKorisnikaPoImenu(String ime) {
        return session.createCriteria(Korisnik.class)
                .add(Restrictions.ilike("ime", ime + "%")).list();
    }

    @Override
    public List<Korisnik> getListaSvihKorisnikaPoPrezimenu(String prezime) {
        return session.createCriteria(Korisnik.class)
                .add(Restrictions.ilike("prezime", prezime + "%")).list();
    }
    
    @Override
    public List<Korisnik> getListaKorisnikaOdDo(int od) {
        int strana = (od - 1) * 20;
        List<Korisnik> lista =
        session.createCriteria(Korisnik.class).setFirstResult(strana).setMaxResults(20)
                .addOrder(Order.asc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return lista;
    }
    
    @Override
    public Korisnik getKorisnikByEmail(String email) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("email",email)).uniqueResult();
    }

    @Override
    public void dodajKorisnika(Korisnik korisnik) {
        session.persist(korisnik);
    }

    @Override
    public void obrisiKorisnika(String email) {
        Korisnik korisnik=getKorisnikByEmail(email);
        session.delete(korisnik);
    }

    @Override
    public Korisnik proveriKorisnika(String email, String sifra) {
        try {
            Korisnik k = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("email",
            email)).add(Restrictions.eq("sifra", sifra)).uniqueResult();
            if (k != null) {
                return k;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }

    }

    @Override
    public Korisnik registrujKorisnika(Korisnik korisnik) {
        return (Korisnik) session.merge(korisnik);
    }

    @Override
    public boolean proveraDaLiPostojiEmail(String email) {
        return (Long)session.createCriteria(Korisnik.class).add(Restrictions.eq("email",
            email)).setProjection(Projections.rowCount()).uniqueResult() != 0;
    }
    
    @Override
    public Korisnik proveraDaLiPostojiFb(String fb) {
        return (Korisnik) session.createCriteria(Korisnik.class)
                .add(Restrictions.eq("facebookId", fb)).uniqueResult();
    }
    
    @Override 
    public int getBrojSvihKorisnika(){
        return getListaSvihKorisnika().size();
    }
}
