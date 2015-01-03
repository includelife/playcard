package multi;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

/**
 * ���ƹ���
 * @author huzhp
 *
 */
public class PokerRules {
	/**
	 * 
	 * @param lasttime ��ʾ����ϴεĳ���
	 * @param thistime ��ʾ�����εĳ���
	 * @return ����û��˴γ��Ʒ��Ϲ���ͷ���true������ͷ���
	 */
public static Boolean canSend(Vector<Poker> lasttime, Vector<Poker> thistime) {
	//�ϴγ�������Ϊ0���ͱ�ʾ�˴θ�����ǵ�һ�����Ƶģ�
	if (lasttime.size() == 0) {
		//����ڸ��ֵ�һ�����ƵľͲ���Ҫ���ϴγ��ƱȽ��ˣ�ֻ���ж��Ƿ�Ϸ��ĳ��Ƽ��ɣ�judgePokerType����0�ʹ������Ҹôγ���Ϊ���Ϸ��ĳ��ƣ�
		if (judgePokerType(thistime) != 0) {
			return true;
		} 
		else {
			return false;
		}
	}
	//�ϴγ���������Ϊ0
	else {
		//�������ϴγ��Ƶ�����������γ��Ƶ���������ͬ
		if (judgePokerType(lasttime) == judgePokerType(thistime)) {
			//����˴θ���ҵĵ������Ƿ�����ϴ���ҵ����ƣ��ͷ���true
			if (getMainPokerValue(thistime) > getMainPokerValue(lasttime)) {
				return true;
			}
			//�˴θ���ҵĵ������Ƿ�С���ϴ���ҵ����ƣ��ͷ���false
			else {
				return false;
			}
		}
		//�������ϴγ��Ƶ�����������γ��Ƶ����Ͳ���ͬ���ж��Ƿ�Ϊ�����ը����37��������ը����
		else if (judgePokerType(thistime) == 37) {
			return true;
		}
		//�������ҳ����������ϴ���ҵĳ��Ƶ����Ͳ�ͬ����������εĳ��Ʋ��ԣ��ͷ���false
		else {
			return false;
		}
	}
}

private static Integer countValue(Hashtable<Integer, Integer> hash) {
	// �ú������ڼ������Ƶ�ֵ����ֵ��ͬ�������������Ƶ�ֵ֮��
	Enumeration<Integer> enum1 = hash.keys();
	Vector<Integer> value = new Vector<Integer>();
	Vector<Integer> times = new Vector<Integer>();
	Integer mainValue = 0;
	while (enum1.hasMoreElements()) {
		if (times.size() == 0) {
			Integer key = enum1.nextElement();
			times.add(hash.get(key));
			value.add(key);
		} else {
			Integer key = enum1.nextElement();
			Integer val = hash.get(key);
			if (val > times.get(0)) {
				times.remove(0);
				value.remove(0);
				times.add(val);
				value.add(key);
			} else if (val == times.get(0)) {
				times.add(val);
				value.add(key);
			} else {

			}
		}

	}
	for (int i = 0; i < times.size(); i++) {
		mainValue += times.get(i) * value.get(i);
	}
	return mainValue;
}

private static Integer eighteenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 18; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 9) {
		if (ts2.last() == 2 && ts2.first() == 2 && isConseq(iter)) {
			return 31;
		} else {
			return 0;
		}
	} else if (ts.size() == 6) {
		if (ts2.last() == 3 && ts2.first() == 3 && isTriConseq(hash)) {
			return 32;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer eightPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 8; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();

	if (ts.size() == 4) {
		if (ts2.last() == 2 && isConseq(iter)) {
			return 14;
		} else if (ts2.last() == 3) {
			if (isTriConseq(hash)) {
				return 15;
			} else {
				return 0;
			}
		}

	} else if (ts.size() == 8 && isConseq(iter)) {
		return 16;
	}
	return 0;

}

private static Integer elevenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 11; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 11) {
		if (isConseq(iter)) {
			return 22;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer fifteenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 15; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	if (ts.size() == 5) {
		if (ts2.last() == 3 && ts2.first() == 3 && isTriConseq(hash)) {
			return 27;
		} else {
			return 0;
		}
	} else if (ts.size() == 6) {
		if (ts2.last() == 3 && ts2.first() == 2 && isTriConseq(hash)) {
			return 28;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}




private static Integer fourteenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 14; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 7) {
		if (ts2.last() == 2 && ts2.first() == 2 && isConseq(iter)) {
			return 26;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer getMainPokerValue(Vector<Poker> pokerVector) {
	/*
	 * ���ǰ����ÿһ�γ�������Ϊ�ֳ����ƺ͸��ƣ�����ͬ�ĵ�ֵ������������Ϊ����
	 * �����Ϊ���ƣ����ÿ��ֵ��ͬ���Ƶ�����Ҳһ���Ļ��������е��ƶ������ƣ��ж�ʱ
	 * �ȱȽ����ƵĴ�С�����ƴ�������ƴ�������ͬ��Ƚϸ��ƣ����ƴ�������ƴ�
	 */
	Hashtable<Integer, Integer> hash = samePokerNUM(pokerVector);
	return countValue(hash);

}

private static Boolean isConseq(Iterator<Integer> iter) {
	// �жϳ������Ƿ�Ϊ��������
	Integer current = iter.next();
	Integer next = 0;
	while (iter.hasNext()) {
		next = iter.next();
		if (next == 15) {
			return false;
		} else if (next - current == 1) {
			current = next;
		} else {
			return false;
		}
	}
	return true;
}

private static Boolean isTriConseq(Hashtable<Integer, Integer> hash) {
	// �жϳ������Ƿ�Ϊ��ö�
	Enumeration<Integer> enum1 = hash.keys();
	TreeSet<Integer> ts = new TreeSet<Integer>();
	Integer current;
	while (enum1.hasMoreElements()) {
		current = enum1.nextElement();
		if (hash.get(current) == 3) {
			ts.add(current);

		}

	}

	Iterator<Integer> iter1 = ts.iterator();
	if (isConseq(iter1)) {
		return true;
	} else {
		return false;
	}
}

//�ж��û��ĳ�������
private static Integer judgePokerType(Vector<Poker> pokerVector) {

	switch (pokerVector.size()) {
	case 1:
		return onePoker(pokerVector); //�ж��û�����һ�����Ƿ�Ϸ�
	case 2:
		return twoPoker(pokerVector);//�ж��û������������Ƿ�Ϸ�
	case 3:
		return threePoker(pokerVector);//�ж��û������������Ƿ�Ϸ�
	case 4:
		return fourPoker(pokerVector);//�ж��û������������Ƿ�Ϸ�
	case 5:
		return fivePoker(pokerVector);//�ж��û������������Ƿ�Ϸ�
	case 6:
		return sixPoker(pokerVector);
	case 7:
		return sevenPoker(pokerVector);
	case 8:
		return eightPoker(pokerVector);
	case 9:
		return ninePoker(pokerVector);
	case 10:
		return tenPoker(pokerVector);
	case 11:
		return elevenPoker(pokerVector);
	case 12:
		return twelvePoker(pokerVector);
	case 13:
		return thirteenPoker(pokerVector);
	case 14:
		return fourteenPoker(pokerVector);
	case 15:
		return fifteenPoker(pokerVector);
	case 16:
		return sixteenPoker(pokerVector);
	case 17:
		return seventeenPoker(pokerVector);
	case 18:
		return eighteenPoker(pokerVector);
	case 19:
		return nineteenPoker(pokerVector);
	case 20:
		return twentyPoker(pokerVector);
	default:
		return 0;  //�û����ƵĿ���ֻ��������Щ��������ǲ��Ϸ��ĳ��ƣ����Է���0
	}

}

    //�ж�1�����Ƿ�Ϸ��������س������ͣ����Ϸ��ͷ���0��
	private static int onePoker(Vector<Poker> pokerVector) {
		return 1;
	}

	//�ж�2�����Ƿ�Ϸ��������س������ͣ����Ϸ��ͷ���0��
	private static Integer twoPoker(Vector<Poker> pokerVector) {
		Poker poker1 = pokerVector.get(0);
		Poker poker2 = pokerVector.get(1);
		//�ж��������Ƿ���ͬ
		if (poker1.equals(poker2)) {
			return 2;
		}
		//�������ͱ�ʾ�ǻ��
		else if (poker1.getValue() + poker2.getValue() == 999) {
			return 37;
		}
		//�Ϸ��������Ƶ����ֻ�����������������Ķ�Ϊ���Ϸ��ĳ���
		else {
			return 0;
		}
	}

	//�ж�3�����Ƿ�Ϸ��������س������ͣ����Ϸ��ͷ���0��
	private static Integer threePoker(Vector<Poker> pokerVector) {
		Poker poker1 = pokerVector.get(0);
		Poker poker2 = pokerVector.get(1);
		Poker poker3 = pokerVector.get(2);
		//�ж��Ƿ�������ͬ��
		if (poker1.equals(poker2) && poker2.equals(poker3)) {
			return 3;
		} else {
			return 0;
		}

	}
	
	//�ж�4�����Ƿ�Ϸ��������س������ͣ����Ϸ��ͷ���0��
	private static Integer fourPoker(Vector<Poker> pokerVector) {
		TreeSet<Poker> ts = new TreeSet<Poker>();
		for (int i = 0; i < 4; i++) {
			ts.add(pokerVector.get(i));
		}
		//��������ͬ�����ش˳�������
		if (ts.size() == 1)
		{
			return 37;
		}
		//�����Ʋ���ȫ��ͬ����ֻ�������ƣ�˵��������һ��
		else if (ts.size() == 2)
		{
			Poker poker1 = pokerVector.get(0);
			Poker poker2 = pokerVector.get(1);
			Poker poker3 = pokerVector.get(2);
			Poker poker4 = pokerVector.get(3);
			if (poker1.equals(poker2)) 
			{
				if (poker1.equals(poker3))
				{
					return 4;
				} 
				else
				{
					if (poker1.equals(poker4))
					{
						return 4;
					}
				}
			}
			else
			{
				if (poker2.equals(poker3) && poker2.equals(poker4))
				{
					return 4;
				}
			}
		}
		//�������������������˵���ǲ��Ϸ��ĳ��ƣ�����0
		return 0;

	}
	
	//�ж�5�����Ƿ�Ϸ��������س������ͣ����Ϸ��ͷ���0��
	private static Integer fivePoker(Vector<Poker> pokerVector) {
		TreeSet<Integer> ts = new TreeSet<Integer>();
		TreeSet<Integer> ts2 = new TreeSet<Integer>();
		Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
		//�ж�����������ͬ���Ƽ����������Щ��Ϣ����һ��hash���з���
		hash = samePokerNUM(pokerVector);
		//��ȡhash�м���hash���У��������˿˵�ֵ����ö��
		Enumeration<Integer> enum1 = hash.keys();
		//enum1.hasMoreElements()����ö�ٻ��������ṩ����һ��ö��ֵʱ�ŷ���true
		while (enum1.hasMoreElements()) {
			ts2.add(hash.get(enum1.nextElement()));
		}

		for (int i = 0; i < 5; i++) {
			ts.add(pokerVector.get(i).getValue());
		}
		Iterator<Integer> iter = ts.iterator();
		if (ts.size() == 2) {
			if (ts2.last() == 4) {
				return 6;
			} else if (ts2.last() == 3) {
				return 5;
			}
		} else if (ts.size() == 5) {
			if (isConseq(iter)) {
				return 7;
			} else {
				return 0;
			}
		}
		return 0;

	}

	//�����Ƶĳ�������
   private static Integer ninePoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	//���ü���ֵ��ͬ���Ƶ�����������������ڹ�ϣ���У����ظù�ϣ��
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 9; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 3 && ts2.last() == 3) {
		if (isConseq(iter)) {
			return 17;
		} else {
			return 0;
		}
	} else if (ts.size() == 9) {
		if (isConseq(iter)) {
			return 18;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer nineteenPoker(Vector<Poker> pokerVector) {
	return 0;
}



// ����ֵ��ͬ���Ƶ�����
private static Hashtable<Integer, Integer> samePokerNUM(
		Vector<Poker> pokerVector) {
	// ����ֵ��ͬ���Ƶ�����
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	Vector<Integer> value = new Vector<Integer>();
	for (int i = 0; i < pokerVector.size(); i++) {
		value.add(pokerVector.get(i).getValue());

	}
	//֪�������е�Ԫ�ر�ȫ���Ƴ���ѭ��ֹͣ
	while (value.size() > 0) {
		int times = 0;
		Integer p = value.get(0);
		//�������������ָ����Ԫ�أ��򷵻� true��ֱ�������в��ٰ�����Ԫ�أ�ѭ��ֹͣ
		while (value.contains(p)) {
			times++;
			//���Ȼ�ȡ�����е�һ�γ��ֵ�ָ��Ԫ�ص����������û���֣��ͽ�����-1����Ȼ���ٴ��������Ƴ���Ԫ��
			value.remove(value.indexOf(p));
		}
		//���öԼ�-ֵ���뵽hash����ϣ����
		hash.put(p, times);
	}
	//����hash
	return hash;
}

private static Integer sevenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 7; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 7) {
		if (isConseq(iter)) {
			return 13;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer seventeenPoker(Vector<Poker> pokerVector) {
	return 0;
}

private static Integer sixPoker(Vector<Poker> pokerVector) {

	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 6; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 2) {
		if (ts2.last() == 4) {
			return 8;
		} else if (ts2.last() == 3) {
			if (ts.first() + 1 == ts.last()) {
				return 9;
			} else {
				return 0;
			}
		}
	} else if (ts.size() == 3) {
		if (ts2.last() == 4) {
			return 10;
		} else if (ts2.last() == 2) {
			if (isConseq(iter)) {

				return 11;
			} else {
				return 0;
			}
		}
	} else if (ts.size() == 6) {
		if (isConseq(iter)) {
			return 12;
		} else {
			return 0;
		}
	}
	return 0;
}

private static Integer sixteenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 16; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 8) {
		if (ts2.first() == 2 && ts2.last() == 2 && isConseq(iter)) {
			return 29;
		} else if (ts2.last() == 3 && ts.first() == 1 && isTriConseq(hash)) {
			return 30;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer tenPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 10; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();

	if (ts.size() == 4 && ts2.last() == 3) {
		if (ts2.first() == 2) {
			if (isTriConseq(hash)) {
				return 19;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	} else if (ts.size() == 5 && ts2.last() == 2) {
		if (isConseq(iter)) {
			return 20;
		} else {
			return 0;
		}
	} else if (ts.size() == 10) {
		if (isConseq(iter)) {
			return 21;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer thirteenPoker(Vector<Poker> pokerVector) {
	return 0;
}



private static Integer twelvePoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 12; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 6) {
		if (ts2.last() == 2 && ts2.first() == 2) {
			if (isConseq(iter)) {
				return 23;
			} else {
				return 0;
			}
		} else if (ts2.last() == 3 && ts2.first() == 1) {
			if (isTriConseq(hash)) {
				return 24;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	} else if (ts.size() == 12) {
		if (isConseq(iter)) {
			return 25;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

private static Integer twentyPoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	hash = samePokerNUM(pokerVector);
	Enumeration<Integer> enum1 = hash.keys();
	while (enum1.hasMoreElements()) {
		ts2.add(hash.get(enum1.nextElement()));
	}

	for (int i = 0; i < 20; i++) {
		ts.add(pokerVector.get(i).getValue());
	}
	Iterator<Integer> iter = ts.iterator();
	if (ts.size() == 10) {
		if (ts2.last() == 2 && ts2.first() == 2 && isConseq(iter)) {
			return 33;
		} else if (ts2.last() == 3 && ts2.first() == 1 && isTriConseq(hash)) {
			return 34;
		} else {
			return 0;
		}
	} else if (ts.size() == 8) {
		if (ts2.last() == 3 && ts2.first() == 2 && isTriConseq(hash)) {
			return 35;
		} else {
			return 0;
		}
	} else {
		return 0;
	}
}

}

