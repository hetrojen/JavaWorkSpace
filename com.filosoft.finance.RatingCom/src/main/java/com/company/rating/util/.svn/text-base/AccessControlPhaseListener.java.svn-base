package com.company.rating.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import com.company.rating.view.GeneralData;

public class AccessControlPhaseListener implements  PhaseListener{

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		FacesContext context = arg0.getFacesContext();
	
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
		
		GeneralData  generalData = (GeneralData) session.getAttribute("generalData");
	
		 if((generalData==null || generalData.getKullanici()==null)  && !"/LoginView.xhtml".equals(context.getViewRoot().getViewId())){
			   context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
		   }
		
		 if((generalData!=null && generalData.getKullanici()!=null && generalData.getFirma()!=null)  && "/LoginView.xhtml".equals(context.getViewRoot().getViewId())){
			   session.invalidate();
		   }
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	
	
	   
	        

		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
