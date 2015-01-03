package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 实现查询功能的封装
 * @author huzhp
 *
 */
public class DBQuery {
	
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
	private String password=null;
	private int count = 0; 
	private String score = null;
	/**
	 * 默认构造方法
	 */
	public DBQuery() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * 实现查询密码
	 * getPassword返回密码
	 * @param name
	 * @throws Exception
	 */
	public DBQuery(String name) throws Exception
	{
		try{
			Class.forName(DRIVER).newInstance();//注册驱动程序类
			String dburl = URL;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stat = conn.createStatement();
			
//			String command2 = "SELECT COUNT(userName) FROM userinfo WHERE userName="+"'"+name+"'";
//			System.out.println(command2);
//			ResultSet rs2 = stat.executeQuery(command2);
//			this.count = rs2.getInt(1);
//			System.out.println(this.count);
			
			String command = "SELECT passWord FROM userinfo WHERE userName="+"'"+name+"'";
			System.out.println(command);
			ResultSet rs = stat.executeQuery(command);	
			while(rs.next())
			{
				this.password = rs.getString("passWord");							
			}
			
//			while(rs.next())
//			{
//				String id = rs.getString("id");
//				String username = rs.getString("userName");
//				String password = rs.getString("passWord");
//				String others = rs.getString("others");
//				System.out.println(id+", "+ username+", "+password+", "+others);
//			}
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
	 * 实现查询分数功能
	 * @param name
	 * @param score
	 */
	public DBQuery(String name,String score){

		try{
			Class.forName(DRIVER).newInstance();//注册驱动程序类
			String dburl = URL;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stat = conn.createStatement();			
			
			String command = "SELECT score FROM score WHERE userName="+"'"+name+"'";
			System.out.println(command);
			ResultSet rs = stat.executeQuery(command);	
			while(rs.next())
			{
				this.score = rs.getString("score");							
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
	

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
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
		DBQuery.conn = conn;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return USERNAME;
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
