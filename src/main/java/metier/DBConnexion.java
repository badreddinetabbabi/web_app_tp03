package metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnexion {
	private static Connection connection = null;
	static {
		try {
			// Charger le pilote d'accès à la BD
			Class.forName("com.mysql.jdbc.Driver");
			// Etablir la connexion à la BD "MAGASIN"
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/magasin", "root", "");
		} 
		catch (Exception e) {
			// Message d'erreur en cas de problème de connexion
			System.out.println("Problème de connexion à la BD..");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		// return l'objet de connexion
		return connection;
	}
}

