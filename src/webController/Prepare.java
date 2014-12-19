/** 
* <p> Title: Global </p> 
* <p> Description: 定义系统中使用的常量 </p> 
* <p> Copyright: Copyright (c) 2004 </p> 
* <p> Company: hptec </p> 
* @author ihavegotyou 
* @version 1.0,2004/1/12 
*/ 

package webController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import model.GirdfileList;

/**
 * Servlet implementation class Prepare
 */
@WebServlet({"/PrepareforMainpage"})
public class Prepare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prepare() {
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
		
		GirdfileList gf=new GirdfileList();
		List list=gf.getFileList();
		List recentmodel=gf.getRecentModelFileList();
		if(list!=null&&recentmodel!=null){
			//System.out.println(list);
			request.setAttribute("filelist", list);
			request.setAttribute("recentmodel", recentmodel);
			try {
				request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			System.out.println("success");
		}
		else{
			request.setAttribute("filelist", 0);
			request.setAttribute("recentmodel", 0);
			try {
				request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			}
	}

}
