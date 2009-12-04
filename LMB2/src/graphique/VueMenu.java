/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VueMenu extends JMenuBar {

	//------------------
	// Attributs
	//------------------
	Aspirateur laspirateur;
	VueConsole laConsole;
	
	JMenu fichier;
	JMenu capture;
	JMenu affichage;
	
	JMenuItem quitter;
	JMenuItem path;
	JMenuItem console;
	
	//------------------
	// Constructeurs
	//------------------
	public VueMenu(Aspirateur laspirateur){
		super();
		this.laspirateur = laspirateur;
		
		// Creation des JMenu
		fichier = new JMenu("Fichier");
		capture = new JMenu("Capturer Site");
		affichage = new JMenu("Affichage");
	
		// Creation des JMenuItem
		quitter = new JMenuItem("Quitter");
		path = new JMenuItem("Changer path de sauvegarde");
		console = new JMenuItem("Afficher la console");
		
		// Ajout des JMenuItem
		fichier.add(quitter);
		capture.add(path);
		affichage.add(console);
		
		// Ajout des JMenu
		add(fichier);
		add(capture);
		add(affichage);
		
		// Ajout des Actions
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		console.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(laConsole.isVisible()){
					laConsole.setVisible(false);
				}else{
					laConsole.setVisible(true);
				}
			}
		});
		
		path.addActionListener(new ActionChangerPath());
		
	}
	
	//------------------
	// Methodes
	//------------------
	public void setConsole(VueConsole uneConsole){
		this.laConsole = uneConsole;
	}
	
	//--------------
	// Actions
	//--------------
	private class ActionChangerPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
						
		    String result = JOptionPane.showInputDialog(null, "Entrer le chemin de votre repertoire de sauvegarde");
		    if(result != null)
		    	laspirateur.setLocal(result);
		}
	}
	
}
