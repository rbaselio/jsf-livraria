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

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 2377084533515043600L;
	private Autor autor = new Autor();
	private Integer autorId;
	
	@Inject
	private AutorDao dao;	

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {

		if (this.autor.getId() == null) {
			dao.adiciona(this.autor);
		} else {
			dao.atualiza(this.autor);
		}
		this.autor = new Autor();
		// return null;
	}

	public void carregaPelaId() {
		this.autor = dao.buscaPorId(autorId);

	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}

	public void remover(Autor autor) {
		try {
			dao.remove(autor);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Autor atribuido a um livro, não pode ser removido"));
		}
	}

	public List<Autor> getAutores() {
		return dao.listaTodos();
	}

}
