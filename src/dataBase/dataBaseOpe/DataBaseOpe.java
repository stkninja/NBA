package dataBase.dataBaseOpe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseOpe {
	
	//对数据库进行创建表格 添加 修改 删除等操作
	public static void createTab_UpdateSQL(String order){
		//加载驱动程序 MySQL语句为
		String className = "com.mysql.jdbc.Driver";
		//MySQL的服务器地址、用户名、密码(确保数据库服务器开启！！！！！！！)
		String url = "jdbc:mysql://localhost:3306/nba";  
		String username = "root";  
		String userpass = "ruangong417";
	
		Connection con = null;  
		Statement stmt = null;  
		try{  
			//建立连接
			Class.forName(className);  
			con = DriverManager.getConnection(url, username, userpass);
			
			//Statement对象用于执行sql语句 
			//sql语句作为参数传递
			stmt = con.createStatement();
			//用于添加、删除、更新表格
			stmt.executeUpdate(order);
			
		}catch(Exception e){  
			System.out.println(e.toString());  
		}finally{  
			//关闭
			try{ stmt.close();}catch(Exception e){}  
			try{ con.close();}catch(Exception e){}  
		}  	
	}
	
	//对数据库进行搜索等操作
	public static ResultSet querySQL(String order){
		//加载驱动程序 MySQL语句为
		String className = "com.mysql.jdbc.Driver";
		//MySQL的服务器地址、用户名、密码(确保数据库服务器开启！！！！！！！)
		String url = "jdbc:mysql://localhost:3306/nba";  
		String username = "root";  
		String userpass = "ruangong417";
	
		Connection con = null;  
		Statement stmt = null;
		ResultSet rs = null;
		try{  
			//建立连接
			Class.forName(className);  
			con = DriverManager.getConnection(url, username, userpass);
			
			//Statement对象用于执行sql语句 
			//sql语句作为参数传递
			stmt = con.createStatement();
			//用于添加、删除、更新表格
			rs = stmt.executeQuery(order);
			
		}catch(Exception e){  
			System.out.println(e.toString());  
		}
		
		return rs;
	}
}
