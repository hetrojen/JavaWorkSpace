package com.company.rating.view.model;

import java.math.BigDecimal;

import com.company.rating.domain.Kriter;

public class RasyoAnalizGrafikModel {
private Kriter kriter;
private BigDecimal result;
public RasyoAnalizGrafikModel(Kriter kriter) {
	
	// TODO Auto-generated constructor stub
	this.kriter=kriter;
}
public Kriter getKriter() {
	return kriter;
}
public void setKriter(Kriter kriter) {
	this.kriter = kriter;
}
public BigDecimal getResult() {
	return result;
}
public void setResult(BigDecimal result) {
	this.result = result;
}

}
