/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.entities;

import com.metropolitan.methotels727.data.ImeSobe;
import com.metropolitan.methotels727.data.Opcije;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "soba")
@NamedQueries({
    @NamedQuery(name = "Soba.findAll", query = "SELECT s FROM Soba s")})
public class Soba implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IME_SOBE")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private ImeSobe imeSobe;
//    private String imeSobe;
    
    @Basic(optional = false)
    @Column(name = "SPRAT")
    @Validate("required, min=0, max=10")
    private int sprat;
    @Basic(optional = false)
    @Column(name = "TV")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Opcije tv;
//    private String tv;

    @Basic(optional = false)
    @Column(name = "INTERNET")
    @Validate("required")
    @Enumerated(EnumType.STRING)
    private Opcije internet;
//    private String internet;
    @Basic(optional = false)
    @Column(name = "DJAKUZI")
    @Validate("required")  
    @Enumerated(EnumType.STRING)
    private Opcije djakuzi;
//    private String djakuzi;
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
//    public Soba(Integer id, String imeSobe, int sprat, String, String internet, String djakuzi) {
//        this.id = id;
//        this.imeSobe = imeSobe;
//        this.sprat = sprat;
//        this.tv = tv;
//        this.internet = internet;
//        this.djakuzi = djakuzi;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImeSobe getImeSobe() {
//    public String getImeSobe() {
        return imeSobe;
    }

    public void setImeSobe(ImeSobe imeSobe) {
//      public void setImeSobe(String imeSobe) {
        this.imeSobe = imeSobe;
    }

    public int getSprat() {
        return sprat;
    }

    public void setSprat(int sprat) {
        this.sprat = sprat;
    }

    public Opcije getTv() {
//    public String getTv(){
        return tv;
    }

    public void setTv(Opcije tv) {
//    public void setTv(String tv) {
        this.tv = tv;
    }

    public Opcije getInternet() {
//    public String getInternet() {
        return internet;
    }

    public void setInternet(Opcije internet) {
//    public void setInternet(String internet) {
        this.internet = internet;
    }

    public Opcije getDjakuzi() {
//    public String getDjakuzi() {
        return djakuzi;
    }

    public void setDjakuzi(Opcije djakuzi) {
//    public void setDjakuzi(String djakuzi){
        this.djakuzi = djakuzi;
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
        return "com.metropolitan.methotels727.entities.Soba[ id=" + id + " ]";
    }
    
}
