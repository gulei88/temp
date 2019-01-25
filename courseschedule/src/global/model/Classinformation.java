package global.model;

/**
 * 班级信息类
 * 
 * @author zzt
 * 
 */
public class Classinformation {
	private String cla_id;
	private String m_id;
	private String cla_name;
	private String cla_grade;
	private int l_id;
	private int cla_number;

	public String getCla_id() {
		return cla_id;
	}

	public Classinformation(String cla_id, String m_id, String cla_name,
			String cla_grade, int l_id, int cla_number) {
		super();
		this.cla_id = cla_id;
		this.m_id = m_id;
		this.cla_name = cla_name;
		this.cla_grade = cla_grade;
		this.l_id = l_id;
		this.cla_number = cla_number;
	}

	public Classinformation() {
		// TODO Auto-generated constructor stub
	}

	public void setCla_id(String cla_id) {
		this.cla_id = cla_id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getCla_name() {
		return cla_name;
	}

	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}

	public String getCla_grade() {
		return cla_grade;
	}

	public void setCla_grade(String cla_grade) {
		this.cla_grade = cla_grade;
	}

	public int getL_id() {
		return l_id;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public int getCla_number() {
		return cla_number;
	}

	public void setCla_number(int cla_number) {
		this.cla_number = cla_number;
	}

}
