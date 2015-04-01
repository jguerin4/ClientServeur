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
 * Servlet implementation class servletInscription
 */
@WebServlet("/servletInscription")
public class servletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Connection connectionBD = null;
	private static CallableStatement callableStatement;
	private static String requeteSql;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletInscription() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = res.getWriter();
		try {
			String nomUtilisateur = (String) req.getParameter("user");
			String motDePasse = (String) req.getParameter("password");
			String confirmMDP = (String) req.getParameter("confirmpassword");
			String prenom = (String) req.getParameter("prenom");
			String nom = (String) req.getParameter("nom");
			String courriel = (String) req.getParameter("courriel");
			String telephone = (String) req.getParameter("telephone");
			String birthday = (String) req.getParameter("birthday");
			String fumeur = (String) req.getParameter("fumeur"); // nonfumeur ou
																	// ouifumeur
			String animaux = (String) req.getParameter("animaux");// nonanimaux
																	// ou
																	// ouianimaux

			System.out.println("user:" + nomUtilisateur + " MDP: " + motDePasse
					+ "MDPConfirm: " + confirmMDP + "prenom :" + prenom
					+ "nom :" + nom + "courriel :" + courriel + "telephone :"
					+ telephone + "birthday :" + birthday + "Fumeur :" + fumeur
					+ "Animaux :" + animaux);

			connectionBD = ConnectionManager.getInstance("connectionBD");
			Statement stmt = null;
			connectionBD.setAutoCommit(false);
			
			if (!courriel.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]"
					+ "+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
			{
				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription raté, le champs \"Courriel\" que vous avez entrée n'est pas valide! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=inscription.jsp");
			}
			else if(!telephone.matches("(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}"))
			{
				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription raté, le champs \"Téléphone\" que vous avez entrée n'est pas valide! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=inscription.jsp");
			}
			else if(!birthday.matches("([0-9]{4}[/]{1}[0-9]{2}[/]{1}[0-9]{2})"))
			{
				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription raté, le champs \"Date de Naissance\" que vous avez entrée n'est pas valide! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=inscription.jsp");
			}
			else if(motDePasse == "")
			{
				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription raté, le champs \"Mot de passe\" que vous avez entrée n'est pas valide! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=inscription.jsp");
				
			}
			else if (motDePasse.equals(confirmMDP)) {
				requeteSql = "{call TP3CREERUSAGER(?,?,?,?,?,?,?,?,?)}";
				callableStatement = connectionBD.prepareCall(requeteSql);
				callableStatement.setString(1, nom);
				callableStatement.setString(2, prenom);
				callableStatement.setString(3, birthday);
				callableStatement.setString(4, telephone);
				callableStatement.setString(5, courriel);
				callableStatement.setString(6, nomUtilisateur);
				callableStatement.setString(7, motDePasse);
				callableStatement.setString(8, fumeur);
				callableStatement.setString(9, animaux);
				callableStatement.executeUpdate();
				connectionBD.commit();

				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription réussi avec succès! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=connexion.jsp");
			}

			else {

				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Inscription raté, la confirmation de mot de passe n'est pas pareil au mot de passe! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				res.setHeader("Refresh", "3; URL=inscription.jsp");
			}

		} catch (SQLException e) {
			System.out.println(e.toString());
			if (connectionBD != null) {
				try {
					connectionBD.rollback();
					out.println("<HTML>\n<BODY>\n"
							+ "<H1>Inscription raté, un problème est survenu avec la base de donnée! Attendez la redirection ...</H1>\n"
							+ "</BODY></HTML>");

					res.setHeader("Refresh", "3; URL=inscription.jsp");
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
		}

	}

}
