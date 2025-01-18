package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static void write(List<List<String>> TwoDList, String sheetName, String browserType) throws IOException {

		String path = "C:\\Users\\2373656\\Desktop\\Testing\\ZigwheelsProject (1)\\ZigwheelsProject\\src\\test\\java\\files\\"
				+ browserType + "_Data.xlsx";
		
		File file = new File(path);
		
		boolean doesexist = file.exists();

		if (doesexist) {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			populateExcel(workbook, sheetName, TwoDList);

			FileOutputStream fos = new FileOutputStream(path);

			workbook.write(fos);
			workbook.close();
			fos.close();

		} else {
			FileOutputStream fos = new FileOutputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook();

			populateExcel(workbook, sheetName, TwoDList);
			workbook.write(fos);

			workbook.close();
			fos.close();
		}

	}

	static void populateExcel(XSSFWorkbook workbook, String sheetName, List<List<String>> TwoDList) {

		XSSFSheet sheet = workbook.createSheet(sheetName);
		
		for (int i = 0; i < TwoDList.size(); i++) {
			XSSFRow row = sheet.createRow(i);
			List<String> rowData = TwoDList.get(i);
			for (int j = 0; j < rowData.size(); j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(rowData.get(j));
			}
		}
	}

}
