package Vue;

import java.awt.Color;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Modele.Livraison;
import Outils.Proprietes;

public class ModeleTable extends AbstractTableModel {
	String[] entetes = {"Couleur", "PlageHoraire", "id", "client", "adresse"};
	Vector<Vector<Object>> donnees = new Vector<Vector<Object>>(); 
	
	public ModeleTable(Vector<Vector<Object>> donnees)
	{
		this.donnees = donnees; 
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return donnees.size();
	}

	public String getColumnName(int column) {
        return entetes[column];
    }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex == 0){
			int num = (int) donnees.get(rowIndex).get(columnIndex); 
			Color c = Proprietes.cols[num];
			return "<html><body bgcolor=\"#"+ Integer.toHexString(c.getRGB()).substring(2) +"\" style=\"height:100px;width:100px;\"></body></html>";
		}
		return donnees.get(rowIndex).get(columnIndex);
		
	}

}
