package com.company.rating.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.rating.dao.CevapDao;
import com.company.rating.dao.DegerlendirmeDao;
import com.company.rating.dao.DegerlendirmeagaciDao;
import com.company.rating.dao.FirmaDao;
import com.company.rating.dao.FirmafileDao;
import com.company.rating.dao.FirmasektorDao;
import com.company.rating.dao.FirmatipiDao;
import com.company.rating.dao.GelirbilancocodeDao;
import com.company.rating.dao.GelirbilancokayitDao;
import com.company.rating.dao.KalitatifdegerlendirmeDao;
import com.company.rating.dao.KalitatifdegerlendirmeKayitDao;
import com.company.rating.dao.KantitatifdegerlendirmeDao;
import com.company.rating.dao.KriterDao;
import com.company.rating.dao.KullaniciDao;
import com.company.rating.dao.OtomatikcevapDao;
import com.company.rating.dao.PuanlarDao;
import com.company.rating.dao.SektorDao;
import com.company.rating.dao.SoruDao;
import com.company.rating.dao.YetkilendirmeDao;
import com.company.rating.domain.Cevap;
import com.company.rating.domain.Degerlendirme;
import com.company.rating.domain.Degerlendirmeagaci;
import com.company.rating.domain.Firma;
import com.company.rating.domain.Firmafile;
import com.company.rating.domain.FirmafilePK;
import com.company.rating.domain.Firmasektor;
import com.company.rating.domain.Firmatipi;
import com.company.rating.domain.Gelirbilancocode;
import com.company.rating.domain.Gelirbilancokayit;
import com.company.rating.domain.GelirbilancokayitPK;
import com.company.rating.domain.Kalitatifdegerlendirme;
import com.company.rating.domain.KalitatifdegerlendirmeKayit;
import com.company.rating.domain.Kantitatifdegerlendirme;
import com.company.rating.domain.Kriter;
import com.company.rating.domain.Kullanici;
import com.company.rating.domain.Otomatikcevap;
import com.company.rating.domain.Puanlar;
import com.company.rating.domain.Sektor;
import com.company.rating.domain.Soru;
import com.company.rating.domain.Yetkilendirme;
import com.company.rating.service.RatingService;

@Service("ratingService")
public class RatingServiceImpl implements RatingService,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    private   DegerlendirmeagaciDao degerlendirmeagaciDao;
	@Autowired
	private KalitatifdegerlendirmeDao kalitatifdegerlendirmeDao;	
	@Autowired
	private DegerlendirmeDao degerlendirmeDao;
    @Autowired
    private  KantitatifdegerlendirmeDao kantitatifdegerlendirmeDao;
    @Autowired
    private KriterDao kriterDao;
    @Autowired
    private SektorDao sektorDao;
    @Autowired
    private CevapDao  cevapDao;
    @Autowired
    private FirmaDao firmaDao;
    @Autowired
    private FirmasektorDao firmasektorDao;
    @Autowired
    private KullaniciDao kullaniciDao;
    @Autowired
    private PuanlarDao puanlarDao;
    @Autowired
    private YetkilendirmeDao yetkilendirmeDao;
    @Autowired
    private FirmatipiDao firmatipiDao;
    @Autowired
    private SoruDao soruDao;  

    @Autowired
    private KalitatifdegerlendirmeKayitDao kalitatifdegerlendirmeKayitDao;
   
    @Autowired
    private  FirmafileDao firmafileDao;

    @Autowired 
    private  GelirbilancokayitDao gelirbilancokayitDao;
    @Autowired
    private  GelirbilancocodeDao  gelirbilancocodeDao; 
    @Autowired
    private OtomatikcevapDao otomatikcevapDao;
public RatingServiceImpl() {
	// TODO Auto-generated constructor stub
          
}
	//
	public List<Puanlar> getPuanlar(int sektor) {
		// TODO Auto-generated method stub
		return puanlarDao.getDegerlendirmeagaci( sektor);
	}

	//
	public List<Degerlendirme> getDegerlendirmeler() {
		// TODO Auto-generated method stub
		return degerlendirmeDao.findAll();
	}

	//
	public List<Kalitatifdegerlendirme> getKalitatifdegerlendirmeler(
			int firmatipi, int sektor, int secim) {
		// TODO Auto-generated method stub
		List<Kalitatifdegerlendirme> list= kalitatifdegerlendirmeDao.getKalitatifDegerlendirmeler(firmatipi, sektor,secim);
		if(list==null || list.isEmpty()){
			list= kalitatifdegerlendirmeDao.getKalitatifDegerlendirmeler(firmatipi, -1,secim);
		}
		if(list==null || list.isEmpty()){
			list= kalitatifdegerlendirmeDao.getKalitatifDegerlendirmeler(firmatipi, sektor,-1);
		}
		if(list==null || list.isEmpty()){
			list= kalitatifdegerlendirmeDao.getKalitatifDegerlendirmeler(firmatipi, -1,-1);
		}
		return list;
	}

	//
	public List<Kantitatifdegerlendirme> getKantitatifdegerlendirmeler(
			int firmatipi, int sektor) {
		// TODO Auto-generated method stub
		List<Kantitatifdegerlendirme>  list= kantitatifdegerlendirmeDao.getKantitatifDegerlendirmeler(firmatipi, sektor);
		if(list==null || list.isEmpty()){
			list= kantitatifdegerlendirmeDao.getKantitatifDegerlendirmeler(firmatipi, -1);
		}
		return list;
	}

	//
	public List<Kriter> getKriterler() {
		// TODO Auto-generated method stub
		return  kriterDao.findAll();
	}

	
	public List<Sektor> getSektorler() {
		// TODO Auto-generated method stub
		return sektorDao.findAll();
	}

	public List<Cevap> getCevaplar() {
		// TODO Auto-generated method stub
		return cevapDao.findAll();
	}

	
	public List<Firma> getFirmalar() {
		// TODO Auto-generated method stub
		return firmaDao.findAll();
	}

	
	public List<Firmasektor> getfirmasektors() {
		// TODO Auto-generated method stub
		return firmasektorDao.findAll();
	}

	
	public List<Firmatipi> getfirmatipleri() {
		// TODO Auto-generated method stub
		return firmatipiDao.findAll();
	}


	public Kullanici getKullanici(String username) {
		// TODO Auto-generated method stub
		
		return kullaniciDao.find(username) ;
	}


	public List<Degerlendirmeagaci> getDegerlendirmeagaci(int firmatipi,int sektor,int secim) {
		

		//  Yatırım  sorularının basılıp basılmamasına  göre ağacın ağırlı değiştiği için 
		if(firmatipi==Firmatipi.HIZMET_FIRMATIP_ID || firmatipi==Firmatipi.URETIM_FIRMATIP_ID){
			   if(secim== Firma.IHRACAT_VAR_SABIT_YATIRIM_VAR_SECIM || secim== Firma.IHRACAT_YOK_SABIT_YATIRIM_VAR_SECIM){
				   secim=Firma.YATIRIMLI;
			   }else{
				   secim=Firma.YATIRIMSIZ;
			   }
		}else{
			secim=-1;
		}
		// TODO Auto-generated method stub
		 List<Degerlendirmeagaci> list= degerlendirmeagaciDao.getDegerlendirmeagaci(firmatipi,sektor,secim);

		 if(list==null || list.isEmpty()){
				 list=degerlendirmeagaciDao.getDegerlendirmeagaci(firmatipi,-1,secim);			
		 }
		 return list;
	}

	public List<Soru> getSorular() {
		// TODO Auto-generated method stub
		return soruDao.findAll();
	}
	
	public List<Yetkilendirme> getYetkiliFirmalar(String kullanici) {
		// TODO Auto-generated method stub
		return yetkilendirmeDao.getYetkiliFirmalar(kullanici);
	}

	@Transactional
	public void saveAllKalitatif(
			List<KalitatifdegerlendirmeKayit> kalitatifdegerlendirme) {
		// TODO Auto-generated method stub
		kalitatifdegerlendirmeKayitDao.saveAll(kalitatifdegerlendirme);
	}

//	public List<Degerlendirmeagaciagirlik> getDegerlendirmeagaciagarliklar(
//			int firmatipi, int sektor) {
//		// TODO Auto-generated method stub
//		List<Degerlendirmeagaciagirlik> list= degerlendirmeagaciagirlikDao.getdeDegerlendirmeagaciagirliklar(firmatipi, sektor);
//		if(list==null || list.isEmpty()){
//			list=degerlendirmeagaciagirlikDao.getdeDegerlendirmeagaciagirliklar(firmatipi, -1);
//		}
//		return list;
//	}
	

	public List<KalitatifdegerlendirmeKayit> getKalitatifDegerlendirmeKayitlari(
			int firmaId, int yil, int donem, String username) {
		// TODO Auto-generated method stub
		//return(List<KalitatifdegerlendirmeKayit>) entityManager.createQuery("Select b  from kalitatifdegerlendirme_kayit b where b.id.idSektor=:sektor and  b.id.idFirmatipi=:firmatipi").setParameter("sektor", sektor).setParameter("firmatipi", firmatipi).getResultList();
	  return kalitatifdegerlendirmeKayitDao.getKalitatifDegerlendirmeKayitlari(firmaId, yil, donem, username);
	}
	@Override
	public int getOldestYearFromKalitatifKayit() {
		// TODO Auto-generated method stub
		return kalitatifdegerlendirmeKayitDao.getOldestYearFromKalitatifKayit();
	}
	@Override
	public String getFileName(int yil, int donem, int firmaId) {
		// TODO Auto-generated method stub
		return firmafileDao.getFileName(yil, donem,  firmaId);
	}
	
    @Transactional
	public void deleteGelirBilancoAndKalitatifRecords(int yil, int donem, int firmaId) {
		// TODO Auto-generated method stub
		gelirbilancokayitDao.deleteGelirBilaGelirbilancokayits(yil, donem, firmaId);
	      kalitatifdegerlendirmeKayitDao.deleteRecords(yil, donem, firmaId); 
	}
    @Transactional
   public void  insertFile(Firmafile firmafile){
	   firmafileDao.save(firmafile);
	   
   }
   public Firmafile getFirmaFile(int yil, int donem, int firmaId){
	   FirmafilePK id=new FirmafilePK();
	   id.setYil(yil);
	   id.setDonem(donem);
	   id.setIdFirma(firmaId);
	   return firmafileDao.find(id);
	   
   }
@Override
@Transactional
public void deleteKalitatifRecords(int yil, int donem, int firmaId) {
	// TODO Auto-generated method stub
	  kalitatifdegerlendirmeKayitDao.deleteRecords(yil, donem, firmaId);
}
@Override
public List<Gelirbilancocode> getGelirbilancocodes() {
	// TODO Auto-generated method stub
	return gelirbilancocodeDao.findAll();
}
@Override
@Transactional
public void savaGelirBilanco(List<Gelirbilancokayit> gelirbilancokayits) {
	// TODO Auto-generated method stub
	gelirbilancokayitDao.saveAll(gelirbilancokayits);
	
}
@Override
public boolean isGelirBilancoKayitExist(int yil, int donem, int firmaId) {
	// TODO Auto-generated method stub
	GelirbilancokayitPK  pk=new GelirbilancokayitPK();
	pk.setIdFirma(firmaId);
	pk.setDonem(donem);
	pk.setYil(yil);
	pk.setKod(100); //100 set edilmesinin  özel  bir anlı yok.  Eger   kayit  varsa kodu 100  olan kayıtta vardır
	Gelirbilancokayit ky=gelirbilancokayitDao.find(pk);
	if(ky!=null){
		return  true;
	}
	return false;
}
@Override
public List<Gelirbilancokayit> getGelirBilaGelirbilancokayits(int yil, int donem, int idfirma) {
	// TODO Auto-generated method stub
	return gelirbilancokayitDao.getGelirBilaGelirbilancokayits(yil, donem, idfirma);
}
@Override
@Transactional
public void upateGelirBilanco(List<Gelirbilancokayit> gelirbilancokayits) {
	// TODO Auto-generated method stub
	for(Gelirbilancokayit gelirbilancokayit:gelirbilancokayits){
		  gelirbilancokayitDao.update(gelirbilancokayit);
	}
	
}
@Override
public List<Firmatipi> getFirmaTipleri() {
	// TODO Auto-generated method stub
	return firmatipiDao.findAll();
}
@Override
@Transactional
public void saveFirmaUser(Firma firma,Kullanici kullanici,Yetkilendirme yetkilendirme){
	// TODO Auto-generated method stub
	firmaDao.save(firma);
	kullaniciDao.save(kullanici);
	yetkilendirmeDao.save(yetkilendirme);
	
}
@Override
public List<Otomatikcevap> getOtomatikCevaplar() {
	// TODO Auto-generated method stub
	return otomatikcevapDao.findAll();
}
}
