package model;

import java.io.File;

import javax.swing.filechooser.FileFilter;
import javax.xml.crypto.dsig.spec.XPathType.Filter;

public class excelFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		String name=f.getName();
		//仅显示目录以及xls，xlsx文件
		return f.isDirectory()||name.toLowerCase().endsWith(".xls")||name.toLowerCase().endsWith(".xlsx");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "*.xls;*.xlsx";
	}

}
