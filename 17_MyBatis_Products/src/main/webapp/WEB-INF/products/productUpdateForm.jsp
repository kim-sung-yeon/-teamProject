<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>    
productUpdateForm.jsp<br>
<style type="text/css">

	table {
		margin: auto;
		width: 400px;
	}
	.err{
		font-size: 10pt;
		color: red;
		font-weight: bold;
	}
</style>
<h2>상품 수정 화면 ${pb.num }</h2>
${pb.image }<br>
${param.pageNumber}/${param.whatColumn}/${param.keyword}
<form:form commandName="pb" action="update.prd" method="post" enctype="multipart/form-data">
	
	<input type="hidden" name="num" value="${pb.num}">
	<input type="hidden" name="pageNumber" value="${param.pageNumber}">
	<input type="hidden" name="whatColumn" value="${param.whatColumn}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	
	<table border="1">
		<tr>
			<td width="150">
				<label>*상품명</label>
			</td>
			<td>
				<input type="text" name="name" value="${pb.name }">
				<form:errors cssClass="err" path="name"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>제조 회사</label>
			</td>
			<td>
				<input type="text" name="company" value="${pb.company }">
			</td>
		</tr>
		<tr>
			<td>
				<label>*가격</label>
			</td>
			<td>
				<input type="text" name="price" value="${pb.price }">
				<form:errors cssClass="err" path="price" />
			</td>
		</tr>
		<tr>
			<td>
				<label>재고 수량</label>
			</td>
			<td>
				<input type="text" name="stock" value="${pb.stock }">
			</td>
		</tr>
		<tr>
			<td>
				<label>적립 포인트</label> 
			</td>
			<td>
				<input type="text" name="point" value="${pb.point }">
			</td>
		</tr>
		<tr>
			<td>
				<label>*설명</label> 
			</td>
			<td>
				<input type="text" name="contents" value="${pb.contents }">
				<form:errors cssClass="err" path="contents"/>
			</td>
		</tr>
		<tr>
			<td>
				<label>*상품 이미지</label> 
			</td>
			<td>
				<img src="<%=request.getContextPath()%>/resources/uploadImage/${pb.image}" width="100" height="100">
				<input type="file" name="upload"> <!-- upload=새이미지 -->
				<input type="text" name="upload2" value="${pb.image }"> <!-- upload2=기존이미지명 -->
				<form:errors cssClass="err" path="image"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정하기">
			</td>
		</tr>
	</table>
</form:form>
