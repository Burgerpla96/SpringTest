
package com.kosmo.springapp.basic.resource;

public class UserCommand {
	
	//멤버변수(속성)
	private String name;
	private String user;
	private String pass;
	
	
	//세터
	public void setName(String name) {
		this.name = name;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "[이름="+name+", 아이디="+user+", 비번="+pass+"]";
	}
}
