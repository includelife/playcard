package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ʵ��ɾ�����ݿ���¼���ܵķ�װ
 * @author huzhp
 *
 */
public class DBDelete {
	
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
	public DBDelete(){
		super();
	}
	
	/**
	 * ɾ���û���Ϣ userinfo
	 * ɾ���û�������Ϣ score
	 * @param name
	 * @throws Exception
	 */
	public DBDelete(String name) throws Exception
	{
		try
		{
			Class.forName(DRIVER).newInstance();//ע������������
			String dburl = URL;
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement stat = conn.createStatement();
			String command = "DELETE FROM userinfo WHERE userName='"+name+"'";
			stat.executeUpdate(command);
			String command2 = "DELETE FROM score WHERE userName='"+name+"'";
			stat.executeUpdate(command2);
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
