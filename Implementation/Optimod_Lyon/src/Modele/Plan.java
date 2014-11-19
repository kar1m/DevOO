package Modele;

import java.util.*;

import org.w3c.dom.*;

/**
 * 
 */
public class Plan {

    /**
     * 
     */
    public Plan() {
    	this.listeNoeuds = new Vector<Noeud>();
    	this.listeTroncons = new Vector<Troncon>();
    }
    
    public void initPlan(Element xmlChunk)
    {
    	NodeList listeNoeuds = xmlChunk.getElementsByTagName("Noeud");
    	for (int i = 0; i<listeNoeuds.getLength(); i++)
    	{
    		Noeud noeudActuel = new Noeud();
    		noeudActuel.initNoeud((Element)listeNoeuds.item(i));
    		this.listeNoeuds.add(noeudActuel);
    	}
    }
    
	/**
	 * @return the listeNoeuds
	 */
	public Vector<Noeud> getListeNoeuds() {
		return listeNoeuds;
	}

	/**
	 * @param listeNoeuds the listeNoeuds to set
	 */
	public void setListeNoeuds(Vector<Noeud> listeNoeuds) {
		this.listeNoeuds = listeNoeuds;
	}

	/**
	 * @return the listeTroncons
	 */
	public Vector<Troncon> getListeTroncons() {
		return listeTroncons;
	}

	/**
	 * @param listeTroncons the listeTroncons to set
	 */
	public void setListeTroncons(Vector<Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;
	}

	private Vector<Noeud> listeNoeuds;
    
    private Vector<Troncon> listeTroncons;

}