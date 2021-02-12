package fr.doranco.jaxrs.jersey.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employe {
	
	private Integer id;
	private String nom;
	private String prenom;
	private String posteOccupe;
	
	public Employe() {}

	public Employe(String nom, String prenom, String posteOccupe) {
		this.nom = nom;
		this.prenom = prenom;
		this.posteOccupe = posteOccupe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPosteOccupe() {
		return posteOccupe;
	}

	public void setPosteOccupe(String posteOccupe) {
		this.posteOccupe = posteOccupe;
	}

	@Override
	public String toString() {
		return "Employe [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", posteOccupe=" + posteOccupe + "]";
	}

}
