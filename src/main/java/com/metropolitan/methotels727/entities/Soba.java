/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import com.metropolitan.methotels727.data.ImeSobe;
import com.metropolitan.methotels727.data.Opcije;
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
@Table(name = "soba")
@NamedQueries({
    @NamedQuery(name = "Soba.findAll", query = "SELECT s FROM Soba s")})
public class Soba extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "IME_SOBE")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private ImeSobe imeSobe;
    @Basic(optional = false)
    @Column(name = "SPRAT")
    @Validate("required,min=0,max=10")
    private int sprat;
    @Basic(optional = false)
    @Column(name = "TV")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Opcije tv;
    @Basic(optional = false)
    @Column(name = "INTERNET")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Opcije internet;
    @Basic(optional = false)
    @Column(name = "DJAKUZI")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Opcije djakuzi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sobId")
    @XmlTransient
    private List<SpecijalnaPonuda> specijalnaPonudaList;
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sobId")
    private List<Rezervacija> rezervacijaList;

    @Inject
    public Soba() {
    }

    public Soba(Integer id) {
        this.id = id;
    }

    public Soba(Integer id, ImeSobe imeSobe, int sprat, Opcije tv, Opcije internet, Opcije djakuzi) {
        this.id = id;
        this.imeSobe = imeSobe;
        this.sprat = sprat;
        this.tv = tv;
        this.internet = internet;
        this.djakuzi = djakuzi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImeSobe getImeSobe() {
        return imeSobe;
    }

    public void setImeSobe(ImeSobe imeSobe) {
        this.imeSobe = imeSobe;
    }

    public int getSprat() {
        return sprat;
    }

    public void setSprat(int sprat) {
        this.sprat = sprat;
    }

    public Opcije getTv() {
        return tv;
    }

    public void setTv(Opcije tv) {
        this.tv = tv;
    }

    public Opcije getInternet() {
        return internet;
    }

    public void setInternet(Opcije internet) {
        this.internet = internet;
    }

    public Opcije getDjakuzi() {
        return djakuzi;
    }

    public void setDjakuzi(Opcije djakuzi) {
        this.djakuzi = djakuzi;
    }

    @XmlTransient
    public List<SpecijalnaPonuda> getSpecijalnaPonudaList() {
        return specijalnaPonudaList;
    }

    public void setSpecijalnaPonudaList(List<SpecijalnaPonuda> specijalnaPonudaList) {
        this.specijalnaPonudaList = specijalnaPonudaList;
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
        if (!(object instanceof Soba)) {
            return false;
        }
        Soba other = (Soba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Soba br = " + id + " tip sobe: " +getImeSobe();
    }
    
}
