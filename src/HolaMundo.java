
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
public class HolaMundo extends HttpServlet {	
	private static final long serialVersionUID = 1L;       	
	public void service(ServletRequest request, ServletResponse response)		
			throws ServletException, IOException {			response.setContentType("text/html");			
			PrintWriter writer = response.getWriter();			
			writer.print("<HTML><BODY><marquee><h1>SI VES ESTO ES PORQUE ESTAS EN PROBLEMAS! JAJAJAJA " + new Date(0).toString()+ "</BODY></HTML>");		} 	}