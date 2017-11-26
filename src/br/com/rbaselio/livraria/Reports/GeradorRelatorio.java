package br.com.rbaselio.livraria.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.rbaselio.livraria.dao.LivroDao;
import br.com.rbaselio.livraria.modelo.Livro;

@Named
@ViewScoped
public class GeradorRelatorio implements Serializable{
	
	private static final long serialVersionUID = -4098362238195259348L;
	
	@Inject
	private LivroDao dao;
	@Inject
	FacesContext context;

	public void geraPDFParaOutputStream() {
		
		ExternalContext externalContext = context.getExternalContext();
        ServletContext contextS = (ServletContext) externalContext.getContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        String arquivo = contextS.getRealPath("/WEB-INF/reports/GastoPorGenero.jasper");
		
		try {			
			List<Livro> livros = dao.listaTodos();
			List<LivroReport> livroReports = new ArrayList<LivroReport>();
			for (Livro livro : livros) {
				livroReports.add(new LivroReport(livro));
			}
			
			JRDataSource dataSource = new JRBeanCollectionDataSource(livroReports);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			
			JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)) , servletOutputStream, null, dataSource);
	                   

			response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();

		} catch (JRException | IOException e) {
			
			e.printStackTrace();
		}

	}

}
