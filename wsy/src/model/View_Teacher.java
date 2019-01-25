package model;

public class View_Teacher {
	private String d_id;
	private String d_name;
	private String o_id;
	private String o_name;
	private String t_id;
	private String t_name;
	private String t_password;
	private String t_power;
	private String t_tel;
	public View_Teacher(String d_id, String d_name, String o_id, String o_name,
			String t_id, String t_name, String t_password, String t_power,
			String t_tel) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.o_id = o_id;
		this.o_name = o_name;
		this.t_id = t_id;
		this.t_name = t_name;
		this.t_password = t_password;
		this.t_power = t_power;
		this.t_tel = t_tel;
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
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_password() {
		return t_password;
	}
	public void setT_password(String t_password) {
		this.t_password = t_password;
	}
	public String getT_power() {
		return t_power;
	}
	public void setT_power(String t_power) {
		this.t_power = t_power;
	}
	public String getT_tel() {
		return t_tel;
	}
	public void setT_tel(String t_tel) {
		this.t_tel = t_tel;
	}
	
	
}
