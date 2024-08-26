package com.kdigital.spring6.dto;

import java.time.LocalDate;

import com.kdigital.spring6.entity.FriendEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Friend {
	private Integer fseq;
	
	// 이름입력 : 2~10길이로 제어
	@Size(min=2, max=10, message="이름은 2~10자 이내로 입력해 주세요.")
	private String fname;
	
	// 나이는 15세 이상만
	@Min(value=15, message="나이는 15세 이상 입력해 주세요.")
	private Integer age;
	
	// 정규표현식 : 
	@Pattern(regexp="01[016789]\\d{4}\\d{4}", message="-없이 숫자로 입력해주세요")
	private String phone;
	
	// 미래날짜는 입력하지 못하도록 제어
	@PastOrPresent(message="과거의 날짜를 선택해주세요")
	private LocalDate birthday;	
	
	private boolean active;
	
	// Entity를 받아서 DTO 반환 (조회할 때)
	public static Friend toDTO(FriendEntity friendEntity) {
		return Friend.builder()
				.fseq(friendEntity.getFseq())
				.fname(friendEntity.getFname())
				.age(friendEntity.getAge())
				.phone(friendEntity.getPhone())
				.birthday(friendEntity.getBirthday())
				.active(friendEntity.isActive())
				.build();
	}
}





