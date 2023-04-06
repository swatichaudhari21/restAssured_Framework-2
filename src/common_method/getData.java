package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData {
	  public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException
      {
   	  ArrayList<String>arrayData = new ArrayList<String>();
   	  // step 1 open the excel data file, by creating the object of fileinputstream
   	  FileInputStream fis = new FileInputStream("C:\\Users\\pc1\\eclipse-workspace\\restAssured_Framework\\test_data.xlsx");
   	  
   	  // step 2 create the object of XSSFWorkbook to open the excel file
   	  XSSFWorkbook workbook = new XSSFWorkbook(fis);
   	  
   	  //step 3 access the desired sheet
   	  //step 3.1 fetch the count of sheet available in the excel file
   	  int countofSheet = workbook.getNumberOfSheets();
   	  
   	  //step 3.2 fetch the name of sheet and compare against desired sheet name
   	  for (int i=0; i<countofSheet; i++) 
   	  {
   		  String sheetname = workbook.getSheetName(i);
   		  //System.out.println(sheetname);
   		  if (sheetname.equalsIgnoreCase(testSheetName))
   		  {
   			  //System.out.println(testSheetName);
   			  //step 4 access the sheet and iterate through rows to fetch the column in which test case name
   			  XSSFSheet Sheet = workbook.getSheetAt(i); // here we creating the variable not object
   			  //step 4.1 create iterator for rows 
   			  Iterator<Row> Rows = Sheet.iterator();
   			  Row firstRow = Rows.next();   //this will give you the 1st row A1
   			  //step 4.2 create iterator for cells
   			  Iterator<Cell> Cells = firstRow.cellIterator();
   			  int j=0;
   			  int tc_column=0;
   			  
   			  //step 4.3 Read the cell values of row no1 to compare against the test case name
   			  while(Cells.hasNext())
   			  {
   				  Cell cellvalue = Cells.next();
   				  //System.out.println(cellvalue);
   				  if (cellvalue.getStringCellValue().equalsIgnoreCase("Tc_name"))
   				  {
   					  tc_column= j;
   					  //System.out.println(tc_column);
   				  }
   				  j++;
   			  }
   			  //step 5 fetch the data for designated test case
   			  while(Rows.hasNext())
   			  {
   				  Row dataRow = Rows.next();
   				  if(dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
   				  {
   					  Iterator<Cell> dataCellvalue = dataRow.cellIterator();
   					 
   					  while(dataCellvalue.hasNext())
   					  {
   						  Cell dataofCell = dataCellvalue.next();
   						  //method 1 ....to extract cell value by using try and catch
   			              try
   						  {
   							  String testdata = dataofCell.getStringCellValue();
       						 // System.out.println(testdata);
   							  arrayData.add(testdata);
       				      }
   						  catch (IllegalStateException e)
   						  {
   							// System.out.println(e);
   							  int inttestData = (int) dataofCell.getNumericCellValue();
   							 // System.out.println(inttestData);
   							  String stringtestData = Integer.toString(inttestData);
   							  arrayData.add(stringtestData);
   						  }
   			              //method 2 ..... to extract the cell value by using if and else
   						 /* CellType datatype = dataofCell.getCellType();
   						  
   						  if (datatype.toString() == "NUMERIC")
   						  {
   							  int inttestData = (int) dataofCell.getNumericCellValue();
   							  String stringtestData = Integer.toString(inttestData);
   							  arrayData.add(stringtestData);
   		    				  System.out.println(inttestData);
   						  }
   						  else if (datatype.toString() == "STRING")
   						  {
   							  String testData = dataofCell.getStringCellValue();
   							  arrayData.add(testData);
   							  System.out.println(testData);
   						  }
   			              // method 3 ....extract the data by converting it into string
   			              String testData = dataCellvalue.next().toString().replaceAll("\\.\\d+$","");
   			              System.out.println(testData);
   			              
   			              // method 4 ....extract the data by using Dataformatter
   			              DataFormatter format = new DataFormatter();
   			              String testData1 = format.formatCellValue(dataCellvalue.next());
   			              System.out.println(testData1);*/
   					  }
   				  }
   			  }
   		  }
   	  }
   	  return arrayData;
   	  
    }

}
