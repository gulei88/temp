package czy.model;

import global.model.View_curriculum;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 表格坐标类
 * 
 * @author Administrator
 * 
 */
public class Coordinate {
	// 行坐标
	private int row;
	// 列坐标
	private int column;

	public Coordinate(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * 将由字母字符串便表示的授课周转换为汉字和数字字符串表示的授课周
	 * 
	 * @param inweeks
	 *            ：字母字符串便表示的授课周
	 * @return 汉字和数字字符串表示的授课周
	 */
	private static String transweeks(String inweeks) {
		String weeksString = "";
		if (inweeks.equals("abcdefghijklmnop"))
			return "全部";
		if (inweeks.equals("abcdefgh"))
			return "前八周";
		if (inweeks.equals("ijklmnop"))
			return "后八周";
		if (inweeks.equals("acegikmo"))
			return "单周";
		if (inweeks.equals("bdfhjlnp"))
			return "双周";
		char[] weeks = inweeks.toCharArray();
		weeksString += (weeks[0] - 96) + "";
		for (int i = 1; i < weeks.length; i++)
			weeksString += "," + (weeks[i] - 96);
		weeksString += "周";
		return weeksString;
	}
	/**
	 * 根据课程表信息列表给Coordinate类型的数组及传递的向量赋值
	 * @param aList：课程表信息列表
	 * @param coordinates:坐标数组
	 * @param v：表格显示内容的向量
	 */
	public static void fillCoordinateArray(ArrayList<View_curriculum> aList,
			Coordinate[] coordinates, Vector[] v) {
		//遍历课程表信息列表
		for (int i = 0; i < aList.size(); i++) {
			//获得课节给变量row赋值
			int row = aList.get(i).getLessons();
			//获得星期给变量column赋值
			int column = aList.get(i).getWeek();
			//在坐标数组中标识出这个点
			coordinates[i] = new Coordinate(row - 1, column);
			//生成字符串用于显示课程的名称、授课教师、授课班级、授课周数、教室或实验室及实验分室信息
			String information = aList.get(i).getCou_name() + ","
					+ aList.get(i).getT_name() + "\n"
					+ aList.get(i).getCla_name() + ","
					+ transweeks(aList.get(i).getInweeks()) + ","
					+ aList.get(i).getCr_name();
			if (!aList.get(i).getSl_name().equals(""))
				information += "," + aList.get(i).getSl_name();
			//向量数组对应位置将information变量的值填入
			v[row - 1].setElementAt(information, column);
		}
	}
}
