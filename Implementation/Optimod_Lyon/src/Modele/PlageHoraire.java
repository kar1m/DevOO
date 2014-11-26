package Modele;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class PlageHoraire {

    public PlageHoraire() {
    	this.livraisons = new Vector<Livraison>();
    }

    private String heureDebut;

    private String heureFin;
    
    private Vector<Livraison> livraisons;


    /**
     * @param Element XMLnode
     */
    public void initPlage(Element XMLnode, Vector<Noeud> l ) {
        this.heureDebut = XMLnode.getAttribute("heureDebut");
        this.heureFin = XMLnode.getAttribute("heureFin");
        
        	//Initialisation du vecteur de livraisons
		   Vector<Livraison> livraisonPH = new Vector<Livraison>();
		   NodeList livraisonXML = XMLnode.getElementsByTagName("Livraison");
		   for (int j = 0 ; j<livraisonXML.getLength();j++)
		   {
			   Element livraisonXMLinstance = (Element)livraisonXML.item(j);
			   Livraison nouvelleLivraison = new Livraison();
			   nouvelleLivraison.initLivraison(livraisonXMLinstance,l);
			   livraisonPH.add(nouvelleLivraison);
		   }
		   this.livraisons =  livraisonPH;
    }


	/**
	 * @return the heureDebut
	 */
	public String getHeureDebut() {
		return heureDebut;
	}
	/**
	 * @return the heureFin
	 */
	public String getHeureFin() {
		return heureFin;
	}
	public Vector<Livraison> getLivraisons() {
		return livraisons;
	}
	
    

}