/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;

import aide.VueAide;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


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
	JMenu apparence;
	
	ArrayList<JMenuItem> lesSkins;
	
	JMenuItem apropos;
	JMenuItem help;
	
	JFrame IGA;
	
	//------------------
	// Constructeurs
	//------------------
	public VueMenu(Aspirateur laspirateur, JFrame IGA){
		super();
		this.laspirateur = laspirateur;
		this.IGA = IGA;
		
		lesSkins = new ArrayList<JMenuItem>();
		/** Les skins possibles */
		lesSkins.add(new JMenuItem("Autumn"));
		lesSkins.add(new JMenuItem("Business"));
		lesSkins.add(new JMenuItem("Business Black Steel"));
		lesSkins.add(new JMenuItem("Business Blue Steel"));
		lesSkins.add(new JMenuItem("Creme"));
		lesSkins.add(new JMenuItem("Creme Coffee"));
		lesSkins.add(new JMenuItem("Mist Aqua"));
		lesSkins.add(new JMenuItem("Mist Silver"));
		lesSkins.add(new JMenuItem("Moderate"));
		lesSkins.add(new JMenuItem("Nebula"));
		lesSkins.add(new JMenuItem("Nebula Brick Wall"));
		lesSkins.add(new JMenuItem("Office Blue 2007"));
		lesSkins.add(new JMenuItem("Office Silver 2007"));
		lesSkins.add(new JMenuItem("Sahara"));
		lesSkins.get(2).setEnabled(false);
		
		for(int i=0; i<lesSkins.size(); i++){
			lesSkins.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(((JMenuItem)arg0.getSource()).isEnabled()){
				        try {
				        	UIManager.setLookAndFeel("org.jvnet.substance.skin.Substance"+((JMenuItem)arg0.getSource()).getText().replaceAll(" ","")+"LookAndFeel");
				        	VueMenu.this.IGA.repaint();
				        	for(int i=0; i<lesSkins.size(); i++){
				        		lesSkins.get(i).setEnabled(true);
				        	}
				        	((JMenuItem)arg0.getSource()).setEnabled(false);
				        } catch (Exception e) {
				        	e.printStackTrace();
				        	System.out.println("Substance Raven Graphite failed to initialize");
				        	System.out.println("org.jvnet.substance.skin.Substance"+((JMenuItem)arg0.getSource()).getText().replaceAll(" ","")+"LookAndFeel");
				        }
					}
				}
			});
		}
		
		
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
		apparence = new JMenu("Apparence");
		
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
		
		for(int i=0; i<lesSkins.size(); i++){
			apparence.add(lesSkins.get(i));
		}
		
		affichage.add(reduireConsole);
		affichage.add(grandirConsole);
		affichage.add(apparence);
		
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
			JOptionPane.showMessageDialog(null,"Eggs are not supposed to be green.");
		}
	}

	private class ActionAspirerStop implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Not Yet Implemented.");
		}
	}
	
	private class ActionAbout implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String message = "Aspirateur - LMB2" +
					"\n\t\t >Besluau Gregoire\n\t\t >Burdajewicz Allan \n\t\t >Laraki Meryem \n\t\t >Mathieu Renaud";
			JOptionPane.showMessageDialog(null,
					message,
					"A propos",
					JOptionPane.INFORMATION_MESSAGE,
				    new ImageIcon(this.getClass().getClassLoader().getResource("logo.png"))
			);
		}
	}
	
	private class ActionHelp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new VueAide();
		}
	}
}
