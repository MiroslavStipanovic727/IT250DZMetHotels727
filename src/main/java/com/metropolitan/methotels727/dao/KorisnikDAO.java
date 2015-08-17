/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.Korisnik;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public interface KorisnikDAO {
    
    public List<Korisnik> getListaSvihKorisnika();
    public List<Korisnik> getListaSvihKorisnikaPoImenu(String ime);
    public List<Korisnik> getListaSvihKorisnikaPoPrezimenu(String prezime);
    public List<Korisnik> getListaKorisnikaOdDo(int od);
    public Korisnik getKorisnikByEmail(String email);
    public void dodajKorisnika(Korisnik korisnik);
    public void obrisiKorisnika(String email);
    public Korisnik proveriKorisnika(String email, String sifra);
    public Korisnik registrujKorisnika(Korisnik korisnik);
    public boolean proveraDaLiPostojiEmail(String email);
    public Korisnik proveraDaLiPostojiFb(String fb);
    public int getBrojSvihKorisnika();
    
}
