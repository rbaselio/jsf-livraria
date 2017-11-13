package br.com.rbaselio.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Autor;

@SuppressWarnings("unused")
@ManagedBean(name = "autorBean")
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		this.autor = new Autor();
	}
	
	
}
