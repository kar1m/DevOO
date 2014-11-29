package Outils;

import java.awt.Color;

public class Proprietes {
	
	public static final String ERREUR_XML = "Fichier Non Valide";
	
	// Chemin des XSD pour valider le XML.
	public static final String PATH_XSD_DL ="src/Outils/livraison.xsd";
	public static final String PATH_XSD_PLAN = "src/Outils/plan.xsd";

	
	// Commandes interpr�tables par le controleur 
	public static final String AJOUTER_LIVRAISON = "Ajouter";
	public static final String SUPP_LIVRAISON = "Supprimer";
	public static final String CALC_TOURNEE = "Calculer";
	public static final String CHARGER_PLAN = "Plan";
	public static final String CHARGER_LIVRAISON = "Livraison";
	public static final String UNDO = "UNDO";
	public static final String REDO = "REDO";
	public static final String SAVE = "Save";

	public static Color[] cols ;
	
	public static Color[] getDifferentColors(int n) {
	    Color[] cols = new Color[n];
	    for (int i = 1; i < n+1; i++)
	    	cols[i-1] = Color.getHSBColor((float) i / n, 1, 1);
	    return cols;
	}
	
}
