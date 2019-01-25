package czy.model;
/**
 * 课程实验环境导出类（与课程实验环境管理界面table内数据字段一致）
 * @author czy
 *
 */
public class ExportExperimentalenvironment {
	private int ce_id;
	private String cou_id;
	private String cou_name;
	private int cou_theoryhour;
	private int cou_experimentalhours;
	private int cou_practicehour;
	private String grade;
	private String cla_name ;
	private String cla_number ;
	private String t_id;
	private String t_name;
	private String Experimentalenvironment;
	public ExportExperimentalenvironment(int ce_id, String cou_id,
			String cou_name, int cou_theoryhour, int cou_experimentalhours,
			int cou_practicehour, String grade, String cla_name,
			String cla_number, String t_id, String t_name,
			String experimentalenvironment) {
		super();
		this.ce_id = ce_id;
		this.cou_id = cou_id;
		this.cou_name = cou_name;
		this.cou_theoryhour = cou_theoryhour;
		this.cou_experimentalhours = cou_experimentalhours;
		this.cou_practicehour = cou_practicehour;
		this.grade = grade;
		this.cla_name = cla_name;
		this.cla_number = cla_number;
		this.t_id = t_id;
		this.t_name = t_name;
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
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public int getCou_theoryhour() {
		return cou_theoryhour;
	}
	public void setCou_theoryhour(int cou_theoryhour) {
		this.cou_theoryhour = cou_theoryhour;
	}
	public int getCou_experimentalhours() {
		return cou_experimentalhours;
	}
	public void setCou_experimentalhours(int cou_experimentalhours) {
		this.cou_experimentalhours = cou_experimentalhours;
	}
	public int getCou_practicehour() {
		return cou_practicehour;
	}
	public void setCou_practicehour(int cou_practicehour) {
		this.cou_practicehour = cou_practicehour;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public String getExperimentalenvironment() {
		return Experimentalenvironment;
	}
	public void setExperimentalenvironment(String experimentalenvironment) {
		Experimentalenvironment = experimentalenvironment;
	}
	
}
