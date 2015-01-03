package multi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * �˿��Ʊ�ǩ��
 * @author huzhp
 *
 */
public class PokerLabel extends JLabel{
	private Boolean clicked = false;
	private int height = 96;
	private int width = 71;
	private int pokerID = 0;

	// ����Ϊ��ǰ�����ʾ�Ƶı�ǩ���̳���JLabel
	// Ϊ�򻯴����ڸ�����Ϊ��ǩ�����Ĭ���¼���Ӧ

	public PokerLabel() {
		initGUI();
	}

	public Boolean getClicked() {
		return clicked;
	}

	public int getPokerID() {
		return pokerID;
	}

	public void initGUI() {
		//���øñ�ǩ�Ĵ�С
		this.setSize(width, height);
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (!clicked) {
					clicked = true;
					setBounds(getBounds().x, getBounds().y - 20,
							getBounds().width, getBounds().height);
				} else {

					clicked = false;
					setBounds(getBounds().x, getBounds().y + 20,
							getBounds().width, getBounds().height);

				}

			}

			//�������¼�
			public void mouseEntered(MouseEvent evt) {
				//�������ǩʱ�����øñ�ǩ�ı߿�
				setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			}
            //����뿪�¼�
			public void mouseExited(MouseEvent evt) {
				//����뿪��ǩʱ���ͽ��ñ�ǩ�ı߿�ȥ��
				setBorder(null);
			}
		});

	}

	public void setClicked(Boolean clicked) {
		this.clicked = clicked;
	}

	public void setPokerID(int pokerID) {
		this.pokerID = pokerID;
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

}
