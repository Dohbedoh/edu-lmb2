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

import statistiques.Statistiques;


public class VueAnalyseComparaisonInfos extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JLabel 	nbFichiersMod, nbFichiersAj, nbFichiersSupp,
					fichiersMod, fichiersAj, fichiersSupp;

	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseComparaisonInfos(Statistiques stats){
		this.stats = stats;

	    Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);
		fichiersMod = new JLabel("• Fichiers Modifiés : ");
		fichiersAj = new JLabel("• Fichiers Ajoutés : ");
		fichiersSupp = new JLabel("• Fichiers Supprimés : ");
		
		nbFichiersMod = new JLabel(" 0");
		nbFichiersAj = new JLabel(" 0");
		nbFichiersSupp = new JLabel(" 0");

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
		

	    layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(fichiersMod)
	                        .addComponent(fichiersAj)
	                        .addComponent(fichiersSupp)
	                    )
	                    //le groupe des nbs
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbFichiersMod)
		                    .addComponent(nbFichiersAj)
		                    .addComponent(nbFichiersSupp)
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
	          )
	    );
	    add(cont, SwingUtilities.CENTER);
	}
	
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	//------------------
	// Méthodes
	//------------------
	public void update(Observable arg0, Object arg1) {
		if(stats.getDataMotsComplet()!=null 
				&& stats.getDataCSS()!=null 
				&& stats.getDataHTML()!=null
				&& stats.getDataImages()!=null
				&& stats.getDataLinksComplet()!=null
				&& stats.getMetaData()!=null){
			nbFichiersMod.setText(stats.getDataMotsComplet().size()+"");
			nbFichiersAj.setText(stats.getMetaData().getMailTosList().size()+"");
			nbFichiersSupp.setText(stats.getDataImages().size()+"");
		}
	}
	
}
