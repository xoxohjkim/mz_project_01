<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<script>
$(document).ready(function(){
	
	var select;
	 $('#gnrSearchBox').change(function(){
		select = $('#gnrSearchBox option:selected').val();
	 });
	 
	 $('#grnSearchBtn').click(function(){
		console.log('condition=' + select);
		var keyword = $('#keyword').val();
		location.href = '/board?kind=${kind}&cond=' + select + '&keyword=' + keyword;
	 });
	 
});
</script>
	<div>
		<select id="gnrSearchBox">
			<option value="" selected>선택</option>
			<option value="brd_title">제목</option>
			<option value="mem_nickname">닉네임</option>
		</select>
		<input type="text" id="keyword">
		<input type="button" id="grnSearchBtn" value="검색">
	</div>
	
	<div>
	<table id="gnr_board_list">
			<colgroup>
				<col width="5%">
				<col width="45%%">
				<col width="20%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>no</th>
					<th>subject</th>
					<th>name</th>
					<th>date</th>
					<th>hits</th>
				</tr>
			</thead>
			<tbody>
			
			<tr>
			<c:if test = "${totalCnt != 0}">
				<c:forEach var="b" items="${list}" varStatus="status">
					<thead>
						<tr>
							<td>${b.id}</td>
							<td><a onclick='location.href="?kind=${kind}&id=${b.id}"'>${b.title}</a>
							<td>${b.member.nickname}</td>
							<td>${b.regDate}</td>
							<td>${b.hit}</td>
						</tr>
					</thead>
				</c:forEach>
			</c:if>
			<c:if test = "${totalCnt == 0}">
				<td colspan=5>게시글이 없습니다.</td>
			</c:if>
			</tr>
			
			</tbody>
	</table>
	</div>
	
	<div>
	<c:if test="${loginUser != null }">
		<input type="button" value="글쓰기" onclick='location.href="/form?kind=${kind}&act=write"'>	
	</c:if>
	</div>
	
			<!-- paging -->
		
			<div>
				    <c:if test="${pageMaker.prev}">
				    	<p><a href="board${pageMaker.makeQuery(pageMaker.startPage - 1)}&kind=${kind}&cond=${cond}&keyword=${keyword}">이전</a></p>
				    </c:if> 
					<ul>
					
					  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					  	<li><a href="<%=request.getContextPath()%>/board${pageMaker.makeQuery(idx)}&kind=${kind}&cond=${cond}&keyword=${keyword}">${idx}</a></li>
					  </c:forEach>
					</ul>
					
					  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					  	
					    <p><a href="<%=request.getContextPath()%>board${pageMaker.makeQuery(pageMaker.endPage + 1)}&kind=${kind}&cond=${cond}&keyword=${keyword}">다음</a></p>
					  </c:if>
			</div>