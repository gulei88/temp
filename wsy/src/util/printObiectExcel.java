package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class printObiectExcel {

	/**
	 * �쳣����
	 * �����쳣�׳�
	 * 
	 */
	public static ArrayList<String> exception=new ArrayList<String>();
	/**
	 * 
	 * @param list
	 * @param header
	 * @param file
	 * @author ��
	 * @return
	 */
	public static <E> List<String> printExcel(List<E> list,String[] header,String file ) {
		List<Object> mlist = new ArrayList<Object>();
		for (Object e : list) {
			Object obj = (Object) e;
			mlist.add(obj);
		}
		if (mlist.size()==0) {
			exception.add("��������Ϊ��");
			return null;
		}
		printFile(mlist, header, file);
		return exception;
	}
	/**
	 *    	���������ݴ�ӡ���ļ���
	 * @param list ��������
	 * @param header ��ͷ��Ϣ
	 * @param file �ļ���
	 */
	public static void printFile(List<Object> list,String[] header,String file) {
		HSSFWorkbook h=new HSSFWorkbook();//�½�excel����
		HSSFSheet sheet=h.createSheet("����");//�½�������
		HSSFRow row=sheet.createRow(0);//�½���ͷ
		for (int i = 0; i < header.length; i++) {
			row.createCell(i).setCellValue(header[i]);//���ñ�ͷ
		}
		/*
		 * д����
		 */
		for (int i = 0; i < list.size(); i++) {
			row=sheet.createRow(i+1);//��һ��
			Object object=list.get(i);//����i������ȡ��
			Class c=object.getClass();//�����ŵ�c��
			Field[] f=c.getDeclaredFields();//��c�����ݴ洢���ļ�������
			/*
			 * д����
			 */
			for (int j = 0; j < f.length; j++) {
				Field t=f[j];
				String temp="";
				t.setAccessible(true);//���ø����Կ��Ա�����
				try {
					if (t.get(object)!=null) {//��������Բ����ڿ�
						temp=t.get(object).toString();//ȡ�������ݲ�����ת��Ϊ�ַ�������
						row.createCell(j).setCellValue(temp);//�������ݴ�ӡ����ǰ��
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exception.add("�������Ϸ�");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					exception.add("��ȫȨ���쳣");
				}
			}
			
			OutputStream outputStream=null;
			try {
				outputStream=new FileOutputStream(file);//ʵ��������ļ�����
				h.write(outputStream);//��ǰ���ӡ�������ݴ�ŵ��ļ���
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				exception.add("�ļ���ռ��");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				exception.add("д���ļ��쳣");
				e.printStackTrace();
			}finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					exception.add("�ر��쳣");
					e.printStackTrace();
				}
			}
		}
	}
}