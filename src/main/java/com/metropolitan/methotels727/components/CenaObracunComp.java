/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.components;

import java.text.DecimalFormat;
import java.util.Date;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Miroslav
 */
public class CenaObracunComp {
    
    @Property
    @Parameter(required=true)
    private Date datumPrijave;
    @Property
    @Parameter(required=true)
    private Date datumOdjave;
    @Property
    @Parameter(required=true)
    private double cenaPoDanu;
    @Property
    @Parameter(value="0.0")
    private double popust;
    
    private long getBrojDana(){
       return ((datumOdjave.getTime() - datumPrijave.getTime())/86400000)+1;
    }
    
    public String getCena(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(cenaPoDanu * getBrojDana() * (1.0-(popust/100.0)))+"\u20AC";
    }
    
}
