package com.company.rating.util;

import java.math.BigDecimal;

import com.company.rating.domain.Kriter;
import com.company.rating.view.model.KantitatifTableModel;

public class TrendFormulUtil {

	// Trend Analizinde, firman�n finansal yap�, b�y�me ve karl�l�k oranlar�n�n
	// son iki d�nemdeki geli�me trendinin
	// (�rnek; 2002 ve 2003 y�llar�na ait oranlar�n kar��la�t�r�lmas� ve geli�im
	// trendinin bulunmas�) de�erlendirilmesi yap�l�r.
	// De�erlendirme �u �ekilde yap�l�r:
	// Trend Puan� = P2 + [P1 x (O2 � O1)/O1]
	// P1 : �lk y�l puan�
	// P2 : Son y�l puan�
	// O1 : �lk y�l oran�
	// O2 : Son y�l oran�
	// Ancak;
	// - Alacak Devir S�resi,
	// - Stok Devir S�resi,
	// - K�sa Vadeli Banka Bor�lar�/Toplam Bor�lar,
	// - K�sa Vadeli Bor�lar/Net Sat��lar,
	// - K�sa Vadeli Bor�lar/Toplam Gelirler,
	// - Nakdi Banka Kredisi / Toplam Bor�lar
	// oranlar� rakamsal de�eri artt�k�a k�t�le�en oranlar oldu�u i�in bu
	// oranlar i�in;
	// Trend Puan� = P2 + [P1 x (O1 � O2)/O1]
	// form�l� kullan�l�r.
	// � 0.7 puanl�k keskin art��lar ancak �nceki ve sonraki puan toplamlar�n�n
	// yar�s� kadar puan alabilir.
	// Bunun nedeni, �ok keskin ��k��lar�n normal olmad��� istikrar�
	// g�stermedi�i d���ncesidir.
	// Zaten ilgili oran �ok s��rad��� bu noktada kal�rsa bir sonraki sene daha
	// olumlu puan al�r (ama� istikrar� puanlamakt�r).
	// Di�er yandan, s�z konusu oran nokta analizinde geldi�i konum i�in zaten
	// puan almaktad�r.
	// � 0.7 puanl�k keskin d����ler puan almaz.
	//
	//
	// Bir oran�n alabilece�i minimum puan 0 maksimum puan 1�dir.

	public static BigDecimal getTrendPuan(Kriter kriter, KantitatifTableModel kantitatifTableModel,BigDecimal noktaAnaliziPuan) {
		BigDecimal zero = new BigDecimal(0);
		BigDecimal one = new BigDecimal(1);
		BigDecimal  puan=zero;
	    try{
		if(noktaAnaliziPuan.compareTo(zero)==0){
			return zero;
		}
		BigDecimal  p1=kantitatifTableModel.getPuanByKriterOnceki();
		BigDecimal  p2=kantitatifTableModel.getPuanByKriter();
		BigDecimal  o1=kantitatifTableModel.getRasyoOncekiyil();
		BigDecimal  o2=kantitatifTableModel.getRasyoBuyil();
		
		if(p2.subtract(p1).compareTo(new BigDecimal(0.7))>0){
			return  p1.add(p2).divide(new BigDecimal(2),3, BigDecimal.ROUND_HALF_EVEN);
		}
		if(p2.subtract(p1).compareTo(new BigDecimal(-0.7))<0){
			return zero;
		}
		
	
		
		if(kriter.getFormulTip()==1){
			// Trend Puan� = P2 + [P1 x (O2 � O1)/O1]
			puan=(((o2.subtract(o1)).divide(o1,3, BigDecimal.ROUND_HALF_EVEN)).multiply(p1)).add(p2);
		}
		// Trend Puan� = P2 + [P1 x (O1 � O2)/O1]
          if(kriter.getFormulTip()==2){
        	  puan=(((o1.subtract(o2)).divide(o1,3, BigDecimal.ROUND_HALF_EVEN)).multiply(p1)).add(p2);
		}
		if(puan.compareTo(zero)<0){
			return zero;
		}
        if(puan.compareTo(one)>0){
        	return one;
        }
	    }catch (ArithmeticException e) {
			// TODO: handle exception
	    	return zero;
		}
		return puan;
	}

	
}
