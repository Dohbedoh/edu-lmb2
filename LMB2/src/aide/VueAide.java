/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package aide;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class VueAide extends JFrame{

	private static final long serialVersionUID = 1L;

	//------------------
	// Attributs
	//------------------
	private JTabbedPane onglets;
	
	private VueAideCapture vueAideCapture;
	private VueAideStatistiques vueAideStatistiques;
	private VueAideComparaison vueAideComparaison;
	//------------------
	// Constructeur
	//------------------
	public VueAide(){
		super("Aspirateur - LMB2");
		setLayout(new BorderLayout());
		
		// Création des éléments graphiques
		onglets = new JTabbedPane(JTabbedPane.TOP);
		
		vueAideCapture = new VueAideCapture();
		vueAideStatistiques = new VueAideStatistiques();
		vueAideComparaison = new VueAideComparaison();
		
		// Ajouts des élements graphiques
		onglets.add("Capture",vueAideCapture);
		onglets.add("Statistiques",vueAideStatistiques);
		onglets.add("Comparaison", vueAideComparaison);
		
		add(onglets);

		
		// Ajout des actions
		
		
		// Options de la JFrame
		pack();
		setVisible(true);
		setLocation(50, 50);
		setPreferredSize(new Dimension(500,500));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
}
