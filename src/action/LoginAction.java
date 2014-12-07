package action;

public class LoginAction {
	private String username = null;
	private String password = null;
	
	public LoginAction(String username,String password){
		super();
		this.username = username;
		this.password = password;
	}
	
	public boolean isLogin() {
		// TODO Auto-generated method stub
		boolean isLogin = true;
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
		
		return check;
	}
	
}
