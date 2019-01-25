package util;

import java.io.File;

import javax.swing.JFileChooser;

import model.excelFileFilter;

public class fileseletion {

	public static File exportselect() {
		JFileChooser fDialog=new JFileChooser();//初始化文件选择窗口
		excelFileFilter excel=new excelFileFilter();//实例化File Filter对象用来显示excel文件,进行筛选
		fDialog.addChoosableFileFilter(excel);//将选择器添加到窗口中
		fDialog.setDialogTitle("请选择文件");//设置窗口标题
		int re=fDialog.showOpenDialog(null);//显示文件选择窗口
		File table=fDialog.getSelectedFile();
		
		return table;
	}
}
