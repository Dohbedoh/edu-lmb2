/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package statistiques;


import graphique.LinkListAdapter;
import graphique.LinkListFormateur;
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

import visualiser.BareBonesBrowserLaunch;

public class VueAnalyseList extends JPanel implements Observer{
	
	//------------------
	// Attributs
	//------------------
	private Statistiques stats;
	private JList jlist;
	private JButton imagesBut, addrBut, cssBut, jsBut, tousBut, filtredBut, mortsBut;
	
	private JPopupMenu menu;
	private JMenuItem visualiserSelection;
	private File selected;
	
	private JScrollPane scroll;
	//------------------
	// Constructeurs
	//------------------
	public VueAnalyseList(Statistiques stats){
		this.stats = stats;
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

		tousBut = new JButton("Voir Tout");
		imagesBut = new JButton("Voir Images");
		addrBut = new JButton("Voir Adresses");
		cssBut = new JButton("Voir CSS");
		jsBut = new JButton("Voir JS");
		mortsBut = new JButton("Voir Morts");
		filtredBut = new JButton("Voir Filtrés");
		
		tousBut.setMinimumSize(new Dimension(100,20));
		imagesBut.setMinimumSize(new Dimension(100,20));
		addrBut.setMinimumSize(new Dimension(100,20));
		cssBut.setMinimumSize(new Dimension(100,20));
		jsBut.setMinimumSize(new Dimension(100,20));
		mortsBut.setMinimumSize(new Dimension(100,20));
		filtredBut.setMinimumSize(new Dimension(100,20));
		
		menu = new JPopupMenu();
		visualiserSelection = new JMenuItem("Visualiser");
		visualiserSelection.addActionListener(new ActionVisualiserSelection());
		menu.add(visualiserSelection);
		
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    		.addComponent(imagesBut)
                        		.addComponent(mortsBut)
	                    )
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                        .addComponent(addrBut)
                        		.addComponent(filtredBut)
		                )
	    	            .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        		.addComponent(cssBut)
                        )
        	    	    .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(jsBut)
                        )
        	    	    .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        		.addComponent(tousBut)
                        )
	                )
	            )
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(imagesBut)
	                		.addComponent(addrBut)
	                		.addComponent(cssBut)
	                		.addComponent(jsBut)
	                		.addComponent(tousBut)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                		.addComponent(mortsBut)
	                		.addComponent(filtredBut)
	                )
	                .addGap(10)
	          )
		);

		add(new JScrollPane(jlist),BorderLayout.CENTER);
		add(cont,BorderLayout.SOUTH);
		
		
		// Actions
		imagesBut.addActionListener(new ActionLoadImages());
		addrBut.addActionListener(new ActionLoadAdress());
		cssBut.addActionListener(new ActionLoadCSS());
		jsBut.addActionListener(new ActionLoadJS());
		tousBut.addActionListener(new ActionLoadTout());
		mortsBut.addActionListener(new ActionLoadMorts());
		filtredBut.addActionListener(new ActionLoadFiltred());
		jlist.addMouseListener(new ActionClickDroit());
		
	}

	public void setEnabled(boolean b){
		imagesBut.setEnabled(b);
		addrBut.setEnabled(b);
		cssBut.setEnabled(b);
		jsBut.setEnabled(b);
		tousBut.setEnabled(b);
		mortsBut.setEnabled(b);
		filtredBut.setEnabled(b);
	}
	
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
		
		// Par défaut on chargera toutes les pages Enregistrees
		jlist.setModel(new ListAdapter(stats.getLesFichiersEnregistres()));

	}
	
	//------------------
	// Actions
	//------------------
	private class ActionLoadImages implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new ListFormateur());
			jlist.setModel(new ListAdapter(stats.getDataImages()));
		}
	}
	
	private class ActionLoadAdress implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new ListFormateur());
			jlist.setListData(stats.getMetaData().getMailTosList().toArray());
		}
	}
	
	private class ActionLoadCSS implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new ListFormateur());
			jlist.setModel(new ListAdapter(stats.getDataCSS()));
		}
	}
	
	private class ActionLoadJS implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new ListFormateur());
			jlist.setModel(new ListAdapter(stats.getDataJS()));
		}
	}
	
	private class ActionLoadTout implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new ListFormateur());
			jlist.setModel(new ListAdapter(stats.getLesFichiersEnregistres()));
		}
	}
	
	private class ActionLoadFiltred implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new LinkListFormateur());
			jlist.setModel(new LinkListAdapter(stats.getMetaData().getFiltredLinks()));
		}
	}
	
	private class ActionLoadMorts implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			jlist.removeAll();
			jlist.setCellRenderer(new LinkListFormateur());
			jlist.setModel(new LinkListAdapter(stats.getMetaData().getBrokenLinks()));
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
