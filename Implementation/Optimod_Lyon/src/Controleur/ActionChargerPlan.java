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
	private String pathFichierData; 
	
	public ActionChargerPlan(DataWareHouse modele, String pathFichierData)
	{
		this.modele = modele;
		this.pathFichierData = pathFichierData;
	}
	
	
	@Override
	public boolean Executer() {
		// TODO Auto-generated method stub
		modele.getLivraisonData().clear();
		
		File fichierData = new File(pathFichierData);
        if (fichierData != null) {
            try {
            	XMLhandler.checkXML(fichierData.getAbsolutePath(), Proprietes.PATH_XSD_PLAN);
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();

               // appel des initialiseurs
               modele.initDataPlan(racine);

           // todo : traiter les erreurs
           } catch (ParserConfigurationException pce) {
        	   pce.printStackTrace();
        	   javax.swing.JOptionPane.showMessageDialog(null,"Erreur de configuration du parseur DOM");
               System.out.println("lors de l'appel a fabrique.newDocumentBuilder();");
           } catch (SAXException se) {
        	   javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors du parsing du document");
               System.out.println(Proprietes.ERREUR_XML);
           } catch (IOException ioe) {
        	   javax.swing.JOptionPane.showMessageDialog(null,"Erreur d'entree/sortie");
               System.out.println("lors de l'appel a construteur.parse(xml)");
           } catch (Exception e) {
			// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				javax.swing.JOptionPane.showMessageDialog(null,e.getMessage()); 
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
