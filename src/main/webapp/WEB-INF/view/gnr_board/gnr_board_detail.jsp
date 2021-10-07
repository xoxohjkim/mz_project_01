<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

	<div>
		<table border="1" width="70%">
			<tr>
				<td colspan="2">${board.title}</td>
			</tr>
			
			<tr>
				<td>${board.member.nickname} </td> <td>date: ${board.regDate} l hit: ${board.hit }</td>
			</tr>
		
			<tr>
				<td colspan="2">${board.content}</td>
			</tr>
		
		</table>
	</div>
	<input type="button" value="수정" id="gnrModifyBtn" onclick='location.href="/form?kind=${kind}&id=${board.id}&act=modify"'>
	<input type="button" value="삭제" id="gnrDeleteBtn" onclick='location.href="/form?kind=${kind}id=${board.id}&act=delete"'>
	<div>
		댓글
		<table border="1" width="70%">
			<tr><td>어쩌구</td></tr>
		
		</table>
	</div>


