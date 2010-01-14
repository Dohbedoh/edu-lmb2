/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */
package graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import statistiques.*;

public class VueInfosStatistiques extends JPanel {

	//------------------
	// Attributs
	//------------------
	Statistiques stats;
	
	JLabel nomSite;
	JLabel dateVersion;
	JLabel tailleVersion;
	
	//------------------
	// Constructeur
	//------------------
	public VueInfosStatistiques(Statistiques stats){
		this.stats = stats;
		
		// Création des éléments graphiques
		nomSite = new JLabel(stats.getNomSite());
		dateVersion = new JLabel(stats.getDate());
		tailleVersion = new JLabel(stats.getLength()+"bytes"); // Octets ?
		
		// Options
		
		
		// Ajout des éléments graphiques
		/**
		 * DISPOSER CORRECTEMENT
		 */
		add(nomSite);
		add(dateVersion);
		add(tailleVersion);
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	public static void main(String[] args){
		JFrame fp = new JFrame("VueInfosStatistiques");
		Statistiques stats = new Statistiques(new File("/Users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470"));
		fp.add(new VueInfosStatistiques(stats));
		fp.pack();
		fp.setVisible(true);
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
