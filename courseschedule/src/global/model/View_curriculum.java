package global.model;

/**
 * 课程表视图类
 * 
 * @author ggc
 * 
 */
public class View_curriculum {
	private int cc_id;
	private int tt_id;
	private String cou_id;
	private String cou_name;
	private String cla_name;
	private String cla_number;
	private int lessons;
	private int week;
	private String Inweeks;
	private String sl_name;
	private int classhour;
	private String t_id;
	private String t_name;
	private String t_tel;
	private int sy_id;
	private String sy_name;
	private int b_id;
	private String b_name;
	private String b_address;
	private int cr_id;
	private String cr_name;
	private int ct_id;

	public View_curriculum(int cc_id, int tt_id, String cou_id,
			String cou_name, String cla_name, String cla_number, int lessons,
			int week, String inweeks, int classhour, String t_id,
			String t_name, String t_tel, int sy_id, String sy_name, int b_id,
			String b_name, String b_address, int cr_id, String cr_name,
			int ct_id, String sl_name) {
		super();
		this.cc_id = cc_id;
		this.tt_id = tt_id;
		this.cou_id = cou_id;
		this.cou_name = cou_name;
		this.cla_name = cla_name;
		this.cla_number = cla_number;
		this.lessons = lessons;
		this.week = week;
		this.Inweeks = inweeks;
		this.classhour = classhour;
		this.t_id = t_id;
		this.t_name = t_name;
		this.t_tel = t_tel;
		this.sy_id = sy_id;
		this.sy_name = sy_name;
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_address = b_address;
		this.cr_id = cr_id;
		this.cr_name = cr_name;
		this.ct_id = ct_id;
		this.sl_name = sl_name;
	}

	public int getCc_id() {
		return cc_id;
	}

	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
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

	public String getCou_name() {
		return cou_name;
	}

	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
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

	public int getLessons() {
		return lessons;
	}

	public void setLessons(int lessons) {
		this.lessons = lessons;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getInweeks() {
		return Inweeks;
	}

	public void setInweeks(String inweeks) {
		Inweeks = inweeks;
	}

	public int getClasshour() {
		return classhour;
	}

	public void setClasshour(int classhour) {
		this.classhour = classhour;
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

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_address() {
		return b_address;
	}

	public void setB_address(String b_address) {
		this.b_address = b_address;
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

	public String getSl_name() {
		return sl_name;
	}

	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}

}
