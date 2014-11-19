package Modele;

import java.util.*;

import org.w3c.dom.*;

/**
 * Point repéré par ses coordonnées
 */
public class Noeud {

    /**
     * Point repéré par ses coordonnées
     */
    public Noeud() {
    }
    
    public void initNoeud (Element xmlChunk)
    {
    	this.idNoeud = Integer.parseInt(xmlChunk.getAttribute("id"));
    }

    /**
     * ID du noeud
     */
    private int idNoeud;

    /**
     * Abscisse du noeud
     */
    private int x;

    /**
     * Ordonnée du noeud
     */
    private int y;

    /**
     * 
     */
    private Vector<Troncon> tronconSortant;

    /**
     * 
     */
    private Vector<Troncon> tronconEntrant;

}