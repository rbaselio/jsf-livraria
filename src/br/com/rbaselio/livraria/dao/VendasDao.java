package br.com.rbaselio.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.rbaselio.livraria.modelo.Venda;

public class VendasDao implements Serializable{

	private static final long serialVersionUID = -812969768977769307L;
	@Inject
	EntityManager em;
	private DAO<Venda> dao;	
	
	@PostConstruct
	void init() {
		dao = new DAO<Venda>(em, Venda.class);
	}	
	
	public List<Venda> getVendas() {		
		return this.em.createQuery("select v from Venda v", Venda.class).getResultList();		
	}
	

	public void adiciona(Venda t) {
		dao.adiciona(t);
	}

	public void remove(Venda t) {
		dao.remove(t);
	}

	public void atualiza(Venda t) {
		dao.atualiza(t);
	}

	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

	public Venda buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	public int hashCode() {
		return dao.hashCode();
	}

	

}
