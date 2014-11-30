package Outils;

import java.util.Vector;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

public class Dijkstra {
	private Noeud origine;
	private Noeud destination;
	private Plan plan;
	
	public Dijkstra(Plan plan, Noeud origine, Noeud destination)
	{
		this.plan = plan;
		this.origine = origine;
		this.destination = destination;
	}
	
	public Noeud[] ChercherVoisins (Noeud noeudCourant)
	{
		Noeud [] listeVoisins  = new Noeud[plan.getListeNoeuds().size()];
		Vector<Troncon> listeSortants = noeudCourant.getTronconSortant();
		for(int i=0;i<listeSortants.size();i++)
		{
			listeVoisins[i] = listeSortants.get(i).getArrivee();
		}
		
		return listeVoisins;
	}
	
	public Noeud[] Calcul()
	{
		Noeud [] chemin = new Noeud[plan.getListeTroncons().size()];
		double [] listeDistanceMinEntreSourceEtN = new int [plan.getListeNoeuds().size()];
		
		//Initialisation tableau des distances minimales à  l'infini
		for (int i=0; i<listeDistanceMinEntreSourceEtN.length; i++)
		{
			listeDistanceMinEntreSourceEtN[i] = Integer.MAX_VALUE;
		}
		
		boolean []  distanceMinTrouvee = new boolean [plan.getListeNoeuds().size()];
		for(int i=0; i<listeDistanceMinEntreSourceEtN.length; i++)
		{
			Noeud noeudCourant = plan.getNoeudbyID(i);
			int idNoeudMin = ExtraireMin(listeDistanceMinEntreSourceEtN, distanceMinTrouvee);
			Noeud noeudMin = plan.getNoeudbyID(idNoeudMin);
			distanceMinTrouvee[i]=true;
			
			Noeud [] listeVoisins = ChercherVoisins( noeudMin );
			for (int j=0; j<listeVoisins.length; j++)
			{
				Noeud voisinCourant = listeVoisins[j];
				double poidsTotal = listeDistanceMinEntreSourceEtN[idNoeudMin] + plan.getTroncon(noeudCourant,noeudMin).getTemps();
				if (poidsTotal < listeDistanceMinEntreSourceEtN[idNoeudMin]) {
					listeDistanceMinEntreSourceEtN[idNoeudMin] = poidsTotal;
				}
			}
		}
		return chemin;
	}
	
	public int ExtraireMin(double[] listeDistanceMinEntreSourceEtN, boolean [] distanceMinTrouvee)
	{
		double distanceMin = Integer.MAX_VALUE;
		int idNoeudMin =0;
		for (int i=0; i< listeDistanceMinEntreSourceEtN.length,i++)
		{
			if(!distanceMinTrouvee[i] && listeDistanceMinEntreSourceEtN[i]<y) {
				idNoeudMin=i;
				distanceMin = listeDistanceMinEntreSourceEtN[i];
			}
		}
		return idNoeudMin;
	}
}


