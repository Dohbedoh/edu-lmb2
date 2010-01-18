/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import statistiques.Statistiques;


public class VueAnalyse extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private VueAnalyseBoutons vueAnalyseBoutons;
	private VueAnalyseInfos vueAnalyseInfos;
	private VueAnalyseList vueAnalyseList;
	
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyse(Statistiques stats){
		
		this.stats = stats;
		//stats.addObserver(this);
		this.setLayout(new BorderLayout());

		TitledBorder afact = BorderFactory.createTitledBorder("Analyse du Contenu");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);

		Container cont = new Container();
		GridLayout layout = new GridLayout(1,2);
		cont.setLayout(layout);
		
		/* Construction des vues */
		vueAnalyseBoutons = new VueAnalyseBoutons(stats);
		vueAnalyseInfos = new VueAnalyseInfos(stats);
		vueAnalyseList = new VueAnalyseList(stats);
		
		cont.add(vueAnalyseInfos);
		cont.add(vueAnalyseList);
		this.add(vueAnalyseBoutons, BorderLayout.NORTH);
		//this.add(vueAnalyseInfos, BorderLayout.WEST);
		//this.add(vueAnalyseList,BorderLayout.CENTER);
		this.add(cont, BorderLayout.CENTER);
		
	}
	
	public void setEnabled(boolean b){
		vueAnalyseBoutons.setEnabled(b);
		vueAnalyseList.setEnabled(b);
	}
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
		vueAnalyseBoutons.setStatistiques(stats);
		vueAnalyseInfos.setStatistiques(stats);
		vueAnalyseList.setStatistiques(stats);
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	//------------------
	// Méthodes
	//------------------
	public VueAnalyseBoutons getVueAnalyseBoutons() {
		return vueAnalyseBoutons;
	}

	public void setVueAnalyseBoutons(VueAnalyseBoutons vueAnalyseBoutons) {
		this.vueAnalyseBoutons = vueAnalyseBoutons;
	}

	public VueAnalyseInfos getVueAnalyseInfos() {
		return vueAnalyseInfos;
	}

	public void setVueAnalyseInfos(VueAnalyseInfos vueAnalyseInfos) {
		this.vueAnalyseInfos = vueAnalyseInfos;
	}

	public VueAnalyseList getVueAnalyseList() {
		return vueAnalyseList;
	}

	public void setVueAnalyseList(VueAnalyseList vueAnalyseList) {
		this.vueAnalyseList = vueAnalyseList;
	}
	
}
