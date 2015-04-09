/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import com.metropolitan.methotels727.data.ImeSobe;
import com.metropolitan.methotels727.data.Opcije;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "soba")
@NamedQueries({
    @NamedQuery(name = "Soba.findAll", query = "SELECT s FROM Soba s")})
public class Soba implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "S_ID")
    private Integer sId;
    @Basic(optional = false)
    @Column(name = "S_IME_SOBE")
    @Validate("required")
    private ImeSobe sImeSobe;
    @Basic(optional = false)
    @Column(name = "S_SPRAT")
    @Validate("required,min=0,max=10")
    private int sSprat;
    @Basic(optional = false)
    @Column(name = "S_TV")
    @Validate("required")
    private Opcije sTv;
    @Basic(optional = false)
    @Column(name = "S_INTERNET")
    @Validate("required")
    private Opcije sInternet;
    @Basic(optional = false)
    @Column(name = "S_DJAKUZI")
    @Validate("required")
    private Opcije sDjakuzi;
    @OneToMany(mappedBy = "sId")
    private List<SpecijalnaPonuda> specijalnaPonudaList;

    @Inject
    public Soba() {
    }

    public Soba(Integer sId) {
        this.sId = sId;
    }

    public Soba(Integer sId, ImeSobe sImeSobe, int sSprat, Opcije sTv, Opcije sInternet, Opcije sDjakuzi) {
        this.sId = sId;
        this.sImeSobe = sImeSobe;
        this.sSprat = sSprat;
        this.sTv = sTv;
        this.sInternet = sInternet;
        this.sDjakuzi = sDjakuzi;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public ImeSobe getSImeSobe() {
        return sImeSobe;
    }

    public void setSImeSobe(ImeSobe sImeSobe) {
        this.sImeSobe = sImeSobe;
    }

    public int getSSprat() {
        return sSprat;
    }

    public void setSSprat(int sSprat) {
        this.sSprat = sSprat;
    }

    public Opcije getSTv() {
        return sTv;
    }

    public void setSTv(Opcije sTv) {
        this.sTv = sTv;
    }

    public Opcije getSInternet() {
        return sInternet;
    }

    public void setSInternet(Opcije sInternet) {
        this.sInternet = sInternet;
    }

    public Opcije getSDjakuzi() {
        return sDjakuzi;
    }

    public void setSDjakuzi(Opcije sDjakuzi) {
        this.sDjakuzi = sDjakuzi;
    }

    public List<SpecijalnaPonuda> getSpecijalnaPonudaList() {
        return specijalnaPonudaList;
    }

    public void setSpecijalnaPonudaList(List<SpecijalnaPonuda> specijalnaPonudaList) {
        this.specijalnaPonudaList = specijalnaPonudaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sId != null ? sId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soba)) {
            return false;
        }
        Soba other = (Soba) object;
        if ((this.sId == null && other.sId != null) || (this.sId != null && !this.sId.equals(other.sId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Soba br = " + sId + " tip sobe: " +getSImeSobe();
    }
    
}
