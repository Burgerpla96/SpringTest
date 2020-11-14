package com.kosmo.springapp.basic.validation;

import java.util.Arrays;

public class FormCommand {
	//폼의 파라미터명과 같게 속성(맴버 변수) 정의
	private String name;
	private String years;
	private String gender;
	private String[] inters;
	private String grade;
	private String self;
	//g,s
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String[] getInters() {
		return inters;
	}
	public void setInters(String[] inters) {
		this.inters = inters;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	
	@Override
	public String toString() {
		return "FormCommand [name=" + name + ", years=" + years + ", gender=" + gender + ", inters="
				+ Arrays.toString(inters) + ", grade=" + grade + ", self=" + self + "]";
	}
	
	
	
}
