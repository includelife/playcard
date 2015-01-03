package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ʵ�ָ������ݿ���ܵķ�װ
 * @author huzhp
 *
 */
public class DBUpdate {
	
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
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public DBUpdate() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * ���·���
	 * @param name
	 * @param score
	 * @throws Exception
	 */
	public DBUpdate(String name,String score) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();//ע������������
			String dburl = URL;
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stat = conn.createStatement();
			String command = "UPDATE score SET score='"+score+"' WHERE userName="+"'"+name+"'";
			stat.executeUpdate(command);
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
	 * @return the conn
	 */
	public static Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public static void setConn(Connection conn) {
		DBUpdate.conn = conn;
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
