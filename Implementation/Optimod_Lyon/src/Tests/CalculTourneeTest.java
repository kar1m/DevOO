package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import Modele.Noeud;
import Modele.Plan;
import Modele.Troncon;
import Outils.Dijkstra;

public class CalculTourneeTest {

	@Test
	public void dijkstra1() throws SAXException, IOException, ParserConfigurationException {
		/*
		 * Test Dijkstra 1	
		 * Action : Calculer un chemin simple
		 * 			Plan : 2 sommets, 1 troncon
		 * Resultat : Chemin contenant 1 troncon
		 */
		
		//Chargement fichier XML
		Element racine = InitialiserPlan("src/Tests/plans/test1_planTronconUnique.xml");
		
		
        //Initialisation plan
		Plan plan = new Plan();
		plan.initPlan(racine);
		
		Noeud origine = plan.getNoeudbyID(0);
		Noeud destination = plan.getNoeudbyID(1);
		
		Dijkstra dijkstra = new Dijkstra(plan);
		Vector<Troncon> listeTroncons = dijkstra.Calcul(origine, destination);
		if(listeTroncons.get(0).getNomRue()!="a0")
		{
			fail("Troncon incorrect");
		}
	}
	
	public Element InitialiserPlan(String fileName)
	{
		Element racine = null;
		try {
			
			File fichierXMLPlan = new File(fileName);
			
			DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
	        // lecture du contenu d'un fichier XML avec DOM
	        Document document = constructeur.parse(fichierXMLPlan);
	        racine = document.getDocumentElement();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return racine;
	}
}
