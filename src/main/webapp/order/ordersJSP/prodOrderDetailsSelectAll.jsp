<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, order.bean.ProdOrderDetailsBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>周邊商品訂單明細全部資料</title>
<style>
.box {
	border: 2px solid black;
	width: 1200px;
	height: 600px;
	margin: 0 auto;
	background-color: #fff5f5;
}

table {
	border-collapse: collapse;
}
th{
padding:15px
}
td {
	border: 1px solid black,
}
</style>
</head>
<body>
	<div align="center" class="box">
		<h2>周邊商品訂單明細全部資料</h2>
		<table>
			<tr style="background-color: #fff5f5">
				<th>周邊商品訂單明細編號
				<th>周邊商品訂單編號
				<th>周邊商品編號
				<th>單價
				<th>數量
				<th>評論內容
				<th>評論時間
				<th>評分 <%
				List<ProdOrderDetailsBean> prodOrderDetailsBeans = (ArrayList<ProdOrderDetailsBean>) request.getAttribute("list");
				for (ProdOrderDetailsBean prodOrderDetailsBean : prodOrderDetailsBeans) {
				%>
			<tr align="center">
				<td><%=prodOrderDetailsBean.getProdOrderDetailID()%></td>
				<td><%=prodOrderDetailsBean.getProdOrderID().getProdOrderID()%></td>
				<td><%=prodOrderDetailsBean.getProductID()%></td>
				<td><%=prodOrderDetailsBean.getPrice()%></td>
				<td><%=prodOrderDetailsBean.getQuantity()%></td>
				<td><%=prodOrderDetailsBean.getContent()%></td>
				<td><%=prodOrderDetailsBean.getReviewTime()%></td>
				<td><%=prodOrderDetailsBean.getScore()%></td>
			</tr>
			<%
			}
			%>





		</table>

	</div>
</body>
</html>