package webController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Servlet implementation class UserClientPara
 */
@WebServlet("/UserClientPara")
public class UserClientPara extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    private static int times = 0;

    private static int wait = 0;

    private static String[]IPAddress = {"223.3.47.107", "223.3.47.107", "223.3.47.107"};

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClientPara()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public void destroy()
    {

        super.destroy();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {

        this.doPost(request, response);

    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {

        String  statusString = request.getParameter("Task");//取ID
        
        String [] proStrings=statusString.split(","); 
        
        System.out.println(proStrings[0]);//proStrings[0]为用户名
        
        System.out.println(proStrings[1]);//proStrings[1]工程的ID号

        String projectName = "";
        
        int length=proStrings[1].length();

        String date = "";

        String Extname = "";

        SAXBuilder builder = new SAXBuilder();

        FileInputStream file = new FileInputStream("../Config"+proStrings[0]+"/config.xml");
        
        try
        {
        	if (length<2) {
	
                Document doc = builder.build(file);//获得文档对象

                Element root = doc.getRootElement();//获得根节点

                List<Element> list = root.getChildren();

                for (int i = 0; i < list.size(); i++)
                {

                    Element element = list.get(i);

                    String str = element.getChildText("id");

                    date = element.getChildText("Time");

                    if (proStrings[1].equals(str))
                    {

                        projectName = element.getChildText("Task");
                    }

                }

                if (projectName.equals(""))
                {

                    projectName = "No";
                }
			}else {
				
				projectName=proStrings[1];
			}


        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Socket client = new Socket(IPAddress[times/5], 9998);

        PrintWriter os = new PrintWriter(client.getOutputStream());

        os.println(proStrings[0]+","+projectName);

        os.flush();

        Extname = projectName.substring(projectName.lastIndexOf(".") + 1).toLowerCase();

        if (os != null)
        {

            os.close();

        }

        if (client != null)
        {

            client.close();
        }

        try {
        	
        	FileInputStream inFile = new FileInputStream("../Config"+proStrings[0]+"/config.xml");
        	
			String [] TaskArray=projectName.split(" ");
			
			Format format = Format.getCompactFormat();
			
			format.setEncoding("gb2312");
			
			format.setIndent("    ");
			
		    XMLOutputter XMLOut = new XMLOutputter(format);
		    
		    SAXBuilder Builder=new SAXBuilder();
			
			Document Doc = Builder.build(inFile);//获得文档对象

			Element Root = Doc.getRootElement();//获得根节点

			List<Element> list = Root.getChildren();
    
			for (String name : TaskArray) {
				
				for (int i = 0; i < list.size(); i++) {
					
					Element element=list.get(i);
					
					String tempStr = element.getChildText("Task");
					
					if (tempStr.equals(name)) {
						
						element.getChild("Running").setText("Start");
					}
				}
				
			}
			
			XMLOut.output(Doc,new FileOutputStream("../Config"+proStrings[0]+"/config.xml"));
			
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            finally
            {

                response.sendRedirect("http://223.3.43.185:8080/ist/inc/user_task.jsp?user_name="+proStrings[0]);

            }

        }


    public void init() throws ServletException
    {
        // Put your code here
    }

}
