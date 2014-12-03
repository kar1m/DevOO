package Modele;

import java.util.Scanner;
import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class PlageHoraire {

    public PlageHoraire(int ID) {
    	this.livraisons = new Vector<Livraison>();
    	this.idPlageHoraire = ID;
    }

    private int idPlageHoraire; 
    private String heureDebut;

    private String heureFin;
    
    private Vector<Livraison> livraisons;


    /**
     * @param Element XMLnode
     * @throws Exception 
     */
    public void initPlage(Element XMLnode, Vector<Noeud> l ) throws Exception {
        this.heureDebut = XMLnode.getAttribute("heureDebut");
        checkerHeure(this.heureDebut);
        this.heureFin = XMLnode.getAttribute("heureFin");
        checkerHeure(this.heureDebut);
        
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

    private void checkerHeure(String target) throws Exception
    {
        String pattern = "[\\s]*:[\\s]*";
        
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(target).useDelimiter(pattern);
        int cpt = 0;
        int heure = -1,min = -1,sec = -1;
        try {
			while (sc.hasNext())
			    {
			    	if	(cpt == 0)
			    	{
			    		heure = Integer.parseInt(sc.next());
			    	}
			    	if	(cpt == 1)
			    	{
			    		min = Integer.parseInt(sc.next());
			    	}
			    	if	(cpt == 2)
			    	{
			    		sec = Integer.parseInt(sc.next());
			    	}
			    	cpt++;
			    }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new Exception("Format Heure invalide");
		}
        if (heure<0 || heure>24 || min<0 || min>60 || sec<0 || sec>60)
        {
        	throw new Exception("Format Heure non respecté");
        }
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
	
	public int getId()
	{
		return idPlageHoraire;
	}
	
    


}