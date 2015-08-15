/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.methotels727.components;

import com.metropolitan.methotels727.dao.RezervacijaDAO;
import com.metropolitan.methotels727.entities.Korisnik;
import java.util.Date;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Miroslav Stipanović 727
 */
public class KorisnikRezervacijaComp {

    @Property
    @Parameter(required=true)
    private Korisnik gost;
    @Inject
    private RezervacijaDAO reDAO;
    
    public boolean getAktivnaRezervacijaGosta(){
        return reDAO.aktivnaRezervacijaKorisnika(gost);
    }
    
    public String getAktivnaRezervacija(){
        return "Ima aktivnu rezervaciju";
    }
    
    public String getNeaktivnaRezervacija(){
        return "Nema aktivnu rezervaciju;";
    }
}
