package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.PageAttributes;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import util.fileUtil;



public class RegisFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel newLabel;
	
	public RegisFrame() {
		// TODO Auto-generated constructor stub
		setTitle("������ע��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrameValidate();
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//�û�����ǩ
		final JLabel nameLable = new JLabel();
		nameLable.setText("�������û��� ��");
		nameLable.setBounds(80, 42, 100, 21);
		//����������ǩ��ӽ�������ǩ
		contentPane.add(nameLable);
		
		//�����ǩ
		final JLabel passLable = new JLabel();
		passLable.setText("���������� ��");
		passLable.setBounds(80, 98, 100, 21);
		//�������ǩ��ӽ�����
		contentPane.add(passLable);
		
		//ȷ�������ǩ
		final JLabel passLable_1 = new JLabel();
		passLable_1.setText("���ٴ������� �� :");
		passLable_1.setBounds(80, 152, 100, 21);
		//��ȷ�������ǩ��ӽ�������ǩ
		contentPane.add(passLable_1);
		
		
		//�û����ı���
		textField = new JTextField();
		textField.setBounds(190, 42, 104, 21);
		textField.setOpaque(false);
		textField.setColumns(10);
		contentPane.add(textField);
		
		//�����ı���
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 98, 104, 21);
		passwordField.setOpaque(false);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		//ȷ�������ı���
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(190, 152, 104, 21);
		passwordField_1.setOpaque(false);
		passwordField_1.setEchoChar('*');
		contentPane.add(passwordField_1);
		
		//ע�ᰴť
		final JButton newButton_1 = new JButton("ע ��");
		newButton_1.setBounds(100, 198, 80, 30);
		getRootPane().setDefaultButton(newButton_1);
		contentPane.add(newButton_1);

		//���ذ�ť
		final JButton reButton = new JButton("�� ��");
		reButton.setBounds(250, 198, 80, 30);
		contentPane.add(reButton);
		
		//��ʾ��Ϣ
		newLabel = new JLabel();
		newLabel.setBounds(180, 240, 185, 20);
		newLabel.setForeground(Color.red);
		contentPane.add(newLabel);
		
		//���ذ�ť����
		reButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reButton.setEnabled(false);
				//���ص�½����
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
				exit();
			}
		});
		//ע�ᰴť����
		newButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Properties userPro = new Properties();
				File file = new File("User.properties");
				fileUtil.loadPro(userPro, file);
				
				String u_name = textField.getText();
				String u_pwd = new String(passwordField.getPassword());
				String u_pwd_ag = new String(passwordField_1.getPassword());

				// �ж��û����Ƿ�����ͨ�û����Ѵ���
				if (u_name.length() != 0) {
					
					if (userPro.containsKey(u_name)) {
						newLabel.setText("�û����Ѵ���!");
					} else {
						isPassword(userPro, file, u_name, u_pwd, u_pwd_ag);
					}
				} else {
					newLabel.setText("�û�������Ϊ�գ�");
				}
			}
			
			private void isPassword(Properties userPro,
					File file, String u_name, String u_pwd, String u_pwd_ag) {
				if (u_pwd.equals(u_pwd_ag)) {
					if (u_pwd.length() != 0) {
						userPro.setProperty(u_name, u_pwd_ag);
						try {
							userPro.store(new FileOutputStream(file),
									"");
							JOptionPane.showMessageDialog(contentPane, "��ϲ�㣬ע��ɹ���");
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						newButton_1.setEnabled(false);
						//���ص�½����
						LoginFrame fr = new LoginFrame();
						fr.setVisible(true);
						exit();
					} else {
						newLabel.setText("����Ϊ�գ�");
					}
				} else {
					newLabel.setText("���벻һ�£�");
				}
			}
		});
		
	}
	
	public void exit() {
		this.dispose();
	}
	
	private void jFrameValidate() {
		// TODO Auto-generated method stub
		Toolkit tk = getToolkit();
		Dimension dim = tk.getScreenSize();
		this.setResizable(false);
		this.setBounds(dim.width/2-225,dim.height/2-150,450,300);
		validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


	public void init() {
		
	}

}
