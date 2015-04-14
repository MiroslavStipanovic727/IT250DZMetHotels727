/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav
 */
@Entity
@Table(name = "rezervacija")
@NamedQueries({
    @NamedQuery(name = "Rezervacija.findAll", query = "SELECT r FROM Rezervacija r")})
public class Rezervacija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "R_ID")
    private Integer rId;
    @Basic(optional = false)
    @Column(name = "R_DATUM_PRIJAVE")
    @Temporal(TemporalType.DATE)
    @Validate("required")
    private Date rDatumPrijave;
    @Basic(optional = false)
    @Column(name = "R_DATUM_ODJAVE")
    @Temporal(TemporalType.DATE)
    @Validate("required")    
    private Date rDatumOdjave;
    @Basic(optional = false)
    @Column(name = "R_CENA")
    @Validate("required")
    private double rCena;
    @JoinColumn(name = "S_ID", referencedColumnName = "S_ID")
    @ManyToOne(optional = false)
    @Validate("required")
    private Soba sId;
    @JoinColumn(name = "K_EMAIL", referencedColumnName = "K_EMAIL")
    @ManyToOne(optional = false)
    @Validate("required")
    private Korisnik kEmail;

    @Inject
    public Rezervacija() {
    }

    public Rezervacija(Integer rId) {
        this.rId = rId;
    }

    public Rezervacija(Integer rId, Date rDatumPrijave, Date rDatumOdjave, double rCena) {
        this.rId = rId;
        this.rDatumPrijave = rDatumPrijave;
        this.rDatumOdjave = rDatumOdjave;
        this.rCena = rCena;
    }

    public Integer getRId() {
        return rId;
    }

    public void setRId(Integer rId) {
        this.rId = rId;
    }

    public Date getRDatumPrijave() {
        return rDatumPrijave;
    }

    public void setRDatumPrijave(Date rDatumPrijave) {
        this.rDatumPrijave = rDatumPrijave;
    }

    public Date getRDatumOdjave() {
        return rDatumOdjave;
    }

    public void setRDatumOdjave(Date rDatumOdjave) {
        this.rDatumOdjave = rDatumOdjave;
    }

    public double getRCena() {
        return rCena;
    }

    public void setRCena(double rCena) {
        this.rCena = rCena;
    }

    public Soba getSId() {
        return sId;
    }

    public void setSId(Soba sId) {
        this.sId = sId;
    }

    public Korisnik getKEmail() {
        return kEmail;
    }

    public void setKEmail(Korisnik kEmail) {
        this.kEmail = kEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rId != null ? rId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.rId == null && other.rId != null) || (this.rId != null && !this.rId.equals(other.rId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metropolitan.methotels727.entities.Rezervacija[ rId=" + rId + " ]";
    }
    
}
