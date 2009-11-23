/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;

import Aspirateur.*;

public class VueProgressBar extends JPanel{

	//------------------
	// Attributs
	//------------------
	Aspirateur laspirateur;
	JProgressBar bar;
	
	//--------------
	// Constructeur
	//--------------
	public VueProgressBar(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		
		// Creation des elements graphiques
		bar = new JProgressBar();
		
		// Options des elements graphiques
		bar.setStringPainted(true);
		
		bar.setMaximum(100);
		bar.setMinimum(0);
		
		
		// Ajout des elements graphiques
		add(bar);
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public JProgressBar getProgressBar(){
		return this.bar;
	}
	
	public VueProgressBar getVueProgressBar(){
		return this;
	}
	
	//--------------
	// Actions
	//--------------
}
