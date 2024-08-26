package com.kdigital.test3.dto;

import java.time.LocalDate;

import com.kdigital.test3.entity.FitnessEntity;

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
@Builder
@ToString
public class FitnessDTO {
	private Integer seq;
	private String name;
	private boolean gender;
	private double height;
	private double weight;
	private LocalDate joinDate;
	private double stdWeight;
	private double bmi;
	private String bmiResult;
	
	// Entity를 전달받아 DTO로 반환하는 메소드
	public static FitnessDTO toDTO(FitnessEntity entity) {
		return FitnessDTO.builder()
				.seq(entity.getSeq())
				.name(entity.getName())
				.gender(entity.isGender())
				.height(entity.getHeight())
				.weight(entity.getWeight())
				.joinDate(entity.getJoinDate())
				.stdWeight(entity.getStdWeight())
				.bmi(entity.getBmi())
				.bmiResult(entity.getBmiResult())
				.build();
	}

	// 몸무게가 수정되면 표준체중과 BMI를 재계산 하기 위해
	public void setHeight(double height) {
		this.height = height;
		calcStdWeight();
		calcBmi();
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
		calcStdWeight();
		calcBmi();
	}

	public double getStdWeight() {
		return stdWeight;
	}

	
	// 일반 메소드 stdWeight, bmi, bmiResult 처리하도록 함
	// 계산되어 나오는 결과이므로
	private void calcStdWeight() {
		double temp = height / 100; //m로 환산된 키

		// 남자인지 여자인지 판단
		if (gender==true) { // 기본 자료형만  == 로 비교
			stdWeight =  temp * temp *22;
		} else {
			stdWeight =  temp * temp *21;
		}
	}
	
	private void calcBmi() {
		double temp = height / 100; //m로 환산된 키

		bmi = weight / (temp * temp);
		calcBmiResult();
	}
	
	private void calcBmiResult(){
	
	if (bmi >= 35) 						bmiResult = "고도 비만";
	else if (bmi < 35 && bmi >=30) 		bmiResult = "중(重)도 비만 (2단계 비만)";
	else if (bmi < 30 && bmi >=25) 		bmiResult = "경도 비만 (1단계 비만) ";
	else if (bmi < 25 && bmi >=23) 		bmiResult = "과체중";
	else if (bmi < 23 && bmi >=18.5) 	bmiResult = "정상";
	else 								bmiResult = "저체중";

	}
		
}
