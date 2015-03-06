package servletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import java.io.*;
import java.util.*;


/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try{
			//RequestDispatcher dispatcher=null; 
			//String destination1 = "/jsp/afficheach.jsp";
			 if (  (request.getParameter("choix")).equals("CreerCompte") ){  
				//dispatcher = request.getRequestDispatcher("/jsp/CreerCompte.jsp"); 
				response.setContentType("text/html");
			    PrintWriter out = response.getWriter();
			    out.println("<HTML>");
			    out.println("<HEAD>");
			    out.println("<TITLE>Login</TITLE>");
			    out.println("</HEAD>");
			    out.println("<BODY>");
			    // if (withErrorMessage)
			      // out.println("Login failed. Please try again.<BR>");
			    out.println("<BR>");
			    out.println("<BR>Please enter your user name and amount.");
			    out.println("<BR><FORM METHOD=POST>");
			    out.println("<BR>User Name: <INPUT TYPE=TEXT NAME=userName>");
			    out.println("<BR>Amount: <INPUT TYPE=TEXT NAME=amount>");
			    out.println("<BR><INPUT TYPE=SUBMIT VALUE=Submit>");
			    out.println("</FORM>");
			    out.println("</BODY>");
			    out.println("</HTML>");
			
			}
			if (  (request.getParameter("choix")).equals("Recherche") ){  
					//dispatcher = request.getRequestDispatcher("/jsp/CreerCompte.jsp"); 
					response.setContentType("text/html");
				    PrintWriter out = response.getWriter();
				    out.println("<HTML>");
				    out.println("<HEAD>");
				    out.println("<TITLE>Login</TITLE>");
				    out.println("</HEAD>");
				    out.println("<BODY>");
				    // if (withErrorMessage)
				      // out.println("Login failed. Please try again.<BR>");
				    out.println("<BR>");
				    out.println("<BR>Please enter your user name and amount.");
				    out.println("<BR><FORM METHOD=POST>");
				    out.println("<BR>User Name: <INPUT TYPE=TEXT NAME=userName>");
				    out.println("<BR>Amount: <INPUT TYPE=TEXT NAME=amount>");
				    out.println("<BR><INPUT TYPE=SUBMIT VALUE=Submit>");
				    out.println("</FORM>");
				    out.println("</BODY>");
				    out.println("</HTML>");
				
				}
			// dispatcher.forward(request, response); 	
		    }catch (Exception e) {  }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try { 
			/* RequestDispatcher dispatcher=null; 
			// String destination1 = "/jsp/afficheach.jsp";
			dispatcher = request.getRequestDispatcher("CreerCompte.jsp"); 
			dispatcher.forward(request, response); 	*/
			System.out.println("Hello .............");
			String userName = request.getParameter("userName");
			String money = request.getParameter("amount");
			double am = Double.valueOf(money);
			bank.CreerCompte compte = new bank.CreerCompte();
			compte.creer_compte(userName, am);
			System.out.println("Hello .............");
			
			PrintWriter out = response.getWriter();
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Login</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println(userName);
			out.println(am);
			out.println("</BODY>");
			out.println("</HTML>");
			
		}catch (Exception e) {  }   
	}

}
