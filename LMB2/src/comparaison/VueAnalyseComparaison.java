/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import graphique.VueAnalyseBoutons;
import graphique.VueAnalyseInfos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import statistiques.Statistiques;


public class VueAnalyseComparaison extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private VueAnalyseBoutons vueAnalyseBoutons;
	private VueAnalyseComparaisonInfos vueAnalyseComparaisonInfos;
	private VueAnalyseComparaisonList vueAnalyseList;
	
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseComparaison(Statistiques stats){
		
		this.stats = stats;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(gbl);

		TitledBorder afact = BorderFactory.createTitledBorder("Analyse du Contenu");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);

		Container cont = new Container();
		cont.setLayout(new BorderLayout(5,5));
		
		/* Construction des vues */
		vueAnalyseBoutons = new VueAnalyseBoutons(stats);
		vueAnalyseComparaisonInfos = new VueAnalyseComparaisonInfos(stats);
		vueAnalyseList = new VueAnalyseComparaisonList(stats);
		
		cont.add(vueAnalyseBoutons, BorderLayout.NORTH);
		cont.add(vueAnalyseComparaisonInfos, BorderLayout.CENTER);

        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(cont, gbc);

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(vueAnalyseList, gbc);
	}
	
	public void setEnabled(boolean b){
		vueAnalyseBoutons.setEnabled(b);
		vueAnalyseList.setEnabled(b);
	}
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
		vueAnalyseBoutons.setStatistiques(stats);
		vueAnalyseComparaisonInfos.setStatistiques(stats);
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

	public VueAnalyseComparaisonInfos getVueAnalyseInfos() {
		return vueAnalyseComparaisonInfos;
	}

	public void setVueAnalyseInfos(VueAnalyseComparaisonInfos vueAnalyseComparaisonInfos) {
		this.vueAnalyseComparaisonInfos = vueAnalyseComparaisonInfos;
	}

	public VueAnalyseComparaisonList getVueAnalyseList() {
		return vueAnalyseList;
	}

	public void setVueAnalyseList(VueAnalyseComparaisonList vueAnalyseList) {
		this.vueAnalyseList = vueAnalyseList;
	}
	
}
