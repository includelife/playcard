package action;

import java.io.File;
import java.util.Properties;

import db.DBQuery;
import util.FileUtil;

/**
 * 实现登陆功能
 * 
 * @author huzhp
 *
 */
public class LoginAction {
	/**
	 * 声明用户名，密码 此处采用文件存储
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
	 * 验证用户名和密码
	 * 
	 * @return
	 */
	public boolean isLogin() {
		// TODO Auto-generated method stub
		boolean isLogin = false;
		if (checkUser()) {
			// 链接数据库
			/*****************/
			isLogin = true;
		}
		return isLogin;
	}

	private boolean checkUser() {
		// TODO Auto-generated method stub
		boolean check = false;

		// /******************************************* 采用数据库
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

		// /*******************************************采用文件********************************************/
		//
		// /*验证用户名和密码*/
		// userPro = new Properties();
		// file = new File("User.properties");
		// FileUtil.loadPro(userPro, file);
		//
		// /**
		// *如果文件不存在，创建
		// *如果文件存在则查找
		// */
		// if(file.length() != 0)
		// {
		// if(userPro.containsKey(this.getUsername()))
		// {
		// if((this.password).equals(userPro.getProperty(this.getUsername())))
		// {
		// check = true;
		// }else{
		// //密码错误
		// check = false;
		// }
		// }else{
		// //用户名不存在
		// check = false;
		// }
		// }else{
		// //用户名不存在
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
