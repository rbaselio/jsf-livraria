package br.com.rbaselio.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.rbaselio.livraria.modelo.Usuario;

public class UsuarioDao {

	public Usuario existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where "
										+ "u.email = :pEmail and "
										+ "u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
	    query.setParameter("pSenha", usuario.getSenha());
	    
	    System.out.println("CRIPTOGRAFIA " + usuario.getSenha());
	    
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
