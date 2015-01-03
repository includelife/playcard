package backup;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
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
import javax.swing.SwingUtilities;

import main.Card;
import main.CardType;
import play.Playing;
import time.Time;
import util.PlayUtil;
import action.LoginAction;
import db.DBQuery;
import frame.SelectFrame;

public class Player extends JFrame implements ActionListener{
	
    //定义容器	
	public Container container = null;
	
	//定义菜单按钮  
	private JMenuItem start,exit,about;
	
	//抢地主按钮
	public JButton landlord[] = new JButton[2]; 
	
	//出牌按钮
	public JButton publishCard[] = new JButton[2];
	
	//地主标志
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
	
	public void Playing() {
		// TODO Auto-generated constructor stub
		InitGame();
	}
	
	/**
	 * 游戏初始化
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
	 * 游戏面板初始化
	 */
	private void Init() {
		// TODO Auto-generated method stub
		this.setTitle("斗地主游戏");
		this.setSize(830, 620);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // 屏幕居中
		container = this.getContentPane();
		container.setLayout(null);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(new Color(44, 114, 104)); // 背景为绿色
	}
	
	/**
	 * 获得分数
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
	 * 初始化分数界面
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
		user.setText("玩家");
		score.setText("分数");
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
	 * 初始化菜单
	 */
	private void setMenu() {
		// TODO Auto-generated method stub
		JMenuBar jMenuBar = new JMenuBar();
		JMenu game = new JMenu("游戏");
		JMenu help = new JMenu("帮助");

		start = new JMenuItem("新游戏");
		exit = new JMenuItem("退出");
		about = new JMenuItem("关于");

		start.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);

		game.add(start);
		game.add(exit);
		help.add(about);

		jMenuBar.add(game);
		jMenuBar.add(help);
		this.setJMenuBar(jMenuBar);
		
		landlord[0]=new JButton("抢地主");
		landlord[1]=new JButton("不     抢");
		publishCard[0]= new JButton("出牌");
		publishCard[1]= new JButton("不要");
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
			time[i]=new JLabel("倒计时:");
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
	 * 发牌洗牌
	 */
	private void CardInit() {
		// TODO Auto-generated method stub
		int count = 1;
		//初始化牌
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
		//打乱顺序
		for (int i = 0; i < 100; i++) {
			Random random = new Random();
			int a = random.nextInt(54)+1;
			int b = random.nextInt(54)+1;
			Card k = card[a];
			card[a] = card[b];
			card[b] = k;			
		}
		//开始发牌
		for (int i = 0; i < 3; i++) {
			this.playerList[i] = new ArrayList<Card>();
		}
		lordList = new ArrayList<Card>();
		int t = 0;
		for (int i = 1; i <= 54; i++)
		{
			if(i>=52)//地主牌
			{
				//移动牌
				PlayUtil.move(card[i], card[i].getLocation(),new Point(300+(i-52)*80,10));
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//左边玩家
				PlayUtil.move(card[i], card[i].getLocation(),new Point(50,60+i*5));
				this.playerList[0].add(card[i]);
				break;
			case 1:
				//自己
				PlayUtil.move(card[i], card[i].getLocation(),new Point(180+i*7,450));
				this.playerList[1].add(card[i]);
				//显示正面
				card[i].turnFront();				
				break;
			case 2:
				//右边玩家
				PlayUtil.move(card[i], card[i].getLocation(),new Point(700,60+i*5));
				this.playerList[2].add(card[i]);
				break;
			}
			container.setComponentZOrder(card[i], 0);
		}
		//发完牌后，从大到小
		for (int i = 0; i < 3; i++) {
			//order
			//rePosition
			PlayUtil.order(playerList[i]);
			PlayUtil.rePosition(this,playerList[i],i);//重新定位
		}
		this.lord = new JLabel(new ImageIcon("images/dizhu.gif"));
		lord.setVisible(false);
		lord.setSize(40,40);
		container.add(lord);
	}
	
	/**
	 * 抢地主
	 */
	private void getLord(){
		for (int i = 0; i < 2; i++) {
			landlord[i].setVisible(true);
		}
	}
	
	/**
	 * 重新开始游戏
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
	 * 实现ActionListener接口
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
			//开始新游戏
		}
		if(e.getSource()==landlord[0])
		{
			time[1].setText("抢地主");
			t.setRun(false);		
		}
		if(e.getSource()==landlord[1])
		{
			time[1].setText("不抢");
			t.setRun(false);
		}
		//如果是不要
		if(e.getSource()==publishCard[1])
		{
			this.nextPlayer=true;
			currentList[1].clear();
			time[1].setText("不要");		
		}
		
		//如果是出牌按钮
		if(e.getSource()==publishCard[0])
		{
			List<Card> c=new ArrayList<Card>();
			//点选出牌
			for(int i=0;i<playerList[1].size();i++)
			{
				Card card=playerList[1].get(i);
				if(card.isClicked())
				{
					c.add(card);
				}
			}
			int flag=0;
			
			//如果我主动出牌
			if(time[0].getText().equals("不要")&&time[2].getText().equals("不要"))
			{
			
				if(PlayUtil.jugdeType(c)!=CardType.c0)
					flag=1;//表示可以出牌
			}//如果我跟牌
			else{
			
				flag=PlayUtil.checkCards(c,this.currentList);
			}
			//判断是否符合出牌
			if(flag==1)
			{
				currentList[1]=c;
				playerList[1].removeAll(currentList[1]);//移除走的牌
				//定位出牌
				Point point=new Point();
				point.x=(770/2)-(currentList[1].size()+1)*15/2;;
				point.y=300;
				for(int i=0,len=currentList[1].size();i<len;i++)
				{
					Card card=currentList[1].get(i);
					PlayUtil.move(card, card.getLocation(), point);
					point.x+=15;
				}
				//抽完牌后重新整理牌
				PlayUtil.rePosition(this, playerList[1], 1);
				time[1].setVisible(false);
				this.nextPlayer=true;
			}
		}
	}
	
	/**
	 * 等待i秒
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
	
	//get,set方法
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
		Playing.setScores(scores);
	}

	/**
	 * @return the container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * @return the start
	 */
	public JMenuItem getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(JMenuItem start) {
		this.start = start;
	}

	/**
	 * @return the exit
	 */
	public JMenuItem getExit() {
		return exit;
	}

	/**
	 * @param exit the exit to set
	 */
	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}

	/**
	 * @return the about
	 */
	public JMenuItem getAbout() {
		return about;
	}

	/**
	 * @param about the about to set
	 */
	public void setAbout(JMenuItem about) {
		this.about = about;
	}

	/**
	 * @return the publishCard
	 */
	public JButton[] getPublishCard() {
		return publishCard;
	}

	/**
	 * @param publishCard the publishCard to set
	 */
	public void setPublishCard(JButton[] publishCard) {
		this.publishCard = publishCard;
	}

	/**
	 * @return the lordFlag
	 */
	public int getLordFlag() {
		return lordFlag;
	}

	/**
	 * @param lordFlag the lordFlag to set
	 */
	public void setLordFlag(int lordFlag) {
		this.lordFlag = lordFlag;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * @return the currentList
	 */
	public List<Card>[] getCurrentList() {
		return currentList;
	}

	/**
	 * @param currentList the currentList to set
	 */
	public void setCurrentList(List<Card>[] currentList) {
		this.currentList = currentList;
	}

	/**
	 * @return the card
	 */
	public Card[] getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(Card[] card) {
		this.card = card;
	}

	/**
	 * @return the t
	 */
	public Time getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(Time t) {
		this.t = t;
	}

	/**
	 * @return the nextPlayer
	 */
	public boolean isNextPlayer() {
		return nextPlayer;
	}

	/**
	 * @param nextPlayer the nextPlayer to set
	 */
	public void setNextPlayer(boolean nextPlayer) {
		this.nextPlayer = nextPlayer;
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
	 * @param lord the lord to set
	 */
	public void setLord(JLabel lord) {
		this.lord = lord;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(JLabel[] time) {
		this.time = time;
	}
	
}

/**
 * 通过实现Runnable接口，创建新的线程
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
		
		public NewTimer(Player player, int i2) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			playing.setTime(new Time(playing,10));	
			playing.getTime().start();
		}
}	
