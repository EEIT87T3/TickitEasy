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
	<h2>票券活動訂單明細${button}成功</h2>
	<table>
		<tr><td>活動票券訂單明細編號<td><input type="text" disabled value= "${ticketOrderDetailsBeanNew.getTicketOrderDetailID()}">
		<tr><td>活動票券訂單編號<td><input type="text" disabled value= "${ticketOrderDetailsBeanNew.getTicketOrderID()}">
		<tr><td>票種編號<td><input type="text" disabled value= "${ticketOrderDetailsBeanNew.getTicketTypeID()}">
		<tr><td>票券取票方式<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getTicketCollectionMethod()}">
		<tr><td>單價<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getPrice()}">
		<tr><td>票券 UUID<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getTicketUUID()}">
		<tr><td>票券狀態<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getTicketStatus()}">
		<tr><td>評論內容<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getContent()}">
		<tr><td>評論時間<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getReviewTime()}">
		<tr><td>評分<td><input type="text" disabled value="${ticketOrderDetailsBeanNew.getScore()}">
	</table>
</div>
</html>