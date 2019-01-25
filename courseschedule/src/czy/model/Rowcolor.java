package czy.model;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Rowcolor {
	public static void setColor(JTable table,
			final ArrayList<Integer> rowIndex, final Color color) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					setBackground(Color.WHITE);
					for (int i = 0; i < rowIndex.size(); i++)
						if (row == rowIndex.get(i))
							setBackground(color);
					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
				}
			};
			int columnCount = table.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
