package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ʵ�ֲ�ѯ���ܵķ�װ
 * @author huzhp
 *
 */
public class DBQuery {
	
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

	private static Connection conn;
	private String password=null;
	private int count = 0; 
	private String score = null;
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public DBQuery() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * ʵ�ֲ�ѯ����
	 * getPassword��������
	 * @param name
	 * @throws Exception
	 */
	public DBQuery(String name) throws Exception
	{
		try{
			Class.forName(DRIVER).newInstance();//ע������������
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
	
	/**
	 * ʵ�ֲ�ѯ��������
	 * @param name
	 * @param score
	 */
	public DBQuery(String name,String score){

		try{
			Class.forName(DRIVER).newInstance();//ע������������
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
