package com.company.rating.view.model;


public class YatayAnalizGrafikModel extends GrafikModel{
private static final String UP_ARROW="/img/up.png";
private static final String DOWN_ARROW="/img/down.png";
private static final String SAME_ARROW="/img/same.png";

private String  arrowImgPath;
private String nameStyle;
	
	public YatayAnalizGrafikModel(GelirbilancoModel model) {
		// TODO Auto-generated constructor stub
         super(model);
 		
         
	}

	public String getArrowImgPath() {
		  if(getPercent().startsWith("-")){
			  return  DOWN_ARROW;
		  }else
		  if(getPercent().startsWith("0")){
			  return SAME_ARROW; 
		  }else{
			  return UP_ARROW;
		  }
		
		
	}

	public void setArrowImgPath(String arrowImgPath) {
		this.arrowImgPath = arrowImgPath;
	}

	public String getNameStyle() {
		if(super.getModel().getGelirbilancocode().getKod()<10){
			return "outputLB";
		}else{
			return "outputL";
		}
	
	}






}
