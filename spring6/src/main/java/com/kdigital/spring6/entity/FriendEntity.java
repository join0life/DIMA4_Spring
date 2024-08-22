package com.kdigital.spring6.entity;

import java.time.LocalDate;

import com.kdigital.spring6.dto.Friend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Entity
@Table(name="friend")
public class FriendEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment 설정
	private Integer fseq;
	
	@Column(name="fname", nullable = false) // 테이블의 NotNULL 제약조건이 걸렸을 때 
	private String fname;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="birthday")
	private LocalDate birthday;
	
	@Column(name="active")
	private boolean active;
	
	// DTO를 받아서 Entity로 반환하는 Builder를 생성
	public static FriendEntity toEntity(Friend friend) {
		return FriendEntity.builder()
				.fseq(friend.getFseq())
				.fname(friend.getFname())
				.age(friend.getAge())
				.phone(friend.getPhone())
				.birthday(friend.getBirthday())
				.active(friend.isActive())
				.build();
	}
}
