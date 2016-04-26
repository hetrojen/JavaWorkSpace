package com.company.rating.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.DegerlendirmeagaciDao;
import com.company.rating.domain.Degerlendirmeagaci;
import com.company.rating.domain.DegerlendirmeagaciPK;



@Repository
public class DegerlendirmeagaciDaoImpl extends GenericDaoImpl<Degerlendirmeagaci, DegerlendirmeagaciPK> implements DegerlendirmeagaciDao {

	
	public List<Degerlendirmeagaci> getDegerlendirmeagaci(int firmatipi,
			int sektor,int secim) {
		// TODO Auto-generated method stub
		return(List<Degerlendirmeagaci>) entityManager.createQuery("Select b  from Degerlendirmeagaci b where b.id.idSektor=:sektor and  b.id.idFirmatipi=:firmatipi and b.id.secim=:secim  order by  idParent,sira").setParameter("sektor", sektor).setParameter("firmatipi", firmatipi).setParameter("secim", secim).getResultList();
	}

	



}
