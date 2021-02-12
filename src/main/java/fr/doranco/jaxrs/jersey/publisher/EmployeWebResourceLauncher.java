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
			
			System.out.println("Jersey d�marr� avec succ�s. WADL disponible � l'adresse: " + BASE_URI);
			System.out.println("\n-------------------------------------------");
			System.out.println("\tJersey d�marr� avec succ�s.");
			System.out.println("\t\tPour tester si le web service est bien d�ploy�");
			System.out.println("\t\tTaper dans le navigateur: " + BASE_URI + "application.wadl");
			System.out.println("\n-------------------------------------------");
			
			System.in.read();
			
			System.out.println("Arr�t du serveur en cours ...");
			server.stop(0);
			Thread.sleep(2000);
			System.out.println("Server arr�t� avec succ�s...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
