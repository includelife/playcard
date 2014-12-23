package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������ݿ�
 * @author huzhp
 *
 */
public class DBTest {
	
	/**
	 * ���ݿ��û���
	 */
	private static final String USERNAME = "root";

	/**
	 * ���ݿ�����
	 */
	private static final String PASSWORD = "root";

	/**
	 * ������Ϣ
	 */	
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * ���ݿ��ַ
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/ddzdb";
	
	private static String id;
	private static String name;
	private static String pass;
	private static String others;

	private static Connection conn;

	public static void main(String[] args) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();//ע������������
			String dburl = URL;
			String username = USERNAME;
			String password = PASSWORD;
			conn = DriverManager.getConnection(dburl,username,password);
			Statement stat = conn.createStatement();
			String command = "SELECT * FROM userinfo";
			ResultSet rs = stat.executeQuery(command);
			while(rs.next())
			{
				id = rs.getString("id");
				name = rs.getString("userName");
				pass = rs.getString("passWord");
				others = rs.getString("others");
				System.out.println(id+", "+ name+", "+pass+", "+others);
			}
		}
		catch(ClassNotFoundException e)	//������Class.forName�׳�
		{
			System.out.println("Can not load mm.mysql driver.");
		}
		catch(InstantiationException e)	//������Class.newInstance�׳�
		{
			System.out.println("Can not load mm.mysql driver.");
		}
		catch(IllegalAccessException e)	//������Class.newInstance�׳�
		{
			System.out.println("Can not load mm.mysql driver.");
		}
		catch(SQLException e)	//������DriverManager.getConnection�׳�
		{
			System.out.println(e);
		}finally{
			if(conn!=null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
}
