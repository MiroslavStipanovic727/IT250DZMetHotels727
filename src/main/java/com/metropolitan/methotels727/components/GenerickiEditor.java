/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.components;

import com.metropolitan.methotels727.entities.AbstraktniEntitet;
import com.metropolitan.methotels727.dao.GenerickiDAO;
import java.util.List;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.PropertyConduitSource;

/**
 *
 * @author Miroslav
 * @param <T>
 */
public class GenerickiEditor<T extends AbstraktniEntitet> {
    
    @Inject
    private PropertyConduitSource conduit;
    @Inject
    private GenerickiDAO generickiDAO;
    @Property
    @Persist
    private T bean;
    @Property
    private T red;
    @Inject
    private BeanModelSource bms;
    @Inject
    private ComponentResources cr;
    
    private Class klasa;
    
    {
        PropertyConduit conduit1 = conduit.create(getClass(), "bean");
        klasa = conduit1.getPropertyType();
    }
    
    public List<T> getGrid(){
        List<T> temp = generickiDAO.getListaSvihObjekata(klasa);
        return temp;
    }
    
    public BeanModel<T> getFormModel(){
        return bms.createEditModel(klasa, cr.getMessages()).exclude("id");
    }
    
    public BeanModel<T> getGridModel(){
        return bms.createDisplayModel(klasa, cr.getMessages()).exclude("id");
    }
    
    @CommitAfter
    Object onActionFromDelete(int id){
        generickiDAO.obrisi(id, klasa);
        return this;
    }
       
    @CommitAfter
    Object onActionFromEdit(int red){
        bean = (T)generickiDAO.getObjekatById(red, klasa);
        return this;
    }
    
    @CommitAfter
    Object onSuccess(){
        generickiDAO.dodajIliUpdatuj(bean);
        try {
            bean = (T) klasa.newInstance();
        } catch(Exception ex){
        }
        return this;
    }
    
}
