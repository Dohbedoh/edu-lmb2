/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueAnalyseInfos extends JPanel {
	
	private Statistiques stats;
	private JLabel 	nbMotsDif, nbAddrDif, nbImagesDif, nbHypersDif,
					motsDif, addrDif, imagesDif, hypersDif;

	public VueAnalyseInfos(Statistiques stats){
		this.stats = stats;
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
	    layout.setAutoCreateContainerGaps(true);
	    layout.setAutoCreateGaps(true);
		motsDif = new JLabel("• Mots différents sur le site : ");
		addrDif = new JLabel("• Adresses mail : ");
		imagesDif = new JLabel("• Images : ");
		hypersDif = new JLabel("• Liens hypertexte : ");
		nbMotsDif = new JLabel(" 0");
		nbAddrDif = new JLabel(" 0");
		nbImagesDif = new JLabel(" 0");
		nbHypersDif = new JLabel(" 0");
		
		nbMotsDif.setForeground(Color.RED);
		nbAddrDif.setForeground(Color.RED);
		nbImagesDif.setForeground(Color.RED);
		nbHypersDif.setForeground(Color.RED);
		
		/* 
		 * Groupe de Label
		 */
	    layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	                    .addGroup(layout.createParallelGroup()  
	                        .addComponent(motsDif)
	                        .addComponent(addrDif)
	                        .addComponent(imagesDif)
	                        .addComponent(hypersDif)
	                    )
	                    //le groupe des nbs
	                    .addGroup(layout.createParallelGroup()
		                    .addComponent(nbMotsDif)
		                    .addComponent(nbAddrDif)
		                    .addComponent(nbImagesDif)
		                    .addComponent(nbHypersDif)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGroup(layout.createSequentialGroup()
	                // Le groupe pour la saisie du nom
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(motsDif)
	                    .addComponent(nbMotsDif)
	                )
	                // Le groupe pour la saisie de l'ancien mot de passe
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    .addComponent(addrDif)
		                    .addComponent(nbAddrDif)
	                )
	                //
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    .addComponent(imagesDif)
		                    .addComponent(nbImagesDif)
	                )
	                //
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    .addComponent(hypersDif)
		                    .addComponent(nbHypersDif)
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
		
		//nbMotsDif.setText(stats.getNbMotsDif);
		//nbAddrDif.setText(stats.getNbAddrDif);
		//nbImagesDif.setText(stats.getNbImagesDif);
		//nbHypersDif.setText(stats.getNbHypersDif);
	}
	
}
