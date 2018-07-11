import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class GradeFrame extends JFrame {
	/**
	 * 显示分数列表
	 */
	String[] columnNames = {"序号","得分"};
	JScrollPane jsPanel;
	public GradeFrame() {
		setTitle("2048");
		setSize(400, 400);
		setLocation(100, 100);
		//更换Java咖啡图标
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/title.png"));	
		this.setIconImage(imageIcon.getImage());	
		try {
			jsPanel = scan(loginFrame.getUserName(), columnNames);
			this.add(jsPanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 居中窗体
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static JScrollPane scan(String userName, String[] columnname) throws Exception {
		/**
		 * 得到得分记录
		 */
		Object[][] content = new Object[40][40];
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		int index = 0;
		for(int i=rowstart+1; i <= rowEnd; i++) {
			HSSFRow row = hssfSheet.getRow(i);
			if(null == row) continue;
			HSSFCell cell = row.getCell(0);
			String s = cell.getStringCellValue();
			if(s.equals(userName)) {
				int cellEnd = row.getLastCellNum();
				for(int k = 2; k <= cellEnd; k++) {
					HSSFCell grade_cell = row.getCell(k);
					if(null == grade_cell) continue;
					if(grade_cell.getCellType() == (int)HSSFCell.CELL_TYPE_NUMERIC) {
						String ss = grade_cell.getNumericCellValue()+"";
						content[index][0] = index+1;
						content[index][1] = ss;
						index++;
					}
				}
			} else {
				continue;
			}
		}
		JTable table = new JTable(content, columnname);
		table.setBackground(Color.GRAY);
		table.setForeground(Color.WHITE);
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);
		table.getTableHeader().setFont(new Font("微软雅黑", 0, 19));
		table.setRowHeight(20);
		
		JScrollPane jScrollPane1 = new JScrollPane(table);
		jScrollPane1.setBounds(100, 100, 300, 300);
		return jScrollPane1;
	}
	
	public static int getMaxGrade(String userName) throws Exception {
		/**
		 * 得到最高分
		 */
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		int maxGrade = 0;
		File file = new File("D:\\2048GameUser.xls");
		InputStream stream = new FileInputStream(file);
		POIFSFileSystem poifsFileSystem = new POIFSFileSystem(stream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();
		for(int i=rowstart+1; i <= rowEnd; i++) {
			HSSFRow row = hssfSheet.getRow(i);
			if(null == row) continue;
			HSSFCell cell = row.getCell(0);
			String s = cell.getStringCellValue();
			if(s.equals(userName)) {
				int cellEnd = row.getLastCellNum();
				for(int k = 2; k <= cellEnd; k++) {
					HSSFCell grade_cell = row.getCell(k);
					if(null == grade_cell) continue;
					if(grade_cell.getCellType() == (int)HSSFCell.CELL_TYPE_NUMERIC) {
						int a = (int)grade_cell.getNumericCellValue();
						list.add(a);
					}
				}
			} else {
				continue;
			}
		}
		maxGrade = Collections.max(list);
		return maxGrade;
	}
}
