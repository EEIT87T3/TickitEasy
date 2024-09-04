<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="product.bean.Products" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>商品資料</h2>

<jsp:useBean id="products" scope="request" type="java.util.List" />

<% 
    List<Products> productsList = (List<Products>) request.getAttribute("products");
    if (productsList != null && !productsList.isEmpty()) { 
%>
    <table border="1">
        <% for (Products product : productsList) { %>
            <tr>
                <td>商品編號</td><td><input type="text" disabled value="<%= product.getProductID() %>" /></td>
            </tr>
            <tr>
                <td>分類</td><td><input type="text" disabled value="<%= product.getCategory() %>" /></td>
            </tr>
            <tr>
                <td>商品名稱</td><td><input type="text" disabled value="<%= product.getProductName() %>" /></td>
            </tr>
            <tr>
                <td>商品圖片</td><td><img src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" width="200" /></td>
            </tr>
            <tr>
                <td>商品描述</td><td><input type="text" disabled value="<%= product.getProductDesc() %>" /></td>
            </tr>
            <tr>
                <td>價格</td><td><input type="text" disabled value="<%= product.getPrice() %>" /></td>
            </tr>
            <tr>
                <td>庫存</td><td><input type="text" disabled value="<%= product.getStock() %>" /></td>
            </tr>
            <tr>
                <td>狀態</td><td><input type="text" disabled value="<%= product.getStatus() %>" /></td>
            </tr>
            <tr>
                <td>評論總數</td><td><input type="text" disabled value="<%= product.getProdTotalReviews() %>" /></td>
            </tr>
            <tr>
                <td>總評分</td><td><input type="text" disabled value="<%= product.getProdTotalScore() %>" /></td>
            </tr>
            
        <% } %>
    </table>
    <!-- 返回按鈕，跳轉到 GetAllProducts 頁面 -->
    <form method="post" action="<%= request.getContextPath() %>/product/GetAllProducts.jsp" style="display:inline;">
        <input type="submit" value="返回商品列表">
    </form>
<% 
    } else { 
%>
    <p>找不到符合條件的商品。</p>
    <form method="post" action="<%= request.getContextPath() %>/product/GetAllProducts.jsp" style="display:inline;">
        <input type="submit" value="返回商品列表">
    </form>
<% 
    } 
%>

</div>
</body>
</html>
