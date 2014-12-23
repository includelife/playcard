package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作集合
 * @author huzhp
 *
 */
public class DBUtil {
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
	
	private Connection connection;
	private Statement stat;
	private ResultSet rst;
	
	private String name;
	private String pass;
	private String score;	
	private String oyhers;
	
	/**
	 * 链接数据库
	 */
	public DBUtil(){
		// TODO Auto-generated constructor stub
		try{
			Class.forName(DRIVER);
			System.out.println("数据库连接成功！");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到conn
	 * @return
	 */
	public Connection getConnection(){
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 查询用户密码
	 * @param username
	 * @return
	 */
	public String queryPass(String username){
		
		return pass;
	}
	
}