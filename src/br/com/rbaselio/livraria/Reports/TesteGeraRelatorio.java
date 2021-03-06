package br.com.rbaselio.livraria.Reports;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import br.com.rbaselio.livraria.dao.LivroDao;
import br.com.rbaselio.livraria.modelo.Livro;

public class TesteGeraRelatorio {

	private static JasperPrint print;
	
	@Inject
	private static LivroDao dao;

	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

		JasperCompileManager.compileReportToFile("resources/reports/GastoPorGenero.jrxml"); 
		
		List<Livro> livros = dao.listaTodos();
		List<LivroReport> livroReports = new ArrayList<LivroReport>();
		for (Livro livro : livros) {
			livroReports.add(new LivroReport(livro));		}
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(livroReports);
		
		print = JasperFillManager.fillReport("resources/reports/GastoPorGenero.jasper", null, dataSource);

		JasperExportManager.exportReportToPdfFile(print,"c:/temp/GastoPorGenero.pdf");
		
		
		JasperViewer viewer = new JasperViewer(print);
		viewer.setTitle("Relatorio");
		viewer.setDefaultCloseOperation(0);
		viewer.setVisible(true);	

		
	}
}
