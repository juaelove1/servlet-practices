package com.bitacademy.mysite.vo;

import java.util.Date;

public class BoardVo {
	
	private int no;//글 번호
	private String title; //글의 제목
	private String writer; //글의 작성자
	private String content; //글의 내용
	private int view; //글의 조회수 
	private int group_no;//그룹번호
	private int order_no; //그룹내순서
	private int depth; //글의 깊이
	private Date date; //작성날짜
	


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getView() {
		return view;
	}



	public void setView(int view) {
		this.view = view;
	}


	public int getGroup_no() {
		return group_no;
	}

	
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}



	public int getOrder_no() {
		return order_no;
	}


	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", tile=" + title + ", writer=" + writer + ", view=" + view + ", group_no="
				+ group_no + ", order_no=" + order_no + ", depth=" + depth + ", date=" + date + "]";
	}
}
