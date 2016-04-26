package com.company.rating.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.GelirbilancokayitDao;
import com.company.rating.domain.Gelirbilancokayit;
import com.company.rating.domain.GelirbilancokayitPK;

@Repository
public class  GelirbilancokayitDaoImpl extends GenericDaoImpl<Gelirbilancokayit, GelirbilancokayitPK> implements GelirbilancokayitDao{

	@Override
	public List<Gelirbilancokayit> getGelirBilaGelirbilancokayits(int yil, int donem, int idfirma) {
		return(List<Gelirbilancokayit>) entityManager.createQuery("Select b  " +
		"from Gelirbilancokayit b where b.id.idFirma=:firmaid and  b.id.yil=:yil and b.id.donem=:donem").setParameter("firmaid", idfirma)
		.setParameter("yil", yil).setParameter("donem", donem).getResultList();
		
	}

	@Override
	public List<Gelirbilancokayit> deleteGelirBilaGelirbilancokayits(int yil, int donem, int idfirma) {
		Query q=entityManager.createNativeQuery("delete  from gelirbilancokayit  where idFirma=? and  yil=? and donem=?");
		q.setParameter(1, idfirma);
		q.setParameter(2, yil);
		q.setParameter(3, donem);
		q.executeUpdate();
		// TODO Auto-generated method stub
		return null;
	}

	

}