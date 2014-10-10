package webController;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4j_init
 */
@WebServlet(
		urlPatterns = { "/Log4j_init" }, 
		initParams = { 
				@WebInitParam(name = "log4j_init_file", value = "log4j.properties")
		},
		loadOnStartup=1
		)
public class Log4j_init extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log4j_init() {
        super();
        // TODO Auto-generated constructor stub

    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(){
		// TODO Auto-generated method stub
		String prefix ="D:/LOG/";
		String file = getInitParameter("log4j_init_file");
		// 如果没有给出相应的配置文件，则不进行初始化
		if(file != null)
		{
			System.out.println(prefix+file);
			PropertyConfigurator.configure(prefix+file); //（1）
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
