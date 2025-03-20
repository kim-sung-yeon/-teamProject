<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<style>
/* body {
    display: flex;
    justify-content: center;  /* 가로(수평) 중앙 정렬 */
    align-items: center;      /* 세로(수직) 중앙 정렬 */
    height: 100vh;            /* 화면 전체 높이 사용 */
    margin: 0;
} */

</style>
<script type="text/javascript">
	function insert(){
		
		location.href="insert.prd";
	}
	function update(num, pageNumber, whatColumn, keyword){
		location.href="update.prd?num=" + num + "&pageNumber=" + pageNumber + "&whatColumn=" + whatColumn + "&keyword=" + keyword;
	}
</script>
<h3>상품 리스트 화면</h3>

ProductList.jsp(${count })
<center>
<form action="list.prd" method="get">
	<select name="whatColumn">
		<option value="">전체 검색</option>
		<option value="name">상품명</option>
		<option value="price">가격</option>
	</select>
	
	<input type="text" name="input">
	<input type="submit" value="검색">
	<input type="hidden" name="pageNumber" value="${pageNumber }">
</form>
</center>
<table border="1" align="center"> 
	<tr>
		<td colspan="6"><input type="button" value="추가하기" onClick="insert()"></td>
	</tr>
	<tr>
		<td>상품 번호</td>
		<td>상품명</td>
		<td>설명</td>
		<td>가격</td>
		<td>삭제</td>
		<td>수정</td>
	</tr>
	
	<c:forEach var="x" items="${list }">
	<tr>
				
		<td>${x.num }</td>
		<td>
			<a href="detail.prd?num=${x.num }&pageNumber=${paging.pageNumber}&whatColumn=${param.whatColumn }&keyword=${param.keyword }">${x.name }</a>
		</td>
		<td>${x.contents }</td>
		<td>${x.price }</td>
		<td><a href="delete.prd?num=${x.num }&pageNumber=${param.pageNumber}">삭제</a></td>
		<td><input type="button" value="수정" onClick="update(${x.num}, ${paging.pageNumber},'${param.whatColumn }','${param.keyword }')"></td>
	</tr>
	</c:forEach>
</table>
<br>
${paging.pagingHtml}