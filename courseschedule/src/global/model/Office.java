package global.model;

public class Office {
	private String o_id;
	private String d_id;
	private String o_name;

	public Office(String o_id, String d_id, String o_name) {
		super();
		this.o_id = o_id;
		this.d_id = d_id;
		this.o_name = o_name;
	}

	public Office() {
		// TODO Auto-generated constructor stub
	}

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getO_name() {
		return o_name;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	@Override
	public String toString() {
		return o_name;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.o_name.equals(((Office) obj).o_name);
	}


}
