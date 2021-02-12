package fr.doranco.jaxrs.jersey.server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import fr.doranco.jaxrs.jersey.dao.EmployeDao;
import fr.doranco.jaxrs.jersey.dao.IEmployeDao;
import fr.doranco.jaxrs.jersey.entity.Employe;

@Path("employes")
@Produces(MediaType.TEXT_PLAIN +  ";charset=UTF8")
public class EmployeWebResource implements IEmployeWebResource {
	
	private static final String CHARSET = ";charset=UTF8";
	private static IEmployeDao employeDao = new EmployeDao();

	public EmployeWebResource() {}

	@Override
	@GET
	public String getInfo() {
		System.out.println("GET /employes");
		return "Vous avez apelé le web service REST 'employes' pour récupérer les infos";
	}

	@Override
	@GET
	@Path("employe-{id}-XML")
	@Produces(MediaType.APPLICATION_XML + CHARSET)
	public String getEmployeXML(@PathParam("id") @DefaultValue("1") Integer id) throws Exception {
		System.out.println("GET /employes/employe-" + id + "-XML");
		Employe employe = employeDao.getElmployeById(id);
		return getEmployeAuFormatXmlString(employe);
	}
	
	private final String getEmployeAuFormatXmlString(Employe employe) {
		StringBuilder sb = new StringBuilder();
		sb.append("<employe>\n");
		sb.append("<id>").append(employe.getId()).append("</id>\n");
		sb.append("<nom>").append(employe.getNom()).append("</nom>\n");
		sb.append("<prenom>").append(employe.getPrenom()).append("</prenom>\n");
		sb.append("<posteOccupe>").append(employe.getPosteOccupe()).append("</posteOccupe>\n");
		sb.append("</employe>");
		return sb.toString();
	}

	@Override
	@GET
	@Path("employe-{id}-JSON")
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	public String getEmployeJSON(@PathParam("id") Integer id) throws Exception {
		System.out.println("GET /employes/employe-" + id + "-JSON");
		Employe employe = employeDao.getElmployeById(id);
		return getEmployeAuFormatJsonString(employe);
	}
	
	private final String getEmployeAuFormatJsonString(Employe employe) {
		JSONObject json = new JSONObject();
		json.put("id", employe.getId());
		json.put("nom", employe.getNom());
		json.put("prenom", employe.getPrenom());
		json.put("poste_occupe", employe.getPosteOccupe());
		return json.toString();
	}

	@Override
	@GET
	@Path("employe-{id}")
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public Response getEmployeJSONtoResponse(@PathParam("id") Integer id) {
		System.out.println("GET /employes/employe-" + id);
		
		try {
			Employe employe = employeDao.getElmployeById(id);
			if (employe == null) return Response.status(Status.NOT_FOUND).build();
			return Response.ok().entity(employe).build();
			
		} catch (Exception e) {
			// Server ERROR
			System.out.println(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@Override
	@GET
	@Path("list")
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public List<Employe> getEmployes() throws Exception {
		System.out.println("GET /employes/list");
		
		List<Employe> listeEmployes = new ArrayList<Employe>();
		listeEmployes = employeDao.getEmployes();

		return listeEmployes;
	}

	@Override
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	@Produces({MediaType.APPLICATION_XML + CHARSET, MediaType.APPLICATION_JSON + CHARSET})
	public Response addEmploye(Employe employe) {
		System.out.println("POST employes/add");
		// TEST
		if (employe == null 
		 || employe.getNom() == null || employe.getNom().isEmpty()
		 || employe.getPrenom() == null || employe.getPrenom().isEmpty()
		 || employe.getPosteOccupe() == null || employe.getPosteOccupe().isEmpty()) {
			System.out.println("\tBAD REQUEST");
			return Response.status(Status.BAD_REQUEST).entity("BAD REQUEST").build();
		}
		
		try {
			Employe employeAdded = employeDao.addEmploye(employe);		
			// 200 OK
			return Response.status(Status.CREATED).entity(employeAdded).build();
		} catch (Exception e) {
			// Server ERROR
			System.out.println(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@Override
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_XML + CHARSET)
	@Produces(MediaType.APPLICATION_XML + CHARSET)
	public Response updateEmploye(Employe employe) {
		System.out.println("PUT employes/update");
		// TEST
		if (employe == null 
		 || employe.getId() == null || employe.getId() < 1
		 || employe.getNom() == null || employe.getNom().isEmpty()
		 || employe.getPrenom() == null || employe.getPrenom().isEmpty()
		 || employe.getPosteOccupe() == null || employe.getPosteOccupe().isEmpty()) {
			System.out.println("\tBAD REQUEST");
			return Response.status(Status.BAD_REQUEST).entity("BAD REQUEST").build();
		}

		try {
			Employe employeUpdated = employeDao.updateEmploye(employe);		
			// 200 OK
			return Response.status(Status.CREATED).entity(employeUpdated).build();
		} catch (Exception e) {
			// Server ERROR
			System.out.println(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@Override
	@DELETE
	@Path("delete/{id}")
	public Response removeEmploye(@PathParam("id") Integer id) {
		System.out.println("DELETE employes/delete/" + id);
		// TEST
		if (id == null || id < 1) {
			System.out.println("\tBAD REQUEST");
			return Response.status(Status.BAD_REQUEST).entity("BAD REQUEST").build();
		}
		
		try {
			employeDao.removeEmploye(id);		
			// 200 OK
			return Response.status(Status.ACCEPTED).build();
		} catch (Exception e) {
			// Server ERROR
			System.out.println(e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
