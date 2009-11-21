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
	
	//------------------
	// Constructeurs
	//------------------
	public IGAspirateur(){
		super("Aspirateur - LMB2");
		
		// Creation du modele
		laspirateur = new Aspirateur();
		
		// Valeurs par defaut du modele
		laspirateur.setName("TestLMB2");
		laspirateur.setPath("/users/renaudmathieu/Desktop/");
		
		// Creation des vues
		vueMenu = new VueMenu(laspirateur);
		vueOnglets = new VueOnglets(laspirateur);
		
		// Ajout des vues
		add(vueOnglets,BorderLayout.CENTER);
		setJMenuBar(vueMenu);
		
		// Options de la JFrame
		//setPreferredSize(new Dimension(800,800));
		pack();
		setVisible(true) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("Coucou");
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
