import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class User {
	private String userName;	//�˺�
	private String userPwd;	//����
	private int[] grade;	//�÷ּ�¼
	public User(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public int[] getGrade() {
		return grade;
	}
	public void setGrade(int[] grade) {
		this.grade = grade;
	}	
	
	public static ArrayList<User> getUser() {
		ArrayList<User> list = new ArrayList<User>();
		User r = new User(" ", " ");
		list.add(r);
		return list;
	}
	
	public static void createExcel() {
		/**
		 * ����EXCEL�ļ�
		 */
		//��һ������workbook   
        HSSFWorkbook wb = new HSSFWorkbook(); 
        //�ڶ�������sheet  
        HSSFSheet sheet = wb.createSheet("��Ϸ�û�");
        //������������row:��ӱ�ͷ0��
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //����   
        //���Ĳ�������Ԫ��
        HSSFCell cell = row.createCell(0);	//��һ����Ԫ��
        cell.setCellValue("�˺�");	//�趨ֵ
        cell.setCellStyle(style);
        
        cell = row.createCell(1);	//�ڶ�����Ԫ��
        cell.setCellValue("����");
        cell.setCellStyle(style);
        
        
        //�����������ɵ�excel�ļ����浽ָ��·����
        try {
        	FileOutputStream fout = new FileOutputStream("D:\\2048GameUser.xls");
        	wb.write(fout);
        	fout.close();
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
	public static void ExportExcel(String userName, String userPwd) throws Exception {
		/**
		 * ������ɫ
		 */
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row;
        User user = new User(userName, userPwd);
        row = sheet.createRow(sheet.getLastRowNum() + 1);
        row.createCell(0).setCellValue(user.getUserName());
        row.createCell(1).setCellValue(user.getUserPwd());
        //�����ɵ�excel�ļ����浽ָ��·����
        try {
        	FileOutputStream fout = new FileOutputStream("D:\\2048GameUser.xls");
        	wb.write(fout);
        	fout.close();
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
	public static void readExcel() throws Exception {
		/**
		 * ��Excel�ļ�
		 */
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		for(int i=rowstart; i <= rowEnd; i++) {
			HSSFRow row = hssfSheet.getRow(i);
			if(null == row) continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();
			for(int k=cellStart; k <= cellEnd; k++) {
				HSSFCell cell = row.getCell(k);
				if(null == cell) continue;
				if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					String s = cell.getStringCellValue();
				}
			}
		}
	}
	
	public static boolean existAccount(String account) throws Exception {
		/**
		 * ����Ƿ��Ѵ����û�
		 */
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		for(int i=rowstart; i <= rowEnd; i++) {
			HSSFRow row = hssfSheet.getRow(i);
			if(null == row) continue;
			HSSFCell cell = row.getCell(0);
			String s = cell.getStringCellValue();
			if(s.equals(account)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean judgeUserPwd(String userName, String pwd) throws Exception {
		/**
		 * �����˻��ж������Ƿ���ȷ
		 */
		String userPwd = null;
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		for(int i=rowstart; i <= rowEnd; i++) {
			HSSFRow row = hssfSheet.getRow(i);
			if(null == row) continue;
			HSSFCell cell = row.getCell(0);
			String s = cell.getStringCellValue();
			if(s.equals(userName)) {
				if(pwd.equals(row.getCell(1).getStringCellValue())) {
					return true;
				}		
			}
		}
		return false;
	}
	
	public static void exportGrade(String userName, int grade) throws Exception{
		/**
		 * ����������¼
		 */
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = wb.getSheetAt(0);
		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		for(int i = rowstart; i <= rowEnd; i++) {
			HSSFRow row = hssfSheet.getRow(i);
			if(null == row) continue;
			HSSFCell cell = row.getCell(0);
			String s = cell.getStringCellValue();
			if(s.equals(userName)) {
				row.createCell(row.getLastCellNum()).setCellValue(grade);
			}
		}
		//�����������ɵ�excel�ļ����浽ָ��·����
        try {
        	FileOutputStream fout = new FileOutputStream("D:\\2048GameUser.xls");
        	wb.write(fout);
        	fout.close();
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
}
