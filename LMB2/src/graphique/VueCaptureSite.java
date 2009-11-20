/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import Aspirateur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VueCaptureSite extends JPanel{

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	// CAPTURE
	public JLabel afficheURL;
	public JTextField url;
	
	public JLabel afficheNom;
	public JTextField nom;
	
	public JLabel affichePath;
	public JTextField path;
	
	public JButton capturer;
	
	// CONTRAINTE
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
	public VueCaptureSite(Aspirateur laAspirateur){
		this.laspirateur = laAspirateur;
		
		this.setLayout(new BorderLayout());
		
		// Creation des elements du panneau
		afficheURL = new JLabel("URL : ", SwingConstants.RIGHT);
		afficheNom = new JLabel("Nom : ", SwingConstants.RIGHT);
		affichePath = new JLabel("Chemin : ", SwingConstants.RIGHT);
		
		url = new JTextField("http://www.renaudmathieu.fr/lmb2/",20);
		nom = new JTextField("Test",20);
		path = new JTextField("/users/renaudmathieu/Desktop/",20);
				
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
	private class ActionCapturerSite implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
		
			laspirateur.setName(nom.getText());
			laspirateur.launchProcess(url.getText());
			laspirateur.setLocal(path.getText());
			laspirateur.launchCopy();
			
			/* Methode pour faire les filtres ? */
		}
	}
}
