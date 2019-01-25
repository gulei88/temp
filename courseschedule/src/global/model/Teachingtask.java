package global.model;

/**
 * 教学任务类
 * 
 * @author zzt
 * 
 */
public class Teachingtask {
	private int tt_id;
	private String cou_id;
	private int cou_theoryhour;
	private int cou_experimentalhours;
	private int cou_practicehour;
	private String t_id;
	private int sy_id;
	private String multimedia;
	private String m_id;
	private String tt_grade;
	private String Practicescheduling;
	private int tt_state;

	public Teachingtask(int tt_id, String cou_id, int cou_theoryhour,
			int cou_experimentalhours, int cou_practicehour, String t_id,
			int sy_id, String multimedia, String m_id, String tt_grade,
			String practicescheduling, int tt_state) {
		super();
		this.tt_id = tt_id;
		this.cou_id = cou_id;
		this.cou_theoryhour = cou_theoryhour;
		this.cou_experimentalhours = cou_experimentalhours;
		this.cou_practicehour = cou_practicehour;
		this.t_id = t_id;
		this.sy_id = sy_id;
		this.multimedia = multimedia;
		this.m_id = m_id;
		this.tt_grade = tt_grade;
		Practicescheduling = practicescheduling;
		this.tt_state = tt_state;
	}

	public Teachingtask(String cou_id, int cou_theoryhour,
			int cou_experimentalhours, int cou_practicehour, String t_id,
			int sy_id, String multimedia, String m_id, String tt_grade,
			String experimentalenvironment, String practicescheduling,
			int tt_state) {
		super();
		this.cou_id = cou_id;
		this.cou_theoryhour = cou_theoryhour;
		this.cou_experimentalhours = cou_experimentalhours;
		this.cou_practicehour = cou_practicehour;
		this.t_id = t_id;
		this.sy_id = sy_id;
		this.multimedia = multimedia;
		this.m_id = m_id;
		this.tt_grade = tt_grade;
		this.Practicescheduling = practicescheduling;
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

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public int getSy_id() {
		return sy_id;
	}

	public void setSy_id(int sy_id) {
		this.sy_id = sy_id;
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
