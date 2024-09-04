<%@page import="event.util.TimestampUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="event.object.dto.updateevent.OneTicketTypeDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>活動票種詳情</title>
    <style>
        .mustMark {
            color: red;
        }
    </style>
</head>
<body style="background-color:#e6f3ff">
<div align="center">
    <h2>活動票種詳情</h2>
    <jsp:useBean id="ticketType" scope="request" class="event.object.dto.updateevent.OneTicketTypeDTO" />
	<% TimestampUtil timestampUtil = new TimestampUtil(); %>
    <form id="updateForm" method="post" action="<%= request.getContextPath() %>/event/UpdateTicketType" enctype="multipart/form-data">
        <table>
            <tr><td>票種編號：</td><td><input type="text" disabled value="${ticketType.ticketTypeID}"><input type="hidden" name="ticketTypeID" value="${ticketType.ticketTypeID}"></td></tr>
            <tr><td>活動名稱：</td><td><input type="text" disabled value="${ticketType.eventName}"><input type="hidden" name="eventName" value="${ticketType.eventName}"></td></tr>
            <tr><td>場次名稱：</td><td><input type="text" disabled value="${ticketType.sessionName}"><input type="hidden" name="sessionName" value="${ticketType.sessionName}"></td></tr>
            <tr class="must"><td>票種名稱：</td><td><input type="text" name="typeName" value="${ticketType.typeName}" disabled></td></tr>
            <tr><td>票種說明：</td><td><textarea name="typeDesc" disabled>${ticketType.typeDesc}</textarea></td></tr>
            <tr class="must"><td>價格：</td><td><input type="number" name="price" value="${ticketType.price}" disabled></td></tr>
            <tr><td>票種限制總數：</td><td><input type="number" name="quantityAvailable" value="${ticketType.quantityAvailable}" disabled></td></tr>
            <tr><td>已購買數量：</td><td><input type="number" name="quantityPurchased" value="${ticketType.quantityPurchased}" disabled></td></tr>
            <tr class="must"><td>開始售票時間：</td><td><input type="datetime-local" name="startSaleTime" value="<%= timestampUtil.timestampToString(ticketType.getStartSaleTime()) %>" disabled></td></tr>
            <tr class="must"><td>結束售票時間：</td><td><input type="datetime-local" name="endSaleTime" value="<%= timestampUtil.timestampToString(ticketType.getEndSaleTime()) %>" disabled></td></tr>
        </table>
        <button type="button" id="update">修改</button>
        <input type="submit" id="updateSubmit" value="儲存修改" disabled>
    </form>
    <form id="deleteForm" method="post" action="<%= request.getContextPath() %>/event/DeleteTicketType">
        <input type="hidden" name="ticketTypeID" value="${ticketType.ticketTypeID}">
        <input type="submit" id="deleteSubmit" value="刪除此票種">
        <span id="deleteMessage"></span>
    </form>
</div>
<script src="../jslib/jquery-3.7.1.js"></script>
<script>
    $(document).ready(function() {
        let mustMark = '<span class="mustMark">*</span>';

        document.getElementById("update").addEventListener("click", function() {
            this.setAttribute("disabled", true);
            document.getElementById("updateSubmit").removeAttribute("disabled");
            let trs = document.getElementsByTagName("tr");
            for (let i = 0; i < trs.length; i++) {
                if (i <= 2) {continue;}
                trs[i].lastElementChild.firstElementChild.removeAttribute("disabled");
            }
            let mustTrs = document.getElementsByClassName("must");
            for (let mustTr of mustTrs) {
                mustTr.firstElementChild.insertAdjacentHTML("afterbegin", mustMark);
            }
        })

        function isEmpty(str) {
            return (!str || str.trim().length === 0);
        }

        function onsubmit(event) {
            let ary = $(".mustMark").parent().next().find("input").toArray();
            for(mustInput of ary) {
                if (isEmpty(mustInput.value)) {
                    alert("有必填欄位未填");
                    event.preventDefault();
                    return false;
                }
            }
        }
        document.getElementById("updateForm").addEventListener("submit", onsubmit);
        
        if (<%= request.getAttribute("isOnly") %>) {
        	document.getElementById("deleteSubmit").setAttribute("disabled", true);
        	document.getElementById("deleteMessage").innerText = "為此場次的唯一票種，不可刪除。";
        }
    })
</script>
</body>
</html>
