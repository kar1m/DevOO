package Vue;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Modele.Livraison;
import Modele.PlageHoraire;
/**
 * Vue correspondant a la table dans la fenetre
 * @author Mehdi Kitane
 *
 */
public class VueTable extends JPanel {
	private JTable t; 
	public VueTable()
	{		
		ModeleTable modele = new ModeleTable(new Vector<Vector<Object>>());
		t = new JTable(modele);
		t.getColumnModel().getColumn(0).setPreferredWidth(2);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(t), BorderLayout.CENTER);
	}

	/**
	 * Methode qui permet de charger la table a partir du vecteur de plage horaire
	 * @param p Vector<PlageHoraire>
	 */
	public void chargerTable(Vector<PlageHoraire> p) {
		// TODO Auto-generated method stub
		Vector<Vector<Object>> donnees = new Vector<Vector<Object>>();
		int numPlageHoraire = 0; 
		for(PlageHoraire plage : p)
		{
			for(Livraison l : plage.getLivraisons())
			{
				Vector<Object> row = new Vector<Object>();
				row.add(numPlageHoraire); //Sert pour la couleur de la premiere cellule
				row.add(plage.getHeureDebut()+ "-" + plage.getHeureFin());
				row.add(l.getIdLivraison());
				row.add(l.getDestinataire().getIdClient());
				row.add(l.getDestinataire().getNoeudAdresse().getIdNoeud());
				donnees.add(row);
			}
			numPlageHoraire++;
		}
	
		ModeleTable modele = new ModeleTable(donnees);
		t.setModel(modele);
		t.getColumnModel().getColumn(0).setPreferredWidth(2);

	}

	public JTable getT() {
		return t;
	}
}
