package czy.model;

import global.model.View_curriculum;

import java.util.ArrayList;
import java.util.Vector;

/**
 * ���������
 * 
 * @author Administrator
 * 
 */
public class Coordinate {
	// ������
	private int row;
	// ������
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
	 * ������ĸ�ַ������ʾ���ڿ���ת��Ϊ���ֺ������ַ�����ʾ���ڿ���
	 * 
	 * @param inweeks
	 *            ����ĸ�ַ������ʾ���ڿ���
	 * @return ���ֺ������ַ�����ʾ���ڿ���
	 */
	private static String transweeks(String inweeks) {
		String weeksString = "";
		if (inweeks.equals("abcdefghijklmnop"))
			return "ȫ��";
		if (inweeks.equals("abcdefgh"))
			return "ǰ����";
		if (inweeks.equals("ijklmnop"))
			return "�����";
		if (inweeks.equals("acegikmo"))
			return "����";
		if (inweeks.equals("bdfhjlnp"))
			return "˫��";
		char[] weeks = inweeks.toCharArray();
		weeksString += (weeks[0] - 96) + "";
		for (int i = 1; i < weeks.length; i++)
			weeksString += "," + (weeks[i] - 96);
		weeksString += "��";
		return weeksString;
	}
	/**
	 * ���ݿγ̱���Ϣ�б��Coordinate���͵����鼰���ݵ�������ֵ
	 * @param aList���γ̱���Ϣ�б�
	 * @param coordinates:��������
	 * @param v�������ʾ���ݵ�����
	 */
	public static void fillCoordinateArray(ArrayList<View_curriculum> aList,
			Coordinate[] coordinates, Vector[] v) {
		//�����γ̱���Ϣ�б�
		for (int i = 0; i < aList.size(); i++) {
			//��ÿνڸ�����row��ֵ
			int row = aList.get(i).getLessons();
			//������ڸ�����column��ֵ
			int column = aList.get(i).getWeek();
			//�����������б�ʶ�������
			coordinates[i] = new Coordinate(row - 1, column);
			//�����ַ���������ʾ�γ̵����ơ��ڿν�ʦ���ڿΰ༶���ڿ����������һ�ʵ���Ҽ�ʵ�������Ϣ
			String information = aList.get(i).getCou_name() + ","
					+ aList.get(i).getT_name() + "\n"
					+ aList.get(i).getCla_name() + ","
					+ transweeks(aList.get(i).getInweeks()) + ","
					+ aList.get(i).getCr_name();
			if (!aList.get(i).getSl_name().equals(""))
				information += "," + aList.get(i).getSl_name();
			//���������Ӧλ�ý�information������ֵ����
			v[row - 1].setElementAt(information, column);
		}
	}
}
