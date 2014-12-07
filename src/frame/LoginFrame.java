package frame;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import action.LoginAction;

//import frame.JProgressBarFrame;

public class LoginFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private JTextField nameTF;
	private JPasswordField passwordTF;
	private JButton loginButton = null, resetButton = null;
	private JProgressBarFrame progressBar = null;
	private String username = null, password = null;
	
	public LoginFrame() {
		// TODO Auto-generated constructor stub
		super();
		getContentPane().setLayout(null);
		
		jFrameValidate(); 
		
		setTitle("��¼��Ϸ");
		
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBounds(0, 0, 800, 600);
		backgroundLabel.setLayout(null);
		
		
		final JLabel nameLable = new JLabel();
		nameLable.setText("�û��� ��");
		nameLable.setBounds(230, 203, 100, 18);
		//����������ǩ��ӽ�������ǩ
		backgroundLabel.add(nameLable);
		
		final JLabel passLable = new JLabel();
		passLable.setText("��  �� ��");
		passLable.setBounds(230, 243, 100, 18);
		//����������ǩ��ӽ�������ǩ
		backgroundLabel.add(passLable);
		
		nameTF = new JTextField();
		nameTF.setBounds(370, 203, 150, 22);
		backgroundLabel.add(nameTF);
		
		//����
		passwordTF = new JPasswordField();
		passwordTF.setBounds(370, 243, 150, 22);
		backgroundLabel.add(passwordTF);
		
		loginButton = new JButton("��¼");
		resetButton = new JButton("����");
		backgroundLabel.add(loginButton);
		backgroundLabel.add(resetButton);
		loginButton.setBounds(280, 360, 80, 30);
		resetButton.setBounds(400, 360, 80, 30);
		
		//��Ӽ�����
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		
		//�ѱ�����ǩ��ӽ��������
		getContentPane().add(backgroundLabel);
		
		progressBar = new JProgressBarFrame(this, "��¼", "��¼�У����Ժ�...");
		
		reset();// Ĭ�ϳ�ʼֵ
	}
	
	private void reset() {
		// TODO Auto-generated method stub
		nameTF.setText("");
		passwordTF.setText("");
	}

	private void jFrameValidate() {
		// TODO Auto-generated method stub
		// ���ϵͳ��������ԣ�������Ҫ��Ļ�Ŀ�͸�
		Toolkit tk = getToolkit();
		//Dimension��Java��һ���࣬��װ��һ�������ĸ߶ȺͿ��
		Dimension dim = tk.getScreenSize();
		this.setResizable(false);
		//��ܾ�����ʾ  760 540
		this.setBounds(dim.width / 2 - 400, dim.height / 2 - 300, 800, 600);
		validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginButton) {// ��¼
			progressBar.setVisible(true);// ���ý������ɼ�
			new Thread() {
				public void run() {
					getValues();// �õ������е��������ֵ
					checkUser();// ��¼��֤
				}
			}.start();
		} else if (e.getSource() == resetButton) {// ����
			reset();// �������ø����ֵ
		}
	}

	private void checkUser() {
		LoginAction login = new LoginAction(username,
				password);
		if (login.isLogin()) {// ��¼�ɹ�
			progressBar.dispose();
			this.dispose();// �ͷű�������Դ
			MainFrame fr = new MainFrame();
			fr.init();
			fr.initSelect();
			fr.setVisible(true);			
		} else {// ��¼ʧ��
			progressBar.setVisible(false);
			JOptionPane.showMessageDialog(this, "<html><h4>"
					+ "��¼ʧ�ܣ������û����������Ƿ���ȷ��" + "<html><h4>", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	@SuppressWarnings("deprecation")
	protected void getValues() {
		// TODO Auto-generated method stub
		username = nameTF.getText().trim();
		password = passwordTF.getText().trim();
	}

}
