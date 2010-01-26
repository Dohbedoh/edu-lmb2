/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.table.AbstractTableModel;

public class TableauModelComparaison extends AbstractTableModel {

    //------------------
    // Attributs
    //------------------
    private Hashtable<String, Integer> dataMotsComplet1;
    private Hashtable<String, Integer> dataMotsComplet2;
    private final String[] columnNames = {"Elements","Occurences Site 1","Occurences Site 2"};
    
    //------------------
    // Constructeur
    //------------------
    public TableauModelComparaison(Hashtable<String, Integer> dataMotsComplet1, Hashtable<String,Integer> dataMotsComplet2){
            this.dataMotsComplet1 = dataMotsComplet1;
            this.dataMotsComplet2 = dataMotsComplet2;
            
    }//cons-1
    //------------------
    // Méthodes
    //------------------
    public int getColumnCount() {
            return 3;
    }

    public int getRowCount() {
            return dataMotsComplet1.size();
    }

    public String getColumnName(int column) {
        return columnNames[column];
      }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
            if(columnIndex == 0){
                    int i = 0;
                    Enumeration<String> e = dataMotsComplet1.keys();
                    String cle = e.nextElement();
                    
                    while (e.hasMoreElements() && i != rowIndex){
                            i++;
                            cle = e.nextElement();
                    }
                    
                    return cle;
            }else{
            	if(columnIndex == 1){
                    int i = 0;
                    Enumeration<String> e = dataMotsComplet1.keys();
                    String cle = e.nextElement();
                    while (e.hasMoreElements() && i != rowIndex){
                            i++;
                            cle = e.nextElement();
                    }
                    return dataMotsComplet1.get(cle);
            	}else{
                    int i = 0;
                    Enumeration<String> e = dataMotsComplet2.keys();
                    String cle = e.nextElement();
                    while (e.hasMoreElements() && i != rowIndex){
                            i++;
                            cle = e.nextElement();
                    }
                    return dataMotsComplet2.get(cle);
            	}
            }
    }
}
