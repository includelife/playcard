package playcard;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame implements ActionListener{
	private Container pane = null;
	private Button single = null;
	private Button multi = null;
	private Button help = null;
	private Button langue = null;
	
	public MainFrame() {		
		super();
	}
	
	public void init() {
		this.setTitle("斗地主");
		this.setSize(830, 620);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // 屏幕居中
		pane = this.getContentPane();
		pane.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setBackground(new Color(0, 112, 26)); // 背景为绿色		
		
		single = new Button("单人模式");
		multi = new Button("多人模式");
		help = new Button("帮  助");
		langue = new Button("语  言");
		
		single.setBounds(365, 190, 100, 40);
		multi.setBounds(365, 190+50, 100, 40);
		help.setBounds(365, 190+50*2, 100, 40);
		langue.setBounds(365, 190+50*3, 100, 40);
		
		single.addActionListener(this);
		multi.addActionListener(this);
		help.addActionListener(this);
		langue.addActionListener(this);

		pane.add(single);
		pane.add(multi);
		pane.add(help);
		pane.add(langue);
		
	}
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == single){
			//JOptionPane.showMessageDialog(this, "单人模式");
			pane.remove(single);
			pane.remove(multi);
			pane.remove(help);
			pane.remove(langue);
			
		}else if(e.getSource() == multi){
			JOptionPane.showMessageDialog(this, "开发中");
		}else if(e.getSource() == help){
			JOptionPane.showMessageDialog(this, "您需要什么帮助？");
		}else if(e.getSource() == langue){
			JOptionPane.showMessageDialog(this, "开发中");	
		}
	}
}
