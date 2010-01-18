/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package aide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class VueAide extends JFrame{

	//------------------
	// Attributs
	//------------------
	private JTabbedPane onglets;
	
	private VueAideCapture vueAideCapture;
	private VueAideStatistiques vueAideStatistiques;
	
	//------------------
	// Constructeur
	//------------------
	public VueAide(){
		super("Aspirateur - LMB2");
		setLayout(new BorderLayout());
		
		// Cr�ation des �l�ments graphiques
		onglets = new JTabbedPane(JTabbedPane.BOTTOM);
		
		vueAideCapture = new VueAideCapture();
		vueAideStatistiques = new VueAideStatistiques();
	
		// Ajouts des �lements graphiques
		onglets.add("Capture",vueAideCapture);
		onglets.add("Statistiques",vueAideStatistiques);
		
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
	// M�thodes
	//------------------
}
