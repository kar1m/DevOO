package Modele;

import java.util.*;

/**
 * Tournee contenant les plus courts chemins calculés
 */
public class Tournee {

    public Tournee(Vector<Vector<Chemin>> map) {
    	this.listeChemins = map; 
    }

    /**
     * Chemins calculés subdivisés en plages horaires
     */
    private Vector<Vector<Chemin>> listeChemins;

	public Vector<Vector<Chemin>> getListeChemins() {
		return listeChemins;
	}
    
    
}