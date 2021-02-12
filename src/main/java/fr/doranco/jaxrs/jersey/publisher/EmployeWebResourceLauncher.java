package fr.doranco.jaxrs.jersey.publisher;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class EmployeWebResourceLauncher {
	
	public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/jersey/").port(9991).build();

	public static void main(String[] args) {

		try {
			ResourceConfig config = new PackagesResourceConfig("fr.doranco.jaxrs.jersey.server");
			System.out.println("Starting server: " + BASE_URI);
			
			HttpServer server = HttpServerFactory.create(BASE_URI, config);
			server.start();
			Thread.sleep(2000);
			
			System.out.println("Jersey démarré avec succès. WADL disponible à l'adresse: " + BASE_URI);
			System.out.println("\n-------------------------------------------");
			System.out.println("\tJersey démarré avec succès.");
			System.out.println("\t\tPour tester si le web service est bien déployé");
			System.out.println("\t\tTaper dans le navigateur: " + BASE_URI + "application.wadl");
			System.out.println("\n-------------------------------------------");
			
			System.in.read();
			
			System.out.println("Arrêt du serveur en cours ...");
			server.stop(0);
			Thread.sleep(2000);
			System.out.println("Server arrêté avec succès...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
