<%@page import="TP3Manager.MenuTravelExpress"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!MenuTravelExpress mm = new MenuTravelExpress();%>
<%
	out.println(mm.getStyleMenu());
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Travel Express</title>

</head>

<body>
		<%
		HttpSession session1 = request.getSession(true);
		Object obj = session1.getAttribute("Utilisateur");
		//out.println("L'utilisateur est :>");
		//out.print(obj);

		if (obj == null)
		{
			out.println(mm.getMenuNonConnecte());
		}
		else
		{
			out.println(mm.getMenu());
		}
		//session1.setAttribute("Utilisateur", "Marcel");
	%>

	<br>
	<%if (obj == null) { %>
	<h1 id="welcome" style="text-align: center">Bonjour visiteur, bienvenue dans la page
		recherche! Veuillez vous connecter dans la page connexion ou vous inscrire si vous n'avez pas de compte.</h1>
	<br>
	<%} else { %>
		<h1 id="welcome" style="text-align: center">Bonjour <%out.print(obj); %>, bienvenue dans la page
		recherche!</h1>
	<br>
	<%} %>
<h4> Veuillez entrer les paramètres de votre recherche. Les coordonnées des personnes interessés seront alors affichés.</h4>
<!-- Départ arrivée date -->

<form>
	Ville de départ:
	<input type="text" name="depart">
	
	
	Ville de destination:
	<input type="text" name="destination">
	
	
	Date du voyage(YYYY/MM/JJ):
	<input type="text" name="confirmpassword">

	<input type="submit" value="Rechercher!">

</form>

</body>

</html> 


