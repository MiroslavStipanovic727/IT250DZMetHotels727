/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.entities.Soba;
import com.metropolitan.methotels727.services.SobaDAO;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 *
 * @author Miroslav Stipanoviæ 727
 */
public class Sobe {
    
    @Property
    private Soba soba;
    @Property
    private Soba onesoba;
    @Inject
    private SobaDAO sobaDAO;
    
    @Property
    private List<Soba> sobe;
    
    
    void onActivate(){
        if(sobe==null){
            sobe = new ArrayList<Soba>();
        }
        sobe = sobaDAO.getListaSvihSoba();
    }
     
    @CommitAfter
    Object onSuccess(){
       sobaDAO.dodajSobu(soba);
        return this;
    }
    
    @CommitAfter
    Object onActionFromDelete(int id){
        sobaDAO.obrisiSobu(id);
        return this;
    }
    
}
