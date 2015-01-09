package model;

import java.sql.ResultSet;

public class UploadTask {
	String time;
	String tablename;
	String taskname;
	String storepath;
	String solver;
	String selectSql="SELECT computetaskId,time,status,solver FROM model3d.computetask where taskname=?;";
	String insertSql="insert into computetask(taskname,storepath,time,solver) values(?,?,?,?) ";
	public boolean checkInsert()
	{
		boolean flag=true;
		mySQLConnector con=new mySQLConnector();
		con.readyPreparedStatement(selectSql);
		con.setString(1,taskname);
		ResultSet rs=con.executeQuery();
		try
		{		
			if(rs.next())
				flag= false;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return flag;
	}
	public void recordInsert()
	{
		mySQLConnector con=new mySQLConnector();
		con.readyPreparedStatement(insertSql);
		con.setString(1,taskname);
		con.setString(2,storepath);
		con.setString(3,time);
		con.setString(4,solver);
		con.executeUpdate();
	}
	
	public String NewTaskParams()
	{
		String param="";
		mySQLConnector con=new mySQLConnector();
		con.readyPreparedStatement(selectSql);
		con.setString(1,taskname);
		ResultSet rs=con.executeQuery();
		try
		{		
			if(rs.next())
				param=rs.getString(1)+','+rs.getString(2)+','+taskname+','+rs.getString(3)+','+rs.getString(4);
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		
		return param;
	}
	 public UploadTask(String time,String taskname,String storepath,String solver){
		 this.time=time;
		 this.taskname=taskname;//未确定参数的获取方式
		 this.storepath="D:/STLfile";//未确定参数的获取方式
		 this.solver=solver;
		 
	 }

//	public static void main(String[] args) {
//		UploadTask ut = new UploadTask("2014/12/23", "test1", "");
//		System.out.println(ut.NewTaskParams());
//
//	}
}
