<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.bean.Products" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除商品資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>刪除商品資料</h2>

<jsp:useBean id="product" scope="request" class="product.bean.Products" />
<table>
<tr><td>商品編號<td><input type="text" disabled value="<%= product.getProductID() %> ">
<tr><td>分類<td><input type="text" disabled value="<%= product.getCategory() %>">
<tr><td>商品名稱<td><input type="text" disabled value="<%= product.getProductName() %>">
<tr><td>商品圖片<td><img src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" width="200" />
<tr><td>商品描述<td><input type="text" disabled value="<%= product.getProductDesc() %>">
<tr><td>價格<td><input type="text" disabled value="<%= product.getPrice() %>">
<tr><td>庫存<td><input type="text" disabled value="<%= product.getStock() %>">
<tr><td>狀態<td><input type="text" disabled value="<%= product.getStatus() %>">
<tr><td>評論總數<td><input type="text" disabled value="<%= product.getProdTotalReviews() %>">
<tr><td>總評分<td><input type="text" disabled value="<%= product.getProdTotalScore() %>">
</table>

<form method="post" action="<%= request.getContextPath() %>/DeleteProduct">
    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
    <input type="hidden" name="confirmDelete" value="true">
    <input type="submit" value="確認刪除並返回所有商品列表" />
</form>

</div>
</body>
</html>
