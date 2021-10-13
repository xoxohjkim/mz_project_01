<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div>
	<a href="/mypage/boardList">내 게시글</a>
	<a href="/mypage/commentList">내 댓글</a>
	<a href="//mypage/myInfo">회원정보 변경</a>
</div>

내가 댓글 단 게시글
	<table id="mypageBoardList">
			<colgroup>
				<col width="5%">
				<col width="70%%%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>no</th>
					<th>subject</th>
					<th>date</th>
					<th>hits</th>
				</tr>
			</thead>
			<tbody>
	
					<c:if test = "${totalCnt != 0}"> 
						<c:forEach var="c" items="${list}" varStatus="status">
							<tr>
								<td>${pageMaker.totalCount - ((pageMaker.cri.page -1) * pageMaker.cri.perPageNum + status.index)}</td>
								<td>
									<a href="/board?kind=${c.board.bgr.key}&id=${c.board.id}">
										<c:if test="${c.board.fileList.size() != 0 }">
											<img src="/resources/upload/img/${c.board.member.id}/${c.board.fileList[0].name}" class="thum" width="50px" height="50px">
										</c:if>
										
										${c.board.title} 
										<span class="cmtcnt">(${c.board.cmtCnt})</span>
									</a>
								</td>

								<td><fmt:formatDate value="${c.board.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${c.board.hit}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test = "${totalCnt == 0}">
						<td colspan=5>게시글이 없습니다.</td>
					</c:if>
			</tbody>
	</table>
	</div>


<!-- paging -->
	
		<div class="board_page">
			    <c:if test="${pageMaker.prev}">
			    	<p><a href="commentList${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></p>
			    </c:if> 
				<ul>
				
				  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				  	<li><a href="commentList${pageMaker.makeQuery(idx)}">${idx}</a></li>
				  </c:forEach>
				</ul>
				
				  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				  	
				    <p><a href="commentList${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></p>
				  </c:if>
		</div>
