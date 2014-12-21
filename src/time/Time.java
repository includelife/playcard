package time;

import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import action.LoginAction;
import Main.Card;
import Main.CardType;
import Main.Model;
import play.Playing;
import util.FileUtil;
import util.PlayUtil;

/**
 * AI
 * @author huzhp
 *
 */
public class Time extends Thread{
	public Playing palying;
	private boolean isRun = true;
	int i = 10;
	private String users;
	private Properties scorePro;
	private File scorefile;

	public Time(Playing play, int i) {
		this.palying = play;
		this.i = i;
	}
	
	/**
	 * ��ʼ������
	 */
	@Override
	public void run() {
		while (i > -1 && isRun()) {
			palying.time[1].setText("����ʱ:" + i--);
			second(1);// ��һ��
		}
		if (i == -1)// �����սᣬ˵����ʱ
			palying.time[1].setText("����");
		palying.landlord[0].setVisible(false);
		palying.landlord[1].setVisible(false);
		for (Card card2 : palying.playerList[1])
			card2.canClick = true;// �ɱ����
		// ����Լ���������
		if (palying.time[1].getText().equals("������")) {
			// �õ�������
			palying.playerList[1].addAll(palying.lordList);
			openlord(true);
			second(3);// �ȴ�3��
			PlayUtil.order(palying.playerList[1]);
			PlayUtil.rePosition(palying, palying.playerList[1], 1);
			setlord(1);
		} else {
			// ����ѡ����
			if (PlayUtil.getScore(palying.playerList[0]) < PlayUtil
					.getScore(palying.playerList[2])) {
				palying.time[2].setText("������");
				palying.time[2].setVisible(true);
				setlord(2);// �趨����
				openlord(true);
				second(3);
				palying.playerList[2].addAll(palying.lordList);
				PlayUtil.order(palying.playerList[2]);
				PlayUtil.rePosition(palying, palying.playerList[2], 2);
				openlord(false);

			} else {
				palying.time[0].setText("������");
				palying.time[0].setVisible(true);
				setlord(0);// �趨����
				openlord(true);
				second(3);
				palying.playerList[0].addAll(palying.lordList);
				PlayUtil.order(palying.playerList[0]);
				PlayUtil.rePosition(palying, palying.playerList[0], 0);
				openlord(false);

			}
		}
		
		// ѡ������� �رյ�����ť		
		palying.landlord[0].setVisible(false);
		palying.landlord[1].setVisible(false);
		turnOn(false);
		for (int i = 0; i < 3; i++)
		{
			palying.time[i].setText("��Ҫ");
			palying.time[i].setVisible(false);
		}
		
		/**
		 * ��ʼ��ʽ��Ϸ�����ݵ�����ͬ˳��ͬ
		 */
		palying.turn=palying.lordFlag;
		while (true) {
			
			if(palying.turn==1) //��
			{
				turnOn(true);// ���ư�ť --�ҳ���
				timeWait(30, 1);// ���Լ��Ķ�ʱ��
				turnOn(false);//ѡ��ر�
				palying.turn=(palying.turn+1)%3;
				if(win())//�ж���Ӯ
				{
//					this.palying.container.removeAll();
//					Runnable thread = new Runnable() {				
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							palying.InitGame();
//						}
//					};				
//					Thread t = new Thread(thread);
//					t.start();
					break;
				}
			}
			if (palying.turn==0) 
			{
				computer0();
				palying.turn=(palying.turn+1)%3;
				if(win())//�ж���Ӯ
					break;
			}
			if(palying.turn==2)
			{
				computer2();
				palying.turn=(palying.turn+1)%3;
				if(win())//�ж���Ӯ
					break;
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

	/**
	 * �����Ʒ���
	 * @param is
	 */
	public void openlord(boolean is) {
		for (int i = 0; i < 3; i++) {
			if (is)
				palying.lordList.get(i).turnFront(); // �����Ʒ���
			else {
				palying.lordList.get(i).turnRear(); // �����Ʊպ�
			}
			palying.lordList.get(i).canClick = true;// �ɱ����
		}
	}

	/**
	 * �趨����
	 * @param i
	 */
	public void setlord(int i) {
		Point point = new Point();
		if (i == 1)// ���ǵ���
		{
			point.x = 80;
			point.y = 430;
			palying.lordFlag = 1;// �趨����
		}
		if (i == 0) {
			point.x = 80;
			point.y = 20;
			palying.lordFlag = 0;
		}
		if (i == 2) {
			point.x = 700;
			point.y = 20;
			palying.lordFlag = 2;
		}
		palying.lord.setLocation(point);
		palying.lord.setVisible(true);
	}

	/**
	 * �򿪳��ư�ť
	 * @param flag
	 */
	public void turnOn(boolean flag) {
		palying.publishCard[0].setVisible(flag);
		palying.publishCard[1].setVisible(flag);
	}

	/**
	 * ����0����(�Ҵ���1)
	 */
	public void computer0() {
		timeWait(1, 0); // ��ʱ
		ShowCard(0); // ����
		
	}

	/**
	 * ����2����(�Ҵ���1)
	 */
	public void computer2() {
		timeWait(1, 2); // ��ʱ
		ShowCard(2); // ����
		
	}

	/**
	 * ����
	 * @param role
	 */
	public void ShowCard(int role) {
		Model model = PlayUtil.getModel(palying.playerList[role]);
		// ���ߵ���
		List<String> list = new ArrayList();
		// �������������
		if (palying.time[(role + 1) % 3].getText().equals("��Ҫ")
				&& palying.time[(role + 2) % 3].getText().equals("��Ҫ")) {
			// �е����� (����3�����ɻ��ܴ��ĵ���)
			if (model.a1.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a1.get(model.a1.size() - 1));
			}// �ж��ӳ����� (����3�����ɻ�)
			else if (model.a2.size() > (model.a111222.size() * 2 + model.a3
					.size())) {
				list.add(model.a2.get(model.a2.size() - 1));
			}// ��˳�ӳ�˳��
			else if (model.a123.size() > 0) {
				list.add(model.a123.get(model.a123.size() - 1));
			}// ��3���ͳ�3����û�оͳ���3
			else if (model.a3.size() > 0) {
				// 3����,�ҷǹؼ�ʱ�̲��ܴ�����2
				if (model.a1.size() > 0) {
					list.add(model.a1.get(model.a1.size() - 1));
				}// 3����
				else if (model.a2.size() > 0) {
					list.add(model.a2.get(model.a2.size() - 1));
				}
				list.add(model.a3.get(model.a3.size() - 1));
			}// ��˫˳��˫˳
			else if (model.a112233.size() > 0) {
				list.add(model.a112233.get(model.a112233.size() - 1));
			}// �зɻ����ɻ�
			else if (model.a111222.size() > 0) {
				String name[] = model.a111222.get(0).split(",");
				// ����
				if (name.length / 3 <= model.a1.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a1.get(i));
				} else if (name.length / 3 <= model.a2.size())// ��˫
				{
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a2.get(i));
				}
				// ��ը����ը��
			} else if (model.a4.size() > 0) {
				// 4��2,1
				int sizea1 = model.a1.size();
				int sizea2 = model.a2.size();
				if (sizea1 >= 2) {
					list.add(model.a1.get(sizea1 - 1));
					list.add(model.a1.get(sizea1 - 2));
					list.add(model.a4.get(0));
				
				} else if (sizea2 >= 2) {
					list.add(model.a2.get(sizea1 - 1));
					list.add(model.a2.get(sizea1 - 2));
					list.add(model.a4.get(0));
					
				} else {// ֱ��ը
					list.add(model.a4.get(0));
					
				}

			}
		}// ����Ǹ���
		else {
			List<Card> player = palying.currentList[(role + 2) % 3].size() > 0 
					? palying.currentList[(role + 2) % 3]
					: palying.currentList[(role + 1) % 3];
			
			CardType cType=PlayUtil.jugdeType(player);
			//����ǵ���
			if(cType==CardType.c1)
			{
				AI_1(model.a1, player, list, role);
			}//����Ƕ���
			else if(cType==CardType.c2)
			{
				AI_1(model.a2, player, list, role);
			}//3��
			else if(cType==CardType.c3)
			{
				AI_1(model.a3, player, list, role);
			}//ը��
			else if(cType==CardType.c4)
			{
				AI_1(model.a4, player, list, role);
			}//�����3��1
			else if(cType==CardType.c31){
				 //ƫ�� �漰������
				//if((role+1)%3==main.dizhuFlag)
					AI_2(model.a3, model.a1, player, list, role);
			}//�����3��2
			else if(cType==CardType.c32){
				 //ƫ��
				//if((role+1)%3==main.dizhuFlag)
					AI_2(model.a3, model.a2, player, list, role);
			}//�����4��11
			else if(cType==CardType.c411){
					AI_5(model.a4, model.a1, player, list, role);
			}
			//�����4��22
			else if(cType==CardType.c422){
					AI_5(model.a4, model.a2, player, list, role);
			}
			//˳��
			else if(cType==CardType.c123){
				AI_3(model.a123, player, list, role);
			}
			//˫˳
			else if(cType==CardType.c1122){
				AI_3(model.a112233, player, list, role);
			}
			//�ɻ�����
			else if(cType==CardType.c11122234){
				AI_4(model.a111222,model.a1, player, list, role);
			}
			//�ɻ�����
			else if(cType==CardType.c1112223344){
				AI_4(model.a111222,model.a2, player, list, role);
			}
			//ը��
			if(list.size()==0)
			{
				int len4=model.a4.size();
				if(len4>0)
					list.add(model.a4.get(len4-1));
			}
		}

		/**
		 * ��λ����
		 */
		palying.currentList[role].clear();
		if (list.size() > 0) {
			Point point = new Point();
			if (role == 0)
				point.x = 200;
			if (role == 2)
				point.x = 550;
			point.y = (400 / 2) - (list.size() + 1) * 15 / 2;// ��Ļ�в�
			// ��nameת����Card
			for (int i = 0, len = list.size(); i < len; i++) {
				List<Card> cards = getCardByName(palying.playerList[role],
						list.get(i));
				for (Card card : cards) {
					PlayUtil.move(card, card.getLocation(), point);
					point.y += 15;
					palying.currentList[role].add(card);
					palying.playerList[role].remove(card);
				}
			}
			PlayUtil.rePosition(palying, palying.playerList[role], role);
		} else {
			palying.time[role].setVisible(true);
			palying.time[role].setText("��Ҫ");
		}
		for(Card card:palying.currentList[role])
			card.turnFront();
	}

	/**
	 * ��name���Card�������Modelȡ��
	 * @param list
	 * @param n
	 * @return
	 */
	public List getCardByName(List<Card> list, String n) {
		String[] name = n.split(",");
		List cardsList = new ArrayList<Card>();
		int j = 0;
		for (int i = 0, len = list.size(); i < len; i++) {
			if (j < name.length && list.get(i).name.equals(name[j])) {
				cardsList.add(list.get(i));
				i = 0;
				j++;
			}
		}
		return cardsList;
	}

	/**
	 * ˳��
	 * @param model
	 * @param player
	 * @param list
	 * @param role
	 */
	public void AI_3(List<String> model,List<Card> player,List<String> list,int role){
		
		for(int i=0,len=model.size();i<len;i++)
		{
			String []s=model.get(i).split(",");
			if(s.length==player.size()&&getValueInt(model.get(i))>PlayUtil.getValue(player.get(0)))
			{
				list.add(model.get(i));
				return;
			}
		}
	}

	/**
	 * �ɻ�������˫
	 * @param model1
	 * @param model2
	 * @param player
	 * @param list
	 * @param role
	 */
	public void AI_4(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role){
		//�����ظ���
		player=PlayUtil.getOrder2(player);
		int len1=model1.size();
		int len2=model2.size();
		
		if(len1<1 || len2<1)
			return;
		for(int i=0;i<len1;i++){
			String []s=model1.get(i).split(",");
			String []s2=model2.get(0).split(",");
			if((s.length/3<=len2)&&(s.length*(3+s2.length)==player.size())&&getValueInt(model1.get(i))>PlayUtil.getValue(player.get(0)))
			{
				list.add(model1.get(i));
				for(int j=1;j<=s.length/3;j++)
					list.add(model2.get(len2-j));
			}
		}
	}

	/**
	 * 4��1��2
	 * @param model1
	 * @param model2
	 * @param player
	 * @param list
	 * @param role
	 */
	public void AI_5(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role){
		//�����ظ���
		player=PlayUtil.getOrder2(player);
		int len1=model1.size();
		int len2=model2.size();
		
		if(len1<1 || len2<2)
			return;
		for(int i=0;i<len1;i++){
			if(getValueInt(model1.get(i))>PlayUtil.getValue(player.get(0)))
			{
				list.add(model1.get(i));
				for(int j=1;j<=2;j++)
					list.add(model2.get(len2-j));
			}
		}
	}

	/**
	 * ���ƣ����ӣ�3����4��,ͨ��
	 * @param model
	 * @param player
	 * @param list
	 * @param role
	 */
	public void AI_1(List<String> model,List<Card> player,List<String> list,int role){
		//����
		if((role+1)%3==palying.lordFlag)
		{
			
			for(int i=0,len=model.size();i<len;i++)
			{
				if(getValueInt(model.get(i))>PlayUtil.getValue(player.get(0)))
				{
					list.add(model.get(i));
					break;
				}
			}
		}else {//ƫ��
			
			for(int len=model.size(),i=len-1;i>=0;i--)
			{
				if(getValueInt(model.get(i))>PlayUtil.getValue(player.get(0)))
				{
					list.add(model.get(i));
					break;
				}
			}
		}
	}

	/**
	 * 3��1,2,4��1,2
	 * @param model1
	 * @param model2
	 * @param player
	 * @param list
	 * @param role
	 */
	public void AI_2(List<String> model1,List<String> model2,List<Card> player,List<String> list,int role){
		//model1������,model2�Ǵ���,player����ҳ�����,,list��׼���ص���
		//�����ظ���
		player=PlayUtil.getOrder2(player);
		int len1=model1.size();
		int len2=model2.size();
		//�������ֱ��ը��
		if(len1>0&&model1.get(0).length()<10)
		{
			list.add(model1.get(0));
			System.out.println("��ը");
			return;
		}
		if(len1<1 || len2<1)
			return;
		for(int len=len1,i=len-1;i>=0;i--)
		{	
			if(getValueInt(model1.get(i))>PlayUtil.getValue(player.get(0)))
			{
				list.add(model1.get(i));
				break;
			}
		} 
		list.add(model2.get(len2-1));
		if(list.size()<2)
			list.clear();
	}

	/**
	 * ��ʱ��ģ��ʱ��
	 * @param n
	 * @param player
	 */
	public void timeWait(int n, int player) {

		if (palying.currentList[player].size() > 0)
			PlayUtil.hideCards(palying.currentList[player]);
		if (player == 1)// ������ң�10�뵽��ֱ����һ�ҳ���
		{
			int i = n;

			while (palying.nextPlayer == false && i >= 0) {
				// main.container.setComponentZOrder(main.time[player], 0);
				palying.time[player].setText("����ʱ:" + i);
				palying.time[player].setVisible(true);
				second(1);
				i--;
			}
			if (i == -1) {
				palying.time[player].setText("��ʱ");
			}
			palying.nextPlayer = false;
		} else {
			for (int i = n; i >= 0; i--) {
				second(1);
				// main.container.setComponentZOrder(main.time[player], 0);
				palying.time[player].setText("����ʱ:" + i);
				palying.time[player].setVisible(true);
			}
		}
		palying.time[player].setVisible(false);
	}

	/**
	 * ͨ��name��ֵ
	 * @param n
	 * @return
	 */
	public  int getValueInt(String n){
		String name[]=n.split(",");
		String s=name[0];
		int i=Integer.parseInt(s.substring(2, s.length()));
		if(s.substring(0, 1).equals("5"))
			i+=3;
		if(s.substring(2, s.length()).equals("1")||s.substring(2, s.length()).equals("2"))
			i+=13;
		return i;
	}

	/**
	 * �ж���Ӯ
	 * @return
	 */
	public boolean win(){
		String score;
		score = Playing.getScores();
		int pscore = Integer.valueOf(score);
		
		users = LoginAction.getUsername();
		scorePro = new Properties();
		scorefile = new File("Score.properties");		
		FileUtil.loadPro(scorePro, scorefile);		
		
		for(int i=0;i<3;i++){
			if(palying.playerList[i].size()==0)
			{
				String s;
				if(i==1)
				{
					s="��ϲ�㣬ʤ����!��ķ�����3";
					pscore += 3;					
					scorePro.setProperty(users, String.valueOf(pscore));
					try {
						scorePro.store(new FileOutputStream(scorefile), "plus 3 scores");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					s="���ź���������!��ķ�����3";
					pscore -= 3;
					scorePro.setProperty(users, String.valueOf(pscore));
					try {
						scorePro.store(new FileOutputStream(scorefile), "subtract 3 scores");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(palying, s);
				this.palying.container.removeAll();
				Runnable thread = new Runnable() {				
					@Override
					public void run() {
						// TODO Auto-generated method stub
						palying.InitGame();
					}
				};				
				Thread t = new Thread(thread);
				t.start();
				return true;
			}
		}
		return false;
	}

	/**
	 * ������get,set����
	 * @return
	 */
	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
}
