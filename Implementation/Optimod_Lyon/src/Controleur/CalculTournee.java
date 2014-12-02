package Controleur;

import Modele.*;
import Outils.*;
import Controleur.*;

public class CalculTournee {

	public static void main(String[] args)
	{
		//calculerTournee();
		testChoco();
	}


	public static void calculerTournee()
	{
		int nbVertices = 20;
		int degree = 12;
		int minCost = 40;
		int maxCost = 120;
		Graph g = new RegularGraph(nbVertices, degree, minCost, maxCost);
		
		for(int i=0; i<nbVertices; i++){
	        for(int j=0; j<nbVertices; j++){
	            System.out.print(String.format("%20s", g.getCost()[i][j]));
	        }
	        System.out.println("");
	    }
		
		int[] succ = g.getSucc(0);
		
		for(int i = 0; i < succ.length; i++) {
			//System.out.println(succ[i]);
		}
	}
	
	public static Graph getGraph()
	{
		int nbVertices = 20;
		int degree = 12;
		int minCost = 40;
		int maxCost = 120;
		Graph g = new RegularGraph(nbVertices, degree, minCost, maxCost);
		
		return g;
	}
	
	public static void testChoco()
	{
		TSP tsp = new TSP(getGraph());
		SolutionState s = tsp.solve(100000, 100000);
		
		if (s == SolutionState.OPTIMAL_SOLUTION_FOUND || s == SolutionState.SOLUTION_FOUND) {
			System.out.println("Solution trouvée");
			int[] next = tsp.getNext();
			for (int i = 0; i < next.length; i++) {
				System.out.println(next[i]);
			}
        }
		else {
			System.out.println("Pas de solution trouvée");
		}
	}

}