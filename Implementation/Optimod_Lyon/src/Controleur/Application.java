package Controleur;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Modele.DataWareHouse;
import Outils.*;

/**
 * 
 */
public class Application {

    public Application() {
    	this.listeAnnulation = new Vector<Action>();
    	this.listeExecution = new Vector<Action>();
    	this.modele = new DataWareHouse();
    	this.outilXML = new XMLhandler();
    	
    }

    	private Vector<Action> listeAnnulation;

    	private Vector<Action> listeExecution;

    	private DataWareHouse modele;
    	
    	private XMLhandler outilXML;
    	
 
    public void chargerDemandeLivraison() {

    	File fichierData = outilXML.selectXML();
        if (fichierData != null) {
            try {
            	outilXML.checkXML(fichierData.getAbsolutePath(), Proprietes.PATH_XSD_DL);
                // creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fichierData);
               Element racine = document.getDocumentElement();
               
               // Initialiser les données
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
    }

    /**
     * 
     */
    public void chargerPlan() {

    	File fichierData = outilXML.selectXML();
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
    }

    /**
     * 
     */
    public void gererCommande() {
        // TODO implement here
    }

    /**
     * 
     */
    public void initApplication() {
        // TODO implement here
    }
    
	/**
	 * @return the listeAnnulation
	 */
	public Vector<Action> getListeAnnulation() {
		return listeAnnulation;
	}

	/**
	 * @return the listeExecution
	 */
	public Vector<Action> getListeExecution() {
		return listeExecution;
	}

	/**
	 * @return the modele
	 */
	public DataWareHouse getModele() {
		return modele;
	}


}