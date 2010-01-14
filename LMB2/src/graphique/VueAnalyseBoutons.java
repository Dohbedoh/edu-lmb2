/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import statistiques.Statistiques;


public class VueAnalyseBoutons extends JPanel {

	private Statistiques stats;
	private JButton lesMotsBut,lesLiensBut;
	
	public VueAnalyseBoutons(Statistiques stats){
		this.stats = stats;
		this.setLayout(new BorderLayout(5,5));
		
		lesMotsBut = new JButton("Statistiques sur la fréquence des Mots");
		lesMotsBut.addActionListener(new ActionStatsMots());
		
		lesLiensBut = new JButton("Statistiques sur les liens hypertextes");
		lesLiensBut.addActionListener(new ActionStatsLiens());
		
		this.add(lesMotsBut,BorderLayout.NORTH);
		this.add(lesLiensBut,BorderLayout.SOUTH);
	}
	
	public Insets getInsets() {
		Insets normal = super.getInsets();
		return new Insets(normal.top + 5, normal.left + 5, 
				normal.bottom + 5, normal.right + 5);
	}
	
	private class ActionStatsMots implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class ActionStatsLiens implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
