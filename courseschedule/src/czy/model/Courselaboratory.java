package czy.model;

public class Courselaboratory {
	private int cl_id ;
	private int ce_id;
	private int cr_id;
	private int sl_id;
	private String sl_idString;
	public Courselaboratory(int cl_id, int ce_id, int cr_id, int sl_id) {
		super();
		this.cl_id = cl_id;
		this.ce_id = ce_id;
		this.cr_id = cr_id;
		this.sl_id = sl_id;
	}
	public Courselaboratory(int cl_id, int ce_id, int cr_id,String sl_idString) {
		super();
		this.cl_id = cl_id;
		this.ce_id = ce_id;
		this.cr_id = cr_id;
		this.sl_idString=sl_idString;
	}
	public int getCl_id() {
		return cl_id;
	}
	public void setCl_id(int cl_id) {
		this.cl_id = cl_id;
	}
	public int getCe_id() {
		return ce_id;
	}
	public void setCe_id(int ce_id) {
		this.ce_id = ce_id;
	}
	public int getCr_id() {
		return cr_id;
	}
	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}
	public int getSl_id() {
		return sl_id;
	}
	public void setSl_id(int sl_id) {
		this.sl_id = sl_id;
	}
	public String getSl_idString() {
		return sl_idString;
	}
	public void setSl_idString(String sl_idString) {
		this.sl_idString = sl_idString;
	}
	
}
