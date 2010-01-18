/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	private JButton lesMotsBut,lesLiensBut,LesBalisesBut;
	
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseBoutons(Statistiques stats){
		this.stats = stats;
		this.setLayout(new BorderLayout(5,5));
		
		lesMotsBut = new JButton("Statistiques sur la fréquence des Mots");
		lesMotsBut.addActionListener(new ActionStatsMots());
		
		lesLiensBut = new JButton("Statistiques sur les liens hypertextes");
		lesLiensBut.addActionListener(new ActionStatsLiens());
		
		LesBalisesBut = new JButton("Statistiques sur les balises (tags)");
		LesBalisesBut.addActionListener(new ActionStatsBalises());
		
		this.add(lesMotsBut,BorderLayout.NORTH);
		this.add(lesLiensBut,BorderLayout.CENTER);
		this.add(LesBalisesBut,BorderLayout.SOUTH);
	}//cons-1
	
	/**
	 * Désactive les boutons
	 */
	public void setEnabled(boolean b){
		lesMotsBut.setEnabled(b);
		lesLiensBut.setEnabled(b);
		LesBalisesBut.setEnabled(b);
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
