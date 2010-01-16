/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.swing.*;
import statistiques.Statistiques;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueStatistiques extends JPanel{

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
		
	}//cons-1
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
		this.vueInfosStatistiques.setStatistiques(stats);
		this.vueAnalyse.setStatistiques(stats);
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	public void setEnabled(boolean b){
		vueAnalyse.setEnabled(b);
	}
	
	//------------------
	// Methodes
	//------------------
	public VueAnalyse getVueAnalyse() {
		return vueAnalyse;
	}

	public void setVueAnalyse(VueAnalyse vueAnalyse) {
		this.vueAnalyse = vueAnalyse;
	}

	public VueInfosStatistiques getVueInfosStatistiques() {
		return vueInfosStatistiques;
	}

	public void setVueInfosStatistiques(VueInfosStatistiques vueInfosStatistiques) {
		this.vueInfosStatistiques = vueInfosStatistiques;
	}
	
}
