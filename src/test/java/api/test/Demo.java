package api.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Demo {

	
	public DataFormatter data;
    public XSSFCell cell;
    public FileInputStream fi;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public int lastRowNum;
    public int lastCellNum;

    @Test(priority = 1)
    public void readData() throws IOException {
        String path = "testData\\testData.xlsx";
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet("sheet1");
        row = sheet.getRow(0);
        lastRowNum = sheet.getLastRowNum();
        lastCellNum = row.getLastCellNum();
        cell = row.getCell(0);
        data = new DataFormatter();
    }
 
/* ---------------
1 | firstName | LastName
  |------------------
2 |  shakthi  | nakkeeran
3 |   vijay   |   varma
4 |   kumar   |  senthil
5 |  vadivel  |   muthu
6 |  kathir   |    sami
 */
    
    
    @Test(priority = 2, dataProvider ="data1")
    public void realTest(String firstName, String LastName) {
        System.out.println(firstName+"-------------"+LastName);
    }
    
    @Test(priority = 3, dataProvider ="userName",dataProviderClass = UserTest.class)
    public void realTest(String firstName) {
        System.out.println(firstName);
    }

    @DataProvider(name ="data1")
    public String[][] getData() {
        String appData[][] = new String[lastRowNum][lastCellNum];
        for (int i = 1; i <= lastRowNum; i++) {
            row = sheet.getRow(i);
         
            for (int j = 0; j < lastCellNum; j++) {
                cell = row.getCell(j);
              
                appData[i-1][j] = data.formatCellValue(cell);
                
            }
        }
        return appData;
    }
    

    @DataProvider(name ="userName")
    public String[] getUserData() {
        String appData[] = new String[lastRowNum];
        for (int i = 1; i <= lastRowNum; i++) {
            row = sheet.getRow(i);
         
            
                cell = row.getCell(0);
              
                appData[i-1]= data.formatCellValue(cell);
                
            
        }
        return appData;
    }
}



