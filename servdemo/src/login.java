

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;


@WebServlet(urlPatterns= {"/login"})
public class login extends HttpServlet 
{

	private static final long serialVersionUID = 1L; //this is like unique id for the class

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		int usrid = Integer.parseInt(request.getParameter("usr"));
		String reason = request.getParameter("reason");
		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		int leavedays = Integer.parseInt(request.getParameter("leavedays"));
		PrintWriter out = response.getWriter();
		

		Connection myConn;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost/userinfo", "succexa", "succexa");
			Statement mystmt = myConn.createStatement();
			String query  = "insert into LeaveApplication values("+usrid+","+fromdate+","+todate+","+leavedays+","+reason+")";
			out.println(query);
			int update = mystmt.executeUpdate("insert into LeaveApplication values('"+usrid+"','"+fromdate+"','"+todate+"','"+leavedays+"','"+reason+"')");
		   try
		    {
		    	out.println(update+"Rows inserted.");
			ResultSet myRs = mystmt.executeQuery("select UserName from Users where UserId="+usrid);
			out.print("Hello, ");
			while(myRs.next())
			{
				out.println(myRs.getString("UserName")+"\n Your Application is for review for Reason :: \n"+reason);
			}
			out.println("From date "+fromdate+" - to date -  "+todate);
			out.print("For days : ");
			out.println(leavedays);
		    }
		    catch(java.sql.SQLIntegrityConstraintViolationException e)
		    {
		     out.println(e);
		     myConn.close();
		    
		    }
		    		
		} 
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}
