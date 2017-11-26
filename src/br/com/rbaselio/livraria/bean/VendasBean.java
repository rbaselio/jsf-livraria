package br.com.rbaselio.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.rbaselio.livraria.dao.LivroDao;
import br.com.rbaselio.livraria.modelo.Livro;
import br.com.rbaselio.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 7951249080034045243L;
	
	@Inject
	private LivroDao livroDao;

	public List<Venda> getVendas() {		
		
		List<Livro> livros = livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(1234);
		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade)); // o que será a
														// quantidade?
		}

		return vendas;

	}

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas();

		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie);

		return model;
	}

	public BarChartModel getVendasAnimatedModel() {
		BarChartModel animatedModel = getVendasModel();
		animatedModel.setTitle("Vendas");
		animatedModel.setAnimate(true);
		animatedModel.setLegendPosition("se");
		Axis yAxis = animatedModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(500);
		return animatedModel;
	}

}