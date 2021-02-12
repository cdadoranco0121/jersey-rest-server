package fr.doranco.jaxws.connexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DataSourceConnexion {

	// Singleton
	private static DataSourceConnexion instance;
	
	// Private constructor
	private DataSourceConnexion() {}
	
	// Only one instance
	public static DataSourceConnexion getInstance() {
		if (instance == null) instance = new DataSourceConnexion();
		return instance;
	}
	
	// Not static method
	public Connection getConnection() throws SQLException, IOException, MissingResourceException {
		ResourceBundle rb = ResourceBundle.getBundle("fr.doranco.jaxws.jersey.dbfile");
		String url = rb.getString("url");
		String user = rb.getString("user");
		String password = rb.getString("password");
		
		return DriverManager.getConnection(url, user, password);
	}

}
