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
	private VueAnalyse vueAnalyse;
	private VueInfosStatistiques vueInfosStatistiques;
	private Statistiques stats;
	
	//------------------
	// Constructeurs
	//------------------
	public VueStatistiques(Statistiques stats){
		this.stats = stats;
		
		this.setLayout(new BorderLayout());
		
		vueAnalyse = new VueAnalyse(stats);
		vueInfosStatistiques = new VueInfosStatistiques(stats);
		this.add(vueAnalyse, BorderLayout.CENTER);
		this.add(vueInfosStatistiques, BorderLayout.NORTH);
		
		update(null,null);
	}//cons-1
	
	//------------------
	// Methodes
	//------------------
	public void update(Observable o, Object arg) {
	}
}
