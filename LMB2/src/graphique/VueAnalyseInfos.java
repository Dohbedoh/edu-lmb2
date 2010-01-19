/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import statistiques.Statistiques;


public class VueAnalyseInfos extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JLabel 	nbMotsDif, nbAddrDif, nbImagesDif, nbHypersDif,
					divers, css, js, html,
					motsDif, addrDif, imagesDif, hypersDif,
					nbCSS, nbJS, nbHTML;

	//------------------
	// Constructeurs
	//------------------
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
		
		nbMotsDif = new JLabel(" 0");
		nbAddrDif = new JLabel(" 0");
		nbImagesDif = new JLabel(" 0");
		nbHypersDif = new JLabel(" 0");
		nbCSS = new JLabel(" 0");
		nbJS = new JLabel(" 0");
		nbHTML = new JLabel(" 0");

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
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(motsDif)
	                        .addComponent(addrDif)
	                        .addComponent(imagesDif)
	                        .addComponent(hypersDif)
	                    )
	                    //le groupe des nbs
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbMotsDif)
		                    .addComponent(nbAddrDif)
		                    .addComponent(nbImagesDif)
		                    .addComponent(nbHypersDif)
		                    .addComponent(nbCSS)
		                    .addComponent(nbJS)
		                    .addComponent(nbHTML)
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
	            	)
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGroup(layout.createSequentialGroup()
		            .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(motsDif)
	                		.addComponent(nbMotsDif)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(addrDif)
		                    .addComponent(nbAddrDif)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(imagesDif)
		                    .addComponent(nbImagesDif)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(hypersDif)
		                    .addComponent(nbHypersDif)
	                )
	          )
	          .addGroup(layout.createSequentialGroup()
		            .addGap(5)
	                .addComponent(divers)
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(css)
		                    .addComponent(nbCSS)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(js)
		                    .addComponent(nbJS)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(html)
		                    .addComponent(nbHTML)
	                )
	          )
	    );
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
		nbMotsDif.setText(stats.getDataMotsComplet().size()+"");
		nbAddrDif.setText("NYI");
		nbImagesDif.setText(stats.getDataImages().size()+"");
		nbHypersDif.setText(stats.getDataLinksComplet().size()+"");
		nbCSS.setText(stats.getDataCSS().size()+"");
		nbJS.setText(stats.getDataJS().size()+"");
		nbHTML.setText(stats.getDataHTML().size()+"");
	}
	
}
