package com.kosmo.rest.service;

public class PhotoDTO {
	
	private String imageUrl;
	private String text;
	
	public PhotoDTO(String imageUrl, String text) {
		super();
		this.imageUrl = imageUrl;
		this.text = text;
	}
	
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
