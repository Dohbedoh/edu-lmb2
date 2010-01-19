/**
 * @author BESLUAU Gregoire, BURDAJEWICZ Allan, LARAKI Meryem, MATHIEU Renaud
 */

package aide;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VueAideStatistiques extends JPanel{
	private static final long serialVersionUID = 1L;
	
	//------------------
	// Attributs
	//------------------
	private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
	

	//------------------
	// Constructeur
	//------------------
	public VueAideStatistiques(){
		 jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel10 = new javax.swing.JLabel();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jLabel13 = new javax.swing.JLabel();
	        jLabel14 = new javax.swing.JLabel();
	        jLabel15 = new javax.swing.JLabel();
	        jLabel16 = new javax.swing.JLabel();
	        jLabel17 = new javax.swing.JLabel();
	        jLabel18 = new javax.swing.JLabel();
	        jLabel19 = new javax.swing.JLabel();
	        jLabel20 = new javax.swing.JLabel();
	        jLabel21 = new javax.swing.JLabel();
	        jLabel22 = new javax.swing.JLabel();
	        jLabel23 = new javax.swing.JLabel();
	        jLabel24 = new javax.swing.JLabel();

	        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
	        jLabel1.setText("Attention ! Avant de pouvoir utiliser cet onglet, il faut calculer les statistiques du site de votre choix sauvegardé précédemment.");

	        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel2.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel2.setText("Comment calculer les statistiques correspondant à un site? ");

	        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel3.setText("Pour cela, il vous suffit de double cliquer sur le nom du dossier contenant votre site dans la liste à gauche de la fenêtre, puis de cliquer");

	        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel4.setText("avec le boutont droit de la souris sur le nom de dossier formé seulement par des chiffres et d'appuyer sur \\\"Lancer les statistiques\\\". ");

	        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel5.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel5.setText("Comment vérifier la liste et la fréquence des mots contenus dans un site ? ");

	        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel6.setText("Dans longlet \"Statistiques\", dans le bloc \"Analyse du Contenu\" à gauche, appuyez sur le bouton \"Statistiques sur la fréquence des Mots\",");

	        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel7.setText("une fenêtre apparaitra alors présentant un tableau et un diagramme citant tous les mots qui se trouvent sur le site et leurs occurences.");

	        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel8.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel8.setText("Comment vérifier la liste et la fréquence des liens hypertextes contenus dans un site ?");

	        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel9.setText("Dans longlet \"Statistiques\", dans le bloc \"Analyse du Contenu\" à gauche, appuyez sur le bouton \"Statistiques sur les liens hypertextes\",");

	        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel10.setText("une fenêtre apparaitra alors présentant un tableau et un diagramme citant tous les liens hypertextes qui se trouvent sur le site et leurs");

	        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel11.setText("occurences.");

	        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel12.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel12.setText("Comment vérifier la liste et la fréquence des balises contenues dans un site ?");

	        jLabel13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel13.setText("Dans longlet \"Statistiques\", dans le bloc \"Analyse du Contenu\" à gauche, appuyez sur le bouton \"Statistiques sur les balises (tags)\", une");

	        jLabel14.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel14.setText("fenêtre apparaitra alors présentant un tableau et un diagramme citant tous les types de balises se trouvant sur le site et leurs ");

	        jLabel15.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel15.setText("occurences.");

	        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel16.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel16.setText("Comment visualiser les fichiers extraits du site ?");

	        jLabel17.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel17.setText("Dans longlet \"Statistiques\", dans le bloc \"Liste de Fichiers\" à droite, double-cliquez sur le fichier que vous voulez voir, ou selectionnez-le");

	        jLabel18.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel18.setText("puis cliquez dessus avec le bouton droit de la souris et appuyez sur ouvrir, le contenu sera affiché avec le logiciel qui lui correspond.");

	        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel19.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel19.setText("Comment afficher uniquement un type de fichiers dans le bloc \"Liste de Fichiers\" ?");

	        jLabel20.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel20.setText("Pour cela, il vous suffit d'appuyer sur les des boutons se trouvant en bas du bloc \"Liste de Fichiers\" dans longlet \"Statistiques\", la liste");

	        jLabel21.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel21.setText("de fichiers affichée sera réduite à la liste des fichiers correspondant au type que vous avez choisi d'afficher.");

	        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
	        jLabel22.setForeground(new java.awt.Color(150, 200, 250));
	        jLabel22.setText("Comment réafficher les type de fichiers dans le bloc \"Liste de Fichiers\" ?");

	        jLabel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel23.setText("En appuyant sur le bouton \"Tout voir\" en bas du bloc \"Liste de Fichiers\" dans longlet \"Statistiques\", tous les fichiers contenus dans le");

	        jLabel24.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
	        jLabel24.setText("site seront listés.");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addContainerGap()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2)
	                            .addComponent(jLabel3)
	                            .addComponent(jLabel4)
	                            .addComponent(jLabel5)
	                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jLabel7)
	                            .addComponent(jLabel8)
	                            .addComponent(jLabel9)
	                            .addComponent(jLabel10)
	                            .addComponent(jLabel11)
	                            .addComponent(jLabel12)
	                            .addComponent(jLabel13)
	                            .addComponent(jLabel14)
	                            .addComponent(jLabel15)
	                            .addComponent(jLabel16)
	                            .addComponent(jLabel17)
	                            .addComponent(jLabel18)
	                            .addComponent(jLabel19)
	                            .addComponent(jLabel20)
	                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
	                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
	                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
	                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(32, 32, 32)
	                        .addComponent(jLabel1)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1)
	                .addGap(18, 18, 18)
	                .addComponent(jLabel2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel3)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel4)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel5)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel6)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel7)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel8)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel9)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel10)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel11)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel12)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel13)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel14)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel15)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel16)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel17)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel18)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel19)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel20)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel21)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel22)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel23)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel24)
	                .addContainerGap(71, Short.MAX_VALUE))
	        );
	}
	
	//------------------
	// Méthodes
	//------------------
	
}
