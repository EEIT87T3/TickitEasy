<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,order.bean.ProdOrdersBean"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>訂單管理後台</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f0f0f0;
}

.container {
	max-width: 1600px;
	margin: 0 auto;
	padding: 20px;
}

h1 {
	color: #333;
}

.order-list {
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #333;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.actions {
	margin-top: 20px;
}

.btn {
	display: inline-block;
	padding: 10px 20px;
	background-color: #333;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	margin-right: 10px;
}

.btnn {
	display: inline-block;
	padding: 5px 7px;
	background-color: #333;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	margin-right: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>訂單管理後台</h1>
		<div class="order-list">
			<table>
				<thead>
					<tr>
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
						<th>操作
					</tr>

				</thead>
				<tbody>
					<%
					List<ProdOrdersBean> prodOrderBeans = (ArrayList<ProdOrdersBean>) request.getAttribute("selectAll");
					if (prodOrderBeans != null) {
	                    for (ProdOrdersBean prodOrderBean : prodOrderBeans) {
					%>

					<tr align="center">

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
							<td>
								<form method="post" action="${pageContext.request.contextPath}/order/delete">
	                            	<input type="hidden" name="prodOrderID" value="<%=prodOrderBean.getProdOrderID()%>">
									<button type="submit" class="btnn" name="button" value="delete">刪除</a> 
								</form>
								<form method="post" action="${pageContext.request.contextPath}/order/update">
	                            	<input type="hidden" name="prodOrderID" value="<%=prodOrderBean.getProdOrderID()%>">
									<button type="submit" class="btnn" name="button" value="update">修改</a> 
								</form>
								<button type="submit" class="btnn" name="button" value="delete">檢查詳細</a> 
							</td>

					</tr>
					<%
	                    }
					}
					%>

				</tbody>
			</table>
		</div>
		<div class="actions">
			<form method="post" action="${pageContext.request.contextPath}/order/insert">
				<button type="submit" class="btnn" name="button" value="insert">新增訂單</a> 
			</form>
		</div>
	</div>
	
</body>
</html>