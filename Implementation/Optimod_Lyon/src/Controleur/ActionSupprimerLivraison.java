package Controleur;

import Modele.DataWareHouse;
import Modele.Livraison;
import Modele.PlageHoraire;

/**
 * 
 */
public class ActionSupprimerLivraison extends Action {

	private DataWareHouse modele;
	private Livraison livraison;
	private PlageHoraire plageHoraire; 
	
	public ActionSupprimerLivraison(DataWareHouse modele, Livraison livraison)
	{
		this.modele = modele;
		this.livraison = livraison; 
	}

	@Override
	public boolean Executer() {
		// TODO Auto-generated method stub
		plageHoraire = modele.supprimerLivraison(livraison);
		return false;
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		modele.ajouterLivraison(plageHoraire, livraison);
		return false;
	}

}