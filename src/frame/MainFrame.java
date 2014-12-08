package frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame implements ActionListener{
	private Container pane = null;
	private JButton single = null;
	private JButton multi = null;
	private JButton help = null;
	private JButton langue = null;
	
	public MainFrame() {		
		super();
	}
	
	public void init() {
		pane = this.getContentPane();
		this.setTitle("斗地主");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // 屏幕居中
		pane.setLayout(null);
		pane.setBackground(new Color(0, 112, 26)); // 背景为绿色		
		
		//标题标签
		final JLabel titleLabel = new JLabel();
		titleLabel.setText("欢迎来到斗地主");
		Font myfont = new Font("宋体",Font.CENTER_BASELINE,60);
		titleLabel.setFont(myfont);
		titleLabel.setBounds(200, 35, 700, 200);
		this.getContentPane().add(titleLabel);
		
//		JLabel backgroundLabel = new JLabel();
//		backgroundLabel.setBounds(0, 0, 800, 600);
//		backgroundLabel.setLayout(null);
//		
//		single = new JButton("单人模式");
//		multi = new JButton("多人模式");
//		help = new JButton("帮  助");
//		langue = new JButton("语  言");
//		
//		single.setBounds(365, 190, 100, 40);
//		multi.setBounds(365, 190+50, 100, 40);
//		help.setBounds(365, 190+50*2, 100, 40);
//		langue.setBounds(365, 190+50*3, 100, 40);
//		
//		single.addActionListener(this);
//		multi.addActionListener(this);
//		help.addActionListener(this);
//		langue.addActionListener(this);
//
//		backgroundLabel.add(single);
//		backgroundLabel.add(multi);
//		backgroundLabel.add(help);
//		backgroundLabel.add(langue);
//
//		this.getContentPane().add(backgroundLabel);
	}	
	
	public void initSelect(){
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 800, 600);
		backgroundLabel.setLayout(null);
		
		single = new JButton("单人模式");
		multi = new JButton("多人模式");
		help = new JButton("帮  助");
		langue = new JButton("语  言");
		
		single.setBounds(365, 190, 100, 40);
		multi.setBounds(365, 190+50, 100, 40);
		help.setBounds(365, 190+50*2, 100, 40);
		langue.setBounds(365, 190+50*3, 100, 40);
		
		single.addActionListener(this);
		multi.addActionListener(this);
		help.addActionListener(this);
		langue.addActionListener(this);

		backgroundLabel.add(single);
		backgroundLabel.add(multi);
		backgroundLabel.add(help);
		backgroundLabel.add(langue);

		this.getContentPane().add(backgroundLabel);
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
			this.dispose();	
			PlayFrame pfr = new PlayFrame();
			pfr.init();
			pfr.setVisible(true);
		}else if(e.getSource() == multi){
			JOptionPane.showMessageDialog(this, "开发中");
		}else if(e.getSource() == help){
			JOptionPane.showMessageDialog(this, "您需要什么帮助？");
		}else if(e.getSource() == langue){
			JOptionPane.showMessageDialog(this, "开发中");	
		}
	}
}
