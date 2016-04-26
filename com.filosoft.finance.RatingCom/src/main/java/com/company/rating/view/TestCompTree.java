package com.company.rating.view;

import java.util.ArrayList;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.model.SelectItem;

import org.primefaces.component.panel.Panel;

public class TestCompTree extends  AbstractView implements PhaseListener {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel  panel=new Panel();
	   private String  ad="hello";
	   private String valu;

	   private HtmlSelectOneMenu htmlSelectOneMenu=new HtmlSelectOneMenu();

        public TestCompTree() {
			// TODO Auto-generated constructor stub
		   System.out.println("in Constracter");
        }  

   public Panel getPanel() {
	     HtmlInputText htmlInputText=new HtmlInputText();
	     htmlInputText.setValueExpression("value", createValueEx("#{testCompTree.ad}", String.class));
	       htmlSelectOneMenu=(HtmlSelectOneMenu) getApplication().createComponent(HtmlSelectOneMenu.COMPONENT_TYPE);
	       htmlSelectOneMenu.setId("asasasas");
	      UISelectItems items=new UISelectItems();
	       items.setValueExpression("value", createValueEx("#{testCompTree.selectItems}", SelectItem[].class));
	       htmlSelectOneMenu.getChildren().add(items);
	      panel.getChildren().add(htmlSelectOneMenu);
	      htmlSelectOneMenu.setValueExpression("value", createValueEx("#{testCompTree.valu}",String.class));
	     panel.getChildren().add(htmlInputText);
	     return panel;
}


public String getAd() {
	return ad;
}


public void setAd(String ad) {
	this.ad = ad;
}

public String actionT(){
	System.out.println("yyyyyy------------>"+valu);
	
	return "";
}


public String getValu() {
	return valu;
}


public void setValu(String valu) {
	this.valu = valu;
}

public SelectItem[] getSelectItems() {
	ArrayList<SelectItem> arrayList=new ArrayList<SelectItem>();
	SelectItem item=new SelectItem();
	SelectItem item2=new SelectItem();
	 item.setValue("1");
     
     item.setLabel("haber");
     item2.setValue("2");
     item2.setLabel("yok");
     arrayList.add(item);
     arrayList.add(item2);
	return arrayList.toArray(new SelectItem[2]);
}



public void setPanel(Panel panel) {
	this.panel = panel;
}




public PhaseId getPhaseId() {
	// TODO Auto-generated method stub
	return PhaseId.ANY_PHASE;
}
public void beforePhase(PhaseEvent event) {

    PhaseId phaseId = event.getPhaseId();
    if (PhaseId.RESTORE_VIEW.equals(phaseId)) {
        beforeRestoreView();
    } else if (PhaseId.APPLY_REQUEST_VALUES.equals(phaseId)) {
        beforeApplyRequestValues();
    } else if (PhaseId.PROCESS_VALIDATIONS.equals(phaseId)) {
        beforeProcessValidations();
    } else if (PhaseId.UPDATE_MODEL_VALUES.equals(phaseId)) {
        beforeUpdateModelValues();
    } else if (PhaseId.INVOKE_APPLICATION.equals(phaseId)) {
        beforeInvokeApplication();
    } else if (PhaseId.RENDER_RESPONSE.equals(phaseId)) {
        if (!FacesContext.getCurrentInstance().getResponseComplete()) {
            beforeRenderResponse();
        }
    }

}


/**
 * <p>Call through to the appropriate "after event" method,
 * depending upon the <code>PhaseId</code> in this event.</p>
 *
 * @param event <code>PhaseEvent</code> to be processed
 */
public void afterPhase(PhaseEvent event) {

    PhaseId phaseId = event.getPhaseId();
    if (PhaseId.RESTORE_VIEW.equals(phaseId)) {
        afterRestoreView();
    } else if (PhaseId.APPLY_REQUEST_VALUES.equals(phaseId)) {
        afterApplyRequestValues();
    } else if (PhaseId.PROCESS_VALIDATIONS.equals(phaseId)) {
        afterProcessValidations();
    } else if (PhaseId.UPDATE_MODEL_VALUES.equals(phaseId)) {
        afterUpdateModelValues();
    } else if (PhaseId.INVOKE_APPLICATION.equals(phaseId)) {
        afterInvokeApplication();
    } else if (PhaseId.RENDER_RESPONSE.equals(phaseId)) {
        if (!FacesContext.getCurrentInstance().getResponseComplete()) {
            afterRenderResponse();
        }
    }

}


// ---------------------------------------------------- PhaseEvent Callbacks


// These methods are called by beforePhase() and afterPhase() as appropriate
// and allow subclasses to perform additional tasks at the corresponding
// moment in the request processing lifecycle for each request.  The default
// implementations do nothing.

protected void beforeRestoreView() {
	System.out.println("beforeRestoreView"+"..."+valu);
	}
protected void afterRestoreView() {
	System.out.println("afterRestoreView"+"..."+valu);
	}
protected void beforeApplyRequestValues() {
	System.out.println("beforeApplyRequestValues"+"..."+valu);
	}
protected void afterApplyRequestValues() {
	System.out.println("afterApplyRequestValues"+"..."+valu);
	}
protected void beforeProcessValidations() {
	System.out.println("beforeProcessValidations"+"..."+valu);
	}
protected void afterProcessValidations() {
	System.out.println("afterProcessValidations"+"..."+valu);
	}
protected void beforeUpdateModelValues() {
	System.out.println("beforeUpdateModelValues"+"..."+valu);
	}
protected void afterUpdateModelValues() {
	System.out.println("afterUpdateModelValues"+"..."+valu);
	}
protected void beforeInvokeApplication() {
	System.out.println("beforeInvokeApplication"+"..."+valu);
	}
protected void afterInvokeApplication() {
	System.out.println("afterInvokeApplication"+"..."+valu);
	}
protected void beforeRenderResponse() {
	System.out.println("beforeRenderResponse"+"..."+valu);
	}
protected void afterRenderResponse() {
	System.out.println("afterRenderResponse"+"..."+valu);
	}

//@Override
//public void processValueChange(ValueChangeEvent arg0)
//		throws AbortProcessingException {
//	// TODO Auto-generated method stub
//	System.out.println(arg0.getNewValue());
//	System.out.println(arg0.getOldValue());
//}


}
