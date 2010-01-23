/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

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
		vueAnalyseInfos = new VueAnalyseInfos(stats);
		vueAnalyseList = new VueAnalyseList(stats);
		
		cont.add(vueAnalyseBoutons, BorderLayout.NORTH);
		cont.add(vueAnalyseInfos, BorderLayout.CENTER);

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
