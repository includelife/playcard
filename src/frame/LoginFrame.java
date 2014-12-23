package frame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import play.Playing;
import action.LoginAction;

/**
 * 登陆框架
 * @author huzhp
 *
 */

public class LoginFrame extends JFrame implements ActionListener{
	/**
	 * 这个属性不太清楚
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *声明输入名字和密码的文本框
	 *声明登陆按钮，重置按钮，注册按钮
	 */
	private JTextField nameTF;
	private JPasswordField passwordTF;
	private JButton loginButton = null, resetButton = null;
	private JProgressBarFrame progressBar = null;
	private String username = null, password = null;
	private JButton regisButton;
	
	public LoginFrame() {
		// TODO Auto-generated constructor stub
		super();
		getContentPane().setLayout(null);
		//初始化界面
		jFrameValidate(); 
		
		setTitle("登录游戏");
		
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 800, 600);
		backgroundLabel.setLayout(null);
		
		//标题标签
		final JLabel titleLabel = new JLabel();
		titleLabel.setText("斗	地	主");
		Font myfont = new Font("宋体",Font.CENTER_BASELINE,60);
		titleLabel.setFont(myfont);
		titleLabel.setBounds(330, 35, 400, 200);
		this.getContentPane().add(titleLabel);

		//用户名标签
		final JLabel nameLable = new JLabel();
		nameLable.setText("用户名 ：");
		nameLable.setBounds(230, 203, 100, 18);
		//将服务器标签添加进背景标签
		backgroundLabel.add(nameLable);
		
		//密码标签
		final JLabel passLable = new JLabel();
		passLable.setText("密  码 ：");
		passLable.setBounds(230, 243, 100, 18);
		//将服务器标签添加进背景标签
		backgroundLabel.add(passLable);
		
		//用户名
		nameTF = new JTextField();
		nameTF.setBounds(370, 203, 150, 22);
		backgroundLabel.add(nameTF);
		
		//密码
		passwordTF = new JPasswordField();
		passwordTF.setBounds(370, 243, 150, 22);
		backgroundLabel.add(passwordTF);
		
		loginButton = new JButton("登录");
		resetButton = new JButton("重置");
		regisButton = new JButton("注册");
		backgroundLabel.add(loginButton);
		backgroundLabel.add(resetButton);
		backgroundLabel.add(regisButton);
		loginButton.setBounds(260, 360, 80, 30);
		resetButton.setBounds(380, 360, 80, 30);
		regisButton.setBounds(500, 360, 80, 30);
		
		//添加监听器
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		regisButton.addActionListener(this);
		
		
		//把背景标签添加进内容面板
		getContentPane().add(backgroundLabel);
		
		getContentPane().setBackground(new Color(0, 112, 26));
		progressBar = new JProgressBarFrame(this, "登录", "登录中，请稍后...");
		
		reset();// 默认初始值
	}
	
	private void reset() {
		// TODO Auto-generated method stub
		nameTF.setText("");
		passwordTF.setText("");
	}

	private void jFrameValidate() {
		// TODO Auto-generated method stub
		// 获得系统的相关属性，这里需要屏幕的宽和高
		Toolkit tk = getToolkit();
		//Dimension是Java的一个类，封装了一个构件的高度和宽度
		Dimension dim = tk.getScreenSize();
		this.setResizable(false);
		//框架居中显示  760 540
		this.setBounds(dim.width / 2 - 400, dim.height / 2 - 300, 800, 600);
		validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginButton) {// 登录
			progressBar.setVisible(true);// 设置进度条可见
			new Thread() {
				public void run() {
					getValues();// 得到界面中的所有项的值
					checkUser();// 登录验证
				}
			}.start();
		} else if (e.getSource() == resetButton) {// 重置
			reset();// 重新设置各项的值
		}else if(e.getSource() == regisButton){
			//注册界面
			this.setVisible(false);;
			RegisFrame rf = new RegisFrame();
			rf.init();
			rf.setVisible(true);			
		}
	}

	private void checkUser() {
		LoginAction login = new LoginAction(getUsername(),
				getPassword());
		if (login.isLogin()) {// 登录成功
			progressBar.dispose();
			this.dispose();// 释放本窗口资源
				
			SelectFrame fr = new SelectFrame();
			fr.init();
			fr.initSelect();
			fr.setVisible(true);			
		} else {// 登录失败
			progressBar.setVisible(false);
			JOptionPane.showMessageDialog(this, "<html><h4>"
					+ "登录失败，请检查用户名、密码是否正确！" + "<html><h4>", "警告",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * 得到输入的值
	 */
	@SuppressWarnings("deprecation")
	protected void getValues() {
		// TODO Auto-generated method stub
		setUsername(nameTF.getText().trim());
		setPassword(passwordTF.getText().trim());
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
