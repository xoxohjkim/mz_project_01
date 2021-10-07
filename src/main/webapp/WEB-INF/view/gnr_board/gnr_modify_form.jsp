<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<script>
$(document).ready(function(){
	
	console.log(${board.id})
	
	$('#gnrModifyBtn').click(function(){
		 if (confirm("게시물을 수정하시겠습니까?")) {
			 $.ajax({
					url : "/form/${kind}/modify/${board.id}" ,
					type:'POST',
					data: $('#doGnrWrite').serialize(),
					success:function(res){
						if(res == 1){
							window.location.href='/board?kind=${kind}&id=${board.id}'
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

gnr수정폼
<form method="post" autocomplete="off" id="doGnrWrite">
	<table>
		<tr>
			<td>제목</td> <td><input type="text" name="title" id="title" value="${board.title}"></td>
		</tr>
		
		<tr>
			<td>내용</td> <td><input type="text" name="content" id="content" value="${board.content}"></td>
		</tr>
	</table>
</form>
	<input type="button" id="goGnrList" value="목록">
	<input type="button" id="gnrModifyBtn" value="수정">
	