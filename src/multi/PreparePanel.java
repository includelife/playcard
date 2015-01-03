package multi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 * 游戏准备界面
 * @author huzhp
 *
 */
public class PreparePanel extends JPanel{
	// 该类为启动程序，游戏开始前的准备界面

		private int height;
		private Boolean isConnect = false;
		private String[] playernames = new String[3];
		private int width;

		//构造方法
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

		//初始化界面
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
				g.setFont(new java.awt.Font("华文隶书", 1, 20));
				g.drawString(playernames[0], (int) (width * 0.5),
						(int) (height * 0.95));
				g.drawString(playernames[1], (int) (width * 0.01),
						(int) (height * 0.5));
				g.drawString(playernames[2], (int) (width * 0.9),
						(int) (height * 0.5));
				g.setFont(new java.awt.Font("华文隶书", 1, 30));
				g.drawString("等待其他玩家", (int) (width * 0.4), (int) (height * 0.5));
			} else {
				this.setBackground(new Color(44, 114, 104));
				g.setFont(new java.awt.Font("华文隶书", 1, 20));
				/*抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防上当受骗。适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。*/
				g.setColor(Color.CYAN);
				g.drawString("抵制不良游戏，拒绝盗版游戏。", (int) (width * 0.35),(int) (height * 0.4));
				g.drawString("注意自我保护，谨防上当受骗。", (int) (width * 0.35),(int) (height * 0.45));
				g.drawString("适度游戏益脑，沉迷游戏伤身。", (int) (width * 0.35),(int) (height * 0.50));
				g.drawString("合理安排时间，享受健康生活。", (int) (width * 0.35),(int) (height * 0.55));
//				g.drawString("适度游戏 沉迷伤身", (int) (width * 0.43),(int) (height * 0.45));
//				g.drawString("适度游戏 沉迷伤身", (int) (width * 0.43),(int) (height * 0.45));
//				g.drawString("你可以通过菜单选择新建或加入一局游戏！", (int) (width * 0.35),(int) (height * 0.5));
			}

		}

		public void setIsConnect(Boolean isConnect) {
			this.isConnect = isConnect;
		}

		public void setPlayernames(String[] playernames) {
			this.playernames = playernames;
		}

	}

