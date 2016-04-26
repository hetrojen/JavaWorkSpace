package com.company.rating.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.YetkilendirmeDao;
import com.company.rating.domain.Yetkilendirme;
import com.company.rating.domain.YetkilendirmePK;
@Repository
public class YetkilendirmeDaoImpl extends  GenericDaoImpl<Yetkilendirme, YetkilendirmePK> implements YetkilendirmeDao{


	public List<Yetkilendirme> getYetkiliFirmalar(String kullanici) {
		return(List<Yetkilendirme>) entityManager.createQuery("Select b  from Yetkilendirme b where b.id.username=:username").setParameter("username", kullanici).getResultList();
	
	}

}
