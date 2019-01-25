package global.model;

/**
 * 课程实验室访问类
 * 
 * @author czy
 * 
 */
public class Courselaboratory {
	private int ul_id;
	private String cou_id;
	private String grade;
	private String Experimentalenvironment;
	private int cr_id;
	private int sl_id;

	public Courselaboratory(int ul_id, String cou_id, String grade,
			String experimentalenvironment, int cr_id, int sl_id) {
		super();
		this.ul_id = ul_id;
		this.cou_id = cou_id;
		this.grade = grade;
		Experimentalenvironment = experimentalenvironment;
		this.cr_id = cr_id;
		this.sl_id = sl_id;
	}

	public int getUl_id() {
		return ul_id;
	}

	public void setUl_id(int ul_id) {
		this.ul_id = ul_id;
	}

	public String getCou_id() {
		return cou_id;
	}

	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getExperimentalenvironment() {
		return Experimentalenvironment;
	}

	public void setExperimentalenvironment(String experimentalenvironment) {
		Experimentalenvironment = experimentalenvironment;
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

}
