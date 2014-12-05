package Modele;

import java.util.*;

import org.w3c.dom.*;

/**
 * Point repere par ses coordonnees cartesiennes
 */
public class Noeud {

    /**
     * Point repere par ses coordonnees cartesiennes
     */
    public Noeud() {
    	this.tronconEntrant = new Vector<Troncon>();
    	this.tronconSortant = new Vector<Troncon>();
    }
	/**
	 * methode d'initialisation du noeud
	 * @param xmlChunk Element XML contenant les informations pour l'initialisation
	 * @throws Exception liee aux malformations semantiques et syntaxiques des fichiers
	 */
    public void initNoeud (Element xmlChunk) throws Exception
    {
    	try {
			this.idNoeud = Integer.parseInt(xmlChunk.getAttribute("id"));
			this.x = Integer.parseInt(xmlChunk.getAttribute("x"));
			this.y = Integer.parseInt(xmlChunk.getAttribute("y"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new Exception("Format num�rique Noeud non respect�");
		}
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
     * Liste de troncons sortants du noeud
     */
    private Vector<Troncon> tronconSortant;

    /**
     * Liste de troncons entrants du noeud
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
    
    

}