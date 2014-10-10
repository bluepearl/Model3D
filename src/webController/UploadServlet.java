package webController;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import model.Uploadfile;
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
		System.out.println("文件上传结束");

	}
    /*
     * 上传处理方法
     */
    public void processUpload(RequestContext request,
            HttpServletResponse response, String savePath) throws IOException {
                                                                                             
        String owner="admin";
        String filepath;
        String name = "";
        String filename = "";
        
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 缓存大小为512KB
        factory.setSizeThreshold(524288);
        // 临时文件夹
        factory.setRepository(new File(savePath + "/temp"));
        // 初始化上传控件
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 文件大小最大3MB
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
                    
                    if(uf.checkInsert()){
                    	System.out.println(name+"文件上传开始……");
                    	try {
                            item.write(file);
                            String imgstr = this.getServletContext()
                                    .getContextPath();
//                            imgstr = imgstr + "/upload/" + filename;
                            imgstr = imgstr + "/upload/" + name;
                            response.getWriter().write("http://localhost:8080"+imgstr);
                            uf.recordInsert();
                            logger.debug("admin upload file:"+name+" success!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                    	logger.debug("admin upload file:"+name+" failed!");
            			response.setContentType("text/plain;charset=utf-8");
            			response.getWriter().append("error");
                    	System.out.println(name+"文件名称冲突,请重新命名！");
                    }
                }
            }
        }
    }
    /*
     * 初始化文件存储路径
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
     * 生成文件名
     */
    private String generateFileName(String name) {
        long currentTime = System.currentTimeMillis();
        int i = (int) (Math.random() * 1000D + 1.0D);
        long result = currentTime + i;
        String filename = String.valueOf(result) + getFileExt(name);
        return filename;
    }
    /*
     * 获取文件格式
     */
    private String getFileExt(String name) {
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            return name.substring(pos);
        } else {
            return name;
        }
    }

}
