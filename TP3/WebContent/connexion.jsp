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
		de connexion! Veuillez vous connecter dans la page connexion ou vous inscrire si vous n'avez pas de compte.</h1>
	<br>
	<%} else { %>
		<h1 id="welcome" style="text-align: center">Bonjour <%out.print(obj); %>, bienvenue dans la page
		de connexion!</h1>
	<br>
	<%} %>
	
	<form method=POST action=servletConnection>
		Nom d'utilisateur:<br><input type="text" name=user ><br>
		
		Mot de passe:<br><input type="password" name=password ><br>

		<input type="submit" value="Connexion!"/>
	</form>

</body>

</html> 


