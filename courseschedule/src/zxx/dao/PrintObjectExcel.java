package zxx.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import global.dao.Databaseconnection;

public class PrintObjectExcel {
	
	
	/**
	 * 
	 * ���ð�����
	 * (1)List<String>
	 * 	List<String> list =new  ArrayList<String>();
	 *	String[] header = {"�쳣���"};
	 * 	List<String> aList =PrintObjectExcel.printExcel(list, "C:/Users/zengx/Desktop/trouble.xls",header);
	 * 	for(String item : aList){
	 * 		System.out.println(item);
	 *	 }
	 *
	 *
	 *  (2)List<Department>
	 *  List<Department> list =new ArrayList<Department>();
	 *  String[] header = {"���ű��","��������"};
	 *  list.add(new Department("30","ѧԺ����"));
	 *  List<String> aList =PrintObjectExcel.printExcel(list, "C:/Users/zengx/Desktop/trouble.xls",header);
	 */
	
	
	
	
	
	
	/**
	 * �쳣����
	 * return List<String>
	 */
	public static List<String> trouble = new ArrayList<String>();
	
	
	/** 
	 * ��ʵ���༯��ǿ��ת����List<Objdect>,�����ô�ӡ����
	 * @author zxx
	 * @param list:�����������ͼ���
	 * @param fileName:��ӡexcel���ļ�·��
	 * @param header:��ͷ��Ϣ
	 * @return �����쳣����List<String> trouble ,������쳣�ύ��ǰ̨�����
	 *  
	 */
	public static <E> List<String> printExcel(List<E> list, String fileName, String[] header)  {
		//ǿ��ת������
		List<Object> mList = new ArrayList<Object>();
		for (Object e : list) {
			Object obj = (Object) e;
			mList.add(obj);
		}
		//�ж������Ƿ�Ϊ��
		if (mList.size() == 0) {
			trouble.add("��������Ϊ��");
			return trouble;
		}
		//�����������ͽ����ж�
		System.out.println(list.get(0).getClass().getName());
		if (list.get(0).getClass().getName().equals("java.lang.String")
				|| list.get(0).getClass().getName().equals("java.lang.Integer")) 
		{
			PrintString(mList, fileName ,header);
			return trouble;
		} else {
			try {
				PrintFile(mList, fileName ,header);
				return trouble;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return trouble;

	}
	
	/**
	 * 
	 * ��ת�����List<Object>��������ӳ�䣬�õ��ֶ������ֶ�ֵ���������ָ���ļ�·����excel��
	 * @author zxx
	 * @param list:ת����ļ�������
	 * @param file:��ӡexcel���ļ�·��
	 * @param header:��ͷ��Ϣ�����������ͷ
	 */

	public static void PrintFile(List<Object> list, String file, String[] header){
		// ����Excel�ĵ�
		HSSFWorkbook hwb = new HSSFWorkbook();
		// ͨ��excle���󴴽�һ��ѡ�����
		HSSFSheet sheet = hwb.createSheet("sheet1");
		// sheet.setColumnWidth(0, 50 * 256);
		//��0�б�ͷ���
		HSSFRow row = sheet.createRow(0);
		//��ͷ��Ϣ��ӡ
		for(int i = 0; i<header.length;i++){
			
			row.createCell(i).setCellValue(header[i]);
		}
		
		// ѭ��list������
		for (int i = 0; i < list.size(); i++) {
			// �½�һ��
			row = sheet.createRow(i + 1);
			Object obj = list.get(i);
			// �õ������
			Class userCla = (Class) obj.getClass();

			/* �õ����е��������Լ��� */
			Field[]	fs = userCla.getDeclaredFields();
			for (int j = 0; j < fs.length; j++) {
				Field f = fs[j];
				f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�

				try {
					String temp = "";
					System.out.println(f.get(obj));
					if(f.get(obj) != null)
						temp = f.get(obj).toString();	
					row.createCell(j).setCellValue(temp);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					trouble.add("���Ϸ��Ĳ����쳣");
				//	e.printStackTrace();
					
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					trouble.add("��ȫȨ���쳣");
					//e.printStackTrace();
				}
			}

		}
		OutputStream out = null;
		try {
			//�ļ�����д��
			out = new FileOutputStream(file);
			hwb.write(out);
			
		} catch (FileNotFoundException e) {
			trouble.add("�ļ���ռ��");
		
		} catch (IOException e) {
			trouble.add("д���ļ��쳣");
			//e.printStackTrace();
		} finally {
			try {
				if(out!=null)
					out.close();
			} catch (IOException e) {
				trouble.add("�ļ��ر��쳣");
				//e.printStackTrace();
			}
		}
	
	}
	
	/**
	 * 
	 * ��ת�����List<Object>��������ӳ�䣬�жϳ����ݵ�����������ΪString����Integer���������ָ���ļ�·����excel��
	 * @author zxx
	 * @param list:ת����ļ�������
	 * @param file:��ӡexcel���ļ�·��
	 * @param header:��ͷ��Ϣ�����������ͷ
	 */

	public static void PrintString(List<Object> list, String file , String[] header) {
		// ����Excel�ĵ�
		HSSFWorkbook hwb = new HSSFWorkbook();
		// ͨ��excle���󴴽�һ��ѡ�����
		HSSFSheet sheet = hwb.createSheet("sheet1");
		// sheet.setColumnWidth(0, 50 * 256);
		
		HSSFRow row = sheet.createRow(0);

		//��ͷ��ӡ
		row.createCell(0).setCellValue(header[0]);

		// ѭ��list������
		for (int i = 0; i < list.size(); i++) {
			// �½�һ��
			row = sheet.createRow(i + 1);
			String temp = "";
			if(list.get(i) != null)
				temp = list.get(i).toString();
			row.createCell(0).setCellValue(temp);

		}
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			hwb.write(out);
		} catch (FileNotFoundException e) {
			trouble.add("�ļ���ռ��");
			
		} catch (IOException e) {
			trouble.add("д���ļ��쳣");
			//e.printStackTrace();
		} finally {
			try {
				if(out!=null)
					out.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				trouble.add("�ر��쳣");
				//e.printStackTrace();
			}
		}
	}
}
