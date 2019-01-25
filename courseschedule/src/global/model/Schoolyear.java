package global.model;

public class Schoolyear {
	private int sy_id;
	private String sy_name;

	public Schoolyear(int sy_id, String sy_name) {
		super();
		this.sy_id = sy_id;
		this.sy_name = sy_name;
	}

	public int getSy_id() {
		return sy_id;
	}

	public void setSy_id(int sy_id) {
		this.sy_id = sy_id;
	}

	public String getSy_name() {
		return sy_name;
	}

	public void setSy_name(String sy_name) {
		this.sy_name = sy_name;
	}

	@Override
	public String toString() {
		return sy_name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.sy_name.equals(((Schoolyear) obj).sy_name);
	}
}
