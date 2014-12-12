package model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class UserClient
{

    private String projectString = "";
    private static String[]IPAddress = {"223.3.47.107", "223.3.47.107", "223.3.47.107"};

    public UserClient(String solution , String path, String name, int times)
    {
        if (solution.equals("CST"))
        {

            try
            {

                writeTempCSTFile(path, name);

                int length = 0;

                byte[] sendBytes = null;

                DataOutputStream dos = null;

                FileInputStream fis = null;

                Socket ss = new Socket("223.3.47.107", 9999);

                dos = new DataOutputStream(ss.getOutputStream());

                File file = new File("../temp.cst");

                if (file == null)
                {

                    System.out.println("文件名错误");
                }

                fis = new FileInputStream(file);

                sendBytes = new byte[1024];

                while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0)
                {

                    dos.write(sendBytes, 0, length);

                    dos.flush();
                }

                if (dos != null)
                {

                    dos.close();
                }

                if (fis != null)
                {

                    fis.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        else if (solution.equals("FEKO"))
        {

            try
            {

                Socket socket = new Socket("223.3.47.107", 9999);

                int length = 0;

                byte[] sendBytes = new byte[1024];

                DataOutputStream dos = null;

                FileInputStream fis = null;

                dos = new DataOutputStream(socket.getOutputStream());

                if (name.substring(name.indexOf(".") + 1).equals("pre"))
                {

                    WriteFEKOFilePre(path, name);

                    File filepre = new File("../tempfeko.pre");

                    fis = new FileInputStream(filepre);

                    while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0)
                    {

                        dos.write(sendBytes, 0, length);

                        dos.flush();

                    }

                }
                else if(name.substring(name.indexOf(".") + 1).equals("cfm"))
                {

                    WriteFEKOFileCfm(path, name);

                    File filecfm = new File("../tempfeko.cfm");

                    fis = new FileInputStream(filecfm);

                    while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0)
                    {

                        dos.write(sendBytes, 0, length);

                        dos.flush();

                    }
                }

                if (fis != null)
                {

                    fis.close();
                }
                if (dos != null)
                {

                    dos.close();
                }

            }
            catch (UnknownHostException e)
            {

                e.printStackTrace();

            }
            catch (IOException e)
            {

                e.printStackTrace();
            }
        }
    }

    public String getstring()
    {

        System.out.println("功成名" + projectString);

        return projectString;
    }

    public void writeTempCSTFile(String path, String name)
    {
        int lenght;

        File in = new File(path);

        File temp = new File("../temp.cst");

        String project = name;
        
        int length=0;
        
        length=project.length();
        
        String ProjectName="";
        
        if (length<10&&length>0) {
			
        	ProjectName="0"+length+project;
		}else {
			
			ProjectName=length+project;
		}

        byte [] tempByte = ProjectName.getBytes();
        //byte [] tempByte = project.getBytes();

        System.out.println("字节长度为" + tempByte.length);

        FileInputStream inputStream = null;

        FileOutputStream outputStream = null;

        byte b[] = null;

        try
        {

            b = new byte[1024];

            inputStream = new FileInputStream(in);

            outputStream = new FileOutputStream(temp);

            outputStream.write(tempByte, 0, tempByte.length);

            while ((lenght = inputStream.read(b, 0, 1024)) > 0)
            {

                outputStream.write(b, 0, lenght);

                outputStream.flush();
            }

            outputStream.close();

            inputStream.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void WriteFEKOFilePre(String path, String name)
    {
        int lenght;

        File in = new File(path);

        File temp = new File("../tempfeko.pre");

        String project = name;
        
        int length=0;
        
        length=project.length();
        
        String ProjectName="";
        
        if (length<10&&length>0) {
			
        	ProjectName="0"+length+project;
        	
		}else {
			
			ProjectName=length+project;
		}

        byte [] tempByte = ProjectName.getBytes();

       // byte [] tempByte = project.getBytes();

        System.out.println("FEKO pre 字节长度为" + tempByte.length);

        FileInputStream inputStream = null;

        FileOutputStream outputStream = null;

        byte b[] = null;

        try
        {

            b = new byte[1024];

            inputStream = new FileInputStream(in);

            outputStream = new FileOutputStream(temp);

            outputStream.write(tempByte, 0, tempByte.length);

            while ((lenght = inputStream.read(b, 0, 1024)) > 0)
            {

                outputStream.write(b, 0, lenght);

                outputStream.flush();
            }

            outputStream.close();

            inputStream.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void WriteFEKOFileCfm(String path, String name)
    {
        int lenght;

        File in = new File(path);

        File temp = new File("../tempfeko.cfm");

        String project = name;

        int length=0;
        
        length=project.length();
        
        String ProjectName="";
        
        if (length<10&&length>0) {
			
        	ProjectName="0"+length+project;
		}else {
			
			ProjectName=length+project;
		}

        byte [] tempByte = ProjectName.getBytes();
       // byte [] tempByte = project.getBytes();

        System.out.println("FEKO cfm字节长度为" + tempByte.length);

        FileInputStream inputStream = null;

        FileOutputStream outputStream = null;

        byte b[] = null;

        try
        {

            b = new byte[1024];

            inputStream = new FileInputStream(in);

            outputStream = new FileOutputStream(temp);

            outputStream.write(tempByte, 0, tempByte.length);

            while ((lenght = inputStream.read(b, 0, 1024)) > 0)
            {

                outputStream.write(b, 0, lenght);

                outputStream.flush();
            }

            outputStream.close();

            inputStream.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}