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

		//connexion � la base de donn�es
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);				
						
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvUser
			ps = con.prepareStatement("INSERT INTO Users (id, pseudo, mdp) VALUES (?, ?, ?)");
			ps.setString(1,nouvUser.getId());
			ps.setString(2,nouvUser.getPseudo());
			ps.setString(3,nouvUser.getMdp());

			//Ex�cution de la requ�te
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Users WHERE id = ? ");
			ps.setString(1,reference);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Users WHERE pseudo = ? ");
			ps.setString(1,reference);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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
	
			//connexion � la base de donn�es
			try {
	
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("DELETE FROM User WHERE id = ?");
				ps.setString(1,reference);
	
				//on ex�cute la requ�te
				//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
				rs=ps.executeQuery();
				//passe � la premi�re (et unique) ligne retourn�e 
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
		//connexion � la base de donn�es
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Users SET pseudo = ?, mdp = ? WHERE id = ? ");
			ps.setString(1,pseudo);
			ps.setString(2,mdp);
			ps.setInt(3,Integer.parseInt(reference));
			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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
