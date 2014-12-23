package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 实现更新数据库表功能的封装
 * @author huzhp
 *
 */
public class DBUpdate {
	
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
	public DBUpdate() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * 更新分数
	 * @param name
	 * @param score
	 * @throws Exception
	 */
	public DBUpdate(String name,String score) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();//注册驱动程序类
			String dburl = URL;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stat = conn.createStatement();
			String command = "UPDATE score SET score='"+score+"' WHERE userName="+"'"+name+"'";
			stat.executeUpdate(command);
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
