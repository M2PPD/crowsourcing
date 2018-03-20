package com.ppd.crowdsourcing;


import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrowdsourcingApplication {

	public static void main(String[] args) {
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
				Connection connection = null;  // For making the connection
				Statement statement = null;    // For the SQL statement
				ResultSet resultSet = null;    // For the result set, if applicable
				
				try
				{
				    // Ensure the SQL Server driver class is available.
				    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				
				    // Establish the connection.
				    connection = DriverManager.getConnection(connectionString);
				
				   // Define the SQL string.
				    
				    String sqlString =  "SELECT * FROM LIGNE;";
				  
				
				    // Use the connection to create the SQL statement.
				    statement = connection.createStatement();
		
		                     resultSet = statement.executeQuery(sqlString); 

		                        // Print results from select statement
		                        System.out.println("Top 20 categories:");
		                        while (resultSet.next())
		                        {
		                            System.out.println(resultSet.getString(1) + " "
		                                + resultSet.getString(2));
		                        }
		                 connection.close();
		                          

				    // Provide a message when processing is complete.
				    System.out.println("Processing complete.");
				
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
		        finally
		        {
		            try
		            {
		                // Close resources.
		                if (null != connection) connection.close();
		                if (null != statement) statement.close();
		                if (null != resultSet) resultSet.close();
		            }
		            catch (SQLException sqlException)
		            {
		                // No additional action if close() statements fail.
		            }
		        }
		//SpringApplication.run(CrowdsourcingApplication.class, args);
	}
}
