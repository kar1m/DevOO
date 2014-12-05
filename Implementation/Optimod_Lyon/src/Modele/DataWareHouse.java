package Modele;

import java.sql.Time;
import java.util.Vector;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * classe de gestion/stockage des elements du modele
 * @author Yassine Moreno
 *
 */
public class DataWareHouse {
	/**
	 * Plan de l'application (noeuds + troncons)
	 */
	private Plan planApp;
	/**
	 * Livraisons chargees (plages horaires + livraisons)
	 */
    private Vector<PlageHoraire> livraisonData;
    /**
     * Entrepot associe a la livraison chargee
     */
    private Noeud entrepot;
    /**
     * Tournee calculee
     */
	private Tournee tournee; 

	public DataWareHouse() {
		this.planApp = new Plan();
		this.livraisonData = new Vector<PlageHoraire>();
		this.tournee = null; 
	}
	/**
	 * methode d'initialisation de l'entrepot (attribut)
	 * @param racine Element XML contenant les informations pour l'initialisation
	 * @throws Exception liee aux malformations semantiques et syntaxiques des fichiers
	 */
	public void initEntrepot(Element racine) throws Exception
	{
			try {
				int idadresse = Integer.parseInt(racine.getAttribute("adresse"));
				this.entrepot = new Noeud();
				entrepot = this.planApp.getListeNoeuds().elementAt(idadresse);
				
				for (PlageHoraire pl : this.livraisonData)
				{
					for (Livraison liv : pl.getLivraisons())
					{
						if (liv.getDestinataire().getNoeudAdresse() == entrepot)
						{
							throw new Exception("Entrepot Affecté à une livraison");
						}
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("Entrepot non declare");
			}
	}
	/**
	 * methode d'initialisation des livraisons/plages horaires (attribut)
	 * @param racine Element XML contenant les informations pour l'initialisation
	 * @throws Exception liee aux malformations semantiques et syntaxiques des fichiers
	 */
	public void initLivraison(Element racine) throws Exception {
			// appel des initialiseurs
	   livraisonData = new Vector<PlageHoraire>();
	   NodeList plagesXML = racine.getElementsByTagName("Plage");
	   for (int i = 0;i < plagesXML.getLength();i++)
	   {
			   //cr�ation de la clef (PH)
			   Element plageXMLinstance = (Element)plagesXML.item(i);
			   PlageHoraire nouvellePlage = new PlageHoraire(i);
			   nouvellePlage.initPlage(plageXMLinstance, planApp.getListeNoeuds());
			   livraisonData.add(nouvellePlage);
	   }
	   chevauchementPH(livraisonData);
	}
    /**
     * methode d'initialisation du plan (attribut)
	 * @param racine Element XML contenant les informations pour l'initialisation
	 * @throws Exception liee aux malformations semantiques et syntaxiques des fichiers
     */
	public void initDataPlan(Element racine) throws Exception {
			this.planApp = new Plan();
			this.planApp.initPlan(racine);
	}
	/**
	 * methode de suppression d'une demande de livraison
	 * @param l livraison e supprimer
	 * @return plage horaire mise a jour
	 */
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
	/**
	 * methode d'ajout d'une livraison dans le modele
	 * @param a plage horaire contenant la livraison a ajouter
	 * @param l livraison a ajouter
	 */
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

	public Noeud getEntrepot() {
		return entrepot;
	}
    /**
     * methode de verification de chevauchement de plages horaires
     * @param target cible a verifier
     * @throws Exception liee au chevauchement ou incompatibilite des plages horaires
     */
	private void chevauchementPH(Vector<PlageHoraire> target) throws Exception
	{
		for (PlageHoraire pl : target)
		{
			Time lower = pl.getHeureDebut_H();
			Time upper = pl.getHeureFin_H();
			// Si heure fin est inférieure à heure debut
			if(lower.after(upper))
			{
				target.clear();
				throw new Exception("Heure de Livraison invalide");
			}
			for (PlageHoraire interdit : target)
			{
				Time lowerInterdit = interdit.getHeureDebut_H();
				if (!upper.equals(lowerInterdit))
				{
					if (lowerInterdit.after(lower) && lowerInterdit.before(upper))
					{
						target.clear();
						throw new Exception("Heure de Livraison invalide : Deux plages se chevauchent");
					}
				}
			}
		}
	}

	public Tournee getTournee() {
		return tournee;
	}

	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}
	public void resetEntrepot()
	{
		this.entrepot = null;
	}
	
	public Livraison getLivraisonById(int ID)
	{
		for(PlageHoraire a : livraisonData)
		{
			for(Livraison b : a.getLivraisons())
			{
				if(ID == b.getIdLivraison())
				{
					return b;
				}
			}
		}
		return null;
	}
}


