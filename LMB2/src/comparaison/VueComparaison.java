/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import graphique.VueComparaisonVersion;
import graphique.VueInfosStatistiques;

import javax.swing.*;
import statistiques.Statistiques;
import java.awt.*;




public class VueComparaison extends JPanel{

	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private VueAnalyseComparaison vueAnalyseComparaison;
	private VueInfosStatistiques vueInfosStatistiques1;
	private VueInfosStatistiques vueInfosStatistiques2;
	
	private Statistiques stats;
	
	//------------------
	// Constructeurs
	//------------------
	public VueComparaison(Statistiques stats){
		this.stats = stats;
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new GridLayout(1,2));
		
		vueAnalyseComparaison = new VueAnalyseComparaison(stats);
		vueInfosStatistiques1 = new VueInfosStatistiques(stats);
		vueInfosStatistiques2 = new VueInfosStatistiques(stats);
		
		top.add(vueInfosStatistiques1);
		top.add(vueInfosStatistiques2);
		
		
		
		this.add(top,BorderLayout.NORTH);
		this.add(vueAnalyseComparaison, BorderLayout.CENTER);
		
		setEnabled(false);
		
		
	}//cons-1
	
	public void setStatistiques1(Statistiques stats){
		this.stats = stats;
		this.vueInfosStatistiques1.setStatistiques(stats);
	}
	
	public void setStatistiques2(Statistiques stats){
		this.stats = stats;
		this.vueInfosStatistiques2.setStatistiques(stats);
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	public void setEnabled(boolean b){
		vueAnalyseComparaison.setEnabled(b);
	}
	
	//------------------
	// Methodes
	//------------------
	public VueAnalyseComparaison getVueAnalyse() {
		return vueAnalyseComparaison;
	}

	public void setVueAnalyse(VueAnalyseComparaison vueAnalyse) {
		this.vueAnalyseComparaison = vueAnalyse;
	}

	public VueInfosStatistiques getVueInfosStatistiques1() {
		return vueInfosStatistiques1;
	}

	public void setVueInfosStatistiques1(VueInfosStatistiques vueInfosStatistiques) {
		this.vueInfosStatistiques1 = vueInfosStatistiques;
	}

	public VueInfosStatistiques getVueInfosStatistiques2() {
		return vueInfosStatistiques1;
	}

	public void setVueInfosStatistiques2(VueInfosStatistiques vueInfosStatistiques) {
		this.vueInfosStatistiques1 = vueInfosStatistiques;
	}
	
}
