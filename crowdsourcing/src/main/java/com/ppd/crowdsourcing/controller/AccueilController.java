package com.ppd.crowdsourcing.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppd.crowdsourcing.entity.Fichier;
import com.ppd.crowdsourcing.entity.Ligne;
import com.ppd.crowdsourcing.controller.algo;

@Controller
public class AccueilController {

	Connection connection = null;  // For making the connection
	Statement statement = null;    // For the SQL statement
	ResultSet rs = null;    // For the result set, if applicable
	List<comparaison> tabRes = new ArrayList<comparaison>();
	int moyChamps1, moyChamps2, moyChamps3, moyChamps4;
	int percentMin = 45;
	boolean champs1= false, champs2= false, champs3 = false, champs4 = false;

	@GetMapping(value = "hello")
	@ResponseBody
	public String accueil(){
		return "Hello CrowdMerde";
	}

	@GetMapping(value = "afficheQuestions")
	@ResponseBody
	public String afficheQuestions(){

		String sqlString = "ceci est un test";
		int i = 0;
		for (comparaison c : tabRes) {
			System.out.println(i + "/" + tabRes.size());
			i ++;
			if (i == 10000) {
				return sqlString;
			}

			if ((champs1 == true && c.getResChamp1() >= percentMin) || (champs1 == false)) {
				if((champs2  == true && c.getResChamp2() >= percentMin) || (champs2 == false)) {
					if ((champs3  == true && c.getResChamp3() >= percentMin) || (champs3 == false)){
						if ((champs4  == true && c.getResChamp4() >= percentMin) || (champs4 == false)){

							sqlString=  sqlString +"INSERT INTO comp (idFichier1, idFichier2, idLigne1, idLigne2, resChamp1, resChamp2, resChamp3, resChamp4) VALUES (";
							sqlString = sqlString + c.getIdFichier1() +", " + c.getIdFichier2() +", " + c.getIdLigne1() +", " + c.getIdLigne2() +", ";
							sqlString = sqlString + c.getResChamp1() +", " + c.getResChamp2() +", " + c.getResChamp3() +", " + c.getResChamp4() +"); " ;
						}
					}
				}
			}
		}
		sauvegardeComparaison(sqlString);
		return sqlString;
	}

	@GetMapping(value = "sauvegardeQuestions")
	@ResponseBody
	public boolean sauvegardeComparaison(String sqlString) {

		try {

			// Use the connection to create the SQL statement.
			statement = connection.createStatement();
			rs = statement.executeQuery(sqlString);
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
		return true;

	}

	@GetMapping(value = "traitement")
	@ResponseBody
	public String detectTuples(){
		algo detect = new algo();
		List<Ligne> tabLigne = new ArrayList<Ligne>();
		List<Ligne> tabLigne2 = new ArrayList<Ligne>();

		int i=0, j=0;

		tabLigne = getLignes(10);
		tabLigne2 = getLignes(11);


		for ( i = 0; i < tabLigne.size(); i++) {
			Ligne l1 = tabLigne.get(i);
			System.out.println("Ligne n°" + i);
			for ( j = 0; j < tabLigne2.size(); j++) {
				comparaison c = new comparaison(i,j);
				Ligne l2 = tabLigne2.get(j);
				c.setIdFichier1(10);
				c.setIdFichier2(11);
				c.setResChamp1(algo.pecentageOfTextMatch(l1.getChamps1(), l2.getChamps1()));
				c.setResChamp2(algo.pecentageOfTextMatch(l1.getChamps2(), l2.getChamps2()));
				c.setResChamp3(algo.pecentageOfTextMatch(l1.getChamps3(), l2.getChamps3()));
				c.setResChamp4(algo.pecentageOfTextMatch(l1.getChamps4(), l2.getChamps4()));
				tabRes.add(c);
			}
		}


		return "Traitement Terminé";
	}

	@GetMapping(value = "attributs")
	@ResponseBody
	public String attributs(){
		int sumChamps1 = 0, sumChamps2 = 0, sumChamps3 = 0, sumChamps4 = 0;

		int j =0;

		for ( j = 0; j < tabRes.size(); j++) {
			sumChamps1 += tabRes.get(j).getResChamp1();
			sumChamps2 += tabRes.get(j).getResChamp2();
			sumChamps3 += tabRes.get(j).getResChamp3();
			sumChamps4 += tabRes.get(j).getResChamp4();	
		}
		moyChamps1 = sumChamps1/j;
		moyChamps2 = sumChamps2/j;
		moyChamps3 = sumChamps3/j;
		moyChamps4 = sumChamps4/j;

		if (moyChamps1 >= percentMin) {
			champs1 =true;
		}
		if (moyChamps2 >= percentMin) {
			champs2 =true;
		}
		if (moyChamps3 >= percentMin) {
			champs3 =true;
		}
		if (moyChamps4 >= percentMin) {
			champs4 =true;
		}

		String s = champs1 +" "+ champs2 + " "+ champs3 + " " + champs4; 
		s = s + "/n" + "Voici les pourcentages de ressemblance entre les attributs  : " + " / champs 1 : " + moyChamps1 +"% "+ " / champs 2 : " + moyChamps2 +"% "+ " / champs 3 : " + moyChamps3 +"% "+ " / champs 4 : " + moyChamps4 +"% ";

		return s;
	}

	/*@GetMapping(value = "detectTuples")
	@ResponseBody
	public String detectTuplesTest(){
		algo detect = new algo();
		List<Ligne> tabLigne = new ArrayList<Ligne>();
		List<Ligne> tabLigne2 = new ArrayList<Ligne>();
		int sumChamps1 = 0, sumChamps2 = 0, sumChamps3 = 0, sumChamps4 = 0;
		int moyChamps1, moyChamps2, moyChamps3, moyChamps4;	
		int i=0, j=0;

		tabLigne = getLignes(10);
		tabLigne2 = getLignes(11);


		for ( i = 0; i < tabLigne.size(); i++) {
			Ligne l1 = tabLigne.get(i);

			for ( j = 0; j < tabLigne2.size(); j++) {
				comparaison c = new comparaison(i,j);
				Ligne l2 = tabLigne2.get(j);
				c.setResChamp1(algo.pecentageOfTextMatch(l1.getChamps1(), l2.getChamps1()));
				c.setResChamp2(algo.pecentageOfTextMatch(l1.getChamps2(), l2.getChamps2()));
				c.setResChamp3(algo.pecentageOfTextMatch(l1.getChamps3(), l2.getChamps3()));
				c.setResChamp4(algo.pecentageOfTextMatch(l1.getChamps4(), l2.getChamps4()));
				tabRes.add(c);
			}
		}
		for (j = 0; j < tabRes.size(); j++) {
			sumChamps1 += tabRes.get(j).getResChamp1();
			sumChamps2 += tabRes.get(j).getResChamp2();
			sumChamps3 += tabRes.get(j).getResChamp3();
			sumChamps4 += tabRes.get(j).getResChamp4();	
		}
		moyChamps1 = sumChamps1/j;
		moyChamps2 = sumChamps2/j;
		moyChamps3 = sumChamps3/j;
		moyChamps4 = sumChamps4/j;
		String s = "Voici les pourcentages de ressemblance entre les attributs  : " + " / champs 1 : " + moyChamps1 +"% "+ " / champs 2 : " + moyChamps2 +"% "+ " / champs 3 : " + moyChamps3 +"% "+ " / champs 4 : " + moyChamps4 +"% ";

		return s;
	}*/

	//fonction permettant de récuperer l'ensemble des lignes contenues dans un fichier passé en paramètre
	public List<Ligne> getLignes(int numfichier){

		connection();
		// Define the SQL string.
		String sqlString =  "SELECT * FROM LIGNE WHERE idFichier ='" + numfichier + "';";
		try {

			// Use the connection to create the SQL statement.
			statement = connection.createStatement();


			rs = statement.executeQuery(sqlString);

			List<Ligne> tabLigne = new ArrayList<Ligne>();

			while (rs.next())
			{
				Ligne l = new Ligne(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
				tabLigne.add (l);
			}
			return tabLigne;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		} 
	}
	//fonction permettant de se connecter au serveur AZURE
	public void connection(){
		// Connection string for your SQL Database server.
		// Change the values assigned to your_server, 
		// your_user@your_server,
		// and your_password.
		String connectionString = 
				"jdbc:sqlserver://ppdserveur.database.windows.net:1433" + ";" +  
						"database=PPD" + ";" + 
						"user=projetsql@ppdserveur" + ";" +  
						"password=Ppdcrowd1";

		// The types for the following variables are
		// defined in the java.sql library.

		try
		{
			// Ensure the SQL Server driver class is available.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Establish the connection.
			connection = DriverManager.getConnection(connectionString);
			//connection.close();
			// Provide a message when processing is complete.
			System.out.println("Connection ouverte");

		}
		// Exception handling
		catch (ClassNotFoundException cnfe)  
		{

			System.out.println("ClassNotFoundException " +
					cnfe.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();
		}		
	}
	//fonction permettant de se deconnecter du serveur Azure
	public void deconnexion()
	{
		try
		{
			// Close resources.
			if (null != connection) connection.close();
			if (null != statement) statement.close();
			if (null != rs) rs.close();
		}
		catch (SQLException sqlException)
		{
			// No additional action if close() statements fail.
		}
	}
}


