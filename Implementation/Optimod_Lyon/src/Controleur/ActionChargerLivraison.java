package Controleur;

import java.io.File;
import java.io.IOException;

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

	private DataWareHouse modele;
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
            	XMLhandler.checkXML(fichierData.getAbsolutePath(), Proprietes.PATH_XSD_DL);
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();
               
               // Initialiser les donn�es
               	 modele.initEntrepot((Element)racine.getElementsByTagName("Entrepot"));
                 modele.initLivraison(racine);

           // todo : traiter les erreurs
           } catch (ParserConfigurationException pce) {
               System.out.println("Erreur de configuration du parseur DOM");
               System.out.println("lors de l'appel a fabrique.newDocumentBuilder();");
           } catch (SAXException se) {
               System.out.println("Erreur lors du parsing du document");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           } catch (IOException ioe) {
               System.out.println("Erreur d'entree/sortie");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           }
       } 
		return false;
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		return false;
	}

}
