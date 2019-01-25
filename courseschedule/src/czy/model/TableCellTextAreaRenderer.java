package czy.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TableCellTextAreaRenderer extends JTextArea implements
		TableCellRenderer {
	Coordinate student[], teacher[], classroom[], current[];
	Color studentcolor, teachercolor, classroomcolor, currentcolor;

	public TableCellTextAreaRenderer(Coordinate[] student,
			Coordinate[] teacher, Coordinate[] classroom, Coordinate[] current,
			Color studentcolor, Color teachercolor, Color classroomcolor,
			Color currentcolor) {
		super();
		this.student = student;
		this.teacher = teacher;
		this.classroom = classroom;
		this.current = current;
		this.studentcolor = studentcolor;
		this.teachercolor = teachercolor;
		this.classroomcolor = classroomcolor;
		this.currentcolor = currentcolor;
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setBackground(Color.white);
		for (int i = 0; i < classroom.length; i++)
			if (row == classroom[i].getRow()
					&& column == classroom[i].getColumn())
				setBackground(classroomcolor);
		for (int i = 0; i < teacher.length; i++)
			if (row == teacher[i].getRow() && column == teacher[i].getColumn())
				setBackground(teachercolor);
		for (int i = 0; i < student.length; i++)
			if (row == student[i].getRow() && column == student[i].getColumn())
				setBackground(studentcolor);
		for (int i = 0; i < current.length; i++)
			if (row == current[i].getRow() && column == current[i].getColumn())
				setBackground(currentcolor);
		// 计算当下行的最佳高度
		int maxPreferredHeight = 0;
		for (int i = 0; i < table.getColumnCount(); i++) {
			setText("" + table.getValueAt(row, i));
			setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
			maxPreferredHeight = Math.max(maxPreferredHeight,
					getPreferredSize().height);
		}

		if (table.getRowHeight(row) != maxPreferredHeight) // 少了这行则处理器瞎忙
			table.setRowHeight(row, maxPreferredHeight);

		setText(value == null ? "" : value.toString());
		return this;
	}
}