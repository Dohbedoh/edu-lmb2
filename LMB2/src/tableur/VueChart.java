/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package tableur;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class VueChart extends JPanel {

	//------------------
	// Attributs
	//------------------
	private Hashtable<String, Integer> dataMotsComplet;
	
	//------------------
	// Constructeurs
	//------------------
	public VueChart(Hashtable<String, Integer> dataMotsComplet){
		this.dataMotsComplet = dataMotsComplet;

		setLayout(new BorderLayout());
		TitledBorder bfact = BorderFactory.createTitledBorder("Graphique");
		bfact.setTitleJustification(TitledBorder.CENTER);
		setBorder(bfact);
		
		/**
		 * UTILISER LA JFREE CHART
		 * METTRE DES BOUTONS
		 * ETC.
		 */
	}//cons-1
	//------------------
	// Méthodes
	//------------------

}
