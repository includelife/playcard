package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ��������
 * @author huzhp
 *
 */
public class DBUtil {
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
	
	private Connection connection;
	private Statement stat;
	private ResultSet rst;
	
	private String name;
	private String pass;
	private String score;	
	private String oyhers;
	
	/**
	 * �������ݿ�
	 */
	public DBUtil(){
		// TODO Auto-generated constructor stub
		try{
			Class.forName(DRIVER);
			System.out.println("���ݿ����ӳɹ���");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ�conn
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
	 * ��ѯ�û�����
	 * @param username
	 * @return
	 */
	public String queryPass(String username){
		
		return pass;
	}
	
}