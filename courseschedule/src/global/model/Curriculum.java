package global.model;

/**
 * ¿Î³Ì±í
 * 
 * @author ggc
 * 
 */

public class Curriculum {
	private int cc_id;
	private int tt_id;
	private int lessons;
	private int week;
	private String Inweeks;
	private int cr_id;
	private String sl_name;

	public Curriculum(int cc_id, int tt_id, int lessons, int week,
			String inweeks, int cr_id, String sl_name) {
		super();
		this.cc_id = cc_id;
		this.tt_id = tt_id;
		this.lessons = lessons;
		this.week = week;
		Inweeks = inweeks;
		this.cr_id = cr_id;
		this.sl_name = sl_name;
	}

	public Curriculum(int tt_id, int lessons, int week, String inweeks,
			int cr_id, String sl_name) {
		super();
		this.tt_id = tt_id;
		this.lessons = lessons;
		this.week = week;
		Inweeks = inweeks;
		this.cr_id = cr_id;
		this.sl_name = sl_name;
	}

	public int getCc_id() {
		return cc_id;
	}

	public int getTt_id() {
		return tt_id;
	}

	public int getLessons() {
		return lessons;
	}

	public int getWeek() {
		return week;
	}

	public String getInweeks() {
		return Inweeks;
	}

	public int getCr_id() {
		return cr_id;
	}

	public void setCc_id(int cc_id) {
		this.cc_id = cc_id;
	}

	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}

	public void setLessons(int lessons) {
		this.lessons = lessons;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public void setInweeks(String inweeks) {
		Inweeks = inweeks;
	}

	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}

	public String getSl_name() {
		return sl_name;
	}

	public void setSl_name(String sl_name) {
		this.sl_name = sl_name;
	}

}
