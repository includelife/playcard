package Main;

import java.awt.Button;

import javax.swing.JFrame;

public class ButtonPane extends JFrame {
	private  Button single = null;
	private  Button multi = null;
	private  Button help = null;
	private  Button langue = null;
	public ButtonPane(){
		single = new Button("����ģʽ");
		multi = new Button("����ģʽ");
		help = new Button("��  ��");
		langue = new Button("��  ��");
		
		single.setBounds(365, 190, 100, 40);
		multi.setBounds(365, 190+50, 100, 40);
		help.setBounds(365, 190+50*2, 100, 40);
		langue.setBounds(365, 190+50*3, 100, 40);
	}
}
