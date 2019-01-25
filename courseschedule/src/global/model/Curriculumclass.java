package global.model;

/**
 * 课程表对应班级
 * 
 * @author ggc
 * 
 */
public class Curriculumclass {
	private int cc_id;
	private String cla_id;

	public Curriculumclass(int cc_id, String cla_id) {
		super();
		this.cc_id = cc_id;
		this.cla_id = cla_id;
	}

	public int getCc_id() {
		return cc_id;
	}

	public String getCla_id() {
		return cla_id;
	}

	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
	}

	public void setCla_id(String cla_id) {
		this.cla_id = cla_id;
	}
}
