/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class VueAnalyseComparaison extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Comparaison comparaison;
	private VueAnalyseComparaisonBoutons vueAnalyseComparaisonBoutons;
	private VueAnalyseComparaisonInfos vueAnalyseComparaisonInfos;
	private VueAnalyseComparaisonList vueAnalyseComparaisonList;
	
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseComparaison(Comparaison comparaison){
		
		this.comparaison = comparaison;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(gbl);

		TitledBorder afact = BorderFactory.createTitledBorder("Analyse du Contenu");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);

		Container cont = new Container();
		cont.setLayout(new BorderLayout(5,5));
		
		/* Construction des vues */
		vueAnalyseComparaisonBoutons = new VueAnalyseComparaisonBoutons(comparaison);
		vueAnalyseComparaisonInfos = new VueAnalyseComparaisonInfos(comparaison);
		vueAnalyseComparaisonList = new VueAnalyseComparaisonList(comparaison);
		
		cont.add(vueAnalyseComparaisonBoutons, BorderLayout.NORTH);
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
        this.add(vueAnalyseComparaisonList, gbc);
	}
	
	public void setEnabled(boolean b){
		vueAnalyseComparaisonBoutons.setEnabled(b);
		vueAnalyseComparaisonList.setEnabled(b);
		vueAnalyseComparaisonInfos.setEnabled(b);
	}
	
	public void setComparaison(Comparaison comparaison){
		this.comparaison = comparaison;
		vueAnalyseComparaisonBoutons.setComparaison(comparaison);
		vueAnalyseComparaisonInfos.setComparaison(comparaison);
		vueAnalyseComparaisonList.setComparaison(comparaison);
	}
	
	public Comparaison getComparaison(){
		return this.comparaison;
	}
	
	//------------------
	// Méthodes
	//------------------
	public VueAnalyseComparaisonBoutons getVueAnalyseComparaisonBoutons() {
		return vueAnalyseComparaisonBoutons;
	}

	public void setVueAnalyseComparaisonBoutons(VueAnalyseComparaisonBoutons vueAnalyseComparaisonBoutons) {
		this.vueAnalyseComparaisonBoutons = vueAnalyseComparaisonBoutons;
	}

	public VueAnalyseComparaisonInfos getVueAnalyseComparaisonInfos() {
		return vueAnalyseComparaisonInfos;
	}

	public void setVueAnalyseComparaisonInfos(VueAnalyseComparaisonInfos vueAnalyseComparaisonInfos) {
		this.vueAnalyseComparaisonInfos = vueAnalyseComparaisonInfos;
	}

	public VueAnalyseComparaisonList getVueAnalyseList() {
		return vueAnalyseComparaisonList;
	}

	public void setVueAnalyseList(VueAnalyseComparaisonList vueAnalyseList) {
		this.vueAnalyseComparaisonList = vueAnalyseList;
	}
	
}
