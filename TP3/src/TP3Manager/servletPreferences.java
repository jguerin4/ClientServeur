package TP3Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class servletPreferences
 */
@WebServlet("/servletPreferences")
public class servletPreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection connectionBD = null;
	Statement stmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletPreferences() {
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
		//Permet de modifier les préférences du comtpte, puisque l'usager peut vérifier si 
		// un voyage correspond à ses préférences.
		
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			HttpSession session1 = req.getSession(true);
			int fumeur = Integer.parseInt(req.getParameter("fumeur"));
			int animaux = Integer.parseInt(req.getParameter("animaux"));
			
			
			connectionBD = ConnectionManager.getInstance("connectionBD");

			
			int idCompteCourant = (int) session1.getAttribute("Utilisateur");
			
			String sql = "UPDATE TP3USAGER SET FUMEUR = "
			+ fumeur + ", ANIMAL = "
			+ animaux + " WHERE IDCOMPTE = "
			+ idCompteCourant;// + ";";
			System.out.println(sql);
			stmt = connectionBD.createStatement();
			stmt.executeQuery(sql);
			PrintWriter out = res.getWriter();
			
			out.println("<HTML>\n<BODY>\n"
					+ "<H1>Changement des préférences réussi avec succès! Attendez la redirection ...</H1>\n"
					+ "</BODY></HTML>");

			res.setHeader("Refresh", "3; URL=index.jsp");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			PrintWriter out = res.getWriter();
			out.println("<HTML>\n<BODY>\n"
					+ "<H1>Changement des préférences échoué, un problème est survenu! Attendez la redirection ...</H1>\n"
					+ "</BODY></HTML>");

			res.setHeader("Refresh", "3; URL=index.jsp");
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
				} catch (Exception e) {
					System.out.println(e.toString());
				}
		}
		
		
		
	}

}
