package com.hasim.interview.web;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hasim.interview.dao.SaleDAO;
import com.hasim.interview.entity.SalesMaster;

@WebServlet("/sale")
public class SaleServlet {
	
	private SaleDAO saleDAO;
	
	public void init() {
		saleDAO=new SaleDAO();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException {
		String customer_name=request.getParameter("customer_name");
		int gender_id=Integer.parseInt(request.getParameter("gender_id"));
		int item_code=Integer.parseInt(request.getParameter("item_code"));
		int quantity_sold=Integer.parseInt(request.getParameter("quantity_sold"));
		
		SalesMaster salesMaster=new SalesMaster();
		
		salesMaster.setCustomer_name(customer_name);
		salesMaster.setGender_id(gender_id);
		salesMaster.setItem_code(item_code);
		salesMaster.setQuantity_sold(quantity_sold);
		
		try {
			saleDAO.insertSales(salesMaster);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
