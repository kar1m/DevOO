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
    	NodeList listeXMLNoeuds = xmlChunk.getElementsByTagName("Noeud");
    	for (int i = 0; i<listeXMLNoeuds.getLength(); i++)
    	{
    		Noeud noeudActuel = new Noeud();
    		noeudActuel.initNoeud((Element)listeXMLNoeuds.item(i));
    		this.listeNoeuds.add(noeudActuel);
    	}

    	for (int i = 0; i<listeXMLNoeuds.getLength(); i++)
    	{
        	NodeList listeXMLTroncons = ((Element)listeXMLNoeuds.item(i))
        			.getElementsByTagName("LeTronconSortant");
        	Noeud depart = this.listeNoeuds.elementAt(i);
        	for(int j=0;j<listeXMLTroncons.getLength();j++)
        	{
        		Element TronconXML = (Element)listeXMLTroncons.item(j);
        		int destination = Integer.parseInt(TronconXML.getAttribute("idNoeudDestination"));
        		Noeud arrivee = this.listeNoeuds.elementAt(destination);
        		Troncon tronconActuel = new Troncon();
            	tronconActuel.initTrancon(TronconXML,depart,arrivee);
            	this.listeTroncons.add(tronconActuel);
            	
            	// Set les infos des noeuds
            	this.listeNoeuds.elementAt(i).setTronconSortant(tronconActuel);
            	this.listeNoeuds.elementAt(destination).setTronconEntrant(tronconActuel);
        	}
    	}
    }
    
	/**
	 * @return the listeNoeuds
	 */
	public Vector<Noeud> getListeNoeuds() {
		return listeNoeuds;
	}
	
	/**
	 * @return Noeud
	 */
	public Noeud getNoeudbyID(int id)
	{
		return this.listeNoeuds.elementAt(id);
	}
	
	/**
	 * @return the listeTroncons
	 */
	public Vector<Troncon> getListeTroncons() {
		return listeTroncons;
	}


	private Vector<Noeud> listeNoeuds;
    
    private Vector<Troncon> listeTroncons;

}