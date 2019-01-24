package HttpServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class DisplayServlet
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter Writer=response.getWriter();
		HttpSession session=request.getSession(false);
		if(session==null);
		{
			Writer.println("you are not logged in");
		}
		else {
			Writer.println("you are logged in");
		}
		
			
		int studRollNo=(int)session.getAttribute("studRoll");
		 try {		
		   	 Class.forName ("oracle.jdbc.driver.OracleDriver");
	           Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
	        System.out.println("connected");
				
				 String Query ="select * from studenttable where roll_num= "+studRoll";
				 java.sql.Statement st= cn.createStatement();
				 ResultSet result=st.executeQuery(Query);
				 if(result.next()==true) {
					 Writer.println("<student marks are:>");
					 Writer.println("<form action='LogoutServlet' method='post'>");
					 Writer.println("<input type='submit' value='ok'");
					 Writer.println("</form>");
					 
					 }
		        }
		    catch (ClassNotFoundException | SQLException e)
		    {	
		    	System.out.println(e.getMessage());
		    	System.out.println("in exception");
				}
			    // Closes the Connection
		}
		
	}

}
