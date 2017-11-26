package br.com.rbaselio.livraria.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.rbaselio.livraria.modelo.Usuario;

public class UsuarioDao {
	
	@Inject
	EntityManager em;
	

	public Usuario existes(Usuario usuario) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where "
										+ "u.email = :pEmail and "
										+ "u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
	    query.setParameter("pSenha", usuario.getSenha());    
	    
	    
	    try {
	    	usuario = query.getSingleResult();	    	
	    }
	    catch (NoResultException ex){
	    	return null;
	    }			
		em.close();		
		return usuario;
	}

}
