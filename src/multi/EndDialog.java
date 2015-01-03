package multi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * ����ģʽ��������
 * @author huzhp
 *
 */
public class EndDialog extends JDialog{
	private JLabel[] count = new JLabel[3];
	private JButton exit;
	// ����Ϊ������Ϸʱ�ĶԻ���
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
			count[i].setFont(new java.awt.Font("����", 0, 12));
		}
	}

	//���ӡ��˳���Ϸ����ť
	public void addExit() {
		this.exit = new JButton("����ѡ����Ϸ");
		getContentPane().add(this.exit);
		exit.setBounds(73, 180, 90, 34);
		//exit��ťע���¼�
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				
				
				System.exit(0);
			}
		});
	}

	//��Ի���������λ���
	public void addPlayer() {
		for (int i = 0; i < 3; i++) {
			player[i] = new JLabel();
			getContentPane().add(player[i]);
			player[i].setBounds(61, 55 + i * 40, 53, 34);
			/*
			 * public void setAlignmentX(float alignmentX) ���ô�ֱ���뷽ʽ�� ������
			 * alignmentX - �µĴ�ֱ���뷽ʽ����������Щ 
			 * TOP_ALIGNMENT 
			 * getAlignmentY() ������ʹ�õĳ�����ָ������������뷽ʽ��
			 * ------------------------------------------------------------------
			 * CENTER_ALIGNMENT
			 * getAlignmentY �� getAlignmentX������ʹ�õĳ�����ָ��������ж��뷽ʽ��
			 * ------------------------------------------------------------------
			 * BOTTOM_ALIGNMENT
			 * getAlignmentY ������ʹ�õĳ�����ָ������ײ����뷽ʽ��
			 * ------------------------------------------------------------------
			 * LEFT_ALIGNMENTgetAlignmentX ������ʹ�õĳ�����ָ���������뷽ʽ��
			 * ����μ��� getAlignmentX(), �����ֶ�ֵ
			 * ------------------------------------------------------------------
			 * RIGHT_ALIGNMENT
			 * getAlignmentX ������ʹ�õĳ�����ָ������Ҷ��뷽ʽ��
			 */
            player[i].setAlignmentX(CENTER_ALIGNMENT);//����Ϊ��ֱ���ж���
			player[i].setFont(new java.awt.Font("����", 0, 12));
		}
	}

	public JLabel[] getCount() {
		return count;
	}

	public JLabel[] getPlayer() {
		return player;
	}

	private void initGUI() {
		try {
			//����Ϊģʽ�Ի���
			setModal(true);
			//��������Ĳ�������Ϊnull���ؼ��Ķ�λҪ�ֶ�����
			getContentPane().setLayout(null);
			{
				name = new JLabel();
				getContentPane().add(name, "Center");
				name.setText("���");
				name.setBounds(61, 20, 53, 34);
				name.setFont(new java.awt.Font("����", 0, 20));
			}
			{
				point = new JLabel();
				getContentPane().add(point);
				point.setText("���");
				point.setBounds(143, 20, 53, 34);
				point.setFont(new java.awt.Font("����", 0, 20));

			}
			this.addPlayer();
			this.addCount();
			this.addExit();
			pack();
			this.setSize(290, 285);
			//��ȡ��Ļ��Wide��High
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
           //���з��øöԻ���
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
