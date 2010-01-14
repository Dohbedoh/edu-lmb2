/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import statistiques.Statistiques;


public class VueAnalyseInfos extends JPanel {
	
	private Statistiques stats;
	private JLabel 	nbMotsDif, nbAddrDif, nbImagesDif, nbHypersDif,
					divers, css, js, html,
					motsDif, addrDif, imagesDif, hypersDif,
					nbCSS, nbJS, nbHTML;

	public VueAnalyseInfos(Statistiques stats){
		this.stats = stats;
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		motsDif = new JLabel("• Mots différents sur le site : ");
		addrDif = new JLabel("• Adresses mail : ");
		imagesDif = new JLabel("• Images : ");
		hypersDif = new JLabel("• Liens hypertexte : ");
		divers = new JLabel("• Divers : ");
		css = new JLabel("• Fichiers CSS : ");
		js = new JLabel("• Fichiers JS : ");
		html = new JLabel("• Fichiers HTML : ");
		
		nbMotsDif = new JLabel(" 00");
		nbAddrDif = new JLabel(" 0");
		nbImagesDif = new JLabel(" 000");
		nbHypersDif = new JLabel(" 00");
		nbCSS = new JLabel(" 0");
		nbJS = new JLabel(" 00");
		nbHTML = new JLabel(" 0");
		
		nbMotsDif.setForeground(Color.RED);
		nbAddrDif.setForeground(Color.RED);
		nbImagesDif.setForeground(Color.RED);
		nbHypersDif.setForeground(Color.RED);
		nbCSS.setForeground(Color.BLUE);
		nbJS.setForeground(Color.BLUE);
		nbHTML.setForeground(Color.BLUE);
		

	    layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(motsDif)
	                        .addComponent(addrDif)
	                        .addComponent(imagesDif)
	                        .addComponent(hypersDif)
	                    )
	                    //le groupe des nbs
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbMotsDif)
		                    .addComponent(nbAddrDif)
		                    .addComponent(nbImagesDif)
		                    .addComponent(nbHypersDif)
		                )
	                )
	            	.addGroup(layout.createSequentialGroup()
		                    // Le groupe des labels
		    	            .addGap(10)
		    	            .addComponent(divers)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    	.addComponent(css)
		                        .addComponent(js)
		                        .addComponent(html)
		                    )
		                    //le groupe des nbs
		    	            .addGap(10)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			                    .addComponent(nbCSS)
			                    .addComponent(nbJS)
			                    .addComponent(nbHTML)
			                )
			         )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(motsDif)
	                		.addComponent(nbMotsDif)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(addrDif)
		                    .addComponent(nbAddrDif)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(imagesDif)
		                    .addComponent(nbImagesDif)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(hypersDif)
		                    .addComponent(nbHypersDif)
	                )
	          )
	          .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addComponent(divers)
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(css)
		                    .addComponent(nbCSS)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(js)
		                    .addComponent(nbJS)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(html)
		                    .addComponent(nbHTML)
	                )
	          )
	    );
	}
	
	/**
	 * Méthode qui mets à jour les champ des infos à partir du modèle 'stats'
	 */
	public void updateInfos(){
		
		/*
		 * A voir avec Renaud ....
		 */
		
		//nbMotsDif.setText(stats.getNbMotsDif());
		//nbAddrDif.setText(stats.getNbAddrDif());
		//nbImagesDif.setText(stats.getNbImagesDif());
		//nbHypersDif.setText(stats.getNbHypersDif);
	}
	
}
