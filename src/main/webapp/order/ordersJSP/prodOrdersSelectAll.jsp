<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,order.bean.ProdOrdersBean"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>訂單記錄</title>
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
            padding: 10px;
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
        table{
        	border-collapse: collapse;
        }
        td{
        	border: 1px solid black,
        }
    	.box{
    		background-color : #2e8b57,
    		width: 5px;
    	}
    </style>

</head>
<body>

    <div class="container">
        <div class="sidebar">
            <h2>嗨！陳予鈞</h2>
            <ul>
                <li><a href="order/ordersHTML/prodOrders.html">訂單記錄</a></li>
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
            
            <div class="order-container">

                <div align="center">
                    <h2>周邊商品訂單紀錄</h2>
                    <table>
                        <tr style="background-color: #fff5f5">
                            <th>周邊商品訂單編號
                            <th>會員編號
                            <th>訂單日期
                            <th>付款方式
                            <th>付款資訊
                            <th>訂單狀態
                            <th>總金額
                            <th>配送狀態
                            <th>配送編號
                            <th>收件人姓名
                            <th>收件地址
                            <th>電話
                            <th>訂單明細 
                            <%
                            List<ProdOrdersBean> prodOrderBeans = (ArrayList<ProdOrdersBean>) request.getAttribute("list");
                            for (ProdOrdersBean prodOrderBean : prodOrderBeans) {
                            %>
                        <tr align="center">
                        	 <form method = "post" action = "OrderController">
                        	 <input type = 'hidden' name = 'form' value = 'prodOrderDetails'>
                        	 <input type = 'hidden' name = 'prodOrderID' value = '<%= prodOrderBean.getProdOrderID() %>'>
                            <td><%=prodOrderBean.getProdOrderID()%></td>
                            <td><%=prodOrderBean.getMemberID()%></td>
                            <td><%=prodOrderBean.getOrderDate()%></td>
                            <td><%=prodOrderBean.getPayments()%></td>
                            <td><%=prodOrderBean.getPaymenInfo()%></td>
                            <td><%=prodOrderBean.getStatus()%></td>
                            <td><%=prodOrderBean.getTotalAmount()%></td>
                            <td><%=prodOrderBean.getShippingStatus()%></td>
                            <td><%=prodOrderBean.getShippingID()%></td>
                            <td><%=prodOrderBean.getRecipientName()%></td>
                            <td><%=prodOrderBean.getAddress()%></td>
                            <td><%=prodOrderBean.getPhone()%></td>
                           
                            <td><button type='submit' class="box" name="button" value="selectAll">檢視</button></td> <!-- 查詢周邊商品訂單明細 針對每筆訂單 -->
                            </form>
            				
                        </tr>
                        <%
                        }
                        %>
            

                    </table>
            		
                </div>
            </div>
        </div>
    </div>

</body>
</html>
