package Vue;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTable;

import Controleur.Application;
import Modele.DataWareHouse;
import Modele.Plan;
import Outils.Dijkstra;
import Outils.Proprietes;
import Tests.ControlTest;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Proprietes.cols = Proprietes.getDifferentColors(10);
		
		
Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		ArrayList<Object> argus = new ArrayList<Object>();
		String path = "C:\\Users\\ABDELALIM\\Desktop\\DevOO\\Ressources\\plan10x10.xml";
		argus.add(path);
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, argus);
		Plan p = commandCenter.getModele().getPlanApp();
		
		System.out.println(p.getListeNoeuds().size());
		double [][] MatAdjacence = new double [p.getListeNoeuds().size()][p.getListeNoeuds().size()] ;
		//MatAdjacence= Dijkstra.calculeMatriceAdjacence(p);
	
		int[] k  = Dijkstra.ChercheVoisin(1,p) ;
		
		for (int i = 0; i <k.length ; i++) 
        {         System.out.println(k[i]);  } 
		
		
		System.out.println("iciiiiiiiiiiiii");
		Dijkstra.Get_Short_Path(p,0,1);
	}
}
