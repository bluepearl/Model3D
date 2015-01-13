package webController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartCompute
 */
@WebServlet("/StartCompute")
public class StartCompute extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String [] IpAddress = {"223.3.37.159","223.3.37.159","223.3.37.159"};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartCompute() {
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
		String taskname=request.getParameter("taskname");
		String solver=request.getParameter("solver");
		System.out.println("Start Project:"+taskname);
		System.out.println("Solver:"+solver);
		//之后由孟写，主要是启动计算的后续步骤
		sendStartCommand(taskname, solver);
		response.getWriter().append("Compute Start!");
	}
	protected void sendStartCommand(String strTask, String strMethod)
	{
		try 
		{
			Socket socket = new Socket("223.3.37.159", 9998);
			PrintWriter pt = new PrintWriter(socket.getOutputStream());
			
			if (strMethod.equals("FEKO"))
			{
				strTask += ".pre";
			}
			else
			{
				strTask += ".cst";
			}
			
			pt.println(strTask);
			pt.flush();
			pt.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
