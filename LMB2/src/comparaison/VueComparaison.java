/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import graphique.VueInfosStatistiques;
import javax.swing.*;
import statistiques.Comparaison;
import java.awt.*;




public class VueComparaison extends JPanel{

	private static final long serialVersionUID = 1L;
	//------------------
	// Attributs
	//------------------
	private VueAnalyseComparaison vueAnalyseComparaison;
	private VueInfosStatistiques vueInfosStatistiques1;
	private VueInfosStatistiques vueInfosStatistiques2;
	
	private Comparaison comparaison;
	
	//------------------
	// Constructeurs
	//------------------
	public VueComparaison(Comparaison comparaison){
		this.comparaison = comparaison;
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new GridLayout(1,2));
		
		vueAnalyseComparaison = new VueAnalyseComparaison(comparaison);
		vueInfosStatistiques1 = new VueInfosStatistiques(comparaison.getStatsCourante());
		vueInfosStatistiques2 = new VueInfosStatistiques(comparaison.getStats2());
		
		top.add(vueInfosStatistiques1);
		top.add(vueInfosStatistiques2);
		
		
		
		this.add(top,BorderLayout.NORTH);
		this.add(vueAnalyseComparaison, BorderLayout.CENTER);
		
		setEnabled(false);
		
		
	}//cons-1
	
	public Comparaison getComparaison(){
		return comparaison;
	}
	
	public void setComparaison(Comparaison comparaison){
		this.comparaison = comparaison;
		this.vueInfosStatistiques1.setStatistiques(comparaison.getStatsCourante());
		this.vueInfosStatistiques2.setStatistiques(comparaison.getStats2());
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
