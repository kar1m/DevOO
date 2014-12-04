package Modele;

import java.util.*;

/**
 * 
 */
public class Tournee {

    /**
     * 
     */
    public Tournee(Vector<Vector<Chemin>> map) {
    	this.listeChemins = map; 
    }

    /**
     * 
     */
    private Vector<Vector<Chemin>> listeChemins;

	public Vector<Vector<Chemin>> getListeChemins() {
		return listeChemins;
	}
    
    
}