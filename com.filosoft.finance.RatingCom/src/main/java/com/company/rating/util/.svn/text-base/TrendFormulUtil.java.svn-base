package com.company.rating.util;

import java.math.BigDecimal;

import com.company.rating.domain.Kriter;
import com.company.rating.view.model.KantitatifTableModel;

public class TrendFormulUtil {

	// Trend Analizinde, firmanýn finansal yapý, büyüme ve karlýlýk oranlarýnýn
	// son iki dönemdeki geliþme trendinin
	// (Örnek; 2002 ve 2003 yýllarýna ait oranlarýn karþýlaþtýrýlmasý ve geliþim
	// trendinin bulunmasý) deðerlendirilmesi yapýlýr.
	// Deðerlendirme þu þekilde yapýlýr:
	// Trend Puaný = P2 + [P1 x (O2 – O1)/O1]
	// P1 : Ýlk yýl puaný
	// P2 : Son yýl puaný
	// O1 : Ýlk yýl oraný
	// O2 : Son yýl oraný
	// Ancak;
	// - Alacak Devir Süresi,
	// - Stok Devir Süresi,
	// - Kýsa Vadeli Banka Borçlarý/Toplam Borçlar,
	// - Kýsa Vadeli Borçlar/Net Satýþlar,
	// - Kýsa Vadeli Borçlar/Toplam Gelirler,
	// - Nakdi Banka Kredisi / Toplam Borçlar
	// oranlarý rakamsal deðeri arttýkça kötüleþen oranlar olduðu için bu
	// oranlar için;
	// Trend Puaný = P2 + [P1 x (O1 – O2)/O1]
	// formülü kullanýlýr.
	// § 0.7 puanlýk keskin artýþlar ancak önceki ve sonraki puan toplamlarýnýn
	// yarýsý kadar puan alabilir.
	// Bunun nedeni, çok keskin çýkýþlarýn normal olmadýðý istikrarý
	// göstermediði düþüncesidir.
	// Zaten ilgili oran çok sýçradýðý bu noktada kalýrsa bir sonraki sene daha
	// olumlu puan alýr (amaç istikrarý puanlamaktýr).
	// Diðer yandan, söz konusu oran nokta analizinde geldiði konum için zaten
	// puan almaktadýr.
	// § 0.7 puanlýk keskin düþüþler puan almaz.
	//
	//
	// Bir oranýn alabileceði minimum puan 0 maksimum puan 1’dir.

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
			// Trend Puaný = P2 + [P1 x (O2 – O1)/O1]
			puan=(((o2.subtract(o1)).divide(o1,3, BigDecimal.ROUND_HALF_EVEN)).multiply(p1)).add(p2);
		}
		// Trend Puaný = P2 + [P1 x (O1 – O2)/O1]
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
