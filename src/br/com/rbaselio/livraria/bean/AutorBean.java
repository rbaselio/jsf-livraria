package br.com.rbaselio.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Autor;
import br.com.rbaselio.livraria.modelo.Livro;
import br.com.rbaselio.livraria.util.RedirectView;

@SuppressWarnings("unused")
@ManagedBean(name = "autorBean")
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public RedirectView  gravar() {
		
		if (this.autor.getId() == null) {
	        new DAO<Autor>(Autor.class).adiciona(this.autor);        
	    } else {
	        new DAO<Autor>(Autor.class).atualiza(this.autor);
	    }
		this.autor = new Autor();
		return new RedirectView ("livro");

	}
	
	public void carregar(Autor autor) {
	    System.out.println("Carregando livro " + autor.getNome());
	    this.autor = autor;
	}
	
	public void remover(Autor autor) {
	    System.out.println("Removendo livro " + autor.getNome());
	    
	    try{
	    	new DAO<Autor>(Autor.class).remove(autor);
	    }
	    catch(Exception e){
	    	FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Autor atribuido a um livro, não pode ser removido"));			
	    }
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

}
