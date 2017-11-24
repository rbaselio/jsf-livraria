package br.com.rbaselio.livraria.Reports;

import java.time.LocalDate;
import java.util.List;

import br.com.rbaselio.livraria.modelo.Autor;
import br.com.rbaselio.livraria.modelo.Livro;

public class LivroReport {
	
	private Livro livro;
	
	public LivroReport(Livro livro) {
		this.livro = livro;
	}
	
	
	public LocalDate getDataLancamento() {
		return this.livro.getDataLancamento();
	}

	
	public String getNome() {
		List<Autor> autores = this.livro.getAutores();
		StringBuilder str = new StringBuilder();
		for (Autor autor : autores) {
			if (str.length() != 0) {
				str.append(", ");
		    }
			str.append(autor.getNome());
		}
		return str.toString();
		
	}

	
	public Integer getId() {
		return this.livro.getId();
	}

	

	public String getTitulo() {
		return this.livro.getTitulo();
	}

	public String getIsbn() {
		return this.livro.getIsbn();
	}	

	public double getPreco() {
		return this.livro.getPreco();
	}

		public String getGenero() {
		return this.livro.getGenero();
	}

	
	
	

}
