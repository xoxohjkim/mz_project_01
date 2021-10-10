<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<script>

$(document).ready(function(){
	
	var bid = {id : ${board.id}};
	
	//댓글 리스트
	$.ajax({
			url : "/board/comment/list" ,
			type:'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data : JSON.stringify(bid),
			cache : false,
			success:function(data){
				var user = "${loginUser.id}";
				var str = "";
				$.each(data, function(i){
					console.log(data)
					str += "<tr>"
					str += "<td>"+ data[i].member.nickname + data[i].id + "</td>"
					str += "<td><span id='cmtContent' bid=${board.id}} cid='" + data[i].id + "'>" + data[i].content + "</span>"
					if(data[i].member.id == user){
						str += "<input type='button' value='수정' id='cmtModifyBtn' cid='" + data[i].id + "'>"
						str += "<input type='button' value='삭제' id='cmtDeleteBtn' cid='" + data[i].id + "'>"
						/* str += "<input type='hidden' value='확인' id='cmtConfirmBtn' cid='" + data[i].id + "'>" */
					}
					str += "<td>" + data[i].regDate + "</td>"
					str += "</tr>";
					
				});
				$('#commentList').append(str);
			
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				console.log(error);
			}
		});
	
	//댓글 등록
	$('#cmtSubmitBtn').click(function(){
		console.log('등록');
		var data = {
				bid : ${board.id},
				content : $('#cmtInput').val()
			}
		$.ajax({
			url : "/comment/add" ,
			type:'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data),
			success:function(res){
				if(res == 1){
					window.location.reload();
				}
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				console.log(error);
			}
		});
	});
	
	var cid;
	//댓글 수정 눌렀을때
	$(document).on('click', '#cmtModifyBtn', function(){
		var cid = $(this).attr('cid');
		
		$.ajax({
			url : "/comment/" + cid ,
			type:"post",
			dataType: "json",
			success:function(data){
				$('#myModal').show();
				$('#cmtModifyInput').val(data.content);
				$('#cmtConfirmBtn').prop('cid', data.id);
				console.log($('#cmtConfirmBtn').prop('cid'));
				
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				console.log(error);
			}
		});
	});

	
	//모달 -> 댓글 수정 확인 눌렀을때
	$(document).on('click', '#cmtConfirmBtn', function(){
		console.log('수정확인');
		var cid = $('#cmtConfirmBtn').prop('cid');
		
		var data = {
				id : cid,
				content : $('#cmtModifyInput').val()
			};
		
		$.ajax({
			url : "/comment/modify" ,
			type:"post",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data),
			success:function(res){
				if(res == 1){
					window.location.reload();	
				}
			},
			error: function(request,status,error){
				alert('에러' + request.status+request.responseText+error);
				console.log(error);
			}
		});
	})
	
	
	
	//댓글 삭제
	$(document).on('click', '#cmtDeleteBtn', function(){
		var cid = $(this).attr('cid');
		console.log('삭제 / ' + cid);
		
		var data = { id : cid };
		
		if(confirm('댓글을 삭제하시겠습니까?')){
			$.ajax({
				url : "/comment/delete" ,
				type:'POST',
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				data: JSON.stringify(data),
				success:function(res){
					if(res == 1){
						window.location.reload();
					}
				},
				error: function(request,status,error){
					alert('에러' + request.status+request.responseText+error);
					console.log(error);
				}
			});
		}else {
			
		}
	})
	
	
	//게시글 삭제
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

function close_pop(flag) {
	$('#myModal').hide();
 };
</script>

<!-- 게시글 내용 -->
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
	
	
<!-- 댓글리스트 -->	

	<div style="padding-top:30px">
		<table border="1" id="commentList" width="70%">
		</table>
		
	<!-- 댓글입력창 -->	
	</div>
	<c:if test = "${loginUser != null}">
		<div>
			<input type="text" name="content" id="cmtInput" >
			<input type="button" value="등록" id="cmtSubmitBtn">
		</div>
	</c:if>



<!-- The Modal -->
    <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content">
      		<div>
      			<span>댓글 수정</span>
      		</div>
      		<div>
                <input type="text" value="" id="cmtModifyInput">
           </div>
           <div>
                <input type="button" value="취소" onClick="close_pop()"> 
                <input type="button" value="수정" id="cmtConfirmBtn">
           </div>
      </div>
 
    </div>
 <!--End Modal-->

	

