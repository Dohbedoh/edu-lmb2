/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;


import graphique.ListAdapter;
import graphique.ListFormateur;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import statistiques.Comparaison;
import statistiques.Statistiques;
import visualiser.BareBonesBrowserLaunch;

public class VueAnalyseComparaisonList extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Comparaison comparaison;
	private JList jlist;
	private JButton ajBut, modBut, suppBut;
	
	private JPopupMenu menu;
	private JMenuItem visualiserSelection;
	private File selected;
	
	private JScrollPane scroll;
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseComparaisonList(Comparaison comparaison){
		this.comparaison = comparaison;
		this.setLayout(new BorderLayout(5,5));
		
		TitledBorder afact = BorderFactory.createTitledBorder("Listes de Fichiers");
		afact.setTitleJustification(TitledBorder.CENTER);
		setBorder(afact);
		
		jlist = new JList();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setCellRenderer(new ListFormateur());
		
		Container cont = new Container();
		GroupLayout layout = new GroupLayout(cont);
		cont.setLayout(layout);

		ajBut = new JButton("Voir Ajoutés");
		modBut = new JButton("Voir Modifiés");
		suppBut = new JButton("Voir Supprimés");
		
		menu = new JPopupMenu();
		visualiserSelection = new JMenuItem("Visualiser");
		visualiserSelection.addActionListener(new ActionVisualiserSelection());
		menu.add(visualiserSelection);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    		.addComponent(ajBut)
	                    )
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                        .addComponent(modBut)
		                )
	    	            .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(suppBut)
                        )
	                )
	            )
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(ajBut)
	                		.addComponent(modBut)
	                		.addComponent(suppBut)
	                )
	                .addGap(10)
	          )
		);

		add(new JScrollPane(jlist),BorderLayout.CENTER);
		add(cont,BorderLayout.SOUTH);
		
		
		// Actions
		ajBut.addActionListener(new ActionLoadAj());
		modBut.addActionListener(new ActionLoadMod());
		suppBut.addActionListener(new ActionLoadSupp());
		jlist.addMouseListener(new ActionClickDroit());
		
	}

	public void setEnabled(boolean b){
		ajBut.setEnabled(b);
		modBut.setEnabled(b);
		suppBut.setEnabled(b);
	}
	
	public void setComparaison(Comparaison comparaison){
		this.comparaison = comparaison;
	}
	
	public Comparaison getComparaison(){
		return this.comparaison;
	}
	
	//------------------
	// Méthodes
	//------------------
	public void update(Observable o, Object arg) {

	}
	
	//------------------
	// Actions
	//------------------
	private class ActionLoadAj implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(comparaison.getLesFichiersAjoutes()));
		}
	}
	
	private class ActionLoadMod implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(comparaison.getLesFichiersModifies()));
		}
	}
	
	private class ActionLoadSupp implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setModel(new ListAdapter(comparaison.getLesFichiersSupprimes()));
		}
	}
	
	/**
	 * Action lancée lorsque l'on clique droit sur le JTree
	 */
	private class ActionClickDroit extends MouseAdapter{

		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				if(!jlist.isSelectionEmpty()){
					if(jlist.getSelectedValue() instanceof File){
						selected = (File)jlist.getSelectedValue();
						visualiserSelection.setText("Visualiser");
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
			if (e.getClickCount() == 2) {
				if(jlist.getSelectedValue() instanceof File){
					selected = (File)jlist.getSelectedValue();
					URL url;
					try {
						url = selected.toURL();
						BareBonesBrowserLaunch.openURL(url.toString());
					} catch (MalformedURLException er) {er.printStackTrace();}
				}
				
			}
		}
		
	}
	
	private class ActionVisualiserSelection implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(jlist.getSelectedValue() instanceof File){
				selected = (File)jlist.getSelectedValue();
				URL url;
				try {
					url = selected.toURL();
					BareBonesBrowserLaunch.openURL(url.toString());
				} catch (MalformedURLException e) {e.printStackTrace();}
			}
			
		}
	}
	
}
