package com.company.rating.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.KalitatifdegerlendirmeDao;
import com.company.rating.domain.Kalitatifdegerlendirme;
import com.company.rating.domain.KalitatifdegerlendirmePK;
@Repository
public class KalitatifdegerlendirmeDaoImpl extends GenericDaoImpl<Kalitatifdegerlendirme, KalitatifdegerlendirmePK> implements KalitatifdegerlendirmeDao{

	
	public List<Kalitatifdegerlendirme> getKalitatifDegerlendirmeler(
			int firmatipi, int sektor, int secim) {
		return(List<Kalitatifdegerlendirme>) entityManager.createQuery("Select b  from Kalitatifdegerlendirme b where b.id.idSektor=:sektor and  b.id.idFirmatipi=:firmatipi and b.id.secim=:secim order by b.id.idParentDegerlendirme,b.id.idDerlendirme,b.sira").setParameter("sektor", sektor).setParameter("firmatipi", firmatipi).setParameter("secim", secim).getResultList();
	
	}

}
