package global.dao;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * 
 * @author ZZT
 *	@return excel���ļ�
 */
public class Fileselection {
	/**
	 * ��װ����͵���excel��ķ���
	 * 
	 * @author ZZT
	 * @return excel���ļ�
	 */
			//����excel���ļ�
	public static File fillselect(){
			JFileChooser fDialog = new JFileChooser();
			
			//��ʼ��ExcelFileFilter�ļ����͹�����
			ExcelFileFilter excelFilter = new ExcelFileFilter();
			//�����ı�������
			fDialog.addChoosableFileFilter(excelFilter);
			//�����ļ�ѡ���ı��� 
			fDialog.setFileFilter(excelFilter);
			//����ļ�
			fDialog.setDialogTitle("��ѡ����Ҫ������ļ�");
			//����ѡ���
			int returnVal = fDialog.showOpenDialog(null);
			fDialog.setSelectedFile(new File("department.xls")); //����Ĭ���ļ���
//			tf.setEditable(false);
			//����table���������洢Ҫ����ļ���·��
			fDialog.setSelectedFile(null);
			File table = fDialog.getSelectedFile();
			table.delete();
			//����ֵ���ļ�table
//			table.setFileHidingEnabled(true);
//			table.setAcceptAllFileFilterUsed(false)
			return table;
	}
	
	
	public static File exportselect(){
		JFileChooser fDialog = new JFileChooser();
		//��ʼ��ExcelFileFilter�ļ����͹�����
		ExcelFileFilter excelFilter = new ExcelFileFilter();
		//�����ı�������
		fDialog.addChoosableFileFilter(excelFilter);
		//�����ļ�ѡ���ı��� 
		fDialog.setFileFilter(excelFilter);
		//����ļ�
		fDialog.setDialogTitle("��ѡ���ļ�");
		//����ѡ���
		int returnVal = fDialog.showOpenDialog(null);
		//����table���������洢Ҫ����ļ���·��
		File table = fDialog.getSelectedFile();
		if(table == null){
//			System.out.println(1);
//			JOptionPane.showConfirmDialog(fDialog, "���Ʋ���Ϊ��");
		}
//		System.out.println(111);
		//����ֵ���ļ�table
		return table;
}
}
	

