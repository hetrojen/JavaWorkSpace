package com.company.rating.view.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import com.company.rating.domain.Gelirbilancocode;

public class GelirbilancoModel implements Serializable{
	private BigDecimal oncekidonem = new BigDecimal(0);
	private BigDecimal cariDonem = new BigDecimal(0);
	private BigDecimal cariRevizyon=new BigDecimal(0);
	private int type;
	private Gelirbilancocode gelirbilancocode;
	private BigDecimal gelirTableFormulResultCari = new BigDecimal(0);
	private BigDecimal gelirTableFormulResultOnceki = new BigDecimal(0);
	private BigDecimal gelirTableFormulRevizeCari = new BigDecimal(0);
	private boolean autoCalc;
	private boolean disableDonem;
	private String  styleClass;
	private String codeText;
	private ArrayList<GelirbilancoModel> childs;
	public static final int GELIR = 1;
	public static final int BILANCO = 2;
	public static final int  CARI_DONEM=1;
	public static final int ONCEKI_DONEM=2;
	public static final int REVIZE_CARI=3;
	public static HashMap<String, String> gelirTableFormulas = new HashMap<String, String>();
    
	static{

        gelirTableFormulas.put("1001", "S[$60,$61]");
		 gelirTableFormulas.put("1002", "S[$60,$61,$62]");
		 gelirTableFormulas.put("1003", "S[$60,$61,$62,$63]");
		 gelirTableFormulas.put("1004", "A[$64,S[$60,$61,$62,$63,$65,$66]]");
		 gelirTableFormulas.put("68", "A[$680,$681,$689]");
		 gelirTableFormulas.put("690", "A[$64,$67,S[$60,$61,$62,$63,$65,$66,$68]]");
		 gelirTableFormulas.put("692", "A[$64,$67,S[$60,$61,$62,$63,$65,$66,$68,$691]]");
	}

	public GelirbilancoModel(Gelirbilancocode g) {
		// TODO Auto-generated constructor stub
		gelirbilancocode = g;
	}

	public BigDecimal getOncekidonem() {
		if (type != GELIR) {
			if (childs == null) {
			
			
				return oncekidonem;
			} else {
				BigDecimal sum = new BigDecimal(0);
				for (GelirbilancoModel model : childs) {
					if (model.getGelirbilancocode().getName().contains("(-)")) {
						sum = sum.subtract(model.getOncekidonem());
					} else {
						sum = sum.add(model.getOncekidonem());
					}
				}
			
				return sum;
			}

		}
		if (type == GELIR) {
		
			if (!autoCalc) {
				if (childs == null) {
				
					return oncekidonem;
					
				} else {
					BigDecimal sum = new BigDecimal(0);
					for (GelirbilancoModel model : childs) {
						if (model.getGelirbilancocode().getName().contains("(-)")) {
							sum = sum.subtract(model.getOncekidonem());
						} else {
							sum = sum.add(model.getOncekidonem());
						}
					}
				
					return sum;
				}

			} else {
				
				return gelirTableFormulResultOnceki;
			}

		}

		return oncekidonem;
	}

	public void setOncekidonem(BigDecimal oncekidonem) {
		this.oncekidonem = oncekidonem;
	}

	public BigDecimal getCariDonem() {
		if (type != GELIR) {
			if (childs == null) {
				return cariDonem;
			} else {
				BigDecimal sum = new BigDecimal(0);
				for (GelirbilancoModel model : childs) {
					if (model.getGelirbilancocode().getName().contains("(-)")) {
						sum = sum.subtract(model.getCariDonem());
					} else {
						sum = sum.add(model.getCariDonem());
					}
				}
				
				return sum;
			}

		}
		if (type == GELIR) {
			
			if (!autoCalc) {
				if (childs == null) {
					return cariDonem;
				} else {
					BigDecimal sum = new BigDecimal(0);
					for (GelirbilancoModel model : childs) {
						if (model.getGelirbilancocode().getName().contains("(-)")) {
							sum = sum.subtract(model.getCariDonem());
						} else {
							sum = sum.add(model.getCariDonem());
						}
					}
				
					return sum;
				}

			} else {
			
				return gelirTableFormulResultCari;
			}

		}

		return cariDonem;
	}

	
	public BigDecimal  getRevizeCari(){
		if (type != GELIR) {
			if (childs == null) {
				return cariDonem.add(cariRevizyon);
			} else {
				BigDecimal sum = new BigDecimal(0);
				for (GelirbilancoModel model : childs) {
					if (model.getGelirbilancocode().getName().contains("(-)")) {
						sum = sum.subtract(model.getRevizeCari());
					} else {
						sum = sum.add(model.getRevizeCari());
					}
				}
			
				return sum;
			}

		}
		if (type == GELIR) {
			
			if (!autoCalc) {
				if (childs == null) {
					return cariDonem.add(cariRevizyon);
				} else {
					BigDecimal sum = new BigDecimal(0);
					for (GelirbilancoModel model : childs) {
						if (model.getGelirbilancocode().getName().contains("(-)")) {
							sum = sum.subtract(model.getRevizeCari());
						} else {
							sum = sum.add(model.getRevizeCari());
						}
					}
			
					return sum;
				}

			} else {
			
				return gelirTableFormulRevizeCari;
			}

		}

		return cariDonem.add(cariRevizyon);
	}
	public void setCariDonem(BigDecimal cariDonem) {
		this.cariDonem = cariDonem;System.out.println(cariDonem.toString());
	}

	public boolean isAutoCalc() {
		return autoCalc;
	}

	public void setAutoCalc(boolean autoCalc) {
		this.autoCalc = autoCalc;
	}

	@Override
	public boolean equals(Object obj) {
		if(gelirbilancocode==null){
			return false;
		}
		// TODO Auto-generated method stub
		if (!(obj instanceof GelirbilancoModel)) {
			return false;
		}
		GelirbilancoModel p = (GelirbilancoModel) obj;
		if (this.gelirbilancocode.getKod() == p.getGelirbilancocode().getKod()) {
			return true;
		}
		return false;
	}

	public Gelirbilancocode getGelirbilancocode() {
		return gelirbilancocode;
	}

	public void setGelirbilancocode(Gelirbilancocode gelirbilancocode) {
		this.gelirbilancocode = gelirbilancocode;
	}

	public ArrayList<GelirbilancoModel> getChilds() {
		return childs;
	}

	public void setChilds(ArrayList<GelirbilancoModel> childs) {
		this.childs = childs;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BigDecimal getGelirTableFormulResultCari() {
		return gelirTableFormulResultCari;
	}

	public void setGelirTableFormulResultCari(BigDecimal gelirTableFormulResultCari) {
		this.gelirTableFormulResultCari = gelirTableFormulResultCari;
	}

	public BigDecimal getGelirTableFormulResultOnceki() {
		return gelirTableFormulResultOnceki;
	}

	public void setGelirTableFormulResultOnceki(BigDecimal gelirTableFormulResultOnceki) {
		this.gelirTableFormulResultOnceki = gelirTableFormulResultOnceki;
	}

	public boolean isDisableDonem() {
		if (type != GELIR) {
			if (childs == null) {
				disableDonem = false;
			} else {
				disableDonem = true;
			}
		}
		if (type == GELIR) {
			if (!autoCalc) {
				if (childs == null) {
					disableDonem = false;

				} else {
					disableDonem = true;
				}

			} else {
				disableDonem = true;
			}

		}

		return disableDonem;
	}

	public void setDisableDonem(boolean disableDonem) {
		this.disableDonem = disableDonem;
	}

	public String getStyleClass() {
		
	 if(isDisableDonem()){
		 styleClass="outputLB";
   		 }else{
   			styleClass="outputL";
   		 }

		return styleClass;
	}



	public String getCodeText() {
		if(gelirbilancocode==null){
			return "";
		}
		if(gelirbilancocode.getKod()>=1000){
			return "";
		}
			
		return gelirbilancocode.getKod()+"";
	}

	public void setCodeText(String codeText) {
		
		this.codeText = codeText;
	}

	public BigDecimal getCariRevizyon() {
		return cariRevizyon;
	}

	public void setCariRevizyon(BigDecimal cariRevizyon) {
		this.cariRevizyon = cariRevizyon;
	}

	public void setGelirTableFormulRevizeCari(BigDecimal gelirTableFormulRevizeCari) {
		this.gelirTableFormulRevizeCari = gelirTableFormulRevizeCari;
	}



}
