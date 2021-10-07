<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<script>
$(document).ready(function(){
	
	console.log(${board.id})
	
	$('#gnrDeleteBtn').click(function(){
		 if (confirm("게시물을 삭제하시겠습니까?")) {
			 $.ajax({
					url : "/form/${kind}/delete/${board.id}" ,
					type:'POST',
					data: $('#doGnrWrite').serialize(),
					success:function(res){
						if(res == 1){
							window.location.href='/board?kind=${kind}'
						}
					},
					error: function(request,status,error){
						alert('에러' + request.status+request.responseText+error);
						console.log(error);
					}
				});
		 } else {
			 
		 }
	});
	
	
	 
});
</script>

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
	
	<div>
	<c:if test="${board.member.id == loginUser.id }">
		<input type="button" value="수정" id="gnrModifyBtn" onclick='location.href="/form?kind=${kind}&id=${board.id}&act=modify"'>
		<input type="button" value="삭제" id="gnrDeleteBtn">
	</c:if>
	</div>
	
	
	<div>
	
		댓글
		<table border="1" width="70%">
			<tr><td>어쩌구</td></tr>
		
		</table>
	</div>


