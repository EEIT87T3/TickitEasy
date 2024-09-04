<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, order.bean.TicketOrderDetailsBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>票券活動訂單明細全部資料</title>
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
<h2>票券活動訂單明細全部資料</h2>
<table>
<tr style="background-color:#a8fefa">
<th>活動票券訂單明細編號<th>活動票券訂單編號<th>票種編號<th>票券取票方式<th>單價<th>票券 UUID<th>票券狀態<th>評論內容<th>評論時間<th>評分
<% List<TicketOrderDetailsBean> ticketOrderDetailsBeans = (ArrayList<TicketOrderDetailsBean>)request.getAttribute("list"); 
for(TicketOrderDetailsBean ticketOrderDetailsBean : ticketOrderDetailsBeans){ %>
<tr align="center">
<td><%= ticketOrderDetailsBean.getTicketOrderDetailID() %></td>
<td><%= ticketOrderDetailsBean.getTicketOrderID() %></td>
<td><%= ticketOrderDetailsBean.getTicketTypeID() %></td>
<td><%= ticketOrderDetailsBean.getTicketCollectionMethod() %></td>
<td><%= ticketOrderDetailsBean.getPrice() %></td>
<td><%= ticketOrderDetailsBean.getTicketUUID() %></td>
<td><%= ticketOrderDetailsBean.getTicketStatus() %></td>
<td><%= ticketOrderDetailsBean.getContent() %></td>
<td><%= ticketOrderDetailsBean.getReviewTime() %></td>
<td><%= ticketOrderDetailsBean.getScore() %></td>
</tr>
<% } %>
</table>

</div>
</body>
</html>