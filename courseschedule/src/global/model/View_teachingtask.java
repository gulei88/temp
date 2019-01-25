package global.model;

/**
 * 教学任务视图类
 * 
 * @author zzt
 * 
 */
public class View_teachingtask {
	private int tt_id;
	private String cou_id;
	private String cou_category;
	private String cou_name;
	private float cou_credit;
	private int cou_theoryhour;
	private int cou_experimentalhours;
	private int cou_practicehour;
	private int cou_semester;
	private int cou_type;
	private String cla_name;
	private int cla_number;
	private String t_id;
	private String t_name;
	private String t_tel;
	private int sy_id;
	private String sy_name;
	private String multimedia;
	private String m_id;
	private String tt_grade;
	private String Practicescheduling;
	private int tt_state;

	public View_teachingtask(int tt_id, String cou_id, String cou_category,
			String cou_name, float cou_credit, int cou_theoryhour,
			int cou_experimentalhours, int cou_practicehour, int cou_semester,
			int cou_type, String cla_name, int cla_number, String t_id,
			String t_name, String t_tel, int sy_id, String sy_name,
			String multimedia, String m_id, String tt_grade,
			String Practicescheduling, int tt_state) {
		super();
		this.tt_id = tt_id;
		this.cou_id = cou_id;
		this.cou_category = cou_category;
		this.cou_name = cou_name;
		this.cou_credit = cou_credit;
		this.cou_theoryhour = cou_theoryhour;
		this.cou_experimentalhours = cou_experimentalhours;
		this.cou_practicehour = cou_practicehour;
		this.cou_semester = cou_semester;
		this.cou_type = cou_type;
		this.cla_name = cla_name;
		this.cla_number = cla_number;
		this.t_id = t_id;
		this.t_name = t_name;
		this.t_tel = t_tel;
		this.sy_id = sy_id;
		this.sy_name = sy_name;
		this.multimedia = multimedia;
		this.m_id = m_id;
		this.tt_grade = tt_grade;
		this.Practicescheduling = Practicescheduling;
		this.tt_state = tt_state;
	}

	public int getTt_id() {
		return tt_id;
	}

	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}

	public String getCou_id() {
		return cou_id;
	}

	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}

	public String getCou_category() {
		return cou_category;
	}

	public void setCou_category(String cou_category) {
		this.cou_category = cou_category;
	}

	public String getCou_name() {
		return cou_name;
	}

	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}

	public float getCou_credit() {
		return cou_credit;
	}

	public void setCou_credit(float cou_credit) {
		this.cou_credit = cou_credit;
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

	public int getCou_semester() {
		return cou_semester;
	}

	public void setCou_semester(int cou_semester) {
		this.cou_semester = cou_semester;
	}

	public int getCou_type() {
		return cou_type;
	}

	public void setCou_type(int cou_type) {
		this.cou_type = cou_type;
	}

	public String getCla_name() {
		return cla_name;
	}

	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}

	public int getCla_number() {
		return cla_number;
	}

	public void setCla_number(int cla_number) {
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

	public String getT_tel() {
		return t_tel;
	}

	public void setT_tel(String t_tel) {
		this.t_tel = t_tel;
	}

	public int getSy_id() {
		return sy_id;
	}

	public void setSy_id(int sy_id) {
		this.sy_id = sy_id;
	}

	public String getSy_name() {
		return sy_name;
	}

	public void setSy_name(String sy_name) {
		this.sy_name = sy_name;
	}

	public String getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getTt_grade() {
		return tt_grade;
	}

	public void setTt_grade(String tt_grade) {
		this.tt_grade = tt_grade;
	}

	public String getPracticescheduling() {
		return Practicescheduling;
	}

	public void setPracticescheduling(String practicescheduling) {
		Practicescheduling = practicescheduling;
	}

	public int getTt_state() {
		return tt_state;
	}

	public void setTt_state(int tt_state) {
		this.tt_state = tt_state;
	}

}
