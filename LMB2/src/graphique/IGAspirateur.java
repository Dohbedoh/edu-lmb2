/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;

public class IGAspirateur extends JFrame{

	//------------------
	// Attributs
	//------------------
	Aspirateur laspirateur;
	
	VueMenu vueMenu;
	VueOnglets vueOnglets;
	VueProgressBar vueProgressBar;
	VueConsole vueConsole;
	VueSauvegarde vueSauvegarde;
	
	JSplitPane split;
	//------------------
	// Constructeurs
	//------------------
	public IGAspirateur(Aspirateur laspirateur){
		super("Aspirateur - LMB2");
		
		this.laspirateur = laspirateur;
		
		// Valeurs par defaut du modele
		laspirateur.setName("TestLMB2");
		
		// Creation des vues
		vueMenu = new VueMenu(laspirateur);
		vueProgressBar = new VueProgressBar(laspirateur);
		vueOnglets = new VueOnglets(laspirateur,vueProgressBar);
		vueConsole = new VueConsole(laspirateur);
		vueSauvegarde = new VueSauvegarde(laspirateur);
		
		// Ajout des vues
		JPanel gauche = new JPanel();
		JPanel milieu = new JPanel();
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//JPanel droit = new JPanel();
		
		gauche.setLayout(new BorderLayout());
		
		split.setTopComponent(vueOnglets);
		split.setBottomComponent(vueConsole);
		milieu.add(split);
		
		gauche.add(vueSauvegarde,BorderLayout.CENTER);
		gauche.add(vueProgressBar,BorderLayout.SOUTH);
		
		add(milieu,BorderLayout.CENTER);
		add(gauche,BorderLayout.WEST);
		setJMenuBar(vueMenu);
		
		// Options pour les vues
		this.vueMenu.setConsole(vueConsole);
		
		
		// Options de la JFrame
		//setPreferredSize(new Dimension(800,800));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	
	//------------------
	// Main
	//------------------
	/*
	public static void main(String arg[]){
		Aspirateur laspirateur = new Aspirateur();
		
		// Renaud
		//laspirateur.setPath("/users/renaudmathieu/Desktop/LMB2/");
		
		// Allan
		//laspirateur.setPath("C:/users/renaudmathieu/Desktop/LMB2/");
		
		// Meryem
		//laspirateur.setPath("C:/LMB2/");
		new IGAspirateur(laspirateur);
	}
	*/
}
