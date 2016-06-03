package com.example.utils;

public class SongItem {
	private int id, font_img;
	private String name, length, artist, album;

	public String getName() {
		return name;
	}

	public SongItem(int id, String name, String length, String artist,
			String album, int font_img) {
		super();
		this.id = id;
		this.name = name;
		this.length = length;
		this.artist = artist;
		this.album = album;
		this.font_img = font_img;
	}

	public int getFont_img() {
		return font_img;
	}

	public void setFont_img(int font_img) {
		this.font_img = font_img;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtisit(String artist) {
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setSongId(int id) {
		this.id = id;
	}

	public int getSongId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public SongItem() {
	}

}
