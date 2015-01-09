package webController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 * Servlet implementation class FileChecked
 */
@WebServlet("/UserDel")
public class UserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor of the object.
	 */
	private static String[] IPAddress={"172.18.14.3","172.18.14.42","172.18.14.43","172.18.14.44","172.18.14.45"};
	
	
	public UserDel() {
		super();
	}

	/**
	 * Destruction of the servlet. 
	 */
	public void destroy() {
		super.destroy(); 
		
	}

	/**
	 * The doGet method of the servlet. 
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 try
	        {

	            SAXBuilder builder = new SAXBuilder();
	            
	            String string = request.getParameter("ID");
	            
	            String [] array=string.split(",");//array[0]涓虹敤鎴峰悕锛宎rray[1]涓哄伐绋�

	            FileInputStream file = new FileInputStream("../Config"+array[0]+"/config.xml");

	            PrintWriter pt = response.getWriter();

	            String ID = "";

	            boolean flages;
	            
	            int index;

	            try
	            {

	                Document doc = builder.build(file);//获得文档对象

	                Element root = doc.getRootElement();//获得根节点

	                List<Element> list = root.getChildren();

	                String name = "";

	                for (int i = 0; i < list.size(); i++)
	                {

	                    Element element = list.get(i);

	                    String str = element.getChildText("id");
	                    
	                    index=Integer.parseInt(str);

	                    if (array[1].equals(str))
	                    {

	                        name = element.getChildText("Task");

	                        element.getParentElement().removeContent(element);

	                    }

	                    XMLOutputter out = new XMLOutputter();

	                    out.output(doc, new FileOutputStream("../Config"+array[0]+"/config.xml"));

	                /*	Socket socket=new Socket(IPAddress[index/10],10000);

	                	PrintWriter printWriter=new PrintWriter(socket.getOutputStream());

	                	printWriter.println(name);

	                	printWriter.flush();*/
	                }
	                response.sendRedirect("http://223.3.43.185:8080/ist/inc/user_task.jsp?user_name="+array[0]);

	            }
	            catch (JDOMException e)
	            {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        catch (FileNotFoundException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();

	        }
	        catch (IOException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	       
	    }


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
