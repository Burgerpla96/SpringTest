package com.kosmo.rest.service;

/*
CREATE TABLE CONTACTS(
CONTACT_ID NUMBER PRIMARY KEY,
NO NUMBER,
NAME NVARCHAR2(10) NOT NULL,
PHOTO VARCHAR2(100),
TEL VARCHAR2(20));
 */
public class ContactsDTO {
	private String contact_id;
	private String no;
	private String name;
	private String photo;
	private String tel;

	//getter,setter
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
