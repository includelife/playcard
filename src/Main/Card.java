package Main;

import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import multi.GamePanel;
import play.Playing;

/**
 * 卡牌属性设置
 * @author huzhp
 *
 */
public class Card extends JLabel implements MouseListener{
	
	//Main类的引用
	Playing playing;
	GamePanel gamepanel;
	//图片url名字
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

	//是否正反面
	boolean up;
	
	//是否可被点击
	public boolean canClick = false;
	
	//是否被点击过
	private boolean clicked = false;
	
	/**
	 * 传入play引用，卡牌名字，是正面还是反面
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
	 * 传入gamepanel引用,卡牌名字，是正面还是反面
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
	
	//设置卡牌背面
	public void turnRear() {
		// TODO Auto-generated method stub
		this.setIcon(new ImageIcon("images/rear.gif"));
		this.up = false; 
	}
	
	//设置卡牌正面
	public void turnFront() {
		// TODO Auto-generated method stub
		this.setIcon(new ImageIcon("images/"+name+".gif"));
		this.up = true; 		
	}
	
	
	//设置鼠标点击事件
	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(isCanClick())
		{
			Point from = this.getLocation();
			//点击一次移动的距离
			int step;
			if(isClicked())
			{
				step = -20;
			}else
			{
				step = 20;
			}
			//反向移动
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
	
}
