package com.example.utils;

import java.util.List;

public class AlbumItem {
	private int front_img;
	private int back_img;
	private String album_name, artist;
	private List<SongItem> song_list;

	public AlbumItem() {
	}

	public AlbumItem(int front_img, int back_img, String album_name,
			String artist, List<SongItem> song_list) {
		super();
		this.front_img = front_img;
		this.back_img = back_img;
		this.album_name = album_name;
		this.artist = artist;
		this.song_list = song_list;
	}

	public int getFront_img() {
		return front_img;
	}

	public void setFront_img(int front_img) {
		this.front_img = front_img;
	}

	public int getBack_img() {
		return back_img;
	}

	public void setBack_img(int back_img) {
		this.back_img = back_img;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<SongItem> getSong_list() {
		return song_list;
	}

	public void setSong_list(List<SongItem> song_list) {
		this.song_list = song_list;
	}

}
