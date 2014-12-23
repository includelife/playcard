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
 * ʵ�ֲ��빦�ܵķ�װ
 * ʹ��PreparedStatment
 * @author huzhp
 *
 */
public class DBWrite2 {
	
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
	 * Ĭ�ϵĹ��췽��
	 */
	public DBWrite2() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/**
	 * ע���û�
	 * ʹ��PreparedStatment����
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
}
