package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;

import Controleur.*;
import Modele.*;
import Outils.Proprietes;
import Vue.Fenetre;

public class ControlTest {
	/**
	 * Chemin ou se trouvent les fichiers a tester
	 */
	private String path = "/Users/Mehdi/Desktop/4IF/DevOO";

	@Test 
	/**
	 * On teste le chargement du plan et de la livraison : dans le cas ou le chargement s'est bien déroulé, il faut que la taille des données de livraisons soit supérieure à 0.
	 * On utilise un fichier XML valide.
	 */
	
	public void testCharger()
	{
		Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(path + "/Ressources/plan10x10.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);

		args.clear();
		args.add(path + "/Ressources/livraison10x10-1.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_LIVRAISON, args);
		
		
		assertTrue(commandCenter.getModele().getLivraisonData().size() > 0 );
		
	}
	
	@Test
	/**
	 * Lorsqu'on charge un plan, on vérifie que les noeuds se sont bien chargés dans l'entrepôt.
	 * On utilise un fichier XML valide.
	 */
	
	public void testDataWareHouseListeNoeud()
	{
		Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(path + "/Ressources/plan10x10.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
		
		assertTrue(commandCenter.getModele().getPlanApp().getListeNoeuds().size() > 0);
	}
	
	@Test
	/**
	 * Lorsqu'on charge un plan, on vérifie que les troncons se sont bien chargés dans l'entrepôt
	 */
	public void testDataWareHouseListeTroncons()
	{
		Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(path + "/Ressources/plan10x10.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
		
		assertTrue(commandCenter.getModele().getPlanApp().getListeTroncons().size() > 0);
	}
	
	@Test
	/**
	 * On teste le chargement de la demande de livraison : dans le cas ou le chargement s'est bien déroulé, il faut que la taille des données de livraisons soit supérieure à 0.
	 * On utilise un fichier XML valide.
	 */
	public void testChargerDemandeLivraison() {
		try {
			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			
			
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(path + "/Ressources/plan10x10.xml");
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);

			args.clear();
			args.add(path + "/Ressources/livraison10x10-1.xml");

			commandCenter.gererCommande(Proprietes.CHARGER_LIVRAISON, args);
			
			
			assertTrue(commandCenter.getModele().getLivraisonData().size() > 0 );
		} 
		catch (Exception e) {
			// TODO: handle exception
			fail("Error Occured");
		}
		
	}
	
	@Test
	/**
	 * Un noeud destination a pour ID celui d'un noeud inexistant. L'application nous signale que l'ID renseigné ne correspond pas aux IDs existants.
	 */
	public void testNotEnoughInPlan() {
		try {
			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			
			
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(path + "/Ressources/plan10x10-copie.xml");
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);

			args.clear();
			args.add(path + "/Ressources/livraison10x10-1.xml");

			commandCenter.gererCommande(Proprietes.CHARGER_LIVRAISON, args);
		} 
		catch (Exception e) {
			// TODO: handle exception
			assertTrue(false);
			return;
		}
		assertTrue(true);
		
	}

	
	@Test
	/**
	 * On teste le chargement de la demande de livraison : dans le cas ou le chargement s'est bien déroulé, il faut que la taille des données de livraisons soit supérieure à 0.
	 * Ici, on s'assure que la taille de la liste des noeuds n'est pas nulle.
	 * On utilise un fichier XML valide.
	 */
	public void testChargerPlan() {
		try {

			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			
			
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(path + "/Ressources/plan10x10.xml");
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
			
			assertFalse(commandCenter.getModele().getPlanApp().getListeNoeuds().size() == 0);
		}
		catch (Exception e)
		{
			fail("Error Occured");
		}
		
	}
	
	@Test
	/**
	 * On vérifie les données d'un noeud donné, à partir d'un fichier XML bien précis. 
	 * Pour le noeud dont l'ID est 3, on vérifie que x=65, y=310 et qu'il possède 2 troncons sortants.
	 * Il s'agit d'un test spécifique par rapport à un fichier XML, permettant de vérifier la cohérence des données.
	 */
	public void testChargerNoeud() {
		try {

			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			
			
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(path + "/Ressources/plan10x10.xml");
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
			Vector<Noeud>tester = commandCenter.getModele().getPlanApp().getListeNoeuds();
			assertTrue(tester.elementAt(3).getIdNoeud() == 3);
			assertTrue(tester.elementAt(3).getX() == 65);
			assertTrue(tester.elementAt(3).getY() == 310);
			assertTrue(tester.elementAt(3).getTronconSortant().size() == 2);
		}
		catch (Exception e)
		{
			fail("Error Occured");
		}
		
	}
	
	@Test
	/**
	 * 
	 */
	public void testTsortantTentrant() {
		try {

			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			
			
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(path + "/Ressources/plan10x10.xml");
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
			Vector<Noeud>tester = commandCenter.getModele().getPlanApp().getListeNoeuds();
			for (Troncon tr : tester.elementAt(3).getTronconSortant())
			{
				assertTrue(tester.elementAt(3) == tr.getDepart());
				assertTrue(tr.getArrivee().getTronconEntrant().contains(tr));
			}
		}
		catch (Exception e)
		{
			fail("Error Occured");
		}
		
	}
	
	@Test
	/**
	 * On teste le chargement du plan à partir d'un fichier inexistant. L'application renvoie une erreur.
	 */
	public void testCharger_unexisting_file_plan()
	{
		Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(path + "/Ressources/plan10.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);

		args.clear();
		args.add(path + "/Ressources/livraison10x10-1.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_LIVRAISON, args);
		
		
		assertFalse(commandCenter.getModele().getLivraisonData().size() > 0 );
		
	}
	@Test
	/**
	 * On teste le chargement des livraisons à partir d'un fichier XML mal formé, où l'heure de fin de la plage horaire n'est pas renseignée.
	 */
	public void testCharger_unvalid_file_plan()
	{
		Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
		
		
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(path + "/Ressources/plan10x10.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);

		args.clear();
		args.add(path + "/Implementation/Optimod_Lyon/src/Tests/plageHFinVide.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_LIVRAISON, args);
		
		
		assertFalse(commandCenter.getModele().getLivraisonData().size() > 0 );
		
	}
}
