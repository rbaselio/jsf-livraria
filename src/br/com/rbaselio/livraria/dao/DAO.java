package br.com.rbaselio.livraria.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

public class DAO<T> implements Serializable{

	private static final long serialVersionUID = -8483994915295132519L;
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager manager, Class<T> classe) {
		this.em = manager;
		this.classe = classe;
	}

	public void adiciona(T t) {
		em.persist(t);
	}

	public void remove(T t) {
		em.remove(em.merge(t));		
	}

	public void atualiza(T t) {
		em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}

	public T buscaPorId(Integer id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public int contaTodos() {
		long result = (Long) em.createQuery("select count(n) from livro n").getSingleResult();
		return (int) result;
	}

	public List<T> listaTodosPaginada(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(classe);

		Root<T> root = query.from(classe);

		List<Predicate> predicates = new ArrayList<>();

		if (filtros != null) {
			for (Entry<String, Object> entry : filtros.entrySet()) {
				predicates.add(cb.like(root.<String> get(entry.getKey()), "%" + entry.getValue() + "%"));
			}
			query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		}

		if (campoOrdenacao != null) {
			Order asc;
			if (sentidoOrdenacao == SortOrder.ASCENDING) {
				asc = cb.asc(root.get(campoOrdenacao));
			} else
				asc = cb.desc(root.get(campoOrdenacao));
			query = query.orderBy(asc);
		}

		List<T> lista = em.createQuery(query).setFirstResult(inicio).setMaxResults(quantidade).getResultList();
		return lista;
	}

	public int quantidadeDeElementos() {
		long result = (Long) em.createQuery("select count(n) from " + classe.getSimpleName() + " n").getSingleResult();
		return (int) result;
	}

}
