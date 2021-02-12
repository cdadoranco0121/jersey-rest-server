package fr.doranco.jaxrs.jersey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.doranco.jaxrs.jersey.entity.Employe;
import fr.doranco.jaxws.connexion.DataSourceConnexion;

public class EmployeDao implements IEmployeDao {

	@Override
	public List<Employe> getEmployes() throws Exception {
		List<Employe> listeEmploye = new ArrayList<Employe>();
		Connection connection = null;
		
		connection = DataSourceConnexion.getInstance().getConnection();
		String requete = "SELECT * FROM employe";
		PreparedStatement ps = connection.prepareStatement(requete);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Employe employe = new Employe();
			employe.setId(rs.getInt("id"));
			employe.setNom(rs.getString("nom"));
			employe.setPrenom(rs.getString("prenom"));
			employe.setPosteOccupe(rs.getString("poste_occupe"));
			
			listeEmploye.add(employe);
		}
		
		return listeEmploye;
	}

	@Override
	public Employe getElmployeById(Integer id) throws Exception {
		Employe employe = null;
		Connection connection = null;
		
		connection = DataSourceConnexion.getInstance().getConnection();
		String requete = "SELECT * FROM employe WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(requete);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			employe = new Employe();
			employe.setId(rs.getInt("id"));
			employe.setNom(rs.getString("nom"));
			employe.setPrenom(rs.getString("prenom"));
			employe.setPosteOccupe(rs.getString("poste_occupe"));
		}
		
		return employe;
	}

	@Override
	public Employe addEmploye(Employe employe) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
			
		connection = DataSourceConnexion.getInstance().getConnection();
		String requete = "INSERT INTO employe (nom, prenom, poste_occupe) "
				+ "VALUES(?, ?, ?)";
		ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, employe.getNom());
		ps.setString(2, employe.getPrenom());
		ps.setString(3, employe.getPosteOccupe());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			employe.setId(rs.getInt(1));
		}
		
		return employe;
	}

	@Override
	public Employe updateEmploye(Employe employe) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
			
		connection = DataSourceConnexion.getInstance().getConnection();
		String requete = "UPDATE employe SET nom = ?, prenom = ?, poste_occupe = ? "
				+ "WHERE id = ?";
		ps = connection.prepareStatement(requete);
		
		ps.setString(1, employe.getNom());
		ps.setString(2, employe.getPrenom());
		ps.setString(3, employe.getPosteOccupe());
		ps.setInt(4, employe.getId());
		ps.executeUpdate();
		
		return employe;
	}

	@Override
	public void removeEmploye(Integer id) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
			
		connection = DataSourceConnexion.getInstance().getConnection();
		String requete = "DELETE FROM employe WHERE id = ?";
		ps = connection.prepareStatement(requete);
		ps.setInt(1, id);
		
		ps.executeUpdate();
	}

}
