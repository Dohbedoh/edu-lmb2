/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VueCaptureSite extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	public VueProgressBar vueProgressBar;
	private Thread t;
	
	// CAPTURE
	public JLabel afficheURL;
	public JTextField url;
	
	public JLabel afficheNom;
	public JTextField nom;
	
	public JLabel affichePath;
	public JTextField path;
	
	public JButton capturer;
	
	// CONTRAINTES
	public JLabel afficheProfondeur;
	public JTextField profondeur;
	
	public JLabel afficheVolume;
	public JTextField volume;
	
	public JLabel afficheFiltre;
	public JCheckBox filtrerImage;
	public JCheckBox filtrerTexte;
	
	//------------------
	// Constructeurs
	//------------------
	public VueCaptureSite(Aspirateur laspirateur, VueProgressBar vueProgressBar){
		this.laspirateur = laspirateur;
		this.vueProgressBar = vueProgressBar;
		
		laspirateur.addObserver(this);
		this.setLayout(new BorderLayout());
		
		// Creation des elements du panneau
		afficheURL = new JLabel("URL : ", SwingConstants.RIGHT);
		afficheNom = new JLabel("Nom : ", SwingConstants.RIGHT);
		affichePath = new JLabel("Chemin : ", SwingConstants.RIGHT);
				
		url = new JTextField(20);
		nom = new JTextField(laspirateur.getName(),20);
		path = new JTextField(laspirateur.getPath(),20);
		
		capturer = new JButton("Capturer");
		
		// Ajout des elements du panneau
		JPanel capture = new JPanel();
		JPanel captureTop = new JPanel();
		JPanel gauche = new JPanel();
		JPanel droit = new JPanel();
		JPanel bas = new JPanel();
		
		captureTop.setLayout(new BorderLayout());
		gauche.setLayout(new GridLayout(3,1));
		droit.setLayout(new GridLayout(3,1));
		
		((GridLayout)gauche.getLayout()).setVgap(10);
		
		gauche.add(afficheURL);
		droit.add(url);
		
		gauche.add(afficheNom);
		droit.add(nom);
		
		gauche.add(affichePath);
		droit.add(path);
		
		bas.add(capturer);
		
		captureTop.add(gauche, BorderLayout.CENTER);
		captureTop.add(droit,BorderLayout.EAST);
		captureTop.add(bas,BorderLayout.SOUTH);
		capture.add(captureTop);
		
		// Ajout des actions
		capturer.addActionListener(new ActionCapturerSite());
		nom.addActionListener(new ActionMAJName());
		path.addActionListener(new ActionMAJPath());
		
		//--------------------------------
		// Contraintes
		JPanel contrainte = new JPanel();
		contrainte.setBorder(BorderFactory.createTitledBorder("Contraintes de capture"));
		contrainte.setLayout(new BorderLayout());
		
		JPanel chaut = new JPanel();
		JPanel cbas = new JPanel();
		
		chaut.setLayout(new GridLayout(2,2));
		cbas.setLayout(new GridLayout(2,2));
		
		afficheProfondeur = new JLabel("Profondeur a parcourir en nombre de pages");
		profondeur = new JTextField(2);
		
		afficheVolume = new JLabel("Volume maximum de donnees a transferer");
		volume = new JTextField(2);
		
		chaut.add(afficheProfondeur);
		chaut.add(profondeur);
		chaut.add(afficheVolume);
		chaut.add(volume);
		
		afficheFiltre = new JLabel("Filtres");
		filtrerImage = new JCheckBox("Filtrer les images");
		filtrerTexte  = new JCheckBox("Filtrer le texte");		
		
		cbas.add(afficheFiltre);
		cbas.add(filtrerImage);
		cbas.add(new JLabel(""));
		cbas.add(filtrerTexte);
		
		contrainte.add(chaut,BorderLayout.NORTH);
		contrainte.add(cbas,BorderLayout.SOUTH);
		
		add(capture,BorderLayout.NORTH);
		add(contrainte,BorderLayout.SOUTH);
		
		profondeur.setText("Not Yet Implemented");
		volume.setText("Not Yet Implemented");
				
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		nom.setText(laspirateur.getName());
		path.setText(laspirateur.getPath());
		
		System.out.println(laspirateur.getNbPagesCopiees());
	}
	
	//------------------
	// Actions
	//------------------
	private class ActionMAJName implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			laspirateur.setName(nom.getText());
			update(null,null);
		}
	}
	
	private class ActionMAJPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			laspirateur.setPath(path.getText());
			update(null,null);
		}
	}
	
	private class ActionCapturerSite implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {

			capturer.setEnabled(false);
			laspirateur.setName(nom.getText());
			laspirateur.setPath(path.getText());
			laspirateur.makeURLLocal();
			
			/* Nouveau processus pour lancer le process */
			t = new Thread(new Runnable(){

				@Override
				public void run() {
					laspirateur.launchProcess(url.getText());

					/* Nouveau processus pour lancer la copie */
					Thread tcopy = new Thread(new Runnable(){

						@Override
						public void run() {
							laspirateur.launchCopy();
							capturer.setEnabled(true);
						}
						
					});
					if(!SwingUtilities.isEventDispatchThread()){
						tcopy.start();
					}else{
						/* On lance la copy une fois que le process est terminé (processus précédent) */
						SwingUtilities.invokeLater(tcopy);
					}
				}
				
			});
			/* Methode pour faire les filtres ?*/
			
			/* On lance le premier processus qui lancera le deuxième */
			t.start();
		}
	}

	

	/*private class ActionCapturerSite implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
		
			laspirateur.setName(nom.getText());
			laspirateur.setPath(path.getText());
			laspirateur.makeURLLocal();
			
			laspirateur.launchProcess(url.getText());
			laspirateur.launchCopy();
			
			t = new Thread(new Traitement());
			t.start();
		}
	}*/
	
	/*public class Traitement implements Runnable{
		
		public void run(){
			for(int val = 0; val <= 500; val++){
				vueProgressBar.getProgressBar().setValue(val);
				try {
					t.sleep(1);
				} catch (InterruptedException e) { e.printStackTrace();}
			}
		}	
	}*/
	
}
