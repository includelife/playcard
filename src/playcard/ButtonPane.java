package playcard;

import java.awt.Button;

import javax.swing.JFrame;

public class ButtonPane extends JFrame {
	private  Button single = null;
	private  Button multi = null;
	private  Button help = null;
	private  Button langue = null;
	public ButtonPane(){
		single = new Button("单人模式");
		multi = new Button("多人模式");
		help = new Button("帮  助");
		langue = new Button("语  言");
		
		single.setBounds(365, 190, 100, 40);
		multi.setBounds(365, 190+50, 100, 40);
		help.setBounds(365, 190+50*2, 100, 40);
		langue.setBounds(365, 190+50*3, 100, 40);
	}
}
