package global.dao;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * 
 * @author ZZT
 *	@return excel表文件
 */
public class Fileselection {
	/**
	 * 封装导入和导出excel表的方法
	 * 
	 * @author ZZT
	 * @return excel表文件
	 */
			//导入excel表文件
	public static File fillselect(){
			JFileChooser fDialog = new JFileChooser();
			
			//初始化ExcelFileFilter文件类型过滤类
			ExcelFileFilter excelFilter = new ExcelFileFilter();
			//设置文本框类型
			fDialog.addChoosableFileFilter(excelFilter);
			//设置文件选择框的标题 
			fDialog.setFileFilter(excelFilter);
			//浏览文件
			fDialog.setDialogTitle("请选择您要导入的文件");
			//弹出选择框
			int returnVal = fDialog.showOpenDialog(null);
			fDialog.setSelectedFile(new File("department.xls")); //设置默认文件名
//			tf.setEditable(false);
			//定义table变量用来存储要浏览文件的路径
			fDialog.setSelectedFile(null);
			File table = fDialog.getSelectedFile();
			table.delete();
			//返回值表文件table
//			table.setFileHidingEnabled(true);
//			table.setAcceptAllFileFilterUsed(false)
			return table;
	}
	
	
	public static File exportselect(){
		JFileChooser fDialog = new JFileChooser();
		//初始化ExcelFileFilter文件类型过滤类
		ExcelFileFilter excelFilter = new ExcelFileFilter();
		//设置文本框类型
		fDialog.addChoosableFileFilter(excelFilter);
		//设置文件选择框的标题 
		fDialog.setFileFilter(excelFilter);
		//浏览文件
		fDialog.setDialogTitle("请选择文件");
		//弹出选择框
		int returnVal = fDialog.showOpenDialog(null);
		//定义table变量用来存储要浏览文件的路径
		File table = fDialog.getSelectedFile();
		if(table == null){
//			System.out.println(1);
//			JOptionPane.showConfirmDialog(fDialog, "名称不能为空");
		}
//		System.out.println(111);
		//返回值表文件table
		return table;
}
}
	

