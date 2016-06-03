package com.example.utils;

public class MessageItem {

	private int imgSrc;
	private String title, content, time;

	public MessageItem() {
	}

	public MessageItem(int imgSrc, String title, String content, String time) {
		super();
		this.imgSrc = imgSrc;
		this.title = title;
		this.content = content;
		this.time = time;
	}

	public int getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(int imgSrc) {
		this.imgSrc = imgSrc;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

}
