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

import Main.Card;
import Main.CardType;

public class GamePanel extends JPanel{
	
	private static ImageIcon imageIcons[] = new ImageIcon[57];
	static {
		imageIcons[1] = new ImageIcon("images/" + 1 +"-"+3+".gif");	
		imageIcons[2] = new ImageIcon("images/" + 2 +"-"+3+".gif");	
		imageIcons[3] = new ImageIcon("images/" + 3 +"-"+3+".gif");	
		imageIcons[4] = new ImageIcon("images/" + 4 +"-"+3+".gif");
		
		imageIcons[5] = new ImageIcon("images/" + 1 +"-"+4+".gif");	
		imageIcons[6] = new ImageIcon("images/" + 2 +"-"+4+".gif");	
		imageIcons[7] = new ImageIcon("images/" + 3 +"-"+4+".gif");	
		imageIcons[8] = new ImageIcon("images/" + 4 +"-"+4+".gif");	
		
		imageIcons[9] = new ImageIcon("images/" + 1 +"-"+5+".gif");		
		imageIcons[10] = new ImageIcon("images/" + 2 +"-"+5+".gif");	
		imageIcons[11] = new ImageIcon("images/" + 3 +"-"+5+".gif");	
		imageIcons[12] = new ImageIcon("images/" + 4 +"-"+5+".gif");
		
		imageIcons[13] = new ImageIcon("images/" + 1 +"-"+6+".gif");	
		imageIcons[14] = new ImageIcon("images/" + 2 +"-"+6+".gif");	
		imageIcons[15] = new ImageIcon("images/" + 3 +"-"+6+".gif");	
		imageIcons[16] = new ImageIcon("images/" + 4 +"-"+6+".gif");
		
		imageIcons[17] = new ImageIcon("images/" + 1 +"-"+7+".gif");	
		imageIcons[18] = new ImageIcon("images/" + 2 +"-"+7+".gif");	
		imageIcons[19] = new ImageIcon("images/" + 3 +"-"+7+".gif");	
		imageIcons[20] = new ImageIcon("images/" + 4 +"-"+7+".gif");
		
		imageIcons[21] = new ImageIcon("images/" + 1 +"-"+8+".gif");	
		imageIcons[22] = new ImageIcon("images/" + 2 +"-"+8+".gif");	
		imageIcons[23] = new ImageIcon("images/" + 3 +"-"+8+".gif");	
		imageIcons[24] = new ImageIcon("images/" + 4 +"-"+8+".gif");
		
		imageIcons[25] = new ImageIcon("images/" + 1 +"-"+9+".gif");	
		imageIcons[26] = new ImageIcon("images/" + 2 +"-"+9+".gif");	
		imageIcons[27] = new ImageIcon("images/" + 3 +"-"+9+".gif");	
		imageIcons[28] = new ImageIcon("images/" + 4 +"-"+9+".gif");	
		
		imageIcons[29] = new ImageIcon("images/" + 1 +"-"+10+".gif");	
		imageIcons[30] = new ImageIcon("images/" + 2 +"-"+10+".gif");	
		imageIcons[31] = new ImageIcon("images/" + 3 +"-"+10+".gif");	
		imageIcons[32] = new ImageIcon("images/" + 4 +"-"+10+".gif");
		
		imageIcons[33] = new ImageIcon("images/" + 1 +"-"+11+".gif");	
		imageIcons[34] = new ImageIcon("images/" + 2 +"-"+11+".gif");	
		imageIcons[35] = new ImageIcon("images/" + 3 +"-"+11+".gif");	
		imageIcons[36] = new ImageIcon("images/" + 4 +"-"+11+".gif");	
		
		imageIcons[37] = new ImageIcon("images/" + 1 +"-"+12+".gif");
		imageIcons[38] = new ImageIcon("images/" + 2 +"-"+12+".gif");	
		imageIcons[39] = new ImageIcon("images/" + 3 +"-"+12+".gif");	
		imageIcons[40] = new ImageIcon("images/" + 4 +"-"+12+".gif");	
		
		imageIcons[41] = new ImageIcon("images/" + 1 +"-"+13+".gif");	
		imageIcons[42] = new ImageIcon("images/" + 2 +"-"+13+".gif");	
		imageIcons[43] = new ImageIcon("images/" + 3 +"-"+13+".gif");	
		imageIcons[44] = new ImageIcon("images/" + 4 +"-"+13+".gif");	
		
		imageIcons[45] = new ImageIcon("images/" + 1 +"-"+1+".gif");	
		imageIcons[46] = new ImageIcon("images/" + 2 +"-"+1+".gif");	
		imageIcons[47] = new ImageIcon("images/" + 3 +"-"+1+".gif");	
		imageIcons[48] = new ImageIcon("images/" + 4 +"-"+1+".gif");	
		
		imageIcons[49] = new ImageIcon("images/" + 1 +"-"+2+".gif");	
		imageIcons[50] = new ImageIcon("images/" + 2 +"-"+2+".gif");	
		imageIcons[51] = new ImageIcon("images/" + 3 +"-"+2+".gif");	
		imageIcons[52] = new ImageIcon("images/" + 4 +"-"+2+".gif");	
		
		imageIcons[53] = new ImageIcon("images/" + 5 +"-"+1+".gif");	
		imageIcons[54] = new ImageIcon("images/" + 5 +"-"+2+".gif");	
		imageIcons[55] = new ImageIcon("images/dizhu.gif");
		imageIcons[56] = new ImageIcon("images/rear.gif");
		imageIcons[0] = new ImageIcon("images/pass.gif");
//	int count = 1;
//		for (int i = 1; i <= 5; i++) {
//			for (int j = 1; j <= 13; j++) {
//				if((i == 5) && (j > 2))
//					break;
//				else{
//					
//					imageIconsTemp[count] = new ImageIcon("images/" + i +"-"+j+".gif");	
//					count++;
//				}
//			}
//		}
//		
	}

	private int distant = 20;// ������֮��ļ��
	private int height = 600;
	private int width = 800;
	private Boolean isMyTurn = false;// �Ƿ��ֵ���ǰ��ҳ���
	private Vector<Integer> lastpokerIDVector = new Vector<Integer>();// ���Ƶ�ID
	private Vector<JLabel> lastPokerLabelVector = new Vector<JLabel>();// ��ʾ���Ƶı�ǩ
	private Vector<Poker> lastSendedPokerVector = new Vector<Poker>();// �ϸ���ҳ�����
	private JButton[] lordChooseButton = new JButton[2];// ѡ������İ�ť
	private JLabel lordLabels[] = new JLabel[3];// ����ͷ����ʾ��ǩ
	private Vector<Integer> mypokerIDVector = new Vector<Integer>();// ��ǰ����Ƶ�ID
	private Vector<PokerLabel> myPokerLabelVector = new Vector<PokerLabel>();// ��ʾ��ǰ��ҵ��Ƶı�ǩ
	private Vector<JLabel> mySendPokerLabelVector = new Vector<JLabel>();// ��ǰ����ѳ��Ƶ���ʾ��ǩ
	private JButton notSendButton;// ������ť
	private Integer notSendNum = 2;// �������Ƶ���ҵĸ���
	private ObjectOutputStream oos;
	private Vector<JLabel> otherSendPokerLabelVector = new Vector<JLabel>();// ��������ѳ��Ƶ���ʾ��ǩ
	private Vector<Integer> player1pokerIDVector = new Vector<Integer>();// ���һ����ߣ����Ƶ�ID
	private Vector<JLabel> player1PokerLabelVector = new Vector<JLabel>();// ��ʾ���һ����ߣ����Ƶı�ǩ
	private Vector<Integer> player2pokerIDVector = new Vector<Integer>();// ��Ҷ����ұߣ����Ƶ�ID
	private Vector<JLabel> player2PokerLabelVector = new Vector<JLabel>();// ��ʾ��Ҷ����ұߣ����Ƶı�ǩ
	private String[] playernames = new String[3];// ��ҵ�����
	// private Operations op;// �����࣬������Ϣ�ķ���
	private int position;// ��¼��ǰ�������Ϸ�е�λ��
	private JButton sendButton;// ���ư�ť
	private Timer timer;// ��ʱ�������ڿ��Ʒ���ʱ�Ķ���

	public GamePanel(int width, int height) {
		super();
		//���û����ĳ��Ϳ�
		this.width = width;
		this.height = height;
		this.distant = width / 45;
		this.playernames[0] = "";
		this.playernames[1] = "";
		this.playernames[2] = "";
		initGUI();
	}

	public void addChoiceLord() {
		final String[] text = { "�е���", "����" };

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

	public void addChoiceLordEvent() {
		lordChooseButton[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					oos.writeObject("call");
					showLordChoose(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ͨ�Ŵ���");
				}

			}
		});
		lordChooseButton[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				try {
					oos.writeObject("notcall");
					showLordChoose(false);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ͨ�Ŵ���");
				}

			}
		});
	}

	//���ӵ��Ƶı�ǩ
	public void addLastPokerLabel() {
		for (int i = 0; i < 3; i++) {
			JLabel label1 = new JLabel();
			label1.setIcon(imageIcons[56]);
			label1.setLocation((int) (width * 0.38) + 75 * i, (height * 0));
			label1.setSize(71, 96);
			label1.setVisible(false);
			//���ӵ��Ƶı�ǩ
			this.lastPokerLabelVector.add(label1);
			this.add(label1);
		}
	}

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

	//��ӵ�ǰ��ҵ��˿˱�ǩ
	public void addMyPokerLabel() {
		for (int i = 19; i >= 0; i--) {
			PokerLabel p = new PokerLabel();
			//�������˿˱�ǩ���ɼ�
			p.setVisible(false);
			//�����˿˱�ǩ��λ��
			p.setLocation((int) (width * 0.25) + i * distant,
					(int) (height * 0.75));
			// ��ʾ��ǰ��ҵ��Ƶı�ǩ
			this.myPokerLabelVector.add(p);
			this.add(p);
		}
	}

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

	public void addNotSendButton() {
		// �������Ʋ������Լ���Ϣ��ʾ
		this.notSendButton = new JButton();
		notSendButton.setText("����");
		notSendButton.setVisible(false);
		notSendButton.setBounds((width * 9 / 16), (int) (height * (0.65)),
				(int) (width * 0.08), (int) (height * 0.05));
		this.add(notSendButton);
		notSendButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (isMyTurn) {
					try {

						if (notSendNum == 2) {
							JOptionPane.showMessageDialog(null, "��������");
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
					JOptionPane.showMessageDialog(null, "��û�ֵ������");
				}
			}
		});
	}

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

	//��������������ҵ��˿˵ı�ǩ
	public void addOthersPokerLabel() {
		for (int i = 19; i >= 0; i--) {
			JLabel label1 = new JLabel();
			//���ڸ������˵������������ҵ��˿��ǲ����ø���ҿ����ģ���ʹ���˿˵ı�����ʾ
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

	//���ӡ����ơ���ť
	public void addSendButton() {
		// ���Ʋ������Լ���Ϣ��ʾ
		sendButton = new JButton();
		sendButton.setText("����");
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
							//������˿˱�ǩ��ѡ��
							if (pokerLabel.getClicked()) {
								/*
								 * public MouseEvent(Component source, int id, long when, int modifiers,
								 * int x, int y, int clickCount, boolean popupTrigger)
								 * ����һ������ָ��Դ��������͡����η�������͵��������� MouseEvent ����
								 * ע�⣬������Ч id �����²�ȷ������Ϊ��������ʽΪ
								 * MouseEvent(source, id, when, modifiers, x, y,
								 * clickCount, popupTrigger) �ķ�����������Ч�������
								 * MouseEvent(source, id, when, modifiers, x, y,
								 * xAbs, yAbs, clickCount, popupTrigger,
								 * MouseEvent.NOBUTTON) ��ȫ��ͬ������ xAbs �� yAbs
								 * ����Ϊ��Ļ��ԭ���λ�ü���������� x �� y�����û����ʾԭ�㣬�� xAbs ��
								 * yAbs ������Ϊ�㡣��� source Ϊ null����˷������׳�
								 * IllegalArgumentException��
								 * ������
								 *  source - �������¼��� Component 
								 *  id - ��ʶ�¼�������
								 * when - �����¼�����ʱ��� long int ֵ 
								 * modifiers - �����¼��ڼ䰴�µ��޸ļ������� shift��ctrl��alt �� meta)��Ӧ��ʹ����չ�� _DOWN_MASK ���η���ɵ� _MASK
								 * ���η���������һ���¼��в�Ӧ��������ģ�͡���ѡ��չ�����η���
								 * x - ���λ�õ�ˮƽ x ����
								 * y - ���λ�õĴ�ֱ y ����
								 * clickCount - ����¼��йص���굥������
								 * popupTrigger - һ�� booleanֵ������¼���ĳ�������˵��Ĵ���������ò���Ϊ true
								 */
                            	MouseEvent e = new MouseEvent(pokerLabel,MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,0, false);
                            	//Ϊ������������֮һָ���¼���
								pokerLabel.dispatchEvent(e);
								pokerIDVector.add(pokerLabel.getPokerID());
								//���˿˵�ID������һ���˿ˣ�����ӵ�sendPokerVector��
								sendPokerVector.add(new Poker(pokerLabel
										.getPokerID()));

							}

						}
						if (pokerIDVector.size() == 0) {
							JOptionPane.showMessageDialog(null, "��ѡ����Ҫ������");
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
							      JOptionPane.showMessageDialog(null, "�밴�������");
						   }
					 }
					else {
						JOptionPane.showMessageDialog(null, "��û�ֵ������");
					  }
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ͨ�Ŵ���");
				}
			}
		});
	}

	public void display() {
		// ÿ�γ���֮��Ľ���ˢ��
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

	public void hideMysendPoker() {
		// ���ص�ǰ��ҳ�����
		for (int i = 0; i < 20; i++) {
			this.mySendPokerLabelVector.get(i).setVisible(false);
		}
	}

	public void hideNextSendPoker() {
		// ������һ����ϴγ�����
		for (int i = 0; i < 12; i++) {
			this.otherSendPokerLabelVector.get(i).setVisible(false);
		}
	}

	public void hideOtherLabel() {
		// �����������
		for (int i = 0; i < 3; i++) {
			this.lordLabels[i].setVisible(false);

			this.lastPokerLabelVector.get(i).setVisible(false);
			this.lastPokerLabelVector.get(i).setIcon(
					new ImageIcon("res/cards/skin2.jpg"));
		}
	}

	public void hidePokerLabel() {
		// ������������Ƶĵı�ǩ
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

	private void initGUI() {
		// ��ʼ��������Ӹ�������ģ��
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
		g.setFont(new java.awt.Font("��������", 1, 20));
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

	public void showLastPoker() {
		// ��ʾ����
		for (int i = 0; i < 3; i++) {
			lastPokerLabelVector.get(i).setVisible(true);
			lastPokerLabelVector.get(i).setIcon(
					imageIcons[lastpokerIDVector.get(i)]);
		}
	}

	public void showLordChoose(Boolean show) {
		// ��ʾѡ�������ť
		this.lordChooseButton[0].setVisible(show);
		this.lordChooseButton[1].setVisible(show);
	}

	public void showMysendPoker(Vector<Integer> pokerIDVector) {
		// ��ʾ��ǰ��ҳ�����
		this.hideNextSendPoker();
		int size = 10 - pokerIDVector.size() / 2;
		for (int i = size, j = 0; j < pokerIDVector.size(); i++, j++) {
			this.mySendPokerLabelVector.get(i).setIcon(
					imageIcons[pokerIDVector.get(j)]);
			this.mySendPokerLabelVector.get(i).setVisible(true);
		}

	}

	public void showNextSendPoker(Vector<Poker> poker) {
		// ��ʾ��һ��ҳ�����
		this.hidePreSendPoker();
		for (int i = 0; i < poker.size(); i++) {
			this.otherSendPokerLabelVector.get(i).setIcon(
					imageIcons[poker.get(i).getId()]);
			this.otherSendPokerLabelVector.get(i).setVisible(true);
		}
	}

	public void showOtherSendPoker(Vector<Poker> poker, int sender) {
		// ��ʾ������ҳ�����
		if (sender == 1) {
			this.showPreSendPoker(poker);
		} else {
			this.showNextSendPoker(poker);
		}

	}

	public void showPreSendPoker(Vector<Poker> poker) {
		// ��ʾ��һ��ҳ�����
		this.hideMysendPoker();
		for (int i = 0; i < poker.size(); i++) {
			this.otherSendPokerLabelVector.get(23 - i).setIcon(
					imageIcons[poker.get(i).getId()]);
			this.otherSendPokerLabelVector.get(23 - i).setVisible(true);

		}

	}

	public void showsSendButton(Boolean show) {
		// ��ʾ���Ƽ�������ť
		sendButton.setVisible(show);
		notSendButton.setVisible(show);
	}

	public void start() {
		// ��ɷ��ƵĶ�������

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
}
