package br.com.rbaselio.livraria.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.primefaces.model.SortOrder;

import br.com.rbaselio.livraria.modelo.Livro;

public class LivroDao implements Serializable{
	
	private static final long serialVersionUID = 1081772129183648415L;
	
	@Inject
	EntityManager em;
	private DAO<Livro> dao;
	
	@PostConstruct
	void init() {
		dao = new DAO<Livro>(em, Livro.class);
	}

	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	public void remove(Livro t) {
		dao.remove(t);
	}

	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}

	public List<Livro> listaTodosPaginada(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		return dao.listaTodosPaginada(inicio, quantidade, campoOrdenacao, sentidoOrdenacao, filtros);
	}

	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}
	
	
	

}
