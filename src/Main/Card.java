package Main;

import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import multi.GamePanel;
import play.Playing;

/**
 * ������������
 * @author huzhp
 *
 */
public class Card extends JLabel implements MouseListener{
	
	//Main�������
	Playing playing;
	GamePanel gamepanel;
	//ͼƬurl����
	public String name;
	
	public Playing getPlaying() {
		return playing;
	}

	public void setPlaying(Playing playing) {
		this.playing = playing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	//�Ƿ�������
	boolean up;
	
	//�Ƿ�ɱ����
	public boolean canClick = false;
	
	//�Ƿ񱻵����
	private boolean clicked = false;
	
	/**
	 * ����play���ã��������֣������滹�Ƿ���
	 * @param play
	 * @param name
	 * @param up
	 */
	public Card(Playing play,String name,boolean up){
		this.playing = play;
		this.name = name;
		this.up = up;
		if(this.up){
			this.turnFront();
		}else{
			this.turnRear();
		}
		this.setSize(71,96);
		this.setVisible(true);
		this.addMouseListener(this);	
	}
	/**
	 * ����gamepanel����,�������֣������滹�Ƿ���
	 * @param gamepanel
	 * @param name
	 * @param up
	 */
	public Card(GamePanel gamepanel,String name,boolean up){
		this.gamepanel = gamepanel;
		this.name = name;
		this.up = up;
		if(this.up){
			this.turnFront();
		}else{
			this.turnRear();
		}
		this.setSize(71,96);
		this.setVisible(true);
		this.addMouseListener(this);	
	}
	
	//���ÿ��Ʊ���
	public void turnRear() {
		// TODO Auto-generated method stub
		this.setIcon(new ImageIcon("images/rear.gif"));
		this.up = false; 
	}
	
	//���ÿ�������
	public void turnFront() {
		// TODO Auto-generated method stub
		this.setIcon(new ImageIcon("images/"+name+".gif"));
		this.up = true; 		
	}
	
	
	//����������¼�
	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(isCanClick())
		{
			Point from = this.getLocation();
			//���һ���ƶ��ľ���
			int step;
			if(isClicked())
			{
				step = -20;
			}else
			{
				step = 20;
			}
			//�����ƶ�
			setClicked(!isClicked());
			this.setLocation(from.x, from.y-step);
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the canClick
	 */
	public boolean isCanClick() {
		return canClick;
	}

	/**
	 * @param canClick the canClick to set
	 */
	public void setCanClick(boolean canClick) {
		this.canClick = canClick;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	/**
	 * @return the gamepanel
	 */
	public GamePanel getGamepanel() {
		return gamepanel;
	}

	/**
	 * @param gamepanel the gamepanel to set
	 */
	public void setGamepanel(GamePanel gamepanel) {
		this.gamepanel = gamepanel;
	}
	
}
