/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import statistiques.*;

public class VueComparaisonVersion extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JList jlist;
	
	//------------------
	// Constructeur
	//------------------
	public VueComparaisonVersion(Statistiques stats){
		this.stats = stats;
		
		TitledBorder afact = BorderFactory.createTitledBorder("Comparaison des versions");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		jlist = new JList();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setCellRenderer(new ListFormateur());
		add(new JScrollPane(jlist),BorderLayout.CENTER);
		jlist.addMouseListener(new ActionClick());
		
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
	
	
	/**
	 * Action lancée lorsque l'on clique droit sur le JTree
	 */
	private class ActionClick extends MouseAdapter{

		public void mousePressed(MouseEvent e) {
			if (e.getClickCount() == 2) {
				File selected = (File)jlist.getSelectedValue();
				/**
				 * FAIRE ICI LE TRAITEMENT
				 */
				
				System.err.println("******Lancer Comparaison********");
			}
		}
		
	}
}
