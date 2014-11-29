package Vue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import Modele.Client;
import Modele.Livraison;
import Modele.PlageHoraire;

public class AjoutLivraison extends JOptionPane {
	private PlageHoraire a; 
	private Client c; 
	private Livraison l; 
	private JButton bouton = new JButton("Valider");
	private JButton bouton2 = new JButton("Annuler");
	
	public AjoutLivraison(JFrame f, String titre, boolean modal)
	{
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = jop.showInputDialog(null, "Client:", "Ajouter point de Livraison", JOptionPane.QUESTION_MESSAGE);
	    jop2.showMessageDialog(null,"Livraison ajoutée pour le client " + nom, "Identité", JOptionPane.INFORMATION_MESSAGE);

	     
	}
}
