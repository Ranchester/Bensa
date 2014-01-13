/*  Ohjelma laskee bensa-asemien hinnoista halvimman mahdollisen aseman. Hinta muodostuu litrahinnasta, ajasta,
ajetusta matkasta, sek‰ siit‰ ajetaanko kaupungissa vai maantiell‰.

Ensin kysyt‰‰n montako asemaa halutaan vertailuun. 
Sitten ohjelma kysyy montako litraa tankataan. T‰m‰n j‰lkeen kysyt‰‰n jokaisen aseman tiedot. 
T‰m‰n j‰lkeen ohjelma tulostaa asemien tiedot ja tulostaa halvimman aseman. 

Kaupunkiajossa bensankulutus on 8l / 100km ja maantieajossa 6,5l/100km. 
Matkaan kulunut bensa lasketaan sen huoltoaseman hinnan mukaan, josta bensaa ollaan hakemassa. 

Ohjelma ottaa huomioon myˆs arvioidun ajan ja siit‰ tulevat lis‰kustannukset.
Maantieajossa yhden kilometrin kulkeminen kest‰‰ v‰lill‰ 35 - 55 sekuntia, 
kun taas kaupunkiajossa yhden kilometrin kulkeminen kest‰‰ v‰lill‰ 50 - 75 sekuntia. 
Yhden kilometrin kulkemiseen kulunut aika arvotaan jokaista kilometri‰ kohti erikseen.
Tankkaus kest‰‰ 75 - 120 sekuntia. Yksi kulunut minuutti maksaa 0.50e. Kulunut aika pyˆristet‰‰n aina
seuraavaan minuuttiin.

Jos edullisimmaksi asemaksi tulee kaksi asemaa, valitaan l‰hemp‰n‰ oleva asema. 
Jos kaksi asemaa saavat saman hinnan ja et‰isyyden kotoa, niin valitaan ensiksi syˆtetty asema.  


*/

import java.io.*;
import java.util.*;

public class Harjoitus1 {

private static Asema[] bensaasema; /* Kun taulukkoon talletetaan Asema-olioita, taulukon tyyppi on Asema[].*/

public static void main ( String [] args ) throws IOException {

/* p‰‰ohjelmassa k‰ytetyt attribuutit */

int asema_lkm = 1; 
int tankattava_l = 1; 
String nimi; 
int etaisyys = 1; 
double hinta = 1; /* Aseman litrahinta */
int valinta = 1; 
int i; 
int j; 
Asema asemaolio; /* Viittausmuuttuja Asema-oliota varten */
double bensa_kulutus = 1; 
double aika = 0; /* Kulunut aika sekunneissa */ 
double aika_hinta = 0; /* Ajasta tuleva lis‰hinta */
double kokonais_hinta = 1; /* Kokonaiskustannukset */

/* BufferedReader n‰pp‰imistˆlt‰ lukemista varten. */

BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));

/* Kysyt‰‰n tarvittavat tiedot */

System.out.println("Anna vertailtavien bensa-asemien lukumaara (1-10) >");

/* Tarkistaa onko bensa asema lkm oikea formaatti */

asema_lkm = tarkistaInt(asema_lkm);

/* Tarkistaa ehdon ett‰ asema lukum‰‰r‰ ei voi olla 0 tai yli 10 */
	
asema_lkm = tarkistaLkmEhdot(asema_lkm);

System.out.println("Anna tankattavan bensiinin maara litroissa >");

/* Tarkistaa onko litram‰‰r‰ oikea formaatti */

tankattava_l = tarkistaInt(tankattava_l);

/* Tarkistaa ehdon ett‰ asema lukum‰‰r‰ ei voi olla alle 0 tai yli 200 */

tankattava_l = tarkistaTankattavaEhdot(tankattava_l);
		
/* Luodaan taulukko asemien tallettamista varten k‰ytt‰j‰n m‰‰rittelem‰n lukum‰‰r‰n mukaan. */

	bensaasema = new Asema[asema_lkm];

/* Silmukka taulukon luontia varten */

	for(i = 0; i <asema_lkm; i++) {
   				
		System.out.println("\nAnna " + (i+1) + ". bensa-aseman nimi > ");
		nimi = stdin.readLine();
   					
		System.out.println("\nAnna litrahinta >");
			
		/* Tarkistaa onko hinta oikea formaatti */
		
		hinta = tarkistaDouble(hinta);			
			
		/* Tarkista ett‰ hinta ei voi olla negatiivinen */
		
		hinta = tarkistaHintaEhdot(hinta);

		System.out.println("\nEtaisyys kotoa (km)>");

		/* Tarkistaa onko hinta oikea formaatti */
		
		etaisyys = tarkistaInt(etaisyys);

		/* Tarkista ett‰ et‰isyys ei voi olla negatiivinen eik‰ yli 200 */
		
		etaisyys = tarkistaTankattavaEhdot(etaisyys);	
										
		/* Kysyt‰‰n onko kaupunki- vai maantieajoa */	
		
		valinta = tarkistaValinta(valinta);
				
		/* lis‰t‰‰n satunnaistettua aikaa etaisyytta(yht‰ kilometri‰) kohden, sek‰ tankkauksen kesto*/
		
		aika = laskeAika(aika, valinta, etaisyys);
					
   		/*Luodaan Asema-olio k‰ytt‰j‰n antamien tietojen mukaan.*/
   			
   		asemaolio = new Asema(nimi, hinta, etaisyys, valinta, bensa_kulutus, kokonais_hinta, aika);
			
		/*Lis‰t‰‰n Asema-olio taulukkoon for-silmukan indeksin m‰‰r‰‰m‰lle paikalle. Ensimm‰inen olio
   		talletetaan indeksiin 0, seuraava indeksiin 1 jne. kunnes taulukko on t‰ynn‰.*/
			
		bensaasema[i] = new Asema(nimi, hinta, etaisyys, valinta, bensa_kulutus, kokonais_hinta, aika); 
   			
		/*Jokaisen Asema olion luonnin j‰lkeen aika nollataan */

		aika = 0;
											
		} 
			
			/*Jos taulukko ei ole tyhj‰, kutsutaan jokaisen Asema-olion metodeja.*/
			
		if(bensaasema != null) {
					
			for(i = 0; i < bensaasema.length; i++) {
								
				bensaasema[i].bensanKulutus();
				bensaasema[i].kokonaisHinta();
				bensaasema[i].tulostaAsema();
									
			}	
		}
				
			else
				System.out.println("Taulukko on tyhja.");
							
		//* Tarkistetaan mik‰ asema on halvin
						
		if(asema_lkm == 1) {
			bensaasema[0].tulostaNimi();
			System.out.println("on ainoa asema.");
			}
				
			else if (asema_lkm > 1) {
				
			//* K‰ytet‰‰n Math-luokan metodeja apuna minimien etsimisess‰ (varmuuden vuoksi myˆs max.)
				
			double min = bensaasema[0].kokonaisHinta();
			double max = bensaasema[0].kokonaisHinta();
				
				for(i=1; i < bensaasema.length; ++i) {
					
					min = Math.min(bensaasema[i].kokonaisHinta(), min);
					max = Math.max(bensaasema[i].kokonaisHinta(), max);

				}

				//* Kun minimi on etsitty tulostetaan halvin asema
				for(i=0; i < bensaasema.length; ++i){
					
				if (bensaasema[i].kokonaisHinta() == min){
					System.out.println("Bensaasema:");
					bensaasema[i].tulostaNimi();
					}
					
				}
					
				System.out.println("on halvin ja sen hinta on:" + Math.round(min*100.0) / 100.0 + "e");
																												
			}				
				
											
							
	}  /* Main p‰‰ttyy */
	
	public static int tarkistaInt(int arvo) throws IOException {
	
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	
	try {
	
		arvo = Integer.parseInt(stdin.readLine());

		}
		catch (NumberFormatException e){
	
		System.out.println("Syota numero");
	
		arvo = tarkistaInt(arvo);
	
		}
		
	return arvo;
		
	} /* tarkistaInt() p‰‰ttyy */
	
	public static double tarkistaDouble(double arvo) throws IOException {
	 
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	 
	try{
	
		arvo = Double.parseDouble(stdin.readLine());
		
		}
	
		catch (NumberFormatException e) {
	
		System.out.println("Syota numero >");
	
		arvo = tarkistaDouble(arvo);
	
		}
		
	return arvo;
	
	} /* tarkistaDouble() p‰‰ttyy */
	
	public static int tarkistaLkmEhdot(int arvo) throws IOException {
	
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	
	while (arvo >= 11 || arvo <= 0) {

	System.out.println("Ala anna nollaa tai yli kymmenta asemaa");
	
	System.out.println("Anna vertailtavien bensa-asemien lukumaara (1-10) >");

	arvo = tarkistaInt(arvo);

	arvo = tarkistaLkmEhdot(arvo);	
			
	}
	
	return arvo;
	
	} /* tarkistaLkmEhdot() p‰‰ttyy */
	
	public static int tarkistaTankattavaEhdot(int arvo) throws IOException {
	
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	
	while (arvo >= 200 || arvo <= 0) {

	System.out.println("Ala anna negatiivista arvoa, alaka yli 200");
	
	System.out.println("Anna uusi arvo >");

	arvo = tarkistaInt(arvo);

	arvo = tarkistaTankattavaEhdot(arvo);	
			
	}
	
	return arvo;		
	
	} /* tarkistaTankattavaEhdot() p‰‰ttyy */
	
	public static double tarkistaHintaEhdot(double arvo) throws IOException {
	
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	
	while (arvo <= 0) {

	System.out.println("Ala anna negatiivista hintaa");
	
	System.out.println("Anna litrahinta >");

	arvo = tarkistaDouble(arvo);

	arvo = tarkistaHintaEhdot(arvo);	
	
	}
	
	return arvo;
	
	
	} /* tarkistaHintaEhdot() p‰‰ttyy */
	
	public static int tarkistaValinta(int arvo) throws IOException {
	
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	
	do{
		System.out.println("\nKaupunki(1) vai Maantieajoa(2) >");
		
		arvo = tarkistaInt(arvo);
			
			if (arvo != 1 && arvo != 2){
				
						System.out.println("Anna 1 tai 2");
				}
					
		}while(arvo != 1 && arvo != 2);
		
	return arvo;
	
	} /* tarkistaValinta() p‰‰ttyy */
	
	public static double laskeAika(double time, int selection, int distance) throws IOException {
	
	BufferedReader stdin = new BufferedReader ( new InputStreamReader ( System.in ));
	
	int i;
	
	for(i=0; i <= distance; i++) {
			
		if (selection == 1) {
						
			time = time + (75 - java.lang.Math.random()*25);
			
			}
			
		else if (selection == 2) {
			
			time = time + (50 - java.lang.Math.random()*25);
					
			}
					
		} 
	
	time = time + (120 - java.lang.Math.random()*45);
	
	return time;	
	
	}

		
} /* Luokka p‰‰ttyy */