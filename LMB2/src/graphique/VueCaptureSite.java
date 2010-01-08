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
	public JButton parcourir;
	
	// CONTRAINTES
	public JLabel afficheProfondeur;
	
	public JLabel afficheVolume;
	
	public JLabel afficheFiltre;
	public JCheckBox filtrerImage;
	public JCheckBox filtrerTexte;
	
	public VueContraintes contraintesProfondeur;
	public VueContraintes contraintesVolume;
	
	//------------------
	// Constructeurs
	//------------------
	public VueCaptureSite(Aspirateur laspirateur, VueProgressBar vueProgressBar){
		this.laspirateur = laspirateur;
		this.vueProgressBar = vueProgressBar;
		
		laspirateur.addObserver(this);
		this.setLayout(new BorderLayout());
		
		// Creation des elements du panneau
		afficheURL = new JLabel("URL du site : ", SwingConstants.RIGHT);
		afficheNom = new JLabel("Nom du site : ", SwingConstants.RIGHT);
		affichePath = new JLabel("Workspace  : ", SwingConstants.RIGHT);
		
		url = new JTextField(20);
		nom = new JTextField(laspirateur.getName(),20);
		path = new JTextField(laspirateur.getPath(),20);
		
		capturer = new JButton("Capturer");
		
		parcourir = new JButton("...");
		parcourir.setToolTipText("Changer de workspace");
		capturer.setToolTipText("Capturer le site");
		capturer.setForeground(Color.red);
		
		
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
		bas.add(parcourir);
		
		captureTop.add(gauche, BorderLayout.CENTER);
		captureTop.add(droit,BorderLayout.EAST);
		captureTop.add(bas,BorderLayout.SOUTH);
		capture.add(captureTop);
		
		// Ajout des actions
		capturer.addActionListener(new ActionCapturerSite());
		nom.addActionListener(new ActionMAJName());
		path.addActionListener(new ActionMAJPath());
		parcourir.addActionListener(new ActionChangerPath());
		
		//--------------------------------
		// Contraintes
		JPanel contrainte = new JPanel();
		contrainte.setBorder(BorderFactory.createTitledBorder("Contraintes de capture"));
		contrainte.setLayout(new BorderLayout());
		
		JPanel chaut = new JPanel();
		JPanel cbas = new JPanel();
		
		chaut.setLayout(new GridLayout(2,2));
		cbas.setLayout(new GridLayout(2,2));
		
		afficheProfondeur = new JLabel("Profondeur à parcourir en nombre de pages");
		afficheVolume = new JLabel("Volume maximum de données à transférer");
	
		afficheProfondeur.setToolTipText("0 : Pas de profondeur");
		contraintesProfondeur = new VueContraintes(laspirateur);
		contraintesVolume = new VueContraintes(laspirateur);
		
		chaut.add(afficheProfondeur);
		chaut.add(contraintesProfondeur);
		
		chaut.add(afficheVolume);
		chaut.add(contraintesVolume);
	
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
		
				
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		nom.setText(laspirateur.getName());
		path.setText(laspirateur.getPath());
		vueProgressBar.getProgressBar().setValue(5);
		double totalCopies = laspirateur.getNbFichiersCopies();
		double total = totalCopies + laspirateur.getNbFichiersACopies();
		if(totalCopies!=0 || total!=0){
			double value = totalCopies/total*100;
			vueProgressBar.getProgressBar().setValue((int)value);
		}
	}
	
	//------------------
	// Actions
	//------------------
	
	/**
	 * Cette action permet de donner au modele le nom specifie pour la sauvegarde du site
	 */
	private class ActionMAJName implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			laspirateur.setName(nom.getText());
			update(null,null);
		}
	}
	
	/**
	 * Cette action permet de donner au modele le chemin du workspace
	 */
	private class ActionMAJPath implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			laspirateur.setPath(path.getText());
			update(null,null);
		}
	}
	
	/**
	 * Cette action permet de changer le workspace du modele
	 */
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
        	
			update(null,null);
		}
	}
	
	/**
	 * Cette action permet de capturer un site
	 */
	private class ActionCapturerSite implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {

			capturer.setEnabled(false);
			
			// Recuperation des informations sur la capture
			laspirateur.setName(nom.getText());
			laspirateur.setPath(path.getText());
			laspirateur.makeURLLocal();
			
			int profondeur = contraintesProfondeur.getValue();
			int volume = contraintesVolume.getValue();
			
			/*
			 * ALLAN TU VALIDES CA ?
			if(profondeur > 0)
				laspirateur.setProfondeur(profondeur);
			*/
			
			System.out.println("Profondeur : "+profondeur+"- Volume : "+volume);
			
			// Nouveau processus pour lancer le process
			t = new Thread(new Runnable(){

				public void run() {
					laspirateur.launchProcess(url.getText());
					capturer.setEnabled(true);
				}
				
			});
			
			
			// Methode pour faire les filtres
			
			// On lance le premier processus qui lancera le deuxième
			t.start();
		}
	}

	
}
