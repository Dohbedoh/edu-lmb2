/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.table.AbstractTableModel;

public class VueTableauModel extends AbstractTableModel {

	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	private final String[] columnNames = {"Elements","Nombre d'occurrences"};
	
	//------------------
	// Constructeur
	//------------------
	public VueTableauModel(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;
		
	}//cons-1
	//------------------
	// Méthodes
	//------------------
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return dataMotsComplet.size();
	}

	public String getColumnName(int column) {
	    return columnNames[column];
	  }
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			int i = 0;
			Enumeration<String> e = dataMotsComplet.keys();
			String cle = e.nextElement();
			
			while (e.hasMoreElements() && i != rowIndex){
				i++;
				cle = e.nextElement();
			}
			
			return cle;
		}else{
			int i = 0;
			Enumeration<String> e = dataMotsComplet.keys();
			String cle = e.nextElement();
			while (e.hasMoreElements() && i != rowIndex){
				i++;
				cle = e.nextElement();
			}
			return dataMotsComplet.get(cle);
		}
	}
}
