package model;

import java.sql.ResultSet;

public class DoLoginCheck {
	String username;
	String password;
	String sql;
	public boolean isSuccess()
	{
		boolean flag=false;

		mySQLConnector con=new mySQLConnector();
		ResultSet rs= con.executeQuery(sql);
		try
		{		
			if(rs.next())
				flag= true;
		}catch(Exception e)
		{ 
			e.printStackTrace();
			}
		con.close();
		return flag;
	}
	public DoLoginCheck(String u,String p)
	{
		username=u;
		password=p;
		sql="select * from user_account where username='"+username+"' and password='"+password+"';";
	}
}
