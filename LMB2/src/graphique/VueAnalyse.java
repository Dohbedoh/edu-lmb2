/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import statistiques.Statistiques;


public class VueAnalyse extends JPanel implements Observer{

	
	private Statistiques stats;
	private VueAnalyseBoutons vueAnalyseBoutons;
	private VueAnalyseInfos vueAnalyseInfos;
	private VueAnalyseList vueAnalyseList;
	
	public VueAnalyse(Statistiques stats){
		
		this.stats = stats;
		//stats.addObserver(this);
		this.setLayout(new BorderLayout(5,5));
		
		setBorder(BorderFactory.createTitledBorder("Analyse du Contenu"));
		
		/* Construction des vues */
		vueAnalyseBoutons = new VueAnalyseBoutons(stats);
		vueAnalyseInfos = new VueAnalyseInfos(stats);
		
		this.add(vueAnalyseBoutons, BorderLayout.NORTH);
		this.add(vueAnalyseInfos, BorderLayout.WEST);
		update(null,null);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		vueAnalyseInfos.updateInfos();
	}
}
