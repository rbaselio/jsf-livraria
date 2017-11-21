package br.com.rbaselio.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Livro;
import br.com.rbaselio.livraria.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {

    public List<Venda> getVendas() {

        List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
        List<Venda> vendas = new ArrayList<Venda>();
        
        Random random = new Random(1234);
        for (Livro livro : livros) {
        	Integer quantidade = random.nextInt(500);
            vendas.add(new Venda(livro, quantidade)); // o que será a quantidade?
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