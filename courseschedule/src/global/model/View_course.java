package global.model;

/**
 * øŒ≥Ã ”Õº¿‡
 * 
 * @author ggc
 * 
 */
public class View_course {
	private String tp_id;
	private String tp_name;
	private String tp_mark;
	private String m_id;
	private String cou_id;
	private String cou_category;
	private String cou_name;
	private float cou_credit;
	private int cou_theoryhour;
	private int cou_experimentalhours;
	private int cou_practicehour;
	private int cou_semester;
	private int cou_type;
	private String d_id;
	private String d_name;

	public View_course(String tp_id, String tp_name, String tp_mark,
		String m_id,String cou_id, String cou_category, String cou_name,
			float cou_credit, int cou_theoryhour, int cou_experimentalhours,
			int cou_practicehour, int cou_semester, int cou_type, String d_id,
			String d_name) {
		super();
		this.tp_id = tp_id;
		this.tp_name = tp_name;
		this.tp_mark = tp_mark;
		this.cou_id = cou_id;
		this.m_id = m_id;
		this.cou_category = cou_category;
		this.cou_name = cou_name;
		this.cou_credit = cou_credit;
		this.cou_theoryhour = cou_theoryhour;
		this.cou_experimentalhours = cou_experimentalhours;
		this.cou_practicehour = cou_practicehour;
		this.cou_semester = cou_semester;
		this.cou_type = cou_type;
		this.d_id = d_id;
		this.d_name = d_name;
	}

	public String getTp_id() {
		return tp_id;
	}

	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}

	public  void setM_id(String m_id){
		this.m_id = m_id;
	}
	public String getTp_name() {
		return tp_name;
	}

	public void setTp_name(String tp_name) {
		this.tp_name = tp_name;
	}

	public String getTp_mark() {
		return tp_mark;
	}

	public String getM_id() {
		return m_id;
	}
	public void setTp_mark(String tp_mark) {
		this.tp_mark = tp_mark;
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
}
