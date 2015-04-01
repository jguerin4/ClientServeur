package TP3Manager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConnectionManager {

	private static Connection connect;
	private static final String ConnStr = "jdbc:oracle:thin:@dim-oracle.uqac.ca:1521/dimdb.uqac.ca";
	private static final String ConnUser = "8ASY200-01";
	private static final String ConnPwd = "eJAASn4Phm";
	private static HashMap hm;;

	// Constructeur privé
	private ConnectionManager() {
		hm = new HashMap();

	}

	public static void ajouterConnection(String nom) throws ClassNotFoundException {

		if (hm == null) {
			hm = new HashMap();	//Pour former un singleton ( s'assurer qu'il n'y a pas plus d'une connexion par poste
		}

		if (!hm.containsKey(nom)) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				Connection conn = DriverManager.getConnection(ConnStr,ConnUser, ConnPwd);
				hm.put(nom, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fermerConnections() {
		Set entrySet = hm.entrySet();
		Iterator it = entrySet.iterator();
		try {
			while (it.hasNext()) {
				Map.Entry me = (Map.Entry) it.next();
				Connection conn = (Connection) me.getValue();
				conn.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode qui va nous retourner notre instance et la créer si elle n'existe
	// pas, patron Singleton
	public static Connection getInstance(String nom) {

		Connection conn = (Connection) hm.get(nom);
		try {
			if (conn == null || !conn.isValid(0)) {
				conn = DriverManager.getConnection(ConnStr, ConnUser, ConnPwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}