<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%ResultSet resultSet1=null; %>
    <%ResultSet resultSet2=null; %>
<!DOCTYPE html>
<html>
<head>
<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/stock","root","Hsm10025@");
		String query1="select gender_id from gender_master";
		String query2="select item_code from stock_master";
		
		PreparedStatement preparedStatement1=connection.prepareStatement(query1);
		PreparedStatement preparedStatement2=connection.prepareStatement(query2);
		
		resultSet1=preparedStatement1.executeQuery();
		resultSet2=preparedStatement2.executeQuery();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Sale Market Form</h1>
		<form action="<%=request.getContextPath()%>/sale" method="post">
			<table style="with: 80%">
				<tr>
					<td>Customer Name</td>
					<td><input type="text" name="customer_name"></td>
				</tr>
				<tr>
					<td>Gender Id</td>
					<td><select name="gender_id">
							<%while(resultSet2.next()){%>
							<option><%resultSet1.getInt(1);%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr>
					<td>Item Code</td>
					<td><select name="item_code">
							<%while(resultSet2.next()){%>
							<option><%resultSet1.getInt(1);%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr>
					<td>Quantity Sold</td>
					<td><input type="text" name="quantity_sold"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>