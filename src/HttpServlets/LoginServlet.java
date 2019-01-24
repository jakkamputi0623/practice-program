package HttpServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String Student=request.getParameter("stuname");
		String password=request.getParameter("pass");
	    
	   
	    try {		
	   	 Class.forName ("oracle.jdbc.driver.OracleDriver");
	   	Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
          System.out.println("connected");
          out.println("connected");
			
			 String qry=("select roll_num,stuname,pass from studenttable where stuname='"+Student+"' and pass='"+ password+"'");
			 java.sql.Statement st= cn.createStatement();
			 ResultSet result=st.executeQuery(qry);
			 if(result.next()==true) {
				 int studRoll=result.getInt(1);
				 out.println("<h1>login successful</h1>");
				 out.println("<form action='DisplayServlet' method='post'>");
				 out.println("<input type='submit' value='ok'");
				 out.println("</form>");
				 HttpSession session=request.getSession();
				 session.setAttribute("studRoll",studRoll);
			 }
			 else {
				 out.println("<h1>login unsuccessful</h1>");
				 out.println("<form action='DisplayServlet' method='post'>");
				 out.println("<input type='submit' value='ok'");
				 out.println("</form>");
				 
				 }
	        }
	    catch (Exception e)
	    {	
	    	
			}
		    // Closes the Connection
	}
	 /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
