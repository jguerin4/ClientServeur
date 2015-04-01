<%@page import="TP3Manager.MenuTravelExpress"%>
<%@page import="TP3Manager.ConnectionManager"%>
<%@page import ="java.sql.Connection"%>
<%@page import ="java.sql.ResultSet"%>
<%@page import ="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!MenuTravelExpress mm = new MenuTravelExpress();%><%
	out.println(mm.getStyleMenu());
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Travel Express</title>

</head>
<body>
	<%
	Statement stmt = null;
	ResultSet rs = null;
	String nomConducteur;
	String prenomConducteur;
	String datePublication;
	String heureDepart;
	String destination;
	int prixParPassager;
	int nombrePassager;
	String depart;
	int actif;
		HttpSession session1 = request.getSession(true);
		Object obj = session1.getAttribute("PrenomNom");
		Object idUser = session1.getAttribute("Utilisateur");
		if (idUser == null)
		{
			out.println(mm.getMenuNonConnecte());
		}
		else
		{
			out.println(mm.getMenu());
		}
	%>

	<br>
	<%if (idUser == null) { %>
	<h1 id="welcome" style="text-align: center">Bonjour visiteur,  les informations sur
		l'itin�raire que vous avez s�lectionn� sont ci-dessous!<br> Veuillez vous
		 connecter dans la page connexion ou vous inscrire si vous n'avez pas de compte.</h1>
	<br>
	<%} else { %>
		<h1 id="welcome" style="text-align: center">Bonjour <%out.print(obj); %>, les informations sur
		l'itin�raire que vous avez s�lectionn� sont ci-dessous!</h1>
	<br>
	<%} %>
	
	<%
	if(request.getParameter("idItineraire") != null) 
	{
		String idItineraire =  request.getParameter("idItineraire");
		Connection connectionBD = null;
		try {
			System.out.println("Test ajout connexion");
			ConnectionManager.ajouterConnection("connectionBD");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connectionBD = ConnectionManager.getInstance("connectionBD");
			String sql = "SELECT u.NOM, u.PRENOM, i.DATEPUBLICATION, i.HEUREDEPART ";
			sql += ", i.DESTINATION, i.PRIXPARPASSAGE, i.NBPASSAGER, i.DEPART, i.ACTIF ";
			sql += " FROM TP3ITINERAIRE i INNER JOIN TP3USAGER u ON i.IDCONDUCTEUR = u.IDCOMPTE ";
			sql += " WHERE i.IDITINERAIRE = ";
			sql += idItineraire;
			
			System.out.println(sql);
			
			stmt = connectionBD.createStatement();
			System.out.println("1");
			rs = stmt.executeQuery(sql);
			System.out.println("2");
			
			if (rs.next())
			{
				System.out.println("3");
				nomConducteur = rs.getString("NOM");
				prenomConducteur = rs.getString("PRENOM");
				datePublication = rs.getString("DATEPUBLICATION");
				heureDepart = rs.getString("HEUREDEPART");
				destination = rs.getString("DESTINATION");
				prixParPassager = rs.getInt("PRIXPARPASSAGE");
				nombrePassager = rs.getInt("NBPASSAGER");
				depart = rs.getString("DEPART");
				actif = rs.getInt("ACTIF");
				
				%>
				 <div style="background-color:#a50026; color:white; padding:20px;">

				 <h2>Voyage #<% out.print(idItineraire); %></h2>
				 <br>
				 <p style="color:white;">
				 <% /*
				 nomConducteur = rs.getString("NOM");
						prenomConducteur = rs.getString("prenomConducteur");
						datePublication = rs.getString("DATEPUBLICATION");
						heureDepart = rs.getString("HEUREDEPART");
						destination = rs.getString("DESTINATION");
						prixParPassager = rs.getInt("PRIXPARPASSAGE");
						nombrePassager = rs.getInt("NBPASSAGER");
						depart = rs.getString("DEPART");
						actif = rs.getInt("ACTIF");*/
						%>
				 Lieu de d�part: <%out.print(depart); %>	<br>
				 Lieu de destination: <%out.print(destination); %>	<br>
				 Date de d�part: <%out.print(depart); %>	<br>
				 Conducteur principal: <%out.print(prenomConducteur + " " + nomConducteur); %>	<br>
				 Heure de d�part:	<br>
				 Prix demand� par passag� (ce prix est bas� selon la distance du voyage):	<br>
				 Nombre de passager:	<br>
				 Vous �tes pr�sentement inscrit � ce voyage: <br>
				 
				 </p>

				 </div> 
				 <form method=POST action=servletEnregistrementItineraire>
				 <input type="hidden" name="idItineraire" value="<%idItineraire.toString();%>">
				 <input type="number" name="nombreReservation" min="0" max="<% String.valueOf(nombrePassager); %>"><br>
				 <input type="submit" value="R�server!">
				 </form>
	<%
	System.out.println("4");
			}
			else
			{
				System.out.println("5");
				out.println("<HTML>\n<BODY>\n"
						+ "<H1>Vous avez s�lectionn� un voyage invalide! Attendez la redirection ...</H1>\n"
						+ "</BODY></HTML>");

				response.setHeader("Refresh", "5; URL=index.jsp");
				
			}
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
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
		%>
		
		
</body>

</html>


