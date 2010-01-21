/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;

public class IGAspirateur extends JFrame{
	private static final long serialVersionUID = 1L;

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
		this.setLayout(new BorderLayout(5,5));
		
		// Valeurs par defaut du modele
		laspirateur.setName("TestLMB2");
		
		// Creation des vues
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vueMenu = new VueMenu(laspirateur);
		vueProgressBar = new VueProgressBar(laspirateur);
		vueOnglets = new VueOnglets(laspirateur,vueProgressBar,split);
		vueConsole = new VueConsole(laspirateur);
		vueSauvegarde = new VueSauvegarde(laspirateur, vueOnglets,vueProgressBar,vueConsole);
		
		// Ajout des vues
		JPanel gauche = new JPanel();
		JPanel milieu = new JPanel();
		
		gauche.setLayout(new BorderLayout());
		milieu.setLayout(new BorderLayout());
		split.setTopComponent(vueOnglets);
		split.setBottomComponent(vueConsole);
		milieu.add(split,BorderLayout.CENTER);
		
		gauche.add(vueSauvegarde,BorderLayout.CENTER);
		gauche.add(vueProgressBar,BorderLayout.SOUTH);
		
		add(milieu,BorderLayout.CENTER);
		add(gauche,BorderLayout.WEST);
		setJMenuBar(vueMenu);
		
		// Options pour les vues
		this.vueMenu.setOnglets(vueOnglets);
		this.vueOnglets.getVueCaptureSite().setVueSauvegarde(vueSauvegarde);
		
		// Options de la JFrame
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	
}
