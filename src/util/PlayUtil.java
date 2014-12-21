package util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Main.Card;
import Main.CardType;
import Main.Model;
import play.Playing;

/**
 * ��Ϸʱ���õķ���
 *
 */
public class PlayUtil {
	
   		/**
		 * �ж�����
		 * @param list
		 * @return
		 */
		public static CardType jugdeType(List<Card> list){
			//��Ϊ֮ǰ��������ԱȽϺ��ж�
			int len=list.size();
			//����,���ӣ�3������4��һ��ը��
			if(len<=4)
			{	//�����һ����������ͬ��˵��ȫ����ͬ
				if(list.size()>0&&PlayUtil.getValue(list.get(0))==PlayUtil.getValue(list.get(len-1)))
				{
					switch (len) {
					case 1:
						return CardType.c1;
					case 2:
						return CardType.c2;
					case 3:
						return CardType.c3;
					case 4:
						return CardType.c4;
					}
				}
				//˫��,��Ϊ���ӷ���
				if(len==2&&PlayUtil.getColor(list.get(1))==5)
					return CardType.c2;
				//����һ����������ͬʱ,3��1
				if(len==4 &&((PlayUtil.getValue(list.get(0))==PlayUtil.getValue(list.get(len-2)))||
						PlayUtil.getValue(list.get(1))==PlayUtil.getValue(list.get(len-1))))
					return CardType.c31;
				else {
					return CardType.c0;
				}
			}
			//��5������ʱ�����֣�3��2���ɻ���2˳��4��2�ȵ�
			if(len>=5)
			{//���ڰ���ͬ���������ִ���
				Card_index card_index=new Card_index();
				for(int i=0;i<4;i++)
					card_index.a[i]=new ArrayList<Integer>();
				//����������ֳ���Ƶ��
				PlayUtil.getMax( card_index,list); //a[0,1,2,3]�ֱ��ʾ�ظ�1,2,3,4�ε���
				//3��2 -----�غ��ظ�3�ε���
				if(card_index.a[2].size()==1 &&card_index.a[1].size()==1 && len==5)
					return CardType.c32;
				//4��2(��,˫)
				if(card_index.a[3].size()==1 && len==6)
					return CardType.c411;
				if(card_index.a[3].size()==1 && card_index.a[1].size()==2 &&len==8)
					return CardType.c422;
				//����,��֤��������
				if((PlayUtil.getColor(list.get(0))!=5)&&(card_index.a[0].size()==len) &&
						(PlayUtil.getValue(list.get(0))-PlayUtil.getValue(list.get(len-1))==len-1))
					return CardType.c123;
				//����
				if(card_index.a[1].size()==len/2 && len%2==0 && len/2>=3
						&&(PlayUtil.getValue(list.get(0))-PlayUtil.getValue(list.get(len-1))==(len/2-1)))
					return CardType.c1122;
				//�ɻ�
				if(card_index.a[2].size()==len/3 && (len%3==0) &&
						(PlayUtil.getValue(list.get(0))-PlayUtil.getValue(list.get(len-1))==(len/3-1)))
					return CardType.c111222;
				//�ɻ���n��,n/2��
				if(card_index.a[2].size()==len/4 &&
						((Integer)(card_index.a[2].get(len/4-1))-(Integer)(card_index.a[2].get(0))==len/4-1))
					return CardType.c11122234;					
				//�ɻ���n˫
				if(card_index.a[2].size()==len/5 && card_index.a[2].size()==len/5 &&
						((Integer)(card_index.a[2].get(len/5-1))-(Integer)(card_index.a[2].get(0))==len/5-1))
					return CardType.c1112223344;
					
			}
			return CardType.c0;
		}
		
		/**
		 * �ƶ�Ч�������ڷ���
		 * @param card
		 * @param from
		 * @param to
		 */
		public static void move(Card card,Point from,Point to){
			if(to.x!=from.x){
				double k=(1.0)*(to.y-from.y)/(to.x-from.x);
				double b=to.y-to.x*k;
				int flag=0;//�ж������������ƶ�����
				if(from.x<to.x)
					flag=20;
				else {
					flag=-20;
				}
				for(int i=from.x;Math.abs(i-to.x)>20;i+=flag)
				{
					double y=k*i+b;//������Ҫ�õ���ѧ�е����Ժ���
				
					card.setLocation(i,(int)y);
					try {
						Thread.sleep(5); //�ӳ٣����Լ�����
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//λ��У׼
			card.setLocation(to);
		}

		/**
		 * ��list����
		 * @param list
		 */
		public static void order(List<Card> list){
			Collections.sort(list,new Comparator<Card>() {
				@Override
				public int compare(Card o1, Card o2) {
					// TODO Auto-generated method stub
					int a1=Integer.parseInt(o1.name.substring(0, 1));//��ɫ
					int a2=Integer.parseInt(o2.name.substring(0,1));
					int b1=Integer.parseInt(o1.name.substring(2,o1.name.length()));//��ֵ
					int b2=Integer.parseInt(o2.name.substring(2,o2.name.length()));
					int flag=0;
					//��������Ļ�
					if(a1==5) b1+=100;
					if(a1==5&&b1==1) b1+=50;
					if(a2==5) b2+=100;
					if(a2==5&&b2==1) b2+=50;
					//�����A����2
					if(b1==1) b1+=20;
					if(b2==1) b2+=20;
					if(b1==2) b1+=30;
					if(b2==2) b2+=30;
					flag=b2-b1;
					if(flag==0)
						return a2-a1;
					else {
						return flag;
					}
				}
			});
		}
		
		/**
		 * ���¶�λ,flag�������1,2���������Լ�
		 * @param play
		 * @param list
		 * @param flag
		 */
		public static void rePosition(Playing play,List<Card> list,int flag){
			Point p=new Point();
			if(flag==0)
			{
				p.x=50;
				p.y=(450/2)-(list.size()+1)*15/2;
			}
			if(flag==1)
			{//�ҵ����� _y=450 width=830
				p.x=(800/2)-(list.size()+1)*21/2;
				p.y=450;
			}
			if(flag==2)
			{
				p.x=700;
				p.y=(450/2)-(list.size()+1)*15/2;
			}
			int len=list.size();
			for(int i=0;i<len;i++){
				Card card=list.get(i);
				PlayUtil.move(card, card.getLocation(), p);
				play.container.setComponentZOrder(card, 0);
				if(flag==1)p.x+=21;
				else p.y+=15;
				
			}
		}
		
		/**
		 * ������Ȩֵ���ж��Ƿ�������
		 * @param list
		 * @return
		 */
		public static int getScore(List<Card> list){
			int count=0;
			for(int i=0,len=list.size();i<len;i++){
				Card card=list.get(i);
				if(card.name.substring(0, 1).equals("5"))
				{
					//System.out.println(card.name.substring(0, 1));
					count+=5;
				}
				if(card.name.substring(2, card.name.length()).equals("2"))
				{
					//System.out.println(2);
					count+=2;
				}
			}
			return count;
			
		}
		
		/**
		 * ���ػ�ɫ
		 * @param card
		 * @return
		 */
		public static int getColor(Card card){
			return Integer.parseInt(card.name.substring(0,1));
		}

		/**
		 * ������ֵ
		 * @param card
		 * @return
		 */
		public static int getValue(Card card){
			int i= Integer.parseInt(card.name.substring(2,card.name.length()));
			if(card.name.substring(2,card.name.length()).equals("2"))
				i+=13;
			if(card.name.substring(2,card.name.length()).equals("1"))
				i+=13;
			if(PlayUtil.getColor(card)==5)
				i+=2;//����
			return i;
		}
		
		/**
		 * �õ������ͬ��
		 * @param card_index
		 * @param list
		 */
		public static void getMax(Card_index card_index,List<Card> list){
			int count[]=new int[14];//1-13����һ��,�����14��
			for(int i=0;i<14;i++)
				count[i]=0;
			for(int i=0,len=list.size();i<len;i++){
				if(PlayUtil.getColor(list.get(i))==5)
					count[13]++;
				else
					count[PlayUtil.getValue(list.get(i))-1]++;
			}
			for(int i=0;i<14;i++)
			{
				switch (count[i]) {
				case 1:
					card_index.a[0].add(i+1);
					break;
				case 2:
					card_index.a[1].add(i+1);
					break;
				case 3:
					card_index.a[2].add(i+1);
					break;
				case 4:
					card_index.a[3].add(i+1);
					break;
				}
			}
		}

		/**
		 * ����
		 * @param list
		 * @return
		 */
		public static Model getModel(List<Card> list){
			//�ȸ���һ��list
			List list2=new ArrayList<Card>(list);
			Model model=new Model();
			//------�Ȳ�ը��
			PlayUtil.getBoomb(list2, model); //ok
			//------��3��
			PlayUtil.getThree(list2, model);
			//��ɻ�
			PlayUtil.getPlane(list2, model);
			//------�����
			PlayUtil.getTwo(list2, model);
			//������
			PlayUtil.getTwoTwo(list2, model);
			//��˳��
			PlayUtil.get123(list2, model);
			//��
			PlayUtil.getSingle(list2, model);
			return model;
		}

		/**
		 * ������
		 * @param list
		 * @param model
		 */	
		public static void get123(List<Card> list,Model model){
			List<Card> del=new ArrayList<Card>();//Ҫɾ����Cards
			if(list.size()>0&&(PlayUtil.getValue(list.get(0))<7 ||PlayUtil.getValue(list.get(list.size()-1))>10))
				return;
			if(list.size()<5)
				return;
			for(int i=0,len=list.size();i<len;i++)
			{
				int k=i;
				for(int j=i;j<len;j++){
					if(PlayUtil.getValue(list.get(i))-PlayUtil.getValue(list.get(j))==j-i)
					{
						k=j;
					}
				}
				if(k-i>=4)
				{
					String s="";
					for(int j=i;j<k;j++)
					{
						s+=list.get(j).name+",";
						del.add(list.get(j));
					}
					s+=list.get(k).name;
					del.add(list.get(k));
					model.a123.add(s);
					i=k;
				}
			}
			list.removeAll(del);
		}

		/**
		 * ��˫˳
		 * @param list
		 * @param model
		 */
		public static void getTwoTwo(List<Card> list,Model model){
			List<String> del=new ArrayList<String>();//Ҫɾ����Cards
			//��model����Ķ�����
			List<String> l=model.a2;
			if(l.size()<3)
				return ;
			Integer s[]=new Integer[l.size()];
			for(int i=0,len=l.size();i<len;i++){
				String []name=l.get(i).split(",");
				s[i]=Integer.parseInt(name[0].substring(2,name[0].length()));
			}
			//s0,1,2,3,4  13,9,8,7,6
			for(int i=0,len=l.size();i<len;i++){
				int k=i;
				for(int j=i;j<len;j++)
				{
					if(s[i]-s[j]==j-i)
						k=j;
				}
				if(k-i>=2)//k=4 i=1
				{//˵����i��k������
					String ss="";
					for(int j=i;j<k;j++)
					{
						ss+=l.get(j)+",";
						del.add(l.get(j));
					}
					ss+=l.get(k);
					model.a112233.add(ss);
					del.add(l.get(k));
					i=k;
				}
			}
			l.removeAll(del);
		}

		/**
		 * ��ɻ�
		 * @param list
		 * @param model
		 */
		public static void getPlane(List<Card> list,Model model){
			List<String> del=new ArrayList<String>();//Ҫɾ����Cards
			//��model�����3����
			List<String> l=model.a3;
			if(l.size()<2)
				return ;
			Integer s[]=new Integer[l.size()];
			for(int i=0,len=l.size();i<len;i++){
				String []name=l.get(i).split(",");
				s[i]=Integer.parseInt(name[0].substring(2,name[0].length()));
			}
			for(int i=0,len=l.size();i<len;i++){
				int k=i;
				for(int j=i;j<len;j++)
				{
					if(s[i]-s[j]==j-i)
						k=j;
				}
				if(k!=i)
				{//˵����i��k�Ƿɻ�
					String ss="";
					for(int j=i;j<k;j++)
					{
						ss+=l.get(j)+",";
						del.add(l.get(j));
					}
					ss+=l.get(k);
					model.a111222.add(ss);
					del.add(l.get(k));
					i=k;
				}
			}
			l.removeAll(del);
		}
		
		/**
		 * ��ը��
		 * @param list
		 * @param model
		 */
		public static void getBoomb(List<Card> list,Model model){
			List<Card> del=new ArrayList<Card>();//Ҫɾ����Cards
			//��ը
			if(list.size()>=2 &&PlayUtil.getColor(list.get(0))==5 && PlayUtil.getColor(list.get(1))==5)
			{
				model.a4.add(list.get(0).name+","+list.get(1).name); //�����ּ���
				del.add(list.get(0));
				del.add(list.get(1));
			}
			//�����������ը��զ�Ȳ�
			if(PlayUtil.getColor(list.get(0))==5&&PlayUtil.getColor(list.get(1))!=5)
			{
				del.add(list.get(0));
				model.a1.add(list.get(0).name);
			}
			list.removeAll(del);
			//һ���ը��
			for(int i=0,len=list.size();i<len;i++){
				if(i+3<len && PlayUtil.getValue(list.get(i))==PlayUtil.getValue(list.get(i+3)))
				{
					String s=list.get(i).name+",";
					s+=list.get(i+1).name+",";
					s+=list.get(i+2).name+",";
					s+=list.get(i+3).name;
					model.a4.add(s);
					for(int j=i;j<=i+3;j++)
						del.add(list.get(j));
					i=i+3;
				}
			}
			list.removeAll(del);
		}
		
		/**
		 * ��3��
		 * @param list
		 * @param model
		 */
		public static void getThree(List<Card> list,Model model){
			List<Card> del=new ArrayList<Card>();//Ҫɾ����Cards
			//����3����ͬ
			for(int i=0,len=list.size();i<len;i++){
				if(i+2<len&&PlayUtil.getValue(list.get(i))==PlayUtil.getValue(list.get(i+2)))
				{
					String s=list.get(i).name+",";
					s+=list.get(i+1).name+",";
					s+=list.get(i+2).name;
					model.a3.add(s);
					for(int j=i;j<=i+2;j++)
						del.add(list.get(j));
					i=i+2;
				}
			}
			list.removeAll(del);
		}

		/**
		 * �����
		 * @param list
		 * @param model
		 */
		public static void getTwo(List<Card> list,Model model){
			List<Card> del=new ArrayList<Card>();//Ҫɾ����Cards
			//����2����ͬ
			for(int i=0,len=list.size();i<len;i++){
				if(i+1<len&&PlayUtil.getValue(list.get(i))==PlayUtil.getValue(list.get(i+1)))
				{
					String s=list.get(i).name+",";
					s+=list.get(i+1).name;
					model.a2.add(s);
					for(int j=i;j<=i+1;j++)
						del.add(list.get(j));
					i=i+1;
				}
			}
			list.removeAll(del);
		}

		/**
		 * ����
		 * @param list
		 * @param model
		 */
		public static void getSingle(List<Card> list,Model model){
			List<Card> del=new ArrayList<Card>();//Ҫɾ����Cards
			//1
			for(int i=0,len=list.size();i<len;i++){
					model.a1.add(list.get(i).name);
					del.add(list.get(i));
				}
			list.removeAll(del);
		}

		/**
		 * ����֮ǰ��������
		 * @param list
		 */
		public static void hideCards(List<Card> list){
			for(int i=0,len=list.size();i<len;i++){
				list.get(i).setVisible(false);
			}
		}

		/**
		 * ������Ƿ��ܳ�
		 * @param c
		 * @param current
		 * @return
		 */
		public static int checkCards(List<Card> c,List<Card>[] current){
			//�ҳ���ǰ���������ĸ����Գ���,c�ǵ�ѡ����
			List<Card> currentlist=(current[0].size()>0)?current[0]:current[2];
			CardType cType=PlayUtil.jugdeType(c);
			//���������ֱͬ�ӹ���
			if(cType!=CardType.c4&&c.size()!=currentlist.size())
				return 0;
			//�Ƚ��ҵĳ�������
			if(PlayUtil.jugdeType(c)!=PlayUtil.jugdeType(currentlist))
			{
				
				return 0;
			}
			//�Ƚϳ������Ƿ�Ҫ��
			//��ը��
			if(cType==CardType.c4)
			{
				if(c.size()==2)
					return 1;
				if(currentlist.size()==2)
					return 0;
			}
			//����,����,3��,4ը��
			if(cType==CardType.c1||cType==CardType.c2||cType==CardType.c3||cType==CardType.c4){
				if(PlayUtil.getValue(c.get(0))<=PlayUtil.getValue(currentlist.get(0)))
				{
					 return 0;
				}else {
					return 1;
				}
			}
			//˳��,���ӣ��ɻ���
			if(cType==CardType.c123||cType==CardType.c1122||cType==CardType.c111222)
			{
				if(PlayUtil.getValue(c.get(0))<=PlayUtil.getValue(currentlist.get(0)))
					return 0;
				else 
					return 1;
			}
			//���ظ���������
			//3��1,3��2 ,�ɻ�������˫,4��1,2,ֻ��Ƚϵ�һ�����У���һ�޶��� 
			if(cType==CardType.c31||cType==CardType.c32||cType==CardType.c411||cType==CardType.c422
					||cType==CardType.c11122234||cType==CardType.c1112223344){
				List<Card> a1=PlayUtil.getOrder2(c); //�ҳ�����
				List<Card> a2=PlayUtil.getOrder2(currentlist);//��ǰ�����
				if(PlayUtil.getValue(a1.get(0))<PlayUtil.getValue(a2.get(0)))
					return 0;
			}
			return 1;
		}

		/**
		 * �����ظ���������
		 * @param list
		 * @return
		 */
		public static List getOrder2(List<Card> list){
			List<Card> list2=new ArrayList<Card>(list);
			List<Card> list3=new ArrayList<Card>();
			List<Integer> list4=new ArrayList<Integer>();
			int len=list2.size();
			int a[]=new int[20];
			for(int i=0;i<20;i++)
				a[i]=0;
			for(int i=0;i<len;i++)
			{
				a[PlayUtil.getValue(list2.get(i))]++;
			}
			int max=0;
			for(int i=0;i<20;i++){
				max=0;
				for(int j=19;j>=0;j--){
					if(a[j]>a[max])
						max=j;
				}

				for(int k=0;k<len;k++){
					if(PlayUtil.getValue(list2.get(k))==max){
						list3.add(list2.get(k));
					}
				}
				list2.remove(list3);
				a[max]=0;
			}
			return list3;
		}
}
class Card_index{
		List a[]=new ArrayList[4];//����
}

