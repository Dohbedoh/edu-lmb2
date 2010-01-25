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
	private Statistiques stats;
	private JList jlist;
	private JButton lancerComp;
	private VueOnglets vueOnglets;
	
	//------------------
	// Constructeur
	//------------------
	public VueComparaisonVersion(Statistiques stats, VueOnglets vueOnglets){
		this.stats = stats;
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
	
	
	public void setStatistiques(Statistiques stats){
		this.stats = stats;
	}
	
	public Statistiques getStatistiques(){
		return this.stats;
	}
	
	//------------------
	// Méthodes
	//------------------
	public void update(Observable o, Object arg) {
		jlist.removeAll();
		jlist.setModel(new ListAdapter(stats.getOthersVersion()));
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
			File selected = (File)jlist.getSelectedValue();
			if(selected!=null){
				vueOnglets.getVueComparaison().setEnabled(true);
				//vueOnglets.getVueComparaison().getComparaison().setStatsCourante(stats);
				vueOnglets.getVueComparaison().getComparaison().setStats2(new Statistiques(selected));
				/**
				 * FAIRE ICI LE TRAITEMENT
				 */
				
				System.err.println("******Lancer Comparaison********");
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
				vueOnglets.getVueComparaison().setEnabled(true);
				//vueOnglets.getVueComparaison().getComparaison().setStatsCourante(stats);
				vueOnglets.getVueComparaison().getComparaison().setStats2(new Statistiques(selected));
				/**
				 * FAIRE ICI LE TRAITEMENT
				 */
				
				System.err.println("******Lancer Comparaison********");
			}
		}

		
	}
	
}
