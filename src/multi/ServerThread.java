package multi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * ���������߳�
 * @author huzhp
 *
 */
public class ServerThread extends Thread{
	//��һλ��������Ƶ�ID
	Vector<Integer> lastPokerIDVector = new Vector<Integer>();
	//��¼����
	Integer lord;
	// ������ҵ�������
	Vector<ObjectInputStream> oisVector = new Vector<ObjectInputStream>();
	// ������ҵ������
	Vector<ObjectOutputStream> oosVector = new Vector<ObjectOutputStream>();
	//��¼��һλ������Ƶ�ID
	private Vector<Integer> player1PokerIDVector = new Vector<Integer>();
	//��¼�ڶ�λ������Ƶ�ID
	private Vector<Integer> player2PokerIDVector = new Vector<Integer>();
	//��¼����λ������Ƶ�ID
	private Vector<Integer> player3PokerIDVector = new Vector<Integer>();
	// ��¼�����������
	String[] playername = new String[3];
	//Server��
	private ServerSocket server;
	//stopΪtrueʱ��ServerThread��ֹͣ
	private boolean stop = false;
	// ���Ƴ���˳��
	Integer turn;

	//���췽��
	public ServerThread(ServerSocket server) {
		//�̳л��෽��
		super();
		//���ܴ����������ص�ServerSocket����Server�˶���
		this.server = server;
		for (int i = 0; i < 3; i++) {
			//��ʼ����λ��ҵ�����
			playername[i] = "";
		}
	}

	private void handCall() throws Exception {
		// �����ǰ���ѡ���������ֱ��֪������ң����õ�ǰ��ҳ���
		for (int i = 0; i < 3; i++) {
			oosVector.get(i).writeObject(-1);
			oosVector.get(i).writeObject(lastPokerIDVector);
			if (i == lord) {
				oosVector.get(i).writeObject("you are lord");
				oosVector.get(i).writeObject("your turn");
			} else {
				oosVector.get(i).writeObject("lord is");
				oosVector.get(i).writeObject(lord);
			}
		}
	}

	private void handleNotCall() throws Exception {
		// �����ǰ��ҷ������������л����¸����
		lord = (lord + 1) % 3;
		turn = lord;
		for (int j = 0; j < 3; j++) {
			oosVector.get(j).writeObject(lord);
		}
	}

	private void handleNotSend(ObjectInputStream in) throws Exception {
		// ��ǰ��ҷ�������
		Integer position = (Integer) in.readObject();
		Integer id = (Integer) in.readObject();
		for (int i = 0; i < oosVector.size(); i++) {
			if (i != position) {
				oosVector.get(i).writeObject("not send");
				oosVector.get(i).writeObject(position);
				oosVector.get(i).writeObject(id);
			}
		}
		turn = (turn + 1) % 3;
		oosVector.get(turn).writeObject("your turn");
	}

	private void handleSendPoker(ObjectInputStream in) throws Exception {
		// ���������Ϣ
		Integer position = (Integer) in.readObject();
		Vector<Integer> pokerIDVector = (Vector<Integer>) in.readObject();
		for (int i = 0; i < oosVector.size(); i++) {
			if (i != position) {
				this.sendPoker(oosVector.get(i), pokerIDVector, position);
			}
		}
		turn = (turn + 1) % 3;
		oosVector.get(turn).writeObject("your turn");
	}

	private void handleWin(ObjectInputStream in) throws Exception {
		// ���ȡ��ʤ���������������������������
		Integer n = (Integer) in.readObject();
		Vector<String> resultVector = new Vector<String>();
		for (int i = 0; i < 3; i++) {
			if (n.equals(lord)) {
				if (i == n) {
					resultVector.add(i, "Winner");
				} else {
					resultVector.add(i, "Loser");
				}
			} else {
				if (i == lord) {
					resultVector.add(i, "Loser");
				} else {
					resultVector.add(i, "Winner");
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			oosVector.get(i).writeObject("win");
			oosVector.get(i).writeObject(playername);
			oosVector.get(i).writeObject(resultVector);
		}
		stop = true;
	}

	public void newGame() throws Exception {
		Integer num = (int) (Math.random() * 3);
		lord = num;
		turn = lord;
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 0; i < 54; i++) {
			v.add(i, i + 1);
		}

		for (int i = 0; i < 3; i++) {
			Integer n = (int) (Math.random() * (54 - i));
			int id = v.get(n);
			lastPokerIDVector.add(id);
			v.remove(v.get(n));
		}

		for (Integer i = 51; i > 0; i--) {
			Integer n = (int) (Math.random() * i);
			Integer id = v.get(n);
			v.remove(v.get(n));
			switch (num) {
			case 0:
				player1PokerIDVector.add(id);
				break;
			case 1:
				player2PokerIDVector.add(id);
				break;
			case 2:
				player3PokerIDVector.add(id);
				break;
			default:
				break;

			}
			num = (num + 1) % 3;
		}

		for (int j = 0; j < 3; j++) {
			oosVector.get(j).writeObject("newGame");
			oosVector.get(j).writeObject(player1PokerIDVector);
			oosVector.get(j).writeObject(player2PokerIDVector);
			oosVector.get(j).writeObject(player3PokerIDVector);
			oosVector.get(j).writeObject(lord);
		}
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			try {
				//���ܿͻ��˵��������󣬲�������ÿͻ������ӵ�Socketʵ��
				Socket socket = server.accept();
				//��ȡ�ÿͻ��˵�������
				oisVector.add(new ObjectInputStream(socket.getInputStream()));
				oosVector.add(new ObjectOutputStream(socket.getOutputStream()));
				String name = (String) oisVector.get(i).readObject();
				playername[i] = name;
				oosVector.get(i).writeObject(i);
				for (int j = 0; j < i; j++) {

					oosVector.get(i).writeObject(playername[j]);
					oosVector.get(j).writeObject(name);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		try {
			this.newGame();
		} catch (Exception e1) {
		}
		while (!stop) {
			try {
				ObjectInputStream in = oisVector.get(turn);
				String message = (String) in.readObject();
				if (message.equals("sendedPoker")) {
					this.handleSendPoker(in);
				} else if (message.equals("notcall")) {
					this.handleNotCall();
				} else if (message.equals("call")) {
					this.handCall();
				} else if (message.equals("not send")) {
					this.handleNotSend(in);
				} else if (message.equals("win")) {
					this.handleWin(in);
				}
			} catch (Exception e) {

				stop = true;

			}
		}

	}

	public void sendPoker(ObjectOutputStream oos,
			Vector<Integer> pokerIDVector, int position) throws Exception {

		oos.writeObject("sendedPoker");
		oos.writeObject(position);
		oos.writeObject(pokerIDVector);

	}

	/**
	 * @return the lastPokerIDVector
	 */
	public Vector<Integer> getLastPokerIDVector() {
		return lastPokerIDVector;
	}

	/**
	 * @param lastPokerIDVector the lastPokerIDVector to set
	 */
	public void setLastPokerIDVector(Vector<Integer> lastPokerIDVector) {
		this.lastPokerIDVector = lastPokerIDVector;
	}

	/**
	 * @return the lord
	 */
	public Integer getLord() {
		return lord;
	}

	/**
	 * @param lord the lord to set
	 */
	public void setLord(Integer lord) {
		this.lord = lord;
	}

	/**
	 * @return the oisVector
	 */
	public Vector<ObjectInputStream> getOisVector() {
		return oisVector;
	}

	/**
	 * @param oisVector the oisVector to set
	 */
	public void setOisVector(Vector<ObjectInputStream> oisVector) {
		this.oisVector = oisVector;
	}

	/**
	 * @return the oosVector
	 */
	public Vector<ObjectOutputStream> getOosVector() {
		return oosVector;
	}

	/**
	 * @param oosVector the oosVector to set
	 */
	public void setOosVector(Vector<ObjectOutputStream> oosVector) {
		this.oosVector = oosVector;
	}

	/**
	 * @return the player1PokerIDVector
	 */
	public Vector<Integer> getPlayer1PokerIDVector() {
		return player1PokerIDVector;
	}

	/**
	 * @param player1PokerIDVector the player1PokerIDVector to set
	 */
	public void setPlayer1PokerIDVector(Vector<Integer> player1PokerIDVector) {
		this.player1PokerIDVector = player1PokerIDVector;
	}

	/**
	 * @return the player2PokerIDVector
	 */
	public Vector<Integer> getPlayer2PokerIDVector() {
		return player2PokerIDVector;
	}

	/**
	 * @param player2PokerIDVector the player2PokerIDVector to set
	 */
	public void setPlayer2PokerIDVector(Vector<Integer> player2PokerIDVector) {
		this.player2PokerIDVector = player2PokerIDVector;
	}

	/**
	 * @return the player3PokerIDVector
	 */
	public Vector<Integer> getPlayer3PokerIDVector() {
		return player3PokerIDVector;
	}

	/**
	 * @param player3PokerIDVector the player3PokerIDVector to set
	 */
	public void setPlayer3PokerIDVector(Vector<Integer> player3PokerIDVector) {
		this.player3PokerIDVector = player3PokerIDVector;
	}

	/**
	 * @return the playername
	 */
	public String[] getPlayername() {
		return playername;
	}

	/**
	 * @param playername the playername to set
	 */
	public void setPlayername(String[] playername) {
		this.playername = playername;
	}

	/**
	 * @return the server
	 */
	public ServerSocket getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(ServerSocket server) {
		this.server = server;
	}

	/**
	 * @return the stop
	 */
	public boolean isStop() {
		return stop;
	}

	/**
	 * @param stop the stop to set
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * @return the turn
	 */
	public Integer getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(Integer turn) {
		this.turn = turn;
	}
	
}
