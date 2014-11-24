package Modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DataWareHouse {
	
	private Plan planApp;
    private Map<PlageHoraire,Vector<Livraison>> livraisonData;
	

	public DataWareHouse() {
		this.planApp = new Plan();
		this.livraisonData = new HashMap<PlageHoraire,Vector<Livraison>>();
	}
	
	public boolean initMapLivraison(Element racine) {
		 // appel des initialiseurs
 	   NodeList plagesXML = racine.getElementsByTagName("Plage");
 	   for (int i = 0;i < plagesXML.getLength();i++)
 	   {
 		   //cr�ation de la clef (PH)
 		   Element plageXMLinstance = (Element)plagesXML.item(i);
 		   PlageHoraire nouvellePlage = new PlageHoraire();
 		   nouvellePlage.initPlage(plageXMLinstance);
 		   
 		   //cr�ation des valeurs (Vector)
 		   Vector<Livraison> livraisonPH = new Vector<Livraison>();
 		   NodeList livraisonXML = plageXMLinstance.getElementsByTagName("Livraison");
 		   for (int j = 0 ; j<livraisonXML.getLength();j++)
 		   {
 			   Element livraisonXMLinstance = (Element)livraisonXML.item(j);
 			   Livraison nouvelleLivraison = new Livraison();
 			   nouvelleLivraison.initLivraison(livraisonXMLinstance);
 			   livraisonPH.add(nouvelleLivraison);
 		   }
 		   
 		   // Inserer le couple plage,Vector<Livraisons> dans la map de l'application
 		   livraisonData.put(nouvellePlage, livraisonPH);
 	   }
		return true;
	}
    
	public boolean initDataPlan(Element racine) {
		
		this.planApp = new Plan();
		this.planApp.initPlan(racine);
		return true;
		
	}

	/**
	 * @return the planApp
	 */
	public Plan getPlanApp() {
		return planApp;
	}

	/**
	 * @return the livraisonData
	 */
	public Map<PlageHoraire, Vector<Livraison>> getLivraisonData() {
		return livraisonData;
	}
    
}


