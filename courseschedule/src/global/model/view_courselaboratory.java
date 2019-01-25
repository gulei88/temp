package global.model;

public class view_courselaboratory {
	private int ul_id;
	private String grade;
	private String Experimentalenvironment;
	private String cou_id;
	private String tp_id;
	private String cou_category;
	private String cou_name;
	private float cou_credit;
	private int cou_theoryhour;
	private int cou_experimentalhours;
	private int cou_practicehour;
	private int cou_semester;
	private int cou_type;
	private String d_id;
	private int cr_id;
	private String cr_name;
	private int ct_id;
	private int cr_seating;
	private int b_id;
	private int sl_id;
	private String sl_name;
	private int sl_seating;

	public view_courselaboratory(int ul_id, String grade,
			String experimentalenvironment, String cou_id, String tp_id,
			String cou_category, String cou_name, float cou_credit,
			int cou_theoryhour, int cou_experimentalhours,
			int cou_practicehour, int cou_semester, int cou_type, String d_id,
			int cr_id, String cr_name, int ct_id, int cr_seating, int b_id,
			int sl_id, String sl_name, int sl_seating) {
		super();
		this.ul_id = ul_id;
		this.grade = grade;
		Experimentalenvironment = experimentalenvironment;
		this.cou_id = cou_id;
		this.tp_id = tp_id;
		this.cou_category = cou_category;
		this.cou_name = cou_name;
		this.cou_credit = cou_credit;
		this.cou_theoryhour = cou_theoryhour;
		this.cou_experimentalhours = cou_experimentalhours;
		this.cou_practicehour = cou_practicehour;
		this.cou_semester = cou_semester;
		this.cou_type = cou_type;
		this.d_id = d_id;
		this.cr_id = cr_id;
		this.cr_name = cr_name;
		this.ct_id = ct_id;
		this.cr_seating = cr_seating;
		this.b_id = b_id;
		this.sl_id = sl_id;
		this.sl_name = sl_name;
		this.sl_seating = sl_seating;
	}

	public int getUl_id() {
		return ul_id;
	}

	public void setUl_id(int ul_id) {
		this.ul_id = ul_id;
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

	public String getCou_id() {
		return cou_id;
	}

	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}

	public String getTp_id() {
		return tp_id;
	}

	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
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

	public int getCr_id() {
		return cr_id;
	}

	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}

	public String getCr_name() {
		return cr_name;
	}

	public void setCr_name(String cr_name) {
		this.cr_name = cr_name;
	}

	public int getCt_id() {
		return ct_id;
	}

	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}

	public int getCr_seating() {
		return cr_seating;
	}

	public void setCr_seating(int cr_seating) {
		this.cr_seating = cr_seating;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public int getSl_id() {
		return sl_id;
	}

	public void setSl_id(int sl_id) {
		this.sl_id = sl_id;
	}

	public String getSl_name() {
		return sl_name;
	}

	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}

	public int getSl_seating() {
		return sl_seating;
	}

	public void setSl_seating(int sl_seating) {
		this.sl_seating = sl_seating;
	}
}
