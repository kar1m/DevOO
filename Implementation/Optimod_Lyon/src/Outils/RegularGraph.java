package Outils;
import java.util.ArrayList;
import java.util.Vector;

import Modele.*;
import Controleur.*;

/**
 * @author Christine Solnon
 *
 */

public class RegularGraph implements Graph {
	private int nbVertices;
	private int maxArcCost;
	private int minArcCost;
	private int[][] cost; 
	private ArrayList<ArrayList<Integer>> succ;
	private Vector<PlageHoraire> plagesHoraires;
	
	
	/**
	 * Creates a graph such that each vertex is connected to the next <code>d</code> vertices (modulo <code>n</code>) and
	 * such that the cost of each arc is a randomly chosen integer ranging between <code>min</code> and <code>max</code>
	 * @param n a number of vertices such that <code>n > 0</code>
	 * @param d a degree such that <code>0 < d < n</code>
	 * @param min a minimal arc cost such that <code>0 < min</code>
	 * @param max a maximal arc cost such that <code>min < max</code>
	 */
	public RegularGraph(int n, int d, int min, int max){
		nbVertices = n;
		minArcCost = min;
		maxArcCost = max;
		cost = new int[nbVertices][nbVertices];
		succ = new ArrayList<ArrayList<Integer>>(); 
		for (int i=0; i<nbVertices; i++){
			for (int j=0; j<nbVertices; j++)
				cost[i][j] = maxArcCost+1;
			ArrayList<Integer> l = new ArrayList<Integer>();
			for (int j = i+1; j <= i+d; j++){
				int k = j % nbVertices;
				cost[i][k] = minArcCost + (int)(Math.random() * (maxArcCost-minArcCost+1));
				l.add(k);
			}
			succ.add(i,l);
		}
	}
	
	public RegularGraph(Noeud entrepot, Vector<PlageHoraire> plagesHoraires, Plan plan)
	{
		// Verification des parametres
		if (plagesHoraires.isEmpty()) {
			return;
		}
		
		succ = new ArrayList<ArrayList<Integer>>();
		
		Vector<Chemin> chemins = calculerTousLesChemins(entrepot, plagesHoraires, plan);
		
		// Successeurs de l'entrepot
		ArrayList<Integer> succEntrepot = new ArrayList<Integer>();
		
		for (int i = 0; i < plagesHoraires.get(0).getLivraisons().size(); i++)
		{
			succEntrepot.add(plagesHoraires.get(0).getLivraisons().get(i).getDestinataire().getNoeudAdresse().getIdNoeud());
		}
		
		succ.add(succEntrepot);
		
		// Successeurs entre plages horaires
		int sum = 1;
		
		for (int i = 0; i < plagesHoraires.size(); i++)
		{
			for (int j = 0; j < plagesHoraires.get(i).getLivraisons().size(); j++)
			{
				sum+=1;
				
				ArrayList<Integer> succLivraison = new ArrayList<Integer>();
				int nbLivraisons = plagesHoraires.get(i).getLivraisons().size(); // nb de livraisons de la plage horaire actuelle
				
				// Liens entre livraisons d'une meme plage horaire
				for (int k = 0; k < nbLivraisons; k++)
				{	
					if(j == k) {
						continue;
					}
					
					succLivraison.add(plagesHoraires.get(i).getLivraisons().get(k).getDestinataire().getNoeudAdresse().getIdNoeud());
				}
				
				// Liens entre livraisons d'une plage horaire i et i+1
				if (i < plagesHoraires.size()-1) {
					for (int k = 0; k < plagesHoraires.get(i+1).getLivraisons().size(); k++)
					{	
						succLivraison.add(plagesHoraires.get(i+1).getLivraisons().get(k).getDestinataire().getNoeudAdresse().getIdNoeud());
					}
				}
				
				// Successeurs entre livraisons de la derniere plage horaire et l'entrepot
				if (i == plagesHoraires.size()-1) {
					succLivraison.add(entrepot.getIdNoeud());
				}
				
				succ.add(succLivraison);
			}
			
		}
		
		for (int i = 0; i < succ.size(); i++) {
			for (int j = 0; j < succ.get(i).size()-1; j++) {
				System.out.println("BETWEEN : " + succ.get(i).get(j) + " AND "+ succ.get(i).get(j+1));
				ArrayList al = Dijkstra.Get_Short_Path(plan,succ.get(i).get(j),succ.get(i).get(j+1));
				System.out.println(al);
				System.out.println("");
			}
			break;
			//System.out.println("");
		}
		
		
		// Création de la matrice de couts
		
		cost = new int[nbVertices][nbVertices];
	}
	
	public Vector<Chemin> calculerTousLesChemins(Noeud entrepot, Vector<PlageHoraire> plagesHoraires, Plan plan)
	{
		if (plagesHoraires.isEmpty()) {
			return null;
		}
		
		// Entre l'entrepot et la 1ere plage horaire
		for (int i = 0; i < plagesHoraires.size(); i++) {
			
		}
		
		return null;
	}

	public int getMaxArcCost() {
		return maxArcCost;
	}

	public int getMinArcCost() {
		return minArcCost;
	}

	public int getNbVertices() {
		return nbVertices;
	}

	public int[][] getCost(){
		return cost;
	}
	
	public int[][] getSucc() {
		int[][] succTab = new int[succ.size()][succ.size()];
		
		for (int i = 0; i < succ.size(); i++) {
			succTab[i] = getSucc(i);
		}
		
		return succTab;
	}

	public int[] getSucc(int i) throws ArrayIndexOutOfBoundsException{
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		int[] tab = new int[succ.get(i).size()];
		for(int j=0;j<tab.length;j++){
			tab[j] = succ.get(i).get(j);
		}
		return tab;
	}


	public int getNbSucc(int i) throws ArrayIndexOutOfBoundsException {
		if ((i<0) || (i>=nbVertices))
			throw new ArrayIndexOutOfBoundsException();
		return succ.get(i).size();
	}


}
