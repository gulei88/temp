package global.model;

/**
 * 实验分室类
 * 
 * @author Administrator
 * 
 */
public class Sublaboratory {
	private int sl_id;
	private int cr_id;
	private String sl_name;
	private int sl_seating;

	public Sublaboratory(int sl_id, int cr_id, String sl_name, int sl_seating) {
		super();
		this.sl_id = sl_id;
		this.cr_id = cr_id;
		this.sl_name = sl_name;
		this.sl_seating = sl_seating;
	}

	public int getSl_id() {
		return sl_id;
	}

	public void setSl_id(int sl_id) {
		this.sl_id = sl_id;
	}

	public int getCr_id() {
		return cr_id;
	}

	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}

	public String getSl_name() {
		return sl_name;
	}

	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}

	public int getSl_seating() {
		return sl_seating;
	}

	public void setSl_seating(int sl_seating) {
		this.sl_seating = sl_seating;
	}

	@Override
	public String toString() {
		return sl_name + "," + sl_seating + "人";
	}

}
