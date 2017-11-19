package br.com.rbaselio.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.rbaselio.livraria.dao.UsuarioDao;
import br.com.rbaselio.livraria.modelo.Usuario;
import br.com.rbaselio.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public RedirectView efetuaLogin() {
		System.out.println("Fazendo login do usuário "+ this.usuario.getEmail());		
		
		this.usuario = new UsuarioDao().existe(this.usuario);
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