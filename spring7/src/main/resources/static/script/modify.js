// 이벤트 소스
let deleteBtn = document.getElementById("deleteBtn");
let updateBtn = document.getElementById("updateBtn");

// 이벤트 핸들러와 연결
deleteBtn.addEventListener('click', goto);
updateBtn.addEventListener('click', goto);

// 이벤트 핸들러 
function goto() {
    let target = this.getAttribute("id"); // deleteBtn, updateBtn
    // let boardNum = this.getAttribute("data-no");
	let boardNum = document.getElementById("boardNum").value;
	let searchItem = document.getElementById("searchItem").value;
	let searchWord = document.getElementById("searchWord").value;
	
    let go = '';

    switch (target) {
        case "updateBtn": go = "boardUpdate"; break;
        case "deleteBtn":
			if(!confirm("정말 삭제할까요?")) return;
			go = "boardDelete";
			break;
    }
	let modifyForm = document.getElementById("modifyForm");
	modifyForm.action = `/board/${go}`;
	modifyForm.submit();
    // let url = `/board/${go}?boardNum=${boardNum}&searchItem=${searchItem}&searchWord=${searchWord}`;
    // location.replace(url); // GET 
}

