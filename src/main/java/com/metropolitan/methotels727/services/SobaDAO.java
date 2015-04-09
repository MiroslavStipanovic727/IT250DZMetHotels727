/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.services;

import com.metropolitan.methotels727.entities.Soba;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public interface SobaDAO {
    
    public List<Soba> getListaSvihSoba();
    public Soba getSobaById(Integer id);
    public void dodajSobu(Soba soba);
    public void obrisiSobu(Integer id);
    
}
