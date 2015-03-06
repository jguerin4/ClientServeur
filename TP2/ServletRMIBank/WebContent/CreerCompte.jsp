<jsp:useBean id="compte" class="bank.CreerCompte"/>
<HTML>
<HEAD>
</HEAD>
<BODY>
<%
	//String userName = request.getParameter("userName");
	//float money = request.getParameter("amount");

	//compte.creer_compte(userName.money);
	// theout.print(theBean.messageAch());
	
	String userName = request.getParameter("userName");
	String money = request.getParameter("amount");
	double am = Double.valueOf(money);
	
	// bank.CreerCompte compte = new bank.CreerCompte();
	compte.creer_compte(userName, am);
	out.println(userName);
	out.println(am);
     
%>
</BODY>
</HTML>