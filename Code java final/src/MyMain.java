import java.sql.SQLException;
import java.util.List;


/**
 * Classe main
 * Définit et ouvre la fenetre principale
 *   
 * @author Delamare - Mahut
 * @version 2.0
 * ESIGELEC 2015
 * Projet informatique 1re année
 * */


public class MyMain {
	public static void main(String[] args)throws SQLException {	
		Fenetre fenetrePrincipale = new Fenetre();
		fenetrePrincipale.accueil();
		System.out.println("Par défaut Admin / Admin");
	}
}


