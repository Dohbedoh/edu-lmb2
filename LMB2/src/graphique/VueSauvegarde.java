/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
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
	private JLabel infos;
	private String selectedNode;
	private VueStatistiques vueStatistiques;
	
	//------------------
	// Constructeurs
	//------------------
	public VueSauvegarde(Aspirateur laspirateur){
		this.laspirateur = laspirateur;
		setLayout(new BorderLayout());
		
		laspirateur.addObserver(this);
		
		// Creation des elements
		racine = new DefaultMutableTreeNode();
		initArbre(racine);
		
		visualisation = new JButton("Visualiser");
		visualisation.setPreferredSize(new Dimension(80,20));
		visualisation.setToolTipText("Lancer la visualisation dans votre navigateur par défaut");
		
		refresh = new JButton("Rafraichir");
		refresh.setPreferredSize(new Dimension(80,20));
		refresh.setToolTipText("Actualiser les sauvegardes");
		
		infos = new JLabel("",SwingConstants.CENTER);
		infos.setForeground(Color.GRAY);
		infos.setToolTipText("Date de capture");
		
		
		
		// Création du Modele
		treeModel = new DefaultTreeModel(racine);
		
		// Création du JTree
		arbre = new JTree(this.racine);
		arbre.setModel(treeModel);
		arbre.setEditable(true);
		
		// Ajout d'actions
		arbre.addTreeSelectionListener(new ActionSelectionArbre());
		arbre.addMouseListener(new ActionClikDroit());
		
		// Ajout des elements
		JPanel options = new JPanel();
		
		options.add(visualisation);
		options.add(refresh);
		
		add(new JScrollPane(arbre),BorderLayout.NORTH);
		add(options,BorderLayout.CENTER);
		add(infos,BorderLayout.SOUTH);
		
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
		pathRoot = laspirateur.getPath();
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
							String date = nom.getName();
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(date+"/");
							courant.add(this.listFile(nom, node));
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

	public boolean version(TreePath treePath){
		if(arbre.getLastSelectedPathComponent() != null){
			String value = arbre.getLastSelectedPathComponent().toString();
			
			File file= new File(getAbsolutePath(treePath));
			getDescription(file);
			
			if(value.matches("[0-9]{13}/")){
				selectedNode = laspirateur.getPath()+"/"+((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent()).getParent()+"/"+value;
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void update(Observable o, Object arg) {
		((DefaultMutableTreeNode)arbre.getModel().getRoot()).removeAllChildren(); 
		initArbre(racine);
		
		// Rechargement en continu pendant une capture
		//((DefaultTreeModel)arbre.getModel()).reload();
		
		arbre.setPreferredSize(arbre.getMinimumSize());
	}
	
	private String getAbsolutePath(TreePath treePath){
		String str = "";
		
		for(Object name : treePath.getPath()){
			
			if(name.toString() != null)
				str += name.toString()+"/";
		}
		return str;
	}
	
	private void getDescription(File file){ 
		
		String str = "";
		//str = "Chemin :"+file.getAbsolutePath();
		//str += "  "+file.getName();
		
		if(file.getName().matches("[0-9]{13}")){
		
			// On converti le timestamp
			Date currentDate = new Date(Long.parseLong(file.getName()));
			int heure = currentDate.getHours();
			int minutes = currentDate.getMinutes();
			int secondes = currentDate.getSeconds();
			
			
			
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRANCE);
			str = df.format(currentDate);
			str += " - "+heure+":"+minutes+":"+secondes;
		}
		
		infos.setText(str);
	}
	
	//------------------
	// Actions
	//------------------
	private class ActionSelectionArbre implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent event) {
			if(version(event.getPath())){
				System.err.println(selectedNode);
				visualisation.setEnabled(true);
			}else{
				visualisation.setEnabled(false);
			}
		}
	}
	
	private class ActionVisualiser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (selectedNode != null) {
				//new Navigateur(selectedNode);
				try{
					File file = new File(selectedNode + "index.html");
					URL url;
					
					if (file != null) {
						url = file.toURL();
						BareBonesBrowserLaunch.openURL(url.toString());
					} else {
						File file2 = new File(selectedNode + "index.php");
						url = file2.toURL();
						BareBonesBrowserLaunch.openURL(url.toString());
					}
					
				} catch (IOException ex) { ex.printStackTrace(); }
				 
			}
		}
	}
	
	/**
	 * Action lancée lorsque l'on sélectionne "Lancer Les Statistiques" dans le clique droit du JTree 
	 * @author Stolen_Flame_57
	 *
	 */
	private class ActionLancerStat implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	/**
	 * Action lancée lorsque l'on sélectionne "Supprimer" dans le clique droit du JTree 
	 * @author Stolen_Flame_57
	 *
	 */
	private class ActionDelete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	
	/**
	 * Action lancée lorsque l'on clique droit sur le JTree
	 * @author Stolen_Flame_57
	 *
	 */
	private class ActionClikDroit extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isRightMouseButton(e)) {
				// System.out.println("click Right");
				int selRow = arbre.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = arbre.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					arbre.clearSelection();
					arbre.setSelectionPath(selPath);
					selectedNode = laspirateur.getPath()+"/"+((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent()).getParent()+"/"+arbre.getLastSelectedPathComponent();
					System.err.println(selectedNode);
				    JPopupMenu menu = new JPopupMenu();
					JMenuItem lancerStat = new JMenuItem("Lancer les Statistiques");
					lancerStat.addActionListener(new ActionLancerStat());
					
					JMenuItem delete = new JMenuItem("Supprimer");
					delete.addActionListener(new ActionDelete());
					
					menu.add(lancerStat);
					menu.add(delete);
					add(menu);
				    menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
		
	}
	
}
