<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,order.bean.ProdOrderDetailsBean, product.bean.*"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>購物車</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .cart_info {
            margin-top: 20px;
            text-align: right;
        }
        .cart_info span {
            margin-right: 20px;
        }
        .button-orange {
            text-decoration: none;
            color: #fff;
            background-color: #ff9800;
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            font-size: 16px; /* 設置字體大小 */
        }
        .button-orange:hover {
            background-color: #e68900;
        }
        form {
            display: inline;
        }
        /* 設置數量框的寬度 */
        input[type="number"] {
            width: 60px; /* 可以根據需要調整這個值 */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>購物車</h1>
    <%List<ProdOrderDetailsBean> listNew = new ArrayList<>(); %> <!-- 子鈞需要的資料 -->
    <table>
        <tr>
            <th>商品名稱</th>
            <th>單價</th>
            <th>數量</th>
            <th>金額</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${sessionScope.cart.items}" var="item">
            <tr>
                <td>${item.value.name}</td>
                <td>NT$${item.value.price}</td>
                <td>
                    <form action="../CartServlet" method="post">
                        <input type="hidden" name="action" value="updateCount">
                        <input type="hidden" name="id" value="${item.value.id}">
                        <input name="count" type="number" min="1" value="${item.value.count}" onchange="this.form.submit()">
                    </form>
                </td>
                <td>NT$${item.value.totalPrice}</td>
                <td>
                    <form action="../CartServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="deleteItem">
                        <input type="hidden" name="id" value="${item.value.id}">
                        <!-- 修改刪除按鈕的樣式 -->
                        <button type="submit" class="button-orange">刪除</button>
                    </form>
                </td>
            </tr>
            <%Map.Entry<Integer, CartItem> map = (Map.Entry<Integer, CartItem>)pageContext.findAttribute("item"); %>
            <%CartItem cartItem = map.getValue(); %> 
            <%listNew.add(new ProdOrderDetailsBean(cartItem.getId(),cartItem.getPrice(),cartItem.getCount())); %> 
        </c:forEach>
    </table>

    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <!-- 繼續購物按鈕 -->
            <a class="button-orange" href="<%= request.getContextPath() %>/product/IndexProducts.jsp">繼續購物</a>
            <span>購物車中共有 ${sessionScope.cart.totalCount} 件商品</span>
            <span>總金額 NT$${sessionScope.cart.totalPrice}</span>
            <form action="../CartServlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="clear">
                <!-- 修改清空購物車按鈕的樣式 -->
                <button type="submit" class="button-orange">清空購物車</button>
            </form>
            <!-- 訂單送出按鈕 -->
            <form action="../OrderController" method="post" style="display:inline;">
                <input type="hidden" name="order" value="products">
                <!-- 送出總金額 -->
                <input type="hidden" name="totalAmount" value="${sessionScope.cart.totalPrice}"></input>
                <!-- 送出會員編號 -->
                <input type="hidden" name="memberID" value="5"></input>
                <!-- 抓取order所需資料 -->
                <%session.setAttribute("listNew", listNew); %>
                <button type="submit" class="button-orange">訂單送出</button>
    		</form>
        </div>
    </c:if>
		
    <c:if test="${empty sessionScope.cart.items}">
        <h1 align="center">目前購物車是空的！</h1>
        <div class="cart_info">
            <span><a href="<%= request.getContextPath() %>/product/IndexProducts.jsp" class="button-orange">去購物</a></span>
        </div>
    </c:if>
</div>
</body>
</html>
