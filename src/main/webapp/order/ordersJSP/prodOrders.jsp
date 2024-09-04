<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增成功</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 250px;
            background-color: #fff5f5;
            padding: 20px;
        }
        .sidebar h2 {
            color: #333;
            font-size: 18px;
            margin-bottom: 20px;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }
        .sidebar ul li {
            margin-bottom: 15px;
        }
        .sidebar ul li a {
            text-decoration: none;
            color: #333;
            display: flex;
            align-items: center;
        }
        .sidebar ul li a:hover {
            color: #ff5e57;
        }
        .main-content {
            flex-grow: 1;
            background-color: #ffffff;
            padding: 20px;
        }
        .main-content h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .order-container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }
        .order-container p {
            font-size: 16px;
            color: #888;
        }
        .order-container button {
            background-color: #ff5e57;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        .order-container button:hover {
            background-color: #e05050;
        }
    </style>
</head>
<body>
<div class="container">
        <div class="sidebar">
            <h2>嗨！陳予鈞</h2>
            <ul>
                
                <li><a href="http://localhost:8080/Project2Combine/order/ordersHTML/prodOrders.html">訂單紀錄</a></li>
                <li><a href="#">票匣</a></li>
                <li><a href="#">點數</a></li>
                <li><a href="#">優惠券</a></li>
                <li><a href="#">訊息通知與公告</a></li>
                <li><a href="#">收藏 / 訂閱</a></li>
            </ul>
            <h2>帳號設定</h2>
            <ul>
                <li><a href="#">電子發票</a></li>
                <li><a href="#">會員綁定</a></li>
            </ul>
        </div>
        <div class="main-content">
            <h1>訂單記錄</h1>
            <div align="center" class="order-container">


                <table>
                    <tr><td>周邊商品訂單編號<td><input type="text" disabled value= "${prodOrderBeanNew.getProdOrderID()}">
                    <tr><td>會員編號<td><input type="text" disabled value= "${prodOrderBeanNew.getMemberID()}">
                    <tr><td>訂單日期<td><input type="text" disabled value= "${prodOrderBeanNew.getOrderDate()}">
                    <tr><td>付款方式<td><input type="text" disabled value="${prodOrderBeanNew.getPayments()}">
                    <tr><td>付款資訊<td><input type="text" disabled value="${prodOrderBeanNew.getPaymenInfo()}">
                    <tr><td>訂單狀態<td><input type="text" disabled value="${prodOrderBeanNew.getStatus()}">
                    <tr><td>總金額<td><input type="text" disabled value="${prodOrderBeanNew.getTotalAmount()}">
                    <tr><td>配送狀態<td><input type="text" disabled value="${prodOrderBeanNew.getShippingStatus()}">
                    <tr><td>配送編號<td><input type="text" disabled value="${prodOrderBeanNew.getShippingID()}">
                    <tr><td>收件人姓名<td><input type="text" disabled value="${prodOrderBeanNew.getRecipientName()}">
                    <tr><td>收件地址<td><input type="text" disabled value="${prodOrderBeanNew.getAddress()}">
                    <tr><td>電話<td><input type="text" disabled value="${prodOrderBeanNew.getPhone()}">
                </table>
            </div>
        </div>
    </div>
</html>