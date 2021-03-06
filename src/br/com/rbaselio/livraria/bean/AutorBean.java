package br.com.rbaselio.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbaselio.livraria.dao.AutorDao;
import br.com.rbaselio.livraria.modelo.Autor;
import br.com.rbaselio.livraria.tx.Transacional;
import br.com.rbaselio.livraria.util.RedirectView;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 2377084533515043600L;
	private Autor autor = new Autor();
	private Integer autorId;
	
	@Inject
	private AutorDao dao;
	
	@Inject
	FacesContext context;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}

	@Transacional
	public RedirectView gravar() {

		if (this.autor.getId() == null) {
			dao.adiciona(this.autor);
		} else {
			dao.atualiza(this.autor);
		}
		this.autor = new Autor();
		 return new RedirectView("livro");
	}

	public void carregaPelaId() {
		this.autor = dao.buscaPorId(autorId);

	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}
	
	@Transacional
	public void remover(Autor autor) {
		try {
			dao.remove(autor);
		} catch (Exception e) {
			context.addMessage("autor", new FacesMessage("Autor atribuido a um livro, n�o pode ser removido"));
		}
	}

	public List<Autor> getAutores() {
		return dao.listaTodos();
	}

}
