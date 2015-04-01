<%@page import="TP3Manager.MenuTravelExpress"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%! MenuTravelExpress mm = new MenuTravelExpress();%>
<% out.println(mm.getStyleMenu()); %>


  <title>Travel Express</title>
  

</head>

<body>
	<%HttpSession session1 = request.getSession(true);
	Object obj = session1.getAttribute("Utilisateur");
	
	if(obj==null){
		out.println(mm.getMenuNonConnecte()); 
	}else{
		
		out.println(mm.getMenu()); 
	}
	
	%>
	
<h1> Bienvenue dans la pag recherche!</h1>
<h4> Veuillez entrer les paramètres de votre recherche. Les coordonnées des personnes interessés seront alors affichés.</h4>
<!-- Départ arrivée date -->

<form action="recherche.jsp">
	Ville de départ:
	<input type="text" name="depart" 
	value="<% out.println(request.getParameter("depart")==null ? "":request.getParameter("depart")); %>">
	
	
	Ville de destination:
	<input type="text" name="destination" 
	value="<% out.println(request.getParameter("destination")==null ? "":request.getParameter("destination")); %>">
	
	
	Date du voyage(YYYY-MM-JJ):
	<input type="text" name="dateVoyage"
	value="<% out.println(request.getParameter("dateVoyage")==null ? "":request.getParameter("dateVoyage")); %>">

	<input type="submit" value="Rechercher!">

</form>
</br>
<h2> Resultats</h2>
</br>

<table border="1" width="800">
	<tr>
		<td align="center"> Départ</td>
		<td align="center"> Arrivé</td>
		<td align="center"> Date</td>
		<td align="center"> Conducteur</td>
	</tr>
	<% 
	

	char cGil = (char)34;
	
	String sql = "SELECT i.IDITINERAIRE, u.NOM, u.PRENOM, i.DATEPUBLICATION, to_char(i.HEUREDEPART, 'yyyy-mm-dd hh24:mi') as HEUREDEPART, i.DESTINATION, i.PRIXPARPASSAGE, i.NBPASSAGER, i.DEPART" ;
	sql += " FROM TP3ITINERAIRE i INNER JOIN TP3USAGER u ON i.IDCONDUCTEUR = u.IDCOMPTE ";
	
	String where = "";
	if(request.getParameter("depart") != null){
		if(!request.getParameter("depart").equals("")){
		where += " UPPER(DEPART) = UPPER('" + request.getParameter("depart") + "') ";
		}
	}
	
	if(request.getParameter("destination") != null){
		if(!request.getParameter("destination").equals("")){
			if(!where.equals(""))
				where += " AND ";
			where += " UPPER(DESTINATION) = UPPER('" + request.getParameter("destination") + "') ";
		}
	}
	
	if(request.getParameter("dateVoyage") != null){
		if(!request.getParameter("dateVoyage").equals("")){
			if(!where.equals(""))
				where += " AND ";
			where += " to_char(HEUREDEPART,'yyyy-mm-dd') = '" + request.getParameter("dateVoyage").trim() + "') ";
		}	
	}
	
	if(!where.equals(""))	
		sql += " WHERE " + where;
	
	//out.println(sql);
	
	java.sql.Statement stmt = null;
	String ConnStr = "jdbc:oracle:thin:@dim-oracle.uqac.ca:1521/dimdb.uqac.ca";
	String ConnUser = "8ASY200-01";
	String ConnPwd = "eJAASn4Phm";
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection conn = java.sql.DriverManager.getConnection(ConnStr,ConnUser, ConnPwd);
		
		stmt = conn.createStatement();

		java.sql.ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
        	out.println("<tr> ");
        	out.println("<td ><a href=" + cGil + "detailItineraire.jsp?idItineraire=" + rs.getString("IDITINERAIRE") + cGil + " >" + rs.getString("DEPART") + "</a></td>)");
        	out.println("<td ><a href=" + cGil + "detailItineraire.jsp?idItineraire=" + rs.getString("IDITINERAIRE") + cGil + " >" + rs.getString("DESTINATION") + "</a></td>)");
        	out.println("<td ><a href=" + cGil + "detailItineraire.jsp?idItineraire=" + rs.getString("IDITINERAIRE") + cGil + " >" + rs.getString("HEUREDEPART") + "</a></td>)");
        	out.println("<td ><a href=" + cGil + "detailItineraire.jsp?idItineraire=" + rs.getString("IDITINERAIRE") + cGil + " >" + rs.getString("PRENOM") + " " + rs.getString("NOM") + "</a></td>)");
        	out.println("</tr>");
        }
        rs.close();
        stmt.close();
        conn.close();
        
	} catch (java.sql.SQLException  e) {
		System.out.println(e.toString());

	}
	
	 %>

</table>

</body>

</html> 