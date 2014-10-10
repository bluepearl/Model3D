package model;

import java.sql.ResultSet;


public class Uploadfile {
	String owner;
	String tablename;
	String filename;
	String filepath;
	String selectSql="select * from gridfile where filename=?";
	String insertSql="insert into gridfile(owner,filename,filepath) values(?,?,?) ";
	public boolean checkInsert()
	{
		boolean flag=true;
		mySQLConnector con=new mySQLConnector();
		con.readyPreparedStatement(selectSql);
		con.setString(1,filename);
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
		con.setString(1,owner);
		con.setString(2,filename);
		con.setString(3,filepath);
		con.executeUpdate();
	}
	 public Uploadfile(String owner,String filename,String filepath){
		 this.owner=owner;
		 this.tablename="gridfile";
		 this.filename=filename;
		 this.filepath="/STLfile";
		 //this.filepath=filepath;
		 
	 }
}
