package TP3Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servletItineraire
 */
@WebServlet(description = "Gère les connexions entre le client, serveur et la bd", urlPatterns = { "/servletItineraire" })
public class servletItineraire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection connectionBD = null;
	private static CallableStatement callableStatement;
	private static String requeteSql;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletItineraire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Affichage de tous les itinéraires liés au compte. Pour ce,
		// on effectue une requête select avec la base de données.
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();
		
		try {
			int idUser = 1;
			String dateDepart = (String) req.getParameter("i_date");
			String heureDepart = (String) req.getParameter("i_heureDepart");
			String lieuDepart = (String) req.getParameter("i_lieuDepart");
			String destination = (String) req.getParameter("i_destination");
			String placeDisponible = (String) req.getParameter("i_placeDisponible");
			String prixParPassager = (String) req.getParameter("i_prixExige");
			String dateFormatCorrect ="to_date('" + dateDepart.toString() + " " + heureDepart.toString() + "', 'yyyy-mm-dd hh24:mi')";

			connectionBD = ConnectionManager.getInstance("connectionBD");
			Statement stmt = null;
			connectionBD.setAutoCommit(false);
	
			requeteSql = "{call tp3CreerItineraire(?,?,?,?,?,?)}";
			callableStatement = connectionBD.prepareCall(requeteSql);
			String sss = "INSERT INTO TP3ITINERAIRE"; 
			sss += " (IDCONDUCTEUR, HEUREDEPART,DEPART, DESTINATION, PRIXPARPASSAGE, DATEPUBLICATION,NBPASSAGER) " ; 
			sss += " VALUES "; 
			sss += "(" + idUser + "," + dateFormatCorrect + ",'" + lieuDepart + "','" + destination + "'," + prixParPassager + "," + "sysdate," + placeDisponible + ")";
			callableStatement.execute(sss);

			connectionBD.commit();

		} catch (SQLException e) {
			System.out.println(e.toString());
			
			if (connectionBD != null) {
				try {
					connectionBD.rollback();
					out.println("<HTML>\n<BODY>\n"
							+ "<H1>Création d'un itinéraire raté, un problème est survenu avec la base de donnée! Attendez la redirection ...</H1>\n"
							+ "</BODY></HTML>");

					res.setHeader("Refresh", "3; URL=voyages.jsp");
				} catch (SQLException excep) {
					System.out.println(excep.toString());
				}
			}
		}

		finally {
			if (callableStatement != null)
				try {
					callableStatement.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			if (connectionBD != null)
				try {
					connectionBD.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			out.println("<HTML>\n<BODY>\n"
					+ "<H1>Création d'un itinéraire reussi. Attendez la redirection ...</H1>\n"
					+ "</BODY></HTML>");

			res.setHeader("Refresh", "3; URL=voyages.jsp");
		}

	}

}
