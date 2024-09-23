<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,order.bean.ProdOrdersBean"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改</title>
<style>
.box {
	width: 600px;
	border: 1px solid black;
	margin: 0 auto;
}
</style>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<%
		ProdOrdersBean prodOrderBeans = (ProdOrdersBean) request.getAttribute("selectByprodOrderID");
		if (prodOrderBeans != null) {        
	%>
	<div class='box'>
		<form method='post' action='${pageContext.request.contextPath}/order/update'>
			<h3 class="text-center">修改</h3>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right">訂單編號</label>
				<div class="col-sm-10">
					<input type="number" id="prodOrderID" class="form-control col-md-6" name="prodOrderID"
						 value="<%=prodOrderBeans.getProdOrderID() %>" readonly>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right">會員編號</label>
				<div class="col-sm-10">
					<input type="number" id="memberID" class="form-control col-md-6" name="memberID"
						 value="<%=prodOrderBeans.getMemberID() %>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right">訂單日期</label>
				<div class="col-sm-10">
					<input type="date" id="orderDate" class="form-control col-md-6" name="orderDate"
						value="<%=prodOrderBeans.getOrderDate() %>">
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<legend class="col-form-label col-sm-2 pt-0 text-right">付款方式</legend>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="payments"
								id="gridRadios1" value="信用卡" checked> 
							<label class="form-check-label" for="gridRadios1"> 信用卡 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="payments"
								id="gridRadios2" value="LINE PAY"> 
							<label class="form-check-label" for="gridRadios2"> LINE PAY </label>
						</div>
						<div class="form-check disabled">
							<input class="form-check-input" type="radio" name="payments"
								id="gridRadios3" value="現金" disabled> 
							<label class="form-check-label" for="gridRadios3"> 現金 </label>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right">付款資訊</label>
				<div class="col-sm-10">
					<input type="password" id="paymentInfo" class="form-control  col-md-8" name="paymentInfo"
						value= "<%=prodOrderBeans.getPaymenInfo() %>">
				</div>
			</div>
			<input type='hidden' name="status" placeholder="請輸入訂單狀態">
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right">總金額</label>
				<div class="col-sm-10">
					<input type="number" id="totalAmount" class="form-control  col-md-6" name="totalAmount"
					  value= "<%=prodOrderBeans.getTotalAmount() %>">
				</div>
			</div>
			<input type='hidden' name="shippingStatus" placeholder="請輸入配送狀態">
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right">配送編號</label>
				<div class="col-sm-10">
					<input type="text" id="shippingID" class="form-control  col-md-6" name="shippingID"
						 value= "<%=prodOrderBeans.getShippingID() %>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right ">收件人</label>
				<div class="col-sm-10">
					<input type="text" id="recipientName" class="form-control  col-md-6" name="recipientName"
						 value= "<%=prodOrderBeans.getRecipientName() %>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right ">收件地址</label>
				<div class="col-sm-10">
					<input type="text" id="address" class="form-control  col-md-6" name="address"
						 value= "<%=prodOrderBeans.getAddress() %>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label text-right ">電話</label>
				<div class="col-sm-10">
					<input type="number" id="phone" class="form-control  col-md-8" name="phone"
						 value= "<%=prodOrderBeans.getPhone() %>">
				</div>
			</div>
			<div class="text-center">
				<button class="btn btn-primary btn-sm" type='submit' name="button"
					value="add">修改</button>
			</div>
		</form>
	</div>
	<%
		}
	%>

</body>
</html>
