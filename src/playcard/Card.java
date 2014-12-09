package playcard;

import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.w3c.dom.events.MouseEvent;

import play.Playing;

public class Card extends JLabel implements MouseListener{
	
	//Main类的引用
	Playing playing;
	
	//图片url名字
	String name;
	
	//是否正反面
	boolean up;
	
	//是否可被点击
	private boolean canClick = false;
	
	//是否被点击过
	boolean clicked = false;
	
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
	
	//设置卡牌背面
	private void turnRear() {
		// TODO Auto-generated method stub
		this.setIcon(new ImageIcon("images/rear.gif"));
		this.up = false; 
	}
	
	//设置卡牌正面
	private void turnFront() {
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
			if(clicked)
			{
				step = -20;
			}else
			{
				step = 20;
			}
			//反向移动
			clicked = !clicked;
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
	
}
