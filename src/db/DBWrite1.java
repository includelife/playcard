package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 实现插入功能的封装
 * 使用Statment
 * @author huzhp
 *
 */
public class DBWrite1 {
	
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
	public DBWrite1()
	{
		super();
	}
	
	/**
	 * 插入用户信息
	 * 插入分数信息,初始分数为100
	 * @param name
	 * @param pass
	 * @throws Exception
	 */
	public DBWrite1(String name,String pass) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();
			String dburl = URL;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stat = conn.createStatement();
			/**
			 * 插入用户名和密码
			 */
			String command = "INSERT INTO userinfo(userName,passWord) VALUES('"+name+"','"+pass+"')";
			stat.executeUpdate(command);
			System.out.println(command);
			/**
			 * 插入用户名和分数
			 */
			String command2 = "INSERT INTO score(userName,score) VALUES('"+name+"','"+"100"+"')";
			stat.executeUpdate(command2);
			System.out.println(command2);

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
