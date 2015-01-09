package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.tracing.dtrace.ArgsAttributes;


public class GirdfileList {

	String checkedFileSql="select ID,filename from gridfile where verified>0;";
	String unCheckedFileSql="select ID,filename from gridfile where verified=0;";
	String searchFileSql="select ID,filename from gridfile where filename LIKE ?;";
	String RecentModelFileSql="SELECT ID,filename FROM gridfile limit 0,3; ";
	String ProjectSql="SELECT computetaskId,taskname,time,status,solver FROM computetask; ";
	ArrayList<String> arrayList1;
	ArrayList<String[]> arrayList2;
	public ArrayList<String[]> getFileList()
	{
		arrayList1 = new ArrayList<String>();
		arrayList2 = new ArrayList<String[]>();
		ArrayList<String[]> list = null;
		mySQLConnector con=new mySQLConnector();
		ResultSet rs=con.executeQuery(checkedFileSql);
		try
		{		
			convertList(rs);
			list=arrayList2;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return list;
	}
	public ArrayList<String[]> getUnCheckedFileList()
	{
		arrayList1 = new ArrayList<String>();
		arrayList2 = new ArrayList<String[]>();
		ArrayList<String[]> list = null;
		mySQLConnector con=new mySQLConnector();
		ResultSet rs=con.executeQuery(unCheckedFileSql);
		try
		{	
			convertList(rs);
			list=arrayList2;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return list;
	}
	public ArrayList<String[]> getRecentModelFileList()
	{
		arrayList1 = new ArrayList<String>();
		arrayList2 = new ArrayList<String[]>();
		ArrayList<String[]> list = null;
		mySQLConnector con=new mySQLConnector();
		ResultSet rs=con.executeQuery(RecentModelFileSql);
		try
		{	
			convertList(rs);
			list=arrayList2;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return list;
	}
	public ArrayList<String[]> getSearchFileList(String filename)
	{
		arrayList1 = new ArrayList<String>();
		arrayList2 = new ArrayList<String[]>();
		ArrayList<String[]> list = null;
		mySQLConnector con=new mySQLConnector();
		con.readyPreparedStatement(searchFileSql);
		con.setString(1,filename+'%');
		ResultSet rs=con.executeQuery();
		try
		{	
			convertList(rs);
			list=arrayList2;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return list;
	}
	
	public ArrayList<String[]> getProjectList()
	{
		arrayList1 = new ArrayList<String>();
		arrayList2 = new ArrayList<String[]>();
		ArrayList<String[]> list = null;
		mySQLConnector con=new mySQLConnector();
		ResultSet rs=con.executeQuery(ProjectSql);
		try
		{		
			convertList(rs);
			list=arrayList2;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return list;
	}
	public  void convertList(ResultSet rs) throws SQLException {

		//List list = new ArrayList();

		ResultSetMetaData md = rs.getMetaData();

		int columnCount = md.getColumnCount(); // Map rowData;

		while (rs.next()){ // rowData = new HashMap(columnCount);
			//Map rowData = new HashMap();
			String[] temp=new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				arrayList1.add(md.getColumnName(i));
				temp[i-1]=rs.getString(i);
				//rowData.put(md.getColumnName(i), rs.getObject(i));
			}

			arrayList2.add(temp);

		} 
		//return list;

	}
//	public static void main(String[] args)
//	{
//		GirdfileList gf=new GirdfileList();
//		ArrayList<String[]> list=gf.getFileList();
//		if(list!=null){
//			System.out.println(list.get(1));
//		}
//		
//	}
}
