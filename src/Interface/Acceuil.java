package Interface;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import entities.Personnage;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.awt.event.ActionEvent;

public class Acceuil {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil window = new Acceuil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public Acceuil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("S'inscrire");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreerPerso wind = new CreerPerso();
				wind.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(158, 64, 124, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSauthentifier = new JButton("S'authentifier");
		btnSauthentifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AuthInterfaces wind = new AuthInterfaces();
				wind.getFrame().setVisible(true);
			}
		});
		btnSauthentifier.setBounds(158, 118, 124, 43);
		frame.getContentPane().add(btnSauthentifier);
		
		JButton btnListeDesPersonnes = new JButton("Liste des personnes connect\u00E9");
		btnListeDesPersonnes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket socket;			        
		            socket = Client.connexion();
					List<Personnage> liste = Client.liste_perso_connecté(socket);
					frame.setVisible(false);
					ListePersonnage wind = new ListePersonnage(liste);
					/*for (Personnage personnage : liste) {
						System.out.println(personnage.getProfil().getDescription() +" "+ personnage.getPseudo());
					}
					*/
					//wind.getFrame().setVisible(true);
				
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListeDesPersonnes.setBounds(158, 171, 124, 43);
		frame.getContentPane().add(btnListeDesPersonnes);
		
		JButton btnDeconnecter = new JButton("Deconnecter");
		btnDeconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Acceuil window = new Acceuil();
				window.getFrame().setVisible(true);
			}
		});
		btnDeconnecter.setBounds(158, 225, 124, 43);
		frame.getContentPane().add(btnDeconnecter);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton}));
	}
}
