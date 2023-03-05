package com.hasim.interview.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hasim.interview.entity.SalesMaster;

public class SaleDAO {
	
	public int insertSales(SalesMaster salesMaster) throws ClassNotFoundException {
		
		String query1="select quantity from stock_master where item_code="+salesMaster.getItem_code();
		String query2="insert into sale_master (customer_name,gender_id,item_code,quantity_sold) values (?,?,?,?);";
		String query3="";
		
		
		int result=0;
		int quantity=0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock","root","Hsm10025@");
			
			PreparedStatement preparedStatement1=null;
			PreparedStatement preparedStatement2=null;
			PreparedStatement preparedStatement3=null;
			
			preparedStatement1=connection.prepareStatement(query1);
			ResultSet resultSet=preparedStatement1.executeQuery();
			if(resultSet.next()) {
				quantity=resultSet.getInt(1);
			}
			
			if(quantity>salesMaster.getQuantity_sold()) {
				preparedStatement2=connection.prepareStatement(query2);
				preparedStatement2.setString(1, salesMaster.getCustomer_name());
				preparedStatement2.setInt(2, salesMaster.getGender_id());
				preparedStatement2.setInt(3, salesMaster.getItem_code());
				preparedStatement2.setInt(4, salesMaster.getQuantity_sold());
				
				result=preparedStatement2.executeUpdate();
				
				query3="update stock_master set quantity="+(quantity-salesMaster.getQuantity_sold());
				preparedStatement3=connection.prepareStatement(query3);
				preparedStatement3.executeUpdate();
			}
			else {
				System.out.println("Can not sold the item");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
