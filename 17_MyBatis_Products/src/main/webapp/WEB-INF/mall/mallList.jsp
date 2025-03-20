<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<h3>주문 내역</h3>
<table border="1">
	<tr>
	<td colspan="5">주문자 정보:${sessionScope.loginInfo.name }(${sessionScope.loginInfo.id })</td>
	</tr>
	<tr>
		<td>상품 번호</td>
		<td>상품명</td>
		<td>주문 수량</td>
		<td>단가</td>
		<td>금액</td>
	</tr>
	
	<c:forEach var="x" items="${shopLists }">
	<tr>
		<td>${x.pnum }</td>
		<td>${x.pname }</td>
		<td>${x.qty }</td>
		<td>${x.price }</td>
		<td>${x.amount }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="3">
			<a href="calculate.mall">결제하기</a><!-- mallList->CalculateController -->
			<a href="list.prd">추가 주문</a>
					
		</td>
		<td colspan="2">총 금액 : ${totalAmount }</td>
	</tr>
</table>
