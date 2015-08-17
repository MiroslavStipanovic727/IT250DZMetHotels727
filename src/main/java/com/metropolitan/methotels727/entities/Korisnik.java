/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import com.metropolitan.methotels727.data.Uloga;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav
 */
@Entity
@XmlRootElement
@Table(name = "korisnik")
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")})
public class Korisnik extends AbstraktniEntitet{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    @Validate("required")
    private String email;
    @Basic(optional = false)
    @Column(name = "SIFRA")
    @Validate("required")
    private String sifra;
    @Basic(optional = false)
    @Column(name = "ULOGA")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Uloga uloga;
    @Basic(optional = false)
    @Column(name = "IME")
    @Validate("required")
    private String ime;
    @Basic(optional = false)
    @Column(name = "PREZIME")
    @Validate("required")
    private String prezime;
    @Column(name = "FACEBOOK_ID")
    private String facebookId;
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korId")
    private List<Rezervacija> rezervacijaList;

    @Inject
    public Korisnik() {
    }

    public Korisnik(Integer id) {
        this.id = id;
    }
    
    public Korisnik(String email, String sifra, Uloga uloga, String ime, String prezime) {
        this.email = email;
        this.sifra = sifra;
        this.uloga = uloga;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Korisnik(Integer id, String email, String sifra, Uloga uloga, String ime, String prezime) {
        this.id = id;
        this.email = email;
        this.sifra = sifra;
        this.uloga = uloga;
        this.ime = ime;
        this.prezime = prezime;
    }
    
    public Korisnik(String email, String sifra, Uloga uloga, String ime, String prezime, String facebookId) {
        this.email = email;
        this.sifra = sifra;
        this.uloga = uloga;
        this.ime = ime;
        this.prezime = prezime;
        this.facebookId = facebookId;
    }
    
    public Korisnik(Integer id, String email, String sifra, Uloga uloga, String ime, String prezime, String facebookId) {
        this.id = id;
        this.email = email;
        this.sifra = sifra;
        this.uloga = uloga;
        this.ime = ime;
        this.prezime = prezime;
        this.facebookId = facebookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    @XmlTransient
    public List<Rezervacija> getRezervacijaList() {
        return rezervacijaList;
    }

    public void setRezervacijaList(List<Rezervacija> rezervacijaList) {
        this.rezervacijaList = rezervacijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Korisnik: "+email;
    }
    
}
