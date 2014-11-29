package Controleur;

import Modele.*;
import Outils.*;
import Controleur.*;

public class CalculTournee {

	public static void main(String[] args)
	{
		calculerTournee();
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
	            //System.out.print(String.format("%20s", g.getCost()[i][j]));
	        }
	        //System.out.println("");
	    }
		
		int[] succ = g.getSucc(0);
		
		for(int i = 0; i < succ.length; i++) {
			System.out.println(succ[i]);
		}
	}

}