package Vue;

import java.awt.EventQueue;

import Controleur.Application;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Application a = new Application();
		a.chargerPlan();
		Fenetre frame = new Fenetre();
		frame.setVisible(true);
		frame.chargerPlan(a.getPlanApp());
	}
}
