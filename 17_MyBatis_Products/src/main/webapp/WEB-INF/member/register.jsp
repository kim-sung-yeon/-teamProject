<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<%
	String[] hobby = {"마라톤","영화감상","게임","공부"};
	request.setAttribute("hobby", hobby);
%>
<style>
	.err{
		color:red;
	}
</style>
<h3>회원 가입 화면</h3>
<form:form action="register.mb" method="post" commandName="mb">
아이디 : <input type="text" name="id" value="${mb.id }">
<form:errors cssClass="err" path="id" />
<br>
이름 : <input type="text" name="name" value="${mb.name }">
<form:errors cssClass="err" path="name" />
<br>
비번 : <input type="text" name="password" value="${mb.password }">
<form:errors cssClass="err" path="password" />
<br>
성별 <input type="radio" name="gender" value="여자" <c:if test="${mb.gender == '여자' }">checked</c:if>	>여자
	<input type="radio" name="gender" value="남자" <c:if test="${mb.gender == '남자' }">checked</c:if>	>남자
	<form:errors cssClass="err" path="gender" />
	<br>

취미:
<c:forEach var="x" items="<%=hobby %>">
<input type="checkbox" name="hobby" value="${x}" <c:if test="${fn:contains(mb.hobby, x) }">checked</c:if>	>${x}
</c:forEach>
<form:errors cssClass="err" path="hobby" />
<br>
주소1 : <input type="text" name="address1" value="${mb.address1 }">
<form:errors cssClass="err" path="address1" />
<br>
주소2 : <input type="text" name="address2" value="${mb.address2 }">

<br>
적립포인트 : <input type="text" name="mpoint">

<br>

<input type="submit" value="제출하기">
</form:form>