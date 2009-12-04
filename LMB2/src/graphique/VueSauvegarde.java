/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import org.w3c.dom.Document;

import java.io.*;
import java.awt.*;

import Aspirateur.*;


public class VueSauvegarde extends JPanel {

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	
	private JButton visualisation;
	
	//------------------
	// Constructeurs
	//------------------
	public VueSauvegarde(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		setLayout(new BorderLayout());
		
		// Creation des elements
		initArbre();
		visualisation = new JButton("Visualiser");
		
		// Ajout des elements
		JPanel options = new JPanel(new GridLayout(1,2));
		
		options.add(visualisation);
		
		add(new JScrollPane(arbre),BorderLayout.NORTH);
		add(options,BorderLayout.SOUTH);
		
		// Option des elements
		setBorder(BorderFactory.createTitledBorder("Gestion des sauvegardes"));
		arbre.setPreferredSize(new Dimension(200,350));
		arbre.setRootVisible(false);
		
	}//cons-1	
	
	//------------------
	// Methodes
	//------------------
	public void initArbre(){
		
		// Creation de la racine
		this.racine = new DefaultMutableTreeNode();
		File workspace = new File(laspirateur.getPath());
		
		for(File file : workspace.listFiles())
		{
			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getName());
			try {
				for(File nom : file.listFiles()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"/");
					
					lecteur.add(this.listFile(nom, node));
					
				}
			} catch (NullPointerException e) {}
			
			this.racine.add(lecteur);
			
		}
		
		arbre = new JTree(this.racine);
		arbre.addTreeSelectionListener(new TreeSelectionListener(){

			public void valueChanged(TreeSelectionEvent event) {
				
				String value = arbre.getLastSelectedPathComponent().toString();
				
				// Si on est dans une sauvegarde
				if(value.matches(".*-.*-.*")){
					
					// On va chercher le chemin absolu de index.html ou index.php qui est contenu dans ce repertoire
					String url = laspirateur.getPath()+((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent()).getParent()+"/"+value;
					
					//System.out.println(laspirateur.getPath()+((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent()).getParent()+"/"+value);
					
					// On créé un JEditorPane avec ce chemin absolu
					JFrame popup = new JFrame("a");
					
					popup.pack();
					popup.setVisible(true);
					popup.setSize(840,664);
					JEditorPane conteneur;
					
					try {
						File file = new File(url+"index.html");
						if(file != null){
							conteneur = new JEditorPane(file.toURL());
							
						}else{
							File file2 = new File(url+"index.php");
							conteneur = new JEditorPane(file2.toURL());
						}
						
						conteneur.setEditable(false);
						JScrollPane scrollPane = new JScrollPane(conteneur);
						popup.add(scrollPane);
					}catch (IOException e) {e.printStackTrace();}
					
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

}
