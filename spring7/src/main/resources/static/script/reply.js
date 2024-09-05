/**
 * 댓글 관련 Ajax 코드
 */
$(function () {
	init(); // 전체 댓글 가져 옴
	$('#replyBtn').on('click', replyWrite);
})

// 모든 댓글 목록(게시글의 모든 댓글)을 읽어 옴
function init() {
	let boardNum = $("#boardNum").val();
	
	$.ajax({
		url: '/reply/replyAll'
		, method : 'GET'
		, data : {"boardNum" : boardNum}
		, success : output
	});
}

function output () {
	// 나중 작업
}

// 댓글 쓰기
// 댓글 쓰기
function replyWrite() {
   let writer = '아무개';
   let replyText = $('#replyText').val();
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