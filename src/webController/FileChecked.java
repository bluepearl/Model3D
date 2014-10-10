package webController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.mySQLConnector;

/**
 * Servlet implementation class FileChecked
 */
@WebServlet("/FileChecked")
public class FileChecked extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileChecked() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql="update gridfile set verified=1 where verified=0;";
		mySQLConnector con=new mySQLConnector();
		con.executeUpdate(sql);
		PrintWriter out = response.getWriter();
		out.print("<script>alert('success!');</script>");
		//request.getRequestDispatcher("/adminManage.jsp").forward(request, response);
        out.print("<script>location.href='/Model3D/adminManage.jsp'</script>");
	}

}
