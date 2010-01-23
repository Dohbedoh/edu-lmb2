/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
		//stats.addObserver(this);
		GridBagLayout gbl = new GridBagLayout();
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

		GroupLayout gl = new GroupLayout(cont);
		
		cont.add(vueAnalyseBoutons, BorderLayout.NORTH);
		cont.add(vueAnalyseInfos, BorderLayout.CENTER);
		
		 /*3- Ajout de ces composants en spécifiant les contraintes de type GridBagConstraints. */
        GridBagConstraints gbc = new GridBagConstraints();

        /* Nous utilisons ici une constante que nous n'avons pas suffisemment utilisé dans nos exemples.
         * En utilisant la constante GridBagConstraints.RELATIVE pour la propriété gridx, cela permet
         * d'incrémenter automatiquement cette propriété à chaque nouveau composant ajouté.
         * Il nous est donc inutile de spécifier à chaque fois un nouveau gridx.
         * */
        gbc.gridx = GridBagConstraints.RELATIVE;  // gridx sera initialisé en 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(cont, gbc);
        this.add(cont);

        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START; // remettons la valeur par défaut
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(vueAnalyseList, gbc);
        this.add(vueAnalyseList);
		//this.add(vueAnalyseInfos, BorderLayout.WEST);
		//this.add(vueAnalyseList,BorderLayout.CENTER);
		//this.add(cont);
		//this.add(vueAnalyseList);
		
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
