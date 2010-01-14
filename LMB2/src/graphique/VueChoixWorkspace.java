/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;

import Aspirateur.*;

public class VueChoixWorkspace extends JFrame {

	//------------------
	// Attributs
	//------------------
	public Aspirateur laspirateur;
	
	public JLabel message;
	public JTextField value;
	public JButton parcourir;
	public JButton valider;
	
	//------------------
	// Constructeur
	//------------------
	public VueChoixWorkspace(Aspirateur laspirateur){
		super("Aspirateur - LMB2");
		this.laspirateur = laspirateur;
		
		// Creation des elements graphiques
		message = new JLabel("Sélectionnez votre workspace");
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setForeground(Color.blue);
		
		value = new JTextField("/users/renaudmathieu/Desktop/LMB2/",25);
		
		parcourir = new JButton("...");
		parcourir.setPreferredSize(new Dimension(20,20));
		parcourir.setToolTipText("Choisir un autre répertoire comme workspace");
		
		valider = new JButton("Valider");

		// Ajout des elements graphiques
		setLayout(new BorderLayout());
		JPanel global = new JPanel(new BorderLayout());
		
		JPanel haut = new JPanel();
		haut.setBackground(new Color(150,200,250));
		global.setBorder(BorderFactory.createLineBorder(Color.black));
		haut.add(message);
		
		JPanel milieu = new JPanel();
		milieu.setBackground(new Color(150,200,250));
		milieu.add(value);
		milieu.add(parcourir);
		
		JPanel bas = new JPanel();
		bas.setBackground(new Color(150,200,250));
		bas.add(valider);
		
		global.add(haut, BorderLayout.NORTH);
		global.add(milieu, BorderLayout.CENTER);
		global.add(bas, BorderLayout.SOUTH);
		
		this.add(global,BorderLayout.EAST);
		
		// Actions
		valider.addActionListener(new ActionDefinirPath());
		parcourir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	//System.out.println(value.getText());
            	JFileChooser chooser = new JFileChooser();
            	chooser.setApproveButtonText("Choisir ce repertoire comme workspace...");
            	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            	int status = chooser.showOpenDialog(null);
 
                if (status == JFileChooser.APPROVE_OPTION) {
                	value.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            	
            }
        });
		
		value.addKeyListener(new ClavierDefinirPath());
		
		//---
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//cons-1
	
	//------------------
	// Méthodes
	//------------------
	
	/**
	 * Action qui lance le projet dans le workspace choisi
	 */
	private class ActionDefinirPath implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			File test = new File(value.getText());
			
			if(test.isDirectory()){
				laspirateur.setPath(test.getPath());
				IGAspirateur ig = new IGAspirateur(laspirateur);
				setVisible(false);
	            dispose();
			}else{
				JOptionPane.showMessageDialog(null,"Vous avez choisi un espace de travail incorrect.","Attention",JOptionPane.WARNING_MESSAGE);
			}
		}
	}//ActionDefinirPath
	
	/**
	 * Action qui lance le projet dans le wokspace choisi apres avoir tapé sur la touche entrée
	 */
	private class ClavierDefinirPath implements KeyListener{

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 10){
				File test = new File(value.getText());
				
				if(test.isDirectory()){
					laspirateur.setPath(test.getPath());
					IGAspirateur ig = new IGAspirateur(laspirateur);
					setVisible(false);
		            dispose();
				}else{
					JOptionPane.showMessageDialog(null,"Vous avez choisi un espace de travail incorrect.","Attention",JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		public void keyReleased(KeyEvent e) {}

		public void keyTyped(KeyEvent e) {}
		
	}//ClavierDefinirPath
}
