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
	public Aspirateur laspirateur;
	public VueProgressBar vueProgressBar;
	public JTabbedPane onglets;
	
	public VueCaptureSite vueCaptureSite;
	public VueStatistiques vueStatistiques;
	//------------------
	// Constructeurs
	//------------------
	public VueOnglets(Aspirateur laspirateur, VueProgressBar vueProgressBar){
		this.laspirateur = laspirateur;
		this.vueProgressBar = vueProgressBar;
		
		onglets = new JTabbedPane();
		
		// Creation des onglets
		vueCaptureSite = new VueCaptureSite(laspirateur,vueProgressBar);
		vueStatistiques = new VueStatistiques(laspirateur);
		
		// Ajout des elements qui constituents les onglets
		onglets.add("Capturer site",vueCaptureSite);
		onglets.add("Statistiques",vueStatistiques);
		
		// Ajout onglets
		this.add(onglets);
		
		// Options
		//onglets.setPreferredSize(new Dimension(700,300));
	}
	
	//------------------
	// Methodes
	//------------------
	public VueCaptureSite getVueCaptureSite(){
		return this.vueCaptureSite;
	}
	
	public VueStatistiques getVueStatistiques(){
		return this.vueStatistiques;
	}
	
}
