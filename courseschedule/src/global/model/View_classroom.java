package global.model;

/**
 * ΩÃ “ ”Õº¿‡
 * 
 * @author ggc
 * 
 */
public class View_classroom {
	private String d_id;
	private String d_name;
	private int cr_id;
	private String cr_name;
	private String sl_name;
	private int seating;
	private int ct_id;
	private String ct_name;
	private int b_id;
	private String b_name;
	private String b_alias;
	private String b_address;

	public View_classroom(String d_id, String d_name, int cr_id,
			String cr_name, String sl_name, int seating, int ct_id,
			String ct_name, int b_id, String b_name, String b_alias,
			String b_address) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.cr_id = cr_id;
		this.cr_name = cr_name;
		this.sl_name = sl_name;
		this.seating = seating;
		this.ct_id = ct_id;
		this.ct_name = ct_name;
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_alias = b_alias;
		this.b_address = b_address;
	}

	public String getD_id() {
		return d_id;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public int getCr_id() {
		return cr_id;
	}

	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}

	public String getCr_name() {
		return cr_name;
	}

	public void setCr_name(String cr_name) {
		this.cr_name = cr_name;
	}

	public String getSl_name() {
		return sl_name;
	}

	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}

	public int getSeating() {
		return seating;
	}

	public void setSeating(int seating) {
		this.seating = seating;
	}

	public int getCt_id() {
		return ct_id;
	}

	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}

	public String getCt_name() {
		return ct_name;
	}

	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}

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
}
