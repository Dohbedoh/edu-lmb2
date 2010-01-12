/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class VueOnglets extends JPanel {

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	public VueProgressBar vueProgressBar;
	public JTabbedPane onglets;
	public JSplitPane split;
	
	public VueCaptureSite vueCaptureSite;
	public VueStatistiques vueStatistiques;
	//------------------
	// Constructeurs
	//------------------
	public VueOnglets(Aspirateur laspirateur, VueProgressBar vueProgressBar, JSplitPane split){
		this.laspirateur = laspirateur;
		this.vueProgressBar = vueProgressBar;
		this.split = split;
		
		onglets = new JTabbedPane();
		
		// Creation des onglets
		vueCaptureSite = new VueCaptureSite(laspirateur,vueProgressBar);
		vueStatistiques = new VueStatistiques(laspirateur);
		
		// Ajout des elements qui constituents les onglets
		onglets.add("Capturer site",vueCaptureSite);
		onglets.add("Statistiques",vueStatistiques);
		onglets.addChangeListener(new ChangerOnglet());
		
		// Ajout onglets
		this.add(onglets);
		
		// Options
		//vueCaptureSite.setPreferredSize(new Dimension(800,420));
		//onglets.setTabPlacement(JTabbedPane.RIGHT);
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
	
	//------------------
	// Actions
	//------------------
	/**
	 * Cette action permet de déplacer le JSplitPane 
	 * (On peut la modifier pour masquer la Console)
	 */
	private class ChangerOnglet implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
			/*
			//System.out.println(split.getDividerLocation());
			if(onglets.getSelectedComponent() instanceof VueStatistiques){
				split.setDividerLocation(600);
			}else if(onglets.getSelectedComponent() instanceof VueCaptureSite){
				split.setDividerLocation(463);
			}
			*/
		}
	}
}
