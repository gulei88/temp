package global.model;

/**
 * 教室类型类
 * 
 * @author zzt
 * 
 */
public class Classroomtype {
	private int ct_id;
	private String ct_name;

	public Classroomtype(int ct_id, String ct_name) {
		super();
		this.ct_id = ct_id;
		this.ct_name = ct_name;
	}

	public int getCt_id() {
		return ct_id;
	}

	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}

	public String getCt_name() {
		return ct_name;
	}

	@Override
	public String toString() {
		return ct_name;
	}

	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}
}
