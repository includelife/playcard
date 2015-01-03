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
 * 多人模式游戏主框架
 * @author huzhp
 *
 */
public class GameFrame extends JFrame{
	
	public GameFrame(){
		super();
	}
	/**
	 * 开始多人模式
	 */
	public void MultiStart() {
		// 程序主函数
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String name = UserBean.getName();
//				String pname = JOptionPane.showInputDialog(s);
				if (name != null) {
					GameFrame inst = new GameFrame(name);
					inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					inst.setTitle("多人斗地主");
					inst.setLocationRelativeTo(null);
					inst.setVisible(true);
				}
				
			}
		});
	}

	private JMenu game;// 游戏菜单
	private GamePanel gamepanel;// 游戏面板
	private JMenuBar menubar;// 菜单栏
	private String name;// /当前玩家名字
	private JMenuItem newGame, addGame, exit;// 菜单项
	private PreparePanel prepare;// 准备面板

	public GameFrame(String name) {
		this.name = name;
		initGUI();
	}

	private void initGUI() {
		// 添加各个功能模块,并初始化其状态
		try {
			/*public void setResizable(boolean resizable)设置此窗体是否可由用户调整大小。 
                                   参数：
			resizable - 如果此窗体是可调整大小的，则为 true；否则为 false。*/
            this.setResizable(false);
            //设置游戏界面面板
			{
				this.setLayout(null);
				gamepanel = new GamePanel(830, 620);
				gamepanel.setLocation(0, 0);
				gamepanel.setSize(830, 620);
				/*设置窗口相对于指定组件的位置。 
				如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。*/
				gamepanel.setVisible(false);
				getContentPane().add(gamepanel);

			}
			//设置游戏准备面板
			{
				prepare = new PreparePanel(name, 830, 620);
				prepare.setLocation(0, 0);
				prepare.setSize(830, 620);
				getContentPane().add(prepare);

			}
			{
				menubar = new JMenuBar();
				game = new JMenu("游戏");
				newGame = new JMenuItem("新游戏");
				addGame = new JMenuItem("加入游戏");
				exit = new JMenuItem("退出");
				game.add(newGame);
				//菜单项“新游戏”注册事件
				newGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						try {
							//让用户输入信息对话框，并指定初始值6000
							String serverPort = JOptionPane.showInputDialog(
									"请输入端口号", 6000);
							startServerThread(serverPort);
							String serverIP = InetAddress.getLocalHost()
									.getHostAddress();
							startClientThread(serverIP, serverPort);
							//加入游戏按钮不可用
							addGame.setEnabled(false);
							//新建游戏不可用
							newGame.setEnabled(false);
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "新建游戏失败");
						}

					}
				});

				game.add(addGame);
				//菜单项“加入游戏”注册事件
				addGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							//定义对话框对象，玩家加入游戏时的界面
							AddDialog inst = new AddDialog();
							//设置inst为模式对话框
							inst.setModal(true);
							/*设置窗口相对于指定组件的位置。 
							如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。*/
                            inst.setLocationRelativeTo(null);
							inst.setVisible(true);
							//获取玩家输入的服务端IP地址
							String serverIP = inst.getIpAdress().getText();
							//获取玩家输入的服务端的端口号
							String serverPort = inst.getPort().getText();
							startClientThread(serverIP, serverPort);
							addGame.setEnabled(false);
							newGame.setEnabled(false);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "加入游戏失败");
						}

					}
				});
				
				game.add(exit);
				//菜单项“退出”注册事件
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//关闭窗体
						System.exit(0);
					}
				});
				menubar.add(game);
				//将菜单栏添加到窗体中
				this.setJMenuBar(menubar);
			}
			setSize(830, 620);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//开启服务端，等待客户端发出连接请求
	private void startServerThread(String serverPort) throws Exception {
		ServerSocket server = new ServerSocket(new Integer(serverPort));
		prepare.setIsConnect(true);
		prepare.repaint();
		ServerThread serverthread = new ServerThread(server);
		serverthread.start();
	}
	
    private void startClientThread(String serverIP, String serverPort)
			throws Exception {
    	//定义客户端对象
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

