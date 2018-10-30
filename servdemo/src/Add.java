

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@WebServlet(urlPatterns= {"/Add"})
public class Add extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ProcessRequest(req, res);

	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		ProcessRequest(req, res);
		
	}
	
	public void ProcessRequest(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		int i = Integer.parseInt(req.getParameter("t1"));
		int j = Integer.parseInt(req.getParameter("t2"));
		String info = "The addition is :";
		int k = i + j;
		
		PrintWriter out = res.getWriter();
		out.print(info);
		out.println(k);
		
	}
}