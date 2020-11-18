package com.sbs.example.easytextboard.dto;

import java.util.Map;

public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int writerId;
	private int hit;
	private int boardId;
	private String extra__writer;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.writerId = (int) articleMap.get("writerId");
		this.hit = (int) articleMap.get("hit");
		this.boardId = (int) articleMap.get("boardId");
		this.extra__writer = (String) articleMap.get("extra__writer");
	}

	public String getRegDate() {
		return regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public String getupdateDate() {
		return updateDate;
	}

	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public int getWriteMemberId() {
		return writerId;
	}

	public int getHit() {
		return hit;
	}

	public int getBoardId() {
		return boardId;
	}

	public String getExtraWriter() {
		return extra__writer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public void setExtraWriter(String extra__writer) {
		this.extra__writer = extra__writer;
	}

}
