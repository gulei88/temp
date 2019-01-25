package global.model;

/**
 * —°øŒ ”Õº¿‡
 * 
 * @author ggc
 * 
 */
public class View_courseclass {
	private int tt_id;
	private String cla_name;

	public View_courseclass(int tt_id, String cla_name, String cla_number) {
		super();
		this.tt_id = tt_id;
		this.cla_name = cla_name;
		this.cla_number = cla_number;
	}

	public int getTt_id() {
		return tt_id;
	}

	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}

	public String getCla_name() {
		return cla_name;
	}

	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}

	public String getCla_number() {
		return cla_number;
	}

	public void setCla_number(String cla_number) {
		this.cla_number = cla_number;
	}

	private String cla_number;
}
