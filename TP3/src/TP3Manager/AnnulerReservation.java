package TP3Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AnnulerReservation
 */
@WebServlet("/AnnulerReservation")
public class AnnulerReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection connectionBD = null;
	private static CallableStatement callableStatement;
	private static String requeteSql;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnulerReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HttpSession session1 = req.getSession(true);
		Object obj = session1.getAttribute("Utilisateur");
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();
		
		try {
			String itineraireID = (String) req.getParameter("idItineraire");
			//Va chercher une connection � l'aide de Connection Manager
			connectionBD = ConnectionManager.getInstance("connectionBD");
			Statement stmt = null;
			stmt = connectionBD.createStatement();
			//Cr�ation de la requ�te sql
			String sss = " DELETE FROM PASSAGER WHERE IDCOMPTE = " + obj + " AND IDITINERAIRE = " + itineraireID;
			System.out.println(sss);
			connectionBD.setAutoCommit(false);
			stmt.executeQuery(sss);
			sss = " UPDATE TP3ITINERAIRE SET NBPASSAGER = NBPASSAGER + 1 WHERE IDITINERAIRE = " + itineraireID;
			System.out.println(sss);
			stmt.executeQuery(sss);
			sss = " UPDATE TP3USAGER SET NBANNULATION = NBANNULATION + 1 WHERE IDCOMPTE = " + obj;
			System.out.println(sss);
			stmt.executeQuery(sss);
			
			// Enregistre la modification dans la base de donn�es
			connectionBD.commit();

		} catch (SQLException e) {
			System.out.println(e.toString());
			
			if (connectionBD != null) {
				try {
					connectionBD.rollback();//Gestion d'erreur, on redirige l'usager pour raison de simplicit�
					out.println("<HTML>\n<BODY>\n"
							+ "<H1>Annulation d'un itin�raire rat�, un probl�me est survenu avec la base de donn�e! Attendez la redirection ...</H1>\n"
							+ "</BODY></HTML>");

					res.setHeader("Refresh", "3; URL=itineraire.jsp");
				} catch (SQLException excep) {
					System.out.println(excep.toString());
				}
			}
		}

		finally {	//Fermeture des �l�ments qui interagissent avec la base de donn�es.
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
			out.println("<HTML>\n<BODY>\n"	//R�ussite
					+ "<H1>Annulation d'un itin�raire reussi. Un courriel � �t� envoy� aux utilisateurs concern�s. Attendez la redirection ...</H1>\n"
					+ "</BODY></HTML>");

			res.setHeader("Refresh", "5; URL=itineraire.jsp");
		}

	
		
		
		
		
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
