package Modele;

import java.util.*;

/**
 * Tournee contenant les plus courts chemins calcules
 */
public class Tournee {

    public Tournee(Vector<Vector<Chemin>> map) {
    	this.listeChemins = map; 
    }

    /**
     * Chemins calcules subdivises en plages horaires
     */
    private Vector<Vector<Chemin>> listeChemins;

	public Vector<Vector<Chemin>> getListeChemins() {
		return listeChemins;
	}
    
    
}