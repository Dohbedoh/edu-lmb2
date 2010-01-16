/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import javax.swing.event.*;

import statistiques.Statistiques;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

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
		this.setLayout(new BorderLayout(5,5));
		
		onglets = new JTabbedPane();
		
		// Creation des onglets
		vueCaptureSite = new VueCaptureSite(laspirateur,vueProgressBar);
		
		//Statistiques stats = new Statistiques(new File("/Users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470"));
		//stats.init();
		vueStatistiques = new VueStatistiques(new Statistiques(null));
		
		// Ajout des elements qui constituents les onglets
		onglets.add("Capturer site",vueCaptureSite);
		onglets.add("Statistiques",vueStatistiques);
		onglets.addChangeListener(new ChangerOnglet());
		// Ajout onglets
		this.add(onglets, BorderLayout.CENTER);
		
		// Options
		
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
	
	public void setOnglet(int i){
		if(onglets.getComponent(i)!=null){
			onglets.setSelectedIndex(i);
		}
	}
	
	public void setEnabled(boolean b){
		vueCaptureSite.setEnabled(b);
		vueStatistiques.setEnabled(b);
	}
	
	//------------------
	// Actions
	//------------------
	/**
	 * Cette action permet de déplacer le JSplitPane 
	 */
	private class ChangerOnglet implements ChangeListener{
		
		public void stateChanged(ChangeEvent e) {
		
			if(onglets.getSelectedComponent() instanceof VueStatistiques){
				split.setDividerLocation(split.getMaximumDividerLocation()-10);
			}else if(onglets.getSelectedComponent() instanceof VueCaptureSite){
				split.setDividerLocation(split.getMinimumDividerLocation()+25);
			}
			
		}
	}
}
