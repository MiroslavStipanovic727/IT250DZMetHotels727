/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.Korisnik;
import com.metropolitan.methotels727.entities.Rezervacija;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Miroslav
 */
public class RezervacijaDAOImpl implements RezervacijaDAO {
    
    @Inject
    private Session session;

    @Override
    public List<Rezervacija> getListaSvihRezervacija() {
        return session.createCriteria(Rezervacija.class).list();
    }
    
    @Override
    public List<Rezervacija> getListaSvihRezervacijaPoImenuKorisnika(String ime) {
        return session.createCriteria(Rezervacija.class).createAlias("korId", "k")
                .add(Restrictions.ilike("k.ime", ime + "%")).list();
    }

    @Override
    public Rezervacija getRezervacijaById(Integer id) {
        return (Rezervacija) session.createCriteria(Rezervacija.class).add(
                Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajRezervaciju(Rezervacija rezervacija) {
        session.persist(rezervacija);
    }

    @Override
    public void obrisiRezervaciju(Integer id) {
        Rezervacija rezervacija = getRezervacijaById(id);
        session.delete(rezervacija);
    }

    @Override
    public boolean aktivnaRezervacijaKorisnika(Korisnik korisnik) {
        Date sad = Calendar.getInstance().getTime();
        return !(session.createCriteria(Rezervacija.class)
                .add(Restrictions.eq("korId", korisnik))
                .add(Restrictions.lt("datumPrijave", sad))
                .add(Restrictions.gt("datumOdjave", sad)).list().isEmpty());
    }
    
    
}
