package br.com.rbaselio.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {
	private static final long serialVersionUID = -3253769778311356560L;

	@Inject
	EntityManager manager;

	@AroundInvoke
	public Object executaTX(InvocationContext context) throws Exception {
		manager.getTransaction().begin();
		Object proceed = context.proceed();
		manager.getTransaction().commit();
		return proceed;
	}

}
