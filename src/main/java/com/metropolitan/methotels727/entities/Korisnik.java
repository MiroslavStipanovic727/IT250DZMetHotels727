/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import com.metropolitan.methotels727.data.Uloga;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav
 */
@Entity
@Table(name = "korisnik")
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")})
public class Korisnik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "K_EMAIL")
    @Validate("required")
    private String kEmail;
    @Basic(optional = false)
    @Column(name = "K_SIFRA")
    @Validate("required")
    private String kSifra;
    @Basic(optional = false)
    @Column(name = "K_ULOGA")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Uloga kUloga;
    @Basic(optional = false)
    @Column(name = "K_IME")
    @Validate("required")
    private String kIme;
    @Basic(optional = false)
    @Column(name = "K_PREZIME")
    @Validate("required")
    private String kPrezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kEmail")
    private List<Rezervacija> rezervacijaList;

    @Inject
    public Korisnik() {
    }

    public Korisnik(String kEmail) {
        this.kEmail = kEmail;
    }

    public Korisnik(String kEmail, String kSifra, Uloga kUloga, String kIme, String kPrezime) {
        this.kEmail = kEmail;
        this.kSifra = kSifra;
        this.kUloga = kUloga;
        this.kIme = kIme;
        this.kPrezime = kPrezime;
    }

    public String getKEmail() {
        return kEmail;
    }

    public void setKEmail(String kEmail) {
        this.kEmail = kEmail;
    }

    public String getKSifra() {
        return kSifra;
    }

    public void setKSifra(String kSifra) {
        this.kSifra = kSifra;
    }

    public Uloga getKUloga() {
        return kUloga;
    }

    public void setKUloga(Uloga kUloga) {
        this.kUloga = kUloga;
    }

    public String getKIme() {
        return kIme;
    }

    public void setKIme(String kIme) {
        this.kIme = kIme;
    }

    public String getKPrezime() {
        return kPrezime;
    }

    public void setKPrezime(String kPrezime) {
        this.kPrezime = kPrezime;
    }

    public List<Rezervacija> getRezervacijaList() {
        return rezervacijaList;
    }

    public void setRezervacijaList(List<Rezervacija> rezervacijaList) {
        this.rezervacijaList = rezervacijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kEmail != null ? kEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.kEmail == null && other.kEmail != null) || (this.kEmail != null && !this.kEmail.equals(other.kEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Korisnik: " + kEmail;
    }
    
}
