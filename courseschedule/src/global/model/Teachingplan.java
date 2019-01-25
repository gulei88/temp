package global.model;

import java.util.List;

/**
 * 教学计划类
 * 
 * @author zzt
 * 
 */
public class Teachingplan {

	private String tp_id;
	private String tp_name;
	private String m_id;
	private String tp_mark;

	public Teachingplan(String tp_id, String tp_name, String m_id,
			String tp_mark) {
		super();
		this.tp_id = tp_id;
		this.tp_name = tp_name;
		this.m_id = m_id;
		this.tp_mark = tp_mark;
	}

	public Teachingplan() {
		// TODO Auto-generated constructor stub
	}

	public String getTp_id() {
		return tp_id;
	}

	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}

	public String getTp_name() {
		return tp_name;
	}

	public void setTp_name(String tp_name) {
		this.tp_name = tp_name;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getTp_mark() {
		return tp_mark;
	}

	public void setTp_mark(String tp_mark) {
		this.tp_mark = tp_mark;
	}

	@Override
	public String toString() {
		return tp_name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.tp_name.equals(((Teachingplan) obj).tp_name);
	}
}
