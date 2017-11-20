package br.com.rbaselio.livraria.dao;

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

public class DAO<T> {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(t);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from livro n")
				.getSingleResult();
		em.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int inicio, int quantidade,
			String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, Object> filtros) {
		
		EntityManager em = new JPAUtil().getEntityManager();
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

		List<T> lista = em.createQuery(query).setFirstResult(inicio)
				.setMaxResults(quantidade).getResultList();

		em.close();
		return lista;
	}

	public int quantidadeDeElementos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery(
				"select count(n) from " + classe.getSimpleName() + " n")
				.getSingleResult();
		em.close();

		return (int) result;
	}

}
