/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */
package statistiques;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import statistiques.*;

public class VueInfosStatistiques extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	Statistiques stats;
	
	JLabel nomSite;
	JLabel dateVersion;
	JLabel tailleVersion;
	JLabel url;
	JLabel time;
	JTextArea metadata;
	JLabel nomSiteLab;
	JLabel dateVersionLab;
	JLabel tailleVersionLab;
	JLabel urlLab;
	JLabel metadataLab;
	JLabel timeLab;
	
	public JScrollPane scroll;
	
	//------------------
	// Constructeur
	//------------------
	public VueInfosStatistiques(Statistiques stats){
		this.stats = stats;
		TitledBorder afact = BorderFactory.createTitledBorder("Informations sur la sauvegarde");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		this.setLayout(new BorderLayout());
		
		Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);
		
		// Cr�ation des �l�ments graphiques
		
		nomSiteLab = new JLabel("� Nom de Site : ");
		dateVersionLab = new JLabel("� Date d'Aspiration : ");
		tailleVersionLab = new JLabel("� Taille du Site : ");
		urlLab = new JLabel("� URL : ");
		metadataLab = new JLabel("� MetaDonn�es : ");
		timeLab = new JLabel("� Time : ");
		
		nomSite = new JLabel("N.C");
		dateVersion = new JLabel("N.C");
		tailleVersion = new JLabel("N.C");
		url = new JLabel("N.C");
		time = new JLabel("N.C");
		metadata = new JTextArea("N.C");
		/*metadata.setLineWrap(true);
		metadata.setWrapStyleWord(true);*/
		metadata.setEditable(false);
		
		nomSite.setForeground(new Color(51,204,0));
		dateVersion.setForeground(new Color(51,204,0));
		tailleVersion.setForeground(new Color(51,204,0));
		url.setForeground(new Color(51,204,0));
		metadata.setForeground(new Color(51,204,0));
		time.setForeground(new Color(51,204,0));
		
		nomSiteLab.setFont(new Font(null,1,11));
		dateVersionLab.setFont(new Font(null,1,11));
		tailleVersionLab.setFont(new Font(null,1,11));
		urlLab.setFont(new Font(null,1,11));
		metadataLab.setFont(new Font(null,1,11));
		timeLab.setFont(new Font(null,1,11));
		nomSite.setFont(new Font(null,1,11));
		dateVersion.setFont(new Font(null,1,11));
		tailleVersion.setFont(new Font(null,1,11));
		url.setFont(new Font(null,1,11));
		metadata.setFont(new Font(null,1,11));
		time.setFont(new Font(null,1,11));
		
		scroll = new JScrollPane(metadata);
		// Options
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(nomSiteLab)
	                        .addComponent(dateVersionLab)
	                        .addComponent(tailleVersionLab)
	                        .addComponent(urlLab)
	                        .addComponent(timeLab)
	                        .addComponent(metadataLab)
	                    )
	                    //le groupe des nbs
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addComponent(nomSite)
		                    .addComponent(dateVersion)
		                    .addComponent(tailleVersion)
		                    .addComponent(url)
		                    .addComponent(time)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGap(10)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(nomSiteLab)
	                		.addComponent(nomSite)
	                )
	                .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(dateVersionLab)
		                    .addComponent(dateVersion)
	                )
	                .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(tailleVersionLab)
		                    .addComponent(tailleVersion)
	                )
	                .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(urlLab)
		                    .addComponent(url)
	                )
	                .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(timeLab)
		                	.addComponent(time)
	                )
	                .addGap(2)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(metadataLab)
	                )
	          )
	          .addGap(10)
	    );
	    
	    this.add(cont, BorderLayout.CENTER);
	    this.add(scroll, BorderLayout.SOUTH);
	    
	  //Cr�ation de la JScrollPane
		metadata.setRows(3);
		
	    
	}//cons-1
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	//------------------
	// M�thodes
	//------------------
	public void update(Observable o, Object arg) {
		nomSite.setText(stats.getNomSite());
		dateVersion.setText(stats.getDate());
		tailleVersion.setText(stats.getLength());
		url.setText(stats.getMetaData().getURL());
		long temp = stats.getMetaData().getTime();
		int nbH = (int) temp / 3600000;
		int nbM = (int) (temp - nbH * 3600000) / 60000;
		int nbS = (int) (temp - (nbH * 3600000 + nbM * 60000)) / 1000;
		int nbMS = (int) (temp - (nbH * 3600000 + nbM * 60000 + nbS * 1000));
		time.setText(nbH + "h " + nbM + "min " + nbS + "s " + nbMS + "ms");
		if (!stats.getMetaData().getMetaData().equals(
				"<Entrez vos commentaires>")
				&& stats.getMetaData().getMetaData().length() != 0) {
			metadata.setText(stats.getMetaData().getMetaData());
		} else {
			metadata.setText("N.C");
		}
	}
	
}
