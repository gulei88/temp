package util;

import java.io.File;

import javax.swing.JFileChooser;

import model.excelFileFilter;

public class fileseletion {

	public static File exportselect() {
		JFileChooser fDialog=new JFileChooser();//��ʼ���ļ�ѡ�񴰿�
		excelFileFilter excel=new excelFileFilter();//ʵ����File Filter����������ʾexcel�ļ�,����ɸѡ
		fDialog.addChoosableFileFilter(excel);//��ѡ������ӵ�������
		fDialog.setDialogTitle("��ѡ���ļ�");//���ô��ڱ���
		int re=fDialog.showOpenDialog(null);//��ʾ�ļ�ѡ�񴰿�
		File table=fDialog.getSelectedFile();
		
		return table;
	}
}
