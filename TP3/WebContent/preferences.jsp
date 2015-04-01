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
		//session1.setAttribute("Utilisateur", "Marcel");
	%>

	<br>
	<%if (idUser == null) { %>
	<h1 id="welcome" style="text-align: center">Bonjour visiteur, bienvenue dans la page
		des préférences du compte! Veuillez vous connecter dans la page connexion ou vous inscrire si vous n'avez pas de compte.</h1>
	<br>
	<%} else { %>
		<h1 id="welcome" style="text-align: center">Bonjour <%out.print(obj); %>, bienvenue dans la page
		des préférences du compte!</h1>
	<br>
	<%} %>
<h4> Vous pouvez ici modifier les préférences de votre compte</h4>

<form method=POST action=servletPreferences>
	<input type="radio" name="fumeur" value=0 checked>Je préfère voyager dans un véhicule sans fumeur 
	<br>
	<input type="radio" name="fumeur" value=1>Je n'ai pas d'objection à voyager dans un véhicule avec fumeurs 
	<br>
	<br>
	<input type="radio" name="animaux" value=0 checked>Je préfère voyager dans une voiture sans animaux 
	<br>
	<input type="radio" name="animaux" value=1>Je n'ai pas d'objection à voyager avec des animaux 
	<br><br>
	<input type="submit" value="Modifier">
</form>


</body>

</html> 


