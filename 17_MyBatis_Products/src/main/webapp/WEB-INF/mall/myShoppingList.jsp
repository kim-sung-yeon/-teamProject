<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<h3>주문 내역</h3>
<table border="1">
	<tr>
		<td colspan="3">주문자 정보:${loginInfo.name }(${loginInfo.id })</td>
	</tr>
	<tr>
		<td>주문 번호</td>
		<td>주문 일자</td>
		<td>상세 보기</td>
	</tr>
	<c:forEach var="x" items="${list }">
	<tr>
		<td>${x.oid }</td>
		<td>${x.orderdate }</td>
		<td><a href="orderDetailView.mall?oid=${x.oid }">상세 보기</a></td>
	</tr>
	</c:forEach>
	
</table>

