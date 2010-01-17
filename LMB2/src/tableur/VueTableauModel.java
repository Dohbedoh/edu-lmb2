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
	
	//------------------
	// Constructeur
	//------------------
	public VueTableauModel(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;
		
	}//cons-1
	//------------------
	// M�thodes
	//------------------
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return dataMotsComplet.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		int i = 0;
		Enumeration<String> e = dataMotsComplet.keys();
		String cle = "";
		while (e.hasMoreElements() || i == rowIndex){
			i++;
			cle = e.nextElement();
		}
		
		return cle;
	}
}
