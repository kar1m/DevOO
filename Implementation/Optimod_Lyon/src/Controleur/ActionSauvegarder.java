package Controleur;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import Modele.*;

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
			JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde du fichier. S�l�ctionnez un nouvel emplacement");
    		return false; 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l'op�ration de sauvegarde");
    		return false; 
		}
		return true;
	}

	private String GenerateSaveString(DataWareHouse modele) {
		String save = "TOURNEE CALCULEE \r\n";
		Tournee tour = modele.getTournee();
		Map<PlageHoraire,Vector<Chemin>> tourChemins = modele.getTournee().getListeChemins();
		
		for (Entry<PlageHoraire, Vector<Chemin>> chemin : tourChemins.entrySet())
		{
			for (Chemin tranc : chemin.getValue())
			{
				Noeud depart = tranc.getListeTroncons().get(0).getDepart();
				save += "Départ de l'adresse : " + depart.getIdNoeud() +"\r\n";
				for (Troncon rue : tranc.getListeTroncons())
				{
					save += rue.getNomRue() + "\r\n";
				}
				Noeud arrivee = tranc.getListeTroncons().get(tranc.getListeTroncons().size()-1).getArrivee();
				save += "Arrivée à l'adresse : " + depart.getIdNoeud() +"\r\n";
			}
		}
		return save;
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		return false;
	}

}
