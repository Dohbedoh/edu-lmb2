package capture;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Aspirateur.Aspirateur;

public class VueCaptureInfos extends JPanel implements Observer {
	
	//------------------
	// Attributs
	//------------------
	private Aspirateur laspirateur;
	private JLabel infosPages, infosRessources, infosTaille, infosFiltres, infosMorts, infosAuth,
	nbPages, nbRessources, taille, nbFiltrees, nbMorts, nbAuth;

	//------------------
	// Constructeur
	//------------------
	public VueCaptureInfos(Aspirateur aspi){
		this.laspirateur = aspi;
		laspirateur.addObserver(this);
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		infosPages = new JLabel("Pages Copiées : ");
		infosRessources = new JLabel("Ressources Copiées : ");
		infosFiltres = new JLabel("Liens Filtrées : ");
		infosTaille = new JLabel("Taille Du Site : ");
		infosMorts = new JLabel("Liens Morts : ");
		infosAuth = new JLabel("Liens avec Auth : ");

		
		nbPages = new JLabel("0 / 0");
		nbRessources = new JLabel("0 / 0");
		nbFiltrees = new JLabel("0");
		nbMorts = new JLabel("0");
		nbAuth = new JLabel("0");
		taille = new JLabel("0");
		

		nbPages.setForeground(Color.BLUE);
		nbRessources.setForeground(Color.BLUE);
		nbMorts.setForeground(Color.BLUE);
		nbAuth.setForeground(Color.BLUE);
		nbFiltrees.setForeground(Color.BLUE);
		taille.setForeground(Color.BLUE);
		
		infosPages.setFont(new Font(null,1,11));
		infosRessources.setFont(new Font(null,1,11));
		infosMorts.setFont(new Font(null,1,11));
		infosAuth.setFont(new Font(null,1,11));
		infosFiltres.setFont(new Font(null,1,11));
		infosTaille.setFont(new Font(null,1,11));
		nbPages.setFont(new Font(null,1,11));
		nbRessources.setFont(new Font(null,1,11));
		nbMorts.setFont(new Font(null,1,11));
		nbAuth.setFont(new Font(null,1,11));
		nbFiltrees.setFont(new Font(null,1,11));
		taille.setFont(new Font(null,1,11));
		
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    // Le groupe des labels
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(infosPages)
	                        .addComponent(infosRessources)
	                        .addComponent(infosFiltres)
	                        .addComponent(infosMorts)
	                        .addComponent(infosAuth)
	                        .addComponent(infosTaille)
	                    )
	                    //le groupe des nbs
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbPages)
		                    .addComponent(nbRessources)
		                    .addComponent(nbFiltrees)
		                    .addComponent(nbMorts)
		                    .addComponent(nbAuth)
		                    .addComponent(taille)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(infosPages)
	                		.addComponent(nbPages)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(infosRessources)
		                    .addComponent(nbRessources)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(infosFiltres)
		                    .addComponent(nbFiltrees)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(infosMorts)
		                    .addComponent(nbMorts)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(infosAuth)
		                    .addComponent(nbAuth)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(infosTaille)
		                    .addComponent(taille)
	                )
	          )
              .addGap(5)
	    );
	}
	
	//------------------
	// Méthodes
	//------------------
	public void update(Observable o, Object arg) {
		double totalCopies = laspirateur.getNbFichiersCopies();
		double total = totalCopies + laspirateur.getNbFichiersACopies();
		if(totalCopies!=0 || total!=0){
			nbPages.setText(laspirateur.getNbPagesCopiees()+"/"+(laspirateur.getNbPagesACopiees()+laspirateur.getNbPagesCopiees()));
			nbRessources.setText(laspirateur.getNbRessourcesCopiees()+"/"+(laspirateur.getNbRessourcesACopiees()+laspirateur.getNbRessourcesCopiees()));
			nbFiltrees.setText(laspirateur.getNbFiltredURL()+"");
			nbMorts.setText(laspirateur.getNbLiensMorts()+"");
			nbAuth.setText(laspirateur.getNbLiensAuth()+"");
			taille.setText(laspirateur.getTailleSite()/1024+" Ko");
		}else{
			nbPages.setText("0/0");
			nbRessources.setText("0/0");
			nbFiltrees.setText("0");
			nbMorts.setText("0");
			nbAuth.setText("0");
			taille.setText("0");
		}
	}

}
