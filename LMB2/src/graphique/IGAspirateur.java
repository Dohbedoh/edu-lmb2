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
	
	//------------------
	// Constructeurs
	//------------------
	public IGAspirateur(){
		super("Aspirateur - LMB2");
		
		// Creation du modele
		laspirateur = new Aspirateur();
		
		// Valeurs par defaut du modele
		laspirateur.setName("TestLMB2");
		//laspirateur.setPath("/users/renaudmathieu/Desktop/LMB2/");
		laspirateur.setPath("C:/users/renaudmathieu/Desktop/LMB2/");
		
		// Creation des vues
		vueMenu = new VueMenu(laspirateur);
		vueProgressBar = new VueProgressBar(laspirateur);
		vueOnglets = new VueOnglets(laspirateur,vueProgressBar);
		vueConsole = new VueConsole(laspirateur);
		vueSauvegarde = new VueSauvegarde(laspirateur);
		
		// Ajout des vues
		JPanel gauche = new JPanel();
		JPanel milieu = new JPanel();
		//JPanel droit = new JPanel();
		
		milieu.setLayout(new BorderLayout());
		gauche.setLayout(new BorderLayout());
		
		milieu.add(vueOnglets,BorderLayout.NORTH);
		milieu.add(vueConsole,BorderLayout.CENTER);
		
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
		setVisible(true) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	
	//------------------
	// Main
	//------------------
	public static void main(String arg[]){
		new IGAspirateur();
	}
	
}
