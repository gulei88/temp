package global.model;

/**
 * ≤„¥Œ¿‡
 * 
 * @author ggc
 * 
 */
public class Level {
	private int l_id;
	private String l_name;

	public Level(int l_id, String l_name) {
		super();
		this.l_id = l_id;
		this.l_name = l_name;
	}

	public int getL_id() {
		return l_id;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String toString() {
		return l_name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.l_name.equals(((Level) obj).l_name);
	}
}
