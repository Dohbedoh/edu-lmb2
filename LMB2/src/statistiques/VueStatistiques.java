/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;

import graphique.VueOnglets;

import javax.swing.*;
import comparaison.Comparaison;
import java.awt.*;

public class VueStatistiques extends JPanel{

	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private VueAnalyse vueAnalyse;
	private VueInfosStatistiques vueInfosStatistiques;
	private VueComparaisonVersion vueComparaisonVersion;
	private VueOnglets vueOnglets;
	
	private Statistiques stats;
	
	//------------------
	// Constructeurs
	//------------------
	public VueStatistiques(Statistiques stats, VueOnglets vueOnglets){
		this.stats = stats;
		this.vueOnglets = vueOnglets;
		
		this.setLayout(new BorderLayout());
		JPanel top = new JPanel(new BorderLayout(5,5));
		
		vueAnalyse = new VueAnalyse(stats);
		vueInfosStatistiques = new VueInfosStatistiques(stats);
		vueComparaisonVersion = new VueComparaisonVersion(new Comparaison(stats,new Statistiques(null)), vueOnglets);
		
		top.add(vueInfosStatistiques,BorderLayout.CENTER);
		top.add(vueComparaisonVersion,BorderLayout.EAST);
		
		this.add(top,BorderLayout.NORTH);
		this.add(vueAnalyse, BorderLayout.CENTER);
		
		setEnabled(false);
		
		
	}//cons-1
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
		this.vueInfosStatistiques.setStatistiques(stats);
		this.vueAnalyse.setStatistiques(stats);
		this.vueComparaisonVersion.setComparasion(new Comparaison(stats,new Statistiques(null)));
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	public void setEnabled(boolean b){
		vueComparaisonVersion.setEnabled(b);
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

	public VueComparaisonVersion getVueComparaison() {
		return vueComparaisonVersion;
	}

	public void setVueComparaison(VueComparaisonVersion vueComparaison) {
		this.vueComparaisonVersion = vueComparaison;
	}
	
	
}
