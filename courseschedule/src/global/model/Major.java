package global.model;

/**
 * ×¨ÒµÀà
 * 
 * @author czy
 * 
 */
public class Major {
	private String m_id;
	private String o_id;
	private String m_name;

	public Major(String m_id,String o_id,String m_name) {
		super();
		this.m_id = m_id;
		this.o_id = o_id;
		this.m_name = m_name;
	}
	public Major() {
	}
	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getO_id() {
		return o_id;
	}

	public void setO_id(String o_id) {
		this.o_id = o_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	@Override
	public String toString() {
		return m_name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.m_name.equals(((Major) obj).m_name);
	}

}
