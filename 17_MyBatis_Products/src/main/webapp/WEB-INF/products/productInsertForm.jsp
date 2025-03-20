<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<style>
	.err{
		color: red;
	}
</style>
<h3>상품 추가 화면</h3>
<form:form action="insert.prd" method="post" commandName="pb" enctype="multipart/form-data">
*상품명 <input type="text" name="name" value="${pb.name }">
<form:errors cssClass="err" path="name"/>
<br>
제조회사 <input type="text" name="company" value="${pb.company }">
<form:errors cssClass="err" path="company"/>
<br>
*가격 <input type="text" name="price" value="${pb.price }">
<form:errors cssClass="err" path="price"/>
<br>
재고 수량 <input type="text" name="stock" value="${pb.stock }">
<form:errors cssClass="err" path="stock"/>
<br>
적립 포인트 <input type="text" name="point" value="${pb.point }">
<form:errors cssClass="err" path="point"/>
<br>
*설명 <input type="text" name="contents" value="${pb.contents }">
<form:errors cssClass="err" path="contents"/>
<br>
*상품 이미지 <input type="file" name="upload">
<form:errors cssClass="err" path="image"/>
<br>
입고일자 <input type="date" name="inputdate" value="${pb.inputdate }">
<form:errors cssClass="err" path="inputdate"/>
<br>
<input type="submit" value="추가하기">
</form:form>



