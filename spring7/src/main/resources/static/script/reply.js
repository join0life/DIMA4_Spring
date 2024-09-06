/**
 * 댓글 관련 Ajax 코드
 */
$(function () {
	init(); // 전체 댓글 가져 옴
	$('#replyBtn').on('click', replyWrite);
})

// 모든 댓글 목록(게시글의 모든 댓글)을 읽어 옴
//      JS 		Thymeleaf 문법을 사용할 수 없다.
// (브라우저)    (서버)
function init() {
	let boardNum = $("#boardNum").val();	// th:text='${board.boardNum}'은 여기서 못 씀
	
	$.ajax({
		url: '/reply/replyAll'
		, method : 'GET'
		, data : {"boardNum" : boardNum}
		, success : output
	});
}

// 댓글 목록 출력
function output (resp) {	// let resp = [{}, {}];
	if(resp.length == 0) return;
	let tag = `
	<table>
		<tr>
			<th>작성자</th>
			<th>내용</th>
			<th>작성일</th>
			<th></th>
		</tr>
	`;
	
	$.each(resp, function (index, item) {
		tag += `
		<tr>
			<td class="reply-writer">${item["replyWriter"]}</td>
			<td class="reply-text">${item["replyText"]}</td>
			<td class="reply-date">${item["createDate"]}</td>
			<td class="btns">
				<input type="button" class="deleteBtn btn btn-danger" data-num="${item["replyNum"]}" value="삭제">
			</td>
		</tr>
		`;
	});
	tag += `</table>`;
	$('#reply-list').html(tag);
	$('.deleteBtn').on('click', deleteReply); // 이벤트 설정
	
}
// 댓글 삭제 (댓글을 쓴 사람이 삭제할 수 있다.)
function deleteReply() {
	let replyNum = $(this).attr('data-num');	// 
	
	$.ajax({
		url : '/reply/replyDelete'
		, method : 'GET'
		, data : {"replyNum":replyNum}
		, success : function(resp) {
			init();
		}
	})
}


// 댓글 쓰기
function replyWrite() {
   let writer = '아무개';	// 로그인한 사람의 이름
   let replyText = $('#replyText').val(); // 댓글을 입력하지 않고 전송버튼을 누를 경우
   let boardNum =$("#boardNum").val();
   
   let sendData = {"replyWriter" : writer, 
                  "replyText" : replyText, 
               "boardNum" : boardNum    };
	
	// POST / replyInsert
	$.ajax({
		url : '/reply/replyInsert'
		, method : 'POST'
		, data : sendData
		, success : function () {
			$("#replyText").val("");
			init();
		}
	})
}