package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 实现删除数据库表记录功能的封装
 * @author huzhp
 *
 */
public class DBDelete {
	
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

	private static Connection conn;
	
	/**
	 * 默认构造方法
	 */
	public DBDelete(){
		super();
	}
	
	/**
	 * 删除用户信息 userinfo
	 * 删除用户分数信息 score
	 * @param name
	 * @throws Exception
	 */
	public DBDelete(String name) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();//注册驱动程序类
			String dburl = URL;
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement stat = conn.createStatement();
			String command = "DELETE FROM userinfo WHERE userName='"+name+"'";
			stat.executeUpdate(command);
			String command2 = "DELETE FROM score WHERE userName='"+name+"'";
			stat.executeUpdate(command2);
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

	/**
	 * @return the conn
	 */
	public static Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public static void setConn(Connection conn) {
		DBDelete.conn = conn;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return USERNAME;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return PASSWORD;
	}

	/**
	 * @return the driver
	 */
	public static String getDriver() {
		return DRIVER;
	}

	/**
	 * @return the url
	 */
	public static String getUrl() {
		return URL;
	}
	
}
