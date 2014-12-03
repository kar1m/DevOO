package Modele;

import java.util.*;

/**
 * 
 */
public class Tournee {

    /**
     * 
     */
    public Tournee(Map<PlageHoraire,Vector<Chemin>> map) {
    	this.listeChemins = map; 
    }

    /**
     * 
     */
    private Map<PlageHoraire,Vector<Chemin>> listeChemins;

	public Map<PlageHoraire, Vector<Chemin>> getListeChemins() {
		return listeChemins;
	}
    
    
}