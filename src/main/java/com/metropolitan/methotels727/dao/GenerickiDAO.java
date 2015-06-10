/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.dao;

import com.metropolitan.methotels727.entities.AbstraktniEntitet;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public interface GenerickiDAO <T extends AbstraktniEntitet>{
    
    public abstract List<T> getListaSvihObjekata(Class klasa);
    public abstract T getObjekatById(Integer id, Class klasa);
    public abstract T dodajIliUpdatuj(T obj);
    public abstract T obrisi(Integer id, Class klasa);
    
}
