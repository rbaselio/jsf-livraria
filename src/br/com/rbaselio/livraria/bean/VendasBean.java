package br.com.rbaselio.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.rbaselio.livraria.dao.VendasDao;
import br.com.rbaselio.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 7951249080034045243L;
	
	
	@Inject
	private VendasDao dao;
	
	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2017");

		List<Venda> vendas = dao.getVendas();

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
		yAxis.setMax(2500);
		return animatedModel;
	}

}