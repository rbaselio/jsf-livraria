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

@SuppressWarnings({ "unused", "deprecation" })
@ManagedBean(name = "autorBean")
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Integer autorId;

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
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		this.autor = new Autor();
		// return null;
	}

	public void carregaPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);

	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}

	public void remover(Autor autor) {
		try {
			new DAO<Autor>(Autor.class).remove(autor);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Autor atribuido a um livro, não pode ser removido"));
		}
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

}
