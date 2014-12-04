package Modele;

import java.util.*;

import org.w3c.dom.*;

/**
 * Plan contenant noeuds et troncons
 */
public class Plan {

	private Vector<Noeud> listeNoeuds;
    private Vector<Troncon> listeTroncons;
	
    public Plan() {
    	this.listeNoeuds = new Vector<Noeud>();
    	this.listeTroncons = new Vector<Troncon>();
    }
    /**
     * methode d'initialisation du plan (attribut)
	 * @param xmlChunk Element XML contenant les informations pour l'initialisation
	 * @throws Exception liée aux malformations sémantiques et syntaxiques des fichiers
     */
    public void initPlan(Element xmlChunk) throws Exception
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
    
    
    public int getMaxX(Vector<Noeud> liste)
	{
		int max = 0; 
		for ( Noeud a : liste)
		{
			if(a.getX()>max)
			{
				max = a.getX();
			}
		}
		return max;
	}
	public int getMaxY(Vector<Noeud> liste)
	{
		int max = 0; 
		for ( Noeud a : liste)
		{
			if(a.getY()>max)
			{
				max = a.getY();
			}
		}
		return max;
	}
	
	
	public Troncon getTroncon(Integer idOrigine, Integer idDestination) 
	{
		Troncon troncon = new Troncon();
		
		for (int i = 0; i < listeTroncons.size(); i++)
		{
			if (listeTroncons.get(i).getDepart().getIdNoeud() == idOrigine && listeTroncons.get(i).getArrivee().getIdNoeud() == idDestination)
			{
				troncon = listeTroncons.get(i);
				break;
			}
		}
		
		return troncon;
	}
}