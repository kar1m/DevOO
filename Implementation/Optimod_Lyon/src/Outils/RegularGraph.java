package Outils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import solver.ResolutionPolicy;
import solver.Solver;
import solver.constraints.IntConstraintFactory;
import solver.search.strategy.IntStrategyFactory;
import solver.variables.IntVar;
import solver.variables.VariableFactory;
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
	private ArrayList<ArrayList<Chemin>> chemins;
	private Vector<PlageHoraire> plagesHoraires;
	private int nbLivraisonsTotal;
	
	private Map<Integer, Integer> nodeToChoco;
	private Map<Integer, Integer> chocoToNode;
	
	
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
		
		this.plagesHoraires = plagesHoraires;
		
		convertNodeIds(plagesHoraires, entrepot);
		
		succ = new ArrayList<ArrayList<Integer>>();
		
		Vector<Chemin> chemins = calculerTousLesChemins(entrepot, plagesHoraires, plan);
		
		// Successeurs de l'entrepot
		ArrayList<Integer> succEntrepot = new ArrayList<Integer>();
		
		for (int i = 0; i < plagesHoraires.get(0).getLivraisons().size(); i++)
		{
			succEntrepot.add(nodeToChoco.get(plagesHoraires.get(0).getLivraisons().get(i).getDestinataire().getNoeudAdresse().getIdNoeud()));
		}
		
		succ.add(succEntrepot);
		
		// Calcul de la matrice des successeurs
		this.nbLivraisonsTotal = 0;
		
		for (int i = 0; i < plagesHoraires.size(); i++)
		{
			//System.out.print("PLAGE " + i);
			for (int j = 0; j < plagesHoraires.get(i).getLivraisons().size(); j++)
			{
				//System.out.print(nodeToChoco.get(plagesHoraires.get(i).getLivraisons().get(j)) + " ");
				
				this.nbLivraisonsTotal+=1;
				
				ArrayList<Integer> succLivraison = new ArrayList<Integer>();
				int nbLivraisons = plagesHoraires.get(i).getLivraisons().size(); // nb de livraisons de la plage horaire actuelle
				
				// Liens entre livraisons d'une meme plage horaire
				for (int k = 0; k < nbLivraisons; k++)
				{	
					if(j == k) {
						continue;
					}
					
					succLivraison.add(nodeToChoco.get(plagesHoraires.get(i).getLivraisons().get(k).getDestinataire().getNoeudAdresse().getIdNoeud()));
				}
				
				// Liens entre livraisons d'une plage horaire i et i+1
				if (i < plagesHoraires.size()-1) {
					for (int k = 0; k < plagesHoraires.get(i+1).getLivraisons().size(); k++)
					{	
						succLivraison.add(nodeToChoco.get(plagesHoraires.get(i+1).getLivraisons().get(k).getDestinataire().getNoeudAdresse().getIdNoeud()));
					}
				}
				
				// Successeurs entre livraisons de la derniere plage horaire et l'entrepot
				if (i == plagesHoraires.size()-1) {
					succLivraison.add(nodeToChoco.get(entrepot.getIdNoeud()));
				}
				
				succ.add(succLivraison);
			}
			//System.out.println("");
		}
		
		for (int i = 0; i < succ.size(); i++) {
			System.out.print("["+i+"] : ");
			for (int j = 0; j < succ.get(i).size(); j++) {
				//System.out.println("BETWEEN : " + succ.get(i).get(j) + " AND "+ succ.get(i).get(j+1));
				//ArrayList al = Dijkstra.Get_Short_Path(plan,succ.get(i).get(j),succ.get(i).get(j+1));
				//System.out.println(al);
				//System.out.println("");
				System.out.print(succ.get(i).get(j)+ " ");
			}
			//break;
			System.out.println("");
		}
		
		
		// Création de la matrice de couts et calcul des chemins
		nbVertices = nbLivraisonsTotal+1;
		
		cost = new int[nbLivraisonsTotal+1][nbLivraisonsTotal+1]; // +1 car l'entrepot
		calculerChemins(plan);
	}
	
	private void convertNodeIds(Vector<PlageHoraire> plagesHoraires, Noeud entrepot)
	{
		nodeToChoco = new HashMap<Integer, Integer>();
		chocoToNode = new HashMap<Integer, Integer>();  
		
		nodeToChoco.put(entrepot.getIdNoeud(), 0);
		chocoToNode.put(0, entrepot.getIdNoeud());
		
		int sum = 1;
		for (int i = 0; i < plagesHoraires.size(); i++)
		{
			for (int j = 0; j < plagesHoraires.get(i).getLivraisons().size(); j++)
			{
				nodeToChoco.put(plagesHoraires.get(i).getLivraisons().get(j).getDestinataire().getNoeudAdresse().getIdNoeud(), sum);
				chocoToNode.put(sum, plagesHoraires.get(i).getLivraisons().get(j).getDestinataire().getNoeudAdresse().getIdNoeud());
				sum++;
			}
		}
		
		for(Integer key: nodeToChoco.keySet()) {
			System.out.println(key + " - " + nodeToChoco.get(key));
		}
	}
	
	private void calculerChemins(Plan plan)
	{
		chemins = new ArrayList<ArrayList<Chemin>>();

		minArcCost = -1;
		maxArcCost = 0;
		
		for (int i = 0; i < succ.size(); i++)
		{
			ArrayList<Chemin> al = new ArrayList<Chemin>();
			
			for (int j = 0; j < succ.get(i).size(); j++)
			{				
				Chemin chemin = new Chemin();
				
				// Calcul du chemin avec Dijkstraa
				ArrayList path = Dijkstra.Get_Short_Path(plan, chocoToNode.get(i), chocoToNode.get(succ.get(i).get(j)));
				
				//System.out.println("");
				//System.out.println("PATH FOR ["+ chocoToNode.get(i) +","+ chocoToNode.get(succ.get(i).get(j)) +"] : "+ path);
				//System.out.println("");
				
				for (int k = 0; k < path.size()-1; k++)
				{
					Troncon troncon = plan.getTroncon((Integer)path.get(k), (Integer)path.get(k+1));
					chemin.addTroncon(troncon);
					
				}
				
				al.add(chemin);
				
				// On rempli la matrice des couts
				int cout = chemin.getTemps();
				cost[i][succ.get(i).get(j)] = cout;
				
				if (cout > maxArcCost) {
					maxArcCost = cout;
				}
				if (cout < minArcCost || minArcCost == -1) {
					minArcCost = cout;
				}
			}
			
			chemins.add(al);
		}
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
	
	public boolean calculerChoco()
	{
		TSP tsp = new TSP(this);
		System.out.println("CHOCO BEGIN");
		SolutionState s = tsp.solve(200000, this.getNbVertices()*this.getMaxArcCost()+1);
		
		if (s == SolutionState.OPTIMAL_SOLUTION_FOUND || s == SolutionState.SOLUTION_FOUND) {
			System.out.println("Solution trouvée");
			int[] next = tsp.getNext();
			for (int i = 0; i < next.length; i++) {
				System.out.println(chocoToNode.get(next[i]));
			}
			return true;
        }
		else {
			System.out.println("Pas de solution trouvée");
		} 
		
		return false;
	}
	
	public void calculerChocoNouveau()
	{
        System.out.println("CHOCO");

        int minCost = getMinArcCost(); // Ajout mï¿½ï¿½thode pour trouver  minCost
        int maxCost = getMaxArcCost();// Ajout mï¿½ï¿½thode pour trouver     maxCost
        int bound = maxCost * this.nbLivraisonsTotal; 
        int[][] cost = getCost();                                                             
        int[][] succ = getSucc();
                                                                                
        System.out.println("matrice cost");
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                System.out.print(cost[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("matrice succ");
        for (int i = 0; i < succ.length; i++) {
            for (int j = 0; j < succ[i].length; j++) {
                System.out.print(succ[i][j] + " ");
            }
            System.out.println();
        }
        // Crï¿½ï¿½ation du solveur
        System.out.println("Choco Solver !");
        Solver solver = new Solver();
        // Dï¿½ï¿½claration des variables
        IntVar[] xNext = xNext = new IntVar[this.nbLivraisonsTotal];
        for (int i = 0; i < this.nbLivraisonsTotal; i++)
            xNext[i] = VariableFactory.enumerated("Next " + i, succ[i], solver);
        IntVar[] xCost = VariableFactory.boundedArray("Cost ", this.nbLivraisonsTotal,
                minCost, maxCost, solver);
        IntVar xTotalCost = VariableFactory.bounded("Total cost ", this.nbLivraisonsTotal
                * minCost, bound - 1, solver);
        // Dï¿½ï¿½claration des contraintes
        for (int i = 0; i < this.nbLivraisonsTotal; i++)
            solver.post(IntConstraintFactory.element(xCost[i], cost[i],
                    xNext[i], 0, "none"));
        solver.post(IntConstraintFactory.circuit(xNext, 0));
        solver.post(IntConstraintFactory.sum(xCost, xTotalCost));
        // Rï¿½ï¿½solution
        solver.set(IntStrategyFactory.firstFail_InDomainMin(xNext));
        solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, xTotalCost);
        System.out.println("HADA HADA HADA HADA");
        for (int k = 0; k < xNext.length; k++) {
            System.out.println(xNext[k]);
        }
        //to do
        //List<Livraison> listLivraisonOrdonne = getListLivraisonOrdonne(xNext, ListLivraisons);
        //System.out.println("List Ordonnee : " + listLivraisonOrdonne);
        //return listLivraisonOrdonne;
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


	public HashMap<Integer, Vector<Chemin>> getChemins()
	{
		HashMap<Integer, Vector<Chemin>> map = new HashMap<Integer, Vector<Chemin>>();
		
		for (int i = 0; i < 3; i++)
		{
			Vector<Chemin> v = new Vector<Chemin>(this.chemins.get(i));
			map.put(i, v);
		}
		
		return map;
	}
	
	public void printCostAndSucc()
	{
		System.out.println("------------- SUCC -------------");
		for (int i = 0; i < succ.size(); i++)
		{
			for (int j = 0; j < succ.get(i).size(); j++)
			{
				System.out.print(succ.get(i).get(j) + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		System.out.println("------------- COST -------------");
		for (int i = 0; i < cost.length; i++)
		{
			for (int j = 0; j < cost[i].length; j++)
			{
				System.out.print(cost[i][j] + " ");
			}
			System.out.println("");
		}
	}

}
