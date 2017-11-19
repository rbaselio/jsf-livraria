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
import br.com.rbaselio.livraria.util.RedirectView;

@ManagedBean(name = "livroBean")
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 2279386244429111128L;

	private Livro livro = new Livro();
	private Integer autorID;

	private Integer livroId;

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

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

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public Livro getLivro() {
		return livro;
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorID);
		this.livro.adicionaAutor(autor);

	}

	public RedirectView formAutor() {
		System.out.println("Chamanda o formulario do Autor");
		return new RedirectView("autor");
	}

	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}
		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		this.livro = new Livro();

	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}

	public void carregaPelaId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"Deveria começar com 1"));
		}
	}

}
