/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package capture;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	private int min;
	private int max;
	
	//------------------
	//Constructeur
	//------------------
	public VueContraintes(Aspirateur aspi, int min, int max){
		this.aspi=aspi;
		this.min = min;
		this.max = max;
		value = min;
		// Creation des elements graphiques
		incrP = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("boutons_plus.png")));
		incrM = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("boutons_moins.png")));
		
		if(value == -1){
			compteur = new JLabel("No");
		}else{
			compteur = new JLabel(""+value);
		}
		incrP.setPreferredSize(new Dimension(15,15));
		incrM.setPreferredSize(new Dimension(15,15));
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(incrM)
	                    )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
		                    .addComponent(compteur)
		                )
	    	            .addGap(5)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addComponent(incrP)
		                )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	          .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(incrM)
		                    .addComponent(compteur)
		                    .addComponent(incrP)
	                )
	          )
	    );
   
        // Le gridlayout vient se confondre avec la fenêtre  
		/*add(incrM, BorderLayout.WEST);
		add(compteur, SwingUtilities.CENTER);
		add(incrP, BorderLayout.EAST);*/
		
		
		// Ajout des actions
		incrP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				increment();
			}
			
		});

		incrM.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				decrement();
			}
			
		});
	}
	
	//------------------
	// Méthodes
	//------------------
	
	public void setEnabled(boolean b){
		incrP.setEnabled(b);
		incrM.setEnabled(b);
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setValue(int val){
		this.value = val;
		if(value > -1){
			compteur.setText(value+"");
		}else{
			compteur.setText("No");
		}
	}
	
	public void increment(){
		if(value < max){
			value++;
		}
		compteur.setText(value+"");
	}
	
	public void decrement(){
		if(value > min){
			value--;
		}
		if(value > -1){
			compteur.setText(value+"");
		}else{
			compteur.setText("No");
		}
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