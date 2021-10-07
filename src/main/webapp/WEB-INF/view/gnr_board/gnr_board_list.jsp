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

<input type="button" value="글쓰기" onclick='location.href="/form?kind=${kind}&act=write"'>