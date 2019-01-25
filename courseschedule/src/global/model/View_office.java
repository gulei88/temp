package global.model;

/**
 * ø∆ “ ”Õº¿‡
 * 
 * @author zzt
 * 
 */
public class View_office {
	private String d_id;
	private String d_name;
	private String o_id;
	private String o_name;
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getD_name() {
		return d_name;
	}
	public View_office(String d_id, String d_name, String o_id, String o_name) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.o_id = o_id;
		this.o_name = o_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	
	
}
