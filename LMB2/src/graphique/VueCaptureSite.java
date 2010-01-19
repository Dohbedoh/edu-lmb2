/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import images.*;
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
	public VueSauvegarde vueSauvegarde;
	
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
	public JLabel afficheVolumePage;
	public JLabel afficheVolumeRessource;
	
	public VueContraintes contraintesProfondeur;
	public JTextField contraintesVolume;
	public JTextField contraintesVolumePage;
	public JTextField contraintesVolumeRessources;
	
	public VueFiltres vueFiltres;
	public VueMetaDonnees vueMeta;
	public VueOnglets vueOnglets;
	
	//------------------
	// Constructeurs
	//------------------
	public VueCaptureSite(Aspirateur laspirateur, VueProgressBar vueProgressBar, VueOnglets vueOnglets){
		this.laspirateur = laspirateur;
		this.vueProgressBar = vueProgressBar;
		this.vueOnglets = vueOnglets;
		

		contraintesProfondeur = new VueContraintes(laspirateur);
		contraintesProfondeur.setMaximumSize(new Dimension(80,20));
		contraintesVolume = new JTextField("Infini");
		contraintesVolume.setPreferredSize(new Dimension(80,20));
		contraintesVolumePage = new JTextField("Infini");
		contraintesVolumePage.setPreferredSize(new Dimension(80,20));
		contraintesVolumeRessources = new JTextField("Infini");
		contraintesVolumeRessources.setPreferredSize(new Dimension(80,20));
		vueFiltres = new VueFiltres(laspirateur);
		vueMeta = new VueMetaDonnees(laspirateur);
		

		
		laspirateur.addObserver(this);
		this.setLayout(new BorderLayout());
		
		// Creation des elements du panneau
		afficheURL = new JLabel("URL du site : ", SwingConstants.RIGHT);
		afficheNom = new JLabel("Nom du site : ", SwingConstants.RIGHT);
		affichePath = new JLabel("Workspace  : ", SwingConstants.RIGHT);
		
		url = new JTextField();
		nom = new JTextField(laspirateur.getName());
		path = new JTextField(laspirateur.getPath());
		
		capturer = new JButton("Capturer");
		stop = new JButton("Stop");
		pause = new JButton("Pause");
		reprendre = new JButton("Reprendre");
		
		parcourir = new JButton("...");
		parcourir.setToolTipText("Changer de workspace");
		
		capturer.setToolTipText("Capturer le site");
		capturer.setForeground(Color.red);
		stop.setForeground(Color.red);
		pause.setForeground(Color.blue);
		reprendre.setForeground(Color.green);
		
		
		// Ajout des elements du panneau
		JPanel capture = new JPanel();
		Container capGauche = new Container();
		capture.setLayout(new BorderLayout());
		
		GroupLayout layout = new GroupLayout(capGauche);
		capGauche.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(afficheURL)
	                    	.addComponent(afficheNom)
	                    	.addComponent(affichePath)
		                )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(url)
	                    	.addComponent(nom)
	                    	.addComponent(path)
		                )
	                )
	            	.addGroup(layout.createSequentialGroup()
		    	            .addGap(5)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    	.addComponent(capturer)
		                    )
		                    .addGap(5)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			                    .addComponent(stop)
			                )
		                    .addGap(5)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			                    .addComponent(pause)
			                )
		                    .addGap(5)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			                    .addComponent(reprendre)
			                )
		                    .addGap(5)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			                    .addComponent(parcourir)
			                )
			        )
	            	/*.addGroup(layout.createSequentialGroup()
		    	            .addGap(5)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    	.addComponent(vueMeta)
		                    )
			        )*/
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGap(5)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheURL)
	                    	.addComponent(url)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheNom)
	                    	.addComponent(nom)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(affichePath)
	                    	.addComponent(path)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(capturer)
	                    	.addComponent(stop)
	                    	.addComponent(pause)
	                    	.addComponent(reprendre)
	                    	.addComponent(parcourir)
	                )
	                /*.addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(vueMeta)
	                )*/
	          )
	          .addGap(10)
	    );
		
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
		
		Container chaut = new Container();
		
		afficheProfondeur = new JLabel("Profondeur à parcourir en nombre de pages");
		afficheVolume = new JLabel("Taille maximum du site (en Ko)");
		afficheVolumePage = new JLabel("Taille maximum des pages (en Ko)");
		afficheVolumeRessource = new JLabel("Taille maximum des ressources (en Ko)");
		afficheVolume = new JLabel("Taille maximale du site");
		afficheVolumePage = new JLabel("Taille maximale des pages");
		afficheVolumeRessource = new JLabel("Taille maximale des ressources");

	
		afficheProfondeur.setToolTipText("No : Profondeur illimitée");
		afficheVolume.setToolTipText("Infini : Taille illimitée");
		afficheVolumePage.setToolTipText("Infini : Taille illimitée");
		afficheVolumeRessource.setToolTipText("Infini : Taille illimitée");
	    
		GroupLayout layoutContraintes = new GroupLayout(chaut);
		chaut.setLayout(layoutContraintes);
		layoutContraintes.setHorizontalGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layoutContraintes.createParallelGroup()
	            	.addGroup(layoutContraintes.createSequentialGroup()
	    	            .addGap(5)
	                    .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(afficheProfondeur)
	                    	.addComponent(afficheVolume)	                    	
	                    	.addComponent(afficheVolumePage)
	                    	.addComponent(afficheVolumeRessource)
		                )
	    	            .addGap(5)
	                    .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(contraintesProfondeur)
	                    	.addComponent(contraintesVolume)
	                    	.addComponent(contraintesVolumePage)
	                    	.addComponent(contraintesVolumeRessources)
		                )
	                )
	            )
	    );
	    
	    layoutContraintes.setVerticalGroup(layoutContraintes.createSequentialGroup()
	            .addGap(5)
	            .addGroup(layoutContraintes.createSequentialGroup()
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheProfondeur)
	                    	.addComponent(contraintesProfondeur)
	                )
	                .addGap(5)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheVolume)
	                    	.addComponent(contraintesVolume)
	                )
	                .addGap(5)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheVolumePage)
	                    	.addComponent(contraintesVolumePage)
	                )
	                .addGap(5)
	                .addGroup(layoutContraintes.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(afficheVolumeRessource)
	                    	.addComponent(contraintesVolumeRessources)
	                )
	             )
	    );
	    
		Container north = new Container();
		north.setLayout(new BorderLayout());
		north.add(capGauche, BorderLayout.CENTER);
		north.add(vueMeta, BorderLayout.SOUTH);
		
		contrainte.add(chaut,BorderLayout.EAST);
		contrainte.add(vueFiltres,BorderLayout.CENTER);
		capture.add(north, BorderLayout.CENTER);
		
		add(capture,BorderLayout.NORTH);
		add(contrainte,BorderLayout.CENTER);
	}//cons-1
	
	
	public void setEnabled(boolean b){
		pause.setEnabled(b);
		reprendre.setEnabled(b);
		stop.setEnabled(b);
		capturer.setEnabled(b);
		parcourir.setEnabled(b);
	}
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		nom.setText(laspirateur.getName());
		path.setText(laspirateur.getPath());
		//vueProgressBar.getProgressBar().setValue(5);
		/*double totalCopies = laspirateur.getNbFichiersCopies();
		double total = totalCopies + laspirateur.getNbFichiersACopies();
		if(totalCopies!=0 || total!=0){
			double value = totalCopies/total*100;
			vueProgressBar.getProgressBar().setValue((int)value);
		}else{
			vueProgressBar.getProgressBar().setValue(0);
		}*/
	}

	public VueSauvegarde getVueSauvegarde() {
		return vueSauvegarde;
	}

	public void setVueSauvegarde(VueSauvegarde vueSauvegarde) {
		this.vueSauvegarde = vueSauvegarde;
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
			
			int profondeur = contraintesProfondeur.getValue();
			int tailleSite = -1;
			int taillePage = -1;
			int tailleRessources = -1;
			
			if(contraintesVolume.getText().matches("[0-9]*")){
				tailleSite = Integer.parseInt(contraintesVolume.getText());
			}else{
				if(!contraintesVolume.getText().equals("Infini")){
					tailleSite = -2;
				}
			}
			
			if(contraintesVolumePage.getText().matches("[0-9]*")){
				tailleSite = Integer.parseInt(contraintesVolume.getText());
			}else{
				if(!contraintesVolumePage.getText().equals("Infini")){
					tailleSite = -2;
				}
			}
			

			if(contraintesVolumeRessources.getText().matches("[0-9]*")){
				tailleSite = Integer.parseInt(contraintesVolume.getText());
			}else{
				if(!contraintesVolumeRessources.getText().equals("Infini")){
					tailleSite = -2;
				}
			}
			
			if(tailleSite!=-2 && taillePage!=-2 && tailleRessources!=-2){

				capturer.setEnabled(false);
				stop.setEnabled(true);
				pause.setEnabled(true);
				reprendre.setEnabled(false);
				vueMeta.setEnabled(false);
				vueOnglets.getVueStatistiques().setEnabled(false);
				
				// Recuperation des informations sur la capture
				laspirateur.setName(nom.getText());
				laspirateur.setPath(path.getText());
				laspirateur.makeURLLocal();
				laspirateur.setMeta(vueMeta.getValeur().getText());
				
				laspirateur.setProfondeur(profondeur);
				//System.err.println("Profondeur : "+profondeur+"- Volume : "+volume);
				
				// Methode pour faire les filtres
				
				ArrayList<String> lesFiltres = vueFiltres.getListeFiltres();
				laspirateur.setFiltres(lesFiltres);
				
				
				// Nouveau processus pour lancer le process
				t = new Thread(new Runnable(){
	
					public void run() {
						laspirateur.launchProcess(url.getText());
						capturer.setEnabled(true);
						vueMeta.setEnabled(true);
						vueOnglets.setEnabled(true);
						vueSauvegarde.refresh();
					}
					
				});
				
				t.start();
			}else{
				JOptionPane.showMessageDialog(null,"Vous avez saisi des tailes invalides.","Attention",JOptionPane.WARNING_MESSAGE);
			}
			
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
