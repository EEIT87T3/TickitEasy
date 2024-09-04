<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="product.bean.Products" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新商品資料</title>
    <style>
        /* 樣式用於展示預覽圖片 */
        #preview {
            margin-top: 10px;
            max-width: 200px;
        }
    </style>
    <script>
        function previewImage() {
            var fileInput = document.getElementById('productPicFile');
            var preview = document.getElementById('preview');
            var file = fileInput.files[0];
            var reader = new FileReader();
            
            // 當文件讀取完畢後
            reader.onload = function (e) {
                preview.src = e.target.result;
                preview.style.display = 'block'; // 顯示圖片預覽
            };
            
            // 讀取文件
            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = '';
                preview.style.display = 'none'; // 隱藏圖片預覽
            }
        }
    </script>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
    <h2>更新商品資料</h2>

    <jsp:useBean id="product" scope="request" class="product.bean.Products" />
    <form method="post" action="<%= request.getContextPath() %>/UpdateProduct" enctype="multipart/form-data">
        <table>
            <tr><td>商品編號</td><td><input type="text" name="productID" readonly value="<%= product.getProductID() %>"></td></tr>
            <tr><td>分類</td><td><input type="text" name="category" value="<%= product.getCategory() %>"></td></tr>
            <tr><td>商品名稱</td><td><input type="text" name="productName" value="<%= product.getProductName() %>"></td></tr>
            <tr><td>商品圖片</td>
                <td>
                   <img id="preview" src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" width="200" style="display:block;" /><br/>
                    <input type="file" name="productPicFile" id="productPicFile" accept="image/*" onchange="previewImage()">
                </td>
            </tr>
            <tr><td>商品描述</td><td><input type="text" name="productDesc" value="<%= product.getProductDesc() %>"></td></tr>
            <tr><td>價格</td><td><input type="number" name="price" value="<%= product.getPrice() %>"></td></tr>
            <tr><td>庫存</td><td><input type="number" name="stock" value="<%= product.getStock() %>"></td></tr>
            <tr><td>狀態</td><td><input type="text" name="status" value="<%= product.getStatus() %>"></td></tr>
            <tr><td>評論總數</td><td><input type="number" name="prodTotalReviews" value="<%= product.getProdTotalReviews() %>"></td></tr>
            <tr><td>總評分</td><td><input type="number" name="prodTotalScore" value="<%= product.getProdTotalScore() %>"></td></tr>
        </table>
        <input type="hidden" name="confirmUpdate" value="true">
        <br>
        <input type="submit" value="確認更新並返回所有商品列表" />
    </form>
</div>
</body>
</html>
