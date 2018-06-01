package Interface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import entities.*;

public class Client {
	
	
	Message msg;
	
	public static Socket connexion() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.43.129",40000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return socket;
	}
	
	public Client() {
		try {
			System.out.println("Connexion avec success");
			Personnage perso = new Personnage();
			Socket socket;
			socket = connexion();
			//creer_profil(socket,perso);
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	
	public static void creer_profil(Socket socket,String pseudo, int prof) {
		ObjectInputStream ois;
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject("1");
			oos.flush();
			Personnage perso = new Personnage();
			
			perso.setPseudo(pseudo);
			
			Profil profil = new Profil();
			profil.setProfil(prof);
			perso.setProfil(profil);
			System.out.println(perso.getProfil().getProfil());
			oos.writeObject(perso);
			oos.flush();
			System.out.println("Envoyer : " + perso);
			String result = (String) ois.readObject();
			System.out.println("Reçu : " + result);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public static void liste_profil(Socket socket) {
		ObjectInputStream ois;
		ObjectOutputStream oos;
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject("6");
			oos.flush();
			List profils = new ArrayList();
			profils = (ArrayList) ois.readObject();	
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
	}
	
	
	public static Personnage infosPerso(Socket socket, String pseudo) {
		ObjectInputStream ois;
		ObjectOutputStream oos;		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println("essayons d'envoyer : 4");
			oos.writeObject("4");
			oos.flush();
			//System.out.println("Creer : " + pseudo);
			oos.writeObject(pseudo);
			oos.flush();
			//System.out.println("Envoyer : " + pseudo);
			Personnage result = (Personnage) ois.readObject();
			System.out.println("Reçu : " + result);			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;	
	}
	
	
	public static Boolean authentification(Socket socket,String pseudo) {
		ObjectInputStream ois;
		ObjectOutputStream oos;		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject("3");
			oos.flush();
			oos.writeObject(pseudo);
			
			
			oos.flush();
			boolean result = (boolean) ois.readObject();
			System.out.println("Pseudo : "+pseudo);
			System.out.println("Recçu : " + result);
			
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static List<Personnage> liste_perso_connecté(Socket socket) {
		ObjectInputStream ois;
		ObjectOutputStream oos;		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject("2");
			oos.flush();
			List<Personnage> result = (List<Personnage>) ois.readObject();
			System.out.print("recu : ");
			System.out.println("Les resutats : " + result);
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;		
	}
	
	public static void MAJ(Socket socket,String pseudo) {
		ObjectInputStream ois;
		ObjectOutputStream oos;		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject("5");
			oos.flush();
			oos.writeObject(pseudo);
			oos.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void deconnecter(Socket socket, String pseudo) {
		ObjectInputStream ois;
		ObjectOutputStream oos;		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject("7");
			oos.flush();			
			System.out.println("Joueur déconnecté");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		
		String pseudo = "Aziz";
		new Client();	
		Socket socket = Client.connexion();
		Personnage p = Client.infosPerso(socket, pseudo);
	}

}
