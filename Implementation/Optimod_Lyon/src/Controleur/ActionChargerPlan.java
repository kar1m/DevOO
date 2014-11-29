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

public class ActionChargerPlan extends Action {

	
	private DataWareHouse modele;
	private XMLhandler outilXML;
	private String pathFichierData; 
	
	public ActionChargerPlan(DataWareHouse modele, XMLhandler outilXML, String pathFichierData)
	{
		this.modele = modele;
		this.outilXML = outilXML;
		this.pathFichierData = pathFichierData;
	}
	
	
	@Override
	public boolean Executer() {
		// TODO Auto-generated method stub
		File fichierData = new File(pathFichierData);
        if (fichierData != null) {
            try {
            	outilXML.checkXML(fichierData.getAbsolutePath(), Proprietes.PATH_XSD_PLAN);
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();

               // appel des initialiseurs
               modele.initDataPlan(racine);

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
