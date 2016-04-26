package com.company.rating.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.rating.domain.Gelirbilancocode;
import com.company.rating.domain.Gelirbilancokayit;
import com.company.rating.domain.GelirbilancokayitPK;
import com.company.rating.service.RatingService;
import com.company.rating.util.RatingFormulaParser;
import com.company.rating.view.ApplicationData;
import com.company.rating.view.GeneralData;
import com.company.rating.view.model.GelirBilancoExcelModel;
import com.company.rating.view.model.GelirbilancoModel;
//@Component("gelirBilancoViewData")
//@Scope("session") 
public class GelirBilancoViewData implements Serializable{
	public static final int DONEN_VARLIKLAR = 1;
	public static final int KISA_VADELI_YABANCI = 3;
	public static final int DURAN_VARLIKLAR = 2;
	public static final int OZKAYNAKLAR = 5;
	public static final int UZUN_VADELI_YABANCI_KAYNAKLAR = 4;
	public static final int GELIR_TABLOSU=6;
    private boolean fromExcelUpload;
    private boolean fromExistingKayit;
   
	private ArrayList<GelirbilancoModel> donenVarliklar;
	private ArrayList<GelirbilancoModel> kisaVadeliYabanci;
	private ArrayList<GelirbilancoModel> duranVarliklar;
	private ArrayList<GelirbilancoModel> ozkaynaklar;
	private ArrayList<GelirbilancoModel> uzunVadeliYabancikaynaklar;
	private ArrayList<GelirbilancoModel> gelirTablosu;
	private ArrayList<GelirbilancoModel> gelirBilancoAllVarlikList;
	private GelirbilancoModel donenVarliklarToplam;
	private GelirbilancoModel kisaVadeliYabanciToplam;
	private GelirbilancoModel duranVarliklarToplam;
	private GelirbilancoModel ozkaynaklarToplam;
	private GelirbilancoModel uzunVadeliYabancikaynaklartoplam;
	private GelirbilancoModel gelirTablosuToplam;
	private HashMap<String, GelirbilancoModel> codeModelMap=new HashMap<String, GelirbilancoModel>();
	
	private GelirbilancoModel pasifKaynaklarToplam=new GelirbilancoModel(null);
	private GelirbilancoModel aktifVarliklarToplam=new GelirbilancoModel(null);
	
	@Autowired
    private ApplicationData applicationData;  

	@Autowired
	private RatingService  ratingService;

   
    @Autowired
    private GeneralData generalData;
    
     @PostConstruct
	public void createBilancoData() {
	   
	
		donenVarliklar = createBilancoModel(DONEN_VARLIKLAR, applicationData);
		kisaVadeliYabanci = createBilancoModel(KISA_VADELI_YABANCI, applicationData);
		duranVarliklar = createBilancoModel(DURAN_VARLIKLAR, applicationData);
		ozkaynaklar = createBilancoModel(OZKAYNAKLAR, applicationData);
		uzunVadeliYabancikaynaklar = createBilancoModel(UZUN_VADELI_YABANCI_KAYNAKLAR, applicationData);
        gelirTablosu=createBilancoModel(GELIR_TABLOSU, applicationData); 
       
        gelirBilancoAllVarlikList =new ArrayList<GelirbilancoModel>();
     
        gelirBilancoAllVarlikList.addAll(donenVarliklar);
        gelirBilancoAllVarlikList.addAll(duranVarliklar);
        gelirBilancoAllVarlikList.addAll(gelirTablosu);
        gelirBilancoAllVarlikList.addAll(kisaVadeliYabanci);
        gelirBilancoAllVarlikList.addAll(ozkaynaklar);
        gelirBilancoAllVarlikList.addAll(uzunVadeliYabancikaynaklar);
        specifyGelirTablosu();
   	 for(GelirbilancoModel gelirbilancoModel:gelirBilancoAllVarlikList){
		 codeModelMap.put(RatingFormulaParser.CODE_PREFIX+""+gelirbilancoModel.getGelirbilancocode().getKod(), gelirbilancoModel);
	
		 
	 }
  
	}
    public  String  setGelirBilancoDataFromExcelFile(HashMap<String, GelirBilancoExcelModel> map){
    	ArrayList<String>  messageList=new ArrayList<String>();
    	String retMessage=null;
    	    for(GelirbilancoModel  model:gelirBilancoAllVarlikList) {
    	    	
    	    	if(model.getChilds()!=null){
    	    		continue;
    	    	}
    	    	 String formula=model.gelirTableFormulas.get(model.getGelirbilancocode().getKod()+"");
    	    	 if(formula!=null){
    	    		 continue;
    	    	 }
    	    	
    	    	GelirBilancoExcelModel excelModel=map.get(model.getGelirbilancocode().getKod()+"");
    	    	
    	    	if(excelModel==null){
    	    		messageList.add(model.getGelirbilancocode().getKod()+ "");
    	    	}else{
    	    		
    	    		model.setOncekidonem(excelModel.getOnceki());
    	    		model.setCariDonem(excelModel.getCari());
    	    		model.setCariRevizyon(new  BigDecimal(0));
    	    	}
    	    	
    	    }
    	       if(!messageList.isEmpty()){
    	    	   retMessage="";
    	    	   for(String s:messageList){
        			   retMessage+=s+",";
        		   }
        		   retMessage+=" nolu varlıkların değerleri okunamadı";
        		   return retMessage;
    	       }else{
    	    	   String msg=kaydetGelirBilanco();
    	    	    if(msg!=null){
    			    return msg;
    		        }
    	       }
    
    	return  retMessage;
    }
	public void loadGelirBilancoDataFromDB(int donem,int yil,int idFirma) {
		   ArrayList<Gelirbilancokayit> list=(ArrayList<Gelirbilancokayit>) ratingService.getGelirBilaGelirbilancokayits(yil, donem, idFirma);
		   for(GelirbilancoModel  model:gelirBilancoAllVarlikList) {
			   for(Gelirbilancokayit gelirbilancokayit:list){
				   if(model.getGelirbilancocode().getKod()==gelirbilancokayit.getId().getKod()){
					   model.setCariDonem(gelirbilancokayit.getCari().add(gelirbilancokayit.getCariRevizyon()));
					   model.setOncekidonem(gelirbilancokayit.getOnceki());
					   model.setCariRevizyon(gelirbilancokayit.getCariRevizyon());
				   }
			   }
		   }
         
	}

	private ArrayList<GelirbilancoModel> createBilancoModel(int code, ApplicationData applicationData) {
		ArrayList<GelirbilancoModel> list = new ArrayList<GelirbilancoModel>();
		HashMap<String, Gelirbilancocode> map = applicationData.getGelirbilancocodeMap();
		Gelirbilancocode gelirbilanco = map.get(code+"");
		if (gelirbilanco == null) {
			System.out.println("logR-----" + code + " kodlu  kayıt gelir bilanço tablosunda  bulunamadı");
			return null;
		}
		GelirbilancoModel modl = new GelirbilancoModel(gelirbilanco);
		if(code==GELIR_TABLOSU){
			modl.setType(GelirbilancoModel.GELIR);
		}

		list.add(modl);
		
		
		for (int i = 0; i < 10; i++) {
			Gelirbilancocode gelirbilancocode = map.get(code + ""+i );
	
			if (gelirbilancocode != null) {
				if (modl.getChilds() == null) {
					modl.setChilds(new ArrayList<GelirbilancoModel>());
				}
				GelirbilancoModel model = new GelirbilancoModel(gelirbilancocode);
				if(code==GELIR_TABLOSU){
					model.setType(GelirbilancoModel.GELIR);
				}
			
				list.add(model);
				modl.getChilds().add(model);
			
				for (int j = 0; j < 10; j++) {
                        Gelirbilancocode gelirbilancocode2 = map.get(code + ""+i +""+ j);
				
					
					if (gelirbilancocode2 != null) {
						if (model.getChilds() == null) {
							model.setChilds(new ArrayList<GelirbilancoModel>());
						}
						GelirbilancoModel model2 = new GelirbilancoModel(gelirbilancocode2);
						if(code==GELIR_TABLOSU){
							model2.setType(GelirbilancoModel.GELIR);
						}
						list.add(model2);
						model.getChilds().add(model2);
					}

				}
				

			}

		}

		return list;
	}
    public  void  specifyGelirTablosu(){
    	Gelirbilancocode gelirbilancocode=new Gelirbilancocode();
    	gelirbilancocode.setKod(1001);
    	gelirbilancocode.setName("C-NET SATIŞLAR");
    	GelirbilancoModel model=new GelirbilancoModel(gelirbilancocode);
    	model.setType(GelirbilancoModel.GELIR);
    	gelirTablosu.add(9, model);
    	
    	Gelirbilancocode gelirbilancocode1=new Gelirbilancocode();
    	gelirbilancocode1.setKod(1002);
    	gelirbilancocode1.setName("BRÜT SATIŞ KARI VEYA ZARARI");
    	GelirbilancoModel model1=new GelirbilancoModel(gelirbilancocode1);
    	model1.setType(GelirbilancoModel.GELIR);
   
    	gelirTablosu.add(15, model1);
    	
    	Gelirbilancocode gelirbilancocode2=new Gelirbilancocode();
    	gelirbilancocode2.setKod(1003);
    	gelirbilancocode2.setName("FAALİYET KARI VEYA ZARARI");
    	GelirbilancoModel model2=new GelirbilancoModel(gelirbilancocode2);
    	model2.setType(GelirbilancoModel.GELIR);
    	gelirTablosu.add(20, model2);
    	  
    	
    	Gelirbilancocode gelirbilancocode3=new Gelirbilancocode();
    	gelirbilancocode3.setKod(1004);
    	gelirbilancocode3.setName("OLAĞAN KAR VEYA ZARAR");
    	GelirbilancoModel model3=new GelirbilancoModel(gelirbilancocode3);
    	model3.setType(GelirbilancoModel.GELIR);
    	gelirTablosu.add(42, model3);
    	
    	
    }
    private   HashMap<String, BigDecimal> getFormulaParametersAsMap(String mainFormula,int type){
    	HashMap<String, BigDecimal> hashMap=new HashMap<String, BigDecimal>();
    	
        String formula=mainFormula;  	
	     
	       int begin=0, end=0; 
	
		   for(int i=0;  i<formula.length();i++){
			   if(formula.charAt(i)==RatingFormulaParser.CODE_PREFIX){
				begin=i;   
			   }
			   if(begin!=0 && (formula.charAt(i)==',' || formula.charAt(i)==']')){
				end=i;   
				
			   }
			   if(begin!=0  && end!=0){
				   String code=formula.substring(begin, end);
			
				   if(type==GelirbilancoModel.CARI_DONEM){
				        if(code.equals("$1100")) {
				        	hashMap.put("$1100", new BigDecimal(360));
				        }else{
				        	hashMap.put(code, codeModelMap.get(code).getCariDonem());
				        }
				 		
					 }
					 if(type==GelirbilancoModel.ONCEKI_DONEM){
						 if(code.equals("$1100")) {
							 hashMap.put("$1100", new BigDecimal(360));
					        }else{
						hashMap.put(code, codeModelMap.get(code).getOncekidonem());
					        }
					 }
					 if(type==GelirbilancoModel.REVIZE_CARI){
						 if(code.equals("$1100")) {
							 hashMap.put("$1100", new BigDecimal(360));
					        }else{
						hashMap.put(code, codeModelMap.get(code).getCariDonem().add(codeModelMap.get(code).getCariRevizyon()));
					        }
					 }
				   
				   begin=0;
				   end=0;
			   }
			   
			  	
		   }
    	
 
    	return hashMap;
    }
    public   BigDecimal evalFormula(String formula,int type){
    	
    	
    	 RatingFormulaParser  parser=new RatingFormulaParser(getFormulaParametersAsMap(formula, type));
    
    		return parser.eval(formula);
    	 
    }
    public void calculateGelirTableFormul(){
    
    
    	
    	 for(GelirbilancoModel gelirbilancoModel:gelirTablosu){
    		 String formula=gelirbilancoModel.gelirTableFormulas.get(gelirbilancoModel.getGelirbilancocode().getKod()+"");
    
    		 if(formula!=null){
    			 gelirbilancoModel.setAutoCalc(true);
    			 BigDecimal cari=evalFormula(formula,GelirbilancoModel.CARI_DONEM);
    			 gelirbilancoModel.setGelirTableFormulResultCari(cari);
    			 BigDecimal onceki=evalFormula(formula,GelirbilancoModel.ONCEKI_DONEM);
    			 gelirbilancoModel.setGelirTableFormulResultOnceki(onceki);
    			 BigDecimal revize=evalFormula(formula,GelirbilancoModel.REVIZE_CARI);
    			 gelirbilancoModel.setGelirTableFormulRevizeCari(revize);
    		 }
    		
    		 
    	 }
    	 
    }
	public ArrayList<GelirbilancoModel> getDonenVarliklar() {
		return donenVarliklar;
	}

	public void setDonenVarliklar(ArrayList<GelirbilancoModel> donenVarliklar) {
		this.donenVarliklar = donenVarliklar;
	}

	public ArrayList<GelirbilancoModel> getKisaVadeliYabanci() {
		return kisaVadeliYabanci;
	}

	public void setKisaVadeliYabanci(ArrayList<GelirbilancoModel> kisaVadeliYabanci) {
		this.kisaVadeliYabanci = kisaVadeliYabanci;
	}

	public ArrayList<GelirbilancoModel> getDuranVarliklar() {
		return duranVarliklar;
	}

	public void setDuranVarliklar(ArrayList<GelirbilancoModel> duranVarliklar) {
		this.duranVarliklar = duranVarliklar;
	}

	public ArrayList<GelirbilancoModel> getOzkaynaklar() {
		return ozkaynaklar;
	}

	public void setOzkaynaklar(ArrayList<GelirbilancoModel> ozkaynaklar) {
		this.ozkaynaklar = ozkaynaklar;
	}

	public ArrayList<GelirbilancoModel> getUzunVadeliYabancikaynaklar() {
		return uzunVadeliYabancikaynaklar;
	}

	public void setUzunVadeliYabancikaynaklar(ArrayList<GelirbilancoModel> uzunVadeliYabancikaynaklar) {
		this.uzunVadeliYabancikaynaklar = uzunVadeliYabancikaynaklar;
	}

	public ArrayList<GelirbilancoModel> getGelirTablosu() {
		ArrayList<GelirbilancoModel>  model=new ArrayList<GelirbilancoModel>();
		for(GelirbilancoModel gelirbilancoModel:gelirTablosu){
			if(gelirbilancoModel.getGelirbilancocode().getKod()!=6 && gelirbilancoModel.getGelirbilancocode().getKod()!=69){
				model.add(gelirbilancoModel);
			}
		}
		
		
		return model;
	}

	public void setGelirTablosu(ArrayList<GelirbilancoModel> gelirTablosu) {
		this.gelirTablosu = gelirTablosu;
	}

	public GelirbilancoModel getDonenVarliklarToplam() {
		if(donenVarliklar.size()>0){
			donenVarliklarToplam=donenVarliklar.get(0);
		}
		return donenVarliklarToplam;
	}



	public GelirbilancoModel getKisaVadeliYabanciToplam() {
		if(kisaVadeliYabanci.size()>0){
			kisaVadeliYabanciToplam=kisaVadeliYabanci.get(0);
		}
		return kisaVadeliYabanciToplam;
	}



	public GelirbilancoModel getDuranVarliklarToplam() {
		if(duranVarliklar.size()>0){
			duranVarliklarToplam=duranVarliklar.get(0);
		}
		return duranVarliklarToplam;
	}



	public GelirbilancoModel getOzkaynaklarToplam() {
		if(ozkaynaklar.size()>0){
			ozkaynaklarToplam=ozkaynaklar.get(0);
		}
		
		return ozkaynaklarToplam;
	}



	public GelirbilancoModel getUzunVadeliYabancikaynaklartoplam() {
		if(uzunVadeliYabancikaynaklar.size()>0){
			uzunVadeliYabancikaynaklartoplam=uzunVadeliYabancikaynaklar.get(0);
		}
		
		return uzunVadeliYabancikaynaklartoplam;
	}



	public GelirbilancoModel getGelirTablosuToplam() {
		if(gelirTablosu.size()>0){
			gelirTablosuToplam=gelirTablosu.get(0);
		}
		
		return gelirTablosuToplam;
	}

	public GelirbilancoModel getPasifKaynaklarToplam() {
		BigDecimal top=getOzkaynaklarToplam().getCariDonem().add(getUzunVadeliYabancikaynaklartoplam().getCariDonem()).add(getKisaVadeliYabanciToplam().getCariDonem());
		pasifKaynaklarToplam.setCariDonem(top);
		top=getOzkaynaklarToplam().getOncekidonem().add(getUzunVadeliYabancikaynaklartoplam().getOncekidonem()).add(getKisaVadeliYabanciToplam().getOncekidonem());
		pasifKaynaklarToplam.setOncekidonem(top);  
		return pasifKaynaklarToplam;
	}

	public GelirbilancoModel getAktifVarliklarToplam() {
		  BigDecimal top=getDuranVarliklarToplam().getCariDonem().add(getDonenVarliklarToplam().getCariDonem());
		  aktifVarliklarToplam.setCariDonem(top);
		  top=getDuranVarliklarToplam().getOncekidonem().add(getDonenVarliklarToplam().getOncekidonem());
		  aktifVarliklarToplam.setOncekidonem(top);
		return aktifVarliklarToplam;
	}
	public ArrayList<GelirbilancoModel> getGelirBilancoAllVarlikList() {
		return gelirBilancoAllVarlikList;
	}
	public void setGelirBilancoAllVarlikList(ArrayList<GelirbilancoModel> gelirBilancoAllVarlikList) {
		this.gelirBilancoAllVarlikList = gelirBilancoAllVarlikList;
	}
	public  String kaydetGelirBilanco(){
		
		try{

		if(  /*getAktifVarliklarToplam().getCariDonem().compareTo(getPasifKaynaklarToplam().getCariDonem())!=0 || */
			getAktifVarliklarToplam().getOncekidonem().compareTo(getPasifKaynaklarToplam().getOncekidonem())!=0){
			return "Aktif  varlıklar toplamı pasif kaynaklar toplamına eşit olmalı";
			
		}
		
	     ArrayList<GelirbilancoModel> list=gelirBilancoAllVarlikList;
	     
	     
		
		ArrayList<Gelirbilancokayit> gelirbilancokayits=new ArrayList<Gelirbilancokayit>();
		
		for(GelirbilancoModel model:list){
			   if(model.getChilds()==null && !model.isAutoCalc() && model.getGelirbilancocode().getKod()<1000){
				   GelirbilancokayitPK gelirbilancokayitPK=new GelirbilancokayitPK();
				   gelirbilancokayitPK.setDonem(generalData.getDonem());
				   gelirbilancokayitPK.setIdFirma(generalData.getFirma().getIdFirma());
				   gelirbilancokayitPK.setKod(model.getGelirbilancocode().getKod());
				   gelirbilancokayitPK.setYil(generalData.getYil());
				   Gelirbilancokayit gelirbilancokayit=new Gelirbilancokayit();
				   gelirbilancokayit.setId(gelirbilancokayitPK);
				   gelirbilancokayit.setCari(model.getCariDonem());
				   gelirbilancokayit.setOnceki(model.getOncekidonem());
				   gelirbilancokayit.setCariRevizyon(model.getCariRevizyon());
				   gelirbilancokayits.add(gelirbilancokayit);
			   }
			
		}
		
	
		if(isFromExcelUpload() || isFromExistingKayit()){
			ratingService.upateGelirBilanco(gelirbilancokayits);
		}else{
			ratingService.savaGelirBilanco(gelirbilancokayits);
		}
		
	
		return null;
		}catch(Exception exception){
			return  "Kaydetme işlemi Başarısız";
		}
		
	}
	public boolean isFromExcelUpload() {
		return fromExcelUpload;
	}
	public void setFromExcelUpload(boolean fromExcelUpload) {
		this.fromExcelUpload = fromExcelUpload;
	}
	public boolean isFromExistingKayit() {
		return fromExistingKayit;
	}
	public void setFromExistingKayit(boolean fromExistingKayit) {
		this.fromExistingKayit = fromExistingKayit;
	}
	public HashMap<String, GelirbilancoModel> getCodeModelMap() {
		return codeModelMap;
	}
	public void setCodeModelMap(HashMap<String, GelirbilancoModel> codeModelMap) {
		this.codeModelMap = codeModelMap;
	}


}
