package com.company.rating.dao;

import java.util.List;

import com.company.rating.domain.Yetkilendirme;
import com.company.rating.domain.YetkilendirmePK;

public interface YetkilendirmeDao extends GenericDao<Yetkilendirme, YetkilendirmePK>{

	
	
	public List<Yetkilendirme>  getYetkiliFirmalar(String kullanici);
}
