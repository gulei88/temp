package global.model;

/**
 * ½ÌÑ§Â¥Àà
 * 
 * @author zzt
 * 
 */

public class Buliding {
	private int b_id;
	private String b_name;
	private String b_alias;

	public Buliding(int b_id, String b_name, String b_alias, String b_address) {
		super();
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_alias = b_alias;
		this.b_address = b_address;
	}

	public Buliding() {
		// TODO Auto-generated constructor stub
	}

	private String b_address;

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_alias() {
		return b_alias;
	}

	public void setB_alias(String b_alias) {
		this.b_alias = b_alias;
	}

	public String getB_address() {
		return b_address;
	}

	public void setB_address(String b_address) {
		this.b_address = b_address;
	}

	@Override
	public String toString() {
		return b_name;
	}
	
	
	
}
