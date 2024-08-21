8월 20일

## 주요 업무
1) 많은 양의 데이터가 클라이언트로부터 전송되면
	서버는 @ModelAttribute 애노테이션을 이용해 수신한다.
	
2) DTO가 생성되어 있어야 한다.
	- 기본 생성자가 있어야 한다. (@NoArgsConstructor)
	- setter를 이용해서 데이터가 저장될 수 있도록 준비 (@Setter)
	- 클라이언트의 form 태그의 name값과 DTO의 변수명이 같아야
	  값을 setting할 수 있다.

3) 메소드의 매개변수 위치에 @ModelAttribute를 이용해 전송받는다.

4) Model 객체에 데이터(객체)를 담는다.

5) Thymeleaf로 데이터를 꺼내야 하는 클라이언트는
	getter를 이용해서 데이터를 꺼냄