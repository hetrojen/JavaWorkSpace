package com.company.rating.service;


import java.util.List;

import com.company.rating.domain.Cevap;
import com.company.rating.domain.Degerlendirme;
import com.company.rating.domain.Degerlendirmeagaci;
import com.company.rating.domain.Firma;
import com.company.rating.domain.Firmafile;
import com.company.rating.domain.Firmasektor;
import com.company.rating.domain.Firmatipi;
import com.company.rating.domain.Gelirbilancocode;
import com.company.rating.domain.Gelirbilancokayit;
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

public interface RatingService {
	
	
	
	
	public List<Degerlendirmeagaci>  getDegerlendirmeagaci(int firmatipi,int sektor,int secim);
	public List<Degerlendirme>  getDegerlendirmeler();
	public List<Kalitatifdegerlendirme>  getKalitatifdegerlendirmeler(int firmatipi,int sektor,int secim);
	public List<Kantitatifdegerlendirme> getKantitatifdegerlendirmeler(int firmatipi,int sektor);
	public List<Kriter> getKriterler();
	public List<Sektor>  getSektorler();
	public List<Cevap>  getCevaplar();
	public List<Firma>   getFirmalar();
	public List<Firmatipi>   getFirmaTipleri();
	public List<Firmasektor>  getfirmasektors();
	public List<Firmatipi>  getfirmatipleri();
	public Kullanici   getKullanici(String username);
	public List<Puanlar>  getPuanlar(int sektor);
	public List<Soru>  getSorular();
	public List<Yetkilendirme>  getYetkiliFirmalar(String kullanici);
	public void saveAllKalitatif(List<KalitatifdegerlendirmeKayit> kalitatifdegerlendirme);

	public List<KalitatifdegerlendirmeKayit> getKalitatifDegerlendirmeKayitlari(
			int firmaId, int yil, int donem, String username);
	public int getOldestYearFromKalitatifKayit();
	public String getFileName(int yil, int donem, int firmaId); 
	public boolean isGelirBilancoKayitExist(int yil, int donem, int firmaId);
    public void deleteGelirBilancoAndKalitatifRecords(int yil, int donem, int firmaId);
    public void deleteKalitatifRecords(int yil, int donem, int firmaId);
    public void  insertFile(Firmafile firmafile);
    public Firmafile getFirmaFile(int yil, int donem, int firmaId);
    public List<Gelirbilancocode> getGelirbilancocodes();
    public List<Gelirbilancokayit> getGelirBilaGelirbilancokayits(int yil, int donem, int idfirma);
    public void  savaGelirBilanco(List<Gelirbilancokayit> gelirbilancokayits);
    public  void upateGelirBilanco(List<Gelirbilancokayit> gelirbilancokayits);
    public  void saveFirmaUser(Firma firma,Kullanici kullanici,Yetkilendirme yetkilendirme);
    public List<Otomatikcevap> getOtomatikCevaplar();
}
