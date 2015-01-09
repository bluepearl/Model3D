package webController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.ProcessingInstruction;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import sun.security.timestamp.TSRequest;
import model.Uploadfile;
import model.UserClient;
import webController.*;
/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getRootLogger();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestContext requestContext = new ServletRequestContext(request);
		String savePath = generateDir();
		processUpload(requestContext, response, savePath);
		System.out.println("�ļ��ϴ�����");

	}
    /*
     * �ϴ�������
     */
    public void processUpload(RequestContext request,
            HttpServletResponse response, String savePath) throws IOException {
                                                                                             
        String owner="admin";
        String filepath;
        String name = "";
        String filename = "";
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����СΪ512KB
        factory.setSizeThreshold(524288);
        // ��ʱ�ļ���
        factory.setRepository(new File(savePath + "/temp"));
        // ��ʼ���ϴ��ؼ�
        ServletFileUpload upload = new ServletFileUpload(factory);
        // �ļ���С���3MB
        upload.setFileSizeMax(3145728);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator<FileItem> it = fileList.iterator();
        
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                if (name != null && !name.trim().equals("")) {
                	System.out.println(name);
                    filename = generateFileName(name);
//                    File file = new File(savePath + "/" + filename);
//                    filepath=savePath + "/" + filename;
//                    Uploadfile uf=new Uploadfile(owner,filename,filepath);
                    
                    File file = new File(savePath + "/" + name);
                    filepath=savePath + "/" + name;
                    Uploadfile uf=new Uploadfile(owner,name,filepath);
                    
                    
                    //Start 12.19
                    String strEx = name.substring(name.lastIndexOf(".")+1).toLowerCase();
                    String strProjectName = name.substring(0, name.lastIndexOf("."));
                    File fekoDir = null;
                    if (strEx.equals("pre") || strEx.equals("cfm")) 
                    {
						fekoDir = new File(savePath + "/" + strProjectName);
						if (!fekoDir.exists()) 
						{
							fekoDir.mkdir();
						}
					}
                    //End 12.19
                    
                    if(uf.checkInsert()){
                    	System.out.println(name+"�ļ��ϴ���ʼ����");
                    	try {
                    		
                           // item.write(file);
                            
                            //Start 12.19
                            if (strEx.equals("cst"))
                            {
                            	System.out.println("�µ��ϴ���ʽ");
                            	InputStream is = item.getInputStream();
                                FileOutputStream os = new FileOutputStream(file);
                                byte [] content = new byte [1024];
                                int length = 0;
                                while((length = is.read(content)) > 0)
                                {
                                	os.write(content, 0, length);
                                }
                                is.close();
                                os.close();
							}
                            else if (strEx.equals("pre") || strEx.equals("cfm")) 
                            {
								InputStream is = item.getInputStream();
								FileOutputStream os = new FileOutputStream(savePath + "/" + strProjectName + "/" + name);
								byte [] content = new byte [1024];
                                int length = 0;
                            	while((length = is.read(content)) > 0)
                            	{
                                	os.write(content, 0, length);
                                }
                                is.close();
                                os.close();
							}
                            else
                            {
                            	System.out.println("�µ��ϴ���ʽ");
                            	InputStream is = item.getInputStream();
                                FileOutputStream os = new FileOutputStream(file);
                                byte [] content = new byte [1024];
                                int length = 0;
                                while((length = is.read(content)) > 0)
                                {
                                	os.write(content, 0, length);
                                }
                                is.close();
                                os.close();
							}
                            //End 12.19
                         
                            String imgstr = this.getServletContext()
                                    .getContextPath();
//                            imgstr = imgstr + "/upload/" + filename;
                            imgstr = imgstr + "/upload/" + name;
                            //response.getWriter().write("http://localhost:8080"+imgstr); Gu
                            uf.recordInsert();
                            logger.debug("admin upload file:"+name+" success!");
                            
                            //12.19
               
                            if (strEx.equals("cst"))
                            {
								UserClient userClient = new UserClient("CST", savePath + "/" + name, name);
							}
                            else if(strEx.equals("pre")) 
                            {
								UserClient userClient = new UserClient("FEKO", savePath + "/" + strProjectName, name);
							}
                           
                            SimpleDateFormat dfs = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                            String strTime = dfs.format(new Date());
                            String strSolver = null;
                            if (strEx .equals("cst")) 
                            {
								strSolver = "CST";
							}
                            else if(strEx.equals("pre") || strEx.equals("cfm")) 
                            {
                            	strSolver = "FEKO";
							}
							
                            if (strSolver!=null&&(strSolver.equals("CST") || strSolver.equals("FEKO"))) 
                            {
                            	if (strEx.equals("cst") || strEx.equals("pre"))
                            	{
                            		//WriteXml(name, strTime, strSolver, "admin", savePath + "/" + "XML");
								}
                            	response.setContentType("text/plain;charset=utf-8");
                            	response.getWriter().append(strProjectName+','+strSolver);
                            	
							}
                            
                            //12.23 start
                            if (strEx.equals("cst")) 
                            {
								new File(savePath + "/" + name).delete();
							}
                            else
                            {
                            	new File(savePath + "/" + strProjectName + "/" + name).delete();
                            	
							}
                            //12.23end
                               
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                    	logger.debug("admin upload file:"+name+" failed!");
            			response.setContentType("text/plain;charset=utf-8");
            			response.getWriter().append("error");
                    	System.out.println(name+"�ļ����Ƴ�ͻ,������������");
                    }
                }
            }
        }
    }
    /*
     * ��ʼ���ļ��洢·��
     */
    private String generateDir() {
        String pathString = "D:/STLfile";
        String tempString = "D:/STLfile/temp";
        File dirPath = new File(pathString);
        File dirTemp = new File(tempString);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        if (!dirTemp.exists()) {
            dirTemp.mkdirs();
        }
        return pathString;
    }
    /*
     * �����ļ���
     */
    private String generateFileName(String name) {
        long currentTime = System.currentTimeMillis();
        int i = (int) (Math.random() * 1000D + 1.0D);
        long result = currentTime + i;
        String filename = String.valueOf(result) + getFileExt(name);
        return filename;
    }
    /*
     * ��ȡ�ļ���ʽ
     */
    private String getFileExt(String name) {
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            return name.substring(pos);
        } else {
            return name;
        }
    }
    /**
     * 
     * @param strName ������
     * @param strTime �ϴ�ʱ��
     * @param strSolver �����
     * @param strUsrName �û���
     * @param strPath ·����
     */
    
    private void WriteXml(String strName , String strTime, String strSolver, String strUsrName, String strPath)
    {
    	try 
    	{
			File xmlFile = new File(strPath + "/" + strUsrName + ".xml");
			Format format = Format.getCompactFormat();
			format.setEncoding("gb2312");
			format.setIndent("    ");
			XMLOutputter XMLOut = new XMLOutputter(format);
			
			if (!xmlFile.exists()) 
			{
				Element rootElement = new Element("TaskList");
				Element taskElement = new Element("Task");
				Element proNameElement = new Element("ProjectName");
				Element timeElement = new Element("Time");
				Element solverElement = new Element("Solver");
				Element usrElement = new Element("User");
				proNameElement.setText(strName);
				timeElement.setText(strTime);
				solverElement.setText(strSolver);
				usrElement.setText(strUsrName);
				Document dos = new Document(rootElement);
				taskElement.addContent(proNameElement);
				taskElement.addContent(timeElement);
				taskElement.addContent(solverElement);
				taskElement.addContent(usrElement);
				rootElement.addContent(taskElement);
				FileOutputStream fis = new FileOutputStream(xmlFile);
				XMLOut.output(dos, fis);
			}
			else 
			{
				SAXBuilder builder = new SAXBuilder();
				Document dos = builder.build(xmlFile);
				Element rootElement = dos.getRootElement();
				Element taskElement = new Element("Task");
				Element proElement = new Element("ProjectName");
				Element timeElement = new Element("Time");
				Element solverElement = new Element("Solver");
				Element userElement = new Element("User");
				proElement.setText(strName);
				timeElement.setText(strTime);
				solverElement.setText(strSolver);
				userElement.setText(strUsrName);
				taskElement.addContent(proElement);
				taskElement.addContent(timeElement);
				taskElement.addContent(solverElement);
				taskElement.addContent(userElement);
				rootElement.addContent(taskElement);
				XMLOut.output(dos, new FileOutputStream(xmlFile));
			}
			
		} catch (Exception e){
			
			e.printStackTrace();
		}
    }
}