package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import frame.LoginFrame;

/**
 * 实现插入功能的封装
 * 使用PreparedStatment
 * @author huzhp
 *
 */
public class DBWrite2 {
	
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
	 * 默认的构造方法
	 */
	public DBWrite2() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * 注册用户
	 * 使用PreparedStatment对象
	 * @param name
	 * @param score
	 * @throws Exception
	 */
	public DBWrite2(String name,String pass) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();
			String dburl = URL;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sqlStr = "INSERT INTO userinfo(userName,passWord) VALUES(?, ?)";
			PreparedStatement stat = conn.prepareStatement(sqlStr);
			stat.setString(1, name);
			stat.setString(2, pass);
			System.out.print(stat.toString());
			stat.executeUpdate();
			
			String sqlStr2 = "INSERT INTO score(userName,score) VALUES(?, ?)";
			PreparedStatement stat2 = conn.prepareStatement(sqlStr2);
			stat2.setString(1, name);
			stat2.setString(2, "100");
			System.out.print(stat2.toString());
			stat2.executeUpdate();
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
		DBWrite2.conn = conn;
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
