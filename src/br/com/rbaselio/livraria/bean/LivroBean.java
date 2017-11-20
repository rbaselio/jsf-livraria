package br.com.rbaselio.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
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
import br.com.rbaselio.livraria.modelo.LivroDataModel;
import br.com.rbaselio.livraria.util.RedirectView;

@SuppressWarnings("deprecation")
@ManagedBean(name = "livroBean")
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 2279386244429111128L;

	private Livro livro = new Livro();
	private Integer autorID;

	private Integer livroId;

	//private List<Livro> livros;
	
	private LivroDataModel livroDataModel = new LivroDataModel();

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	/*public List<Livro> getLivros() {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (livros == null) this.livros = dao.listaTodos();
		return livros;
	}
*/
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
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (this.livro.getId() == null) {
			dao.adiciona(this.livro);
			//this.livros = dao.listaTodos();
		} else {
			dao.atualiza(this.livro);
		}
		this.livro = new Livro();

	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		dao.remove(livro);
		
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
