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
    	this.tronconEntrant = new Vector<Troncon>();
    	this.tronconSortant = new Vector<Troncon>();
    }
    
    public void initNoeud (Element xmlChunk)
    {
    	this.idNoeud = Integer.parseInt(xmlChunk.getAttribute("id"));
    	this.x = Integer.parseInt(xmlChunk.getAttribute("x"));
    	this.y = Integer.parseInt(xmlChunk.getAttribute("y"));
    }

    /**
	 * @return the tronconSortant
	 */
	public Vector<Troncon> getTronconSortant() {
		return tronconSortant;
	}

	/**
	 * @param tronconSortant the tronconSortant to set
	 */
	public void setTronconSortant(Troncon tronconSortant) {
		this.tronconSortant.addElement(tronconSortant);
	}

	/**
	 * @return the tronconEntrant
	 */
	public Vector<Troncon> getTronconEntrant() {
		return tronconEntrant;
	}

	/**
	 * @param tronconEntrant the tronconEntrant to set
	 */
	public void setTronconEntrant(Troncon tronconEntrant) {
		this.tronconEntrant.addElement(tronconEntrant);
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

	/**
	 * @return the idNoeud
	 */
	public int getIdNoeud() {
		return idNoeud;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
    
    public static boolean egaux(Noeud noeud1, Noeud noeud2)
    {
    	return (noeud1.getIdNoeud()==noeud2.getIdNoeud());
    }

}