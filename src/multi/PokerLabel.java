package multi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class PokerLabel extends JLabel{
	private Boolean clicked = false;
	private int height = 96;
	private int width = 71;
	private int pokerID = 0;

	// 该类为当前玩家显示牌的标签，继承了JLabel
	// 为简化处理，在该类中为标签添加了默认事件响应

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
		//设置该标签的大小
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

			//鼠标进入事件
			public void mouseEntered(MouseEvent evt) {
				//鼠标进入标签时，设置该标签的边框
				setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			}
            //鼠标离开事件
			public void mouseExited(MouseEvent evt) {
				//鼠标离开标签时，就将该标签的边框去除
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

}
