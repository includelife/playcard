package main;

/**
 * UserBean
 * @author huzhp
 *
 */
public class UserBean {
	private static String name=null;
	private static String password=null;
	private static String score=null;
	private static String others=null;
	
	/**
	 * simple struct method
	 */
	public UserBean(){
		super();
	}
	
	/**
	 * name method
	 * @param name
	 */
	public UserBean(String name) {
		this.name=name;
	}
	
	/**
	 * name and pass method
	 * @param name
	 * @param pass
	 */
	public UserBean(String name,String pass) {
		this.name=name;
		this.password=pass;
	}
	
	/**
	 * name,password and scores method
	 * @param name
	 * @param pass
	 * @param scores
	 */
	public UserBean(String name,String pass,String scores){
		this.name=name;
		this.password=pass;
		this.score=scores;
	}
	
	/**
	 * name,password,score and others method
	 * @param name
	 * @param pass
	 * @param scores
	 * @param others
	 */
	public UserBean(String name,String pass,String scores,String others){
		this.name=name;
		this.password=pass;
		this.score=scores;
		this.others=others;
	}

	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the score
	 */
	public static String getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the others
	 */
	public static String getOthers() {
		return others;
	}

	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}
}
