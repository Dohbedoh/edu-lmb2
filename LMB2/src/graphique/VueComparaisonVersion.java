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

import comparaison.VueComparaisonList;

import statistiques.*;

public class VueComparaisonVersion extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JList jlist;
	private JButton lancerComp;
	
	//------------------
	// Constructeur
	//------------------
	public VueComparaisonVersion(Statistiques stats){
		this.stats = stats;
		setLayout(new BorderLayout());
		
		TitledBorder afact = BorderFactory.createTitledBorder("Comparaison des versions");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		lancerComp = new JButton("Lancer la comparaison");
		lancerComp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VueComparaisonList vueComparaison= new VueComparaisonList("doh1","doh2");
			}
		});
		
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
