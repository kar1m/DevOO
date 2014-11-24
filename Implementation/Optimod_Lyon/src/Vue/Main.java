package Vue;

import java.awt.EventQueue;

import Controleur.Application;
import Outils.Proprietes;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Application a = new Application();
		// Passer toujours par gererCommande !!!!!!!!!!!!!!!!!!!
		a.gererCommande(Proprietes.CHARGER_PLAN);
		Fenetre frame = new Fenetre();
		frame.setVisible(true);
		frame.chargerPlan(a.getModele().getPlanApp());
	}
}
