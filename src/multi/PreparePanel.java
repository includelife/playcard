package multi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class PreparePanel extends JPanel{
	// ����Ϊ����������Ϸ��ʼǰ��׼������

		private int height;
		private Boolean isConnect = false;
		private String[] playernames = new String[3];
		private int width;

		//���췽��
		public PreparePanel(String name, int width, int height) {
			super();
			this.playernames[0] = name;
			this.playernames[1] = "";
			this.playernames[2] = "";
			this.width = width;
			this.height = height;
			initGUI();
		}

		public String[] getPlayernames() {
			return playernames;
		}

		//��ʼ������
		private void initGUI() {
			try {
				GroupLayout thisLayout = new GroupLayout(this);
				this.setLayout(thisLayout);
				thisLayout.setHorizontalGroup(thisLayout.createParallelGroup());
				thisLayout.setVerticalGroup(thisLayout.createParallelGroup());
				setPreferredSize(new Dimension(width, height));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void paint(Graphics g) {
			super.paint(g);
			if (this.isConnect) {
				this.setBackground(new Color(44, 114, 104));
				g.setFont(new java.awt.Font("��������", 1, 20));
				g.drawString(playernames[0], (int) (width * 0.5),
						(int) (height * 0.95));
				g.drawString(playernames[1], (int) (width * 0.01),
						(int) (height * 0.5));
				g.drawString(playernames[2], (int) (width * 0.9),
						(int) (height * 0.5));
				g.setFont(new java.awt.Font("��������", 1, 30));
				g.drawString("�ȴ��������", (int) (width * 0.4), (int) (height * 0.5));
			} else {
				this.setBackground(new Color(44, 114, 104));
				g.setFont(new java.awt.Font("��������", 1, 20));
				/*���Ʋ�����Ϸ���ܾ�������Ϸ��ע�����ұ����������ϵ���ƭ���ʶ���Ϸ���ԣ�������Ϸ����������ʱ�䣬���ܽ������*/
				g.setColor(Color.CYAN);
				g.drawString("���Ʋ�����Ϸ���ܾ�������Ϸ��", (int) (width * 0.35),(int) (height * 0.4));
				g.drawString("ע�����ұ����������ϵ���ƭ��", (int) (width * 0.35),(int) (height * 0.45));
				g.drawString("�ʶ���Ϸ���ԣ�������Ϸ����", (int) (width * 0.35),(int) (height * 0.50));
				g.drawString("������ʱ�䣬���ܽ������", (int) (width * 0.35),(int) (height * 0.55));
//				g.drawString("�ʶ���Ϸ ��������", (int) (width * 0.43),(int) (height * 0.45));
//				g.drawString("�ʶ���Ϸ ��������", (int) (width * 0.43),(int) (height * 0.45));
//				g.drawString("�����ͨ���˵�ѡ���½������һ����Ϸ��", (int) (width * 0.35),(int) (height * 0.5));
			}

		}

		public void setIsConnect(Boolean isConnect) {
			this.isConnect = isConnect;
		}

		public void setPlayernames(String[] playernames) {
			this.playernames = playernames;
		}

	}

