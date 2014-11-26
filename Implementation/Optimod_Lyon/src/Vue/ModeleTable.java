package Vue;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import Modele.Livraison;

public class ModeleTable extends AbstractTableModel {
	String[] entetes = {"PlageHoraire", "id", "client", "adresse"};
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
		return donnees.get(rowIndex).get(columnIndex);
		
	}

}
