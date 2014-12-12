package webController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserClient;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Servlet implementation class UserUpload
 */
@WebServlet("/UserUpload")
public class UserUpload extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private static int times = 0;

    //上传文件的保存路径
    protected String configPath = "attached/";

    protected String dirTemp = "attached/temp/";

    protected String dirName = "file";
    
    private static String[] IPAddress={"172.18.14.45","172.18.14.41.46","172.18.1.47"};
    
    private String  FekoPre="";
    
    private static List<String> nameList=new ArrayList<String>();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
       //2.28日添加的用来标记使用的用户
        String username=request.getParameter("user_name");

        PrintWriter out = response.getWriter();
        
        int index=0;
        
        for (Iterator i=nameList.iterator();i.hasNext();) {
			
        	if (i.next().equals(username)) {
				
        		index++;
			}
		}
        if (index==0) {
        	
        	nameList.add(username);
        	times=0;
			
		}
        /**
         * 3.8查看用户列表
         */
        File fileNew = new File("../UserName.log");
        FileWriter osFile = new FileWriter(fileNew);
        for(Iterator iterator=nameList.iterator();iterator.hasNext();)
        {
        	osFile.write(iterator.next()+"\n");
        	osFile.flush();
        }
        osFile.close();

        //文件保存目录路径
        String savePath = this.getServletContext().getRealPath("/") + configPath;

        //临时文件目录
        String tempPath = this.getServletContext().getRealPath("/") + dirTemp;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        String ymd = sdf.format(new Date());

        savePath += "/" + ymd + "/";
        //创建文件夹
        File dirFile = new File(savePath);

        SimpleDateFormat ymdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式

        String newFileName = "";

        String date = ymdate.format(new Date());

        String project = "";

        if (!dirFile.exists())
        {
            dirFile.mkdirs();
        }

        tempPath += "/" + ymd + "/";
        //创建临时文件夹
        File dirTempFile = new File(tempPath);

        if (!dirTempFile.exists())
        {
            dirTempFile.mkdirs();
        }

        DiskFileItemFactory  factory = new DiskFileItemFactory();
        factory.setSizeThreshold(20 * 1024 * 1024); //设定使用内存超过5M时，将产生临时文件并存储于临时目录中。
        factory.setRepository(new File(tempPath)); //设定存储临时文件的目录。
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        RequestContext requestContext = new ServletRequestContext(request);
        upload.setHeaderEncoding("UTF-8");

        String fileExt = "";
        
        String FileName="";
        
  
        try
        {
            List items = upload.parseRequest(requestContext);
            Iterator itr = items.iterator();
            String name = "";
            String qq = "";
            while (itr.hasNext())
            {
                FileItem item = (FileItem) itr.next();
                String fileName = item.getName();//上传的文件名
                long fileSize = item.getSize();
                if (!item.isFormField())
                {
                    fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    FileName =fileName.substring(0,fileName.lastIndexOf("."));
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    //4.15
                  //  newFileName = df.format(new Date()) + /*"_" + new Random().nextInt(1000) + */"." + fileExt;
                    newFileName =FileName +/*"/"+df.format(new Date())+"_"+new Random().nextInt(1000)+*/"." + fileExt;
                    /**
                     * 12.24  FEKO
                     */
                    if (fileExt.equals("pre"))
                    {
                        FekoPre = newFileName;
                    }
                    else if(fileExt.equals("cfm"))
                    {
                        String tmp = FekoPre.substring(0, FekoPre.indexOf("."));
                        newFileName = tmp + "." + fileExt;
                    }

                    try
                    {
                        File uploadedFile = new File(savePath, newFileName);
                        /*
                         * 第一种方法
                         *
                         * 好处： 一目了然..简单啊...
                         * 弊端： 这种方法会导致上传的文件大小比原来的文件要大
                         *
                         * 推荐使用第二种
                         */
                        //item.write(uploadedFile);
                        //--------------------------------------------------------------------
                        //第二种方法
                        OutputStream os = new FileOutputStream(uploadedFile);
                        InputStream is = item.getInputStream();
                        byte buf[] = new byte[1024];//可以修改 1024 以提高读取速度
                        int length = 0;

                        while( (length = is.read(buf)) > 0 )
                        {
                            os.write(buf, 0, length);
                        }
                        //关闭流
                        os.flush();
                        os.close();
                        is.close();

                        System.out.println(newFileName);
                        System.out.println(savePath);
                        System.out.println("上传成功！路径：" + savePath + "/" + newFileName);
                        out.print("1");

                        String direction = savePath + "/" + newFileName;
                        int count = times;

                        /**
                         * 12.24  FEKO
                         */

                        if (fileExt.equals("cst"))
                        {
                        	UserClient clc = new UserClient("CST", direction, newFileName, count);
                        }
                        else if(fileExt.equals("pre") || fileExt.equals("cfm"))
                        {
                        	UserClient clf = new UserClient("FEKO", direction, newFileName, count);
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    String filedName = item.getFieldName();
                    if (filedName.equals("userName"))
                    {
                        name = item.getString();
                    }
                    else
                    {
                        qq = item.getString();
                    }

                    System.out.println("FieldName：" + filedName);
                    System.out.println("String：" + item.getString());
                    System.out.println("===============");
                }
            }
        }
        catch (FileUploadException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.flush();
        out.close();
        //3.7主要修改的是配置文件"../Config"+username+"/config.xml"
        //3.7为了使每个用户有自己的任务列表，需要每个用户都拥有自己的配置文件
        File file = new File("../Config"+username);
        if (!file.exists())
        {
            file.mkdir();
        }

        File file2 = new File("../Config"+username+"/config.xml");
        if (!file2.exists()) {
			
        	file2.createNewFile();
		}
        /*
         * 为了了解times的取值情况我们写一个log文件
         */
        File logFile=new File("../Config"+username+"/"+username+".log");
        FileWriter logWriter=new FileWriter(logFile);
        logWriter.write("USERNAME锛�"+username+"\n"+"times:"+times+"\n"+"ERROR INFORMATION:"+"No");
        logWriter.flush();
        logWriter.close();
        Format format = Format.getCompactFormat();
        format.setEncoding("gb2312");
        format.setIndent("    ");
        XMLOutputter XMLOut = new XMLOutputter(format);

        if (fileExt.equals("cst") || fileExt.equals("pre"))
        {
            try
            {
                if (times == 0)
                {
                    Element root, task, id, Task, Status, Solver, Priority, Time, Node,Running;
                    root = new Element("TaskList");
                    task = new Element("task");
                    id = new Element("id");
                    Task = new Element("Task");
                    //4.12
                    Running=new Element("Running");
                    Status = new Element("Status");
                    Solver = new Element("Solver");
                    Priority = new Element("Priority");
                    Time = new Element("Time");
                    Node = new Element("Node");
                    Document doc = new Document(root);

                    id.setText(String.valueOf(times));

                    if (fileExt.equals("cst") || fileExt.equals("pre"))
                    {
                        Task.setText(newFileName);
                    }
                    
                    Running.setText("No Start");

                    Status.setText("OK");

                    if (fileExt.equals("cst"))
                    {
                        Solver.setText("CST");
                    }
                    else if (fileExt.equals("pre"))
                    {
                        Solver.setText("FEKO");
                    }

                    Priority.setText("1");
                    Time.setText(date);
                    Node.setText("172.18.14.45");
                    task.addContent(id);
                    task.addContent(Task);
                    task.addContent(Running);
                    task.addContent(Status);
                    task.addContent(Solver);
                    task.addContent(Priority);
                    task.addContent(Time);
                    task.addContent(Node);
                    root.addContent(task);
                    FileOutputStream fileOutputStream = new FileOutputStream("../Config"+username+"/config.xml");
                    XMLOut.output(doc, fileOutputStream);
                    times++;
                }
                else
                {
                    SAXBuilder builder = new SAXBuilder();
                    Document doc = builder.build("../Config"+username+"/config.xml");//获得文档对象
                    Element root = doc.getRootElement();//获得根节点
                    Element task, id, Task, Status, Solver, Priority, Time, Node,Running;
                    task = new Element("task");
                    id = new Element("id");
                    id.setText(String.valueOf(times));
                    Task = new Element("Task");
                    if (fileExt.equals("pre") || fileExt.equals("cst"))
                    {
                        Task.setText(newFileName);
                    }
                    //4.12
                    Running=new Element("Running");
                    Running.setText("No Start");
                    Status = new Element("Status");
                    Status.setText("OK");
                    Solver = new Element("Solver");

                    if (fileExt.equals("cst"))
                    {
                        Solver.setText("CST");
                    }
                    else if (fileExt.equals("pre"))
                    {
                        Solver.setText("FEKO");
                    }

                    Priority = new Element("Priority");
                    Priority.setText("1");
                    Time = new Element("Time");
                    Time.setText(date);
                    Node = new Element("Node");
                    Node.setText("172.18.14.45");
                    task.addContent(id);
                    task.addContent(Task);
                    task.addContent(Running);
                    task.addContent(Status);
                    task.addContent(Solver);
                    task.addContent(Priority);
                    task.addContent(Time);
                    task.addContent(Node);
                    root.addContent(task);
                    doc.setRootElement(root);
                    XMLOut.output(doc, new FileOutputStream("../Config"+username+"/config.xml"));
                    times++;
                }
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
    }
}
