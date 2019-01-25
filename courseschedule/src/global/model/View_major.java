package global.model;

/**
 * 专业视图类
 * 
 * @author zzt
 * 
 */
public class View_major {
	private String d_id;
	private String d_name;
	private String o_id;
	private String o_name;
	private String m_id;
	private String m_name;

	public View_major(String d_id, String d_name, String o_id, String o_name,
			String m_id, String m_name) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.o_id = o_id;
		this.o_name = o_name;
		this.m_id = m_id;
		this.m_name = m_name;
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

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getO_name() {
		return o_name;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
}
