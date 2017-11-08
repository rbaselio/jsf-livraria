package br.com.rbaselio.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Livro;

@ManagedBean(name = "livroBean")
public class LivroBean {

	private Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

}
