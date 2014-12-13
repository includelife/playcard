package action;

import java.io.File;
import java.util.Properties;
import util.FileUtil;

public class LoginAction {
	private static String username = null;
	private String password = null;
	private Properties userPro = null;
	private File file = null;
	public LoginAction(String username,String password){
		super();
		this.setUsername(username);
		this.password = password;		
	}
	
	public boolean isLogin() {
		// TODO Auto-generated method stub
		boolean isLogin = false;
		if(checkUser()){
			//�������ݿ�
			/*****************/
			isLogin = true;
		}
		return isLogin;
	}

	private boolean checkUser() {
		// TODO Auto-generated method stub
		boolean check = false;
		
		/*��֤�û���������*/
		userPro = new Properties();
		file = new File("User.properties");		
		FileUtil.loadPro(userPro, file);
		
		
		
		if(file.length() != 0)
		{	
			if(userPro.containsKey(this.getUsername()))
			{
				if((this.password).equals(userPro.getProperty(this.getUsername())))
				{
					check = true;
				}else{   
					//�������
					check = false;
				}
			}else{
				//�û���������
				check = false;
			}
		}else{
			//�û���������
			check = false;
		}
		return check;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
}
