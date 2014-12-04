package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Controleur.Application;
import Modele.DataWareHouse;
import Modele.Livraison;
import Modele.PlageHoraire;
import Outils.Proprietes;
import Vue.Fenetre;

public class ControlUndoRedo {

	private String path = "/Users/Mehdi/Desktop/4IF/DevOO";
	private Application commandCenter ; 
	
	
	@Before 
	public void initialize() {
		commandCenter= new Application(new Fenetre(), new DataWareHouse());
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(path + "/Ressources/plan10x10.xml");
		commandCenter.gererCommande(Proprietes.CHARGER_PLAN, args);
	}
	
	@Test
	/**
	 * Aucune action n'a encore été effectuée, la pile des undo et la pile des redo est vide.
	 * Lorsque l'utilisateur tente de faire un undo, un message signale a l'utilisateur que c'est impossible.
	 */
	public void testUR1() {
		
			/*
			 * ACTIONS
			 */
			commandCenter.gererCommande(Proprietes.UNDO, null);
			
			/*
			 * VERIFS
			 */
			assertEquals(commandCenter.getListeAnnulation().size(),0);
			assertEquals(commandCenter.getListeExecution().size(),0);
	}
	
	@Test
	/**
	 * Aucune action n'a encore été effectuée, la pile des undo et la pile des redo est vide.
	 * Lorsque l'utilisateur tente de faire un redo, un message signale a l'utilisateur que c'est impossible.
	 */
	public void testUR2() {
			/*
			 * ACTIONS
			 */
			commandCenter.gererCommande(Proprietes.REDO, null);
			
			/*
			 * VERIFS
			 */
			assertEquals(commandCenter.getListeAnnulation().size(),0);
			assertEquals(commandCenter.getListeExecution().size(),0);
		}
		
	@Test
	/**
	 * On effectue une action non annulable (enregistrer, par exemple) et on essaye de faire un undo. Ca ne fonctionne pas, car la pile du undo est vide.
	 */
	public void testUR3() {
			/*
			 * ACTIONS
			 */
			commandCenter.gererCommande(Proprietes.SAVE, null);
			
			/*
			 * VERIFS
			 */
			assertEquals(commandCenter.getListeAnnulation().size(),0);
			assertEquals(commandCenter.getListeExecution().size(),0);
		}
		
	@Test	
	/**
	 * On effectue une action annulable (créer une nouvelle plage horaire, par exemple) et on fait un undo. 
	 * L'action est annulée, la pile caractérisant la liste d'exécution est vide (ou contient les actions précédemment effectuées).
	 * La pile caractérisant la liste d'actions annulées contient l'action que l'on vient d'annuler.
	 */
	public void testUR4() {	
			/*
			 * ACTIONS
			 */
			PlageHoraire a = new PlageHoraire(5); 
			Livraison l = new Livraison(); 
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(a);
			args.add(l);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);
			commandCenter.gererCommande(Proprietes.UNDO, null);
			
			
			/*
			 * VERIFS
			 */
			assertTrue(commandCenter.getListeAnnulation().size() == 1);
			assertTrue(commandCenter.getListeExecution().size() == 0);
			//On verifie que la liste des livraisons est vide
			assertTrue(commandCenter.getModele().getLivraisonData().size() == 1); 
			
		}

	@Test
	public void testUR5() {
		/**
		 * La pile caractérisant la liste d'exécution est initialement vide.
		 * On effectue une action annulable (créer une nouvelle plage horaire, par exemple) et on fait un undo, puis un redo. 
		 * L'action est annulée.
		 * L'action est refaite.
		 * La pile caractérisant la liste d'actions annulées est désormais vide, et la pile caractérisant la liste d'exécution contient l'action effectuée.
		 * 
		 */
		
			/*
			 * ACTIONS
			 */
			PlageHoraire a = new PlageHoraire(5); 
			Livraison l = new Livraison(); 
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(a);
			args.add(l);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);
			commandCenter.gererCommande(Proprietes.UNDO, null);
			commandCenter.gererCommande(Proprietes.REDO, null);
			
			/*
			 * VERIFS
			 */
			assertTrue(commandCenter.getListeAnnulation().size() == 0);
			assertTrue(commandCenter.getListeExecution().size() == 1);
			//On verifie que la liste des livraisons contient 1 �l�ment
			assertTrue(commandCenter.getModele().getLivraisonData().size() == 1);
		}

	@Test	
	public void testUR6() {
		/**
		 * La pile caractérisant la liste d'exécution est initialement vide.
		 * On effectue une action annulable (créer une nouvelle plage horaire, par exemple) et on fait un undo, puis un redo et enfin un undo à nouveau. 
		 * L'action est annulée.
		 * L'action est refaite.
		 * L'action est annulée à nouveau
		 * La pile caractérisant la liste d'actions annulées contient l'action annulée, et la pile caractérisant la liste d'exécution est désormais vide.
		 * 
		 */
			commandCenter.getModele().getLivraisonData().clear();

			/*
			 * ACTIONS
			 */
			PlageHoraire a = new PlageHoraire(5); 
			Livraison l = new Livraison(); 
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(a);
			args.add(l);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);

			commandCenter.gererCommande(Proprietes.UNDO, null);
			commandCenter.gererCommande(Proprietes.REDO, null);
			commandCenter.gererCommande(Proprietes.UNDO, null);
			
			/*
			 * VERIFS
			 */
			assertEquals(commandCenter.getListeAnnulation().size(),1);
			assertEquals(commandCenter.getListeExecution().size(),0);
			//On verifie que la liste des livraisons est vide
			int indexPlageHoraire = commandCenter.getModele().getLivraisonData().indexOf(a);
			assertEquals(commandCenter.getModele().getLivraisonData().get(indexPlageHoraire).getLivraisons().size(),0);		
			
		}
		
	@Test	
	public void testUR7() {
		/**
		 * La pile caractérisant la liste d'exécution est initialement vide.
		 * On effectue une action1 annulable (créer une nouvelle plage horaire, par exemple).
		 * On effectue une deuxième action2 annulable.
		 * On fait un undo.
		 * On fait un undo.
		 * On fait un redo. 
		 * L'action action1 est annulée.
		 * L'action action2 est annulée.
		 * L'action action2 est refaite.
		 */
			
			/*
			 * ACTIONS
			 */
			PlageHoraire a = new PlageHoraire(5); 
			Livraison l = new Livraison(); 
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(a);
			args.add(l);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);
			commandCenter.gererCommande(Proprietes.UNDO, null);
			commandCenter.gererCommande(Proprietes.UNDO, null);
			commandCenter.gererCommande(Proprietes.REDO, null);
			
			/*
			 * VERIFS
			 */
			assertEquals(commandCenter.getListeAnnulation().size(),1);
			assertEquals(commandCenter.getListeExecution().size(),1);
			//On verifie que la liste des livraisons contient 1 �l�ment
			int indexPlageHoraire = commandCenter.getModele().getLivraisonData().indexOf(a);
			assertEquals(commandCenter.getModele().getLivraisonData().get(indexPlageHoraire).getLivraisons().size(),1);
			
		}
		
	@Test	
	public void testUR8() {
		/**
	 * La pile caractérisant la liste d'exécution est initialement vide.
	 * On effectue une action1 annulable (créer une nouvelle plage horaire, par exemple).
	 * On fait un undo.
	 * On effectue une deuxième action2 annulable..
	 * On fait un redo.
	 * L'action action1 est annulée.
	 * La pile caractérisant la liste d'actions annulées est désormais vide, et la pile caractérisant la liste d'exécution contient l'action effectuée.
	*/

			
			/*
			 * ACTIONS
			 */
			PlageHoraire a = new PlageHoraire(5); 
			Livraison l = new Livraison(); 
			ArrayList<Object> args = new ArrayList<Object>();
			args.add(a);
			args.add(l);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);
			commandCenter.gererCommande(Proprietes.UNDO, null);
			commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON, args);
			commandCenter.gererCommande(Proprietes.REDO, null);
			
			/*
			 * VERIFS
			 */
			assertEquals(commandCenter.getListeAnnulation().size(),0);
			assertEquals(commandCenter.getListeExecution().size(),1);
			//On verifie que la liste des livraisons contient 1 �l�ment
			assertEquals(commandCenter.getModele().getLivraisonData().size(),1);
		}	
}
