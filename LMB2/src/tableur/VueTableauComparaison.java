/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class VueTableauComparaison extends JPanel {

	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet1;
	private Hashtable<String, Integer> dataMotsComplet2;
	
	private JTable table;
	private TableauModelComparaison modele;
	//------------------
	// Constructeurs
	//------------------
	public VueTableauComparaison(Hashtable<String, Integer> dataMotsComplet1, Hashtable<String, Integer> dataMotsComplet2){
		this.dataMotsComplet1 = dataMotsComplet1;
		this.dataMotsComplet2 = dataMotsComplet2;
		
		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Tableau");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);
		
		// Création des éléments graphiques
		modele = new TableauModelComparaison(dataMotsComplet1, dataMotsComplet2);
		table = new JTable(modele);
		table.setAutoCreateRowSorter(true);
		
		// Ajout des éléments graphiques
		add(new JScrollPane(table));
		
		// Options
		
	}//cons-1
	//------------------
	// Méthodes
	//------------------
}
