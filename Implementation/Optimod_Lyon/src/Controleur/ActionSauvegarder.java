package Controleur;

import java.io.*;
import java.sql.Time;
import java.util.*;
import javax.swing.JOptionPane;

import Modele.*;
import Outils.Proprietes;

public class ActionSauvegarder extends Action {

	
	private DataWareHouse modele;
	private String pathFichier;
	
	public ActionSauvegarder(DataWareHouse modele, String pathFichier) {
		super();
		this.modele = modele;
		this.pathFichier = pathFichier;
	}

	@Override
	public boolean Executer() {
		File fichierData = new File(pathFichier);
		FileOutputStream is;
		try {
			is = new FileOutputStream(fichierData);
	        OutputStreamWriter osw = new OutputStreamWriter(is);    
	        Writer w = new BufferedWriter(osw);
	        
	        // Ecriture dans le flux
	        w.write(this.GenerateSaveString(modele));
	        w.close();
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde du fichier. Sï¿½lï¿½ctionnez un nouvel emplacement");
    		return false; 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l'opï¿½ration de sauvegarde");
    		return false; 
		}
		return true;
	}
	/**
	 * @param modele contenant la tournée à sauvegarder
	 * @return string parsé et formaté contenant les éléments de la sauvegarde
	 */
	private String GenerateSaveString(DataWareHouse modele) {
		String save = "TOURNEE CALCULEE \r\n";
		Vector<Vector<Chemin>> tourChemins = modele.getTournee().getListeChemins();
		
		Time departGlobal = modele.getLivraisonData().firstElement().getHeureDebut_H();
		save += "\r\n";
		save += "DÃ©part de l'entrepot Ã  : " + departGlobal.toString();
		save += "\r\n";
		for (Vector<Chemin> chemin : tourChemins)
		{
			for (Chemin tranc : chemin)
			{
				Noeud depart = tranc.getListeTroncons().get(0).getDepart();
				save += "DÃ©part de l'adresse : " + depart.getIdNoeud() +" Ã  "+departGlobal+ "\r\n";
				for (Troncon rue : tranc.getListeTroncons())
				{
					save += "Passez par la rue : "+rue.getNomRue() +" Vitesse prÃ©conisÃ©e : "+rue.getVitesse()+ "\r\n";
				}
				Noeud arrivee = tranc.getListeTroncons().get(tranc.getListeTroncons().size()-1).getArrivee();
				departGlobal = CalculerTempsAdditionnel(departGlobal,tranc.getTemps());
				save += "ArrivÃ©e Ã  l'adresse : " + arrivee.getIdNoeud() +" Ã  "+departGlobal+"\r\n";
					if (chemin != tourChemins.lastElement() || tranc != chemin.lastElement()) {
						save += "En attente du client : 10 min";
						departGlobal = CalculerTempsAdditionnel(departGlobal,
								Proprietes.ATTENTE);
						save += "\r\n";
						save += "\r\n";
					}
			}
		}
		save += "ArrivÃ©e Ã  l'entrepot Ã  : " + departGlobal.toString();
		save += "\r\n";
		return save;
	}
	/**
	 * méthode de calcul de temps
	 * @param top temps initial
	 * @param delta délai d'attente à ajouter
	 * @return nouveau temps calculé
	 */
	private Time CalculerTempsAdditionnel(Time top,int delta){
		long heuresOutput = top.getTime()+(delta*1000);
		return new Time (heuresOutput);
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		return false;
	}

}
