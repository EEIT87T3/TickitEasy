<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="product.bean.Products" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增商品</title>
    <style>
        #imagePreview {
            max-width: 200px;
            margin-top: 10px;
        }
    </style>
    <script>
        function previewImage(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('imagePreview').src = e.target.result;
                    document.getElementById('imagePreview').style.display = 'block';
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        function fillDemoData() {
        	document.getElementById('category').value = '動漫';
            document.getElementById('name').value = '你的名字';
            document.getElementById('description').value = '你的名字電影周邊商品';
            document.getElementById('price').value = '500';
            document.getElementById('stock').value = '150';
            document.getElementById('status').value = '上架';
            document.getElementById('reviews').value = '0';
            document.getElementById('score').value = '0';
        }
    </script>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
    <h2>新增商品</h2>
    <jsp:useBean id="product" scope="request" class="product.bean.Products" />
        <form method="post" action="${pageContext.request.contextPath}/AddProduct/addProduct" enctype="multipart/form-data">    
    <!-- <form method="post" action="<%= request.getContextPath() %>/AddProduct" enctype="multipart/form-data"> 
        <form method="post" action="${pageContext.request.contextPath}/AddProduct/addProduct" enctype="multipart/form-data">    
        <table>
            <tr><td>分類：</td><td><input type="text" name="category" id="category"></td></tr>
            <tr><td>商品名稱：</td><td><input type="text" name="productName" id="productName"></td></tr>
            <tr>
                <td>商品圖片：</td>
                <td>
                    <input type="file" name="productPic" accept="image/*" onchange="previewImage(this)">
                    <img id="imagePreview" src="" alt="商品圖片預覽" style="display:none;">
                </td>
            </tr>
            <tr><td>商品描述：</td><td><input type="text" name="productDesc" id="productDesc"></td></tr>
            <tr><td>價格：</td><td><input type="number" name="price" id="price"></td></tr>
            <tr><td>庫存：</td><td><input type="number" name="stock" id="stock"></td></tr>
            <tr><td>狀態：</td><td><input type="text" name="status" id="status"></td></tr>
            <tr><td>評論總數：</td><td><input type="number" name="prodTotalReviews" id="prodTotalReviews"></td></tr>
            <tr><td>總評分：</td><td><input type="number" name="prodTotalScore" id="prodTotalScore"></td></tr>
        </table>-->
        <table>
            <!-- <tr><td>商品編號</td><td><input type="text" name="productID" readonly value="<%= product.getProductID() %>"></td></tr> -->
            <tr><td>分類</td><td><input type="text" name="category" id="category" value="<%= product.getCategory() %>"></td></tr>
            <tr><td>商品名稱</td><td><input type="text" name="productName" id="productName" value="<%= product.getProductName() %>"></td></tr>
           
            <tr><td>商品圖片</td>
               <!-- <td>
                   <img id="preview" src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" width="200" style="display:block;" /><br/>
                    <input type="file" name="productPicFile" id="productPicFile" accept="image/*" onchange="previewImage()">
                </td>
                <td>商品圖片：</td>
                <td>
                    <input type="file" name="productPic" accept="image/*" onchange="previewImage(this)">
                    <img id="imagePreview" src="" alt="商品圖片預覽" style="display:none;">
                </td>-->
                <td>
                <input type="file" name="productPic" id="imagePreview" accept="image/*" onchange="previewImage()">
                <img id="imagePreview" src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" width="200" style="display:block;" /><br/>
                <input type="hidden" name="oldproductPic" value="<%= product.getProductPic() %>">
            </td>
            </tr>
            <tr><td>商品描述</td><td><input type="text" name="productDesc" value="<%= product.getProductDesc() %>"></td></tr>
            <tr><td>價格</td><td><input type="number" name="price" value="<%= product.getPrice() %>"></td></tr>
            <tr><td>庫存</td><td><input type="number" name="stock" value="<%= product.getStock() %>"></td></tr>
            <tr><td>狀態</td><td><input type="text" name="status" value="<%= product.getStatus() %>"></td></tr>
            <tr><td>評論總數</td><td><input type="number" name="prodTotalReviews" value="<%= product.getProdTotalReviews() %>"></td></tr>
            <tr><td>總評分</td><td><input type="number" name="prodTotalScore" value="<%= product.getProdTotalScore() %>"></td></tr>
        </table>
        <br>
        <input type="submit" value="新增商品">
    </form>
    <br>
    <!--<button onclick="fillDemoData()">一鍵填入(DEMO用)</button>-->
    <br><br>
    <!-- 返回按鈕，跳轉到 GetAllProducts 頁面 
    <form method="post" action="<%= request.getContextPath() %>/product/GetAllProducts.jsp" style="display:inline;">
        <input type="submit" value="返回商品列表">
    </form>-->
</div>
</body>
</html>
