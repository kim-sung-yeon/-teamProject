<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

<h3>주문 상세 내역</h3>
<table border="1">

	<tr>
		<td colspan="2">고객명 : ${loginInfo.name }</td>
		<td colspan="3">송장 번호(주문 번호):${param.oid }</td>
	</tr>
	
	<tr>
		<td colspan="5">배송지:${loginInfo.address1 } ${loginInfo.address2 }</td>
		
	</tr>
	<tr>
		
		<td>순번</td>	
		<td>상품명(상품번호)</td>	
		<td>수량</td>	
		<td>단가</td>	
		<td>금액</td>	
	</tr>
	<c:forEach var="i" begin="0" end="${fn:length(lists)-1}">
		<tr>
			<td>${i+1 }</td>	
			<td>${lists[i].pname }(${lists[i].pnum })</td>	
			<td>${lists[i].qty }</td>	
			<td>${lists[i].price }</td>	
			<td>${lists[i].amount }</td>
		
		</tr>
	</c:forEach>
</table>
