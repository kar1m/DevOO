package Controleur;

import java.io.*;

import javax.swing.JOptionPane;

import Modele.*;

public class ActionSauvegarder extends Action {

	
	private DataWareHouse modele;
	private Tournee tourneeCalculee;
	private String pathFichier;
	
	public ActionSauvegarder(DataWareHouse modele, Tournee tourneeCalculee, String pathFichier) {
		super();
		this.modele = modele;
		this.tourneeCalculee = tourneeCalculee;
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
	        w.write(this.GenerateSaveString(modele,tourneeCalculee));
	        w.close();
		}

		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde du fichier. Séléctionnez un nouvel emplacement");
    		return false; 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l'opération de sauvegarde");
    		return false; 
		}
		return true;
	}

	private String GenerateSaveString(DataWareHouse modele,
			Tournee tourneeCalculee) {
		return null;
	}

	@Override
	public boolean Annuler() {
		// TODO Auto-generated method stub
		return false;
	}

}
