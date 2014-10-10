package webController;

import model.DoLoginCheck;
import model.GirdfileList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.v2.runtime.Name;


/**
 * Servlet implementation class AuthorVerify
 */
@WebServlet("/AuthorVerify")
public class AuthorVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getRootLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
//	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
//	}

	/**
	 * @see Servlet#destroy()
	 */
//	public void destroy() {
		// TODO Auto-generated method stub
//	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name=request.getParameter("name");
		System.out.println(name);
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		DoLoginCheck ch=new DoLoginCheck(username,password);
		if(ch.isSuccess())
		{
			response.sendRedirect("/Model3D/mainPage.jsp");
			logger.debug(username+" Login in success!");

		}
		else
		{
			PrintWriter out = response.getWriter();
            out.print("<script>alert('Access Failed!');location.href='/Model3D/login.jsp'</script>");
		}
	}


}
