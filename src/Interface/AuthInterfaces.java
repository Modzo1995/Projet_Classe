package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;


import entities.Personnage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class AuthInterfaces {

	private JFrame frame;
	private JTextField textField;
	private JButton btnRetour;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthInterfaces window = new AuthInterfaces();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuthInterfaces() {
		initialize();
	}
	
	

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(112, 84, 186, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		

	      
	      
	    
		 
		
		JLabel lblNewLabel = new JLabel("Pseudo");
		lblNewLabel.setBounds(149, 30, 79, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Socket socket;
					 String pseudo = textField.getText();
					 System.out.println("Pseudo Interface : "+ pseudo);
		            socket = Client.connexion();
		            Boolean result =  Client.authentification(socket, pseudo);		           
		            	if (result) {
		            		System.out.println("Authentification du perso : "+ pseudo);
							//Personnage p = Client.infosPerso(socket, pseudo);
							//System.out.println(p);
		            		frame.setVisible(false);
							Acceuil window = new Acceuil();
							window.getFrame().setVisible(true);
		            	}else {
		            		System.out.println("Le joueur n'existe pas Veuillez reessayer : ");
		            		frame.setVisible(false);
							Acceuil window = new Acceuil();
							window.getFrame().setVisible(true);
		            		
		            	}
	                socket.close();
	                               
		         
		               
		        }catch (UnknownHostException e1) {
		            
		            e1.printStackTrace();
		        }catch (IOException e1) {
		            
		            e1.printStackTrace();
		        }

				
			}
		});
		btnNewButton.setBounds(38, 173, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Acceuil a = new Acceuil();
				a.getFrame().setVisible(true);
			}
		});
		btnRetour.setBounds(250, 170, 107, 35);
		frame.getContentPane().add(btnRetour);
		
		
		
		
		
		
	}
}
