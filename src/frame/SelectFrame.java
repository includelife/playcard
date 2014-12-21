package frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import action.LoginAction;
import play.Playing;
import util.FileUtil;

/**
 * 游戏选择界面
 * @author huzhp
 *
 */

public class SelectFrame extends JFrame implements ActionListener{
	private Container pane = null;
	private JButton single = null;
	private JButton multi = null;
	private JButton help = null;
	private JButton search = null;
	private JButton rebutton = null;
	private String users;
	private Properties scorePro;
	private File scorefile;
	private String scores;
	
	public SelectFrame() {		
		super();
	}
	
	public void init() {
		pane = this.getContentPane();
		this.setTitle("斗地主");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // 屏幕居中
		pane.setLayout(null);
		pane.setBackground(new Color(0, 112, 26)); // 背景为绿色		
		
		//标题标签
		final JLabel titleLabel = new JLabel();
		titleLabel.setText("欢迎来到斗地主");
		Font myfont = new Font("宋体",Font.CENTER_BASELINE,60);
		titleLabel.setFont(myfont);
		titleLabel.setBounds(200, 35, 700, 200);
		this.getContentPane().add(titleLabel);		

	}	
	
	/**
	 * 界面元素初始化
	 */
	public void initSelect(){
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 800, 600);
		backgroundLabel.setLayout(null);
		
		single = new JButton("单人模式");
		multi = new JButton("多人模式");
		help = new JButton("帮  助");
		search = new JButton("分数查询");
		rebutton = new JButton("返 回");
		
		single.setBounds(365, 190, 100, 40);
		multi.setBounds(365, 190+50, 100, 40);
		help.setBounds(365, 190+50*3, 100, 40);
		search.setBounds(365, 190+50*2, 100, 40);
		rebutton.setBounds(365, 190+50*4, 100, 40);
		
		single.addActionListener(this);
		multi.addActionListener(this);
		help.addActionListener(this);
		search.addActionListener(this);
		rebutton.addActionListener(this);

		backgroundLabel.add(single);
		backgroundLabel.add(multi);
		backgroundLabel.add(help);
		backgroundLabel.add(search);
		backgroundLabel.add(rebutton);

		this.getContentPane().add(backgroundLabel);
	}
	
	//获得分数
	private void getScore(){
		users = LoginAction.getUsername();
		scorePro = new Properties();
		scorefile = new File("Score.properties");		
		FileUtil.loadPro(scorePro, scorefile);	
		
		scores = scorePro.getProperty(users);
	}
	
	/**
	 * 分数界面初始化
	 */
	private void InitScore(){
		
		JFrame scorefr = new JFrame();
		Toolkit tk = getToolkit();
		Dimension dim = tk.getScreenSize();
		scorefr.setResizable(false);
		scorefr.setBounds(dim.width/2-225,dim.height/2-150,450,300);
		validate();
		scorefr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		getScore();
		JPanel scorePanel = new JPanel();
		scorePanel.setBounds(0, 0, 150, 50);
		scorePanel.setBackground(Color.white);
		scorePanel.setLayout(null);
		JLabel user = new JLabel();
		JLabel puser = new JLabel();
		JLabel score = new JLabel();
		JLabel pscore = new JLabel();
		user.setText("玩家");
		score.setText("分数");
		puser.setText(users);
		pscore.setText(scores);
		
		user.setBounds(20, 2, 50, 20);	
		puser.setBounds(20, 25, 50, 20);	
		score.setBounds(90, 2, 50, 20);
		pscore.setBounds(90, 25, 50, 20);
		
		scorePanel.add(user);
		scorePanel.add(score);
		scorePanel.add(puser);
		scorePanel.add(pscore);
		
		scorefr.add(scorePanel);
		scorefr.setVisible(true);
		
	}

	private static final long serialVersionUID = 1L;
	/**
	 * 这个属性不太清楚
	 */

	/**
	 * 实现ActionListener接口
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == single){
			//JOptionPane.showMessageDialog(this, "单人模式");
			this.dispose();
			
			Runnable thread = new Runnable() {				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Playing play = new Playing();
					play.setVisible(true);
				}
			};
			
			Thread t = new Thread(thread);
			t.start();
		}else if(e.getSource() == multi){
			//多人模式
			JOptionPane.showMessageDialog(this, "开发中");
		}else if(e.getSource() == help){
			//帮助
			JOptionPane.showMessageDialog(this, "您需要什么帮助？");
		}else if(e.getSource() == search){
			//查询分数
			InitScore();	
		}else if(e.getSource() == rebutton){
			//返回登陆界面
			this.dispose();
			LoginFrame fr = new LoginFrame();
			fr.setVisible(true);
		}
	}
}
