package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.tracing.dtrace.ArgsAttributes;



public class mySQLConnector {
		
		/*�����Ƕ���һЩ�ֶ�*/
		private String url = "jdbc:mysql://localhost:3306/model3d?user=root&password=blue";	//���ݿ�URL
		private static String className = "com.mysql.jdbc.Driver";	//���ݿ�������·��
		private Connection conn = null;	//����һ��Connection����
		private Statement stmt = null;	//����һ��Statement����
		private PreparedStatement pstmt = null;	//����һ��PreparedStatement����
		
		/*��ʼ��һЩ�������������ݿ�����������������*/
		public void init(){
			try {
				//�������ݿ�����
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("driver load success");
				//�������ӣ����ӵ�������urlָ�������ݿ�URL����ָ����¼���ݿ���û���������
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/model3d","root","blue");
				System.out.println("connect success");
				//�ڳ�ʼ�������������Ȼ�ȡStatement����
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��ʼ������init()���ִ��󣬼������ݿ�����ʧ�� ���� ��������ʧ�ܣ���");
			}
		}
		
		/*���췽��*/
		public mySQLConnector(){
			init();
		}
/*		public static void main(String args[])
		{
			mySQLConnector con=new mySQLConnector();
			ResultSet rs=con.executeQuery("select * from USER_ACCOUNT;");
			try{
			if(rs.next())
				System.out.println("success");}
			catch(Exception e){e.printStackTrace();}
		}
*/
		/*********************************���·��������Statement��****************************************/
		
		/*��ѯ���ݿ⣬���ؽ����ResultSet*/
		public ResultSet executeQuery(String sql){
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("executeQuery(String sql)����������");
			}
			return rs;
		}
		
		/*�����ݿ��е����ݽ������ӡ��޸Ļ�ɾ����������Ӱ�������*/
		public int executeUpdate(String sql){
			int iCount = 0;
			try {
				iCount = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("executeUpdate(String sql)����������");
			}
			return iCount;
		}
		
		/*********************************���·��������PreparedStatement��****************************/
		
		/*����SQL����pstmt����ֵ*/
		public void readyPreparedStatement(String sql){
			try {
				pstmt =  conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("readyPreparedStatement()����������");
			}
		}
		
		/*������һЩ PreparedStatement ���õķ���*/
		public void setString(int index, String value){
			try {
				pstmt.setString(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setString()������");
			}
		}
		public void setInt(int index, int value){
			try {
				pstmt.setInt(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setInt()������");
			}
		}
		public void setBoolean(int index, boolean value){
			try {
				pstmt.setBoolean(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setBoolean()������");
			}
		}
		public void setDate(int index, Date value){
			try {
				pstmt.setDate(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setDate()������");
			}
		}
		public void setLong(int index, long value){
			try {
				pstmt.setLong(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setLong()������");
			}
		}
		public void setFloat(int index, float value){
			try {
				pstmt.setFloat(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setFloat()������");
			}
		}
		public void setBytes(int index, byte[] value){
			try {
				pstmt.setBytes(index, value);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("PreparedStatement�����÷���setBytes()������");
			}
		}
		
		/*��ѯ���ݿ⣬���ؽ����ResultSet*/
		public ResultSet executeQuery(){
			if (pstmt != null) {
				try {
					return pstmt.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("executeQuery()����������");
					return null;
				}
			} else{
				System.out.println("PreparedStatement δ����ֵ����");
				return null;
			}
		}
		
		/*�����ݿ��е����ݽ������ӡ��޸Ļ�ɾ����������Ӱ�������*/
		public int executeUpdate(){
			int iCount = 0;
			if (pstmt != null){
				try {
					iCount = pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("executeUpdate()����������");
				}
			} else{
				System.out.println("PreparedStatement δ����ֵ����");
			}
			return iCount;
		}
		
		/*********************************************************************************************************/
		
		/*�ر���������*/
		public void close(){
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر��������ӵķ���close()�����ˣ���");
			}
		}

}
