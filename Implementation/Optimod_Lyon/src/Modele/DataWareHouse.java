package Modele;

import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DataWareHouse {
	
	private Plan planApp;
    private Vector<PlageHoraire> livraisonData;
	

	public DataWareHouse() {
		this.planApp = new Plan();
		this.livraisonData = new Vector<PlageHoraire>();
	}
	
	public boolean initLivraison(Element racine) {
		 try {
			// appel des initialiseurs
	   NodeList plagesXML = racine.getElementsByTagName("Plage");
	   for (int i = 0;i < plagesXML.getLength();i++)
	   {
			   //cr�ation de la clef (PH)
			   Element plageXMLinstance = (Element)plagesXML.item(i);
			   PlageHoraire nouvellePlage = new PlageHoraire();
			   nouvellePlage.initPlage(plageXMLinstance);

			   livraisonData.add(nouvellePlage);
	   }
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    
	public boolean initDataPlan(Element racine) {
		
		try {
			this.planApp = new Plan();
			this.planApp.initPlan(racine);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public PlageHoraire supprimerLivraison(Livraison l)
	{
		for(PlageHoraire a : livraisonData)
		{
			for(Livraison b : a.getLivraisons())
			{
				if(l==b)
				{
					a.getLivraisons().remove(b);
					return a;
				}
			}
		}
		return null;
	}
	
	public void ajouterLivraison(PlageHoraire a, Livraison l)
	{
		int index = livraisonData.indexOf(a);
		if(index == -1)
		{
			a.getLivraisons().add(l);
			livraisonData.add(a);
		}else{
			livraisonData.get(index).getLivraisons().add(l);
		}
	}
	/**
	 * @return the planApp
	 */
	public Plan getPlanApp() {
		return planApp;
	}

	public Vector<PlageHoraire> getLivraisonData() {
		return livraisonData;
	}
    
}


