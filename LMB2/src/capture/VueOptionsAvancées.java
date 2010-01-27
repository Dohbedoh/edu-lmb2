package capture;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import Aspirateur.Aspirateur;

public class VueOptionsAvancées extends JFrame{
	
	private JLabel proxyHostL;
	private JLabel proxyPwdL;
	private JLabel proxyPortL;
	private JLabel proxyUserL;
	private JTextField proxyHost;
	private JPasswordField proxyPwd;
	private JTextField proxyPort;
	private JTextField proxyUser;
	
	private JLabel userL;
	private JLabel pwdL;
	private JTextField user;
	private JPasswordField pwd;
	
	private JButton ok;
	private JButton annuler;
	
	private Aspirateur laspirateur;
	
	public VueOptionsAvancées(Aspirateur laspirateur){
		super("Aspirateur - LMB2");
		this.laspirateur = laspirateur;
		
		ok = new JButton("Ok");
		ok.addActionListener(new ActionOk());
		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionAnnuler());
		
		Container boutons = new Container();
	    GroupLayout layoutBut = new GroupLayout(boutons);
	    boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
	    boutons.add(ok);
	    boutons.add(annuler);
	    
	    layoutBut.setVerticalGroup(layoutBut.createSequentialGroup()
	            .addGroup(layoutBut.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layoutBut.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(ok)
		                	.addComponent(annuler)
	                )
	          )
	    );
		
		
		proxyHostL = new JLabel("Hôte : ");
		proxyHostL.setToolTipText("L'Hôte du Proxy");
		proxyPwdL = new JLabel("Mot de Passe : ");
		proxyPwdL.setToolTipText("Votre mot de passe sur le Proxy");
		proxyPortL = new JLabel("Port : ");
		proxyPortL.setToolTipText("le Port utilisé par le Proxy");
		proxyUserL = new JLabel("User : ");
		proxyUserL.setToolTipText("Votre nom d'utilisateur");
		
		proxyHost = new JTextField(laspirateur.getProxyHost());
		proxyPwd = new JPasswordField(laspirateur.getProxyPassword());
		proxyPort = new JTextField(laspirateur.getProxyPort());
		proxyUser = new JTextField(laspirateur.getProxyUser());
		
		proxyHost.setMinimumSize(new Dimension(300,20));
		proxyHost.setMaximumSize(new Dimension(300,20));
		proxyHost.setToolTipText("L'Hôte du Proxy");
		proxyPwd.setMinimumSize(new Dimension(300,20));
		proxyPwd.setMaximumSize(new Dimension(300,20));
		proxyPwd.setToolTipText("Votre mot de passe sur le Proxy");
		proxyPort.setMinimumSize(new Dimension(50,20));
		proxyPort.setMaximumSize(new Dimension(50,20));
		proxyPort.setToolTipText("le Port utilisé par le Proxy");
		proxyUser.setMinimumSize(new Dimension(300,20));
		proxyUser.setMaximumSize(new Dimension(300,20));
		proxyUser.setToolTipText("Votre nom d'utilisateur sur le Proxy");

		pwdL = new JLabel("Mot de Passe : ");
		pwdL.setToolTipText("Votre mot de passe");
		userL = new JLabel("User : ");
		userL.setToolTipText("Votre nom d'utilisateur");
		
		pwd = new JPasswordField(laspirateur.getHTTPPassword());
		pwd.setToolTipText("Votre mot de passe");
		user = new JTextField(laspirateur.getHTTPUser());
		user.setToolTipText("Votre nom d'utilisateur");

		
		user.setMinimumSize(new Dimension(300,20));
		user.setMaximumSize(new Dimension(300,20));
		pwd.setMinimumSize(new Dimension(300,20));
		pwd.setMaximumSize(new Dimension(300,20));
		
		proxyHost.addKeyListener(new ActionOkClavier());
		proxyPort.addKeyListener(new ActionOkClavier());
		proxyPwd.addKeyListener(new ActionOkClavier());
		proxyUser.addKeyListener(new ActionOkClavier());
		user.addKeyListener(new ActionOkClavier());
		pwd.addKeyListener(new ActionOkClavier());
		
		
	    /** Authentification Proxy */
		JPanel proxy = new JPanel();
		TitledBorder afact = BorderFactory.createTitledBorder("Authentification Proxy");
		afact.setTitleJustification(TitledBorder.CENTER);
		proxy.setBorder(afact);
		GroupLayout layoutProxy = new GroupLayout(proxy);
		proxy.setLayout(layoutProxy);
		layoutProxy.setHorizontalGroup(layoutProxy.createParallelGroup()
	            .addGroup(layoutProxy.createParallelGroup()
	            	.addGroup(layoutProxy.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layoutProxy.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(proxyUserL)
	                        .addComponent(proxyPwdL)
	                    	.addComponent(proxyHostL)
	                        .addComponent(proxyPortL)
	                    )
	    	            .addGap(10)
	                    .addGroup(layoutProxy.createParallelGroup(GroupLayout.Alignment.LEADING)
			                .addComponent(proxyUser)
		                    .addComponent(proxyPwd)
		                    .addComponent(proxyHost)
		                    .addComponent(proxyPort)
		                )
	                )
	            )
	    );
	    
	    layoutProxy.setVerticalGroup(layoutProxy.createSequentialGroup()
	            .addGroup(layoutProxy.createSequentialGroup()
		            .addGap(10)
		            .addGroup(layoutProxy.createParallelGroup(GroupLayout.Alignment.BASELINE)
		            		.addComponent(proxyUserL)
			            	.addComponent(proxyUser)
		            )
		            .addGap(10)
		            .addGroup(layoutProxy.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(proxyPwdL)
		                	.addComponent(proxyPwd)
		            )
		            .addGap(10)
	                .addGroup(layoutProxy.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(proxyHostL)
		                    .addComponent(proxyHost)
	                )
	                .addGap(10)
	                .addGroup(layoutProxy.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(proxyPortL)
		                    .addComponent(proxyPort)
	                )
	          )
              .addGap(10)
	    );
		
	    /** Authentification HTTP */
	    JPanel http = new JPanel();
		TitledBorder afact2 = BorderFactory.createTitledBorder("Authentification HTTP");
		afact2.setTitleJustification(TitledBorder.CENTER);
		http.setBorder(afact2);
		GroupLayout layoutHTTP = new GroupLayout(http);
		http.setLayout(layoutHTTP);
		layoutHTTP.setHorizontalGroup(layoutHTTP.createParallelGroup()
	            .addGroup(layoutHTTP.createParallelGroup()
	            	.addGroup(layoutHTTP.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layoutHTTP.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(userL)
	                        .addComponent(pwdL)
	                    )
	    	            .addGap(10)
	                    .addGroup(layoutHTTP.createParallelGroup(GroupLayout.Alignment.TRAILING)
		                    .addComponent(user)
		                    .addComponent(pwd)
		                )
	                )
	            )
	    );
	    
	    layoutHTTP.setVerticalGroup(layoutHTTP.createSequentialGroup()
	            .addGroup(layoutHTTP.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layoutHTTP.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(userL)
		                    .addComponent(user)
	                )
	                .addGap(10)
	                .addGroup(layoutHTTP.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(pwdL)
		                    .addComponent(pwd)
	                )
	          )
              .addGap(10)
	    );
	    
	    Container options = new Container();
	    GroupLayout layout = new GroupLayout(options);
	    options.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
	            .addGroup(layout.createParallelGroup()
	            	.addGroup(layout.createSequentialGroup()
	    	            .addGap(10)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    	.addComponent(http)
	                        .addComponent(proxy)
	                        .addComponent(boutons)
	                    )
	                )
	            )
	    );
	    
	    layout.setVerticalGroup(layout.createSequentialGroup()
	            .addGroup(layout.createSequentialGroup()
		            .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    	.addComponent(http)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(proxy)
	                )
	                .addGap(10)
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                	.addComponent(boutons)
	                )
	          )
              .addGap(10)
	    );
	    
	    setLayout(new BorderLayout());
	    add(options, BorderLayout.CENTER);
	    
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	private class ActionOk implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(proxyPort.getText().matches("[0-9]*")){
				laspirateur.setProxyHost(proxyHost.getText());
				if(!proxyPort.getText().equals("")){
					laspirateur.setProxyPort(Integer.parseInt(proxyPort.getText()));
				}
				laspirateur.setProxyUser(proxyUser.getText());
				laspirateur.setProxyPassword(proxyPwd.getText());
				laspirateur.setUser(user.getText());
				laspirateur.setPassword(pwd.getText());
				dispose();
				JOptionPane.showMessageDialog(null,"Authentification enregistrée!","Validation",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"Erreur de saisie!","Attention",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private class ActionOkClavier implements KeyListener{
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(proxyPort.getText().matches("[0-9]*")){
				laspirateur.setProxyHost(proxyHost.getText());
				if(!proxyPort.getText().equals("")){
					laspirateur.setProxyPort(Integer.parseInt(proxyPort.getText()));
				}
				laspirateur.setProxyUser(proxyUser.getText());
				laspirateur.setProxyPassword(proxyPwd.getText());
				laspirateur.setUser(user.getText());
				laspirateur.setPassword(pwd.getText());
				dispose();
				JOptionPane.showMessageDialog(null,"Authentification enregistrée!","Validation",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"Erreur de saisie!","Attention",JOptionPane.WARNING_MESSAGE);
			}
			}
			
		}

		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	
	private class ActionAnnuler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
