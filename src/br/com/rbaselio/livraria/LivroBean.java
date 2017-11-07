package br.com.rbaselio.livraria;

import javax.faces.bean.ManagedBean;

@SuppressWarnings("deprecation")
@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();
	
	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
	}

	

}
