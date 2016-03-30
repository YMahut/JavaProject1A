import java.sql.*;
import java.util.ArrayList;
import java.util.List;
	
public class EquipeDAO {
		final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		final static String LOGIN="bdd10";
		final static String PASS="bdd10";
		
		public EquipeDAO()
		{
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e2) {
				System.out.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
			}

		}

		public static int add(Equipe nouvEquipe)
		{
			Connection con = null;
			PreparedStatement ps = null;
			int retour=0;

			//connexion à la base de données
			try {

				//tentative de connexion
				con = DriverManager.getConnection(URL, LOGIN, PASS);				
								
				//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
				//les getters permettent de récupérer les valeurs des attributs souhaités de nouvEquipe
				ps = con.prepareStatement("INSERT INTO equipe (id, nom, localite, nbMembre) VALUES (?, ?, ?, ?)");
				ps.setString(1,nouvEquipe.getId());
				ps.setString(2,nouvEquipe.getNom());
				ps.setString(3,nouvEquipe.getLocalite());
				ps.setInt(4,nouvEquipe.getNbMembre());

				//Exécution de la requête
				retour=ps.executeUpdate();

			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				//fermeture du preparedStatement et de la connexion
				try {if (ps != null)ps.close();} catch (Exception t) {}
				try {if (con != null)con.close();} catch (Exception t) {}
			}
			return retour;

		}
		
		public static Equipe getEquipe(String reference){
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			Equipe retour=null;

			//connexion à la base de données
			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("SELECT * FROM equipe WHERE id = ? ");
				ps.setString(1,reference);

				//on exécute la requête
				//rs contient un pointeur situé jusute avant la première ligne retournée
				rs=ps.executeQuery();
				//passe à la première (et unique) ligne retournée 
				if(rs.next())retour=new Equipe(rs.getString("id"),rs.getString("nom"),rs.getString("localite"),rs.getInt("nbMembre"));


			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				//fermeture du ResultSet, du PreparedStatement et de la Connection
				try {if (rs != null)rs.close();} catch (Exception t) {}
				try {if (ps != null)ps.close();} catch (Exception t) {}
				try {if (con != null)con.close();} catch (Exception t) {}
			}
			return retour;

		}

		public static Equipe getEquipeByName(String reference){
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			Equipe retour=null;

			//connexion à la base de données
			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("SELECT * FROM equipe WHERE nom = ? ");
				ps.setString(1,reference);

				//on exécute la requête
				//rs contient un pointeur situé jusute avant la première ligne retournée
				rs=ps.executeQuery();
				//passe à la première (et unique) ligne retournée 
				if(rs.next())retour=new Equipe(rs.getString("id"),rs.getString("nom"),rs.getString("localite"),rs.getInt("nbMembre"));


			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				//fermeture du ResultSet, du PreparedStatement et de la Connection
				try {if (rs != null)rs.close();} catch (Exception t) {}
				try {if (ps != null)ps.close();} catch (Exception t) {}
				try {if (con != null)con.close();} catch (Exception t) {}
			}
			return retour;

		}
		
		public static ArrayList getEquipeAll(){
			ArrayList <Equipe> listEquipe = new ArrayList();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			Equipe retour=null;

			//connexion à la base de données
			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("SELECT * FROM equipe");

				//on exécute la requête
				//rs contient un pointeur situé jusute avant la première ligne retournée
				rs=ps.executeQuery();
				//passe à la première (et unique) ligne retournée 
				while(rs.next())
					listEquipe.add(new Equipe(rs.getString("id"),rs.getString("nom"),rs.getString("localite"),rs.getInt("nbMembre")));


			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				//fermeture du ResultSet, du PreparedStatement et de la Connection
				try {if (rs != null)rs.close();} catch (Exception t) {}
				try {if (ps != null)ps.close();} catch (Exception t) {}
				try {if (con != null)con.close();} catch (Exception t) {}
			}			
			return listEquipe;
		}
		
		public static void deleteEquipe(String reference){
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			Equipe retour=null;

			//connexion à la base de données
			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("DELETE FROM equipe WHERE id = ?");
				ps.setString(1,reference);

				//on exécute la requête
				//rs contient un pointeur situé jusute avant la première ligne retournée
				rs=ps.executeQuery();
				//passe à la première (et unique) ligne retournée 
			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				//fermeture du ResultSet, du PreparedStatement et de la Connection
				try {if (rs != null)rs.close();} catch (Exception t) {}
				try {if (ps != null)ps.close();} catch (Exception t) {}
				try {if (con != null)con.close();} catch (Exception t) {}
			}

		}

		public static void updateEquipe(String nom, String localite,int nbMembre, String reference){
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			//connexion à la base de données
			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("UPDATE equipe SET nom = ?, localite = ?, nbmembre = ? WHERE id = ? ");
				ps.setString(1,nom);
				ps.setString(2,localite);
				ps.setInt(3,nbMembre);
				ps.setString(4,reference);
				
				//on exécute la requête
				//rs contient un pointeur situé jusute avant la première ligne retournée
				rs=ps.executeQuery();
				//passe à la première (et unique) ligne retournée 
			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				//fermeture du ResultSet, du PreparedStatement et de la Connection
				try {if (rs != null)rs.close();} catch (Exception t) {}
				try {if (ps != null)ps.close();} catch (Exception t) {}
				try {if (con != null)con.close();} catch (Exception t) {}
			}
			
		}
}
