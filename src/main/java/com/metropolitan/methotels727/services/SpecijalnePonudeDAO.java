/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.services;

import com.metropolitan.methotels727.entities.SpecijalnaPonuda;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public interface SpecijalnePonudeDAO {
    public List<SpecijalnaPonuda> getListaSvihSpecijalnihPonuda();
    public SpecijalnaPonuda getSpecijalnaPonudaById(Integer id);
    public void dodajSpecijalnuPonudu(SpecijalnaPonuda specijanaPonuda);
    public void obrisiSpecijalnuPonudu(Integer id);
}
