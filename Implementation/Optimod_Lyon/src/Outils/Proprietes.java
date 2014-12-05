package Outils;

import java.awt.Color;
/**
 * Classe contenant toutes les proprietes statiques et partagees du projet
 * @author Yassine Moreno
 *
 */
public class Proprietes {
	
	public static final String ERREUR_XML = "Fichier Non Valide";
	
	/**
	 *  Chemin du XSD livraison pour valider le XML.
	 */
	public static final String PATH_XSD_DL ="src/Outils/livraison.xsd";
	/**
	 *  Chemin du XSD plan pour valider le XML.
	 */
	public static final String PATH_XSD_PLAN = "src/Outils/plan.xsd";

	
	/**
	 * Commande AJOUTER_LIVRAISON interpretable par le controleur 
	 */
	public static final String AJOUTER_LIVRAISON = "Ajouter";
	/**
	 * Commande SUPP_LIVRAISON interpretable par le controleur 
	 */
	public static final String SUPP_LIVRAISON = "Supprimer";
	/**
	 * Commande CALC_TOURNEE interpretable par le controleur 
	 */
	public static final String CALC_TOURNEE = "Calculer";
	/**
	 * Commande CHARGER_PLAN interpretable par le controleur 
	 */
	public static final String CHARGER_PLAN = "Plan";
	/**
	 * Commande CHARGER_LIVRAISON interpretable par le controleur 
	 */
	public static final String CHARGER_LIVRAISON = "Livraison";
	/**
	 * Commande UNDO interpretable par le controleur 
	 */
	public static final String UNDO = "UNDO";
	/**
	 * Commande REDO interpretable par le controleur 
	 */
	public static final String REDO = "REDO";
	/**
	 * Commande SAVE interpretable par le controleur 
	 */
	public static final String SAVE = "Save";
	/**
	 * Délai d'attente du livreur
	 */
	public static final int ATTENTE = 600;
	
	/**
	 *  Calcul (choco) timeout
	 */
	public static final int CHOCO_TIMEOUT = 200000;

	
	/**
	 * Couleurs utilisees
	 */
	public static Color[] cols  = getDifferentColors(10);
	
	/**
	 * Methode qui genere les differentes couleurs
	 * @param n : nombre de couleurs a generer
	 * @return tableau de couleurs
	 */
	public static Color[] getDifferentColors(int n) {
	    Color[] cols = new Color[n];
	    for (int i = 1; i < n+1; i++)
	    	cols[i-1] = Color.getHSBColor((float) i / n, 1, 1);
	    return cols;
	}
	
}
