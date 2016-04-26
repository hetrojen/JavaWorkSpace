package com.company.rating.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.FirmafileDao;
import com.company.rating.domain.Firmafile;
import com.company.rating.domain.FirmafilePK;
@Repository
public class FirmafileDaoImpl extends  GenericDaoImpl<Firmafile, FirmafilePK> implements FirmafileDao{
	 

	@Override
	public String getFileName(int yil, int donem, int firmaId) {
		// TODO Auto-generated method stub
		List<Firmafile> ss=(List<Firmafile>) entityManager.createQuery("Select b  from Firmafile b where b.id.idFirma=:firma and  b.id.yil=:yil and b.id.donem=:donem").setParameter("firma", firmaId).setParameter("donem", donem).setParameter("yil", yil).getResultList();
		if(ss==null || ss.isEmpty()){
			return null;
		}
		Firmafile firmafile=ss.get(0);
		return firmafile.getFilename();
	}

	@Override
	public void deleteFile(int yil, int donem, int firmaId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("delete  from firmafiles  where idFirma=? and  yil=? and donem=?");
		q.setParameter(1, firmaId);
		q.setParameter(2, yil);
		q.setParameter(3, donem);
		q.executeUpdate();
	}

}
