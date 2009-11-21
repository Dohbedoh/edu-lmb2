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
	
	JMenu fichier;
	JMenu capture;
	
	JMenuItem quitter;
	JMenuItem path;

	//------------------
	// Constructeurs
	//------------------
	public VueMenu(Aspirateur laspirateur){
		super();
		this.laspirateur = laspirateur;
		
		// Creation des JMenu
		fichier = new JMenu("Fichier");
		capture = new JMenu("Capturer Site");
	
		// Creation des JMenuItem
		quitter = new JMenuItem("Quitter");
		path = new JMenuItem("Changer path de sauvegarde");
		
		// Ajout des JMenuItem
		fichier.add(quitter);
		capture.add(path);
		
		// Ajout des JMenu
		add(fichier);
		add(capture);
		
		// Ajout des Actions
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		path.addActionListener(new ActionChangerPath());
	}
	
	//------------------
	// Methodes
	//------------------
	
	//--------------
	// Actions
	//--------------
	private class ActionChangerPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
		    String result = JOptionPane.showInputDialog(null, "Entrer le chemin de votre repertoire de sauvegarde");

		    System.out.println(result);
			laspirateur.setLocal(result);
		}
	}
	
}
