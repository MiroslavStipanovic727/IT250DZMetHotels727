/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.services;

import com.metropolitan.methotels727.entities.Rezervacija;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public interface RezervacijaDAO {
    
    public List<Rezervacija> getListaSvihRezervacija();
    public Rezervacija getRezervacijaById(Integer id);
    public void dodajRezervaciju(Rezervacija rezervacija);
    public void obrisiRezervaciju(Integer id);
}
