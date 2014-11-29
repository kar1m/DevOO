package Vue;

import java.awt.EventQueue;

import Controleur.Application;
import Modele.DataWareHouse;
import Outils.Proprietes;
import Tests.ControlTest;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		DataWareHouse modele = new DataWareHouse();
		Fenetre vue = new Fenetre();
		Application controlleur = new Application(vue, modele);
		
		ControlTest tests = new ControlTest();
		tests.testUndoRedo();
		
		vue.getPlan().addMouseListener(controlleur);
		vue.getBtnChargerPlan().addActionListener(controlleur);
		vue.getBtnChargerDemandeLivraison().addActionListener(controlleur);
		vue.getBtnRedo().addActionListener(controlleur);
		vue.getBtnUndo().addActionListener(controlleur);
	}
}
