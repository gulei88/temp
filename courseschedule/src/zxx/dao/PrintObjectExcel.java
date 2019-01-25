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
	 * 调用案例：
	 * (1)List<String>
	 * 	List<String> list =new  ArrayList<String>();
	 *	String[] header = {"异常输出"};
	 * 	List<String> aList =PrintObjectExcel.printExcel(list, "C:/Users/zengx/Desktop/trouble.xls",header);
	 * 	for(String item : aList){
	 * 		System.out.println(item);
	 *	 }
	 *
	 *
	 *  (2)List<Department>
	 *  List<Department> list =new ArrayList<Department>();
	 *  String[] header = {"部门编号","部门名称"};
	 *  list.add(new Department("30","学院名称"));
	 *  List<String> aList =PrintObjectExcel.printExcel(list, "C:/Users/zengx/Desktop/trouble.xls",header);
	 */
	
	
	
	
	
	
	/**
	 * 异常集合
	 * return List<String>
	 */
	public static List<String> trouble = new ArrayList<String>();
	
	
	/** 
	 * 将实体类集合强制转化成List<Objdect>,并调用打印方法
	 * @author zxx
	 * @param list:接收所有类型集合
	 * @param fileName:打印excel的文件路径
	 * @param header:表头信息
	 * @return 返回异常集合List<String> trouble ,方便把异常提交到前台输出。
	 *  
	 */
	public static <E> List<String> printExcel(List<E> list, String fileName, String[] header)  {
		//强制转化类型
		List<Object> mList = new ArrayList<Object>();
		for (Object e : list) {
			Object obj = (Object) e;
			mList.add(obj);
		}
		//判断数据是否为空
		if (mList.size() == 0) {
			trouble.add("导入数据为空");
			return trouble;
		}
		//单个数据类型进行判断
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
	 * 将转化后的List<Object>进行数据映射，得到字段名和字段值，并输出到指定文件路径的excel中
	 * @author zxx
	 * @param list:转化后的集合数据
	 * @param file:打印excel的文件路径
	 * @param header:表头信息，进行输出表头
	 */

	public static void PrintFile(List<Object> list, String file, String[] header){
		// 创建Excel文档
		HSSFWorkbook hwb = new HSSFWorkbook();
		// 通过excle对象创建一个选项卡对象
		HSSFSheet sheet = hwb.createSheet("sheet1");
		// sheet.setColumnWidth(0, 50 * 256);
		//第0行表头输出
		HSSFRow row = sheet.createRow(0);
		//表头信息打印
		for(int i = 0; i<header.length;i++){
			
			row.createCell(i).setCellValue(header[i]);
		}
		
		// 循环list创建行
		for (int i = 0; i < list.size(); i++) {
			// 新建一行
			row = sheet.createRow(i + 1);
			Object obj = list.get(i);
			// 得到类对象
			Class userCla = (Class) obj.getClass();

			/* 得到类中的所有属性集合 */
			Field[]	fs = userCla.getDeclaredFields();
			for (int j = 0; j < fs.length; j++) {
				Field f = fs[j];
				f.setAccessible(true); // 设置些属性是可以访问的

				try {
					String temp = "";
					System.out.println(f.get(obj));
					if(f.get(obj) != null)
						temp = f.get(obj).toString();	
					row.createCell(j).setCellValue(temp);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					trouble.add("不合法的参数异常");
				//	e.printStackTrace();
					
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					trouble.add("安全权限异常");
					//e.printStackTrace();
				}
			}

		}
		OutputStream out = null;
		try {
			//文件数据写入
			out = new FileOutputStream(file);
			hwb.write(out);
			
		} catch (FileNotFoundException e) {
			trouble.add("文件被占用");
		
		} catch (IOException e) {
			trouble.add("写入文件异常");
			//e.printStackTrace();
		} finally {
			try {
				if(out!=null)
					out.close();
			} catch (IOException e) {
				trouble.add("文件关闭异常");
				//e.printStackTrace();
			}
		}
	
	}
	
	/**
	 * 
	 * 将转化后的List<Object>进行数据映射，判断出数据单个数据类型为String或者Integer，并输出到指定文件路径的excel中
	 * @author zxx
	 * @param list:转化后的集合数据
	 * @param file:打印excel的文件路径
	 * @param header:表头信息，进行输出表头
	 */

	public static void PrintString(List<Object> list, String file , String[] header) {
		// 创建Excel文档
		HSSFWorkbook hwb = new HSSFWorkbook();
		// 通过excle对象创建一个选项卡对象
		HSSFSheet sheet = hwb.createSheet("sheet1");
		// sheet.setColumnWidth(0, 50 * 256);
		
		HSSFRow row = sheet.createRow(0);

		//表头打印
		row.createCell(0).setCellValue(header[0]);

		// 循环list创建行
		for (int i = 0; i < list.size(); i++) {
			// 新建一行
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
			trouble.add("文件被占用");
			
		} catch (IOException e) {
			trouble.add("写入文件异常");
			//e.printStackTrace();
		} finally {
			try {
				if(out!=null)
					out.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				trouble.add("关闭异常");
				//e.printStackTrace();
			}
		}
	}
}
