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

		//connexion � la base de donn�es
		try {

			//tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);				
						
			//pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
			//les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvParticipant
			ps = con.prepareStatement("INSERT INTO Participant (id, nom, prenom, age, sexe, localite, equipeID) VALUES (?, ?, ?, ?,?,?,?)");
			ps.setString(1,nouvParticipant.getId());
			ps.setString(2,nouvParticipant.getNom());
			ps.setString(3,nouvParticipant.getPrenom());
			ps.setInt(4,nouvParticipant.getAge());	
			ps.setString(5,nouvParticipant.getSexe());
			ps.setString(6,nouvParticipant.getLocalite());
			ps.setString(7,nouvParticipant.getEquipeID());


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
	
	public static Participant getParticipantByID(String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		Participant retour=null;

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Participant WHERE nom = ? ");
			ps.setString(1,reference);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Participant WHERE nom = ? ");
			ps.setString(1,nom);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Participant WHERE equipeid = ? ");
			ps.setInt(1, id);

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e 
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM participant");

			//on ex�cute la requ�te
			//rs contient un pointeur situ� jusute avant la premi�re ligne retourn�e
			rs=ps.executeQuery();
			//passe � la premi�re (et unique) ligne retourn�e  String id, String nom, String prenom, int age, String sexe, String localite, String equipeIDz
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

		//connexion � la base de donn�es
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM Participant WHERE id = ?");
			ps.setInt(1,Integer.parseInt(reference));

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

	public static void updateParticipant(String nom, String prenom, int age, String sexe, String localite, String equipeID, String reference){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		//connexion � la base de donn�es
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

