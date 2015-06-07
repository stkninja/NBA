package dataBase.dataBaseOpe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseOpe {
	
	//�����ݿ���д������ ��� �޸� ɾ���Ȳ���
	public static void createTab_UpdateSQL(String order){
		//������������ MySQL���Ϊ
		String className = "com.mysql.jdbc.Driver";
		//MySQL�ķ�������ַ���û���������(ȷ�����ݿ������������������������)
		String url = "jdbc:mysql://localhost:3306/nba";  
		String username = "root";  
		String userpass = "ruangong417";
	
		Connection con = null;  
		Statement stmt = null;  
		try{  
			//��������
			Class.forName(className);  
			con = DriverManager.getConnection(url, username, userpass);
			
			//Statement��������ִ��sql��� 
			//sql�����Ϊ��������
			stmt = con.createStatement();
			//������ӡ�ɾ�������±��
			stmt.executeUpdate(order);
			
		}catch(Exception e){  
			System.out.println(e.toString());  
		}finally{  
			//�ر�
			try{ stmt.close();}catch(Exception e){}  
			try{ con.close();}catch(Exception e){}  
		}  	
	}
	
	//�����ݿ���������Ȳ���
	public static ResultSet querySQL(String order){
		//������������ MySQL���Ϊ
		String className = "com.mysql.jdbc.Driver";
		//MySQL�ķ�������ַ���û���������(ȷ�����ݿ������������������������)
		String url = "jdbc:mysql://localhost:3306/nba";  
		String username = "root";  
		String userpass = "ruangong417";
	
		Connection con = null;  
		Statement stmt = null;
		ResultSet rs = null;
		try{  
			//��������
			Class.forName(className);  
			con = DriverManager.getConnection(url, username, userpass);
			
			//Statement��������ִ��sql��� 
			//sql�����Ϊ��������
			stmt = con.createStatement();
			//������ӡ�ɾ�������±��
			rs = stmt.executeQuery(order);
			
		}catch(Exception e){  
			System.out.println(e.toString());  
		}
		
		return rs;
	}
	
	public static void main(String[] args) throws SQLException {
		ResultSet rs = DataBaseOpe.querySQL("SELECT * FROM t_team");
		while(rs.next())  
		{  
			for(int i = 0; i < rs.getMetaData().getColumnCount(); ++i)  
				System.out.println(rs.getString(i+1));  
			System.out.println();  
		} 	
		
		rs = DataBaseOpe.querySQL("SELECT * FROM t_player");
		rs.next();//��һ��
		System.out.println(rs.getString(1));//��һ�� �޵����У�
	}
}
