package com.company.rating.view.model;

import java.util.ArrayList;

public class FirmaDerecelendirme {
   private String not;
   private  int puan;
   private String descriptionNot;
   private  String descriptionFirma;
   private static ArrayList<FirmaDerecelendirme> firmaDerecelendirmeList=new ArrayList<FirmaDerecelendirme>();
   static{
	   FirmaDerecelendirme f=new FirmaDerecelendirme("AAA",100,"En Yüksek","Çok güçlü öz sermaye ve borç ödeme kapasitesi, mükemmel aktif kalitesi, sektöründe güçlü ve çok başarılı bir firma, mükemmel yönetim, kredi değerliliği çok yüksek.");
	   FirmaDerecelendirme f2=new FirmaDerecelendirme("AA",95,"Yüksek","Güçlü öz sermaye ve borç ödeme kapasitesi, yüksek likidite ve aktif kalitesi, sektöründe başarılı bir firma, iyi yönetim, kredi değerliliği yüksek.");
	   FirmaDerecelendirme f3=new FirmaDerecelendirme("A",85,"Yükseğe Yakın","Orta düzeyde öz sermaye ve borç ödeme kapasitesi, tatmin edici likidite ve aktif kalitesi, sektöründe orta seviyede bir firma, iyi yönetim, kredi değerliliği yükseğe yakın ");
	   FirmaDerecelendirme f4=new FirmaDerecelendirme("BBB",75,"Orta","Yüksek borçluluğa sahip olabilecek firma, kabul edilebilir aktif kalitesi ancak biraz zayıf likidite, dikkatli takip edilmesi gereken ve mali yapısı olası krizleri atlatabilecek kadar ");
	   FirmaDerecelendirme f5=new FirmaDerecelendirme("BB",65,"Kabul","Kabul edilebilir aktif kalitesi ancak sınıra yakın seviyede düşük likidite. Yüksek veya çok yüksek borçluluğa, düşük borç ödeme kapasitesine sahip olabilecek ve önemle takip ");
	   FirmaDerecelendirme f6=new FirmaDerecelendirme("B",55,"Dikkatli olmak kaydıyla kabul edilebilir","Genellikle kabul edilebilir aktif kalitesi ancak sınırda likidite. Yüksek borçluluk ve zayıf yönetim. Sürekli yakın izleme gerektiren firma.");
	   FirmaDerecelendirme f7=new FirmaDerecelendirme("CCC",45,"Kabul Edilebilirin Altında","Düşük aktif kalitesi, yüksek borçluluk, olası krizlerden yüksek etkilenme ve ödemelerinde aksaklık yaşanma ihtimali olan firma. Kredi değerliliği kabul edilebilirin altında.");
	   FirmaDerecelendirme f8=new FirmaDerecelendirme("CC",35,"Kötü","Mali yapısı zayıf olup, kredi değerliliği kötü olan firma. Başlangıçta kredi geri ödemelerinde problem olmayacağı düşünülse bile herhangi bir olumsuz gelişmede problem ");
	   FirmaDerecelendirme f9=new FirmaDerecelendirme("C",25,"Çok Kötü","Mali yapısı çok zayıf olup, kredi ilişkisine girilmesi halinde temerrüde düşme ihtimali yüksek olan firma.");
	   FirmaDerecelendirme f10=new FirmaDerecelendirme("D",15,"En Kötü","Kredi değerliliği olmayan, kredi ilişkisine kesinlikle girilmemesi gereken, kredi ilişkisine girilmesi halinde temerrüde düşme ihtimali çok yüksek olan firma.");
	   firmaDerecelendirmeList.add(f);
	   firmaDerecelendirmeList.add(f2);
	   firmaDerecelendirmeList.add(f3);
	   firmaDerecelendirmeList.add(f4);
	   firmaDerecelendirmeList.add(f5);
	   firmaDerecelendirmeList.add(f6);
	   firmaDerecelendirmeList.add(f7);
	   firmaDerecelendirmeList.add(f8);
	   firmaDerecelendirmeList.add(f9);
	   firmaDerecelendirmeList.add(f10);
	   
		


	   
	   
	   
	   
   }
   
   public FirmaDerecelendirme(String not,int puan,String descriptionNot, String descriptionFirma) {
	// TODO Auto-generated constructor stub
	   this.not=not;
	   this.puan=puan;
	   this.descriptionNot=descriptionNot;
	   this.descriptionFirma=descriptionFirma;
	   
}

	public static FirmaDerecelendirme getFirmaDerecelendirme(int puan) {
		FirmaDerecelendirme genelDereceInfo = null;
		int i = 0;
		if (puan > 100) {
			puan = 100;
		}

		for (FirmaDerecelendirme derecelendirme : FirmaDerecelendirme.getFirmaDerecelendirmeList()) {
			if (derecelendirme.getPuan() == 0) {

			}
			if (puan > derecelendirme.getPuan()) {
				genelDereceInfo = getFirmaDerecelendirmeList().get(i - 1);
				break;
			}
			i++;
		}
		if (genelDereceInfo == null) {
			genelDereceInfo = FirmaDerecelendirme.getFirmaDerecelendirmeList().get(i - 1);
		}

		return genelDereceInfo;
	}
public String getNot() {
	return not;
}
public void setNot(String not) {
	this.not = not;
}
public int getPuan() {
	return puan;
}
public void setPuan(int puan) {
	this.puan = puan;
}
public String getDescriptionNot() {
	return descriptionNot;
}
public void setDescriptionNot(String descriptionNot) {
	this.descriptionNot = descriptionNot;
}
public String getDescriptionFirma() {
	return descriptionFirma;
}
public void setDescriptionFirma(String descriptionFirma) {
	this.descriptionFirma = descriptionFirma;
}

public static ArrayList<FirmaDerecelendirme> getFirmaDerecelendirmeList() {
	return firmaDerecelendirmeList;
}
   
   
   
   
}
