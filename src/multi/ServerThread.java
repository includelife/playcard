package multi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread{
	//上一位玩家所出牌的ID
	Vector<Integer> lastPokerIDVector = new Vector<Integer>();
	//记录地主
	Integer lord;
	// 所有玩家的输入流
	Vector<ObjectInputStream> oisVector = new Vector<ObjectInputStream>();
	// 所有玩家的输出流
	Vector<ObjectOutputStream> oosVector = new Vector<ObjectOutputStream>();
	//记录第一位玩家手牌的ID
	private Vector<Integer> player1PokerIDVector = new Vector<Integer>();
	//记录第二位玩家手牌的ID
	private Vector<Integer> player2PokerIDVector = new Vector<Integer>();
	//记录带三位玩家手牌的ID
	private Vector<Integer> player3PokerIDVector = new Vector<Integer>();
	// 记录所有玩家名字
	String[] playername = new String[3];
	//Server端
	private ServerSocket server;
	//stop为true时，ServerThread就停止
	private boolean stop = false;
	// 控制出牌顺序
	Integer turn;

	//构造方法
	public ServerThread(ServerSocket server) {
		//继承基类方法
		super();
		//接受从主方法返回的ServerSocket对象（Server端对象）
		this.server = server;
		for (int i = 0; i < 3; i++) {
			//初始化三位玩家的姓名
			playername[i] = "";
		}
	}

	private void handCall() throws Exception {
		// 如果当前玩家选择地主，则分别告知其他玩家，并让当前玩家出牌
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
		// 如果当前玩家放弃地主，则切换至下个玩家
		lord = (lord + 1) % 3;
		turn = lord;
		for (int j = 0; j < 3; j++) {
			oosVector.get(j).writeObject(lord);
		}
	}

	private void handleNotSend(ObjectInputStream in) throws Exception {
		// 当前玩家放弃出牌
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
		// 处理出牌信息
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
		// 玩家取得胜利，计算结果，并反馈给各个玩家
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
				//接受客户端的连接请求，并返回与该客户端连接的Socket实例
				Socket socket = server.accept();
				//获取该客户端的输入流
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
}
