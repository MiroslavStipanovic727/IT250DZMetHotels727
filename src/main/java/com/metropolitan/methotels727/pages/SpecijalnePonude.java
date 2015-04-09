/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.entities.Soba;
import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import com.metropolitan.methotels727.services.SobaDAO;
import com.metropolitan.methotels727.services.SpecijalnePonudeDAO;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 *
 * @author Miroslav Stipanoviæ 727
 */
public class SpecijalnePonude {
    @Property
    private SpecijalnaPonuda specPonuda;
    @Property
    private SpecijalnaPonuda oneSpecijalnaPonuda;
  
    @Inject
    private SpecijalnePonudeDAO spDAO;
    @Inject
    private SobaDAO sobaDAO;
    
    @Property
    private Soba sid;
    
    @Property
    @Persist
    private List<Soba> sobe;
    
    public ValueEncoder getEncoder(){
        return new ValueEncoder<Soba>(){

            @Override
            public String toClient(Soba v) {
                return String.valueOf(v.getSId());
            }

            @Override
            public Soba toValue(String string) {
                Soba soba=sobaDAO.getSobaById(Integer.parseInt(string));
                return soba;
            }
            
        };
    }
    
    @Property
    private List<SpecijalnaPonuda> specPonude;
    
    
    void onActivate(){
        specPonuda = new SpecijalnaPonuda();
        if(specPonude==null){
            specPonude = new ArrayList<SpecijalnaPonuda>();
        }
        specPonude = spDAO.getListaSvihSpecijalnihPonuda();
        sobe = sobaDAO.getListaSvihSoba();
    }
     
    @CommitAfter
    Object onSuccess(){
        specPonuda.setSId(sid);
       spDAO.dodajSpecijalnuPonudu(specPonuda);
        return this;
    }
    
    public String getSoba(){
        if(oneSpecijalnaPonuda.getSId()!=null){
            return oneSpecijalnaPonuda.getSId().getSImeSobe().name();
        }
        else {
            return "";
        }
        
    }
    
    public String getBrsobe(){
        if(oneSpecijalnaPonuda.getSId()!=null){
            return ""+oneSpecijalnaPonuda.getSId().getSId();
        }
        else {
            return "";
        }
        
    }
    
    @CommitAfter
    Object onActionFromDelete(int id){
        spDAO.obrisiSpecijalnuPonudu(id);
        return this;
    }
}
