8월 20일

[문제-1]

1) test1 프로젝트를 생성하시오
2) 프로그램이 구동되면 화면에서
	x와 y값을 정수로 입력 받는다. (input)
	"=" 버튼을 생성한 후 이 버튼을 클릭하면 x, y값을 서버로 전송한다.
	
	서버 쪽에서는 입력 받은 데이터(정수)를 더하여 그 더해진 값을 반환한다.
	
3) 생성할 파일
	- MainController 	: 프로그램이 처음으로 구동됐을 때 index.html로 forwading하는 파일
	- CalculatorController : index.html에서 GET 방식으로 전달한 변수값 x, y를 더한 후
							 그 결과를 다시 index.html로 반환하여 출력
	- 만약 데이터가 전달되지 않을 경우에는 x, y값을 1로 사용한다.
							 
4) 클라이언트 측 생성 파일
	- index.html

5) 주요 기능
   - x, y값을 서버에 전달(submit)하여 계산한 후 그 결과를 다시 index.html에 출력
   - clear 버튼을 클릭하면 계산 결과 및 x, y 입력상자의 값도 삭제하는 코드 추가