package br.com.rbaselio.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.rbaselio.livraria.modelo.Usuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void afterPhase(PhaseEvent event) {	
		
		FacesContext context = FacesContext.getCurrentInstance();
		String nomePagina = context.getViewRoot().getViewId();
		if ("/login.xhtml".equals(nomePagina)) {
			return;
		}

		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		if (usuarioLogado != null) {
			return;
		}

		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, new RedirectView("login").toString());
		context.renderResponse();

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}