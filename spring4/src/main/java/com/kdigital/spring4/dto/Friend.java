package com.kdigital.spring4.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Friend {
	private String username;
	private Integer age;
	private String phone;
	private LocalDate birthday;
	private boolean active;
}
