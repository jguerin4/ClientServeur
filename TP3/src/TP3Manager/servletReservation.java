package TP3Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class servletReservation
 */
@WebServlet("/servletReservation")
public class servletReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection connectionBD = null;
	Statement stmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Servlet appelé par reservation.jsp , s'occupe d'associer une comtpte à 
		// un itinéraire et de mettre à jour les caractéristiques de l'itinéraire. 
		
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();
		
		HttpSession session1 = req.getSession(true);
		String idUser = session1.getAttribute("Utilisateur").toString();
		String idItineraire_s = req.getParameter("idItineraire");
		String nombrePassager_s = req.getParameter("nombreReservation");
		
		int nombrePassager = Integer.parseInt(nombrePassager_s);
		int idCompteCourant = Integer.parseInt((String)idUser);
		int idItineraire = Integer.parseInt(idItineraire_s);				
		
		System.out.println("Itinéraire #" + idItineraire + "idUser #" + idCompteCourant + " Nombre passager: " + nombrePassager);
		
		try {
		connectionBD = ConnectionManager.getInstance("connectionBD");
		connectionBD.setAutoCommit(false);
		String sql = "INSERT INTO PASSAGER (IDCOMPTE,IDITINERAIRE) VALUES (";
		sql += idCompteCourant;
		sql += ",";
		sql += idItineraire;
		sql += ")";
		System.out.println(sql);
			
		stmt = connectionBD.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		
		if (rs.next()) {
			System.out.println("Inscription au voyage (1/2) réussi");
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			out.println("<HTML>\n<BODY>\n"
					+ "<H1>Inscription raté, la base de donnée n'a pas trouvé de correspondance à votre demande(1/2)! Attendez la redirection ...</H1>\n"
					+ "</BODY></HTML>");

			res.setHeader("Refresh", "3; URL=index.jsp");
		}
		
		//Deuxième requête sql
		sql = "";
		sql = "UPDATE TP3ITINERAIRE SET NBPASSAGER = NBPASSAGER - ";
		sql += nombrePassager;
		sql += " WHERE IDITINERAIRE = ";
		sql += idItineraire;
		
		System.out.println(sql);
		
		
		stmt = connectionBD.createStatement();
		rs = stmt.executeQuery(sql);
		
		
		if (rs.next()) {
			System.out.println("Inscription au voyage (2/2) réussi");
			connectionBD.commit();
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			out.println("<HTML>\n<BODY>\n"
					+ "<H1>Inscription raté, la base de donnée n'a pas trouvé de correspondance à votre demande(2/2)! Attendez la redirection ...</H1>\n"
					+ "</BODY></HTML>");

			res.setHeader("Refresh", "3; URL=index.jsp");
		}
		
		
		
		
	}
		catch (Exception e) {
			try {
				connectionBD.rollback();
				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription raté, un problème est survenu avec la base de donnée! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=recherche.jsp");
				e.printStackTrace();
			} catch (SQLException excep) {
				System.out.println(excep.toString());
			}
		
			
		}
		
		finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			if (connectionBD != null)
				try {
					connectionBD.close();
					out.println("<HTML>\n<BODY>\n"
							+ "<H1>Inscription au voyage réussi! Attendez la redirection ...</H1>\n"
							+ "</BODY></HTML>");

					res.setHeader("Refresh", "3; URL=itineraire.jsp");
				} catch (Exception e) {
					System.out.println(e.toString());
				}
		}
		
		
	}

}
