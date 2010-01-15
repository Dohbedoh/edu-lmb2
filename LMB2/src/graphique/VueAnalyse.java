/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import statistiques.Statistiques;


public class VueAnalyse extends JPanel implements Observer{

	
	private Statistiques stats;
	private VueAnalyseBoutons vueAnalyseBoutons;
	private VueAnalyseInfos vueAnalyseInfos;
	private VueAnalyseList vueAnalyseList;
	
	public VueAnalyse(Statistiques stats){
		
		this.stats = stats;
		//stats.addObserver(this);
		this.setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Analyse du Contenu"));
		
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
		update(null,null);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		vueAnalyseInfos.updateInfos();
	}
}
