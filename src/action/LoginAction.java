package action;

import java.io.File;
import java.util.Properties;
import util.FileUtil;

public class LoginAction {
	private String username = null;
	private String password = null;
	private Properties userPro = null;
	private File file = null;
	public LoginAction(String username,String password){
		super();
		this.username = username;
		this.password = password;		
	}
	
	public boolean isLogin() {
		// TODO Auto-generated method stub
		boolean isLogin = false;
		if(checkUser()){
			//链接数据库
			/*****************/
			isLogin = true;
		}
		return isLogin;
	}

	private boolean checkUser() {
		// TODO Auto-generated method stub
		boolean check = false;
		
		/*验证用户名和密码*/
		userPro = new Properties();
		file = new File("User.properties");		
		FileUtil.loadPro(userPro, file);
		
		
		
		if(file.length() != 0)
		{	
			if(userPro.containsKey(this.username))
			{
				if((this.password).equals(userPro.getProperty(this.username)))
				{
					check = true;
				}else{   
					//密码错误
					check = false;
				}
			}else{
				//用户名不存在
				check = false;
			}
		}else{
			//用户名不存在
			check = false;
		}
		return check;
	}
	
}
