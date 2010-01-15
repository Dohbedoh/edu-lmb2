/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */
package graphique;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import statistiques.*;

public class VueInfosStatistiques extends JPanel {

	//------------------
	// Attributs
	//------------------
	Statistiques stats;
	
	JLabel nomSite;
	JLabel dateVersion;
	JLabel tailleVersion;
	JLabel nomSiteLab;
	JLabel dateVersionLab;
	JLabel tailleVersionLab;
	
	//------------------
	// Constructeur
	//------------------
	public VueInfosStatistiques(Statistiques stats){
		this.stats = stats;
		
		setBorder(BorderFactory.createTitledBorder("Informations sur la sauvegarde"));
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		// Création des éléments graphiques
		/*nomSite = new JLabel(stats.getNomSite());
		dateVersion = new JLabel(stats.getDate());
		tailleVersion = new JLabel(stats.getLength()+"bytes");*/
		
		nomSiteLab = new JLabel("• Nom de Site : ");
		dateVersionLab = new JLabel("• Date d'Aspiration : ");
		tailleVersionLab = new JLabel("• Taille du Site : ");
		
		nomSite = new JLabel(" N.C");
		dateVersion = new JLabel(" N.C");
		tailleVersion = new JLabel(" N.C");
		
		nomSite.setForeground(new Color(51,204,0));
		dateVersion.setForeground(new Color(51,204,0));
		tailleVersion.setForeground(new Color(51,204,0));
		
		nomSiteLab.setFont(new Font(null,1,11));
		dateVersionLab.setFont(new Font(null,1,11));
		tailleVersionLab.setFont(new Font(null,1,11));
		nomSite.setFont(new Font(null,1,11));
		dateVersion.setFont(new Font(null,1,11));
		tailleVersion.setFont(new Font(null,1,11));
		
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
	                    )
	                    //le groupe des nbs
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addComponent(nomSite)
		                    .addComponent(dateVersion)
		                    .addComponent(tailleVersion)
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
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(dateVersionLab)
		                    .addComponent(dateVersion)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(tailleVersionLab)
		                    .addComponent(tailleVersion)
	                )
	          )
	          .addGap(10)
	    );
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	/*
	public void update(Observable o, Object arg) {
		nomSite.setText(stats.getNomSite());
		System.out.println(stats.getNomSite());
	}
	*/
	
	public static void main(String[] args){
		JFrame fp = new JFrame("VueInfosStatistiques");
		Statistiques stats = new Statistiques(new File("/Users/renaudmathieu/Desktop/LMB2/TestLMB2/1262958433470"));
		
		fp.add(new VueInfosStatistiques(stats));
		fp.pack();
		fp.setVisible(true);
		fp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
