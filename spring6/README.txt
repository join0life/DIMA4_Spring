8월 23일

## 주요 업무
Friend 데이터의 CRUD 작업 완료

8월 26일 추가
## validation 기능
1) javascript로 검증 기능 넣기
2) spring에서 제공하는 validation 기능
   (1) 디펜던시 추가작업 필요 -> Gradle refresh 실시
   (2) GET 방식으로 화면을 요청할 때 비어있는 데이터를 생성해서 넘겨줘야함. (Controller)
      --> 입력하는 용도 + Validation 해서 결과를 받는 용도
   (3) DTO에 검증 기능을 추가 (@Size 등을 이용)
   (4) 입력하는 html 에 메시지를 출력할 수 있도록 th:error값을 갖는 태그 추가
   (5) POST로 데이터를 받는 부분에서 @Valid, BindingResult를 추가
   
   
  
