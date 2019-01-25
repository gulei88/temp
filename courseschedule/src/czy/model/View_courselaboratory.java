package czy.model;
/**
 * 课程实验室对应关系类
 * @author czy
 *
 */
public class View_courselaboratory {
	private String cl_id; 
	private int ce_id; 
	private String sl_id;
	private String sl_name;
	private int sumsl_seating;
	private String sl_seating;
	private int cr_id;
	private String d_id;
	private String cr_name; 
	private int ct_id;
	private int cr_seating;
	private int b_id;
	public View_courselaboratory(String cl_id, int ce_id, String sl_id,
			String sl_name, int sumsl_seating, String sl_seating, int cr_id,
			String d_id, String cr_name, int ct_id, int cr_seating, int b_id) {
		super();
		this.cl_id = cl_id;
		this.ce_id = ce_id;
		this.sl_id = sl_id;
		this.sl_name = sl_name;
		this.sumsl_seating = sumsl_seating;
		this.sl_seating = sl_seating;
		this.cr_id = cr_id;
		this.d_id = d_id;
		this.cr_name = cr_name;
		this.ct_id = ct_id;
		this.cr_seating = cr_seating;
		this.b_id = b_id;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public int getCe_id() {
		return ce_id;
	}
	public void setCe_id(int ce_id) {
		this.ce_id = ce_id;
	}
	public String getSl_id() {
		return sl_id;
	}
	public void setSl_id(String sl_id) {
		this.sl_id = sl_id;
	}
	public String getSl_name() {
		return sl_name;
	}
	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}
	public int getSumsl_seating() {
		return sumsl_seating;
	}
	public void setSumsl_seating(int sumsl_seating) {
		this.sumsl_seating = sumsl_seating;
	}
	public String getSl_seating() {
		return sl_seating;
	}
	public void setSl_seating(String sl_seating) {
		this.sl_seating = sl_seating;
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
	
	
}
