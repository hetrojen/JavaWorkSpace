package com.company.rating.form;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.rating.service.RatingService;
import com.company.rating.view.GeneralData;
//@Component("uploadViewData")
//@Scope("session") 
public class UploadViewData implements Serializable{
	public  static final int FILE_UPLOAD=1;
	public  static final int EXISTING_KAYIT=2;
	public  static final int SELECT_DATA_CREATE_METHOD=3;
	public  static final int SHOW_UPLOADED_FILE=4;
    private boolean visibleFileUpload;
    private boolean visibleShowExistingKayit;
    private boolean visibleDataCreateMethod;
    private boolean visibleUploadedFile;
    private String gelirbilancoLink;
    
  
    private UploadedFile file;
    @Autowired
    private GeneralData generalData;
    
     @Autowired
     private RatingService ratingService;
 
 
	@Autowired
	private  GelirBilancoViewData bilancoViewData;
    public UploadViewData() {
		// TODO Auto-generated constructor stub
          System.out.println();
    	
	}

	@PostConstruct
	public void initiate() {

		Calendar cal = Calendar.getInstance();
		int yil_ = cal.get(Calendar.YEAR);
		int donem_ = 1;
        generalData.setDonem_combo(donem_+"");
        generalData.setYil_combo(yil_+"");
        

		boolean kayitExist = ratingService.isGelirBilancoKayitExist(yil_, donem_,generalData.getFirma().getIdFirma());
		if (kayitExist) {
			setCurrentPanel(EXISTING_KAYIT);
			bilancoViewData.setFromExistingKayit(true);
		} else {
			setCurrentPanel(SELECT_DATA_CREATE_METHOD);
		}

	}
  
      public void  setCurrentPanel(int panel){
    	  visibleDataCreateMethod=false;
    	  visibleFileUpload=false;
    	  visibleShowExistingKayit=false;
    	  visibleUploadedFile=false;
    	  
    	  switch (panel) {
		case FILE_UPLOAD:
			visibleFileUpload=true;
			break;
        case EXISTING_KAYIT:
        	visibleShowExistingKayit=true;
			break;
        case SELECT_DATA_CREATE_METHOD:
        	visibleDataCreateMethod=true;
	   break;
        case SHOW_UPLOADED_FILE:
        	visibleUploadedFile=true;
	    break;
		
		}
    	  
    	  
      }

	


	public boolean isVisibleFileUpload() {
		return visibleFileUpload;
	}
	public boolean isVisibleShowExistingKayit() {
		return visibleShowExistingKayit;
	}
	public boolean isVisibleDataCreateMethod() {
		return visibleDataCreateMethod;
	}
	public boolean isVisibleUploadedFile() {
		return visibleUploadedFile;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getGelirbilancoLink() {
		return gelirbilancoLink;
	}





	public void setGelirbilancoLink(String gelirbilancoLink) {
		this.gelirbilancoLink = gelirbilancoLink;
	}

	
    
}
