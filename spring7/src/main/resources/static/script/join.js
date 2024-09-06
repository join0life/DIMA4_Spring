/**
 * 회원 가입할 때 Validation 제어
 */

$(function () {
	$('#userId').on('keyup', confirm);
	$('#submitBtn').on('click', join);
});

// 비밀번호, 비밀번호 확인, 이름이 입력되었는지 확인 ==> joinFlag 값이 true일 경우에만 가입
function join () {
	let userPwd = $("#userPwd").val();
	if(userPwd.trim().length < 3 || userPwd.trim().length > 5) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호는 3~5자 사이로 입력하시오');
		return;
	}
	
	let userPwdCheck = $("#userPwdCheck").val();
	
	if(userPwd.trim() != userPwdCheck.trim()) {
		$('#confirmPwd').css('color', 'red');
		$('#confirmPwd').html('비밀번호와 비밀번호 확인은 값이 같아야 합니다.');
		return;
	}
}

// 사용가능한 아이디인지 여부를 판단 (ajax로 작업)
function confirm () {
	// 회원가입 버튼 불가능 상태로 세팅
	$('#submitBtn').prop('disabled', true);
	let joinFlag = false; // 가입 불가능한 상태
	
	let userId = $('#userId').val();
	
	if(userId.trim().length < 3 || userId.trim().length > 5) {
		$('#confirmId').css('color', 'red');
		$('#confirmId').html('길이는 3~5자 사이로 입력하시오');
		return;
	}
	
	// 중복 아이디인지 체크
	$.ajax({
		url : "/user/confirmId"
		, method : "POST"
		, data : {"userId" : userId}
		, success : function (resp) { // resp = true : 사용 가능한 아이디
			if(resp) {
				$('#confirmId').css('color', 'blue');
				$('#confirmId').prop('disabled', false);
				$('#confirmId').html('사용 가능한 아이디입니다');
				joinFlag = true;
			} else {
				$('#confirmId').css('color', 'red');
				$('#confirmId').prop('disabled', true);
				$('#confirmId').html('사용 불가능한 아이디입니다');
				joinFlag = false;
			}
		} 
	})
	$('#confirmId').html('');
}