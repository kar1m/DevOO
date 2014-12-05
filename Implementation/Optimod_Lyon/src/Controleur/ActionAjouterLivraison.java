package Controleur;

import Modele.DataWareHouse;
import Modele.Livraison;
import Modele.PlageHoraire;

/**
 * 
 */
public class ActionAjouterLivraison extends Action {

	/**
	 * Modele associe a laction
	 */
	private DataWareHouse modele;
	/**
	 * Livraison associee a laction
	 */
	private Livraison livraison;
	/**
	 * PlageHoraire associee a laction
	 */
	private PlageHoraire plageHoraire; 
	
	public ActionAjouterLivraison(DataWareHouse modele,PlageHoraire plageHoraire, Livraison livraison)
	{
		this.modele = modele;
		this.livraison = livraison; 
		this.plageHoraire = plageHoraire; 
	}

	@Override
	public boolean Executer() {
		// TODO Auto-generated method stub
		modele.ajouterLivraison(plageHoraire, livraison);
		return true;
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		plageHoraire = modele.supprimerLivraison(livraison);
		return true;
	}
}