package org.meruvian.workshop.jaxrs.entity;

import java.util.Date;

public class News {
	private long id;
	private String title;
	private String content;
	private Date createDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
