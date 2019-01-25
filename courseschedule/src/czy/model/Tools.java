package czy.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * �Ա�ʵ��С������
 * 
 * @author czy
 * 
 */
public class Tools {
	/**
	 * ��ѧ�ں��꼶ת��Ϊ����ѧ��
	 * 
	 * @param semester
	 *            ���ַ�������ѧ��
	 * @param grade
	 *            ���ַ��������꼶
	 * @return��ת���������ѧ��
	 */
	public static int Semesterconversion(String semester, String grade) {
		// ȡ���ַ�����ѧ���������
		String year = semester.substring(0, 4);
		// ȡ���ַ�����ѧ�ڵġ����������ѧ��
		String name = semester.substring(4, 5);
		// ��ȡ����ѧ�����ת��Ϊ����
		int end = Integer.parseInt(year.substring(2));
		// ���꼶ת��Ϊ����
		int start = Integer.parseInt(grade);
		// ��ǰ��ݼ�ȥ�꼶�����2��������ѧ��
		int s = (end - start) * 2;
		// ����Ǵ���ѧ�ڣ�������ѧ�ڼ�1
		if (name.equals("��"))
			s += 1;
		// ��������ѧ��
		return s;
	}

	/**
	 * �������ӻع�����
	 * 
	 * @param con
	 *            �����ݿ������
	 * @param msg
	 *            :������ʾ��Ϣ
	 * @throws SQLException
	 */
	public static void connectionroolback(Connection con, String msg)
			throws SQLException {
		// ��ʾ��ʾ��Ϣ
		JOptionPane.showMessageDialog(null, msg, "��ʾ��Ϣ",
				JOptionPane.INFORMATION_MESSAGE);
		// ������ݿ�����δ�رգ����Ȼع��ٹر�
		if (!con.isClosed()) {
			con.rollback();
			con.close();
		}
	}

	/**
	 * ����δѡ�е��б��
	 * 
	 * @param selectedrows
	 *            ��ѡ�е�����������
	 * @param rowcount
	 *            ��������
	 * @return û��ѡ�е�����������
	 */
	public static int[] getunselectedrows(int selectedrows[], int rowcount) {
		// ����δѡ�е��е�����
		int r = rowcount - selectedrows.length;
		int c = 0;
		// ����δѡ�е��е�����
		int[] rows = new int[r];
		// �������е���
		for (int i = 0; i < rowcount; i++) {
			int j;
			// ������ѡ�е�������
			for (j = 0; j < selectedrows.length; j++)
				// �����ǰ������ѡ�е��������У����˳���ǰѭ��
				if (i == selectedrows[j])
					break;
			// �����ѡ�е�����δ���ֵ�ǰ�У��򽫵�ǰ����ӵ�δѡ����������
			if (j >= selectedrows.length)
				rows[c++] = i;
		}
		// ����δѡ����
		return rows;
	}

	// public static String WeektoString(JCheckBox[] jcb) {
	// String weeks = "";
	// for (int i = 0; i < jcb.length; i++) {
	// if (jcb[i].isSelected()) {
	// char c = (char) (i + 97);
	// weeks += c;
	// }
	// }
	// if (weeks.equals("abcdefghijklmnop")) {
	// weeks = "ȫ��";
	// }
	// if (weeks.equals("abcdefgh")) {
	// weeks = "ǰ����";
	// }
	// if (weeks.equals("ijklmnop")) {
	// weeks = "�����";
	// }
	// if (weeks.equals("acegikmo")) {
	// weeks = "����";
	// }
	// if (weeks.equals("bdfhjlnp")) {
	// weeks = "˫��";
	// }
	// return weeks;
	// }

	/**
	 * ���ַ�������ת��Ϊ��������
	 * 
	 * @param inweeks
	 *            ���ַ�������
	 * @return ��������
	 */
	public static int numberofweek(String inweeks) {
		int number = -1;
		if (inweeks.equals("ȫ��")) {
			number = 16;
		} else if (inweeks.equals("ǰ����") || inweeks.equals("�����")
				|| inweeks.equals("����") || inweeks.equals("˫��")) {
			number = 8;
		} else {
			number = inweeks.length();
		}
		return number;
	}

	/**
	 * ���ر���е�ĳһ��
	 * 
	 * @param table
	 *            ���
	 * @param index
	 *            Ҫ���ص��� ������
	 */
	public static void hideColumn(JTable table, int index) {
		TableColumn tc = table.getColumnModel().getColumn(index);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setMinWidth(0);
		tc.setWidth(0);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0);
	}
}
