package com.company.rating.dao;

import java.util.List;

import com.company.rating.domain.Puanlar;
import com.company.rating.domain.PuanlarPK;

public interface PuanlarDao  extends GenericDao<Puanlar, PuanlarPK> {
	public List<Puanlar>  getDegerlendirmeagaci(int sektor);
}
