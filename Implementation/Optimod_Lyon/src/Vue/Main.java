package Vue;

import Controleur.Application;
import Modele.DataWareHouse;
/**
 * 
 * @author Mehdi Kitane
 *
 */
public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		DataWareHouse modele = new DataWareHouse();
		Fenetre vue = new Fenetre();
		vue.setVisible(true);
		Application controlleur = new Application(vue, modele);
				
		vue.getBtnChargerPlan().addActionListener(controlleur);
		vue.getBtnChargerDemandeLivraison().addActionListener(controlleur);
		vue.getBtnCalcul().addActionListener(controlleur);
		vue.getBtnRedo().addActionListener(controlleur);
		vue.getBtnUndo().addActionListener(controlleur);
		vue.getBtnEnregistrer().addActionListener(controlleur);
		
		vue.getPlan().addMouseListener(controlleur);
		vue.getTable().getT().addMouseListener(controlleur);
	}
}