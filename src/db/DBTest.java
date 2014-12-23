package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试数据库
 * @author huzhp
 *
 */
public class DBTest {
	
	/**
	 * 数据库用户名
	 */
	private static final String USERNAME = "root";

	/**
	 * 数据库密码
	 */
	private static final String PASSWORD = "root";

	/**
	 * 驱动信息
	 */	
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * 数据库地址
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
			Class.forName(DRIVER).newInstance();//注册驱动程序类
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
		catch(ClassNotFoundException e)	//可能由Class.forName抛出
		{
			System.out.println("Can not load mm.mysql driver.");
		}
		catch(InstantiationException e)	//可能由Class.newInstance抛出
		{
			System.out.println("Can not load mm.mysql driver.");
		}
		catch(IllegalAccessException e)	//可能由Class.newInstance抛出
		{
			System.out.println("Can not load mm.mysql driver.");
		}
		catch(SQLException e)	//可能由DriverManager.getConnection抛出
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
