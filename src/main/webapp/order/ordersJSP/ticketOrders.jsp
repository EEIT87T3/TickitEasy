<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增成功</title>
<style>
	.box{
		width: 600px;
		height: 400px;
		border: 1px solid black;
		background-color: #fdf5e6;
		margin: 0 auto;
	}
</style>
</head>
<body>
<div align="center" class="box">
	<h2>票券活動訂單${button}成功</h2>
	<table>
		<tr><td>活動票券訂單編號<td><input type="text" disabled value= "${ticketOrdersBeanNew.getTickedOrderID()}">
		<tr><td>會員編號<td><input type="text" disabled value= "${ticketOrdersBeanNew.getMemberID()}">
		<tr><td>訂單日期<td><input type="text" disabled value= "${ticketOrdersBeanNew.getOrderDate()}">
		<tr><td>付款方式<td><input type="text" disabled value="${ticketOrdersBeanNew.getPayments()}">
		<tr><td>訂單狀態<td><input type="text" disabled value="${ticketOrdersBeanNew.getStatus()}">
		<tr><td>總金額<td><input type="text" disabled value="${ticketOrdersBeanNew.getTotalAmount()}">
	</table>
</div>
</html>