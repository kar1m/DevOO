package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Modele.DataWareHouse;
import Modele.Plan;
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
		System.out.println(p);	
		
	}

}
