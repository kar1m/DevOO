package Tests;

import static org.junit.Assert.*;

import java.util.Vector;
import org.junit.Test;
import Controleur.*;
import Modele.*;
import Outils.Proprietes;
import Vue.Fenetre;

public class ControlTest {

	@Test
	public void testChargerDemandeLivraison() {
		try {
			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			commandCenter.gererCommande(Proprietes.CHARGER_LIVRAISON);;
			//Pour tester
			for (PlageHoraire entry : commandCenter.getModele()
					.getLivraisonData()) {
				PlageHoraire value = entry;
				System.out.println(value.getHeureDebut() + " "
						+ value.getHeureFin());
				Vector<Livraison> vect = entry.getLivraisons();
				for (Livraison valu : vect) {
					System.out.println(valu.getIdLivraison() + " "
							+ valu.getDestinataire().getIdClient() + " "
							+ valu.getDestinataire().getIdNoeudAdresse());
				}
			}
			assertTrue(true);
		} 
		catch (Exception e) {
			// TODO: handle exception
			fail("Error Occured");
		}
		
	}

	@Test
	public void testChargerPlan() {
		try {

			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN);
			//Pour tester
			Plan ned = commandCenter.getModele().getPlanApp();
			Vector<Troncon> ts = ned.getListeTroncons();
			for (Troncon tr : ts)
				{
					System.out.println(tr.toString());
				}
			}
			catch (Exception e)
			{
				fail("Error Occured");
			}
		
	}
	
	/*
	 * TESTS UNDO/REDO
	 */
	public void testUndoRedo() {
		try {
			Application commandCenter = new Application(new Fenetre(), new DataWareHouse());
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN);
			
			testUR1(commandCenter);
			testUR2(commandCenter);
			testUR3(commandCenter);
			testUR4(commandCenter);
			testUR5(commandCenter);
			testUR6(commandCenter);
			testUR7(commandCenter);
			testUR8(commandCenter);
			
			
		}
		catch (Exception e)
		{
			fail("Une erreur est survenue "+ e.getMessage());
		}
		
	}
	
	public void testUR1(Application commandCenter) {
	/*
	 * Test UNDO REDO 1	
	 * PILES VIDES & UNDO
	 * Action : Pile undo & redo vide, demande de Undo
	 * Resultat : Rien, un message doit signaler à l'utilisateur que c'est impossible
	 */
	
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.UNDO);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),0);
		assertEquals(commandCenter.getListeExecution().size(),0);
	}
	
	public void testUR2(Application commandCenter) {
	/*
	 * Test UNDO REDO 2
	 * PILE VIDES & REDO
	 * Action : Pile undo & redo vide, demande de Redo
	 * Resultat : Rien
	 */
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.REDO);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),0);
		assertEquals(commandCenter.getListeExecution().size(),0);
	}
	
	public void testUR3(Application commandCenter) {
	/*
	 * Test UNDO REDO 3
	 * UNDO SUR ACTION NON ANNULABLE
	 * Action : On effectue une action non annulable, la pile était vide
	 * 			On effectue un undo
	 * Resultat : Rien, la pile est toujours vide
	 */
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.SAVE);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),0);
		assertEquals(commandCenter.getListeExecution().size(),0);
	}
	
	public void testUR4(Application commandCenter) {
	/*
	 * Test UNDO REDO 4
	 * UNDO SUR UNE ACTION ANNULABLE
	 * Action : On effectue une action annulable, la pile était vide
	 * 			On effectue un undo
	 * Resultat : 	L'action a été annulée, 
	 * 				la pile listeExecution est desormais vide, 
	 * 				la pile listeAnnulation contient l'action annulée 
	 */
		
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON);
		commandCenter.gererCommande(Proprietes.UNDO);
		
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),1);
		assertEquals(commandCenter.getListeExecution().size(),0);
		//On verifie que la liste des livraisons est vide
		assertEquals(commandCenter.getModele().getLivraisonData().size(),0);
		
	}

	public void testUR5(Application commandCenter) {
	/*
	 * Test UNDO REDO 5
	 * REDO SUR UNE ACTION ANNULABLE ET ANNULEE
	 * Action : On effectue une action annulable, la pile était vide
	 * 			On effectue un undo
	 * 			On effectue un redo
	 * Resultat : 	L'action a été annulée, 
	 * 				l'action a été refaite
	 * 				la pile listeAnnulation est desormais vide, 
	 * 				la pile listeExecution contient l'action annulée 
	 */
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON);
		commandCenter.gererCommande(Proprietes.UNDO);
		commandCenter.gererCommande(Proprietes.REDO);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),0);
		assertEquals(commandCenter.getListeExecution().size(),1);
		//On verifie que la liste des livraisons contient 1 élément
		assertEquals(commandCenter.getModele().getLivraisonData().size(),1);
	}

	public void testUR6(Application commandCenter) {
	/*
	 * Test UNDO REDO 6
	 * DOUBLE UNDO / REDO SUR UNE ACTION ANNULABLE
	 * Action : On effectue une action annulable, la pile était vide
	 * 			On effectue un undo
	 * 			On effectue un redo
	 * 			On effectue un undo
	 * Resultat : 	L'action a été annulée, 
	 * 				l'action a été refaite
	 * 				l'action a été annulée
	 * 				la pile listeExecution est desormais vide, 
	 * 				la pile listeAnnulation contient l'action annulée 
	 */
		
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON);
		commandCenter.gererCommande(Proprietes.UNDO);
		commandCenter.gererCommande(Proprietes.REDO);
		commandCenter.gererCommande(Proprietes.UNDO);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),1);
		assertEquals(commandCenter.getListeExecution().size(),0);
		//On verifie que la liste des livraisons est vide
		assertEquals(commandCenter.getModele().getLivraisonData().size(),0);		
		
	}
	
	public void testUR7(Application commandCenter) {
	/*
	 * Test UNDO REDO 7
	 * VERIFICATION BONNE ACTION REDO
	 * Action : On effectue une action1 annulable, la pile était vide
	 * 			On effectue une autre action2 annulable
	 * 			On effectue un undo
	 * 			On effectue un undo
	 * 			On effectue un redo
	 * Resultat : 	L'action1 a été annulée, 
	 * 				l'action2 a été annulée
	 * 				l'action2 a été refaite
	 */
		
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON);
		commandCenter.gererCommande(Proprietes.SUPP_LIVRAISON);
		commandCenter.gererCommande(Proprietes.UNDO);
		commandCenter.gererCommande(Proprietes.UNDO);
		commandCenter.gererCommande(Proprietes.REDO);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),1);
		assertEquals(commandCenter.getListeExecution().size(),1);
		//On verifie que la liste des livraisons contient 1 élément
		assertEquals(commandCenter.getModele().getLivraisonData().size(),0);
		
	}
	
	public void testUR8(Application commandCenter) {
	/*
	 * Test UNDO REDO 8
	 * VERIFICATION CLEAR SUR NOUVELLE ACTION
	 * Action : On effectue une action1 annulable, la pile était vide
	 * 			On effectue un undo
	 * 			On effectue une action2 annulable
	 * 			On effectue un redo
	 * Resultat : 	Il ne se passe rien
	 * 				L'action1 a été annulée,
	 * 				la pile listeExecution contient l'action, 
	 * 				la pile listeAnnulation est desormais vide
	 */
		
		/*
		 * ACTIONS
		 */
		commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON);
		commandCenter.gererCommande(Proprietes.UNDO);
		commandCenter.gererCommande(Proprietes.AJOUTER_LIVRAISON);
		commandCenter.gererCommande(Proprietes.REDO);
		
		/*
		 * VERIFS
		 */
		assertEquals(commandCenter.getListeAnnulation().size(),0);
		assertEquals(commandCenter.getListeExecution().size(),1);
		//On verifie que la liste des livraisons contient 1 élément
		assertEquals(commandCenter.getModele().getLivraisonData().size(),1);
	}	
	
}
