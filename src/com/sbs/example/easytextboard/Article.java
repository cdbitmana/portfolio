package com.sbs.example.easytextboard;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Article {
	int id;
	String title;
	String body;
	public String regDate;
	
	Calendar time = Calendar.getInstance();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String format = format1.format(time.getTime());
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = format;
	}
}
