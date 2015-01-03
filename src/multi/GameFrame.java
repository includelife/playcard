package multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Main.UserBean;
import frame.LoginFrame;


/**
 * ����ģʽ��Ϸ�����
 * @author huzhp
 *
 */
public class GameFrame extends JFrame{
	
	public GameFrame(){
		super();
	}
	/**
	 * ��ʼ����ģʽ
	 */
	public void MultiStart() {
		// ����������
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String name = UserBean.getName();
//				String pname = JOptionPane.showInputDialog(s);
				if (name != null) {
					GameFrame inst = new GameFrame(name);
					inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					inst.setTitle("���˶�����");
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
				
			}
		});
	}

	private JMenu game;// ��Ϸ�˵�
	private GamePanel gamepanel;// ��Ϸ���
	private JMenuBar menubar;// �˵���
	private String name;// /��ǰ�������
	private JMenuItem newGame, addGame, exit;// �˵���
	private PreparePanel prepare;// ׼�����

	public GameFrame(String name) {
		this.name = name;
		initGUI();
	}

	private void initGUI() {
		// ��Ӹ�������ģ��,����ʼ����״̬
		try {
			/*public void setResizable(boolean resizable)���ô˴����Ƿ�����û�������С�� 
                                   ������
			resizable - ����˴����ǿɵ�����С�ģ���Ϊ true������Ϊ false��*/
            this.setResizable(false);
            //������Ϸ�������
			{
				this.setLayout(null);
				gamepanel = new GamePanel(830, 620);
				gamepanel.setLocation(0, 0);
				gamepanel.setSize(830, 620);
				/*���ô��������ָ�������λ�á� 
				��������ǰδ��ʾ������ c Ϊ null����˴��ڽ�������Ļ�����롣*/
				gamepanel.setVisible(false);
				getContentPane().add(gamepanel);

			}
			//������Ϸ׼�����
			{
				prepare = new PreparePanel(name, 830, 620);
				prepare.setLocation(0, 0);
				prepare.setSize(830, 620);
				getContentPane().add(prepare);

			}
			{
				menubar = new JMenuBar();
				game = new JMenu("��Ϸ");
				newGame = new JMenuItem("����Ϸ");
				addGame = new JMenuItem("������Ϸ");
				exit = new JMenuItem("�˳�");
				game.add(newGame);
				//�˵������Ϸ��ע���¼�
				newGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						try {
							//���û�������Ϣ�Ի��򣬲�ָ����ʼֵ6000
							String serverPort = JOptionPane.showInputDialog(
									"������˿ں�", 6000);
							startServerThread(serverPort);
							String serverIP = InetAddress.getLocalHost()
									.getHostAddress();
							startClientThread(serverIP, serverPort);
							//������Ϸ��ť������
							addGame.setEnabled(false);
							//�½���Ϸ������
							newGame.setEnabled(false);
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "�½���Ϸʧ��");
						}

					}
				});

				game.add(addGame);
				//�˵��������Ϸ��ע���¼�
				addGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							//����Ի��������Ҽ�����Ϸʱ�Ľ���
							AddDialog inst = new AddDialog();
							//����instΪģʽ�Ի���
							inst.setModal(true);
							/*���ô��������ָ�������λ�á� 
							��������ǰδ��ʾ������ c Ϊ null����˴��ڽ�������Ļ�����롣*/
                            inst.setLocationRelativeTo(null);
							inst.setVisible(true);
							//��ȡ�������ķ����IP��ַ
							String serverIP = inst.getIpAdress().getText();
							//��ȡ�������ķ���˵Ķ˿ں�
							String serverPort = inst.getPort().getText();
							startClientThread(serverIP, serverPort);
							addGame.setEnabled(false);
							newGame.setEnabled(false);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "������Ϸʧ��");
						}

					}
				});
				
				game.add(exit);
				//�˵���˳���ע���¼�
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//�رմ���
						System.exit(0);
					}
				});
				menubar.add(game);
				//���˵�����ӵ�������
				this.setJMenuBar(menubar);
			}
			setSize(830, 620);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��������ˣ��ȴ��ͻ��˷�����������
	private void startServerThread(String serverPort) throws Exception {
		ServerSocket server = new ServerSocket(new Integer(serverPort));
		prepare.setIsConnect(true);
		prepare.repaint();
		ServerThread serverthread = new ServerThread(server);
		serverthread.start();
	}
	
    private void startClientThread(String serverIP, String serverPort)
			throws Exception {
    	//����ͻ��˶���
		Socket socket = new Socket(serverIP, new Integer(serverPort));
		prepare.setIsConnect(true);
		prepare.repaint();
		ClientThread clientThread = new ClientThread(socket, gamepanel, prepare);
		clientThread.start();
	}
	/**
	 * @return the game
	 */
	public JMenu getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(JMenu game) {
		this.game = game;
	}
	/**
	 * @return the gamepanel
	 */
	public GamePanel getGamepanel() {
		return gamepanel;
	}
	/**
	 * @param gamepanel the gamepanel to set
	 */
	public void setGamepanel(GamePanel gamepanel) {
		this.gamepanel = gamepanel;
	}
	/**
	 * @return the menubar
	 */
	public JMenuBar getMenubar() {
		return menubar;
	}
	/**
	 * @param menubar the menubar to set
	 */
	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the newGame
	 */
	public JMenuItem getNewGame() {
		return newGame;
	}
	/**
	 * @param newGame the newGame to set
	 */
	public void setNewGame(JMenuItem newGame) {
		this.newGame = newGame;
	}
	/**
	 * @return the addGame
	 */
	public JMenuItem getAddGame() {
		return addGame;
	}
	/**
	 * @param addGame the addGame to set
	 */
	public void setAddGame(JMenuItem addGame) {
		this.addGame = addGame;
	}
	/**
	 * @return the exit
	 */
	public JMenuItem getExit() {
		return exit;
	}
	/**
	 * @param exit the exit to set
	 */
	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}
	/**
	 * @return the prepare
	 */
	public PreparePanel getPrepare() {
		return prepare;
	}
	/**
	 * @param prepare the prepare to set
	 */
	public void setPrepare(PreparePanel prepare) {
		this.prepare = prepare;
	}
    
}

