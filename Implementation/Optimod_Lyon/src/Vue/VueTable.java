package Vue;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Modele.Livraison;
import Modele.PlageHoraire;

public class VueTable extends JPanel {
	private JTable t; 
	public VueTable()
	{		
		ModeleTable modele = new ModeleTable(new Vector<Vector<Object>>());
		t = new JTable(modele);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(t), BorderLayout.CENTER);
	}

	public void chargerTable(Vector<PlageHoraire> p) {
		// TODO Auto-generated method stub
		Vector<Vector<Object>> donnees = new Vector<Vector<Object>>();
		
		for(PlageHoraire plage : p)
		{
			for(Livraison l : plage.getLivraisons())
			{
				Vector<Object> row = new Vector<Object>();
				row.add(plage.getHeureDebut()+ "-" + plage.getHeureFin());
				row.add(l.getIdLivraison());
				row.add(l.getDestinataire().getIdClient());
				row.add(l.getDestinataire().getNoeudAdresse().getIdNoeud());
				
				donnees.add(row);
			}
		}
	
		ModeleTable modele = new ModeleTable(donnees);
		t.setModel(modele);
		
	}

	public JTable getT() {
		return t;
	}
}
