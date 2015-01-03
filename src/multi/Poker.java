 package multi;

import java.io.Serializable;


/**
 * 扑克牌类
 * @author huzhp
 *
 */
public class Poker implements Comparable<Poker>, Serializable {
	private Integer id;
	/*
	 * 该类为扑克牌类，我们定义扑克牌具有花色，大小，id号，以及对应的图片 其中id号是每一张牌的唯一标示，从系统自动分发扑克，到网络传输，甚至界面
	 * 响应都用到的是扑克牌的id，在需要用到扑克牌的其他属性的地方,我们以id为参数
	 * 构造一张扑克牌，扑克牌的对应图片 存放在"images"文件夹下
	 */
	private Integer value;

	// 根据id生成一张扑克
	public Poker(Integer id) {
		this.id = id;
		if (id == 0) {
			this.value = 0;
		} else if (id == 53) {
			this.value = 499;
		} else if (id == 54) {
			this.value = 500;
		} else if (id > 0 && id < 53) {
			this.value = (id - 1) / 4 + 3;
		}
	}

	public int compareTo(Poker o) {
		return this.value.compareTo(o.value);
	}

	//判断两张牌是否一样大，一样大，就返回true，否则就返回false
	@Override
	public boolean equals(Object obj) {
		Poker poker = (Poker) obj;
		if (this.value.equals(poker.value)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	
}