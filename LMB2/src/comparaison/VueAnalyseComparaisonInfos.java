/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import java.awt.Color;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class VueAnalyseComparaisonInfos extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Comparaison comparaison;
	private JLabel 	nbFichiersMod, nbFichiersAj, nbFichiersSupp,nbAddr,
					fichiersMod, fichiersAj, fichiersSupp, addr;

	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseComparaisonInfos(Comparaison comparaison){
		this.comparaison = comparaison;
		
	    Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);
		fichiersMod = new JLabel("• Fichiers Modifiés : ");
		fichiersAj = new JLabel("• Fichiers Ajoutés : ");
		fichiersSupp = new JLabel("• Fichiers Supprimés : ");
		addr = new JLabel("• Adresse e-mail : ");
		
		nbFichiersMod = new JLabel(" 0");
		nbFichiersAj = new JLabel(" 0");
		nbFichiersSupp = new JLabel(" 0");
		nbAddr = new JLabel(" 0");

		/*html.setFont(new Font(null,1,11));
		js.setFont(new Font(null,1,11));
		css.setFont(new Font(null,1,11));
		divers.setFont(new Font(null,1,11));
		hypersDif.setFont(new Font(null,1,11));
		imagesDif.setFont(new Font(null,1,11));
		addrDif.setFont(new Font(null,1,11));
		motsDif.setFont(new Font(null,1,11));
		nbHypersDif.setFont(new Font(null,1,11));
		nbCSS.setFont(new Font(null,1,11));
		nbJS.setFont(new Font(null,1,11));
		nbHTML.setFont(new Font(null,1,11));
		nbImagesDif.setFont(new Font(null,1,11));
		nbAddrDif.setFont(new Font(null,1,11));
		nbMotsDif.setFont(new Font(null,1,11));*/
		
		nbFichiersMod.setForeground(Color.RED);
		nbFichiersAj.setForeground(Color.RED);
		nbFichiersSupp.setForeground(Color.RED);
		nbAddr.setForeground(Color.RED);
		

	    layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(fichiersMod)
	                        .addComponent(fichiersAj)
	                        .addComponent(fichiersSupp)
	                        .addComponent(addr)
	                    )
	                    //le groupe des nbs
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbFichiersMod)
		                    .addComponent(nbFichiersAj)
		                    .addComponent(nbFichiersSupp)
	                        .addComponent(nbAddr)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGroup(layout.createSequentialGroup()
		            .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(fichiersMod)
	                		.addComponent(nbFichiersMod)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(fichiersAj)
		                    .addComponent(nbFichiersAj)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(fichiersSupp)
		                    .addComponent(nbFichiersSupp)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(addr)
		                    .addComponent(nbAddr)
	                )
	          )
	    );
	    add(cont, SwingUtilities.CENTER);
	}
	
	
	public void setComparaison(Comparaison comparaison){
		this.comparaison = comparaison;
	}
	
	public Comparaison getComparaison(){
		return this.comparaison;
	}
	
	//------------------
	// Méthodes
	//------------------
	public void update(Observable arg0, Object arg1) {
		nbFichiersMod.setText(""+comparaison.getLesFichiersModifies().size());
		nbFichiersAj.setText(""+comparaison.getLesFichiersAjoutes().size());
		nbFichiersSupp.setText(""+comparaison.getLesFichiersSupprimes().size());
	}
	
}
