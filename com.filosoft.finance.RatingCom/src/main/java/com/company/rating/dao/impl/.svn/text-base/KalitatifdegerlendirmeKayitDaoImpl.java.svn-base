package com.company.rating.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.KalitatifdegerlendirmeKayitDao;
import com.company.rating.domain.KalitatifdegerlendirmeKayit;
import com.company.rating.domain.KalitatifdegerlendirmeKayitPK;


@Repository
public class KalitatifdegerlendirmeKayitDaoImpl extends GenericDaoImpl<KalitatifdegerlendirmeKayit, KalitatifdegerlendirmeKayitPK>  implements KalitatifdegerlendirmeKayitDao{

	
	public List<KalitatifdegerlendirmeKayit> getKalitatifDegerlendirmeKayitlari(
			int firmaId, int yil, int donem, String username) {
		// TODO Auto-generated method stub
		//return(List<KalitatifdegerlendirmeKayit>) entityManager.createQuery("Select b  from kalitatifdegerlendirme_kayit b where b.id.idSektor=:sektor and  b.id.idFirmatipi=:firmatipi").setParameter("sektor", sektor).setParameter("firmatipi", firmatipi).getResultList();
		return(List<KalitatifdegerlendirmeKayit>) entityManager.createQuery("Select b  " +
		"from KalitatifdegerlendirmeKayit b where b.id.idFirma=:firmaid and  b.id.yil=:yil and b.id.donem=:donem and b.id.username=:username").setParameter("firmaid", firmaId)
		.setParameter("yil", yil).setParameter("donem", donem).setParameter("username", username).getResultList();
	}

	@Override
	public int getOldestYearFromKalitatifKayit() {
		// TODO Auto-generated method stub
		return  (Integer)entityManager.createQuery("Select min(yil) from  KalitatifdegerlendirmeKayit").getSingleResult();
	}

	@Override
	public void deleteRecords(int yil, int donem, int firmaId) {
		// TODO Auto-generated method stub
		List<KalitatifdegerlendirmeKayit> ss=(List<KalitatifdegerlendirmeKayit>) entityManager.createQuery("Select b  from KalitatifdegerlendirmeKayit b where b.id.idFirma=:firma and  b.id.yil=:yil and b.id.donem=:donem").setParameter("firma", firmaId).setParameter("donem", donem).setParameter("yil", yil).getResultList();
		if(ss!=null && !ss.isEmpty()){
			Query q=entityManager.createNativeQuery("delete  from kalitatifdegerlendirme_kayit   where idFirma=? and  yil=? and donem=?");
			q.setParameter(1, firmaId);
			q.setParameter(2, yil);
			q.setParameter(3, donem);
			q.executeUpdate();
		
		}
	}

}
