import java.sql.SQLException;
import java.util.List;


/**
 * Classe main
 * D�finit et ouvre la fenetre principale
 *   
 * @author Delamare - Mahut
 * @version 2.0
 * ESIGELEC 2015
 * Projet informatique 1re ann�e
 * */


public class MyMain {
	public static void main(String[] args)throws SQLException {	
		Fenetre fenetrePrincipale = new Fenetre();
		fenetrePrincipale.accueil();
		System.out.println("Par d�faut Admin / Admin");
	}
}


