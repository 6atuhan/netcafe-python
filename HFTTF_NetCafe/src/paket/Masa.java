package paket;

public class Masa implements IMasa {
	String masaNo;
	private String baslangicSaati;
	private String bitisSaati;
	private String masaAcikmi;
	private String masaUcret;

	public String getBaslangicSaati() {
		return baslangicSaati;
	}
	public void setBaslangicSaati(String baslangicSaati) {
		this.baslangicSaati = baslangicSaati;
	}
	

	public String getBitisSaati() {
		return bitisSaati;
	}
	public void setBitisSaati(String bitisSaati) {
		this.bitisSaati = bitisSaati;
	}
	

	public String getMasaAcikmi() {
		return masaAcikmi;
	}
	public void setMasaAcikmi(String masaAcikmi) {
		this.masaAcikmi = masaAcikmi;
	}
	

	public String getMasaUcret() {
		return masaUcret;
	}
	public void setMasaUcret(String masaUcret) {
		this.masaUcret = masaUcret;
	}


}
