package api.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class XlsxReader {

	public String path;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public Logger logger = LogManager.getLogger(this.getClass());
	public FileInputStream fi;
	public DataFormatter data = new DataFormatter();

	XlsxReader(String path) {
		this.path = path;
	}

	public int getRowCount(String SheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(SheetName);
		int RowCount = sheet.getLastRowNum();

		workbook.close();
		fi.close();

		return RowCount;

	}

	public int getCellCount(String SheetName, int RowNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(SheetName);
		row = sheet.getRow(RowNum);
		int CellCount = row.getLastCellNum();

		workbook.close();
		fi.close();

		return CellCount;

	}

	public String getData(String SheetName, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(SheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		String cellData = data.formatCellValue(cell);
		return cellData;
	}

	
	public static void readData() throws IOException {

		XlsxReader ref = new XlsxReader("testData/testData.xlsx");

		int rowCount = ref.getRowCount("sheet1");
		int cellCount = ref.getCellCount("sheet1", 0);

		String data2 = ref.getData("sheet1", rowCount - 1, cellCount - 1);
		System.out.println(data2);
	}

	@DataProvider(name = "testData")
	public String[][] dataprovider() throws IOException {
		XlsxReader ref = new XlsxReader("testData/testData.xlsx");

		int rowCount = ref.getRowCount("sheet1");
		int cellCount = ref.getCellCount("sheet1", 0);
		String appData[][] = new String[rowCount][cellCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {

				appData[i - 1][j] = ref.getData("sheet1", i, j);
			}
		}
		return appData;
	}
	
	@Test(dataProvider = "testData", enabled = false)
	public void getdata(String firstName,String LastName) {
		
		System.out.println(firstName+" "+LastName);

	}
	
	@Test(priority = 1)
	public void readPropertyFile() throws IOException {
		logger.info("-----------------create  user-----------------------");
		
		
		
	ResourceBundle resourceBundle =  ResourceBundle.getBundle("routes");
	String string1 = resourceBundle.getString("base_url");
	
	System.out.println(string1);
	
	
	FileInputStream fi = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\PetAPI\\src\\test\\resource\\routes.properties");
	
	Properties pro = new Properties();
	
	pro.load(fi);
	
	String propertyData = pro.getProperty("base_url");
	
	System.out.println(propertyData);
	logger.info("-----------------create  is-----user-----------------------");
		
	}
}
