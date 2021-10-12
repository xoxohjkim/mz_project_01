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
					if(res == ""){
						alert('존재하지 않는 회원입니다.');
					}else {
						alert('고객님의 아이디는 ' + res + '입니다.');
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
					if(res == ""){
						alert('존재하지 않는 회원입니다.');
					}else {
						alert('회원님의 메일주소로 임시비밀번호가 전송되었습니다.')
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
				<label>아이디</label> <input type="text" id="id" name="id">
			</div>	
			<div>
				<label>이메일</label> <input type="text" id="email" name="email">
			</div>
			<input type="button" value="비밀번호 찾기" id="findPwBtn">
		</form>
	</div>