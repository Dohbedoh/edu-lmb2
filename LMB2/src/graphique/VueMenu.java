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
	VueOnglets vueOnglets;
	
	JMenu fichier;
	JMenu capture;
	JMenu affichage;
	JMenu about;
	
	JMenuItem quitter;
	
	JMenuItem path;
	JMenuItem aspirerPlay;
	JMenuItem aspirerStop;
	
	JMenuItem reduireConsole;
	JMenuItem grandirConsole;
	
	JMenuItem apropos;
	JMenuItem help;
	
	//------------------
	// Constructeurs
	//------------------
	public VueMenu(Aspirateur laspirateur){
		super();
		this.laspirateur = laspirateur;
		
		// Creation des JMenu
		fichier = new JMenu("Fichier");
		capture = new JMenu("Aspiration");
		affichage = new JMenu("Affichage");
		about = new JMenu("Aide");
	
		// Creation des JMenuItem
		quitter = new JMenuItem("Quitter");
		
		path = new JMenuItem("Changer workspace");
		aspirerPlay = new JMenuItem("Lancer la capture");
		aspirerStop = new JMenuItem("Arreter la capture");
		
		reduireConsole = new JMenuItem("Réduire la console");
		grandirConsole = new JMenuItem("Agrandir la console");
		
		apropos = new JMenuItem("A propos");
		help = new JMenuItem("Aide");
		
		// Options des Menus
		reduireConsole.setAccelerator(KeyStroke.getKeyStroke('M',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		grandirConsole.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		
		quitter.setAccelerator(KeyStroke.getKeyStroke('Q',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		
		path.setAccelerator(KeyStroke.getKeyStroke('W',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		aspirerPlay.setAccelerator(KeyStroke.getKeyStroke('P',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		aspirerStop.setAccelerator(KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false));
		
		// Ajout des JMenuItem
		fichier.add(quitter);
		
		capture.add(path);
		capture.addSeparator();
		capture.add(aspirerPlay);
		capture.add(aspirerStop);
		
		affichage.add(reduireConsole);
		affichage.add(grandirConsole);
		
		about.add(apropos);
		about.addSeparator();
		about.add(help);
		
		// Ajout des JMenu
		add(fichier);
		add(capture);
		add(affichage);
		add(about);
		
		// Ajout des Actions
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		reduireConsole.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vueOnglets.getSplit().setDividerLocation(vueOnglets.getSplit().getMaximumDividerLocation()-10);
			}
		});
		
		grandirConsole.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vueOnglets.getSplit().setDividerLocation(vueOnglets.getSplit().getMinimumDividerLocation()+25);
			}
		});
		
		path.addActionListener(new ActionChangerPath());
		aspirerPlay.addActionListener(new ActionAspirerPlay());
		aspirerStop.addActionListener(new ActionAspirerStop());
		
		apropos.addActionListener(new ActionAbout());
		help.addActionListener(new ActionHelp());
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public void setOnglets(VueOnglets vueOnglets){
		this.vueOnglets = vueOnglets;
	}
	
	//--------------
	// Actions
	//--------------
	private class ActionChangerPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {		
			JFileChooser chooser = new JFileChooser();
        	chooser.setApproveButtonText("Choisir ce repertoire comme workspace...");
        	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	int status = chooser.showOpenDialog(null);

            if (status == JFileChooser.APPROVE_OPTION) {
            	path.setText(chooser.getSelectedFile().getAbsolutePath());
            	laspirateur.setPath(chooser.getSelectedFile().getAbsolutePath());
            	System.out.println("Votre workspace : "+path.getText());
            }
		}
	}
	
		
	private class ActionAspirerPlay implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/**
			 * A FAIRE
			 */
		}
	}

	private class ActionAspirerStop implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/**
			 * A FAIRE
			 */
		}
	}
	
	private class ActionAbout implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/**
			 * A FAIRE
			 */
		}
	}
	
	private class ActionHelp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/**
			 * A FAIRE
			 */
		}
	}
}
