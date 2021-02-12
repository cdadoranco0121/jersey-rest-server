package fr.doranco.jaxrs.jersey.dao;

import java.util.List;

import fr.doranco.jaxrs.jersey.entity.Employe;

public interface IEmployeDao {
	// GET
	List<Employe> getEmployes() throws Exception;
	Employe getElmployeById(Integer id) throws Exception;
	// POST
	Employe addEmploye(Employe employe) throws Exception;
	Employe updateEmploye(Employe employe) throws Exception;
	void removeEmploye(Integer id) throws Exception;
}