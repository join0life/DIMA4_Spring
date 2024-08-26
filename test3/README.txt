프로젝트 주제 : Fitness 관리

1) Controller
MainController.java
FitnessController.java

2) DTO
FitnessDTO.java
	seq, [name, gender(0, 1), height, weight, joinDate], stdWeight, bmi, bmiResult
	
	// 예전에 했던 작업 중에서 일반메소드 가져오기
3) Entity
FitnessEntity.java
	seq, name, gender, height, weight, joinDate, stdWeight, bmi, bmiResult

4) Service
FitnessService.java
	insertMember()
	selectMember()
	deleteMember()
	updateMemeber()
	selectAll()
	
5) Repository
<< FitnessRepository.java >>

6) Frontend
	index.html
	insertMember.html
		입력 받는 정보: 이름, 성별, 키, 몸무게
	listMember.html
	updateMember.html
	
7) DB 설계