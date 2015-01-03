package multi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 * 建立新游戏的窗口
 * @author huzhp
 *
 */
public class AddDialog extends JDialog{
	
	private JButton connect;
	private JTextField ipAddress;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField port;

	//构造方法
	public AddDialog() {
		//初始化界面
		initGUI();
	}

	//返回玩家输入的服务端IP地址
	public JTextField getIpAdress() {
		return ipAddress;
	}

	//返回玩家输入的服务端开启的端口号
	public JTextField getPort() {
		return port;
	}

	//初始化对话框
	private void initGUI() {
		try {

			//组布局，jdk1.6新增的一种布局
			GroupLayout thisLayout = new GroupLayout(getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("输入");
			{
				ipAddress = new JTextField();
				//设置文本框的初始值（换回地址）
				ipAddress.setText("127.0.0.1");
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("IP地址");
				jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 16));
			}
			{
				connect = new JButton();
				connect.setText("链接");
				connect.setFont(new java.awt.Font("微软雅黑", 1, 16));
				connect.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						dispose();
					}
				});
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("端口号");
				jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 16));
			}
			{
				port = new JTextField();
				port.setText("6000");
			}
			//设置垂直方向
			thisLayout.setVerticalGroup(
					//创建顺序组
					thisLayout.createSequentialGroup()
					.addContainerGap(54, 54).addGroup(
							thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											/*
											 * public GroupLayout.ParallelGroupaddComponent
											 * (Component component,GroupLayout.Alignment alignment,
											 *  int min, int pref, int max)
											 * 使用指定的对齐方式和大小将 Component（组件） 添加到此ParallelGroup（平行组）
											 * 参数： 
											 * alignment - 对齐方式
											 * component -要添加的 Component 
											 * min - 最小大小
											 * pref -首选大小 
											 * max - 最大大小 
											 * 返回： 此 Group
											 */

								    .addComponent(ipAddress,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 26,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(jLabel1,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 23,
											GroupLayout.PREFERRED_SIZE)
											)
							/*
							 * public GroupLayout.ParallelGroup addGap(int pref)
							 * 将固定间隙添加到此 Group。
							 * 覆盖： 类 GroupLayout.Group 中的 addGap 
							 * 参数： pref -间隙的大小 
							 * 返回： 此 Group
							 */
                          .addGap(25).addGroup(
							thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(jLabel2,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 20,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(port,
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
											)
							/*
							 * public GroupLayout.ParallelGroup addGap(int min,int pref, int max)
							 * 将指定大小的间隙添加到此 Group。
							 * 覆盖： 类 GroupLayout.Group 中的 addGap 
							 * 参数：
							 * min -间隙的最小大小
							 * pref-间隙的首选大小
							 * max -间隙的最大大小
							 * 返回： 此 Group
							 */
                         .addGap(0, 20, Short.MAX_VALUE)
                         .addComponent(connect,
							GroupLayout.PREFERRED_SIZE, 33,
							GroupLayout.PREFERRED_SIZE).addContainerGap());
			//水平方向上
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
							.addContainerGap(17, 17).addGroup(thisLayout.createParallelGroup()
									   .addGroup(
											   GroupLayout.Alignment.LEADING,
							  thisLayout.createSequentialGroup().addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 68,  GroupLayout.PREFERRED_SIZE)
										     .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										        )
										    .addComponent(jLabel1,
										                   GroupLayout.Alignment.LEADING,
									                       GroupLayout.PREFERRED_SIZE,73,
										                   GroupLayout.PREFERRED_SIZE))
							         .addGroup(thisLayout.createParallelGroup()
									 .addGroup(
										 GroupLayout.Alignment.LEADING,
										 thisLayout.createSequentialGroup()
										 .addComponent(connect,
										                GroupLayout.PREFERRED_SIZE,101,
										                GroupLayout.PREFERRED_SIZE)
											.addGap(0,71,Short.MAX_VALUE))
								     .addGroup(
											thisLayout	.createSequentialGroup()
											.addPreferredGap(connect,
															  port,
															LayoutStyle.ComponentPlacement.INDENT)
											.addGroup(thisLayout.createParallelGroup()
												.addComponent(port,
															GroupLayout.Alignment.LEADING,0,160,
																Short.MAX_VALUE)
																.addGroup(
																thisLayout
																.createSequentialGroup()
																.addComponent(ipAddress,
																			GroupLayout.PREFERRED_SIZE,	160,
																			GroupLayout.PREFERRED_SIZE)
																			.addGap(0,0,Short.MAX_VALUE)))))
							                                               .addContainerGap());
			//调整此窗口的大小，以适合其子组件的首选大小和布局
			pack();
			this.setSize(290, 231);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the connect
	 */
	public JButton getConnect() {
		return connect;
	}

	/**
	 * @param connect the connect to set
	 */
	public void setConnect(JButton connect) {
		this.connect = connect;
	}

	/**
	 * @return the ipAddress
	 */
	public JTextField getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(JTextField ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the jLabel1
	 */
	public JLabel getjLabel1() {
		return jLabel1;
	}

	/**
	 * @param jLabel1 the jLabel1 to set
	 */
	public void setjLabel1(JLabel jLabel1) {
		this.jLabel1 = jLabel1;
	}

	/**
	 * @return the jLabel2
	 */
	public JLabel getjLabel2() {
		return jLabel2;
	}

	/**
	 * @param jLabel2 the jLabel2 to set
	 */
	public void setjLabel2(JLabel jLabel2) {
		this.jLabel2 = jLabel2;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(JTextField port) {
		this.port = port;
	}
	
}

