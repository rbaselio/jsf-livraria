package br.com.rbaselio.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbaselio.livraria.modelo.Usuario;
import br.com.rbaselio.livraria.util.RedirectView;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 3852723270938118526L;
	
	@Inject
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public RedirectView efetuaLogin() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuario != null ) {			
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);	
			return new RedirectView("livro");
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage("messages",	new FacesMessage("Usuario ou senha incorreto"));
		return new RedirectView("login");		
		
	}
	
	public RedirectView efetuaLogoff() {

	    FacesContext context = FacesContext.getCurrentInstance();
	    context.getExternalContext().getSessionMap().remove("usuarioLogado");

	    return new RedirectView("login");
	}

}