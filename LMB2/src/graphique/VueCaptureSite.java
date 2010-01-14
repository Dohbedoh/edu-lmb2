/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
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
	public JButton stop;
	public JButton pause;
	public JButton reprendre;
	public JButton parcourir;
	
	// CONTRAINTES
	public JLabel afficheProfondeur;
	
	public JLabel afficheVolume;
	
	public VueContraintes contraintesProfondeur;
	public VueContraintes contraintesVolume;
	
	public VueFiltres vueFiltres;
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
		stop = new JButton("Stop");
		pause = new JButton("Pause");
		reprendre = new JButton("Reprendre");
		
		parcourir = new JButton("...");
		parcourir.setPreferredSize(new Dimension(20,20));
		parcourir.setToolTipText("Changer de workspace");
		
		capturer.setToolTipText("Capturer le site");
		capturer.setForeground(Color.red);
		stop.setForeground(Color.red);
		pause.setForeground(Color.blue);
		reprendre.setForeground(Color.green);
		
		
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
		bas.add(stop);
		bas.add(pause);
		bas.add(reprendre);
		bas.add(parcourir);
		
		captureTop.add(gauche, BorderLayout.CENTER);
		captureTop.add(droit,BorderLayout.EAST);
		captureTop.add(bas,BorderLayout.SOUTH);
		capture.add(captureTop);
		
		
		TitledBorder afact = BorderFactory.createTitledBorder("Données");
		afact.setTitleJustification(TitledBorder.CENTER);
		capture.setBorder(afact);
		
		// Ajout des actions
		capturer.addActionListener(new ActionCapturerSite());
		stop.addActionListener(new ActionStopAsiprateur());
		pause.addActionListener(new ActionPauseAsiprateur());
		reprendre.addActionListener(new ActionResumeAsiprateur());
		nom.addActionListener(new ActionMAJName());
		path.addActionListener(new ActionMAJPath());
		parcourir.addActionListener(new ActionChangerPath());
		
		pause.setEnabled(false);
		reprendre.setEnabled(false);
		
		//--------------------------------
		// Contraintes
		JPanel contrainte = new JPanel();
		
		TitledBorder bfact = BorderFactory.createTitledBorder("Contraintes de capture");
		bfact.setTitleJustification(TitledBorder.CENTER);
		contrainte.setBorder(bfact);
		contrainte.setLayout(new BorderLayout());
		
		JPanel chaut = new JPanel();
		
		afficheProfondeur = new JLabel("Profondeur à parcourir en nombre de pages");
		afficheVolume = new JLabel("Volume maximum de données à transférer");
	
		afficheProfondeur.setToolTipText("-1 : Profondeur illimitée");
		afficheVolume.setToolTipText("-1 : Volume illimité");
		
		contraintesProfondeur = new VueContraintes(laspirateur);
		contraintesVolume = new VueContraintes(laspirateur);
		
		JPanel cgauche = new JPanel(new GridLayout(2,1));
		cgauche.add(afficheProfondeur);
		cgauche.add(afficheVolume);
		
		((GridLayout)cgauche.getLayout()).setVgap(10);
		
		JPanel cdroit = new JPanel(new GridLayout(2,1));
		cdroit.add(contraintesProfondeur);
		cdroit.add(contraintesVolume);
	
		chaut.add(cgauche);
		chaut.add(cdroit);
		
		vueFiltres = new VueFiltres(laspirateur);
		
		contrainte.add(chaut,BorderLayout.NORTH);
		contrainte.add(vueFiltres,BorderLayout.CENTER);
		
		add(capture,BorderLayout.CENTER);
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
		}else{
			vueProgressBar.getProgressBar().setValue(0);
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
			stop.setEnabled(true);
			pause.setEnabled(true);
			reprendre.setEnabled(false);
			
			// Recuperation des informations sur la capture
			laspirateur.setName(nom.getText());
			laspirateur.setPath(path.getText());
			laspirateur.makeURLLocal();
			
			int profondeur = contraintesProfondeur.getValue();
			int volume = contraintesVolume.getValue();
			
			laspirateur.setProfondeur(profondeur);
			System.err.println("Profondeur : "+profondeur+"- Volume : "+volume);
			
			// Methode pour faire les filtres
			
			ArrayList<String> lesFiltres = vueFiltres.getListeFiltres();
			laspirateur.setFiltres(lesFiltres);
			
			
			// Nouveau processus pour lancer le process
			t = new Thread(new Runnable(){

				public void run() {
					laspirateur.launchProcess(url.getText());
					capturer.setEnabled(true);
				}
				
			});
			
			
			
			
			// On lance le premier processus qui lancera le deuxième
			t.start();
		}
	}

	/**
	 * Cette action permet stopper le parser
	 */
	private class ActionStopAsiprateur implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			if(!laspirateur.isStopped()){
				laspirateur.stop();
			}
			pause.setEnabled(false);
			reprendre.setEnabled(false);
		}
	}
	
	/**
	 * Cette action permet de mettre en pause le parser
	 */
	private class ActionPauseAsiprateur implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			laspirateur.suspend();
			reprendre.setEnabled(true);
			pause.setEnabled(false);
		}
	}
	
	/**
	 * Cette action permet de reprendre le parser mis en pause
	 */
	private class ActionResumeAsiprateur implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			laspirateur.reprendre();
			pause.setEnabled(true);
			reprendre.setEnabled(false);
		}
	}
}
