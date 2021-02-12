package fr.doranco.jaxrs.jersey.server;

import java.util.List;

import javax.ws.rs.core.Response;

import fr.doranco.jaxrs.jersey.entity.Employe;

public interface IEmployeWebResource {
	String getInfo();
	String getEmployeXML(Integer id) throws Exception;
	String getEmployeJSON(Integer id) throws Exception;
	Response getEmployeJSONtoResponse(Integer id) throws Exception;
	List<Employe> getEmployes() throws Exception;
	Response addEmploye(Employe employe) throws Exception;
	Response updateEmploye(Employe employe) throws Exception;
	Response removeEmploye(Integer id) throws Exception;
}
