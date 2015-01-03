package action;

import java.io.File;
import java.util.Properties;

import db.DBQuery;
import util.FileUtil;

/**
 * ʵ�ֵ�½����
 * 
 * @author huzhp
 *
 */
public class LoginAction {
	/**
	 * �����û��������� �˴������ļ��洢
	 */
	private static String username = null;
	private String password = null;
	private Properties userPro = null;
	private File file = null;

	public LoginAction(String username, String password) {
		super();
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * ��֤�û���������
	 * 
	 * @return
	 */
	public boolean isLogin() {
		// TODO Auto-generated method stub
		boolean isLogin = false;
		if (checkUser()) {
			// �������ݿ�
			/*****************/
			isLogin = true;
		}
		return isLogin;
	}

	private boolean checkUser() {
		// TODO Auto-generated method stub
		boolean check = false;

		// /******************************************* �������ݿ�
		// **********************************************/
		try {
			DBQuery userquery = new DBQuery(this.username);
			String pass = userquery.getPassword();
			if (pass.equals(this.password)) {
				check = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /*******************************************�����ļ�********************************************/
		//
		// /*��֤�û���������*/
		// userPro = new Properties();
		// file = new File("User.properties");
		// FileUtil.loadPro(userPro, file);
		//
		// /**
		// *����ļ������ڣ�����
		// *����ļ����������
		// */
		// if(file.length() != 0)
		// {
		// if(userPro.containsKey(this.getUsername()))
		// {
		// if((this.password).equals(userPro.getProperty(this.getUsername())))
		// {
		// check = true;
		// }else{
		// //�������
		// check = false;
		// }
		// }else{
		// //�û���������
		// check = false;
		// }
		// }else{
		// //�û���������
		// check = false;
		// }

		return check;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
