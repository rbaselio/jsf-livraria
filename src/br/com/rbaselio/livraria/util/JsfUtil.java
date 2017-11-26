package br.com.rbaselio.livraria.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class JsfUtil {
	
	@Produces
	@RequestScoped
	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();	
	}
	

}
