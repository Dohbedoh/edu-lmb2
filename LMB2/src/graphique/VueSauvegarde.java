/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import Aspirateur.*;
import visualiser.*;

public class VueSauvegarde extends JPanel{

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private DefaultTreeModel model;
	
	private JButton visualisation;
	private JButton refresh;
	
	private String selectedNode;
	
	//------------------
	// Constructeurs
	//------------------
	public VueSauvegarde(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		setLayout(new BorderLayout());
				
		// Creation des elements
		initArbre();
		visualisation = new JButton("Visualiser");
		refresh = new JButton("Rafraichir");
		
		// Ajout des elements
		JPanel options = new JPanel(new GridLayout(1,2));
		
		options.add(visualisation);
		options.add(refresh);
		
		add(new JScrollPane(arbre),BorderLayout.NORTH);
		add(options,BorderLayout.SOUTH);
		
		// Option des elements
		setBorder(BorderFactory.createTitledBorder("Gestion des sauvegardes"));
		arbre.setPreferredSize(new Dimension(200,350));
		arbre.setRootVisible(false);
		visualisation.setEnabled(false);
		visualisation.addActionListener(new ActionVisualiser());
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.nodeChanged(racine);
			}
		});
	}//cons-1	
	
	//------------------
	// Methodes
	//------------------
	public void initArbre(){
		
		// Creation de la racine
		this.racine = new DefaultMutableTreeNode();
		File workspace = new File(laspirateur.getPath());
		
		for(File file : workspace.listFiles()) {
			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getName());
			try {
				for(File nom : file.listFiles()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"/");
					
					lecteur.add(this.listFile(nom, node));
					
				}
			} catch (NullPointerException e) {}
			
			this.racine.add(lecteur);
			
		}
		
		this.model = new DefaultTreeModel(this.racine);
		this.model.addTreeModelListener(new TreeModelListener() {
			
	        public void treeNodesChanged(TreeModelEvent evt) {
	          System.out.println("Not Yet Implemented.");
	        }
	        
	        
	        public void treeNodesInserted(TreeModelEvent event) {
				System.out.println("Un noeud a été inséré !");
				
			}
	        
	        
			public void treeNodesRemoved(TreeModelEvent event) {
				System.out.println("Un noeud a été retiré !");
			}
			
			public void treeStructureChanged(TreeModelEvent event) {
				System.out.println("La structure d'un noeud a changé !");
			}
        });
		arbre = new JTree(this.racine);
		arbre.setModel(model);
		arbre.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event) {
				if(arbre.getLastSelectedPathComponent() != null){
					String value = arbre.getLastSelectedPathComponent().toString();
					
					// Si on est dans une sauvegarde
					if(value.matches(".*-.*-.*")){
						visualisation.setEnabled(true);
						// On va chercher le chemin absolu de index.html ou index.php qui est contenu dans ce repertoire
						selectedNode = laspirateur.getPath()+((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent()).getParent()+"/"+value;
					}else{
						visualisation.setEnabled(false);
					}
				}
			}
		});
	}
	
	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
		
		if(file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else{
			for(File nom : file.listFiles()){
				DefaultMutableTreeNode subNode;
				if(nom.isDirectory()){
					subNode = new DefaultMutableTreeNode(nom.getName()+"/");
					node.add(this.listFile(nom, subNode));
				}else{
					subNode = new DefaultMutableTreeNode(nom.getName());
				}
				node.add(subNode);
			}
			return node;
		}
	}

	//------------------
	// Actions
	//------------------
	private class ActionVisualiser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (selectedNode != null) {
				new Navigateur(selectedNode);
			}
		}
	}
	
}
