package com.company.rating.dao;

import java.util.List;

import com.company.rating.domain.KalitatifdegerlendirmeKayit;
import com.company.rating.domain.KalitatifdegerlendirmeKayitPK;

public interface KalitatifdegerlendirmeKayitDao extends GenericDao<KalitatifdegerlendirmeKayit, KalitatifdegerlendirmeKayitPK>{
	public  List<KalitatifdegerlendirmeKayit> getKalitatifDegerlendirmeKayitlari(int firmaId, int yil, int donem, String username);
	public int getOldestYearFromKalitatifKayit();
   public void deleteRecords(int yil, int donem, int firmaId); 
}
