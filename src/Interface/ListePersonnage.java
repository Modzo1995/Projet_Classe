package Interface;

import javax.swing.*;

import entities.*;

import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.*;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ListePersonnage extends JFrame implements ActionListener
{
	private JTable table;
	private List<Personnage> liste = new ArrayList<>();
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Frame frame;
	
	
	public ListePersonnage(List <Personnage> liste)
	{
		frame = new Frame();
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setVisible(false);
				Acceuil p = new Acceuil();
				p.getFrame().setVisible(true);
			}
		});
		 this.liste=liste;

		  table = new JTable();
		  sc  = new JScrollPane();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("pseudo");
		  modele.addColumn("profil");
		  modele.addColumn("Vies");	
		  

		  
		  //table.getColumn("Button").setCellRenderer(new ButtonRenderer());
	      //table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
		  int ligne=0;
		  for (Personnage per : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(per.getPseudo(),ligne,0);
			  modele.setValueAt(per.getProfil().getDescription(), ligne, 1);
			  modele.setValueAt(per.getCapacite().getNombreVie(), ligne, 2);
			  ligne++;
		  }
		 
		  
		  
		  setTitle("Liste des Personnages");
		  setSize(757,500);
		  qt.addActionListener(this); 
		  panneau1.add(sc);
		  
		  btnNewButton = new JButton("New button");
		  sc.setColumnHeaderView(btnNewButton);
		  panneau2.add(qt);
		  getContentPane().add(panneau1,BorderLayout.NORTH);
		  
		  btnNewButton_1 = new JButton("Attack");
		  btnNewButton_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		int i = table.getSelectedRow();
				  Socket socket = Client.connexion();
				  String pseudo = (String) table.getModel().getValueAt(i, 0);
				  System.out.println(pseudo);
				  Client.MAJ(socket, pseudo);
		  	}
		  });
		  panneau1.add(btnNewButton_1);
		  getContentPane().add(panneau2,BorderLayout.SOUTH);
		  setLocationRelativeTo(null);
		  setVisible(true);
		  
		  Timer t = new Timer();
		    t.schedule(new TimerTask() {
		        @Override public void run() {
		              // textField_t.setText(YOUR TEXT); 
		        	try {
	        		Socket socket;			        
		            socket = Client.connexion();
		        	//frame.setVisible(false);
					List<Personnage> liste = Client.liste_perso_connecté(socket);
		        	ListePersonnage p = new ListePersonnage(liste);
		        	p.setVisible(true);
		        	dispose();	
					socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }, 5000L, 5000L);
	}
	public Frame getFrame() {
		return this.frame;
	}
	
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource()==qt)
    	{
    		dispose();
    		//this.getFrame().setVisible(false);
    		//this.
    	}
    }
    
  
}


