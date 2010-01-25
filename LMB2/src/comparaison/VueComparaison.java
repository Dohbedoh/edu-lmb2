/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package comparaison;

import graphique.ListFormateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class VueComparaison extends JFrame{

	//------------------
	// Attributs
	//------------------
	private ArrayList<String> fichiersSup;
	private ArrayList<String> fichiersAj;
	private ArrayList<String> fichiersMod;
	private JLabel fichiersSupLab;
	private JLabel fichiersAjLab;
	private JLabel fichiersModLab;
	private JLabel nbFichiersSup;
	private JLabel nbFichiersAj;
	private JLabel nbFichiersMod;
	private JButton fichiersSupBut;
	private JButton fichiersAjBut;
	private JButton fichiersModBut;
	private JList jlist;
	private String dir1,dir2;
	
	//------------------
	// Constructeurs
	//------------------
	public VueComparaison(String dir1, String dir2){
		super("Aspirateur - LMB2");
		setLayout(new BorderLayout(10,10));
		
		fichiersSup = new ArrayList<String>();
		fichiersAj = new ArrayList<String>();
		fichiersMod = new ArrayList<String>();
		
		fichiersAjLab = new JLabel("• Fichiers ajoutés : ");
		fichiersSupLab = new JLabel("• Fichiers supprimés : ");
		fichiersModLab = new JLabel("• Fichiers modifiés : ");
		nbFichiersAj = new JLabel("N.C");
		nbFichiersSup = new JLabel("N.C");
		nbFichiersMod = new JLabel("N.C");
		fichiersSupBut = new JButton("Voir supprimés");
		fichiersAjBut = new JButton("Voir ajoutés");
		fichiersModBut = new JButton("Voir modifiés");
		
		fichiersSupBut.setMinimumSize(new Dimension(150,20));
		fichiersModBut.setMinimumSize(new Dimension(150,20));
		fichiersAjBut.setMinimumSize(new Dimension(150,20));

		fichiersSupLab.setFont(new Font(null,1,11));
		nbFichiersSup.setFont(new Font(null,1,11));
		nbFichiersSup.setForeground(new Color(51,204,0));
		fichiersAjLab.setFont(new Font(null,1,11));
		nbFichiersAj.setFont(new Font(null,1,11));
		nbFichiersAj.setForeground(new Color(51,204,0));
		fichiersModLab.setFont(new Font(null,1,11));
		nbFichiersMod.setFont(new Font(null,1,11));
		nbFichiersMod.setForeground(new Color(51,204,0));
		
		Container west = new Container();
		GroupLayout layout = new GroupLayout(west);
		west.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(fichiersAjLab)
	                        .addComponent(fichiersModLab)
	                        .addComponent(fichiersSupLab)
	                    )
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(nbFichiersAj)
		                    .addComponent(nbFichiersMod)
		                    .addComponent(nbFichiersSup)
		                )
	                )
	            )
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
		    	            .addGap(10)
		                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    	.addComponent(fichiersAjBut)
			                    .addComponent(fichiersModBut)
			                    .addComponent(fichiersSupBut)
			                )
		                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            // Le groupe pour la saisie des infos
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(fichiersAjLab)
		                    .addComponent(nbFichiersAj)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(fichiersModLab)
		                    .addComponent(nbFichiersMod)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(fichiersSupLab)
		                    .addComponent(nbFichiersSup)
	                )
	             )
	             .addGroup(layout.createSequentialGroup()
	            		.addGap(10)
	 	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    	.addComponent(fichiersAjBut)
		                )
	            		.addGap(10)
	 	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    	.addComponent(fichiersModBut)
		                )
	            		.addGap(10)
	 	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                    	.addComponent(fichiersSupBut)
		                )
		         )
		         .addGap(10)
	    );
		
		jlist = new JList();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.setCellRenderer(new ListFormateur());
		
		// Ajouts des élements graphiques
		add(west,BorderLayout.WEST);
		add(new JScrollPane(jlist),BorderLayout.CENTER);
		
		// Options de la JFrame
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		//setPreferredSize(new Dimension(800,800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//cons-1
	

	public static void main(String[] args){
		
		// TEST
		VueComparaison vueComparaison = new VueComparaison("doh1", "doh2");
	}
	
}