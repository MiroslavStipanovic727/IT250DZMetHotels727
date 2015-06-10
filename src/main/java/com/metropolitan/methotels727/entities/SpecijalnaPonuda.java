/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
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
public class SpecijalnaPonuda extends AbstraktniEntitet {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NAZIV")
    @Validate("required")
    private String naziv;
    @Basic(optional = false)
    @Column(name = "POPUST")
    @Validate("required,min=0,max=99")
    private double popust;
    @JoinColumn(name = "SOB_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @Validate("required")
    private Soba sobId;
//    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private AbstraktniEntitet abstraktniEntitet;

    @Inject
    public SpecijalnaPonuda() {
    }

    public SpecijalnaPonuda(Integer id) {
        this.id = id;
    }

    public SpecijalnaPonuda(Integer id, String naziv, double popust) {
        this.id = id;
        this.naziv = naziv;
        this.popust = popust;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public Soba getSobId() {
        return sobId;
    }

    public void setSobId(Soba sobId) {
        this.sobId = sobId;
    }

//    public AbstraktniEntitet getAbstraktniEntitet() {
//        return abstraktniEntitet;
//    }
//
//    public void setAbstraktniEntitet(AbstraktniEntitet abstraktniEntitet) {
//        this.abstraktniEntitet = abstraktniEntitet;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecijalnaPonuda)) {
            return false;
        }
        SpecijalnaPonuda other = (SpecijalnaPonuda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metropolitan.methotels727.entities.SpecijalnaPonuda[ id=" + id + " ]";
    }
    
}
