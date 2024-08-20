/**
 * 
 */

// 이벤트 소스(버튼)
document.getElementById("btn").addEventListener('click', function() {
	let myname = "홍길동입니다.";
	
	document.getElementById("myname").innerHTML = myname;
});