package br.com.rbaselio.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Autor;
import br.com.rbaselio.livraria.modelo.Livro;

@ManagedBean(name = "livroBean")
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 2279386244429111128L;

	private Livro livro = new Livro();
	private Integer autorID;

	public Integer getAutorID() {
		return autorID;
	}

	public void setAutorID(Integer autorID) {
		this.autorID = autorID;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.getLivro().getAutores();
	}

	public Livro getLivro() {
		return livro;
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorID);
		livro.adicionaAutor(autor);

	}

	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}
		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria come�ar com 1"));
		}
	}

}
