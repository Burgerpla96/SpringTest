package com.kosmo.springapp.onememo.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
	1.pom.xml에 라이브러리 추가
	<!-- Lombok 라이브러리 추가 -->
	<dependency>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok</artifactId>
	    <version>1.18.16</version>
	    <scope>provided</scope>
	</dependency>
	2.다운로드 받은 경로로 가서 java -jar lombok-1.18.16.jar 실행
	  예] C:\Users\KSM06-00\.m2\repository\org\projectlombok\lombok\1.18.16 > java -jar lombok-1.18.16.jar 
	3. Project Lombok installer창이 뜨면
	   Specify Location 버튼 클릭후  eclipse.exe지정 그리고 install버튼 클릭
	
	4.이클립스 재시작

 */

@Setter
@Getter
@ToString
public class OneMemoDTO {
	
	
	private String no;	
	private String title;
	private String content;
	private java.sql.Date postDate;
	private String id;	
	private String name;;//이름 출력용
	
	
	
	/*
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.sql.Date getPostDate() {
		return postDate;
	}
	public void setPostDate(java.sql.Date postDate) {
		this.postDate = postDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	*/
	
}
