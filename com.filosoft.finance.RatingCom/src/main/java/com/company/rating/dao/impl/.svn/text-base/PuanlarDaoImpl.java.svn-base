package com.company.rating.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.PuanlarDao;
import com.company.rating.domain.Puanlar;
import com.company.rating.domain.PuanlarPK;
@Repository
public class PuanlarDaoImpl extends  GenericDaoImpl<Puanlar, PuanlarPK>  implements PuanlarDao{


	public List<Puanlar> getDegerlendirmeagaci(int sektor) {
		// TODO Auto-generated method stub
		return(List<Puanlar>) entityManager.createQuery("Select b  from Puanlar b where   b.id.idSektor=:sektor order by id.idSektor,id.idKriter,id.puan").setParameter("sektor", sektor).getResultList();
	}

}
