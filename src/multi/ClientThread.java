package multi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * 客户端线程
 * @author huzhp
 *
 */
public class ClientThread extends Thread{
	private GamePanel gamepanel;//游戏开始的面板
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String playernames[] = new String[3];// 记录所有玩家的名字
	private Integer position;// 当前玩家进入游戏的位置，即第一个进入游戏的为0，第二个加入游戏的为1，第三个加入游戏的为2
	private PreparePanel prepare;//游戏开始前的面板
	private Boolean stop = false;

	public ClientThread(Socket socket, GamePanel gamepanel, PreparePanel prepare) {
		// 该类为游戏线程类，主要
		this.gamepanel = gamepanel;
		this.prepare = prepare;
		for (int i = 0; i < 3; i++) {
			playernames[i] = "";
		}
		try {

			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(this.prepare.getPlayernames()[0]);
			position = (Integer) ois.readObject();
			this.gamepanel.setPosition(position);
			this.gamepanel.setOos(this.oos);
			handlePlayerName();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 下面的方法用于计算其他出牌玩家在当前玩家面板上的位置
	private int countPosition(int n) {
		if (position == 0) {
			if (n == 2) {
				return 1;
			} else {
				return 2;
			}
		} else if (position == 1) {
			if (n == 2) {
				return 2;
			} else {
				return 1;
			}
		} else {
			if (n == 1) {
				return 1;
			} else {
				return 2;
			}
		}
	}

	private void handleLord() throws Exception {
		// 处理地主选择
		Integer lord = (Integer) ois.readObject();

		while (lord != -1) {
			if (lord == position) {
				gamepanel.showLordChoose(true);
			}
			lord = (Integer) ois.readObject();
		}
		gamepanel.setLastpokerIDVector((Vector<Integer>) ois.readObject());
		gamepanel.showLastPoker();

	}

	private void handleLordIS() throws Exception {
		// 如果当前玩家不是地主
		Integer lord = (Integer) ois.readObject();
		if (this.countPosition(lord) == 1) {

			this.gamepanel.getPlayer1pokerIDVector().addAll(
					gamepanel.getLastpokerIDVector());
			gamepanel.getLordLabels()[1].setVisible(true);
			gamepanel.showsSendButton(true);
			this.gamepanel.display();
		} else {

			gamepanel.getLordLabels()[2].setVisible(true);
			this.gamepanel.getPlayer2pokerIDVector().addAll(
					gamepanel.getLastpokerIDVector());
			gamepanel.showsSendButton(true);
			this.gamepanel.display();
		}
	}

	private void handleNewGame() throws Exception {
		// 处理新建游戏时的数据传输
		Vector<Integer> poker1IDVector = (Vector<Integer>) ois.readObject();
		Vector<Integer> poker2IDVector = (Vector<Integer>) ois.readObject();
		Vector<Integer> poker3IDVector = (Vector<Integer>) ois.readObject();
		Collections.sort(poker1IDVector);
		Collections.sort(poker2IDVector);
		Collections.sort(poker3IDVector);
		if (position == 0) {
			gamepanel.setMypokerIDVector(poker1IDVector);
			gamepanel.setPlayer1pokerIDVector(poker3IDVector);
			gamepanel.setPlayer2pokerIDVector(poker2IDVector);
		} else if (position == 1) {
			gamepanel.setMypokerIDVector(poker2IDVector);
			gamepanel.setPlayer1pokerIDVector(poker1IDVector);
			gamepanel.setPlayer2pokerIDVector(poker3IDVector);
		} else {
			gamepanel.setMypokerIDVector(poker3IDVector);
			gamepanel.setPlayer1pokerIDVector(poker2IDVector);
			gamepanel.setPlayer2pokerIDVector(poker1IDVector);
		}
		prepare.setVisible(false);
		gamepanel.setPlayernames(playernames);
		gamepanel.start();
		gamepanel.setVisible(true);
		handleLord();
	}

	private void handleNotSend() throws Exception {
		// 其他玩家选择不出
		Integer n = (Integer) ois.readObject();
		Integer id = (Integer) ois.readObject();
		Vector<Poker> pokerVector = new Vector<Poker>();
		pokerVector.add(new Poker(id));
		gamepanel.setNotSendNum(gamepanel.getNotSendNum() + 1);
		if (gamepanel.getNotSendNum() == 2) {
			gamepanel.getLastSendedPokerVector().clear();
		}
		if (this.countPosition(n) == 1) {
			gamepanel.showOtherSendPoker(pokerVector, 1);

		} else {
			gamepanel.showOtherSendPoker(pokerVector, 2);

		}
	}

	private void handlePlayerName() throws Exception {
		if (position == 0 || position == 2) {
			playernames[0] = prepare.getPlayernames()[0];

			for (int i = 2; i > 0; i--) {
				playernames[i] = (String) ois.readObject();
				this.prepare.setPlayernames(playernames);
				this.prepare.repaint();
			}

		} else {
			playernames[0] = prepare.getPlayernames()[0];
			for (int i = 1; i <= 2; i++) {
				playernames[i] = (String) ois.readObject();
				this.prepare.setPlayernames(playernames);
				this.prepare.repaint();
			}
		}
	}

	private void handleSendPoker(int n) throws Exception {
		// 处理其他玩家出牌，当前玩家面板上的响应
		Vector<Integer> pokerIDVector = (Vector<Integer>) ois.readObject();
		Vector<Poker> pokerVector = new Vector<Poker>();
		for (int i = 0; i < pokerIDVector.size(); i++) {
			pokerVector.add(new Poker(pokerIDVector.get(i)));
		}
		gamepanel.setLastSendedPokerVector(pokerVector);
		gamepanel.setNotSendNum(0);
		if (this.countPosition(n) == 1) {
			gamepanel.showOtherSendPoker(pokerVector, 1);
			this.gamepanel.getPlayer1pokerIDVector().removeAll(pokerIDVector);
			this.gamepanel.display();
		} else {
			gamepanel.showOtherSendPoker(pokerVector, 2);
			this.gamepanel.getPlayer2pokerIDVector().removeAll(pokerIDVector);
			this.gamepanel.display();
		}
	}

	private void handleWin() throws Exception {
		// 玩家取得胜利
		gamepanel.showsSendButton(false);
		String[] name = (String[]) ois.readObject();
		Vector<String> resultVector = (Vector<String>) ois.readObject();
		EndDialog endframe = new EndDialog();
		for (int i = 0; i < 3; i++) {
			endframe.getPlayer()[i].setText(name[i]);
			endframe.getCount()[i].setText(resultVector.get(i));
		}
		endframe.setVisible(true);
		stop = true;
	}

	private void handleYouAreLord() {
		// 如果当前玩家是地主，则刷新界面
		gamepanel.getMypokerIDVector().addAll(gamepanel.getLastpokerIDVector());
		Collections.sort(gamepanel.getMypokerIDVector());
		gamepanel.showsSendButton(true);
		gamepanel.getLordLabels()[0].setVisible(true);
		gamepanel.display();
	}

	private void handleYourTurn() {
		// 轮到当前玩家出牌
		gamepanel.setIsMyTurn(true);
	}

	public void run() {

		while (!stop) {
			try {
				String message = (String) ois.readObject();

				if (message.equals("sendedPoker")) {
					Integer n = (Integer) ois.readObject();
					this.handleSendPoker(n);
				} else if (message.equals("newGame")) {
					this.handleNewGame();
				} else if (message.equals("you are lord")) {
					this.handleYouAreLord();
				} else if (message.equals("lord is")) {
					this.handleLordIS();
				} else if (message.equals("your turn")) {
					this.handleYourTurn();
				} else if (message.equals("not send")) {
					this.handleNotSend();
				} else if (message.equals("win")) {
					this.handleWin();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "与服务器连接中断");
				System.exit(0);
			}
		}
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
	 * @return the ois
	 */
	public ObjectInputStream getOis() {
		return ois;
	}

	/**
	 * @param ois the ois to set
	 */
	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	/**
	 * @return the oos
	 */
	public ObjectOutputStream getOos() {
		return oos;
	}

	/**
	 * @param oos the oos to set
	 */
	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	/**
	 * @return the playernames
	 */
	public String[] getPlayernames() {
		return playernames;
	}

	/**
	 * @param playernames the playernames to set
	 */
	public void setPlayernames(String[] playernames) {
		this.playernames = playernames;
	}

	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
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

	/**
	 * @return the stop
	 */
	public Boolean getStop() {
		return stop;
	}

	/**
	 * @param stop the stop to set
	 */
	public void setStop(Boolean stop) {
		this.stop = stop;
	}
	
}