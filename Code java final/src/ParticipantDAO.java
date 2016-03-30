import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ParticipantDAO {
	final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final static String LOGIN="bdd10";
	final static String PASS="bdd10";
	
	public ParticipantDAO()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e2) {
			System.out.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	public static int add(Participant nouvParticipant)
	{
		Connection con = null;
		PreparedStatement ps = null;
		int retour=0;

		//connexion à la base de données
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);				
						
			//préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
			//les getters permettent de récupérer les valeurs des attributs souhaités de nouvParticipant
			ps = con.prepareStatement("INSERT INTO Participant (id, nom, prenom, age, sexe, localite, equipeID) VALUES (?, ?, ?, ?,?,?,?)");
			ps.setString(1,nouvParticipant.getId());
			ps.setString(2,nouvParticipant.getNom());
			ps.setString(3,nouvParticipant.getPrenom());
			ps.setInt(4,nouvParticipant.getAge());	
			ps.setString(5,nouvParticipant.getSexe());
			ps.setString(6,nouvParticipant.getLocalite());
			ps.setString(7,nouvParticipant.getEquipeID());


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
	
	public static Participant getParticipantByID(String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Participant retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Participant WHERE nom = ? ");
			ps.setString(1,reference);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())retour=new Participant(rs.getString("id"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),rs.getString("sexe"),rs.getString("Localite"),rs.getString("equipeID"));


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

	public static Participant getParticipantByName(String nom){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Participant retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Participant WHERE nom = ? ");
			ps.setString(1,nom);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			if(rs.next())retour=new Participant(rs.getString("id"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),rs.getString("sexe"),rs.getString("Localite"),rs.getString("equipeID"));


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
	
	public static ArrayList getParticipantByTeam(int id){
		ArrayList <Participant> listParticipant = new ArrayList();
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Participant retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Participant WHERE equipeid = ? ");
			ps.setInt(1, id);

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée 
			while(rs.next())
				listParticipant.add(new Participant(rs.getString("id"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),rs.getString("sexe"),rs.getString("Localite"),rs.getString("equipeID")));


		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}
		
		return listParticipant;
	}
	
	public static ArrayList getParticipantAll(){
		ArrayList <Participant> listParticipant = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Equipe retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM participant");

			//on exécute la requête
			//rs contient un pointeur situé jusute avant la première ligne retournée
			rs=ps.executeQuery();
			//passe à la première (et unique) ligne retournée  String id, String nom, String prenom, int age, String sexe, String localite, String equipeIDz
			while(rs.next())
				listParticipant.add(new Participant(rs.getString("id"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"),rs.getString("sexe"),rs.getString("localite"),rs.getString("equipeId")));
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			//fermeture du ResultSet, du PreparedStatement et de la Connection
			try {if (rs != null)rs.close();} catch (Exception t) {}
			try {if (ps != null)ps.close();} catch (Exception t) {}
			try {if (con != null)con.close();} catch (Exception t) {}
		}		
		return listParticipant;
	}
	
	public static void deleteParticipant(String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Participant retour=null;

		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM Participant WHERE id = ?");
			ps.setInt(1,Integer.parseInt(reference));

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

	public static void updateParticipant(String nom, String prenom, int age, String sexe, String localite, String equipeID, String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		//connexion à la base de données
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE Participant SET nom = ?, prenom = ?, age = ?, sexe = ?, localite = ?, equipeID = ? WHERE id = ? ");
			ps.setString(1,nom);
			ps.setString(2,prenom);
			ps.setInt(3,age);
			ps.setString(4,sexe);			
			ps.setString(5,localite);
			ps.setString(6, equipeID);
			ps.setString(7,reference);
			
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

