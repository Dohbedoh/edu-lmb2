/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import statistiques.Statistiques;


public class VueAnalyseBoutons extends JPanel {

	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JButton lesMotsBut,lesLiensBut;
	
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
		
		this.add(lesMotsBut,BorderLayout.NORTH);
		this.add(lesLiensBut,BorderLayout.SOUTH);
	}//cons-1
	
	/**
	 * Désactive les boutons
	 */
	public void setEnabled(boolean b){
		lesMotsBut.setEnabled(b);
		lesLiensBut.setEnabled(b);
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
						
		}
	}
	
	private class ActionStatsLiens implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
				
		}
	}

	
	
	
}
