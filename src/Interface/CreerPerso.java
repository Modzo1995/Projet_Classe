package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class CreerPerso {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerPerso window = new CreerPerso();
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
	public CreerPerso() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pseudo = textField.getText();
				int profil = Integer.parseInt(textField_1.getText());
				try {
					Socket socket;			        
		            socket = Client.connexion();

					Client.creer_profil(socket, pseudo,profil);
					
					socket.close();
					
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
		btnValider.setBounds(42, 179, 89, 23);
		frame.getContentPane().add(btnValider);
		
		JButton btnQuitter = new JButton("quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Acceuil window = new Acceuil();
				window.getFrame().setVisible(true);
			}
		});
		btnQuitter.setBounds(268, 179, 89, 23);
		frame.getContentPane().add(btnQuitter);
		
		textField = new JTextField();
		textField.setBounds(166, 52, 172, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(166, 97, 172, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setBounds(62, 52, 66, 20);
		frame.getContentPane().add(lblPseudo);
		
		JLabel lblProfil = new JLabel("Profil");
		lblProfil.setBounds(62, 100, 66, 20);
		frame.getContentPane().add(lblProfil);
	}
}
