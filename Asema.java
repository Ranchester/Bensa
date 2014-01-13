/*Luokka Asemat kuvaa bensa-asemia, jolla on erin‰isi‰ ominaisuuksia. Nimi, Hinta, Et‰isyys ja Valinta palautetaan
omissa metodeissa. Lis‰ksi luokassa on metodit bensan kulutuksen ja hinnan laskemista varten, sek‰ lopuksi viel‰ metodi
tulostusta varten. */

public class Asema {
	
	/*Attribuutit*/
	
	private String asemaNimi;
  	private double asemaLHinta;
	private int asemaEtaisyys;
	private int asemaValinta;
	private double asemaKulutus;
	private double asemaKHinta;
	private double asemaAika;
	
	/*Konstruktori*/
	
  	public Asema (String nimi, double hinta, int etaisyys, int valinta, double bensa_kulutus, double kokonais_hinta, double aika) {

    	asemaNimi = nimi;
    	asemaLHinta = hinta;
		asemaEtaisyys = etaisyys;
		asemaValinta = valinta;
		asemaKulutus = bensa_kulutus;
		asemaKHinta = kokonais_hinta;
		asemaAika = aika;

  	}

	/*Metodit attribuuttien palautuksia varten*/
	
	public double palautaHinta() {

    	return asemaLHinta;
	}
	
	public String palautaNimi() {
		return asemaNimi;
	}
	
	public int palautaEtaisyys() {
	
	return asemaEtaisyys;
	
	}
	
	public int palautaValinta() {
	
	return asemaValinta;
	
	}
	
	/* metodi laskee bensan kulutuksen sen mukaan ajetaanko maantie- vai kaupunkiajoa */
	
	public double bensanKulutus() {
	
	 if  (asemaValinta == 1) {
			
				asemaKulutus = 0.08 * asemaEtaisyys;

			
			}
			
			if (asemaValinta == 2) {
			
				asemaKulutus = 0.065 * asemaEtaisyys;
				
			}
			
		return asemaKulutus; 
		
	}
	
/* Metodi laskee kokonaishinnan tarvittavista attribuuteista */

	public double kokonaisHinta() {
	
		asemaKHinta = asemaKulutus * asemaLHinta + (asemaAika / 120);
		
		return asemaKHinta;
	
	}
	
	public void tulostaNimi() {
	
	System.out.println("" + asemaNimi);
	
	}
	
	public void tulostaHinta() {
	
	System.out.println("" + Math.round(asemaKHinta*100.0) / 100.0);
	
	}
	
	public void tulostaEtaisyys() {
	
	System.out.println("" + asemaEtaisyys);
	
	}

/* Metodi tulostaa tiedot */

	public void tulostaAsema() {
		
		System.out.println ("Asema: " + asemaNimi + "\n Litrahinta: " + asemaLHinta + " e\n Etaisyys: " + asemaEtaisyys + " \nKaupunki(1) vai Maantie(2) " + asemaValinta +"\n");	
		
		System.out.println ("Aikaa kuluu " + Math.round(asemaAika / 60) + " minuuttia \n");

		System.out.println ("Bensan kulutus on " + Math.round(asemaKulutus*100.0) / 100.0 + " litraa. \n ja kokonaishinnaksi tulee: " + Math.round(asemaKHinta*100.0) / 100.0 + "e\n");
	
	}	

} //Asema-luokka p‰‰ttyy





	

		