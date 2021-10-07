<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.js"  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
	var kind = getParameterByName('kind');
	var act = getParameterByName('act');
	
	$('#gnrWriteBtn').click(function(){
		 if (confirm("게시물을 작성하시겠습니까?")) {
			
			 $.ajax({
					url : "/form/" + kind + "/" + act ,
					type:'POST',
					data: $('#doGnrWrite').serialize(),
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
		 } 
		
	});
	
	//파라미터값 가져오기
	//model로 받은값 오류남ㅠ
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
});
</script>

<form method="post"  action="/gnrWrite" autocomplete="off" id="doGnrWrite">
	<table>
		<tr>
			<td>제목</td> <td><input type="text" name="title" id="title"></td>
		</tr>
		
		<tr>
			<td>내용</td> <td><input type="text" name="content" id="content"></td>
		</tr>
	</table>
</form>
	<input type="button" id="gnrWriteBtn" value="글쓰기">