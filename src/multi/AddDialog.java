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
 * ��������Ϸ�Ĵ���
 * @author huzhp
 *
 */
public class AddDialog extends JDialog{
	
	private JButton connect;
	private JTextField ipAddress;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField port;

	/**
	 * ���췽��
	 */
	public AddDialog() {
		//��ʼ������
		initGUI();
	}

	/**
	 * �����������ķ����IP��ַ
	 * @return
	 */
	public JTextField getIpAdress() {
		return ipAddress;
	}

	/**
	 * �����������ķ���˿����Ķ˿ں�
	 * @return
	 */
	public JTextField getPort() {
		return port;
	}

	/**
	 * ��ʼ���Ի���
	 */
	private void initGUI() {
		try {

			//�鲼�֣�jdk1.6������һ�ֲ���
			GroupLayout thisLayout = new GroupLayout(getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("����");
			{
				ipAddress = new JTextField();
				//�����ı���ĳ�ʼֵ�����ص�ַ��
				ipAddress.setText("127.0.0.1");
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("IP��ַ");
				jLabel1.setFont(new java.awt.Font("΢���ź�", 0, 16));
			}
			{
				connect = new JButton();
				connect.setText("����");
				connect.setFont(new java.awt.Font("΢���ź�", 1, 16));
				connect.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						dispose();
					}
				});
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("�˿ں�");
				jLabel2.setFont(new java.awt.Font("΢���ź�", 0, 16));
			}
			{
				port = new JTextField();
				port.setText("6000");
			}
			//���ô�ֱ����
			thisLayout.setVerticalGroup(
					//����˳����
					thisLayout.createSequentialGroup()
					.addContainerGap(54, 54).addGroup(
							thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
											/*
											 * public GroupLayout.ParallelGroupaddComponent
											 * (Component component,GroupLayout.Alignment alignment,
											 *  int min, int pref, int max)
											 * ʹ��ָ���Ķ��뷽ʽ�ʹ�С�� Component������� ��ӵ���ParallelGroup��ƽ���飩
											 * ������ 
											 * alignment - ���뷽ʽ
											 * component -Ҫ��ӵ� Component 
											 * min - ��С��С
											 * pref -��ѡ��С 
											 * max - ����С 
											 * ���أ� �� Group
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
							 * ���̶���϶��ӵ��� Group��
							 * ���ǣ� �� GroupLayout.Group �е� addGap 
							 * ������ pref -��϶�Ĵ�С 
							 * ���أ� �� Group
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
							 * ��ָ����С�ļ�϶��ӵ��� Group��
							 * ���ǣ� �� GroupLayout.Group �е� addGap 
							 * ������
							 * min -��϶����С��С
							 * pref-��϶����ѡ��С
							 * max -��϶������С
							 * ���أ� �� Group
							 */
                         .addGap(0, 20, Short.MAX_VALUE)
                         .addComponent(connect,
							GroupLayout.PREFERRED_SIZE, 33,
							GroupLayout.PREFERRED_SIZE).addContainerGap());
			//ˮƽ������
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
			//�����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ���
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

