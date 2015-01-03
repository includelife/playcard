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

import multi.GameFrame;
import db.DBQuery;
import action.LoginAction;
import play.Playing;
import util.FileUtil;

/**
 * ��Ϸѡ�����
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
		this.setTitle("������");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // ��Ļ����
		pane.setLayout(null);
		pane.setBackground(new Color(44, 114, 104)); // ����Ϊ��ɫ		
		
		//�����ǩ
		final JLabel titleLabel = new JLabel();
		titleLabel.setText("��ӭ����������");
		Font myfont = new Font("����",Font.CENTER_BASELINE,60);
		titleLabel.setFont(myfont);
		titleLabel.setBounds(200, 35, 700, 200);
		this.getContentPane().add(titleLabel);		

	}	
	
	/**
	 * ����Ԫ�س�ʼ��
	 */
	public void initSelect(){
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 800, 600);
		backgroundLabel.setLayout(null);
		
		single = new JButton("����ģʽ");
		multi = new JButton("����ģʽ");
		help = new JButton("��  ��");
		search = new JButton("������ѯ");
		rebutton = new JButton("�� ��");
		
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
	
	//��÷���
	private void getScore(){
//  /***********************************�������ݿ�*********************************************/	
		users = LoginAction.getUsername();
		DBQuery scorequery = new DBQuery(users,"score");
		scores = scorequery.getScore();
//  /***********************************�����ļ�**********************************************/		
//		users = LoginAction.getUsername();
//		scorePro = new Properties();
//		scorefile = new File("Score.properties");		
//		FileUtil.loadPro(scorePro, scorefile);			
//		scores = scorePro.getProperty(users);
	}
	
	/**
	 * ���������ʼ��
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
		user.setText("���");
		score.setText("����");
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
	 * ������Բ�̫���
	 */

	/**
	 * ʵ��ActionListener�ӿ�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == single){
			//JOptionPane.showMessageDialog(this, "����ģʽ");
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
			//����ģʽ
//			JOptionPane.showMessageDialog(this, "������");
			GameFrame gameframe = new GameFrame();
			this.dispose();
			gameframe.MultiStart();
		}else if(e.getSource() == help){
			//����
			JOptionPane.showMessageDialog(this, "����Ҫʲô������");
		}else if(e.getSource() == search){
			//��ѯ����
			InitScore();	
		}else if(e.getSource() == rebutton){
			//���ص�½����
			this.dispose();
			LoginFrame fr = new LoginFrame();
			fr.setVisible(true);
		}
	}

	/**
	 * @return the pane
	 */
	public Container getPane() {
		return pane;
	}

	/**
	 * @param pane the pane to set
	 */
	public void setPane(Container pane) {
		this.pane = pane;
	}

	/**
	 * @return the single
	 */
	public JButton getSingle() {
		return single;
	}

	/**
	 * @param single the single to set
	 */
	public void setSingle(JButton single) {
		this.single = single;
	}

	/**
	 * @return the multi
	 */
	public JButton getMulti() {
		return multi;
	}

	/**
	 * @param multi the multi to set
	 */
	public void setMulti(JButton multi) {
		this.multi = multi;
	}

	/**
	 * @return the help
	 */
	public JButton getHelp() {
		return help;
	}

	/**
	 * @param help the help to set
	 */
	public void setHelp(JButton help) {
		this.help = help;
	}

	/**
	 * @return the search
	 */
	public JButton getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(JButton search) {
		this.search = search;
	}

	/**
	 * @return the rebutton
	 */
	public JButton getRebutton() {
		return rebutton;
	}

	/**
	 * @param rebutton the rebutton to set
	 */
	public void setRebutton(JButton rebutton) {
		this.rebutton = rebutton;
	}

	/**
	 * @return the users
	 */
	public String getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(String users) {
		this.users = users;
	}

	/**
	 * @return the scorePro
	 */
	public Properties getScorePro() {
		return scorePro;
	}

	/**
	 * @param scorePro the scorePro to set
	 */
	public void setScorePro(Properties scorePro) {
		this.scorePro = scorePro;
	}

	/**
	 * @return the scorefile
	 */
	public File getScorefile() {
		return scorefile;
	}

	/**
	 * @param scorefile the scorefile to set
	 */
	public void setScorefile(File scorefile) {
		this.scorefile = scorefile;
	}

	/**
	 * @return the scores
	 */
	public String getScores() {
		return scores;
	}

	/**
	 * @param scores the scores to set
	 */
	public void setScores(String scores) {
		this.scores = scores;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
