package com.company.rating.dao.impl;

import org.springframework.stereotype.Repository;

import com.company.rating.dao.CevapDao;
import com.company.rating.domain.Cevap;
import com.company.rating.domain.CevapPK;

@Repository
public class CevapDaoImpl extends GenericDaoImpl<Cevap, CevapPK> implements CevapDao{

}
