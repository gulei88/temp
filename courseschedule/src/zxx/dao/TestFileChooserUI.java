package zxx.dao;


import global.dao.ExcelFileFilter;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
 
/**
 * 类名： TestFileChooserUI 描述：
 * 
 * @author admin
 * @date Mar 20, 2013 4:11:04 PM
 * 
 * 
 */
public class TestFileChooserUI {
	private static JLabel findLabel(JComponent comp, String s) {
		JLabel label = null;
		if (comp instanceof JLabel) {
			if (((JLabel) comp).getText().equals(s)) {
				label = (JLabel) comp;
			}
		} else if (comp instanceof JComponent) {
			Component[] comps = comp.getComponents();
			for (int i = 0; i < comps.length; i++) {
				if (comps[i] instanceof JComponent) {
					label = findLabel((JComponent) comps[i], s);
					if (label != null) {
						break;
					}
				}
			}
		}
		return label;
	}
 
	public static Component getLabelForInChooser(JFileChooser chooser,
			String key) {
		java.util.Locale l = chooser.getLocale();
		String s = UIManager.getString(key, l);
 
		javax.swing.plaf.FileChooserUI ui = chooser.getUI();
		int count = ui.getAccessibleChildrenCount(chooser);
		for (int i = 0; i < count; i++) {
			javax.accessibility.Accessible a = ui
					.getAccessibleChild(chooser, i);
			JLabel label = findLabel((JComponent) a, s);
			if (label != null) {
				return label.getLabelFor();
			}
		}
		return null;
	}
 
	public static String main(String aString) {
		JFileChooser fDialog = new JFileChooser();
		Component comp = getLabelForInChooser(fDialog,
				"FileChooser.fileNameLabelText");
		if (comp instanceof JTextField) {
			JTextField field = ((JTextField) comp);
			field.setEditable(false);
			// 随意
			// field.setBackground(Color.WHITE);
		}
		ExcelFileFilter excelFilter = new ExcelFileFilter();
		// 设置文本框类型
		fDialog.addChoosableFileFilter(excelFilter);
		// 设置文件选择框的标题
		fDialog.setFileFilter(excelFilter);
		int returnVal = fDialog.showOpenDialog(null);
		String table = fDialog.getSelectedFile().toString();
//		System.out.println(table);
		return table;
	}
}

