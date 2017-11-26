package br.com.rbaselio.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.rbaselio.livraria.modelo.Autor;

public class AutorDao implements Serializable{

	private static final long serialVersionUID = -812969768977769307L;
	@Inject
	EntityManager em;
	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		dao = new DAO<Autor>(em, Autor.class);
	}

	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	public void remove(Autor t) {
		dao.remove(t);
	}

	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	

}
