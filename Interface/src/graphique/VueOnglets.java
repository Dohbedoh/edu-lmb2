/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class VueOnglets extends JPanel {

	//------------------
	// Attributs
	//------------------
	public TestAspirateur laspirateur;
	public JTabbedPane onglets;
	
	public VueCaptureSite vueCaptureSite;
	public VueStatistiques vueStatistiques;
	//------------------
	// Constructeurs
	//------------------
	public VueOnglets(TestAspirateur laspirateur){
		this.laspirateur = laspirateur;
		onglets = new JTabbedPane();
		
		// Creation des onglets
		vueCaptureSite = new VueCaptureSite(laspirateur);
		vueStatistiques = new VueStatistiques(laspirateur);
		
		// Ajout des elements qui constituents les onglets
		onglets.add("Capturer site",vueCaptureSite);
		onglets.add("Statistiques",vueStatistiques);
		
		// Ajout onglets
		this.add(onglets);
	}
}
