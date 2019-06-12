package serviceimpl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Employee;
import bean.User;
import dao.EmployeeDao;
import dao.UserDao;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import service.EmployeeService;
import service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {
	
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	UserDao userDao;
	@Autowired
	EmployeeService employeeService;

	@Override
	public String AddEmployeeUserByExcel(String path) {
		
//		 File xlsFile = new File(path);
//	      Workbook workbook = null;
//		try {
//			workbook = Workbook.getWorkbook(xlsFile);
//		} catch (BiffException | IOException e) {
//			e.printStackTrace();
//		}
//	      Sheet[] sheets = workbook.getSheets();
//	      if (sheets != null){
//	         for (Sheet sheet : sheets){
//	            int rows = sheet.getRows();
//	            for (int row = 0; row < rows; row++){
//	            	Employee employee = new Employee();
//	            	
//	            	sheet.getCell(0, row).getContents();
//	            	sheet.getCell(1, row).getContents();
//	            	sheet.getCell(2, row).getContents();
//	            	sheet.getCell(3, row).getContents();
//	            	sheet.getCell(4, row).getContents();
//	            	sheet.getCell(5, row).getContents();
//	            	sheet.getCell(6, row).getContents();	            	   	            	   	            	 	            	   	            	   	              
//	              
//	               employeeService.addEmployee(employee);	               	   
//	            }
//	         }
//	      }
//	      workbook.close();
		return null;
	   }

	}

