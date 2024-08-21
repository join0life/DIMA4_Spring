package com.kdigital.test2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Fitness {
	private String name;
	private String gender;
	private double height;
	private double weight;
	private double stdWeight;
	private double bmi;
	private String bmiResult;
	
	public Fitness(String name, String gender, double height, double weight) {

		this.name = name;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		
		calcStdWeight();
		calcBMI();
	}
	

	// 키가 수정되면 표준체중과 BMI를 재계산 하기 위해 
	public void setHeight(double height) {
		this.height = height;
		calcStdWeight();
		calcBMI();
	}

	public double getWeight() {
		return weight;
	}
	// 몸무게가 수정되면 표준체중과 BMI를 재계산 하기 위해 
	public void setWeight(double weight) {
		this.weight = weight;
		calcStdWeight();
		calcBMI();
	}

	public double getStdWeight() {
		return stdWeight;
	}

	public double getBmi() {
		return bmi;
	}

	public String getBmiResult() {
		return bmiResult;
	}
	
	// 일반 메소드로 stdWeight, bmi, bmiResult 처리하도록 함
	// 계산되어 나오는 결과이므로!
	private void calcStdWeight() {
		double temp = height / 100;
		
		if(gender.equals("남")) { // 기본자료형만 ==으로 비교
			stdWeight = temp * temp * 22;
		} else {
			stdWeight = temp * temp * 21;
		}		
	}
	
	private void calcBMI() {
		double temp = height / 100;
		
		bmi = weight / (temp * temp);
		calcBmiResult();
	}

	private void calcBmiResult() {
		if (bmi >= 35) {
			bmiResult = "고도 비만입니다.";
		} else if (bmi >= 30 && bmi <= 34.9) {
			bmiResult = "중도 비만입니다.";
		} else if (bmi >= 25 && bmi <= 29.9) {
			bmiResult = "경도 비만입니다.";
		} else if (bmi >= 23 && bmi <= 24.9) {
			bmiResult = "과체중입니다.";
		} else if (bmi >= 18.5 && bmi <= 22.9) {
			bmiResult = "정상입니다.";
		} else {
			bmiResult = "저체중입니다.";
		}
	}
	@Override
	public String toString() {
		String data = String.format("%s %s %.2fcm %.2fKg 표준체중: %.2f BMI: %.2f %s%n", name, gender, height, weight, stdWeight, bmi, bmiResult);
		return data;
	}

}