package com.company.rating.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.KantitatifdegerlendirmeDao;
import com.company.rating.domain.Kantitatifdegerlendirme;
import com.company.rating.domain.KantitatifdegerlendirmePK;
@Repository
public class KantitatifdegerlendirmeDaoImpl extends GenericDaoImpl<Kantitatifdegerlendirme, KantitatifdegerlendirmePK> implements KantitatifdegerlendirmeDao{

	
	public List<Kantitatifdegerlendirme> getKantitatifDegerlendirmeler(
			int firmatipi, int sektor) {
		// TODO Auto-generated method stub
		return(List<Kantitatifdegerlendirme>) entityManager.createQuery("Select b  from Kantitatifdegerlendirme b where b.id.idSektor=:sektor and  b.id.idFirmatipi=:firmatipi order by b.id.idParentDegerlendirme,b.id.idDerlendirme,b.sira").setParameter("sektor", sektor).setParameter("firmatipi", firmatipi).getResultList();
	}

}
