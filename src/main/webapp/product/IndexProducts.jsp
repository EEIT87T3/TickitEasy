<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="product.dao.ProductDao" %>
<%@ page import="product.bean.Products" %>
<%@ page import="product.bean.Cart" %>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>周邊商品</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #f4f4f4;
        }
        .header .wel_word {
            font-size: 1.5em;
        }
        .header .cart-link {
            text-decoration: none;
            color: #000;
            font-size: 1em;
            background-color: #ff9800;
            padding: 10px;
            border-radius: 5px;
        }
        .container {
            width: 90%;
            margin: 0 auto;
            padding-top: 20px;
        }
        .header-bar {
            display: flex;
            align-items: center;
            justify-content: center; 
            margin-bottom: 20px;
        }
        .header-bar h1 {
            margin: 0;
            flex: 0 1 auto; 
        }
        .search-bar {
            display: flex;
            margin-left: auto;
            margin-right: auto; 
        }
        .search-bar form {
            display: flex;
        }
        .search-bar input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ddd;
            border-right: none;
            border-radius: 5px 0 0 5px;
            outline: none;
        }
        .search-bar button {
            padding: 10px;
            background-color: #ff9800;
            color: #fff;
            border: 1px solid #ff9800;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            outline: none;
        }
        .search-bar button:hover {
            background-color: #e68900;
        }
        .product-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }
        .product {
            flex: 0 0 calc(25% - 20px);
            max-width: calc(25% - 20px);
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 15px;
            text-align: center;
            background-color: #fff;
        }
        .product img {
            width: 100%;
            height: auto;
        }
        .product h3 {
            font-size: 1.2em;
            margin: 10px 0;
        }
        .product p {
            font-size: 0.9em;
            color: #666;
        }
        .product .price, .product .stock {
            font-weight: bold;
            margin: 5px 0;
        }
        .product button {
            background-color: #ff9800;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 1em;
            margin-top: 10px;
        }
        .product button:hover {
            background-color: #e68900;
        }
        a:link {
            text-decoration: none;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<div class="header">
    <span class="wel_word"><i class="fa-solid fa-ticket"></i> TickitEasy-活動售票管理平台</span>
    <a href="<%= request.getContextPath() %>/product/Cart.jsp" class="cart-link">
        <i class="fa-solid fa-cart-shopping"></i>購物車
        <span>
            <%
                if (session.getAttribute("cart") != null) {
                    out.print("(" + ((Cart)session.getAttribute("cart")).getTotalCount() + ")");
                } else {
                    out.print("(0)");
                }
            %>
        </span>
    </a>
</div>

<div class="container">
    <!-- 標題和搜索框 -->
    <div class="header-bar">
        <h1>周邊商品</h1>
        <div class="search-bar">
            <form action="" method="get">
                <input type="text" name="search" placeholder="請輸入商品名稱">
                <button type="submit">搜尋</button>
            </form>
        </div>
    </div>

    <div class="product-grid">
        <%
            ProductDao productDao = new ProductDao();
            List<Products> productList = null;

            String searchQuery = request.getParameter("search");
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                // 使用搜索的商品名稱進行查詢
                productList = productDao.findProductsByName(searchQuery);
            } else {
                // 沒有搜索，顯示所有商品
                productList = productDao.findAll();
            }

            if (productList != null) {
                for (Products product : productList) {
        %>
                    <div class="product">
                        <img src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" />
                        <h3><%= product.getProductName() %></h3>
                        <p><%= product.getProductDesc() %></p>
                        <div class="price">價格：NT$<%= product.getPrice() %></div>
                        <div class="stock">庫存：<%= product.getStock() %>件</div>
                        <form action="<%= request.getContextPath() %>/CartServlet" method="post">
                            <input type="hidden" name="action" value="addItem">
                            <input type="hidden" name="id" value="<%= product.getProductID() %>">
                            <button type="submit" class="addToCart">加入購物車</button>
                        </form>
                    </div>
        <%
                }
            } else {
        %>
                <p>沒有找到相關商品。</p>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
