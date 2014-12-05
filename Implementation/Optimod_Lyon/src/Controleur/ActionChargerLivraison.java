package Controleur;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import Modele.DataWareHouse;
import Outils.Proprietes;
import Outils.XMLhandler;

public class ActionChargerLivraison extends Action {

	/**
	 * Modele associe a laction
	 */
	private DataWareHouse modele;
	/**
	 * Chemin du fichier associe a laction
	 */
	private String pathFichier;
	
	
	public ActionChargerLivraison(DataWareHouse modele, String pathFichier)
	{
		this.modele = modele;
		this.pathFichier = pathFichier; 
	}
	@Override
	public boolean Executer() {
		
		File fichierData = new File(pathFichier);
        if (fichierData != null) {
            try {
            	if(!XMLhandler.checkXML(fichierData.getAbsolutePath(), Proprietes.PATH_XSD_DL))
            	{
					JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la demande de livraison.");
            		return false; 
            	}
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();
               
               // Initialiser les donn�es
               	modele.initLivraison(racine);
               	modele.initEntrepot((Element)racine.getElementsByTagName("Entrepot").item(0));
               	
           // todo : traiter les erreurs
            } catch (ParserConfigurationException pce) {
         	   pce.printStackTrace();
         	   javax.swing.JOptionPane.showMessageDialog(null,"Erreur de configuration du parseur DOM");
                System.out.println("lors de l'appel a fabrique.newDocumentBuilder();");
                return false;
            } catch (SAXException se) {
         	   javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors du parsing du document");
                System.out.println(Proprietes.ERREUR_XML);
                return false;
            } catch (IOException ioe) {
         	   javax.swing.JOptionPane.showMessageDialog(null,"Erreur d'entree/sortie");
                System.out.println("lors de l'appel a construteur.parse(xml)");
                return false;
            } catch (Exception e) {
 				System.out.println(e.getMessage());
 				javax.swing.JOptionPane.showMessageDialog(null,e.getMessage()); 
 				return false;
            }
       } 
		return true;
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		return false;
	}

}
