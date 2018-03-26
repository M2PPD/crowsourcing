package com.ppd.crowdsourcing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ligne {
<<<<<<< HEAD
	
=======

>>>>>>> 2fe22d921e58a3a93e56745dd6c787dfac9bf25a
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int numeroLigne;
<<<<<<< HEAD
	
	@ManyToOne
	private Fichier idFichier;
	
=======
	@ManyToOne
	private Fichier idFichier;
>>>>>>> 2fe22d921e58a3a93e56745dd6c787dfac9bf25a
	private String champs1;
	private String champs2;
	private String champs3;
	private String champs4;


	public Ligne(int id, int numeroLigne, int idFichier, String champs1,  String champs2, String champs3, String champs4){
		super();
		this.id = id;
		this.numeroLigne = numeroLigne;
		this.idFichier = new Fichier(idFichier);
		this.champs1 = champs1;
		this.champs2 = champs2;
		this.champs3 = champs3;
		this.champs4 = champs4;
	}

	public String LignetoString(Ligne l) {
		return  l.getId() + " / " + l.getNumeroLigne() + " / " + l.getIdFichier().getId() + " / " + l.getChamps1() + " / " + l.getChamps2() + l.getChamps3() + " / " + l.getChamps4() ;

	}

<<<<<<< HEAD
	
=======
>>>>>>> 2fe22d921e58a3a93e56745dd6c787dfac9bf25a
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroLigne() {
		return numeroLigne;
	}
	public void setNumeroLigne(int numeroLigne) {
		this.numeroLigne = numeroLigne;
	}
<<<<<<< HEAD
	
=======

>>>>>>> 2fe22d921e58a3a93e56745dd6c787dfac9bf25a
	public Fichier getIdFichier() {
		return idFichier;
	}
	public void setIdFichier(Fichier idFichier) {
		this.idFichier = idFichier;
	}

	public String getChamps1() {
		return champs1;
	}

	public void setChamps1(String champs1) {
		this.champs1 = champs1;
	}

	public String getChamps2() {
		return champs2;
	}

	public void setChamps2(String champs2) {
		this.champs2 = champs2;
	}

	public String getChamps3() {
		return champs3;
	}

	public void setChamps3(String champs3) {
		this.champs3 = champs3;
	}

	public String getChamps4() {
		return champs4;
	}

	public void setChamps4(String champs4) {
		this.champs4 = champs4;
	}



}
