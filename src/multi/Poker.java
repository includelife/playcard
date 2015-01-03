 package multi;

import java.io.Serializable;


/**
 * �˿�����
 * @author huzhp
 *
 */
public class Poker implements Comparable<Poker>, Serializable {
	private Integer id;
	/*
	 * ����Ϊ�˿����࣬���Ƕ����˿��ƾ��л�ɫ����С��id�ţ��Լ���Ӧ��ͼƬ ����id����ÿһ���Ƶ�Ψһ��ʾ����ϵͳ�Զ��ַ��˿ˣ������紫�䣬��������
	 * ��Ӧ���õ������˿��Ƶ�id������Ҫ�õ��˿��Ƶ��������Եĵط�,������idΪ����
	 * ����һ���˿��ƣ��˿��ƵĶ�ӦͼƬ �����"images"�ļ�����
	 */
	private Integer value;

	// ����id����һ���˿�
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

	//�ж��������Ƿ�һ����һ���󣬾ͷ���true������ͷ���false
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