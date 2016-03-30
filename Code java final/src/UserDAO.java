import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN="bdd10";
	final static String PASS="bdd10";
	
	public UserDAO()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e2) {
			System.out.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	public static int add(User nouvUser)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);				
						
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvUser
			ps = con.prepareStatement("INSERT INTO Users (id, pseudo, mdp) VALUES (?, ?, ?)");
			ps.setString(1,nouvUser.getId());
			ps.setString(2,nouvUser.getPseudo());
			ps.setString(3,nouvUser.getMdp());

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
	
	public static User getUser(String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		User retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Users WHERE id = ? ");
			ps.setString(1,reference);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())retour=new User(rs.getString("id"),rs.getString("pseudo"),rs.getString("mdp"));

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

	public static User getUserByName(String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		User retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Users WHERE pseudo = ? ");
			ps.setString(1,reference);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())retour=new User(rs.getString("id"),rs.getString("pseudo"),rs.getString("mdp"));

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
	
	public static void deleteUser(String reference){
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs=null;
			User retour=null;
	
			//connexion à la base de données
			try {
	
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("DELETE FROM User WHERE id = ?");
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

	public static void updateUser(String pseudo, String mdp, String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		//connexion à la base de données
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Users SET pseudo = ?, mdp = ? WHERE id = ? ");
			ps.setString(1,pseudo);
			ps.setString(2,mdp);
			ps.setInt(3,Integer.parseInt(reference));
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
