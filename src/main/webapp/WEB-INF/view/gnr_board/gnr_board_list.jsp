<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>


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
		</tr>
		</tbody>
</table>

<c:if test="${loginUser != null }">
	<input type="button" value="글쓰기" onclick='location.href="/form?kind=${kind}&act=write"'>	
</c:if>


	<div>
		    <c:if test="${pageMaker.prev}">
		    	<p><a href="form?kind=${kind}&act=write&${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></p>
		    </c:if> 
			<ul>
			
			  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			  	<li><a href="<%=request.getContextPath()%>/form?kind=${kind}&act=write&${pageMaker.makeQuery(idx)}">${idx}</a></li>
			  </c:forEach>
			</ul>
			
			  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			  	
			    <p><a href="<%=request.getContextPath()%>form?kind=${kind}&act=write&${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></p>
			  </c:if>
			
		</div>
		
		${pageMaker.cri.rowStart}
