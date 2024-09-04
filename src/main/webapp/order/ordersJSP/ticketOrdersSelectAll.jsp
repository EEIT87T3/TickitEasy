<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, order.bean.TicketOrdersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券活動訂單全部資料</title>
<style>
	.box{
		border: 2px solid black;
		width: 1200px;
		margin: 0 auto;
	}
</style>
</head>
<body style='background-color:#fdf5e6'>
<div align="center" class = "box">
<h2>票券活動訂單全部資料</h2>
<table>
<tr style="background-color:#a8fefa">
<th>活動票券訂單編號<th>會員編號<th>訂單日期<th>付款方式<th>訂單狀態<th>總金額
<% List<TicketOrdersBean> ticketOrdersBeans = (ArrayList<TicketOrdersBean>)request.getAttribute("list"); 
for(TicketOrdersBean ticketOrdersBean : ticketOrdersBeans){ %>
<tr align="center">
<td><%= ticketOrdersBean.getTickedOrderID() %></td>
<td><%= ticketOrdersBean.getMemberID() %></td>
<td><%= ticketOrdersBean.getOrderDate() %></td>
<td><%= ticketOrdersBean.getPayments() %></td>
<td><%= ticketOrdersBean.getStatus() %></td>
<td><%= ticketOrdersBean.getTotalAmount() %></td>
</tr>
<% } %>





</table>

</div>
</body>
</html>