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
	Object userActif = session1.getAttribute("Utilisateur");
	int usagerPourTeste = 1;
	if(obj==null){
		out.println(mm.getMenuNonConnecte()); 
	}else{
		
		out.println(mm.getMenu()); 
	}
	
	%>
	
<h1> Bienvenue dans la page publication!</h1>
<h4> Voici la liste de vos itinéraires.</h4>
<!-- Départ arrivée date -->

<table border="1" width="800">
	<tr>
		<td align="center"> Départ</td>
		<td align="center"> Arrivé</td>
		<td align="center"> Date</td>
		<td align="center"> Conducteur</td>
		<td align="center"> Prix Par passager</td>
		<td align="center"> </td>
	</tr>
	<% 
	
	char cGil = (char)34;
	
	String sql = "SELECT i.IDITINERAIRE, u.NOM, u.PRENOM, i.DATEPUBLICATION, to_char(i.HEUREDEPART, 'yyyy-mm-dd hh24:mi') as HEUREDEPART, i.DESTINATION, i.PRIXPARPASSAGE, i.NBPASSAGER, i.DEPART" ;
	sql += " FROM TP3USAGER u inner join PASSAGER p ON u.IDCOMPTE = p.IDCOMPTE inner join TP3ITINERAIRE i on p.IDITINERAIRE = i.IDITINERAIRE ";
	sql += "WHERE u.IDCOMPTE = " + userActif;
	System.out.println(sql);
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
        	out.println("<td > " + rs.getString("DEPART") + "</a></td>)");
        	out.println("<td > " + rs.getString("DESTINATION") + "</a></td>)");
        	out.println("<td > " + rs.getString("HEUREDEPART") + "</a></td>)");
        	out.println("<td > " + rs.getString("PRENOM") + " " + rs.getString("NOM") + "</a></td>)");
        	out.println("<td > " + rs.getString("PRIXPARPASSAGE") + "</a></td>)");
        	out.println("<td ><a href=" + cGil + "AnnulerReservation?idItineraire=" + rs.getString("IDITINERAIRE") + cGil + " >" + "Annulation" + "</a></td>)");
        	//out.println("<td ><a href= http://localhost:8080/TP3/itineraire.jsp "+ " >" + "Annulation"  + "</a></td>)");
        	out.println("</tr>");
        	
        }
        rs.close();
        stmt.close();
        conn.close();
        
	} catch (java.sql.SQLException  e) {
		System.out.println(e.toString());

	}
	
	 %>
	 
	 <form method="post" action = "servletItineraire">
	 

</table>

</body>

</html> 