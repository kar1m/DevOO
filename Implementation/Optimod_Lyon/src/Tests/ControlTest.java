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
			commandCenter.gererCommande(Proprietes.CHARGER_PLAN);;
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

}
