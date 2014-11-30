package Outils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;

public class Dijkstra {
	private Noeud origine;
	private Noeud destination;
	private Plan plan;
	private MultiValueMap<Double,Noeud> mapNoeudsInitiale;
	
	public Dijkstra(Plan plan)
	{
		this.plan = plan;
		Vector<Noeud> listeNoeuds = plan.getListeNoeuds();
		this.mapNoeudsInitiale = new MultiValueMap<Double, Noeud>();
		
		for(int i=0; i<listeNoeuds.size(); i++)
		{
			mapNoeudsInitiale.put(Double.MAX_VALUE, listeNoeuds.get(i));
		}
		
	}
	
	public Vector<Noeud> ChercherVoisins (Noeud noeudCourant)
	{
		Vector<Noeud> listeVoisins  = new Vector<Noeud>();
		Vector<Troncon> listeSortants = noeudCourant.getTronconSortant();
		for(int i=0;i<listeSortants.size();i++)
		{
			listeVoisins.add(listeSortants.get(i).getArrivee());
		}
		
		return listeVoisins;
	}
	
	public Vector<Troncon> Calcul(Noeud origine, Noeud destination)
	{
		Vector<Troncon> chemin = new Vector<Troncon>();
		double [] listeDistanceMinEntreSourceEtN = new double [plan.getListeNoeuds().size()];
		Noeud [] noeudPrecedent = new Noeud[plan.getListeNoeuds().size()];
		
		//Initialisation tableau des distances minimales à  l'infini
		for (int i=0; i<listeDistanceMinEntreSourceEtN.length; i++)
		{
			listeDistanceMinEntreSourceEtN[i] = Double.MAX_VALUE;
		}
		
		boolean []  distanceMinTrouvee = new boolean [plan.getListeNoeuds().size()];
		listeDistanceMinEntreSourceEtN[origine.getIdNoeud()] = 0;
		for(int i=0; i<listeDistanceMinEntreSourceEtN.length; i++)
		{
			Noeud noeudCourant = plan.getNoeudbyID(i);
			int idNoeudMin = ExtraireMin(listeDistanceMinEntreSourceEtN, distanceMinTrouvee);
			Noeud noeudMin = plan.getNoeudbyID(idNoeudMin);
			distanceMinTrouvee[idNoeudMin]=true;
			
			Vector<Noeud> listeVoisins = ChercherVoisins( noeudMin );
			for (int j=0; j<listeVoisins.size(); j++)
			{
				Noeud voisinCourant = listeVoisins.get(j);
				Troncon tronconCourant = plan.getTroncon(noeudMin,voisinCourant);
				double poidsTotal = listeDistanceMinEntreSourceEtN[idNoeudMin] + tronconCourant.getTemps();
				if (poidsTotal < listeDistanceMinEntreSourceEtN[voisinCourant.getIdNoeud()]) {
					listeDistanceMinEntreSourceEtN[idNoeudMin] = poidsTotal;
					noeudPrecedent[voisinCourant.getIdNoeud()] = noeudMin;
				}
			}
		}
		
		chemin = reconstituerChemin(noeudPrecedent, origine, destination);
		return chemin;
	}
	
	private Vector<Troncon> reconstituerChemin (Noeud[] noeudPrecedent, Noeud origine, Noeud destination)
	{
		Vector <Troncon> chemin = new Vector<Troncon>();
		//Reconstitution du chemin
		Noeud noeudCourant = destination;
		for(int i=0; !Noeud.egaux(noeudCourant, origine) ; i++) {
		/* On parcourt de la destination vers l'origine le tableau du chemin le plus court
		 * C'est ce tableau inversé qui sera renvoyé
		 * Par contre le troncon cherche est bien celui qui va du noeud Precedent vers le noeud Courant
		 * car le noeud précédent vient avant dans le chemin venant de l'origine, puis on passe au noeud courant
		 */
			Noeud noeudPreced = noeudPrecedent[noeudCourant.getIdNoeud()];
			chemin.add(plan.getTroncon(noeudPreced,noeudCourant));
			noeudCourant = noeudPreced;
		}
		Collections.reverse(chemin);
		return chemin;
	}
	
	public int ExtraireMin(double[] listeDistanceMinEntreSourceEtN, boolean [] distanceMinTrouvee)
	{
		double distanceMin = Double.MAX_VALUE;
		int idNoeudMin =0;
		for (int i=0; i< listeDistanceMinEntreSourceEtN.length;i++)
		{
			if(!distanceMinTrouvee[i] && listeDistanceMinEntreSourceEtN[i]<distanceMin) {
				idNoeudMin=i;
				distanceMin = listeDistanceMinEntreSourceEtN[i];
			}
		}
		return idNoeudMin;
	}
	
	/*public Vector<Troncon> Calcul(Noeud origine, Noeud destination)
	{
		Noeud [] noeudPrecedent = new Noeud[plan.getListeNoeuds().size()];
		MultiValueMap<Double,Noeud> listeNoeudsNonUtilises = this.mapNoeudsInitiale;
		Set<Noeud> listeNoeudsDontCheminDetermine = new TreeSet<Noeud>();
		
		recupererItNoeudAvecId(origine.getIdNoeud(), listeNoeudsNonUtilises).remove();
		
		
		while(listeNoeudsNonUtilises.size()!=0)
		{
			if(listeNoeudsNonUtilises.size()!=0){
				MapIterator<Double, Object> itMin = listeNoeudsNonUtilises.mapIterator();
				Noeud noeudMin = (Noeud) itMin.getValue();
				double distanceJusquaNoeudMin = itMin.getKey();
				itMin.remove();
			
				listeNoeudsDontCheminDetermine.add(noeudMin);
				
				Vector<Noeud> listeVoisins = ChercherVoisins(noeudMin);
				for(int i=0; i<listeVoisins.size(); i++)
				{
					Noeud voisinCourant = listeVoisins.get(i);
					noeudPrecedent[voisinCourant.getIdNoeud()] = noeudMin;
					MapIterator<Double, Object> itVoisin = recupererItNoeudAvecId(voisinCourant.getIdNoeud(), listeNoeudsNonUtilises);
					double currentPoids = distanceJusquaNoeudMin + plan.getTroncon(noeudMin,voisinCourant).getTemps();
					itVoisin.remove();
					listeNoeudsNonUtilises.put(currentPoids, voisinCourant);
				}
			}
		}
		
		Vector<Troncon> chemin = reconstituerChemin(noeudPrecedent);
		
		return chemin;
	}
	
	private Noeud ExtraireMin(MultiValueMap<Double,Noeud> listeNoeuds)
	{
		Noeud noeudMin = null;
		
		return noeudMin;
	}
	
	private MapIterator<Double, Object> recupererItNoeudAvecId(int id, MultiValueMap<Double,Noeud> listeNoeuds)
	{
		MapIterator<Double, Object> itListeNoeuds = listeNoeuds.mapIterator();
		boolean trouve = false;
		MapIterator<Double, Object> resultat = null;
		Noeud noeudCourant = null;
		while( itListeNoeuds.hasNext())
		{
			noeudCourant = (Noeud) itListeNoeuds.getValue();
			if(noeudCourant.getIdNoeud() == id)
			{
				trouve = true;
				break;
			}
		}
		
		if(trouve)
		{
			resultat = itListeNoeuds;
		}
		return resultat;
	}*/
}




