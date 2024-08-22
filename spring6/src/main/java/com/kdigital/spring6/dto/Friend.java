package com.kdigital.spring6.dto;

import java.time.LocalDate;

import com.kdigital.spring6.entity.FriendEntity;

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
	private String fname;
	private Integer age;
	private String phone;
	private LocalDate birthday;
	private boolean active;
	
	// Entity를 받아서 DTO 반환 
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
