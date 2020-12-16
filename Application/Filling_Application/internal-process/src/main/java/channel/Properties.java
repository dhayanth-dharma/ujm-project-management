package channel;

import java.util.Random;

public class Properties {

	byte rack ;
	byte slot ;
	int computer_horaire ;
	int computer_pots ;
	int consigne_vitesse_conv;
	int duree_arret_en_cours ;
	int duree_arret_cumules ;
	boolean KM1 ;
	boolean memp_accumulation ;
	int  nombre_darret;
	int num_message_puptre ;
	boolean produit ;
	int tempsRemplissage ;
	boolean verrtine_verte;
	
	public Properties()
	{
		getValue();
	}
	public void  getValue()
	{
		rack=(byte) genRand(0,1);
		slot =(byte) genRand(0,1);
		computer_horaire = genRand(1200,9000);
		computer_pots = genRand(1,12000);
		consigne_vitesse_conv= genRand(124420,12114442);
		duree_arret_en_cours= genRand(245,12000); 
		duree_arret_cumules = genRand(1,1412);
		KM1 =getBool();
		memp_accumulation =getBool();
		nombre_darret=genRand(1,14);
		num_message_puptre=genRand(1,9) ;
		produit =getBool();
		tempsRemplissage=genRand(99875,10045781) ;
		verrtine_verte=getBool();
		
		
	}

	public int genRand(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	public boolean getBool()
	{
		 int a=genRand(0,1);
		 if(a==0)
			 return false;
		 else 
			 return true;
	}

	
}
