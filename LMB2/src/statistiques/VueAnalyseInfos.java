/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import java.awt.Color;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class VueAnalyseInfos extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JLabel 	nbAddrDif, nbImagesDif, nbHypersDif, nbFiltred, nbMorts,
					css, js, html,
					addrDif, imagesDif, hypersDif, filtred, morts,
					nbCSS, nbJS, nbHTML;

	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseInfos(Statistiques stats){
		this.stats = stats;

	    Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);
		addrDif = new JLabel("• Adresses mail : ");
		imagesDif = new JLabel("• Images : ");
		hypersDif = new JLabel("• Liens hypertexte : ");
		css = new JLabel("• Fichiers CSS : ");
		js = new JLabel("• Fichiers JS : ");
		html = new JLabel("• Fichiers HTML : ");
		filtred = new JLabel("• Liens Filtrés : ");
		morts = new JLabel("• Lien Morts : ");
		nbAddrDif = new JLabel(" 0");
		nbImagesDif = new JLabel(" 0");
		nbHypersDif = new JLabel(" 0");
		nbCSS = new JLabel(" 0");
		nbJS = new JLabel(" 0");
		nbHTML = new JLabel(" 0");
		nbFiltred = new JLabel(" 0");
		nbMorts = new JLabel(" 0");

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
		
		nbAddrDif.setForeground(Color.RED);
		nbImagesDif.setForeground(Color.RED);
		nbHypersDif.setForeground(Color.RED);
		nbCSS.setForeground(Color.BLUE);
		nbJS.setForeground(Color.BLUE);
		nbHTML.setForeground(Color.BLUE);
		nbFiltred.setForeground(Color.BLUE);
		nbMorts.setForeground(Color.BLUE);
		
		

	    layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(addrDif)
	                        .addComponent(imagesDif)
	                        .addComponent(hypersDif)
	                    	.addComponent(css)
	                        .addComponent(js)
	                        .addComponent(html)
	                        .addComponent(filtred)
	                        .addComponent(morts)
	                    )
	                    //le groupe des nbs
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbAddrDif)
		                    .addComponent(nbImagesDif)
		                    .addComponent(nbHypersDif)
		                    .addComponent(nbCSS)
		                    .addComponent(nbJS)
		                    .addComponent(nbHTML)
		                    .addComponent(nbFiltred)
		                    .addComponent(nbMorts)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGroup(layout.createSequentialGroup()
		            .addGap(5)
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
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(filtred)
		                    .addComponent(nbFiltred)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(morts)
		                    .addComponent(nbMorts)
	                )
	                .addGap(5)
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
			nbAddrDif.setText(stats.getMetaData().getMailTosList().size()+"");
			nbImagesDif.setText(stats.getDataImages().size()+"");
			nbHypersDif.setText(stats.getDataLinksComplet().size()+"");
			nbCSS.setText(stats.getDataCSS().size()+"");
			nbJS.setText(stats.getDataJS().size()+"");
			nbHTML.setText(stats.getDataHTML().size()+"");
			nbFiltred.setText(stats.getMetaData().getFiltredLinks().size()+"");
			nbMorts.setText(stats.getMetaData().getBrokenLinks().size()+"");
		}
	}
	
}
