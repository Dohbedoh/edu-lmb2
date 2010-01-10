/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Aspirateur.Aspirateur;

public class VueContraintes extends JPanel{

	//------------------
	// Attributs 
	//------------------
	
	public Aspirateur aspi;
	public JButton incrP;
	public JButton incrM;
	JLabel compteur;
	int value;
	
	//------------------
	//Constructeur
	//------------------
	public VueContraintes(Aspirateur aspi){
		this.aspi=aspi;
		
		value = -1;
		// Creation des elements graphiques
		incrP = new JButton(new ImageIcon("boutons_plus.png"));
		incrM = new JButton(new ImageIcon("boutons_moins.png"));
		
		compteur = new JLabel(""+value);
		incrP.setPreferredSize(new Dimension(10,10));
		incrM.setPreferredSize(new Dimension(10,10));
		
		// Ajout des elements dans le conteneur
		   
        // Le gridlayout vient se confondre avec la fenêtre  
		add(incrM, BorderLayout.NORTH);
		add(compteur);
		add(incrP, BorderLayout.SOUTH);
		
		
		// Ajout des actions
		incrP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				value++;
				compteur.setText(value+"");
			}
			
		});

		incrM.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(value > -1){
					value--;
				}
				if(value >= -1){
					compteur.setText(value+"");
				}else{
					compteur.setText("No");
				}
			}
			
		});
		
	}
	
	//------------------
	// Méthodes
	//------------------
	public int getValue(){
		return this.value;
	}
	
	public void setValue(int val){
		this.value = val;
	}
	
	/*
	// TEST
	public static void main(String[] args){
		JFrame fp = new JFrame("Test");
		fp.add(new VueContraintes(new Aspirateur()));
		
		fp.pack();
		fp.setVisible(true);
	}
	*/
}