package TP3Manager;

import java.sql.DriverManager;
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

import TP3Manager.ConnectionManager;

/**
 * Servlet implementation class servletConnection
 */
@WebServlet(description = "Gère les connexions entre le client, serveur et la bd", urlPatterns = { "/servletConnection" })
public class servletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection connectionBD = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletConnection() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		HttpSession session1 = req.getSession(true);

			System.out.println("Connexion");
			
				String nomUtilisateur = (String)req.getParameter("user");
				String motDePasse = (String)req.getParameter("password");
				
				String msg;
				try
				{
					//  ml   pwd =1234
					connectionBD = ConnectionManager.getInstance("connectionBD");
					Statement stmt = null;
					
					String nomUtilisateurForm = "'" + nomUtilisateur + "'";
					String passwordForm = "'" + motDePasse + "'";
					String sql = "SELECT IDCOMPTE, PSEUDO, MOTDEPASSE FROM TP3USAGER where PSEUDO =";
					sql += nomUtilisateurForm;
					sql += " and MOTDEPASSE =";
					sql += passwordForm;
					//sql += ";";
					System.out.println(sql);	
					stmt = connectionBD.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					if (rs.next()) {
						System.out.println("Tu as un compte et peut te connecter");
						session1.setAttribute("Utilisateur", nomUtilisateur);
		
					} else {
						System.out.println("Tu n'as pas de compte et ne peux te connecter");
					}
					//if nomUtilisateur == .......
					rs.close();
					stmt.close();
					connectionBD.close();
					res.sendRedirect("index.jsp");  
					
				}
				catch(Exception e)
				{
					e.printStackTrace();			
				}
			}


		/*String msg;
		PrintWriter out = res.getWriter();
		if (req.getParameter("eat") != null)
			msg = eat();
		else if (req.getParameter("pick") != null)
			msg = pick();
		else {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Requete incomplete.");
			return;
		}
		res.setContentType("text/html");
		res.setHeader("pragma", "no cache");
		out = res.getWriter();
		out.println("<html><head><title>");
		out.println("Le panier de pommes:");
		out.println("</title></head><body><h4>");
		out.println(msg);
		out.println("</h4><hr>");
		out.print("<ahref=\"");
		out.print(req.getRequestURI()); // out.println("\">rejouer?</a><br>");
										// out.close(); }*/
	
	}
