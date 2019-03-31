package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.AccessBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

// Data Access Object 
public class AccessDAO {
	
	Properties properties; 
	
	public AccessDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<AccessBean> getAccess(){
		String sql = properties.getProperty("get.access"); 
		
		GetConnection gc  = new GetConnection(); 
		List<AccessBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<AccessBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				AccessBean temp = new AccessBean(); 
				temp.setMemberName(gc.rs1.getString(1));
				temp.setAccessLevel(gc.rs1.getString(2));
				temp.setCommentsText(gc.rs1.getString(3)); 

				list.add(temp);  
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new AccessDAO().getAccess().forEach(System.out :: println);
	}
	
	
}
