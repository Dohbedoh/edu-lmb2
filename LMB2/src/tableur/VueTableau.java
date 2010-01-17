/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VueTableau extends JPanel {

	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	
	private JTable table;
	private VueTableauModel modele;
	//------------------
	// Constructeurs
	//------------------
	public VueTableau(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;

		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Tableau");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);
		
		// Création des éléments graphiques
		modele = new VueTableauModel(this.dataMotsComplet);
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
