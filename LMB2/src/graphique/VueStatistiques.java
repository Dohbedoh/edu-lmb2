/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class VueStatistiques extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	public JLabel afficheURL;
	
	//------------------
	// Constructeurs
	//------------------
	public VueStatistiques(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		
		laspirateur.addObserver(this);
		// Creation des elements graphique
		afficheURL = new JLabel();
		
		// Ajout des elements
		add(afficheURL);
		
		update(null,null);
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		afficheURL.setText(laspirateur.getName());
	}
}
