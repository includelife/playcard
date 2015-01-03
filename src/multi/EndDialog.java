package multi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import frame.SelectFrame;

/**
 * 多人模式结束窗口
 * @author huzhp
 *
 */
public class EndDialog extends JDialog{
	private JLabel[] count = new JLabel[3];
	private JButton exit;
	// 该类为结束游戏时的对话框
	private JLabel name;
	private JLabel[] player = new JLabel[3];
	private JLabel point;

	public EndDialog() {
		super();
		initGUI();
	}

	public void addCount() {
		for (int i = 0; i < 3; i++) {
			count[i] = new JLabel();
			getContentPane().add(count[i]);
			count[i].setBounds(143, 55 + i * 40, 53, 34);
			count[i].setFont(new java.awt.Font("隶书", 0, 12));
		}
	}

	//增加“返回游戏”按钮
//	public void addExit() {
//		this.exit = new JButton("返回");
//		getContentPane().add(this.exit);
//		exit.setBounds(73, 180, 90, 34);
//		//exit按钮注册事件
//		exit.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt) {
//				
//				SelectFrame reselect = new SelectFrame();
//				reselect.init();
//				reselect.initSelect();
//				reselect.setVisible(true);	
//			}
//		});
//	}

	/**
	 * 向对话框增加三位玩家
	 */
	public void addPlayer() {
		for (int i = 0; i < 3; i++) {
			player[i] = new JLabel();
			getContentPane().add(player[i]);
			player[i].setBounds(61, 55 + i * 40, 53, 34);
			/*
			 * public void setAlignmentX(float alignmentX) 设置垂直对齐方式。 参数：
			 * alignmentX - 新的垂直对齐方式，有以下这些 
			 * TOP_ALIGNMENT 
			 * getAlignmentY() 的易于使用的常量。指定组件顶部对齐方式。
			 * ------------------------------------------------------------------
			 * CENTER_ALIGNMENT
			 * getAlignmentY 和 getAlignmentX的易于使用的常量。指定组件居中对齐方式。
			 * ------------------------------------------------------------------
			 * BOTTOM_ALIGNMENT
			 * getAlignmentY 的易于使用的常量。指定组件底部对齐方式。
			 * ------------------------------------------------------------------
			 * LEFT_ALIGNMENTgetAlignmentX 的易于使用的常量。指定组件左对齐方式。
			 * 另请参见： getAlignmentX(), 常量字段值
			 * ------------------------------------------------------------------
			 * RIGHT_ALIGNMENT
			 * getAlignmentX 的易于使用的常量。指定组件右对齐方式。
			 */
            player[i].setAlignmentX(CENTER_ALIGNMENT);//设置为垂直居中对齐
			player[i].setFont(new java.awt.Font("隶书", 0, 12));
		}
	}

	public JLabel[] getCount() {
		return count;
	}

	public JLabel[] getPlayer() {
		return player;
	}
	
	/**
	 * 初始化对话框界面
	 */
	private void initGUI() {
		try {
			//设置为模式对话框
			setModal(true);
			//将主窗体的布局设置为null，控件的定位要手动设置
			getContentPane().setLayout(null);
			{
				name = new JLabel();
				getContentPane().add(name, "Center");
				name.setText("玩家");
				name.setBounds(61, 20, 53, 34);
				name.setFont(new java.awt.Font("隶书", 0, 20));
			}
			{
				point = new JLabel();
				getContentPane().add(point);
				point.setText("结果");
				point.setBounds(143, 20, 53, 34);
				point.setFont(new java.awt.Font("隶书", 0, 20));

			}
			this.addPlayer();
			this.addCount();
//			this.addExit();
			pack();
			this.setSize(290, 285);
			//获取屏幕的Wide和High
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
           //居中放置该对话框
			setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the exit
	 */
	public JButton getExit() {
		return exit;
	}

	/**
	 * @param exit the exit to set
	 */
	public void setExit(JButton exit) {
		this.exit = exit;
	}

	/**
	 * @return the name
	 */
	public JLabel getLabelName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(JLabel name) {
		this.name = name;
	}

	/**
	 * @return the point
	 */
	public JLabel getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(JLabel point) {
		this.point = point;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(JLabel[] count) {
		this.count = count;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(JLabel[] player) {
		this.player = player;
	}
	
}
