<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
memberList.jsp<br>
<%@ include file="../common/common.jsp" %>
<script>
	function insert(){
		location.href="register.mb";
	}
	
	function update(id,pageNumber,whatColumn,keyword){
		location.href="update.mb?id="+id+"&pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
</script>
<h3>회원 리스트 화면(${totalCount })</h3>
<form action="list.mb" method="get">
	<select name="whatColumn">
		<option value="">전체검색</option>
		<option value="name">이름</option>
		<option value="gender">성별</option>
		
	</select>
	<input type="text" name="keyword">
	<input type="submit" value="검색">
</form>
<br>

<table border="1">
	<tr>
		<td colspan="9"><input type="button" value="추가하기" onClick="insert()"></td>
	</tr>
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>비밀번호</td>
		<td>성별</td>
		<td>취미</td>
		<td>주소</td>
		<td>포인트</td>
		<td>삭제</td>
		<td>수정</td>
	</tr>
	<c:forEach var="x" items="${list }">
		<tr>
					
		<td>${x.id }</td>
		<td>${x.name }</td>
		<td>${x.password }</td>
		<td>${x.gender }</td>
		<td>${x.hobby }</td>
		<td>${x.address1 } ${x.address2 }</td>
		<td>${x.mpoint }</td>
		<td><a href="delete.mb?id=${x.id }&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">삭제</a></td>
		<td><input type="button" value="수정" onClick="update('${x.id }','${pageInfo.pageNumber}','${param.whatColumn}','${param.keyword}')"></td>
		</tr>
	</c:forEach>
</table>
<br>

${pageInfo.pagingHtml}