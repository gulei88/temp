package global.model;

import zzt.view.teachingplangrade;

public class Teachingplangrade {
	/**
	 * 教学计划与专业、年级对应类
	 * 
	 * @author czy
	 */
	private String tp_id;
	private String m_id;
	private String tg_grade;

	public Teachingplangrade(String tp_id, String m_id, String tg_grade) {
		super();
		this.tp_id = tp_id;
		this.m_id = m_id;
		this.tg_grade = tg_grade;
	}

	public String getTp_id() {
		return tp_id;
	}

	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getTg_grade() {
		return tg_grade;
	}

	public void setTg_grade(String tg_grade) {
		this.tg_grade = tg_grade;
	}
	
	public String toString() {
		return tg_grade;
	}
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.tg_grade.equals(((Teachingplangrade) obj).tg_grade);
	}
}
