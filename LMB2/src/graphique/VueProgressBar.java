/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;

import statistiques.Statistiques;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import Aspirateur.*;

public class VueProgressBar extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	private Aspirateur laspirateur;
	private Statistiques stats;
	private JProgressBar bar;
	
	//--------------
	// Constructeur
	//--------------
	public VueProgressBar(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		laspirateur.addObserver(this);
		
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

	public void setStatistiques(Statistiques stats){
		this.stats = stats;
		stats.addObserver(this);
	}
	
	public void update(Observable o, Object arg) {
		if(o instanceof Aspirateur){
			double totalCopies = laspirateur.getNbFichiersCopies();
			double total = totalCopies + laspirateur.getNbFichiersACopies();
			if(totalCopies!=0 || total!=0){
				double value = totalCopies/total*100;
				bar.setValue((int)value);
			}else{
				bar.setValue(0);
			}
		}else if (o instanceof Statistiques){
			/**
			 * CRITERE A DEFINIR
			 */
			bar.setValue(100);
		}
	}
	
	//--------------
	// Actions
	//--------------
}
