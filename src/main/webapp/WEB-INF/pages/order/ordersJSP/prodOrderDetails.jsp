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
		height: 500px;
		border: 1px solid black;
		background-color: #fdf5e6;
		margin: 0 auto;
	}
</style>
</head>
<body>
<div align="center" class="box">
	<h2>周邊商品訂單明細${button}成功</h2>
	<table>
		<tr><td>周邊商品訂單明細編號<td><input type="text" disabled value= "${prodOrderDetailsBeanNew.getProdOrderDetailID()}">
		<tr><td>周邊商品訂單編號<td><input type="text" disabled value= "${prodOrderDetailsBeanNew.getProdOrderID()}">
		<tr><td>周邊商品編號<td><input type="text" disabled value= "${prodOrderDetailsBeanNew.getProductID()}">
		<tr><td>單價<td><input type="text" disabled value="${prodOrderDetailsBeanNew.getPrice()}">
		<tr><td>數量<td><input type="text" disabled value="${prodOrderDetailsBeanNew.getQuantity()}">
		<tr><td>評論內容<td><input type="text" disabled value="${prodOrderDetailsBeanNew.getContent()}">
		<tr><td>評論時間<td><input type="text" disabled value="${prodOrderDetailsBeanNew.getReviewTime()}">
		<tr><td>評分<td><input type="text" disabled value="${prodOrderDetailsBeanNew.getScore()}">
	</table>
</div>
</html>