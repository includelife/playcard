package multi;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

/**
 * 出牌规则
 * @author huzhp
 *
 */
public class PokerRules {
	/**
	 * 
	 * @param lasttime 表示玩家上次的出牌
	 * @param thistime 表示玩家这次的出牌
	 * @return 如果用户此次出牌符合规则就返回true，否则就返回
	 */
public static Boolean canSend(Vector<Poker> lasttime, Vector<Poker> thistime) {
	//上次出牌张数为0（就表示此次该玩家是第一个出牌的）
	if (lasttime.size() == 0) {
		//玩家在该轮第一个出牌的就不需要与上次出牌比较了，只需判断是否合法的出牌即可（judgePokerType返回0就代表该玩家该次出牌为不合法的出牌）
		if (judgePokerType(thistime) != 0) {
			return true;
		} 
		else {
			return false;
		}
	}
	//上次出牌张数不为0
	else {
		//如果玩家上次出牌的类型是与这次出牌的类型是相同
		if (judgePokerType(lasttime) == judgePokerType(thistime)) {
			//如果此次该玩家的的主牌是否大于上次玩家的主牌，就返回true
			if (getMainPokerValue(thistime) > getMainPokerValue(lasttime)) {
				return true;
			}
			//此次该玩家的的主牌是否小于上次玩家的主牌，就返回false
			else {
				return false;
			}
		}
		//如果玩家上次出牌的类型是与这次出牌的类型不相同，判断是否为火箭或炸弹（37代表火箭或炸弹）
		else if (judgePokerType(thistime) == 37) {
			return true;
		}
		//如果该玩家出牌类型与上次玩家的出牌的类型不同，则该玩家这次的出牌不对，就返回false
		else {
			return false;
		}
	}
}

private static Integer countValue(Hashtable<Integer, Integer> hash) {
	// 该函数用于计算主牌的值，即值相同并且张数最多的牌的值之和
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
	 * 我们把玩家每一次出的牌人为分成主牌和副牌，即相同的的值的张数最多的牌为主牌
	 * 其余的为副牌，如果每种值相同的牌的张数也一样的话，那所有的牌都是主牌，判断时
	 * 先比较主牌的大小，主牌大则该手牌大，主牌相同则比较副牌，副牌大则该手牌大。
	 */
	Hashtable<Integer, Integer> hash = samePokerNUM(pokerVector);
	return countValue(hash);

}

private static Boolean isConseq(Iterator<Integer> iter) {
	// 判断出的牌是否为单张连牌
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
	// 判断出的牌是否为姊妹对
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

//判断用户的出牌类型
private static Integer judgePokerType(Vector<Poker> pokerVector) {

	switch (pokerVector.size()) {
	case 1:
		return onePoker(pokerVector); //判断用户出的一张牌是否合法
	case 2:
		return twoPoker(pokerVector);//判断用户出的两张牌是否合法
	case 3:
		return threePoker(pokerVector);//判断用户出的三张牌是否合法
	case 4:
		return fourPoker(pokerVector);//判断用户出的四张牌是否合法
	case 5:
		return fivePoker(pokerVector);//判断用户出的五张牌是否合法
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
		return 0;  //用户出牌的可能只用上面这些，否则就是不合法的出牌，所以返回0
	}

}

    //判断1张牌是否合法，并返回出牌类型（不合法就返回0）
	private static int onePoker(Vector<Poker> pokerVector) {
		return 1;
	}

	//判断2张牌是否合法，并返回出牌类型（不合法就返回0）
	private static Integer twoPoker(Vector<Poker> pokerVector) {
		Poker poker1 = pokerVector.get(0);
		Poker poker2 = pokerVector.get(1);
		//判断两张牌是否相同
		if (poker1.equals(poker2)) {
			return 2;
		}
		//该种类型表示是火箭
		else if (poker1.getValue() + poker2.getValue() == 999) {
			return 37;
		}
		//合法的两张牌的组合只有以上两种情况，别的都为不合法的出牌
		else {
			return 0;
		}
	}

	//判断3张牌是否合法，并返回出牌类型（不合法就返回0）
	private static Integer threePoker(Vector<Poker> pokerVector) {
		Poker poker1 = pokerVector.get(0);
		Poker poker2 = pokerVector.get(1);
		Poker poker3 = pokerVector.get(2);
		//判断是否是三张同牌
		if (poker1.equals(poker2) && poker2.equals(poker3)) {
			return 3;
		} else {
			return 0;
		}

	}
	
	//判断4张牌是否合法，并返回出牌类型（不合法就返回0）
	private static Integer fourPoker(Vector<Poker> pokerVector) {
		TreeSet<Poker> ts = new TreeSet<Poker>();
		for (int i = 0; i < 4; i++) {
			ts.add(pokerVector.get(i));
		}
		//四张牌相同，返回此出牌类型
		if (ts.size() == 1)
		{
			return 37;
		}
		//四张牌不完全相同，且只用两种牌（说明是三带一）
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
		//不是上面两种情况，就说明是不合法的出牌，返回0
		return 0;

	}
	
	//判断5张牌是否合法，并返回出牌类型（不合法就返回0）
	private static Integer fivePoker(Vector<Poker> pokerVector) {
		TreeSet<Integer> ts = new TreeSet<Integer>();
		TreeSet<Integer> ts2 = new TreeSet<Integer>();
		Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
		//判断五张牌中相同的牌及其个数，这些信息放在一个hash表中返回
		hash = samePokerNUM(pokerVector);
		//获取hash中键（hash表中，键就是扑克的值）的枚举
		Enumeration<Integer> enum1 = hash.keys();
		//enum1.hasMoreElements()当该枚举还可以提提供至少一个枚举值时才返回true
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

	//九张牌的出牌类型
   private static Integer ninePoker(Vector<Poker> pokerVector) {
	TreeSet<Integer> ts = new TreeSet<Integer>();
	TreeSet<Integer> ts2 = new TreeSet<Integer>();
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	//调用计算值相同的牌的张数，并将结果放在哈希表中，返回该哈希表
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



// 计算值相同的牌的张数
private static Hashtable<Integer, Integer> samePokerNUM(
		Vector<Poker> pokerVector) {
	// 计算值相同的牌的张数
	Hashtable<Integer, Integer> hash = new Hashtable<Integer, Integer>();
	Vector<Integer> value = new Vector<Integer>();
	for (int i = 0; i < pokerVector.size(); i++) {
		value.add(pokerVector.get(i).getValue());

	}
	//知道向量中的元素被全部移除，循环停止
	while (value.size() > 0) {
		int times = 0;
		Integer p = value.get(0);
		//如果此向量包含指定的元素，则返回 true，直到向量中不再包含该元素，循环停止
		while (value.contains(p)) {
			times++;
			//首先获取向量中第一次出现的指定元素的索引（如果没出现，就将返回-1），然后再从向量中移除该元素
			value.remove(value.indexOf(p));
		}
		//将该对键-值放入到hash（哈希表）中
		hash.put(p, times);
	}
	//返回hash
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

