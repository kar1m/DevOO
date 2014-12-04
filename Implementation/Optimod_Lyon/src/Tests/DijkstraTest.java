package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import Modele.*;
import Outils.Dijkstra;
import Outils.Proprietes;
import Outils.XMLhandler;
import Vue.Fenetre;
import Controleur.Application;

public class DijkstraTest {

	@Test
	public void test() {
		Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		ArrayList<Object> args = new ArrayList<Object>();
		String path = "C:\\Users\\ABDELALIM\\Desktop\\DevOO\\Ressources\\plan10x10.xml";
		args.add(path);
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
		Plan p = commandCenter.getModele().getPlanApp();
		
		System.out.println(p.getListeNoeuds().size());
		double [][] MatAdjacence = new double [p.getListeNoeuds().size()][p.getListeNoeuds().size()] ;
		//MatAdjacence= Dijkstra.calculeMatriceAdjacence(p);
	
		
		ArrayList al = Dijkstra.getShortPath(p,45,54);
		System.out.println(al);
	//System.out.println(chemin);
		/*int[] k  =	Dijkstra.calculer_cours_chemins(p,0);
		for (int i = 0; i <k.length ; i++) 
        {         System.out.println(k[i]);  }
		*/
/*int[] k  = Dijkstra.ChercheVoisin(1,p) ;
		
		for (int i = 0; i <k.length ; i++) 
        {         System.out.println(k[i]);  } */
		
		//System.out.println("iciiiiiiiiiiiii");
		//Dijkstra.Get_Short_Path(p,0,1);
		//LinkedList chemin = new LinkedList();
				//chemin = Dijkstra.Get_Short_Path(p,0,1);
		
		// System.out.println(chemin);
		
	/*	for (int i = 0; i <p.getListeNoeuds().size() ; i++) 
        {
		for (int j = 0; j <p.getListeNoeuds().size() ; j++) 
			MatAdjacence[i][j]=0;
        }

		for (int i = 0; i <p.getListeNoeuds().size() ; i++) 
            {
			for (int j = 0; j <p.getListeNoeuds().size() ; j++) 
			System.out.println(MatAdjacence[i][j]+" hada i "+ i+ "et hada j " +j+"----------->>>");
            }
		*/
	}

}
