package multi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import main.Card;
import main.CardType;

/**
 * 多人模式游戏主界面Panel
 * @author huzhp
 *
 */
public class GamePanel extends JPanel{
	
	/**
	 * 生成57个图标
	 */
	private static ImageIcon imageIcons[] = new ImageIcon[57];public GamePanel() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 将图标与图片资源一一对应起来
	 */
	public void createRecouse() {
		
		imageIcons[1] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+3+".gif"));	
		imageIcons[2] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+3+".gif"));	
		imageIcons[3] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+3+".gif"));	
		imageIcons[4] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+3+".gif"));
		
		imageIcons[5] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+4+".gif"));	
		imageIcons[6] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+4+".gif"));	
		imageIcons[7] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+4+".gif"));	
		imageIcons[8] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+4+".gif"));	
		
		imageIcons[9] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+5+".gif"));		
		imageIcons[10] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+5+".gif"));	
		imageIcons[11] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+5+".gif"));	
		imageIcons[12] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+5+".gif"));
		
		imageIcons[13] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+6+".gif"));	
		imageIcons[14] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+6+".gif"));	
		imageIcons[15] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+6+".gif"));	
		imageIcons[16] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+6+".gif"));
		
		imageIcons[17] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+7+".gif"));	
		imageIcons[18] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+7+".gif"));	
		imageIcons[19] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+7+".gif"));	
		imageIcons[20] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+7+".gif"));
		
		imageIcons[21] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+8+".gif"));	
		imageIcons[22] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+8+".gif"));	
		imageIcons[23] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+8+".gif"));	
		imageIcons[24] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+8+".gif"));
		
		imageIcons[25] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+9+".gif"));	
		imageIcons[26] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+9+".gif"));	
		imageIcons[27] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+9+".gif"));	
		imageIcons[28] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+9+".gif"));	
		
		imageIcons[29] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+10+".gif"));	
		imageIcons[30] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+10+".gif"));	
		imageIcons[31] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+10+".gif"));	
		imageIcons[32] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+10+".gif"));
		
		imageIcons[33] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+11+".gif"));	
		imageIcons[34] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+11+".gif"));	
		imageIcons[35] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+11+".gif"));	
		imageIcons[36] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+11+".gif"));	
		
		imageIcons[37] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+12+".gif"));
		imageIcons[38] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+12+".gif"));	
		imageIcons[39] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+12+".gif"));	
		imageIcons[40] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+12+".gif"));	
		
		imageIcons[41] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+13+".gif"));	
		imageIcons[42] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+13+".gif"));	
		imageIcons[43] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+13+".gif"));	
		imageIcons[44] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+13+".gif"));	
		
		imageIcons[45] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+1+".gif"));	
		imageIcons[46] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+1+".gif"));	
		imageIcons[47] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+1+".gif"));	
		imageIcons[48] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+1+".gif"));	
		
		imageIcons[49] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 1 +"-"+2+".gif"));	
		imageIcons[50] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 2 +"-"+2+".gif"));	
		imageIcons[51] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 3 +"-"+2+".gif"));	
		imageIcons[52] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 4 +"-"+2+".gif"));	
		
		imageIcons[53] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 5 +"-"+1+".gif"));	
		imageIcons[54] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + 5 +"-"+2+".gif"));	
		imageIcons[55] = new ImageIcon(this.getClass().getClassLoader().getResource("images/dizhu.gif"));
		imageIcons[56] = new ImageIcon(this.getClass().getClassLoader().getResource("images/rear.gif"));
		imageIcons[0] = new ImageIcon(this.getClass().getClassLoader().getResource("images/pass.gif"));
//	int count = 1;
//		for (int i = 1; i <= 5; i++) {
//			for (int j = 1; j <= 13; j++) {
//				if((i == 5) && (j > 2))
//					break;
//				else{
//					
//					imageIconsTemp[count] = new ImageIcon(this.getClass().getClassLoader().getResource("images/" + i +"-"+j+".gif");	
//					count++;
//				}
//			}
//		}
//		
	}

	private int distant = 20;// 两张牌之间的间距
	private int height = 600;
	private int width = 800;
	private Boolean isMyTurn = false;// 是否轮到当前玩家出牌
	private Vector<Integer> lastpokerIDVector = new Vector<Integer>();// 底牌的ID
	private Vector<JLabel> lastPokerLabelVector = new Vector<JLabel>();// 显示底牌的标签
	private Vector<Poker> lastSendedPokerVector = new Vector<Poker>();// 上个玩家出的牌
	private JButton[] lordChooseButton = new JButton[2];// 选择地主的按钮
	private JLabel lordLabels[] = new JLabel[3];// 地主头像显示标签
	private Vector<Integer> mypokerIDVector = new Vector<Integer>();// 当前玩家牌的ID
	private Vector<PokerLabel> myPokerLabelVector = new Vector<PokerLabel>();// 显示当前玩家的牌的标签
	private Vector<JLabel> mySendPokerLabelVector = new Vector<JLabel>();// 当前玩家已出牌的显示标签
	private JButton notSendButton;// 不出按钮
	private Integer notSendNum = 2;// 放弃出牌的玩家的个数
	private ObjectOutputStream oos;
	private Vector<JLabel> otherSendPokerLabelVector = new Vector<JLabel>();// 其他玩家已出牌的显示标签
	private Vector<Integer> player1pokerIDVector = new Vector<Integer>();// 玩家一（左边）的牌的ID
	private Vector<JLabel> player1PokerLabelVector = new Vector<JLabel>();// 显示玩家一（左边）得牌的标签
	private Vector<Integer> player2pokerIDVector = new Vector<Integer>();// 玩家二（右边）的牌的ID
	private Vector<JLabel> player2PokerLabelVector = new Vector<JLabel>();// 显示玩家二（右边）的牌的标签
	private String[] playernames = new String[3];// 玩家的名字
	// private Operations op;// 操作类，处理信息的发送
	private int position;// 记录当前玩家在游戏中的位置
	private JButton sendButton;// 出牌按钮
	private Timer timer;// 计时器，用于控制发牌时的动画

	/**
	 * GamePanel构造方法
	 * @param width
	 * @param height
	 */
	public GamePanel(int width, int height) {
		super();
		this.createRecouse();
		//设置画布的长和宽
		this.width = width;
		this.height = height;
		this.distant = width / 45;
		this.playernames[0] = "";
		this.playernames[1] = "";
		this.playernames[2] = "";
		initGUI();
	}
	
	/**
	 * 选择地主按钮
	 */
	public void addChoiceLord() {
		final String[] text = { "叫地主", "不叫" };

		for (int i = 0; i < 2; i++) {
			lordChooseButton[i] = new JButton(text[i]);
			lordChooseButton[i].setVisible(false);
			this.add(lordChooseButton[i]);
			
			lordChooseButton[i].setBounds((width * 7 / 16)+i*(int)(width*0.1), (int) (height * (0.65)),
					(int) (width * 0.09), (int) (height * 0.05));
			
//			lordChooseButton[i].setBounds((int) (width * 0.38) + i
//					* (int) (width * 0.1), (int) (height * 0.65),
//					(int) (width * 0.095), (int) (height * 0.052));

		}
		this.addChoiceLordEvent();
	}

	/**
	 * 地主选择按钮事件
	 */
	public void addChoiceLordEvent() {
		lordChooseButton[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					oos.writeObject("call");
					showLordChoose(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "通信错误");
				}

			}
		});
		lordChooseButton[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					oos.writeObject("notcall");
					showLordChoose(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "通信错误");
				}

			}
		});
	}

	/**
	 * 增加底牌的标签
	 */
	public void addLastPokerLabel() {
		for (int i = 0; i < 3; i++) {
			JLabel label1 = new JLabel();
			label1.setIcon(imageIcons[56]);
			label1.setLocation((int) (width * 0.38) + 75 * i, (height * 0));
			label1.setSize(71, 96);
			label1.setVisible(false);
			//增加底牌的标签
			this.lastPokerLabelVector.add(label1);
			this.add(label1);
		}
	}

	/**
	 * 增加地主标签
	 */
	public void addLordlabel() {
		this.lordLabels[0] = new JLabel(imageIcons[55]);
		this.add(this.lordLabels[0]);
		this.lordLabels[0].setBounds((int) (width * 0.38),
				(int) (height * 0.92), (int) (width * 0.053),
				(int) (height * 0.069));
		this.lordLabels[0].setVisible(false);
		
		this.lordLabels[1] = new JLabel(imageIcons[55]);
		this.add(this.lordLabels[1]);
		this.lordLabels[1].setBounds((int) (width * 0.011),
				(int) (height * 0.521), (int) (width * 0.053),
				(int) (height * 0.069));
		this.lordLabels[1].setVisible(false);

		this.lordLabels[2] = new JLabel(imageIcons[55]);
		this.add(this.lordLabels[2]);
		this.lordLabels[2].setBounds((int) (width * 0.916),
				(int) (height * 0.521), (int) (width * 0.053),
				(int) (height * 0.069));
		this.lordLabels[2].setVisible(false);
	}

	/**
	 * 添加当前玩家的扑克标签
	 */
	public void addMyPokerLabel() {
		for (int i = 19; i >= 0; i--) {
			PokerLabel p = new PokerLabel();
			//先设置扑克标签不可见
			p.setVisible(false);
			//设置扑克标签的位置
			p.setLocation((int) (width * 0.25) + i * distant,
					(int) (height * 0.75));
			// 显示当前玩家的牌的标签
			this.myPokerLabelVector.add(p);
			this.add(p);
		}
	}
	
	/**
	 * 出牌按钮
	 */
	public void addMySendedPokerLabel() {
		for (int i = 19; i >= 0; i--) {
			JLabel p = new JLabel();
			p.setSize(71, 96);
			p.setVisible(false);
			this.add(p);
			p.setLocation((int) (width * 0.2 + i * distant),
					(int) (height * 0.5));
			this.mySendPokerLabelVector.add(p);

		}
	}

	/**
	 *  放弃出牌操作，以及信息提示
	 */
	public void addNotSendButton() {
		this.notSendButton = new JButton();
		notSendButton.setText("不出");
		notSendButton.setVisible(false);
		notSendButton.setBounds((width * 9 / 16), (int) (height * (0.65)),
				(int) (width * 0.08), (int) (height * 0.05));
		this.add(notSendButton);
		notSendButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (isMyTurn) {
					try {

						if (notSendNum == 2) {
							JOptionPane.showMessageDialog(null, "你必须出牌");
							lastSendedPokerVector.clear();

						} else {

							oos.writeObject("not send");
							notSendNum++;
							oos.writeObject(position);
							isMyTurn = false;
							Vector<Integer> send = new Vector<Integer>();
							send.add(0);
							oos.writeObject(0);

							showMysendPoker(send);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "还没轮到你出牌");
				}
			}
		});
	}

	/**
	 * 其他人出牌按钮
	 */
	public void addOtherSendedPokerLabel() {
		for (int i = 23; i >= 0; i--) {
			JLabel p = new JLabel();
			p.setSize(71, 96);
			p.setVisible(false);
			this.add(p);
			p.setLocation((int) (width * 0.21 + i * distant),
					(int) (height * 0.25));
			this.otherSendPokerLabelVector.add(p);
		}
	}

	/**
	 * 增加另外两个玩家的扑克的标签
	 */
	public void addOthersPokerLabel() {
		for (int i = 19; i >= 0; i--) {
			JLabel label1 = new JLabel();
			//对于该玩家来说，另外两个玩家的扑克是不能让该玩家看到的，故使用扑克的背面显示
			label1.setIcon(imageIcons[56]);
			label1.setLocation((int) (width * 0.12), (int) (height * 0.125)
					+ 15 * i);
			label1.setSize(71, 96);
			label1.setVisible(false);
			JLabel label2 = new JLabel();
			label2.setIcon(imageIcons[56]);
			label2.setLocation((int) (width * 0.81), (int) (height * 0.125)
					+ 15 * i);
			label2.setSize(71, 96);
			label2.setVisible(false);
			this.player1PokerLabelVector.add(label1);
			this.player2PokerLabelVector.add(label2);
			this.add(label1);
			this.add(label2);
		}
	}

	/**
	 * 增加“出牌”按钮
	 */
	public void addSendButton() {
		// 出牌操作，以及信息提示
		sendButton = new JButton();
		sendButton.setText("出牌");
		sendButton.setVisible(false);
		sendButton.setBounds((width * 7 / 16), (int) (height * (0.65)),
				(int) (width * 0.08), (int) (height * 0.05));
		this.add(sendButton);
		sendButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					Vector<Integer> pokerIDVector = new Vector<Integer>();
					Vector<Poker> sendPokerVector = new Vector<Poker>();
					if (isMyTurn) {
						for (int i = mypokerIDVector.size() - 1; i >= 0; i--) {
							PokerLabel pokerLabel = myPokerLabelVector.get(i);
							//如果该扑克标签被选中
							if (pokerLabel.getClicked()) {
								/*
								 * public MouseEvent(Component source, int id, long when, int modifiers,
								 * int x, int y, int clickCount, boolean popupTrigger)
								 * 构造一个具有指定源组件、类型、修饰符、坐标和单击计数的 MouseEvent 对象。
								 * 注意，传入无效 id 将导致不确定的行为。调用形式为
								 * MouseEvent(source, id, when, modifiers, x, y,
								 * clickCount, popupTrigger) 的方法所产生的效果与调用
								 * MouseEvent(source, id, when, modifiers, x, y,
								 * xAbs, yAbs, clickCount, popupTrigger,
								 * MouseEvent.NOBUTTON) 完全相同，其中 xAbs 和 yAbs
								 * 定义为屏幕上原点的位置加上相对坐标 x 和 y。如果没有显示原点，则 xAbs 和
								 * yAbs 被设置为零。如果 source 为 null，则此方法将抛出
								 * IllegalArgumentException。
								 * 参数：
								 *  source - 产生该事件的 Component 
								 *  id - 标识事件的整数
								 * when - 给出事件发生时间的 long int 值 
								 * modifiers - 发生事件期间按下的修改键（例如 shift、ctrl、alt 和 meta)，应该使用扩展的 _DOWN_MASK 修饰符或旧的 _MASK
								 * 修饰符，但是在一个事件中不应混用两种模型。首选扩展的修饰符。
								 * x - 鼠标位置的水平 x 坐标
								 * y - 鼠标位置的垂直 y 坐标
								 * clickCount - 与该事件有关的鼠标单击次数
								 * popupTrigger - 一个 boolean值，如此事件是某个弹出菜单的触发器，则该参数为 true
								 */
                            	MouseEvent e = new MouseEvent(pokerLabel,MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,0, false);
                            	//为组件或其子组件之一指派事件。
								pokerLabel.dispatchEvent(e);
								pokerIDVector.add(pokerLabel.getPokerID());
								//以扑克的ID来创建一个扑克，并添加到sendPokerVector中
								sendPokerVector.add(new Poker(pokerLabel
										.getPokerID()));

							}

						}
						if (pokerIDVector.size() == 0) {
							JOptionPane.showMessageDialog(null, "请选择你要出的牌");
						} 
						else if (PokerRules.canSend(lastSendedPokerVector,
								sendPokerVector)) {

							mypokerIDVector.removeAll(pokerIDVector);
							lastSendedPokerVector = sendPokerVector;
							showMysendPoker(pokerIDVector);
							display();
							notSendNum = 0;
							isMyTurn = false;
								if (mypokerIDVector.size() == 0) {
									oos.writeObject("win");
									oos.writeObject(position);
								   }
								oos.writeObject("sendedPoker");
								oos.writeObject(position);
								oos.writeObject(pokerIDVector);

						   } 
						   else {
							      JOptionPane.showMessageDialog(null, "请按规则出牌");
						   }
					 }
					else {
						JOptionPane.showMessageDialog(null, "还没轮到你出牌");
					  }
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "通信错误");
				}
			}
		});
	}

	/**
	 * 每次出牌之后的界面刷新
	 */
	public void display() {
		for (int i = 19; i >= 0; i--) {
			if (i <= mypokerIDVector.size() - 1) {
				int id = mypokerIDVector.get(i);
				PokerLabel pokerLabel = this.myPokerLabelVector.get(i);
				pokerLabel.setPokerID(id);
				pokerLabel.setVisible(true);
				pokerLabel.setIcon(imageIcons[id]);
			} else {
				this.myPokerLabelVector.get(i).setVisible(false);
			}
		}
		for (int i = 19; i >= 0; i--) {
			if (i <= player1pokerIDVector.size() - 1) {
				JLabel label = this.player1PokerLabelVector.get(i);
				label.setVisible(true);
			} else {
				this.player1PokerLabelVector.get(i).setVisible(false);
			}
		}
		for (int i = 19; i >= 0; i--) {
			if (i <= player2pokerIDVector.size() - 1) {
				JLabel label = this.player2PokerLabelVector.get(i);
				label.setVisible(true);
			} else {
				this.player2PokerLabelVector.get(i).setVisible(false);
			}
		}

	}

	public Vector<Integer> getLastpokerIDVector() {
		return lastpokerIDVector;
	}

	public Vector<Poker> getLastSendedPokerVector() {
		return lastSendedPokerVector;
	}

	public JLabel[] getLordLabels() {
		return lordLabels;
	}

	public Vector<Integer> getMypokerIDVector() {
		return mypokerIDVector;
	}

	public Integer getNotSendNum() {
		return notSendNum;
	}

	public Vector<Integer> getPlayer1pokerIDVector() {
		return player1pokerIDVector;
	}

	public Vector<Integer> getPlayer2pokerIDVector() {
		return player2pokerIDVector;
	}

	/**
	 *  隐藏当前玩家出的牌
	 */
	public void hideMysendPoker() {
		for (int i = 0; i < 20; i++) {
			this.mySendPokerLabelVector.get(i).setVisible(false);
		}
	}

	/**
	 * 隐藏下一玩家上次出的牌
	 */
	public void hideNextSendPoker() {
		for (int i = 0; i < 12; i++) {
			this.otherSendPokerLabelVector.get(i).setVisible(false);
		}
	}

	/**
	 *  隐藏其他组件
	 */
	public void hideOtherLabel() {
		for (int i = 0; i < 3; i++) {
			this.lordLabels[i].setVisible(false);

			this.lastPokerLabelVector.get(i).setVisible(false);
			this.lastPokerLabelVector.get(i).setIcon(
					new ImageIcon("res/cards/skin2.jpg"));
		}
	}

	/**
	 *  隐藏所有玩家牌的的标签
	 */
	public void hidePokerLabel() {
		for (int i = 0; i < 20; i++) {
			this.myPokerLabelVector.get(i).setVisible(false);
			this.player1PokerLabelVector.get(i).setVisible(false);
			this.player2PokerLabelVector.get(i).setVisible(false);
		}
		for (int i = 19; i >= 0; i--) {

			this.myPokerLabelVector.get(i).setVisible(false);

		}
		for (int i = 0; i < 24; i++) {
			this.otherSendPokerLabelVector.get(i).setVisible(false);
		}
	}

	public void hidePreSendPoker() {

		for (int i = 23; i >= 12; i--) {
			this.otherSendPokerLabelVector.get(i).setVisible(false);
		}
	}

	/**
	 *  初始化，并添加各个功能模块
	 */
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout(this);
			this.setLayout(thisLayout);
			thisLayout.setHorizontalGroup(thisLayout.createParallelGroup());
			thisLayout.setVerticalGroup(thisLayout.createParallelGroup());
			setPreferredSize(new Dimension(width, height));
			{
				this.addMyPokerLabel();
				this.addOthersPokerLabel();
				this.addLastPokerLabel();
				this.addSendButton();
				this.addChoiceLord();
				this.addNotSendButton();
				this.addMySendedPokerLabel();
				this.addOtherSendedPokerLabel();
				this.addLordlabel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(new Color(44, 114, 104));
		g.setFont(new java.awt.Font("华文隶书", 1, 20));
		g
				.drawString(playernames[0], (int) (width * 0.5),
						(int) (height * 0.95));
		g
				.drawString(playernames[1], (int) (width * 0.005),
						(int) (height * 0.5));
		g.drawString(playernames[2], (int) (width * 0.9), (int) (height * 0.5));

	}

	// private void sendPoker(Vector<Integer> pokerID) throws Exception {

	// }

	public void setIsMyTurn(Boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}

	public void setLastpokerIDVector(Vector<Integer> lastpokerIDVector) {
		this.lastpokerIDVector = lastpokerIDVector;
	}

	public void setLastSendedPokerVector(Vector<Poker> lastSendedPokerVector) {
		this.lastSendedPokerVector = lastSendedPokerVector;
	}

	public void setMypokerIDVector(Vector<Integer> mypokerIDVector) {
		this.mypokerIDVector = mypokerIDVector;
	}

	public void setNotSendNum(Integer notSendNum) {
		this.notSendNum = notSendNum;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	public void setPlayer1pokerIDVector(Vector<Integer> player1pokerIDVector) {
		this.player1pokerIDVector = player1pokerIDVector;
	}

	public void setPlayer2pokerIDVector(Vector<Integer> player2pokerIDVector) {
		this.player2pokerIDVector = player2pokerIDVector;
	}

	public void setPlayernames(String[] playernames) {
		this.playernames = playernames;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 *  显示底牌
	 */
	public void showLastPoker() {
		for (int i = 0; i < 3; i++) {
			lastPokerLabelVector.get(i).setVisible(true);
			lastPokerLabelVector.get(i).setIcon(
					imageIcons[lastpokerIDVector.get(i)]);
		}
	}

	/**
	 *  显示选择地主按钮
	 * @param show
	 */
	public void showLordChoose(Boolean show) {
		this.lordChooseButton[0].setVisible(show);
		this.lordChooseButton[1].setVisible(show);
	}

	/**
	 *  显示当前玩家出的牌
	 * @param pokerIDVector
	 */
	public void showMysendPoker(Vector<Integer> pokerIDVector) {
		this.hideNextSendPoker();
		int size = 10 - pokerIDVector.size() / 2;
		for (int i = size, j = 0; j < pokerIDVector.size(); i++, j++) {
			this.mySendPokerLabelVector.get(i).setIcon(
					imageIcons[pokerIDVector.get(j)]);
			this.mySendPokerLabelVector.get(i).setVisible(true);
		}

	}

	/**
	 *  显示下一玩家出的牌
	 * @param poker
	 */
	public void showNextSendPoker(Vector<Poker> poker) {
		this.hidePreSendPoker();
		for (int i = 0; i < poker.size(); i++) {
			this.otherSendPokerLabelVector.get(i).setIcon(
					imageIcons[poker.get(i).getId()]);
			this.otherSendPokerLabelVector.get(i).setVisible(true);
		}
	}

	/**
	 *  显示其他玩家出的牌
	 * @param poker
	 * @param sender
	 */
	public void showOtherSendPoker(Vector<Poker> poker, int sender) {
		if (sender == 1) {
			this.showPreSendPoker(poker);
		} else {
			this.showNextSendPoker(poker);
		}

	}

	/**
	 *  显示上一玩家出的牌
	 * @param poker
	 */
	public void showPreSendPoker(Vector<Poker> poker) {
		this.hideMysendPoker();
		for (int i = 0; i < poker.size(); i++) {
			this.otherSendPokerLabelVector.get(23 - i).setIcon(
					imageIcons[poker.get(i).getId()]);
			this.otherSendPokerLabelVector.get(23 - i).setVisible(true);

		}

	}

	/**
	 *  显示出牌及不出按钮
	 * @param show
	 */
	public void showsSendButton(Boolean show) {
		sendButton.setVisible(show);
		notSendButton.setVisible(show);
	}

	/**
	 *  完成发牌的动画过程
	 */
	public void start() {

		timer = new Timer(245, new ActionListener() {
			int count = 0;

			public void actionPerformed(ActionEvent evt) {
				if (count < mypokerIDVector.size()) {
					myPokerLabelVector.get(count).setIcon(
							imageIcons[mypokerIDVector.get(count)]);
					myPokerLabelVector.get(count).setVisible(true);
				}
				if (count < player1pokerIDVector.size()) {
					player1PokerLabelVector.get(count).setVisible(true);
				}
				if (count < player2pokerIDVector.size()) {
					player2PokerLabelVector.get(count).setVisible(true);
				}
				if (count == 22) {
					for (int i = 0; i < 3; i++) {
						lastPokerLabelVector.get(i).setVisible(true);
					}

					timer.stop();
				}
				count++;
			}
		});
		timer.start();
	}

	/**
	 * @return the imageIcons
	 */
	public static ImageIcon[] getImageIcons() {
		return imageIcons;
	}

	/**
	 * @param imageIcons the imageIcons to set
	 */
	public static void setImageIcons(ImageIcon[] imageIcons) {
		GamePanel.imageIcons = imageIcons;
	}

	/**
	 * @return the distant
	 */
	public int getDistant() {
		return distant;
	}

	/**
	 * @param distant the distant to set
	 */
	public void setDistant(int distant) {
		this.distant = distant;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the lastPokerLabelVector
	 */
	public Vector<JLabel> getLastPokerLabelVector() {
		return lastPokerLabelVector;
	}

	/**
	 * @param lastPokerLabelVector the lastPokerLabelVector to set
	 */
	public void setLastPokerLabelVector(Vector<JLabel> lastPokerLabelVector) {
		this.lastPokerLabelVector = lastPokerLabelVector;
	}

	/**
	 * @return the lordChooseButton
	 */
	public JButton[] getLordChooseButton() {
		return lordChooseButton;
	}

	/**
	 * @param lordChooseButton the lordChooseButton to set
	 */
	public void setLordChooseButton(JButton[] lordChooseButton) {
		this.lordChooseButton = lordChooseButton;
	}

	/**
	 * @return the myPokerLabelVector
	 */
	public Vector<PokerLabel> getMyPokerLabelVector() {
		return myPokerLabelVector;
	}

	/**
	 * @param myPokerLabelVector the myPokerLabelVector to set
	 */
	public void setMyPokerLabelVector(Vector<PokerLabel> myPokerLabelVector) {
		this.myPokerLabelVector = myPokerLabelVector;
	}

	/**
	 * @return the mySendPokerLabelVector
	 */
	public Vector<JLabel> getMySendPokerLabelVector() {
		return mySendPokerLabelVector;
	}

	/**
	 * @param mySendPokerLabelVector the mySendPokerLabelVector to set
	 */
	public void setMySendPokerLabelVector(Vector<JLabel> mySendPokerLabelVector) {
		this.mySendPokerLabelVector = mySendPokerLabelVector;
	}

	/**
	 * @return the notSendButton
	 */
	public JButton getNotSendButton() {
		return notSendButton;
	}

	/**
	 * @param notSendButton the notSendButton to set
	 */
	public void setNotSendButton(JButton notSendButton) {
		this.notSendButton = notSendButton;
	}

	/**
	 * @return the otherSendPokerLabelVector
	 */
	public Vector<JLabel> getOtherSendPokerLabelVector() {
		return otherSendPokerLabelVector;
	}

	/**
	 * @param otherSendPokerLabelVector the otherSendPokerLabelVector to set
	 */
	public void setOtherSendPokerLabelVector(
			Vector<JLabel> otherSendPokerLabelVector) {
		this.otherSendPokerLabelVector = otherSendPokerLabelVector;
	}

	/**
	 * @return the player1PokerLabelVector
	 */
	public Vector<JLabel> getPlayer1PokerLabelVector() {
		return player1PokerLabelVector;
	}

	/**
	 * @param player1PokerLabelVector the player1PokerLabelVector to set
	 */
	public void setPlayer1PokerLabelVector(Vector<JLabel> player1PokerLabelVector) {
		this.player1PokerLabelVector = player1PokerLabelVector;
	}

	/**
	 * @return the player2PokerLabelVector
	 */
	public Vector<JLabel> getPlayer2PokerLabelVector() {
		return player2PokerLabelVector;
	}

	/**
	 * @param player2PokerLabelVector the player2PokerLabelVector to set
	 */
	public void setPlayer2PokerLabelVector(Vector<JLabel> player2PokerLabelVector) {
		this.player2PokerLabelVector = player2PokerLabelVector;
	}

	/**
	 * @return the sendButton
	 */
	public JButton getSendButton() {
		return sendButton;
	}

	/**
	 * @param sendButton the sendButton to set
	 */
	public void setSendButton(JButton sendButton) {
		this.sendButton = sendButton;
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * @return the isMyTurn
	 */
	public Boolean getIsMyTurn() {
		return isMyTurn;
	}

	/**
	 * @return the oos
	 */
	public ObjectOutputStream getOos() {
		return oos;
	}

	/**
	 * @return the playernames
	 */
	public String[] getPlayernames() {
		return playernames;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param lordLabels the lordLabels to set
	 */
	public void setLordLabels(JLabel[] lordLabels) {
		this.lordLabels = lordLabels;
	}
	
}
