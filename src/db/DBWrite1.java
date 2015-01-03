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
 * ʵ�ֲ��빦�ܵķ�װ
 * ʹ��Statment
 * @author huzhp
 *
 */
public class DBWrite1 {
	
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
	public DBWrite1()
	{
		super();
	}
	
	/**
	 * �����û���Ϣ
	 * ���������Ϣ,��ʼ����Ϊ100
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
			 * �����û���������
			 */
			String command = "INSERT INTO userinfo(userName,passWord) VALUES('"+name+"','"+pass+"')";
			stat.executeUpdate(command);
			System.out.println(command);
			/**
			 * �����û����ͷ���
			 */
			String command2 = "INSERT INTO score(userName,score) VALUES('"+name+"','"+"100"+"')";
			stat.executeUpdate(command2);
			System.out.println(command2);

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
		DBWrite1.conn = conn;
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
