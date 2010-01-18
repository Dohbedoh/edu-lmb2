/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Aspirateur.Meta;

import statistiques.Statistiques;
import tableur.*;

public class VueAnalyseBoutons extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JButton lesMotsBut,lesLiensBut,lesBalisesBut;
	
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseBoutons(Statistiques stats){
		this.stats = stats;
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		lesMotsBut = new JButton("Statistiques sur la fréquence des Mots");
		lesMotsBut.addActionListener(new ActionStatsMots());
		
		lesLiensBut = new JButton("Statistiques sur les liens hypertextes");
		lesLiensBut.addActionListener(new ActionStatsLiens());
		
		lesBalisesBut = new JButton("Statistiques sur les balises (tags)");
		lesBalisesBut.addActionListener(new ActionStatsBalises());
		
		Dimension maxDim = lesMotsBut.getMaximumSize();
		lesMotsBut.setPreferredSize(maxDim);
		lesLiensBut.setMinimumSize(maxDim);
		lesBalisesBut.setMinimumSize(maxDim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(lesMotsBut)
	                        .addComponent(lesLiensBut)
	                        .addComponent(lesBalisesBut)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGap(10)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(lesMotsBut)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                        .addComponent(lesLiensBut)
	                )
	                .addGap(5)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                        .addComponent(lesBalisesBut)
	                )
	          )
	          .addGap(10)
	    );
		
		/*this.add(lesMotsBut,BorderLayout.NORTH);
		this.add(lesLiensBut,BorderLayout.CENTER);
		this.add(LesBalisesBut,BorderLayout.SOUTH);*/
	}//cons-1
	
	/**
	 * Désactive les boutons
	 */
	public void setEnabled(boolean b){
		lesMotsBut.setEnabled(b);
		lesLiensBut.setEnabled(b);
		lesBalisesBut.setEnabled(b);
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
	public Insets getInsets() {
		Insets normal = super.getInsets();
		return new Insets(normal.top + 5, normal.left + 5, 
				normal.bottom + 5, normal.right + 5);
	}
	
	//------------------
	// Actions
	//------------------
	private class ActionStatsMots implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			SpreadSheet s = new SpreadSheet(stats.getDataMotsComplet());
		}
	}
	
	private class ActionStatsLiens implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			SpreadSheet s = new SpreadSheet(stats.getDataLinksComplet());
		}
	}
	
	private class ActionStatsBalises implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			Meta meta;
			try {
				meta = Meta.deserializer(stats.getVersion().getAbsolutePath()+"/meta.dat");
				SpreadSheet s = new SpreadSheet(meta.getTagsTable());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
}
