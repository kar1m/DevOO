package Vue;

import java.awt.EventQueue;

import Controleur.Application;
import Modele.DataWareHouse;
import Outils.Proprietes;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Proprietes.cols = Proprietes.getDifferentColors(10);
		
		
		DataWareHouse modele = new DataWareHouse();
		Fenetre vue = new Fenetre();
		Application controlleur = new Application(vue, modele);
	
		
		vue.getPlan().addMouseListener(controlleur);
		vue.getBtnChargerPlan().addActionListener(controlleur);
		vue.getBtnChargerDemandeLivraison().addActionListener(controlleur);
		vue.getBtnRedo().addActionListener(controlleur);
		vue.getBtnUndo().addActionListener(controlleur);
	}
}
