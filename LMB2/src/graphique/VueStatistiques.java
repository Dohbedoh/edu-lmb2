/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;
import statistiques.Statistiques;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueStatistiques extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	private JLabel afficheURL;
	private VueAnalyse vueAnalyse;
	private Statistiques stats;
	
	//------------------
	// Constructeurs
	//------------------
	public VueStatistiques(Statistiques stats){
		this.stats = stats;
		
		this.setLayout(new BorderLayout());
		
		// Creation des elements graphique
		afficheURL = new JLabel();
		
		vueAnalyse = new VueAnalyse(stats);
		this.add(vueAnalyse, BorderLayout.CENTER);
		
		update(null,null);
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
		
	}
}
