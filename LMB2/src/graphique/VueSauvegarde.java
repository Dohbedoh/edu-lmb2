/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.event.*;

import Aspirateur.*;
import visualiser.*;

public class VueSauvegarde extends JPanel implements Observer{

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private DefaultTreeModel treeModel;
	private String pathRoot;
	
	private JButton visualisation;
	private JButton refresh;
	
	private String selectedNode;
	
	//------------------
	// Constructeurs
	//------------------
	public VueSauvegarde(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		setLayout(new BorderLayout());
		
		laspirateur.addObserver(this);
		// Creation des elements
		pathRoot = laspirateur.getPath();
		racine = new DefaultMutableTreeNode();
		initArbre(racine);
		visualisation = new JButton("Visualiser");
		refresh = new JButton("Rafraichir");
		
		
		// Création du Modele
		treeModel = new DefaultTreeModel(racine);
		
		// Création du JTree
		arbre = new JTree(this.racine);
		arbre.setModel(treeModel);
		arbre.setEditable(true);
		
		// Ajout d'actions
		arbre.addTreeSelectionListener(new ActionSelectionArbre());		
		
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
				((DefaultMutableTreeNode)arbre.getModel().getRoot()).removeAllChildren(); 
				initArbre(racine);
				((DefaultTreeModel)arbre.getModel()).reload();
			}
		});
	}//cons-1	
	
	//------------------
	// Methodes
	//------------------
	public void initArbre(DefaultMutableTreeNode racine){
		
		// Creation du workspace
		File workspace = new File(pathRoot);
		if(!workspace.exists()){
			System.out.println("Le workspace spécifié est introuvable");
			//workspace.mkdirs();
		}
				
		for(File file : workspace.listFiles()) {
			//System.out.println(file.getName());
			if(!file.isHidden()){
				DefaultMutableTreeNode courant = new DefaultMutableTreeNode(file.getName());
				try {
					for(File nom : file.listFiles()){
						if(nom.getName().matches("[0-9]{13}")){
							
							/*
							// On converti le timestamp
							Date currentDate = new Date(Long.parseLong(nom.getName()));
							int heure = currentDate.getHours();
							int minutes = currentDate.getMinutes();
							int secondes = currentDate.getSeconds();
							String date = heure+":"+minutes+":"+secondes;
							*/
							String date = nom.getName();
							
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(date+"/");
							courant.add(this.listFile(nom, node));
						}
					}
				} catch (NullPointerException e) {}
				
				this.racine.add(courant);
			}
		}
		
		
	}
	
	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
		
		if(file.isFile()){
			return new DefaultMutableTreeNode(file.getName());
		}else{
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

	public void update(Observable o, Object arg) {
		((DefaultMutableTreeNode)arbre.getModel().getRoot()).removeAllChildren(); 
		initArbre(racine);
		//((DefaultTreeModel)arbre.getModel()).reload();
	}
	
	//------------------
	// Actions
	//------------------
	private class ActionSelectionArbre implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent event) {
			if(arbre.getLastSelectedPathComponent() != null){
				String value = arbre.getLastSelectedPathComponent().toString();
				
				// Si on est dans une sauvegarde
				if(value.matches("[0-9]{13}/")){
					visualisation.setEnabled(true);
					// On va chercher le chemin absolu de index.html ou index.php qui est contenu dans ce repertoire
					selectedNode = laspirateur.getPath()+((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent()).getParent()+"/"+value;
				}else{
					visualisation.setEnabled(false);
				}
			}
		}
	}
	
	private class ActionVisualiser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (selectedNode != null) {
				new Navigateur(selectedNode);
			}
		}
	}
	
}
