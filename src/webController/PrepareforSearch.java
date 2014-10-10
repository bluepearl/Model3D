package webController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GirdfileList;

/**
 * Servlet implementation class PrepareforSearch
 */
@WebServlet("/PrepareforSearch")
public class PrepareforSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareforSearch() {
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
		String filename=request.getParameter("filename");
		GirdfileList gf=new GirdfileList();
		ArrayList<String[]> list=gf.getSearchFileList(filename);
		if(list!=null){
			//System.out.println(list.get(1));
			request.setAttribute("list", list);
			try {
				request.getRequestDispatcher("/SearchFile.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			System.out.println("success");
		}
	}

}
