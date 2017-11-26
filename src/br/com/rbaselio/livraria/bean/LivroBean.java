package br.com.rbaselio.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.rbaselio.livraria.dao.AutorDao;
import br.com.rbaselio.livraria.dao.LivroDao;
import br.com.rbaselio.livraria.modelo.Autor;
import br.com.rbaselio.livraria.modelo.Livro;
import br.com.rbaselio.livraria.modelo.LivroDataModel;
import br.com.rbaselio.livraria.tx.Transacional;
import br.com.rbaselio.livraria.util.RedirectView;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 2279386244429111128L;

	private Livro livro = new Livro();
	private Integer autorID;
	private Integer livroId;

	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;	
	
	@Inject
	FacesContext context;
	
	@Inject
	private LivroDataModel livroDataModel;

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	public Integer getAutorID() {
		return autorID;
	}

	public void setAutorID(Integer autorID) {
		this.autorID = autorID;
	}

	public void setLivro(Livro livro) {
		this.livro = livroDao.buscaPorId(livro.getId());
	}

	public List<Autor> getAutores() {
		return autorDao.listaTodos();
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
		Autor autor = autorDao.buscaPorId(this.autorID);
		this.livro.adicionaAutor(autor);

	}

	public RedirectView formAutor() {
		System.out.println("Chamanda o formulario do Autor");
		return new RedirectView("autor");
	}
	
	@Transacional
	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}
		
		if (this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
			//this.livros = livroDao.listaTodos();
		} else {
			livroDao.atualiza(this.livro);
		}
		this.livro = new Livro();

	}

	@Transacional
	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		livroDao.remove(livro);
		
	}

	public void carregaPelaId() {
		this.livro = livroDao.buscaPorId(this.livroId);
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"Deveria começar com 1"));
		}
	}

	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public void setLivroDataModel(LivroDataModel livroDataModel) {
		this.livroDataModel = livroDataModel;
	}
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	public List<String> getGeneros() {
	    return generos;
	}

}
