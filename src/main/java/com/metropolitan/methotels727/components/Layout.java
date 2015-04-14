package com.metropolitan.methotels727.components;

import com.metropolitan.methotels727.entities.Korisnik;
import org.apache.tapestry5.*;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

/**
 * Layout component for pages of application
 * @author Miroslav Stipanovic 727
 */
//@Import(module="bootstrap/collapse")
@Import(stylesheet="context:css/layout.css")
public class Layout
{
	@Inject
	private ComponentResources resources;

	/**
	 * The page title, for the <title> element and the <h1> element.
	 */
	@Property
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	private String pageName;

	@Property
	@Inject
	@Symbol(SymbolConstants.APPLICATION_VERSION)
	private String appVersion;
        
        @SessionState
        private Korisnik ulogovaniKorisnik;
        @Property
        @SessionState
        private String ulogovaniEmail;



	public String getClassForPageName()
	{
		return resources.getPageName().equalsIgnoreCase(pageName)
				? "active"
				: null;
	}

	public String[] getPageNames()
	{
		return new String[]{"Index", "Sobe", "Novosti", "SpecijalnePonude", "ONama", "Kontakt", "AdminPanel"};
	}

        public String prepare(String pageName) 
        {
            if(pageName.equals("Index")){
                return pageName.replace("Index", "Po\u010Detna"); 
            }
            else if(pageName.equals("SpecijalnePonude")){
                return pageName.replace("ne", "ne "); 
            }
            else if(pageName.equals("ONama")){
                return pageName.replace("ON", "O N"); 
            }
            else if(pageName.equals("Kontakt")){
                return pageName.replace("kt", "ktirajte nas"); 
            } else
                return pageName;
        }
        
        public boolean getUlogovan(){
        if(ulogovaniKorisnik.getKEmail()!=null)
            return true;
        else
            return false;
        }
    
        public void onActionFromLogout(){
            ulogovaniKorisnik=null;
            ulogovaniEmail="";
        }
}
