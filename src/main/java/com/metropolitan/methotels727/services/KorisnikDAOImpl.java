/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.services;

import com.metropolitan.methotels727.entities.Korisnik;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
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
    public Korisnik getKorisnikByEmail(String email) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("kEmail",email)).uniqueResult();
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
            Korisnik k = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("kEmail",
            email)).add(Restrictions.eq("kSifra", sifra)).uniqueResult();
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
        Long redovi = (Long)session.createCriteria(Korisnik.class).add(Restrictions.eq("kEmail",
            email)).setProjection(Projections.rowCount()).uniqueResult();
        return (redovi==0) ? false : true;
    }
    
}
