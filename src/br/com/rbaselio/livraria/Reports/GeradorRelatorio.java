package br.com.rbaselio.livraria.Reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.rbaselio.livraria.dao.DAO;
import br.com.rbaselio.livraria.modelo.Livro;

@SuppressWarnings("deprecation")
@ManagedBean(name = "geradorRelatorio")
@ViewScoped
public class GeradorRelatorio {

	public void geraPDFParaOutputStream() {
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext contextS = (ServletContext) externalContext.getContext();

        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        String arquivo = contextS.getRealPath("/WEB-INF/reports/GastoPorGenero.jasper");
		
		try {			
			List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
			List<LivroReport> livroReports = new ArrayList<LivroReport>();
			for (Livro livro : livros) {
				livroReports.add(new LivroReport(livro));
			}
			
			JRDataSource dataSource = new JRBeanCollectionDataSource(livroReports);
			
			 servletOutputStream = response.getOutputStream();
			JasperRunManager.runReportToPdfStream(new FileInputStream(
                    new File(arquivo)) , servletOutputStream, null, dataSource);
	                   

			response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
			
			
			
			
			
			
			System.out.println("4>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		} catch (JRException | IOException e) {
			
			e.printStackTrace();
		}

	}

}
