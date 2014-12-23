package play;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.stream.events.StartDocument;














import db.DBQuery;
import action.LoginAction;
import Main.Card;
import Main.CardType;
import frame.SelectFrame;
import time.Time;
import util.FileUtil;
import util.PlayUtil;

/**
 * ��Ϸ����
 * @author huzhp
 *
 */
public class Playing extends JFrame implements ActionListener{
	
    //��������	
	public Container container = null;
	
	//����˵���ť
	private JMenuItem start,exit,about;
	
	//��������ť
	public JButton landlord[] = new JButton[2]; 
	
	//���ư�ť
	public JButton publishCard[] = new JButton[2];
	
	//������־
	public int lordFlag;
	public int turn;
	public JLabel lord;
	public List<Card> currentList[] = new ArrayList[3];
	public List<Card> playerList[] = new ArrayList[3];
	public List<Card> lordList;	
	public Card card[] = new Card[56];
	//public JTextField time[] = new JTextField[3];
	public JLabel time[] = new JLabel[3];
	public Time t;
	public boolean nextPlayer = false;
	
	private String users = null; 
	private static String scores = null;

	private Properties scorePro = null;

	private File scorefile = null; 
	
	public Playing() {
		// TODO Auto-generated constructor stub
		InitGame();
	}
	
	/**
	 * ��Ϸ��ʼ��
	 */
	public void InitGame(){
		Init();
		//InitScore();
		getScore();
		setMenu();
		this.setVisible(true);
		CardInit();
		getLord();
		time[1].setVisible(true);
		SwingUtilities.invokeLater(new NewTimer(this,10));
	}
	
	/**
	 * ��Ϸ����ʼ��
	 */
	private void Init() {
		// TODO Auto-generated method stub
		this.setTitle("��������Ϸ");
		this.setSize(830, 620);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // ��Ļ����
		container = this.getContentPane();
		container.setLayout(null);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(0, 112, 26)); // ����Ϊ��ɫ
	}
	
	/**
	 * ��÷���
	 */
	private void getScore(){
		users = LoginAction.getUsername();
		DBQuery scorequery = new DBQuery(users,"score");
		scores = scorequery.getScore();
		
//		scorePro = new Properties();
//		scorefile = new File("Score.properties");		
//		FileUtil.loadPro(scorePro, scorefile);		
//		setScores(scorePro.getProperty(users));
	}
	
	/**
	 * ��ʼ����������
	 */
	private void InitScore(){
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
		pscore.setText(getScores());
		
		user.setBounds(20, 2, 50, 20);	
		puser.setBounds(20, 25, 50, 20);	
		score.setBounds(90, 2, 50, 20);
		pscore.setBounds(90, 25, 50, 20);
		
		scorePanel.add(user);
		scorePanel.add(score);
		scorePanel.add(puser);
		scorePanel.add(pscore);
		container.add(scorePanel);
		
	}
	
	/**
	 * ��ʼ���˵�
	 */
	private void setMenu() {
		// TODO Auto-generated method stub
		JMenuBar jMenuBar = new JMenuBar();
		JMenu game = new JMenu("��Ϸ");
		JMenu help = new JMenu("����");

		start = new JMenuItem("����Ϸ");
		exit = new JMenuItem("�˳�");
		about = new JMenuItem("����");

		start.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);

		game.add(start);
		game.add(exit);
		help.add(about);

		jMenuBar.add(game);
		jMenuBar.add(help);
		this.setJMenuBar(jMenuBar);
		
		landlord[0]=new JButton("������");
		landlord[1]=new JButton("��     ��");
		publishCard[0]= new JButton("����");
		publishCard[1]= new JButton("��Ҫ");
		for(int i=0;i<2;i++)
		{
			publishCard[i].setBounds(420-i*100, 400, 60, 20);
			landlord[i].setBounds(320+i*100, 400,75,20);
			container.add(landlord[i]);
			landlord[i].addActionListener(this);
			landlord[i].setVisible(false);
			container.add(publishCard[i]); 
			publishCard[i].setVisible(false);
			publishCard[i].addActionListener(this);
		}
		for(int i=0;i<3;i++){
			time[i]=new JLabel("����ʱ:");
			//Font font = new Font(arg0, arg1, arg2);
			//time[i].setFont(font);
			time[i].setForeground(Color.white);
			time[i].setVisible(false);
			container.add(time[i]);
		}
		time[0].setBounds(140, 230, 60, 20);
		time[1].setBounds(374, 360, 60, 20);
		time[2].setBounds(620, 230, 60, 20);
		
		for(int i=0;i<3;i++)
		{
			currentList[i]=new ArrayList<Card>();
		}
		
	}
	
	/**
	 * ����ϴ��
	 */
	private void CardInit() {
		// TODO Auto-generated method stub
		int count = 1;
		//��ʼ����
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++) {
				if((i == 5) && (j >2 ))
					break;
				else{
					card[count] = new Card(this,i+"-"+j,false);
					card[count].setLocation(350,50);
					container.add(card[count]);
					count++;
				}
			}
		}		
		//����˳��
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			int a = random.nextInt(54)+1;
			int b = random.nextInt(54)+1;
			Card k = card[a];
			card[a] = card[b];
			card[b] = k;			
		}
		//��ʼ����
		for (int i = 0; i < 3; i++) {
			this.playerList[i] = new ArrayList<Card>();
		}
		lordList = new ArrayList<Card>();
		int t = 0;
		for (int i = 1; i <= 54; i++)
		{
			if(i>=52)//������
			{
				//�ƶ���
				PlayUtil.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//������
				PlayUtil.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				this.playerList[0].add(card[i]);
				break;
			case 1:
				//�Լ�
				PlayUtil.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				this.playerList[1].add(card[i]);
				//��ʾ����
				card[i].turnFront();				
				break;
			case 2:
				//�ұ����
				PlayUtil.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				this.playerList[2].add(card[i]);
				break;
			}
			container.setComponentZOrder(card[i], 0);
		}
		//�����ƺ󣬴Ӵ�С
		for (int i = 0; i < 3; i++) {
			//order
			//rePosition
			PlayUtil.order(playerList[i]);
			PlayUtil.rePosition(this,playerList[i],i);//���¶�λ
		}
		this.lord = new JLabel(new ImageIcon("images/dizhu.gif"));
		lord.setVisible(false);
		lord.setSize(40,40);
		container.add(lord);
	}
	
	/**
	 * ������
	 */
	private void getLord(){
		for (int i = 0; i < 2; i++) {
			landlord[i].setVisible(true);
		}
	}
	
	/**
	 * ���¿�ʼ��Ϸ
	 */
	public void reStart()
	{
		this.dispose();
	    // TODO Auto-generated method stub
		SelectFrame reselect = new SelectFrame();
		reselect.init();
		reselect.initSelect();
		reselect.setVisible(true);	
	}


	/**
	 * ʵ��ActionListener�ӿ�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exit){
			this.dispose();
		}
		else if(e.getSource() == about){
			JOptionPane.showMessageDialog(this, "made by huzhp");
		}
		else if(e.getSource() == start){
			this.dispose();
			this.reStart();
			//��ʼ����Ϸ
		}
		if(e.getSource()==landlord[0])
		{
			time[1].setText("������");
			t.setRun(false);		
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("����");
			t.setRun(false);
		}
		//����ǲ�Ҫ
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			time[1].setText("��Ҫ");		
		}
		
		//����ǳ��ư�ť
		if(e.getSource()==publishCard[0])
		{
			List<Card> c=new ArrayList<Card>();
			//��ѡ����
			for(int i=0;i<playerList[1].size();i++)
			{
				Card card=playerList[1].get(i);
				if(card.isClicked())
				{
					c.add(card);
				}
			}
			int flag=0;
			
			//�������������
			if(time[0].getText().equals("��Ҫ")&&time[2].getText().equals("��Ҫ"))
			{
			
				if(PlayUtil.jugdeType(c)!=CardType.c0)
					flag=1;//��ʾ���Գ���
			}//����Ҹ���
			else{
			
				flag=PlayUtil.checkCards(c,this.currentList);
			}
			//�ж��Ƿ���ϳ���
			if(flag==1)
			{
				currentList[1]=c;
				playerList[1].removeAll(currentList[1]);//�Ƴ��ߵ���
				//��λ����
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					Card card=currentList[1].get(i);
					PlayUtil.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//�����ƺ�����������
				PlayUtil.rePosition(this, playerList[1], 1);
				time[1].setVisible(false);
				this.nextPlayer=true;
			}
		}
	}
	
	/**
	 * �ȴ�i��
	 * @param i
	 */
	public void second(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//get,set����
	public Time getTime() {
		return t;
	}

	public void setTime(Time t) {
		this.t = t;
	}

	/**
	 * @return the time
	 */
	public JLabel[] getTimefield() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTimefield(JLabel time[]) {
		this.time = time;
	}

	/**
	 * @return the playerList
	 */
	public List<Card>[] getPlayerList() {
		return playerList;
	}

	/**
	 * @param playerList the playerList to set
	 */
	public void setPlayerList(List<Card> playerList[]) {
		this.playerList = playerList;
	}

	/**
	 * @return the landlord
	 */
	public JButton[] getLandlord() {
		return landlord;
	}

	/**
	 * @param landlord the landlord to set
	 */
	public void setLandlord(JButton landlord[]) {
		this.landlord = landlord;
	}

	/**
	 * @return the lordList
	 */
	public List<Card> getLordList() {
		return lordList;
	}

	/**
	 * @param lordList the lordList to set
	 */
	public void setLordList(List<Card> lordList) {
		this.lordList = lordList;
	}

	/**
	 * @return the scores
	 */
	public static String getScores() {
		return scores;
	}

	/**
	 * @param scores the scores to set
	 */
	public static void setScores(String scores) {
		Playing.scores = scores;
	}
}

/**
 * ͨ��ʵ��Runnable�ӿڣ������µ��߳�
 * @author huzhp
 *
 */
class NewTimer implements Runnable{
		
		Playing playing;
		int i;
		public NewTimer(Playing play,int i) {
			// TODO Auto-generated constructor stub
			this.playing = play;
			this.i = i;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			playing.setTime(new Time(playing,10));	
			playing.getTime().start();
		}
}	

