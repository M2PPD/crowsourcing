package com.ppd.crowdsourcing.controller;

public class comparaison {
	
	public int idFichier1;
	public int idFichier2;
	public int idLigne1;
	public int idLigne2;
	
	public int getIdFichier1() {
		return idFichier1;
	}

	public void setIdFichier1(int idFichier1) {
		this.idFichier1 = idFichier1;
	}

	public int getIdFichier2() {
		return idFichier2;
	}

	public void setIdFichier2(int idFichier2) {
		this.idFichier2 = idFichier2;
	}
	
	public int getIdLigne1() {
		return idLigne1;
	}

	public void setIdLigne1(int idLigne1) {
		this.idLigne1 = idLigne1;
	}

	public int getIdLigne2() {
		return idLigne2;
	}

	public void setIdLigne2(int idLigne2) {
		this.idLigne2 = idLigne2;
	}

	public int getResChamp1() {
		return resChamp1;
	}

	public void setResChamp1(int resChamp1) {
		this.resChamp1 = resChamp1;
	}

	public int getResChamp2() {
		return resChamp2;
	}

	public void setResChamp2(int resChamp2) {
		this.resChamp2 = resChamp2;
	}

	public int getResChamp3() {
		return resChamp3;
	}

	public void setResChamp3(int resChamp3) {
		this.resChamp3 = resChamp3;
	}

	public int getResChamp4() {
		return resChamp4;
	}

	public void setResChamp4(int resChamp4) {
		this.resChamp4 = resChamp4;
	}

	public int resChamp1;
	public int resChamp2;
	public int resChamp3;
	public int resChamp4;
	
	public comparaison(int idLigne1, int idLigne2) {
		super();
		this.idLigne1 = idLigne1;
		this.idLigne2 = idLigne2;
	}

	
	
	
}
