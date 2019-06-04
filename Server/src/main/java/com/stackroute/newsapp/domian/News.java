package com.stackroute.newsapp.domian;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	 
	@Column(name = "author")
	private String author;
	
	@Lob
	@Column(name = "title")
	private String title;
	 
	@Lob
	@Column(name = "description")
	private String description;
	 
	@Column(name = "url")
	private String url;
	 
	@Column(name = "image_url")
	private String urlToImage;
	 
	@Column(name = "publishedAt")
	private String publishedAt;
	 
	@Lob
	@Column(name = "content")
	private String content;
	
	private String userId;
	
	
	 
	public News() {
		super();
	}
	

	public News(int id, String author, String title, String description, String url, String urlToImage,
			String publishedAt, String content, String userId) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.description = description;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.content = content;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrlToImage() {
		return urlToImage;
	}
	
	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	
	public String getPublishedAt() {
		return publishedAt;
	}
	
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

}
