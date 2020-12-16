package com.pottin.model;

import java.util.Random;


public class ResponseStatusDisplay {

	public byte chan_1_dx10_rack ;
	public 	byte chan_1_dx10_slot ;
	public 	int chan_1_dx10_computer_horaire ;
	public 	int chan_1_dx10_computer_pots ;
	public 	int chan_1_dx10_consigne_vitesse_conv;
	public 	int chan_1_dx10_duree_arret_en_cours ;
	public 	int chan_1_dx10_duree_arret_cumules ;
	public 	boolean chan_1_dx10_KM1 ;
	public 	boolean chan_1_dx10_memp_accumulation ;
	public 	int  chan_1_dx10_nombre_darret;
	public 	int chan_1_dx10_num_message_puptre ;
	public 	boolean chan_1_dx10_produit ;
	public 	int chan_1_dx10_tempsRemplissage ;
	public 	boolean chan_1_dx10_verrtine_verte;
	
	
	public ResponseStatusDisplay(int chdannelID)
	{
//		get_staus(chdannelID);
	}
	public  void get_staus(int channelID)
	{
		
		chan_1_dx10_rack=(byte) genRand(0,1);
		chan_1_dx10_slot =(byte) genRand(0,1);
		chan_1_dx10_computer_horaire = genRand(1200,9000);
//		res.chan_1_dx10_computer_pots = genRand(1,12000);
//		res.chan_1_dx10_consigne_vitesse_conv= genRand(124420,12114442);
//		res.chan_1_dx10_duree_arret_en_cours= genRand(245,12000); 
//		res.chan_1_dx10_duree_arret_cumules = genRand(1,1412);
//		res.chan_1_dx10_KM1 =getBool();
//		res.chan_1_dx10_memp_accumulation =getBool();
//		res.chan_1_dx10_nombre_darret=genRand(1,14);
//		res.chan_1_dx10_num_message_puptre=genRand(1,9) ;
//		res.chan_1_dx10_produit =getBool();
//		res.chan_1_dx10_tempsRemplissage=genRand(99875,10045781) ;
//		res.chan_1_dx10_verrtine_verte=getBool();
//	
//		return res;
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
	
//	public Channel_1 getChannel_1()
//	{
//        Channel_1 chn=new Channel_1();
//        return chn;
//
//	}

}
