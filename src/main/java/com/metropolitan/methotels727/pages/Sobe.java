/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.pages;

import com.metropolitan.methotels727.components.GenerickiEditor;
import com.metropolitan.methotels727.entities.Soba;
import com.metropolitan.methotels727.dao.SobaDAO;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.hibernate.Session;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class Sobe extends GenerickiEditor<Soba>{
    
    
    
    
//    @Property
//    @Persist
//    private Soba soba;
//    @Property
//    private Soba onesoba;
//    @Inject
//    private SobaDAO sobaDAO;
//    
//    @Property
//    private List<Soba> sobe;
//    
//    
//    void onActivate(){
//        if(sobe==null){
//            sobe = new ArrayList<Soba>();
//        }
//        sobe = sobaDAO.getListaSvihSoba();
//    }
//     
//    @CommitAfter
//    Object onSuccess(){
//       sobaDAO.dodajIliIzmeniSobu(soba);
//       soba = new Soba();
//        return this;
//    }
//    
//    @CommitAfter
//    Object onActionFromEdit(Soba soba2){
//        soba = soba2;
//        return this;
//    }
//    
//    @CommitAfter
//    Object onActionFromDelete(int id){
//        sobaDAO.obrisiSobu(id);
//        return this;
//    }
//    
//    public JSONObject getOptions(){
//        JSONObject json = new JSONObject();
//        json.put("bJQueryUI", true);
//        json.put("bStateSave", true);
//        json.put("bAutoWidth", true);
//        return json;
//    }
    
//    void onActivate(){
//        System.out.println("ON ACTIVATE OVDE");
//        System.out.println("GE.GETCLASS.TOSTRING: "+ge.getClass().toString());
//        System.out.println("GE.TOSTRING"+ge.toString());
//        try{
//        System.out.println("GE.getGrid.getClass: "+ge.getGrid().getClass());
//        System.out.println("GE.getFormModel.getClass: "+ge.getFormModel().getClass());
//        } catch(Exception ex){
//            System.out.println("Exception!!!: "+ex.getMessage());
//        }
//    }
    
}
