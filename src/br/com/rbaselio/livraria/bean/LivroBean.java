package br.com.rbaselio.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Autor;
import br.com.rbaselio.livraria.modelo.Livro;

@ManagedBean(name = "livroBean")
@ViewScoped
public class LivroBean {

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
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

}
