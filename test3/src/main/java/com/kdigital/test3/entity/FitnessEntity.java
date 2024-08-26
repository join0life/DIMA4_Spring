package com.kdigital.test3.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.kdigital.test3.dto.FitnessDTO;

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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="fitness")
public class FitnessEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="seq")
	private Integer seq;
	
	@Column(name="name", nullable=false)
	private String name;

	@Column(name="gender")
	private boolean gender;

	@Column(name="height")
	private double height;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="join_date")
	@CreationTimestamp // 회원에 대한 정보가 처음 생성될 때 자동으로 날짜 세팅
	private LocalDate joinDate;

	@Column(name="std_weight")
	private double stdWeight;

	@Column(name="bmi")
	private double bmi;

	@Column(name="bmi_result")
	private String bmiResult;
	
	// DTO를 전달받아 Entity로 반환하는 메소드
	public static FitnessEntity toEntity(FitnessDTO fitness) {
		return FitnessEntity.builder()
				.seq(fitness.getSeq())
				.name(fitness.getName())
				.gender(fitness.isGender())
				.height(fitness.getHeight())
				.weight(fitness.getWeight())
				.joinDate(fitness.getJoinDate())
				.stdWeight(fitness.getStdWeight())
				.bmi(fitness.getBmi())
				.bmiResult(fitness.getBmiResult())
				.build();
	}
}
