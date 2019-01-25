package zzt.model;

/**
 * ΩÃ “¿‡
 * 
 * @author zzt
 * 
 */
public class Classroom {
	private int cr_id;
	private String d_id;
	private String cr_name;
	private int ct_id;
	private int cr_seating;
	private int b_id;

	public Classroom(int cr_id, String d_id, String cr_name, int ct_id,
			int cr_seating, int b_id) {
		super();
		this.cr_id = cr_id;
		this.d_id = d_id;
		this.cr_name = cr_name;
		this.ct_id = ct_id;
		this.cr_seating = cr_seating;
		this.b_id = b_id;
	}

	public int getCr_id() {
		return cr_id;
	}

	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getCr_name() {
		return cr_name;
	}

	public void setCr_name(String cr_name) {
		this.cr_name = cr_name;
	}

	public int getCt_id() {
		return ct_id;
	}

	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}

	public int getCr_seating() {
		return cr_seating;
	}

	public void setCr_seating(int cr_seating) {
		this.cr_seating = cr_seating;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	@Override
	public String toString() {
		return cr_name;
	}

	@Override
	public boolean equals(Object obj) {
		return this.cr_name.equals(((Classroom) obj).cr_name);
	}
}
