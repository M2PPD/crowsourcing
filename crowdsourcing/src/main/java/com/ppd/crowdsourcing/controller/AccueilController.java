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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppd.crowdsourcing.entity.Fichier;
import com.ppd.crowdsourcing.entity.Ligne;


@Controller
public class AccueilController {

	Connection connection = null;  // For making the connection
	Statement statement = null;    // For the SQL statement
	ResultSet rs = null;    // For the result set, if applicable

	
	@GetMapping(value = "hello")
	@ResponseBody
	public String accueil(){
		return "Hello CrowdMerde";
	}

	@GetMapping(value = "ligne")
	@ResponseBody
	public List<Ligne> getLignes(){

		connection();
		// Define the SQL string.
		String sqlString =  "SELECT * FROM LIGNE;";
		try {
			
			// Use the connection to create the SQL statement.
			statement = connection.createStatement();


			rs = statement.executeQuery(sqlString);

			List<Ligne> tabLigne = new ArrayList<Ligne>();
			// Print results from select statement
			System.out.println("Top 20 categories:");
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


