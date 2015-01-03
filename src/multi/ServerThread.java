package multi;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

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
}
