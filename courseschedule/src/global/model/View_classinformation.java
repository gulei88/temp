package global.model;

/**
 * ∞‡º∂ ”Õº¿‡
 * 
 * @author ggc
 * 
 */
public class View_classinformation {
	private String d_id;
	private String d_name;
	private String o_id;
	private String o_name;
	private String m_id;
	private String m_name;
	private String cla_id;
	private String cla_name;
	private String cla_grade;
	private int l_id;
	private String l_name;
	private int cla_number;

	public View_classinformation(String d_id, String d_name, String o_id,
			String o_name, String m_id, String m_name, String cla_id,
			String cla_name, String cla_grade, int l_id, String l_name,
			int cla_number) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.o_id = o_id;
		this.o_name = o_name;
		this.m_id = m_id;
		this.m_name = m_name;
		this.cla_id = cla_id;
		this.cla_name = cla_name;
		this.cla_grade = cla_grade;
		this.l_id = l_id;
		this.l_name = l_name;
		this.cla_number = cla_number;
	}

	public String getD_id() {
		return d_id;
	}

	public String getD_name() {
		return d_name;
	}

	public String getO_id() {
		return o_id;
	}

	public String getO_name() {
		return o_name;
	}

	public String getM_id() {
		return m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public String getCla_id() {
		return cla_id;
	}

	public String getCla_name() {
		return cla_name;
	}

	public String getCla_grade() {
		return cla_grade;
	}

	public int getL_id() {
		return l_id;
	}

	public String getL_name() {
		return l_name;
	}

	public int getCla_number() {
		return cla_number;
	}

	public void setD_id(String d_id) {
		this.d_id = d_id;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public void setCla_id(String cla_id) {
		this.cla_id = cla_id;
	}

	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}

	public void setCla_grade(String cla_grade) {
		this.cla_grade = cla_grade;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public void setCla_number(int cla_number) {
		this.cla_number = cla_number;
	}
}
