/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.data;

import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav Stipanoviæ 727
 */
public class Soba {
    
    @Validate("required")
    private ImeSobe imeSobe;
    @Validate("required, min=0, max=10")
    private int sprat;
    @Validate("required")
    private Opcije tv;
    @Validate("required")
    private Opcije internet;
    @Validate("required")
    private Opcije djakuzi;

    @Inject
    public Soba() {
    }

    public Soba(ImeSobe imeSobe, int sprat, Opcije tv, Opcije internet, Opcije djakuzi) {
        this.imeSobe = imeSobe;
        this.sprat = sprat;
        this.tv = tv;
        this.internet = internet;
        this.djakuzi = djakuzi;
    }

    /**
     * @return the imeSobe
     */
    public ImeSobe getImeSobe() {
        return imeSobe;
    }

    /**
     * @param imeSobe the imeSobe to set
     */
    public void setImeSobe(ImeSobe imeSobe) {
        this.imeSobe = imeSobe;
    }

    /**
     * @return the sprat
     */
    public int getSprat() {
        return sprat;
    }

    /**
     * @param sprat the sprat to set
     */
    public void setSprat(int sprat) {
        this.sprat = sprat;
    }

    /**
     * @return the tv
     */
    public Opcije getTv() {
        return tv;
    }

    /**
     * @param tv the tv to set
     */
    public void setTv(Opcije tv) {
        this.tv = tv;
    }

    /**
     * @return the internet
     */
    public Opcije getInternet() {
        return internet;
    }

    /**
     * @param internet the internet to set
     */
    public void setInternet(Opcije internet) {
        this.internet = internet;
    }

    /**
     * @return the djakuzi
     */
    public Opcije getDjakuzi() {
        return djakuzi;
    }

    /**
     * @param djakuzi the djakuzi to set
     */
    public void setDjakuzi(Opcije djakuzi) {
        this.djakuzi = djakuzi;
    }

        
}
