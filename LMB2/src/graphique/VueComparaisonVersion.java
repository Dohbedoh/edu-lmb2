/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import comparaison.VueComparaison;

import statistiques.*;

public class VueComparaisonVersion extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Comparaison comparaison;
	private JList jlist;
	private JButton lancerComp;
	private VueOnglets vueOnglets;
	
	//------------------
	// Constructeur
	//------------------
	public VueComparaisonVersion(Comparaison comparaison, VueOnglets vueOnglets){
		this.comparaison = comparaison;
		this.vueOnglets = vueOnglets;
		setLayout(new BorderLayout());
		
		TitledBorder afact = BorderFactory.createTitledBorder("Comparaison des versions");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		lancerComp = new JButton("Lancer la comparaison");
		lancerComp.addActionListener(new ActionLancerComparaison());
		
		jlist = new JList();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setCellRenderer(new ListFormateur());
		jlist.addMouseListener(new ActionClick());
		
		add(new JScrollPane(jlist),BorderLayout.CENTER);
		add(lancerComp, BorderLayout.SOUTH);
		
	}//cons-1
	
	
	public void setComparasion(Comparaison comparaison){
		this.comparaison = comparaison;
	}
	
	public Comparaison getStatistiques(){
		return this.comparaison;
	}
	
	//------------------
	// Méthodes
	//------------------
	public void update(Observable o, Object arg) {
		jlist.removeAll();
		jlist.setModel(new ListAdapter(comparaison.getStatsCourante().getOthersVersion()));
	}
	
	
	public void setEnabled(boolean b){
		jlist.setEnabled(b);
		lancerComp.setEnabled(b);
	}
	
	
	/**
	 * Action lancée lorsque l'on clique droit sur le JTree
	 */
	private class ActionClick extends MouseAdapter{

		public void mousePressed(MouseEvent e) {
			if(e.getClickCount() == 2){
				File selected = (File)jlist.getSelectedValue();
				if(selected!=null){
	
	
					setEnabled(false);
					vueOnglets.setEnabled(false);
					//vueProgressBar.setStatistiques(stats);
					vueOnglets.setOnglet(2);
					//vueConsole.reset();
					
					// Ajout des observers
					comparaison.addObserver(vueOnglets.getVueComparaison().getVueInfosStatistiques1());
					comparaison.addObserver(vueOnglets.getVueComparaison().getVueAnalyse().getVueAnalyseInfos());
					comparaison.addObserver(vueOnglets.getVueComparaison().getVueAnalyse().getVueAnalyseList());
					comparaison.addObserver(vueOnglets.getVueComparaison().getVueInfosStatistiques2());
					comparaison.setStats2(new Statistiques(selected));
					vueOnglets.getVueComparaison().setComparaison(comparaison);
					
					// Nouveau processus pour lancer le process
					Thread t = new Thread(new Runnable() {
						public void run() {
							comparaison.init();
							vueOnglets.setEnabled(true);
							setEnabled(true);
						}
					});
					t.start();
				}
			}
		}
		
	}
	
	/**
	 * Action qui lance la comparaison
	 */
	private class ActionLancerComparaison implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File selected = (File)jlist.getSelectedValue();
			if(selected!=null){


				setEnabled(false);
				vueOnglets.setEnabled(false);
				//vueProgressBar.setStatistiques(stats);
				vueOnglets.setOnglet(2);
				//vueConsole.reset();
				
				// Ajout des observers
				comparaison.addObserver(vueOnglets.getVueComparaison().getVueInfosStatistiques1());
				comparaison.addObserver(vueOnglets.getVueComparaison().getVueAnalyse().getVueAnalyseInfos());
				comparaison.addObserver(vueOnglets.getVueComparaison().getVueAnalyse().getVueAnalyseList());
				comparaison.addObserver(vueOnglets.getVueComparaison().getVueInfosStatistiques2());
				comparaison.setStats2(new Statistiques(selected));
				vueOnglets.getVueComparaison().setComparaison(comparaison);
				
				// Nouveau processus pour lancer le process
				Thread t = new Thread(new Runnable() {
					public void run() {
						comparaison.init();
						vueOnglets.setEnabled(true);
						setEnabled(true);
					}
				});
				t.start();
			}
		}

		
	}
	
}
