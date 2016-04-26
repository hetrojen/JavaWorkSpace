package com.company.rating.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.domain.Cevap;
import com.company.rating.domain.Degerlendirme;
import com.company.rating.domain.Firma;
import com.company.rating.domain.Firmatipi;
import com.company.rating.domain.Gelirbilancocode;
import com.company.rating.domain.Kriter;
import com.company.rating.domain.Otomatikcevap;
import com.company.rating.domain.Sektor;
import com.company.rating.domain.Soru;
import com.company.rating.service.RatingService;

@Component("applicationData")
@Scope("singleton")
public class ApplicationData implements Serializable {
	private ArrayList<Degerlendirme> degerlendirmeler = new ArrayList<Degerlendirme>();
	private ArrayList<Soru> sorular = new ArrayList<Soru>();
	private ArrayList<Kriter> kriterler = new ArrayList<Kriter>();
	private ArrayList<Firma> firmalar = new ArrayList<Firma>();
	private ArrayList<Cevap> cevaplar = new ArrayList<Cevap>();
	private ArrayList<Sektor> sektorler = new ArrayList<Sektor>();
	private ArrayList<Firmatipi> firmaTipleri=new ArrayList<Firmatipi>();
    private  HashMap<String,List<Otomatikcevap>>  otomatikCevaplar=new HashMap<String, List<Otomatikcevap>>();
	private ArrayList<Gelirbilancocode> gelirbilancocodes = new ArrayList<Gelirbilancocode>();
	private HashMap<String, Gelirbilancocode> gelirbilancocodeMap = new HashMap<String, Gelirbilancocode>();

	private RatingService ratingService;

	public ApplicationData() {
		// TODO Auto-generated constructor stub

	}

	@Autowired
	public ApplicationData(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@PostConstruct
	public void loadAppData() {

		setDegerlendirmeler((ArrayList<Degerlendirme>) ratingService.getDegerlendirmeler());
		setKriterler((ArrayList<Kriter>) ratingService.getKriterler());
		setSorular((ArrayList<Soru>) ratingService.getSorular());
		setFirmalar((ArrayList<Firma>) ratingService.getFirmalar());
		setCevaplar((ArrayList<Cevap>) ratingService.getCevaplar());
		setSektorler((ArrayList<Sektor>) ratingService.getSektorler());
		setGelirbilancocodes((ArrayList<Gelirbilancocode>) ratingService.getGelirbilancocodes());
		setFirmaTipleri((ArrayList<Firmatipi>) ratingService.getfirmatipleri());
		loadGelirBilancoMap();
		loadOtoCevaplar();
	}

	private void loadGelirBilancoMap() {
		for (Gelirbilancocode gelirbilancocode : gelirbilancocodes) {
			gelirbilancocodeMap.put(gelirbilancocode.getKod() + "", gelirbilancocode);
		}

	}
    private void  loadOtoCevaplar(){
    	ArrayList<Otomatikcevap>  list=(ArrayList<Otomatikcevap>) ratingService.getOtomatikCevaplar();
    	ArrayList<Otomatikcevap> hizmet=new ArrayList<Otomatikcevap>();
      	ArrayList<Otomatikcevap> uretim=new ArrayList<Otomatikcevap>();
    	for(Otomatikcevap otomatikcevap:list){
    		if(otomatikcevap.getId().getIdFirmaTipi()==Firmatipi.HIZMET_FIRMATIP_ID){
    			hizmet.add(otomatikcevap);
    		}
    		if(otomatikcevap.getId().getIdFirmaTipi()==Firmatipi.URETIM_FIRMATIP_ID){
    			uretim.add(otomatikcevap);
    		}
    	}
    	
	   otomatikCevaplar.put(Firmatipi.URETIM_FIRMATIP_ID+"",uretim);
	   otomatikCevaplar.put(Firmatipi.HIZMET_FIRMATIP_ID+"",hizmet);
   }
	public ArrayList<Degerlendirme> getDegerlendirmeler() {
		return degerlendirmeler;
	}

	public void setDegerlendirmeler(ArrayList<Degerlendirme> degerlendirmeler) {
		this.degerlendirmeler = degerlendirmeler;
	}

	public ArrayList<Soru> getSorular() {
		return sorular;
	}

	public void setSorular(ArrayList<Soru> sorular) {
		this.sorular = sorular;
	}

	public String getSoruName(int id) {
		for (Soru soru : sorular) {
			if (soru.getIdSoru() == id) {
				return soru.getSoruName();
			}
		}
		return "";
	}

	public ArrayList<Kriter> getKriterler() {
		return kriterler;
	}

	public void setKriterler(ArrayList<Kriter> kriterler) {
		this.kriterler = kriterler;
	}

	public Kriter getKriterById(int id) {
		for (Kriter kriter : kriterler) {
			if (kriter.getIdKriter() == id) {
				return kriter;
			}
		}
		return null;
	}

	public String getDegerlendirmeName(int degerlendirmeId) {
		for (Degerlendirme degerlendirme : degerlendirmeler) {
			if (degerlendirme.getIdDegerlendirme() == degerlendirmeId) {
				return degerlendirme.getDegerlendirmeName();
			}
		}
		return "";
	}

	public String getFirmaName(int firmaId) {
		for (Firma firma : firmalar) {
			if (firma.getIdFirma() == firmaId) {
				return firma.getFirmaName();
			}
		}
		return "";
	}

	public RatingService getRatingService() {
		return ratingService;
	}

	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	public ArrayList<Firma> getFirmalar() {
		return firmalar;
	}

	public void setFirmalar(ArrayList<Firma> firmalar) {
		this.firmalar = firmalar;
	}

	public Firma getFirma(int firmaId) {
		for (Firma firma : firmalar) {
			if (firma.getIdFirma() == firmaId) {
				return firma;
			}
		}
		return null;
	}

	public ArrayList<Cevap> getCevaplar() {
		return cevaplar;
	}

	public void setCevaplar(ArrayList<Cevap> cevaplar) {
		this.cevaplar = cevaplar;
	}

	public ArrayList<Cevap> getCevaplarByGroup(int groupId) {
		ArrayList<Cevap> cevaps = new ArrayList<Cevap>();
		for (Cevap cevap : cevaplar) {
			if (cevap.getId().getIdGrup() == groupId) {
				cevaps.add(cevap);
			}
		}
		return cevaps;
	}

	public ArrayList<Sektor> getSektorler() {
		return sektorler;
	}

	public void setSektorler(ArrayList<Sektor> sektorler) {
		this.sektorler = sektorler;
	}

	public Sektor getSektorById(int sektorId) {
		for (Sektor sektor : sektorler) {
			if (sektor.getIdSektor() == sektor.getIdSektor()) {
				return sektor;
			}
		}
		return null;
	}

	public ArrayList<Gelirbilancocode> getGelirbilancocodes() {
		return gelirbilancocodes;
	}

	public void setGelirbilancocodes(ArrayList<Gelirbilancocode> gelirbilancocodes) {
		this.gelirbilancocodes = gelirbilancocodes;
	}

	public HashMap<String, Gelirbilancocode> getGelirbilancocodeMap() {
		return gelirbilancocodeMap;
	}

	public void setGelirbilancocodeMap(HashMap<String, Gelirbilancocode> gelirbilancocodeMap) {
		this.gelirbilancocodeMap = gelirbilancocodeMap;
	}

	public ArrayList<Firmatipi> getFirmaTipleri() {
		return firmaTipleri;
	}

	public void setFirmaTipleri(ArrayList<Firmatipi> firmaTipleri) {
		this.firmaTipleri = firmaTipleri;
	}
    public   ArrayList<Otomatikcevap>  getOtoCevapNameList(int idFirmaTipi,int sektor){
    	    ArrayList<Otomatikcevap> list=   (ArrayList<Otomatikcevap>) otomatikCevaplar.get(idFirmaTipi+"");
    	    if(list==null){
    	    	return null;
    	    }
    	    ArrayList<Otomatikcevap>  listBySektor=new ArrayList<Otomatikcevap>();
    	  for(Otomatikcevap otomatikcevap:list){
    		  if(otomatikcevap.getId().getIdSektor()==sektor){
    			  listBySektor.add(otomatikcevap);
    		  }
    	  }
    	return  listBySektor;
    }
    public BigDecimal getCevapValueByName(String name, int cvpGroupId){
   
		for(Cevap cevap:getCevaplar()){
		    if(cvpGroupId==cevap.getId().getIdGrup() && cevap.getId().getCevapName().equalsIgnoreCase(name)){
		    	  return cevap.getCevapDeger();
		    }		    
	}
		return null;
    }
}
