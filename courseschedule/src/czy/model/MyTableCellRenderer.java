package czy.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * 设置单元格背景颜色
 * 
 * @author czy
 * 
 */
public class MyTableCellRenderer implements TableCellRenderer {
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	Coordinate student[], teacher[], classroom[], current[];
	Color studentcolor, teachercolor, classroomcolor, currentcolor;

	public MyTableCellRenderer(Coordinate[] student, Coordinate[] teacher,
			Coordinate[] classroom, Coordinate[] current, Color studentcolor,
			Color teachercolor, Color classroomcolor, Color currentcolor) {
		super();
		this.student = student;
		this.teacher = teacher;
		this.classroom = classroom;
		this.current = current;
		this.studentcolor = studentcolor;
		this.teachercolor = teachercolor;
		this.classroomcolor = classroomcolor;
		this.currentcolor = currentcolor;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = dtcr.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		renderer.setBackground(Color.white);

		for (int i = 0; i < classroom.length; i++)
			if (row == classroom[i].getRow()
					&& column == classroom[i].getColumn())
				renderer.setBackground(classroomcolor);
		for (int i = 0; i < teacher.length; i++)
			if (row == teacher[i].getRow() && column == teacher[i].getColumn())
				renderer.setBackground(teachercolor);
		for (int i = 0; i < student.length; i++)
			if (row == student[i].getRow() && column == student[i].getColumn())
				renderer.setBackground(studentcolor);
		for (int i = 0; i < current.length; i++)
			if (row == current[i].getRow() && column == current[i].getColumn())
				renderer.setBackground(currentcolor);
		return renderer;
	}
}
