<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
productsDetailForm.jsp<br>

<style type="text/css">

	table {
		margin: auto;
		width: 600px;
		height: 350px;
	}

</style>    

<h2 align="center">상품 상세 화면 ${pb.num }</h2>
<table border="1">
	<tr>
		<td rowspan="6" width="100">
			<img src="<%=request.getContextPath()%>/resources/uploadImage/${pb.image}" width="100px" height="100px">
		</td>
		<th width="100">상품명</th>
		<td width="400">${pb.name }</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>${pb.price }</td>
	</tr>
	<tr>
		<th>재고 수량</th>
		<td>${pb.stock }</td>
	</tr>
	<tr>
		<th>설명</th>
		<td>${pb.contents }</td>
	</tr>
	<tr>
		<th>주문 수량</th>
		<td>
			<form action="add.mall" method="post">
			<input type="hidden" name="num" value="${pb.num }">
			<input type="text" name="orderqty" value="1">
			<input type="submit" value="주문">
			</form>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="list.prd?pageNumber=${param.pageNumber }&whatColumn=${param.whatColumn}&keyword=${param.keyword}">상품 리스트</a>
		</td>
	</tr>
</table>