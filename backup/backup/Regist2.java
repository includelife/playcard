package backup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import db.DBQuery;
import db.DBWrite1;
import frame.LoginFrame;

public class Regist2 extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel newLabel;
	
	public Regist2() {
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
			private Properties scorePro;
			private File scorefile;

			public void actionPerformed(ActionEvent e) {
				String u_name = textField.getText();
				String u_pwd = new String(passwordField.getPassword());
				String u_pwd_ag = new String(passwordField_1.getPassword());				
//  /************************************�������ݿ�*****************************************************/
				if(u_name.length() != 0)
				{
					try {
						DBQuery userquery = new DBQuery(u_name);
						String pass = userquery.getPassword();
						//���벻Ϊ�գ�˵���û����Ѿ�����
						if(pass!=null)
						{
							newLabel.setText("�û����Ѵ���!");
						}else{
							isPassword2(u_name,u_pwd,u_pwd_ag);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					newLabel.setText("�û�������Ϊ�գ�");
				}
			}
				
				/**
				 * ��֤�û����������Ƿ�Ϸ�
				 * ע��
				 * @param u_name
				 * @param u_pwd
				 * @param u_pwd_ag
				 */
				private void isPassword2(String u_name, String u_pwd, String u_pwd_ag) {
					// TODO Auto-generated method stub
					if(u_pwd.equals(u_pwd_ag))
					{
						if(u_pwd.length()!=0)
						{
							try {
								DBWrite1 userWrite = new DBWrite1(u_name,u_pwd);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							newButton_1.setEnabled(false);
							//���ص�½����
							LoginFrame fr = new LoginFrame();
							fr.setVisible(true);
							exit();
						}else
						{
							newLabel.setText("����Ϊ�գ�");
						}
					}else
					{
						newLabel.setText("���벻һ�£�");
					}
				}
		});
						
//  /************************************�����ļ�******************************************************/				
//				Properties userPro = new Properties();
//				File file = new File("User.properties");
//				FileUtil.loadPro(userPro, file);
//				
//				scorePro = new Properties();
//				scorefile = new File("Score.properties");
//				FileUtil.loadPro(scorePro, scorefile);
//				
//				// �ж��û����Ƿ�����ͨ�û����Ѵ���
//				if (u_name.length() != 0) {
//					
//					if (userPro.containsKey(u_name)) {
//						newLabel.setText("�û����Ѵ���!");
//					} else {
//						isPassword(userPro, file, u_name, u_pwd, u_pwd_ag);
//					}
//				} else {
//					newLabel.setText("�û�������Ϊ�գ�");
//				}
//			}
//			
//			private void isPassword(Properties userPro,
//					File file, String u_name, String u_pwd, String u_pwd_ag) {
//				if (u_pwd.equals(u_pwd_ag)) {
//					if (u_pwd.length() != 0) {
//						userPro.setProperty(u_name, u_pwd_ag);
//						scorePro.setProperty(u_name, "0");
//						try {
//							userPro.store(new FileOutputStream(file),
//									"created by huzhp");
//							scorePro.store(new FileOutputStream(scorefile), "stored the scores");
//							JOptionPane.showMessageDialog(contentPane, "��ϲ�㣬ע��ɹ���");
//						} catch (FileNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} catch (IOException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						newButton_1.setEnabled(false);
//						//���ص�½����
//						LoginFrame fr = new LoginFrame();
//						fr.setVisible(true);
//						exit();
//					} else {
//						newLabel.setText("����Ϊ�գ�");
//					}
//				} else {
//					newLabel.setText("���벻һ�£�");
//				}
//			}
//		});
		
	}
	
	public void exit() {
		this.dispose();
	}
	
	/**
	 * �����ʼ��
	 */
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
		/**
		 * �պ���
		 */
	}

	/**
	 * @return the contentPane
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	/**
	 * @param contentPane the contentPane to set
	 */
	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	/**
	 * @return the textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * @param textField the textField to set
	 */
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @return the passwordField_1
	 */
	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}

	/**
	 * @param passwordField_1 the passwordField_1 to set
	 */
	public void setPasswordField_1(JPasswordField passwordField_1) {
		this.passwordField_1 = passwordField_1;
	}

	/**
	 * @return the newLabel
	 */
	public JLabel getNewLabel() {
		return newLabel;
	}

	/**
	 * @param newLabel the newLabel to set
	 */
	public void setNewLabel(JLabel newLabel) {
		this.newLabel = newLabel;
	}
	

}

