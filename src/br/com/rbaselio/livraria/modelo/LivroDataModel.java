package br.com.rbaselio.livraria.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.rbaselio.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5901855682971158988L;
	DAO<Livro> dao = new DAO<Livro>(Livro.class);
	
	public LivroDataModel() {
	    super.setRowCount(dao.quantidadeDeElementos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		return dao.listaTodosPaginada(inicio, quantidade, campoOrdenacao, sentidoOrdenacao, filtros);
	}
	
	

}
