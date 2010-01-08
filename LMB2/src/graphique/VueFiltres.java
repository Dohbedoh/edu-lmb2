/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import Aspirateur.*;

public class VueFiltres extends JPanel{

	
	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	public JLabel afficheFiltre;
	
	//------------------
	// Contructeur
	//------------------
	public VueFiltres(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		this.setLayout(new BorderLayout());
		
		// Creation des elements graphiques
		afficheFiltre = new JLabel("Filtres");
		
		// Ajout des elements dans le conteneur
		add(afficheFiltre,BorderLayout.NORTH);
		
		// Ajout des actions
		
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public static void main(String[] args){
		JFrame fp = new JFrame("Test");
		fp.add(new VueFiltres(new Aspirateur()));
		fp.pack();
		fp.setVisible(true);
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
