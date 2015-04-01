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
	%>

	<br>
	<%if (idUser == null || obj == null) { %>
	<h1 id="welcome" style="text-align: center">Bonjour visiteur, bienvenue dans la page
		de gestion des voyages! Veuillez vous connecter dans la page connexion ou vous inscrire si vous n'avez pas de compte.</h1>
	<br>
	<%} else { %>
		<h1 id="welcome" style="text-align: center">Bonjour <%out.print(obj); %>, bienvenue dans la page
		de gestion des voyages!</h1>
	<br>
	<%} %>
	
	<form method="post" action = "servletItineraire">
		Date de départ format (YYYY-MM-JJ):<br>
		<input type="text" name="i_date">
		<br>
		
		Heure de départ exemple (18:45):<br>
		<input type="test" name="i_heureDepart">
		<br>
		
		Lieu de départ:<br>
		<input type="test" name="i_lieuDepart">
		<br>
		
		Destination:<br>
		<input type="test" name="i_destination">
		<br>
		
		Nombre de place disponible:<br>
		<input type="test" name="i_placeDisponible">
		<br>
		
		Prix par passager exigé (il est conseillé de baser le montant sur la distance à parcourir ):<br>
		<input type="test" name="i_prixExige">
		<br>
		
		<input type="submit" value="Valider!">

	</form>

</body>

</html> 


