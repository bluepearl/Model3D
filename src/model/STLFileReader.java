package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class STLFileReader {

    /**
     * ���ֽ�Ϊ��λ��ȡ�ļ��������ڶ��������ļ�����ͼƬ��������Ӱ����ļ���
     */
    public static String readFileByBytes(String fileName) {
        File file = new File(fileName);
        String filecontent="";
        InputStream in = null;
        try {
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
            // һ�ζ�����ֽ�
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            STLFileReader.showAvailableBytes(in);
            // �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
            while ((byteread = in.read(tempbytes)) != -1) {
                //System.out.write(tempbytes, 0, byteread);
            	String str=new String(tempbytes);
            	filecontent+=str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        //System.out.print(filecontent);
		return filecontent;
    }

    /**
     * ��ʾ�������л�ʣ���ֽ���
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("��ǰ�ֽ��������е��ֽ���Ϊ:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        String fileName = "D:/testfilereader/pr2_head_pan.stl";
//        STLFileReader.readFileByBytes(fileName);
//    }
}
