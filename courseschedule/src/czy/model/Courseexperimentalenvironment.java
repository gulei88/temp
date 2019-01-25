package czy.model;

public class Courseexperimentalenvironment {
	private int ce_id;
	private String cou_id;
	private String grade;
	private String Experimentalenvironment;

	public Courseexperimentalenvironment(String cou_id, String grade,
			String experimentalenvironment) {
		super();
		this.cou_id = cou_id;
		this.grade = grade;
		Experimentalenvironment = experimentalenvironment;
	}

	public Courseexperimentalenvironment(int ce_id, String cou_id,
			String grade, String experimentalenvironment) {
		super();
		this.ce_id = ce_id;
		this.cou_id = cou_id;
		this.grade = grade;
		Experimentalenvironment = experimentalenvironment;
	}

	public int getCe_id() {
		return ce_id;
	}

	public void setCe_id(int ce_id) {
		this.ce_id = ce_id;
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

}
