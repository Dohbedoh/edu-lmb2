/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VueTableauStatistiques extends JPanel {

	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	
	private JTable table;
	private TableauModelStatistiques modele;
	//------------------
	// Constructeurs
	//------------------
	public VueTableauStatistiques(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;

		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Tableau");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);
		
		// Création des éléments graphiques
		modele = new TableauModelStatistiques(this.dataMotsComplet);
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
