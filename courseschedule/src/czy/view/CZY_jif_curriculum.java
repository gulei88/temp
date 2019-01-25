/*
 * CZY_jif_curriculum.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import czy.dao.Curriculumaccess;
import czy.model.Coordinate;
import czy.model.Tools;
import czy.model.TableCellTextAreaRenderer;
import czy.model.Rowcolor;

import global.dao.Classroomaccess;
import czy.dao.Courselaboratoryaccess;
import global.dao.Databaseconnection;
import global.dao.Majoraccess;
import global.dao.Teachingtaskaccess;
import global.model.Classroom;
import global.model.Curriculum;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Schoolyear;
import global.model.Sublaboratory;
import global.model.View_classroom;
import global.model.View_curriculum;
import global.model.View_teacher;
import global.model.View_teachingtask;
import czy.model.View_courselaboratory;

/**
 * 
 * @author __USER__
 */
public class CZY_jif_curriculum extends javax.swing.JInternalFrame {
	private View_teacher vt;
	private JCheckBox jcb[] = new JCheckBox[16];
	private ArrayList<Integer> rowlist = new ArrayList<Integer>();
	private String inweeks = "";
	ActionTry select = new ActionTry();

	/** Creates new form CZY_jif_curriculum */
	public CZY_jif_curriculum() {
		initComponents();
	}

	/**
	 * �������
	 * 
	 * @author czy
	 * @param vt
	 *            ����¼�û�����ͼ��Ϣ
	 */
	public CZY_jif_curriculum(View_teacher vt) {
		// ��ʼ������
		initComponents();
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.vt = vt;
		// ���ú�����ʼ��ѧԺ�����б�
		fillcomboboxdepartment();
		// ������ľ�̬������ʼ��ѧ�������б�
		Fillcombobox.fillschoolyear(jComboBox4);
		// �����ڿ�������и��п��
		setColumnWidth();
		// ���ڿ�������ѡ��ť��ı�����ת��Ϊ��ѡ��ť���飬���ں�������
		initCheckBoxArray();
		ActionTry select = new ActionTry();
		for (int i = 0; i < jcb.length; i++) {
			jcb[i].addActionListener(select);
			jcb[i].setSelected(true);
		}
		inweeks = "abcdefghijklmnop";
		// ����jtable����е�ѡ
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * �ڲ���,ʵ����ActionListener�ӿ�
	 * 
	 * @author czy
	 * 
	 */
	class ActionTry implements ActionListener {
		/**
		 * ���������������Ӧ�ؼ۴������¼��󣬽���ѡ��ť�����ѡ��״̬ת��Ϊ�ַ�������ֵ��CZY_jif_curriculum��������ֶΡ�
		 * 
		 * @author czy
		 */
		public void actionPerformed(ActionEvent e) {
			// ��ʼ���ַ���
			inweeks = "";
			// ������ѡ��ť����
			for (int i = 0; i < jcb.length; i++) {
				// �������Ԫ�ر�ѡ�У��򽫴���ø�ѡ��ť��Сд��ĸ��ӵ��ַ�����β��
				if (jcb[i].isSelected()) {
					inweeks += (char) (i + 97);
				}
			}
			// ���γ̱���
			filltablecurriculum();
		}
	}

	/**
	 * ��ʼ��ѧԺ�����б�
	 * 
	 * @author czy
	 */
	private void fillcomboboxdepartment() {
		// �����¼��Ȩ��Ϊϵ���ſλ��Ź�����ѧԺ�����б������֮��ʼ��Ϊ��¼�û�����ѧԺ
		if (vt.getT_power().equals("ϵ���ſ�") || vt.getT_power().equals("���Ź���")) {
			Department dm = new Department(vt.getD_id(), vt.getD_name());
			jComboBox1.addItem(dm);
		}
		// ���Ȩ��ΪѧУ������ѧԺ�����б������ľ�̬������ʼ��Ϊȫ��ѧԺ
		else if (vt.getT_power().equals("ѧУ����")) {
			Fillcombobox.filldepartment(jComboBox1);
		}
	}

	/**
	 * ��ʼ����ѧ������
	 * 
	 * @author czy
	 */
	private void fillteachingtasktable() {
		// ȡ�ñ���model��Ϣ
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		// ��ձ������ʾ������
		dtm.setRowCount(0);
		// ȡ��ϵ�������б�ѡ������
		Office o = (Office) jComboBox2.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (o == null)
			return;
		// ȡ��ѧ�������б�ѡ������
		Schoolyear sy = (Schoolyear) jComboBox4.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (sy == null)
			return;

		List<Major> al = Majoraccess.getMajor(o.getO_id());
		if (al.size() == 0)
			return;
		// ���ɲ�ѯ�������ڲ�ѯָ��ѧ�ڶ�Ӧ�Ľ�ѧ����
		String condition = " sy_id=" + sy.getSy_id() + " and (m_id='"
				+ al.get(0).getM_id() + "'";
		for (int i = 1; i < al.size(); i++) {
			condition += " or m_id='" + al.get(i).getM_id() + "'";
		}
		condition += ") order by m_id,cou_id";
		// �������������Ľ�ѧ�ƻ���ͼ��Ϣ��ָ���Ŀγ���ͼ���͵������б���
		ArrayList<View_teachingtask> aList = Teachingtaskaccess
				.getView_teachingtask(condition);
		// �����γ���ͼ���͵������б�

		for (int i = 0; i < aList.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
			int tt_id = aList.get(i).getTt_id();
			String cou_id = aList.get(i).getCou_id();
			String cou_name = aList.get(i).getCou_name();
			float Cou_credit = aList.get(i).getCou_credit();
			int cou_theoryhour = aList.get(i).getCou_theoryhour();
			int cou_experimentalhours = aList.get(i).getCou_experimentalhours();
			int cou_practicehour = aList.get(i).getCou_practicehour();
			int cou_type = aList.get(i).getCou_type();
			String cla_name = aList.get(i).getCla_name();
			int cla_number = aList.get(i).getCla_number();
			String tt_grade = aList.get(i).getTt_grade();
			String t_id = aList.get(i).getT_id();
			String t_name = aList.get(i).getT_name();
			int sy_id = aList.get(i).getSy_id();
			String sy_name = aList.get(i).getSy_name();
			String multimedia = aList.get(i).getMultimedia();
			String Practicescheduling = aList.get(i).getPracticescheduling();
			int tt_state = aList.get(i).getTt_state();
			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
			v.add(tt_id);
			v.add(cou_id);
			v.add(cou_name);
			v.add(Cou_credit);
			v.add(cou_theoryhour + cou_experimentalhours + cou_practicehour);
			v.add(cou_theoryhour);
			v.add(cou_experimentalhours);
			v.add(cou_practicehour);
			if (cou_type == 1)
				v.add("����");
			else
				v.add("ѡ��");
			v.add(cla_name);
			v.add(cla_number);
			v.add(tt_grade);
			v.add(t_id);
			v.add(t_name);
			v.add(new Schoolyear(sy_id, sy_name));
			v.add(multimedia);
			v.add(Practicescheduling);
			if (tt_state == 1) {
				v.add("����ſ�");
				rowlist.add(i);
			} else
				v.add("δ����ſ�");
			// ��������ӵ������
			dtm.addRow(v);
			Rowcolor.setColor(jTable1, rowlist, Color.green);
		}

	}

	/**
	 * ����ʵ�������������ʵ���Ҳ�ѯ���������
	 * 
	 * @param sl_name
	 *            ��ʵ���������
	 * @return
	 */
	private String getconditionsublaboratory(String sl_name) {
		// ���������ַ�����������ʵ���ұ��Ϊ��ʼ������
		String condition = "(cr_id="
				+ ((Classroom) jComboBox5.getSelectedItem()).getCr_id() + ")";
		// ������ݵ�ʵ��������Ʋ�Ϊ����
		if (!sl_name.equals("")) {
			// ��ʵ���������Զ���Ϊ�ָ����������ʵ����ҷֽ⿪���浽�ַ�������name��
			String[] name = sl_name.split(",");
			// ���ݶ��ʵ����ң��ֱ����ɲ�ѯ����������ӵ������ַ�����
			condition = condition.substring(0, condition.length() - 1);
			condition += " and (sl_name like '%" + name[0] + "%' ";
			for (int i = 1; i < name.length; i++) {
				condition += " or sl_name like '%" + name[i] + "%' ";
			}
			condition += "))";
		}
		// ���������ַ���
		return condition;
	}

	/**
	 * ���ɽ�ѧ������ѯ�����ַ���
	 * 
	 * @author czy
	 * @return ��ѧ������ѯ�����ַ���
	 */
	private String getconditioninweeks() {
		// ��ʼ���ַ�������ֵΪ����
		String condition = "";
		// �����ѧ�����ַ���������ȫ��
		if (!inweeks.equals("abcdefghijklmnop")) {
			// ����ѧ�����ַ���ת��Ϊ�ַ�����
			char[] weeks = inweeks.toCharArray();
			// ������ѧ�����ַ����飬�������ݿ��ѯ����
			for (int i = 0; i < weeks.length; i++)
				condition += "or Inweeks like '%" + weeks[i] + "%' ";
			// ȥ����ѯ�ַ����ĵ�һ����or���ַ���
			condition = condition.replaceFirst("or", "");
			// ��ѯ�����ַ����������
			condition = "(" + condition + ")";
		}
		// ���������ַ���
		return condition;
	}

	/**
	 * ���γ̱���
	 * 
	 * @author czy
	 * 
	 */
	private void filltablecurriculum() {
		// ��տγ̱�
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		dtm.setRowCount(0);
		int row = jTable1.getSelectedRow();
		// ���δѡ���ѧ��������ʾ������Ϣ
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "��ѡ���ѧ����");
			return;
		}
		// ���δѡ���ڿ�����������ʾ������Ϣ
		if (inweeks.equals("")) {
			JOptionPane.showMessageDialog(this, "��ѡ���ѧ��");
			return;
		}
		int seat = 0;
		// ���ʵ������б���ڴ�������
		if (jList1.isEnabled()) {
			// �õ��б�ؼ���ѡ���ʵ�����
			Object[] sl = jList1.getSelectedValues();
			// ����ѡ���ʵ���������λ��
			for (int i = 0; i < sl.length; i++) {
				seat += ((Sublaboratory) sl[i]).getSl_seating();
			}
		} else {
			Classroom cr=(Classroom) jComboBox5.getSelectedItem();
			if(cr==null)
				return;
			seat = cr.getCr_seating();
		}
		// ��ý�ѧ���������
		int seating = (Integer) jTable1.getValueAt(row, 10);
		// ���ʵ����ҵ�����λ��С�ڽ�ѧ��������������˳����δ����ִ��
		if (seat <= seating) {
			return;
		}
		// ���û�н�����Ϣ�����˳����δ����ִ��
		if (jComboBox5.getItemCount() == 0)
			return;
		// �ж��ڿ�������ѡ��ť�飬������еĸ�ѡ��ť��û��ѡ�У����˳����δ����ִ��
		int i;
		for (i = 0; i < jcb.length; i++) {
			if (jcb[i].isSelected()) {
				break;
			}
		}
		if (i >= jcb.length)
			return;
		// �������γ̱�����������飬����ʼ���ν�
		Vector[] v = new Vector[10];
		for (i = 0; i < 10; i++) {
			String x = "��" + (i + 1) + "��";
			v[i] = new Vector();
			v[i].add(x);
			for (int j = 1; j < 8; j++) {
				v[i].add("");
			}
		}
		String weeks = getconditioninweeks();
		String sl_name = getconditionsublaboratory(getsublaboratory());
		// ���ѧ�ڱ��
		int sy_id = ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id();
		// ���û�����ѯ������ѧ��Ϊ���õ�ѧ��
		String base = "sy_id=" + sy_id;
		// ��ѯ����Ϊѧ��+���ҵĿγ���Ϣ
		String condition = base + " and " + sl_name;
		// ����ڿ��������趨�����ѯ����������ڿ���������
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// �����ݿ��в�ѯָ�������Ŀγ���Ϣ
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		// ���ݲ�ѯ����������ɽ��ҵı����������
		Coordinate[] classroom = new Coordinate[aList.size()];
		// ���ݿγ̱���Ϣ�б��Coordinate���͵����鼰���ݵ�������ֵ
		Coordinate.fillCoordinateArray(aList, classroom, v);
		// ���ҽ�ʦ�γ���Ϣ
		// ȡ�ý�ʦ���
		String t_id = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 12);
		// ��ѯ����Ϊѧ��+��ʦ�Ŀγ���Ϣ
		condition = base + " and t_id='" + t_id + "'";
		// ����ڿ��������趨�����ѯ����������ڿ���������
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// �����ݿ��в�ѯָ�������Ŀγ���Ϣ
		aList = Curriculumaccess.getCurriculums(condition);
		// ���ݲ�ѯ����������ɽ�ʦ�ı����������
		Coordinate[] teacher = new Coordinate[aList.size()];
		// ���ݿγ̱���Ϣ�б��Coordinate���͵����鼰���ݵ�������ֵ
		Coordinate.fillCoordinateArray(aList, teacher, v);
		// ȡ�ð༶������Ϣ
		String cla_name = jTextField1.getText();
		// ���༶�����ַ����Զ���Ϊ�ָ������зֽ⣨�ֽ����༶��
		String[] cla = cla_name.split(",");
		// ��ѯ����Ϊѧ��+�༶�Ŀγ���Ϣ
		condition = base + " and (cla_name like '%" + cla[0] + "%'";
		for (i = 1; i < cla.length; i++)
			condition += " or cla_name like '%" + cla[i] + "%'";
		condition += ")";
		// ����ڿ��������趨�����ѯ����������ڿ���������
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// �����ݿ��в�ѯָ�������Ŀγ���Ϣ
		aList = Curriculumaccess.getCurriculums(condition);
		// ���ݲ�ѯ�����������ѧ���ı����������
		Coordinate[] student = new Coordinate[aList.size()];
		// ���ݿγ̱���Ϣ�б��Coordinate���͵����鼰���ݵ�������ֵ
		Coordinate.fillCoordinateArray(aList, student, v);
		// ��ȡ��ǰ������
		int tt_id = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		// ��ѯ����Ϊѧ��+��ǰ����Ŀγ���Ϣ
		condition = base + " and tt_id=" + tt_id;
		// ����ڿ��������趨�����ѯ����������ڿ���������
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// �����ݿ��в�ѯָ�������Ŀγ���Ϣ
		aList = Curriculumaccess.getCurriculums(condition);
		// ���ݲ�ѯ����������ɵ�ǰ�γ̵ı����������
		Coordinate[] current = new Coordinate[aList.size()];
		// ���ݿγ̱���Ϣ�б��Coordinate���͵����鼰���ݵ�������ֵ
		Coordinate.fillCoordinateArray(aList, current, v);

		Color studentcolor = Color.GREEN;
		Color teachercolor = Color.YELLOW;
		Color classroomcolor = Color.CYAN;
		Color currentcolor = Color.RED;
		// ������������ӵ��γ̱���
		for (i = 0; i < v.length; i++) {
			dtm.addRow(v[i]);
			jTable2.setRowHeight(i, 40);
		}
		// ����jtable2��Ӧ��ı�����ɫ
		jTable2.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer(
				student, teacher, classroom, current, studentcolor,
				teachercolor, classroomcolor, currentcolor));
	}

	private ArrayList<Classroom> findclassroom() {
		int row = jTable1.getSelectedRow();
		int cla_number = (Integer) jTable1.getValueAt(row, 10);
		String multimedia = (String) jTable1.getValueAt(row, 15);
		String condition = " seating>=" + cla_number;
		if (multimedia.equals("��"))
			condition += " and ct_id=2";
		else
			condition += " and ct_id=1";
		ArrayList<View_classroom> aList = Classroomaccess
				.getView_classroom(condition);
		ArrayList<Classroom> crlist = new ArrayList<Classroom>();
		for (int i = 0; i < aList.size(); i++) {
			int cr_id = aList.get(i).getCr_id();
			String d_id = aList.get(i).getD_id();
			String cr_name = aList.get(i).getCr_name();
			int ct_id = aList.get(i).getCt_id();
			int cr_seating = aList.get(i).getSeating();
			int b_id = aList.get(i).getB_id();
			crlist.add(new Classroom(cr_id, d_id, cr_name, ct_id, cr_seating,
					b_id));
		}
		return crlist;
	}

	private ArrayList<Classroom> findlaboratory() {
		int row = jTable1.getSelectedRow();
		 int cla_number = (Integer) jTable1.getValueAt(row, 10);
		// int tt_id = (Integer) jTable1.getValueAt(row, 0);
		String cou_id = (String) jTable1.getValueAt(row, 1);
		String grade = (String) jTable1.getValueAt(row, 11);
		String condition = "((sumsl_seating is null and cr_seating>"+cla_number+")or sumsl_seating>"+cla_number+") and ce_id in (select ce_id from courseexperimentalenvironment where grade='"
				+ grade + "' and cou_id='" + cou_id + "')";
		ArrayList<View_courselaboratory> aList = Courselaboratoryaccess
				.getCourselaboratorybycondition(condition);
		ArrayList<Classroom> crlist = new ArrayList<Classroom>();
		for (int i = 0; i < aList.size(); i++) {
			int cr_id = aList.get(i).getCr_id();
			String d_id = aList.get(i).getD_id();
			String cr_name = aList.get(i).getCr_name();
			int ct_id = aList.get(i).getCt_id();
			int cr_seating = aList.get(i).getCr_seating();
			int b_id = aList.get(i).getB_id();
			crlist.add(new Classroom(cr_id, d_id, cr_name, ct_id, cr_seating,
					b_id));
		}
		return crlist;
	}

	private void fillcomboxclassroom() {
		// ����ڿν��������б�
		jComboBox5.removeAllItems();
		// ��ȡ��ѧ������ѡ�е���
		int row = jTable1.getSelectedRow();
		// ���û��ѡ�е��У����˳����δ���
		if (row < 0)
			return;
		// ��ȡ�༶����
		int cla_number = (Integer) jTable1.getValueAt(row, 10);
		// ��ȡʵ���ʱ
		int cou_experimentalhours = (Integer) jTable1.getValueAt(row, 6);
		// ��ȡʵ����ʱ
		int cou_practicehour = (Integer) jTable1.getValueAt(row, 7);
		// ���ʵ���ʵ����ʱ��Ϊ0����ʵ���Ҹ�ѡ��ťͣ��
		if (cou_experimentalhours == 0 && cou_practicehour == 0) {
			jCheckBox17.setSelected(false);
			jCheckBox17.setEnabled(false);
		} else {
			// ʵ���Ҹ�ѡ��ť����
			jCheckBox17.setEnabled(true);
		}
		ArrayList<Classroom> aList = null;

		if (jCheckBox17.isSelected()) {
			aList = findlaboratory();
		} else {
			aList = findclassroom();
		}
		if (aList == null)
			return;
		for (int i = 0; i < aList.size(); i++) {
			Classroom cr = aList.get(i);
			boolean r = false;
			for (int j = 0; j < jComboBox5.getItemCount(); j++) {
				if (jComboBox5.getItemAt(j).equals(cr)) {
					r = true;
					break;
				}
			}
			if (!r)
				jComboBox5.addItem(aList.get(i));
		}
		if (jComboBox5.getItemCount() == 0) {
			JOptionPane.showMessageDialog(this,
					"û�н��һ�ʵ�������ṩ���ÿγ̵��ڿΣ��볢�Խ��зְࡢ�ϰ࣬����������γ�ʱ�䣡");
			// ȡ�ñ���model��Ϣ
			DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
			// ��ձ������ʾ������
			dtm.setRowCount(0);
			jTable2.setEnabled(false);
		} else {
			jTable2.setEnabled(true);
			filltablecurriculum();
		}
	}

	private void initCheckBoxArray() {
		jcb[0] = jCheckBox1;
		jcb[1] = jCheckBox2;
		jcb[2] = jCheckBox3;
		jcb[3] = jCheckBox4;
		jcb[4] = jCheckBox5;
		jcb[5] = jCheckBox6;
		jcb[6] = jCheckBox7;
		jcb[7] = jCheckBox8;
		jcb[8] = jCheckBox9;
		jcb[9] = jCheckBox10;
		jcb[10] = jCheckBox11;
		jcb[11] = jCheckBox12;
		jcb[12] = jCheckBox13;
		jcb[13] = jCheckBox14;
		jcb[14] = jCheckBox15;
		jcb[15] = jCheckBox16;
	}

	private void setColumnWidth() {
		TableColumn Column;
		// ���ý�ѧ��������п��
		Column = jTable1.getColumnModel().getColumn(0);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(1);
		Column.setPreferredWidth(80);
		Column = jTable1.getColumnModel().getColumn(2);
		Column.setPreferredWidth(180);
		Column = jTable1.getColumnModel().getColumn(3);
		Column.setPreferredWidth(40);
		Column = jTable1.getColumnModel().getColumn(4);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(5);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(6);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(7);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(8);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(9);
		Column.setPreferredWidth(180);
		Column = jTable1.getColumnModel().getColumn(10);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(11);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(12);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(13);
		Column.setPreferredWidth(90);
		Column = jTable1.getColumnModel().getColumn(14);
		Column.setPreferredWidth(60);
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel6 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jCheckBox2 = new javax.swing.JCheckBox();
		jCheckBox3 = new javax.swing.JCheckBox();
		jCheckBox4 = new javax.swing.JCheckBox();
		jCheckBox5 = new javax.swing.JCheckBox();
		jCheckBox6 = new javax.swing.JCheckBox();
		jCheckBox7 = new javax.swing.JCheckBox();
		jCheckBox8 = new javax.swing.JCheckBox();
		jCheckBox9 = new javax.swing.JCheckBox();
		jCheckBox10 = new javax.swing.JCheckBox();
		jCheckBox11 = new javax.swing.JCheckBox();
		jCheckBox12 = new javax.swing.JCheckBox();
		jCheckBox13 = new javax.swing.JCheckBox();
		jCheckBox14 = new javax.swing.JCheckBox();
		jCheckBox15 = new javax.swing.JCheckBox();
		jCheckBox16 = new javax.swing.JCheckBox();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jComboBox5 = new javax.swing.JComboBox();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane3 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jCheckBox17 = new javax.swing.JCheckBox();
		jPanel7 = new javax.swing.JPanel();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();

		setClosable(true);
		setIconifiable(true);
		setTitle("\u6392\u8bfe\u7ba1\u7406");

		jLabel1.setText("\u5b66\u9662\uff1a");

		jComboBox1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox1ItemStateChanged(evt);
			}
		});

		jLabel2.setText("\u7cfb\u90e8\uff1a");

		jComboBox2.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox2ItemStateChanged(evt);
			}
		});

		jLabel4.setText("\u5b66\u671f\uff1a");

		jComboBox4.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox4ItemStateChanged(evt);
			}
		});

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "���", "�γ̱��", "�γ�����", "ѧ��", "��ѧʱ", "����ѧʱ",
						"ʵ��ѧʱ", "ʵ��ѧʱ", "����", "���ΰ༶", "�༶����", "�꼶", "��ʦ���",
						"�ڿν�ʦ", "����ѧ��", "��ý�����", "ʵ���Ƿ��ſ�", "�ſ�״̬" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jTable1MousePressed(evt);
			}
		});
		jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				jTable1KeyPressed(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u8bfe\u7a0b\u4fe1\u606f"));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u6388\u8bfe\u5468\u6570"));

		jCheckBox1.setText("1");

		jCheckBox2.setText("2");

		jCheckBox3.setText("3");

		jCheckBox4.setText("4");

		jCheckBox5.setText("5");

		jCheckBox6.setText("6");

		jCheckBox7.setText("7");

		jCheckBox8.setText("8");

		jCheckBox9.setText("9");

		jCheckBox10.setText("10");

		jCheckBox11.setText("11");

		jCheckBox12.setText("12");

		jCheckBox13.setText("13");

		jCheckBox14.setText("14");

		jCheckBox15.setText("15");

		jCheckBox16.setText("16");

		jButton1.setText("\u5168\u9009");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u6e05\u7a7a");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("\u524d\u516b\u5468");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setText("\u540e\u516b\u5468");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jButton5.setText("\u5355\u5468");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton6.setText("\u53cc\u5468");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGap(16, 16, 16)
										.addComponent(jCheckBox1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox4)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox5)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox6)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox7)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox8)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox9)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox10)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox11)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox12)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox13)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox14)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox15)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox16)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton4)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton5)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton6)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(jButton2)
														.addComponent(jButton3)
														.addComponent(jButton4)
														.addComponent(jButton5)
														.addComponent(jButton6)
														.addComponent(
																jCheckBox1)
														.addComponent(
																jCheckBox2)
														.addComponent(
																jCheckBox3)
														.addComponent(
																jCheckBox4)
														.addComponent(
																jCheckBox5)
														.addComponent(
																jCheckBox6)
														.addComponent(
																jCheckBox7)
														.addComponent(
																jCheckBox8)
														.addComponent(
																jCheckBox9)
														.addComponent(
																jCheckBox10)
														.addComponent(
																jCheckBox11)
														.addComponent(
																jCheckBox12)
														.addComponent(
																jCheckBox13)
														.addComponent(
																jCheckBox14)
														.addComponent(
																jCheckBox15)
														.addComponent(
																jCheckBox16))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabel6.setText("\u6388\u8bfe\u73ed\u7ea7\uff1a");

		jLabel7.setText("\u6388\u8bfe\u6559\u5e08\uff1a");

		jTextField2.setEditable(false);

		jLabel8.setText("\u6388\u8bfe\u6559\u5ba4\uff1a");

		jComboBox5.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox5ItemStateChanged(evt);
			}
		});

		jTextField1.setEditable(false);

		jList1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("\u5b9e\u9a8c\u5206\u5ba4"));
		jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				jList1ValueChanged(evt);
			}
		});
		jScrollPane3.setViewportView(jList1);

		jCheckBox17.setText("\u5b9e\u9a8c\u5ba4");
		jCheckBox17.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jCheckBox17ItemStateChanged(evt);
			}
		});
		jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox17ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextField1)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jTextField2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				170,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jCheckBox17)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jLabel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				67,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jComboBox5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				213,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												119,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(67, Short.MAX_VALUE)));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane3,
																0, 0,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel6Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel6)
																						.addComponent(
																								jComboBox5,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel8)
																						.addComponent(
																								jCheckBox17)
																						.addComponent(
																								jTextField2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel7)
																						.addComponent(
																								jTextField1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u989c\u8272\u56fe\u4f8b"));

		jTextField3.setBackground(new java.awt.Color(255, 0, 0));
		jTextField3.setEditable(false);
		jTextField3.setText("  \u5f53\u524d\u8bfe\u7a0b");
		jTextField3.setBorder(null);

		jTextField4.setBackground(new java.awt.Color(0, 128, 0));
		jTextField4.setEditable(false);
		jTextField4.setText("  \u5b66\u751f\u8bfe\u7a0b");
		jTextField4.setBorder(null);

		jTextField5.setBackground(new java.awt.Color(255, 255, 0));
		jTextField5.setEditable(false);
		jTextField5.setText("  \u6559\u5e08\u8bfe\u7a0b");
		jTextField5.setBorder(null);

		jTextField6.setBackground(new java.awt.Color(0, 255, 255));
		jTextField6.setEditable(false);
		jTextField6.setText("  \u6559\u5ba4\u8bfe\u7a0b");
		jTextField6.setBorder(null);

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jTextField3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextField4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												70,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextField5,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												66, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextField6,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												65, Short.MAX_VALUE)
										.addGap(13, 13, 13)));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE)
						.addComponent(jTextField4,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE)
						.addComponent(jTextField5,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE)
						.addComponent(jTextField6,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE));

		jTable2.setFont(new java.awt.Font("����", 0, 10));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "�ν�", "����һ", "���ڶ�", "������", "������", "������",
						"������", "������" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable2.setEnabled(false);
		jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable2MouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(jTable2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														1236, Short.MAX_VALUE)
												.addComponent(
														jPanel6,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														1236, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jComboBox1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		157,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jComboBox2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		174,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel4)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jComboBox4,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		147,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														jPanel7,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel2)
												.addComponent(
														jComboBox2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4)
												.addComponent(
														jComboBox4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										168,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel6,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel7,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										197, Short.MAX_VALUE).addContainerGap()));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents
	private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
		filltablecurriculum();
	}

	private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {

		if (jComboBox5.getSelectedItem() == null) {
			jList1.setEnabled(false);
			DefaultListModel dlm = new DefaultListModel();
			jList1.setModel(dlm);
			return;
		}
		if (jCheckBox17.isSelected()) {
			int cr_id = ((Classroom) jComboBox5.getSelectedItem()).getCr_id();
			String cou_id = (String) jTable1.getValueAt(
					jTable1.getSelectedRow(), 1);
			String grade = (String) jTable1.getValueAt(
					jTable1.getSelectedRow(), 11);
			String condition = "cr_id="
					+ cr_id
					+ " and ce_id in (select ce_id from courseexperimentalenvironment where grade='"
					+ grade + "' and cou_id='" + cou_id + "')";
			ArrayList<View_courselaboratory> aList = Courselaboratoryaccess
					.getCourselaboratorybycondition(condition);
			DefaultListModel dlm = new DefaultListModel();
			jList1.setModel(dlm);
			String sl_idString = aList.get(0).getSl_id();
			if (sl_idString != null) {
				String[] array_sl_id = sl_idString.split(",");
				String[] array_sl_name = aList.get(0).getSl_name().split(",");
				String[] array_sl_seating = aList.get(0).getSl_seating()
						.split(",");
				for (int i = 0; i < array_sl_id.length; i++) {
					int sl_id = Integer.parseInt(array_sl_id[i]);
					String sl_name = array_sl_name[i];
					int sl_seating = Integer.parseInt(array_sl_seating[i]);
					dlm.addElement(new Sublaboratory(sl_id, cr_id, sl_name,
							sl_seating));
				}
			}
			if (dlm.getSize() > 0)
				jList1.setEnabled(true);
			else
				jList1.setEnabled(false);
		} else {
			jList1.setEnabled(false);
			DefaultListModel dlm = new DefaultListModel();
			jList1.setModel(dlm);
		}
		filltablecurriculum();
	}

	private void jCheckBox17ItemStateChanged(java.awt.event.ItemEvent evt) {
		// ȡ�ð༶����
		int row = jTable1.getSelectedRow();
		if (row < 0)
			return;
		// ���ʵ���ҵ�ѡ��ť��ѡ�У��򰴵����༶����Ϊ��λ����õİ༶����Ϣ�ֽ⣬�浽�ַ���������
		if (jCheckBox17.isSelected()) {
			jLabel8.setText(" ʵ���ң�");
		} else {
			jLabel8.setText("�ڿν��ң�");
		}
		// ���ݵõ��İ༶�������༶�����б�
		fillcomboxclassroom();
	}

	private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {
		jTable1MousePressed(null);
	}

	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
		// �õ�ѡ�е���
		int row = jTable1.getSelectedRow();
		// ���û��ѡ�е��У����˳���ǰ����ִ��
		if (row < 0)
			return;

		// ��ȡ�༶���ƣ������ڿΰ༶�ı���ֵ
		jTextField1.setText((String) jTable1.getValueAt(row, 9));
		// ����ڿ�����δѡ���ʦ���������ʾ��Ϣ�������ÿؼ���ʼֵ���˳���ǰ�����ִ��
		if (((String) jTable1.getValueAt(row, 12)).equals("-1")) {
			JOptionPane.showMessageDialog(this,
					"�γ�����δѡ���ڿν�ʦ���뵽��ѧ�������ҳ�������ñ��γ��ڿν�ʦ��");
			// ȡ�ñ���model��Ϣ
			DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
			// ��ձ������ʾ������
			dtm.setRowCount(0);
			jTable2.setEnabled(false);
			return;
		} else {
			int cou_theoryhour = (Integer) jTable1.getValueAt(row, 5);
			if (cou_theoryhour > 0)
				jCheckBox17.setSelected(false);
			else
				jCheckBox17.setSelected(true);
			// ����Ļ������ÿؼ���Ӧ��ֵ
			jTextField2.setText((String) jTable1.getValueAt(row, 12));
			fillcomboxclassroom();
			jTable2.setEnabled(true);
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 0; i < jcb.length; i++)
			if (i % 2 == 1)
				jcb[i].setSelected(true);
		inweeks = "bdfhjlnp";
		filltablecurriculum();
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 0; i < jcb.length; i++)
			if (i % 2 == 0)
				jcb[i].setSelected(true);
		inweeks = "acegikmo";
		filltablecurriculum();
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 8; i < jcb.length; i++)
			jcb[i].setSelected(true);
		inweeks = "ijklmnop";
		filltablecurriculum();
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 0; i < 8; i++)
			jcb[i].setSelected(true);
		inweeks = "abcdefgh";
		filltablecurriculum();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < jcb.length; i++)
			jcb[i].setSelected(false);
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		dtm.setRowCount(0);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < jcb.length; i++)
			jcb[i].setSelected(true);
		inweeks = "abcdefghijklmnop";
		filltablecurriculum();
	}

	private void jTable2MousePressed(java.awt.event.MouseEvent evt) {

	}

	/**
	 * �ж�ָ����������ν��Ƿ�ռ��
	 * 
	 * @param row
	 *            �ж��Ŀν�
	 * @param column
	 *            �ж�������
	 * @return �ƶ������ڶ�Ӧ�Ŀν��Ƿ�ռ�ã�ռ�÷����棬δռ�÷��ؼ�
	 */
	private String getsublaboratory() {
		if (!jCheckBox17.isSelected())
			return "";
		Object[] sl = jList1.getSelectedValues();
		if (sl.length == 0)
			return "";
		String sl_name = ((Sublaboratory) sl[0]).getSl_name();
		for (int i = 1; i < sl.length; i++) {
			sl_name += "," + ((Sublaboratory) sl[i]).getSl_name();
		}
		return sl_name;
	}

	private boolean isOccupied(int row, int column) {
		String cla_name = jTextField1.getText();
		String[] cla = cla_name.split(",");
		String sl_name = getconditionsublaboratory(getsublaboratory());
		// �����������ڲ������ݿ��иýڿ����ʦ�����ҡ�ѧ��ʱ���Ƿ��ͻ
		String condition = "sy_id="
				+ ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id()
				+ " and (cla_name like '%" + cla[0] + "%'";
		for (int i = 1; i < cla.length; i++)
			condition += " or cla_name like '%" + cla[i] + "%'";
		condition += " or t_id='"
				+ (String) jTable1.getValueAt(jTable1.getSelectedRow(), 11)
				+ "'or " + sl_name + ") and lessons=" + (row + 1)
				+ " and week=" + column + " and tt_id!="
				+ (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		// ��ӽ�ѧ������
		String weeks = getconditioninweeks();
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// ����������ѯ���ݿ�
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		// ��������ݣ�������г�ͻ����ʱ��β����ſ�
		if (aList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж�ָ����������ν��Ƿ񱻵�ǰ�γ�ռ��
	 * 
	 * @param row
	 *            �ж��Ŀν�
	 * @param column
	 *            �ж�������
	 * @return �ǵĻ������ſα�ţ���Ļ�����-1
	 */
	private int Havebeeninclass(int row, int column) {

		// �����������ڲ������ݿ��иýڿ��Ƿ��Ѿ������γ�ռ��
		String condition = "sy_id="
				+ ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id()
				+ " and lessons=" + (row + 1) + " and week=" + column
				+ " and tt_id="
				+ (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		String weeks = getconditioninweeks();
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// ����������ѯ���ݿ�
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		if (aList.size() > 0)
			return aList.get(0).getCc_id();
		else
			return -1;
	}

	/**
	 * ɾ��ָ�����ſ���Ϣ
	 * 
	 * @param cc_id
	 *            ��Ҫɾ�����ſα��
	 * @return��ɾ���Ƿ�ɹ���
	 */
	private boolean deletecurriculum(int cc_id) {
		boolean b = false;

		return b;
	}

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
		// ��ȡ���������
		int clicked = evt.getClickCount();
		// ���������2���˳�����ִ��(���˫��)
		if (clicked != 2)
			return;
		// ��ȡ��ǰ������У�����Ŀν�����
		int row = jTable2.getSelectedRow();
		// ��ȡ��ǰ������У���������ڣ�
		int column = jTable2.getSelectedColumn();
		// ���ѡ�еĲ��ǿνڵ�λ�ã�������ͷ����ͷ��λ�ã������˳�
		if (row < 0 || column <= 0)
			return;
		// ȡ�ý�ѧ������
		int tt_id = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		// �ж������ʱ��α��γ��Ƿ��Ѿ��ſ�
		int cc_id = Havebeeninclass(row, column);
		// ����Ѿ��ſ�
		if (cc_id >= 0) {
			// ��ʾ��Ϣ
			int r = JOptionPane.showConfirmDialog(this, "�Ƿ�Ҫɾ�����γ��ڴ�ʱ��ε��ſΣ�",
					"��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
			// ���ѡ���ǰ�ť,��ɾ����ʱ��α��γ̵��ſ���Ϣ
			if (r == 0) {
				Connection con = null;
				try {
					con = Databaseconnection.getconnection();
					con.setAutoCommit(false);
					int re = Curriculumaccess.delete(con, cc_id);
					if (re < 1) {
						Tools.connectionroolback(con, "�ſ�ȡ��ʧ�ܣ�����ϵϵͳ����Ա��");
						return;
					}
					re = Teachingtaskaccess.updatestate(con, tt_id, 0);
					if (re < 1) {
						Tools.connectionroolback(con, "��ѧ����״̬�޸�ʧ�ܣ�����ϵϵͳ����Ա");
						return;
					}
					jTable1.setValueAt("δ����ſ�", jTable1.getSelectedRow(), 17);
					con.commit();
					filltablecurriculum();
					boolean b = rowlist.remove((Integer) jTable1
							.getSelectedRow());
					if (b) {
						Rowcolor.setColor(jTable1, rowlist, Color.green);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (!con.isClosed())
							con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			return;
		}
		// ����г�ͻ����ʱ��β����ſ�
		if (isOccupied(row, column)) {
			JOptionPane.showMessageDialog(this, "��ʱ���ѱ�ռ�ã������ſΣ�");
			return;
		}
		// ȡ�ý�ʦ���
		int cr_id = ((Classroom) jComboBox5.getSelectedItem()).getCr_id();
		// ��ȡ�γ���ѧʱ
		int counthour = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(),
				4);
		// ��ÿγ̵�����ѧʱ
		int theoryhour = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(),
				5);
		// ��ÿγ̵�ʵ����ʵ��ѧʱ
		int experimentalhours = (Integer) jTable1.getValueAt(
				jTable1.getSelectedRow(), 6);
		int practicehour = (Integer) jTable1.getValueAt(
				jTable1.getSelectedRow(), 7);
		String Practicescheduling = (String) jTable1.getValueAt(
				jTable1.getSelectedRow(), 16);
		if (Practicescheduling.equals("��")) {
			counthour -= practicehour;
			practicehour = 0;
		}
		// ���ò��ұ��ſγ����ſ�ʱ���Ĳ�ѯ����
		String condition = "tt_id=" + tt_id;
		// ���ұ��ſγ����ſ�ʱ��
		int classhour = Curriculumaccess.getClasshour(condition);
		// ȡ�õ�ǰ���ſεĿ�ʱ
		int currentinweeks = Tools.numberofweek(inweeks);
		// ���ſκ��ܿ�ʱ
		int newhour = currentinweeks + classhour;
		// �ж�����ſκ��ܿ�ʱ���޶�������ֹ�ſ�
		if (newhour > counthour) {
			JOptionPane.showMessageDialog(this, "�ſ�ѧʱ�ѳ����ܿ�ʱ���ƣ����ܼ����ſ�");
			return;
		}
		// �����ǰѡ����ʵ���ҵĻ�
		if (jCheckBox17.isSelected()) {
			// ���ò��ұ��ſγ��������ۿ�ʱ���Ĳ�ѯ����
			condition = "tt_id=" + tt_id + " and ct_id=3";
			// ���ұ��ſγ�����ʵ��ʵ����ʱ��
			classhour = Curriculumaccess.getClasshour(condition);
			// �ж�����ſκ�ʵ��ʵ���ܿ�ʱ���޶�������ʾ��Ϣ
			if ((currentinweeks + classhour) > (experimentalhours + practicehour)) {
				int r = JOptionPane.showConfirmDialog(this,
						"ʵ��ʵ���ſ�ѧʱ�ѳ�����ʱ���ƣ��Ƿ�����ſΣ�", "��ʾ��Ϣ",
						JOptionPane.YES_NO_OPTION);
				// ���ѡ�񲻼����ſ�,���˳�����ִ��
				if (r == 1)
					return;
			}
		} else {
			// ���ò��ұ��ſγ��������ۿ�ʱ���Ĳ�ѯ����
			condition = "tt_id=" + tt_id + " and (ct_id=1 or ct_id=2)";
			// ���ұ��ſγ��������ۿ�ʱ��
			classhour = Curriculumaccess.getClasshour(condition);
			// �ж�����ſκ������ܿ�ʱ���޶�������ʾ��Ϣ
			if (currentinweeks + classhour > theoryhour) {
				int r = JOptionPane.showConfirmDialog(this,
						"�����ſ�ѧʱ�ѳ������ۿ�ʱ���ƣ��Ƿ�����ſΣ�", "��ʾ��Ϣ",
						JOptionPane.YES_NO_OPTION);
				// ���ѡ�񲻼����ſ�,���˳�����ִ��
				if (r == 1)
					return;
			}
		}
		// �������ϻ�ȡ����Ϣ���ɿα���Ķ���
		Curriculum cc = new Curriculum(tt_id, row + 1, column, inweeks, cr_id,
				getsublaboratory());
		// �������ݿ�����Ӷ���
		Connection con = null;
		try {
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// ���ݿ����ӵ��Զ��ύ��Ϊ��
			con.setAutoCommit(false);

			// ���ɲ����ʱ��ογ�ռ��������������
			String cla_name = jTextField1.getText();
			String[] cla = cla_name.split(",");
			String sl_name = getconditionsublaboratory(getsublaboratory());
			// �����������ڲ������ݿ��иýڿ����ʦ�����ҡ�ѧ��ʱ���Ƿ��ͻ
			condition = "sy_id="
					+ ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id()
					+ " and (cla_name like '%" + cla[0] + "%'";
			for (int i = 1; i < cla.length; i++)
				condition += " or cla_name like '%" + cla[i] + "%'";
			condition += " or t_id='"
					+ (String) jTable1.getValueAt(jTable1.getSelectedRow(), 11)
					+ "'or " + sl_name + ") and lessons=" + (row + 1)
					+ " and week=" + column + " and tt_id!="
					+ (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
			String weeks = getconditioninweeks();
			if (!weeks.equals(""))
				condition += " and " + weeks;
			// ���÷������α������Ϣ���뵽���ݿ���
			int r = Curriculumaccess.insert(con, cc, condition);
			// ���벻�ɹ�����ʾ������Ϣ�����ݿ�ع���Ȼ���˳��������ִ��
			if (r == -2) {
				Tools.connectionroolback(con, "��ʱ����Ѿ��������γ�ռ�ã���ѡ������ʱ���");
				filltablecurriculum();
				return;
			}
			if (r < 1) {
				Tools.connectionroolback(con, "�ſ���Ϣ����ʧ�ܣ�����ϵϵͳ����Ա");
				return;
			}
			boolean b = false;
			if (newhour == counthour) {
				r = Teachingtaskaccess.updatestate(con, tt_id, 1);
				if (r < 1) {
					Tools.connectionroolback(con, "��ѧ����״̬�޸�ʧ�ܣ�����ϵϵͳ����Ա");
					return;
				}
				jTable1.setValueAt("����ſ�", jTable1.getSelectedRow(), 17);
				b = rowlist.add(jTable1.getSelectedRow());
			}
			// ���������ύ����
			con.commit();
			// ˢ�¿γ̱������
			filltablecurriculum();
			if (b) {
				Rowcolor.setColor(jTable1, rowlist, Color.green);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
		fillteachingtasktable();
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		fillteachingtasktable();
	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		if (vt.getT_power().equals("ϵ���ſ�")) {
			Office o = new Office(vt.getO_id(), vt.getD_id(), vt.getO_name());
			jComboBox2.addItem(o);
			return;
		}
		Department dm = (Department) jComboBox1.getSelectedItem();
		if (dm == null)
			return;
		String d_id = dm.getD_id();
		Fillcombobox.filloffice(d_id, jComboBox2);
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox10;
	private javax.swing.JCheckBox jCheckBox11;
	private javax.swing.JCheckBox jCheckBox12;
	private javax.swing.JCheckBox jCheckBox13;
	private javax.swing.JCheckBox jCheckBox14;
	private javax.swing.JCheckBox jCheckBox15;
	private javax.swing.JCheckBox jCheckBox16;
	private javax.swing.JCheckBox jCheckBox17;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JCheckBox jCheckBox4;
	private javax.swing.JCheckBox jCheckBox5;
	private javax.swing.JCheckBox jCheckBox6;
	private javax.swing.JCheckBox jCheckBox7;
	private javax.swing.JCheckBox jCheckBox8;
	private javax.swing.JCheckBox jCheckBox9;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JList jList1;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	// End of variables declaration//GEN-END:variables

}