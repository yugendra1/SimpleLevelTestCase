package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.AccessBean;
import com.training.bean.LoginBean;
import com.training.dao.AccessDAO;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ApachePOIExcelReadAccess;
import com.training.readexcel.ApachePOIExcelReadAdv;
import com.training.readexcel.ReadExcel;

public class AccessDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		return result;
	}
	
	@DataProvider(name = "access-inputs")
	public Object [][] getAccessDBData() {

		List<AccessBean> list = new AccessDAO().getAccess(); 
			
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(AccessBean temp : list){
			Object[]  obj = new Object[3]; 
			obj[0] = temp.getMemberName(); 
			obj[1] = temp.getAccessLevel(); 
			obj[2] = temp.getCommentsText(); 
			
			result[count ++] = obj; 
		}
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:/Yugi/Access_Excel.xlsx"; 
		return new ApachePOIExcelReadAccess().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "adv-inputs")
	public Object[][] getExcelAdvData(){
		String fileName ="C:/Yugi/Advertisement_Excel.xlsx"; 
		return new ApachePOIExcelReadAdv().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
