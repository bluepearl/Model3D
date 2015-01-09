package webController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class NewComputeTask
 */
@WebServlet("/NewComputeTask")
public class NewComputeTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewComputeTask() {
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
		String storepath=request.getParameter("storepath");
		String submittime=request.getParameter("time");
		UploadTask ut=new UploadTask(submittime,taskname,"",solver);
		ut.recordInsert();
		
		
		String param=ut.NewTaskParams();
		if(param != null){
		response.getWriter().append(param);
		}
		else response.getWriter().append("project failed!");
	}

//	private String generateTaskName() {
//		long currentTime = System.currentTimeMillis();
//		int i = (int) (Math.random() * 1000D + 1.0D);
//		long result = currentTime + i;
//		String taskname = String.valueOf(result);
//		return taskname;
//	}
}
