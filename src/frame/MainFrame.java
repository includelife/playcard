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

import play.Playing;

public class MainFrame extends JFrame implements ActionListener{
	private Container pane = null;
	private JButton single = null;
	private JButton multi = null;
	private JButton help = null;
	private JButton langue = null;
	private JButton rebutton = null;
	
	public MainFrame() {		
		super();
	}
	
	public void init() {
		pane = this.getContentPane();
		this.setTitle("������");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(getOwner()); // ��Ļ����
		pane.setLayout(null);
		pane.setBackground(new Color(0, 112, 26)); // ����Ϊ��ɫ		
		
		//�����ǩ
		final JLabel titleLabel = new JLabel();
		titleLabel.setText("��ӭ����������");
		Font myfont = new Font("����",Font.CENTER_BASELINE,60);
		titleLabel.setFont(myfont);
		titleLabel.setBounds(200, 35, 700, 200);
		this.getContentPane().add(titleLabel);
		
//		JLabel backgroundLabel = new JLabel();
//		backgroundLabel.setBounds(0, 0, 800, 600);
//		backgroundLabel.setLayout(null);
//		
//		single = new JButton("����ģʽ");
//		multi = new JButton("����ģʽ");
//		help = new JButton("��  ��");
//		langue = new JButton("��  ��");
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
		
		single = new JButton("����ģʽ");
		multi = new JButton("����ģʽ");
		help = new JButton("��  ��");
		langue = new JButton("��  ��");
		rebutton = new JButton("�� ��");
		
		single.setBounds(365, 190, 100, 40);
		multi.setBounds(365, 190+50, 100, 40);
		help.setBounds(365, 190+50*2, 100, 40);
		langue.setBounds(365, 190+50*3, 100, 40);
		rebutton.setBounds(365, 190+50*4, 100, 40);
		
		single.addActionListener(this);
		multi.addActionListener(this);
		help.addActionListener(this);
		langue.addActionListener(this);
		rebutton.addActionListener(this);

		backgroundLabel.add(single);
		backgroundLabel.add(multi);
		backgroundLabel.add(help);
		backgroundLabel.add(langue);
		backgroundLabel.add(rebutton);

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
			//JOptionPane.showMessageDialog(this, "����ģʽ");
			this.dispose();
//			PlayFrame pfr = new PlayFrame();
//			String s = new String();
//			pfr.init(s);
//			pfr.setVisible(true);
			Playing play = new Playing();
			play.setVisible(true);
		}else if(e.getSource() == multi){
			JOptionPane.showMessageDialog(this, "������");
		}else if(e.getSource() == help){
			JOptionPane.showMessageDialog(this, "����Ҫʲô������");
		}else if(e.getSource() == langue){
			JOptionPane.showMessageDialog(this, "������");	
		}else if(e.getSource() == rebutton){
			this.dispose();
			LoginFrame fr = new LoginFrame();
			fr.setVisible(true);
		}
	}
}
