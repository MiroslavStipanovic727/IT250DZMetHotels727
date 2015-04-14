/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import java.io.Serializable;
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
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav
 */
@Entity
@Table(name = "specijalna_ponuda")
@NamedQueries({
    @NamedQuery(name = "SpecijalnaPonuda.findAll", query = "SELECT s FROM SpecijalnaPonuda s")})
public class SpecijalnaPonuda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SP_ID")
    private Integer spId;
    @Basic(optional = false)
    @Column(name = "SP_NAZIV")
    @Validate("required")
    private String spNaziv;
    @Basic(optional = false)
    @Column(name = "SP_POPUST")
    @Validate("required,min=0,max=99")
    private double spPopust;
    @JoinColumn(name = "S_ID", referencedColumnName = "S_ID")
    @ManyToOne
    private Soba sId;

    @Inject
    public SpecijalnaPonuda() {
    }

    public SpecijalnaPonuda(Integer spId) {
        this.spId = spId;
    }

    public SpecijalnaPonuda(Integer spId, String spNaziv, double spPopust) {
        this.spId = spId;
        this.spNaziv = spNaziv;
        this.spPopust = spPopust;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public String getSpNaziv() {
        return spNaziv;
    }

    public void setSpNaziv(String spNaziv) {
        this.spNaziv = spNaziv;
    }

    public double getSpPopust() {
        return spPopust;
    }

    public void setSpPopust(double spPopust) {
        this.spPopust = spPopust;
    }

    public Soba getSId() {
        return sId;
    }

    public void setSId(Soba sId) {
        this.sId = sId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spId != null ? spId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecijalnaPonuda)) {
            return false;
        }
        SpecijalnaPonuda other = (SpecijalnaPonuda) object;
        if ((this.spId == null && other.spId != null) || (this.spId != null && !this.spId.equals(other.spId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metropolitan.methotels727.entities.SpecijalnaPonuda[ spId=" + spId + " ]";
    }
    
}
