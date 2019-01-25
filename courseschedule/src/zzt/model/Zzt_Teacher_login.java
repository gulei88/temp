package zzt.model;
/**
 * 
 * @author zzt
 *
 */
public class Zzt_Teacher_login {

		private String t_id;
		private String t_password;
		public Zzt_Teacher_login(String t_id, String t_password) {
			super();
			this.t_id = t_id;
			this.t_password = t_password;
		}
		public String gett_id() {
			return t_id;
		}
		public void sett_id(String t_id) {
			this.t_id = t_id;
		}
		public String gett_password() {
			return t_password;
		}
		public void setT_password(String t_password) {
			this.t_password = t_password;
		}
}
