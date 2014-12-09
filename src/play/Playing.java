package play;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.stream.events.StartDocument;

import playcard.Card;
import time.Time;

public class Playing extends JFrame implements ActionListener{
	
    //定义容器	
	public Container container = null;
	
	//定义菜单按钮
	private JMenuItem start,exit,about;
	
	//抢地主按钮
	private JButton landlord[] = new JButton[2]; 
	
	//出牌按钮
	private JButton publishCard[] = new JButton[2];
	
	//地主标志
	int lordFlag;
	int turn;
	private JLabel lord;
	private List<Card> currenList[] = new ArrayList[3];
	private List<Card> playerList[] = new ArrayList[3];
	private List<Card> lordList;	
	private Card card[] = new Card[56];
	private JTextField time[] = new JTextField[3];
	private Time t;
	private boolean nextPlayer = false;
	
	public Playing() {
		// TODO Auto-generated constructor stub
		Init();
		setMenu();
		this.setVisible(true);
		CardInit();
		getLord();
		getTimefield()[1].setVisible(true);
		SwingUtilities.invokeLater(new NewTimer(this,10));
	}
	
	public void getLord(){
		for (int i = 0; i < 2; i++) {
			getLandlord()[i].setVisible(true);
		}
	}
	
	
	//发牌洗牌
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
				lordList.add(card[i]);
				continue;
			}
			switch ((t++)%3) {
			case 0:
				//左边玩家
				//move
				this.playerList[0].add(card[i]);
				break;
			case 1:
				//自己
				//move
				this.playerList[1].add(card[i]);
				break;
			case 2:
				//右边玩家
				//move
				this.playerList[2].add(card[2]);
				break;
			}
			container.setComponentZOrder(card[1], 0);
		}
		//发完牌后，从大到小
		for (int i = 0; i < 3; i++) {
			//order
			//rePosition
		}
		this.lord = new JLabel("images/dizhu.gif");
		lord.setVisible(false);
		lord.setSize(40,40);
		container.add(lord);
	}
	

	private void setMenu() {
		// TODO Auto-generated method stub
		
	}
	
	//初始化窗体
	private void Init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public Time getTime() {
		return t;
	}

	public void setTime(Time t) {
		this.t = t;
	}

	/**
	 * @return the time
	 */
	public JTextField[] getTimefield() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTimefield(JTextField time[]) {
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
	
}

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
