package br.com.rbaselio.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		if (emf == null) emf = Persistence.createEntityManagerFactory("livraria");		
		return emf.createEntityManager();
	}		
	
	public void close(@Disposes EntityManager em) {		
		em.close();		
	}
}
