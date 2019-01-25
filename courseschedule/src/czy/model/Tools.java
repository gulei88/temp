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
 * 自编实用小工具类
 * 
 * @author czy
 * 
 */
public class Tools {
	/**
	 * 将学期和年级转化为数字学期
	 * 
	 * @param semester
	 *            ：字符串类型学期
	 * @param grade
	 *            ：字符串类型年级
	 * @return：转换后的整形学期
	 */
	public static int Semesterconversion(String semester, String grade) {
		// 取出字符串型学期所在年份
		String year = semester.substring(0, 4);
		// 取出字符串型学期的“春”或“秋”季学期
		String name = semester.substring(4, 5);
		// 将取出的学期年份转换为整形
		int end = Integer.parseInt(year.substring(2));
		// 将年级转换为整形
		int start = Integer.parseInt(grade);
		// 当前年份减去年级后乘以2后获得整形学期
		int s = (end - start) * 2;
		// 如果是春季学期，则整形学期加1
		if (name.equals("秋"))
			s += 1;
		// 返回整形学期
		return s;
	}

	/**
	 * 数据连接回滚方法
	 * 
	 * @param con
	 *            ：数据库的连接
	 * @param msg
	 *            :错误提示信息
	 * @throws SQLException
	 */
	public static void connectionroolback(Connection con, String msg)
			throws SQLException {
		// 显示提示信息
		JOptionPane.showMessageDialog(null, msg, "提示信息",
				JOptionPane.INFORMATION_MESSAGE);
		// 如果数据库连接未关闭，则先回滚再关闭
		if (!con.isClosed()) {
			con.rollback();
			con.close();
		}
	}

	/**
	 * 计算未选中的行编号
	 * 
	 * @param selectedrows
	 *            ：选中的行所在数组
	 * @param rowcount
	 *            ：总行数
	 * @return 没有选中的行所在数组
	 */
	public static int[] getunselectedrows(int selectedrows[], int rowcount) {
		// 计算未选中的行的数量
		int r = rowcount - selectedrows.length;
		int c = 0;
		// 生成未选中的行的数组
		int[] rows = new int[r];
		// 遍历所有的行
		for (int i = 0; i < rowcount; i++) {
			int j;
			// 遍历已选中的行数组
			for (j = 0; j < selectedrows.length; j++)
				// 如果当前行在已选中的行数组中，则退出当前循环
				if (i == selectedrows[j])
					break;
			// 如果在选中的行中未发现当前行，则将当前行添加到未选中行数组中
			if (j >= selectedrows.length)
				rows[c++] = i;
		}
		// 返回未选中行
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
	// weeks = "全部";
	// }
	// if (weeks.equals("abcdefgh")) {
	// weeks = "前八周";
	// }
	// if (weeks.equals("ijklmnop")) {
	// weeks = "后八周";
	// }
	// if (weeks.equals("acegikmo")) {
	// weeks = "单周";
	// }
	// if (weeks.equals("bdfhjlnp")) {
	// weeks = "双周";
	// }
	// return weeks;
	// }

	/**
	 * 将字符串周数转换为整数周数
	 * 
	 * @param inweeks
	 *            ：字符串周数
	 * @return 整数周数
	 */
	public static int numberofweek(String inweeks) {
		int number = -1;
		if (inweeks.equals("全部")) {
			number = 16;
		} else if (inweeks.equals("前八周") || inweeks.equals("后八周")
				|| inweeks.equals("单周") || inweeks.equals("双周")) {
			number = 8;
		} else {
			number = inweeks.length();
		}
		return number;
	}

	/**
	 * 隐藏表格中的某一列
	 * 
	 * @param table
	 *            表格
	 * @param index
	 *            要隐藏的列 的索引
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
