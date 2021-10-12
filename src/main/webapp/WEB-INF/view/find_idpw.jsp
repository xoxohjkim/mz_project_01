<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<script>
$(document).ready(function(){
	
	$('#findIdBtn').click(function(){
	
		 $.ajax({
				url : "/find/id",
				type:'POST',
				data: $('#findIdForm').serialize(),
				success:function(res){
					if(res == 1){
						window.location.href='main'
					}
				},
				error: function(request,status,error){
					alert('에러' + request.status+request.responseText+error);
					console.log(error);
				}
			});
	});
	
	$('#findPwBtn').click(function(){
		
		 $.ajax({
				url :"/find/pwd",
				type:'POST',
				data: $('#findPwForm').serialize(),
				success:function(res){
					if(res == 1){
						window.location.href='main'
					}
				},
				error: function(request,status,error){
					alert('에러' + request.status+request.responseText+error);
					console.log(error);
				}
			});
	});
});
</script>	
	<div>
		<form action="post" autocomplete="off" id="findIdForm">
		<div>아이디찾기</div>
			<div>
				<label>이  름</label> <input type="text" id="name" name="name">
			</div>
			<div>
				<label>이메일</label> <input type="text" id="email" name="email">
			</div>
			<input type="button" value="아이디찾기" id="findIdBtn">
		</form>
	</div>
	<br>
	
	<div>
		<form action="post" autocomplete="off" id="findPwForm">
		<div>비밀번호 찾기</div>
			<div>
				<label>아이디</label> <input type="text" id="name" name="name">
			</div>	
			<div>
				<label>이메일</label> <input type="text" id="email" name="email">
			</div>
			<input type="button" value="비밀번호 찾기" id="findPwBtn">
		</form>
	</div>