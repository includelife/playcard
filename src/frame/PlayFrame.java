package frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import play.Playing;

public class PlayFrame extends JFrame implements ActionListener {
	private Container pane = null;
	private JButton returnButton;
	private JButton settingButton;
	public PlayFrame(){
		super();
	}
	public void init(String single){
		Playing pfr = new Playing();
		pfr.setVisible(true);
	}
	public void init(){
		
		this.setTitle("斗地主");
		this.setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // 屏幕居中
		pane = this.getContentPane();
		pane.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBackground(new Color(0, 112, 26)); // 背景为绿色
		
		returnButton = new JButton("返 回");
		settingButton = new JButton("设 置");
		
		returnButton.setBounds(700, 10, 80, 30);
		settingButton.setBounds(700, 45, 80, 30);
		
		returnButton.addActionListener(this);
		settingButton.addActionListener(this);
		
		pane.add(returnButton);
		pane.add(settingButton);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == returnButton){
			this.dispose();
			SelectFrame fr = new SelectFrame();
			fr.init();
			fr.initSelect();
			fr.setVisible(true);
		}else if(e.getSource() == settingButton){
			JOptionPane.showMessageDialog(this,"开发中");
		}
	}

}
