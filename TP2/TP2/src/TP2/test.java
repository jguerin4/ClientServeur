package TP2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class test {

	
	public static void main(String[] argv) {
		 
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		Voiture maVoiture = null;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "SELECT NO_SERIE, MARQUE, MODELE, POIDS, PRIX, COULEUR, ANNEE FROM VOITURES WHERE NO_SERIE = 12345 ";
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!");
 
		//Connection connection = null;
 
		try {
 
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "8ASY200-01",
					"eJAASn4Phm");
			
		if (conn != null) {
			stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while (rs.next()) {
	        	maVoiture = new Voiture();
	            maVoiture.setM_noSerie( rs.getInt("NO_SERIE"));
	            
	        }
	        rs.close();
		} else {
			System.out.println("Failed to make connection!");
		}
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}finally {
			if (stmt != null) 
				try { stmt.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
			  if (conn != null) {
				     try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
				     }
				}
 

	}
}
