package global.model;

/**
 * 教学任务对应班级
 * 
 * @author ggc
 * 
 */
public class Courseclass {
	private int tt_id;
	private String cla_id;

	public Courseclass(int tt_id, String cla_id) {
		super();
		this.tt_id = tt_id;
		this.cla_id = cla_id;
	}

	public int getTt_id() {
		return tt_id;
	}

	public String getCla_id() {
		return cla_id;
	}

	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}

	public void setCla_id(String cla_id) {
		this.cla_id = cla_id;
	}
}
