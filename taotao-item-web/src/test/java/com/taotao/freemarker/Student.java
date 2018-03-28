package com.taotao.freemarker;

import java.util.Date;

public class Student {
	private Integer id;
	private String name;
	private Date birthday;
	private String addrees;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddrees() {
		return addrees;
	}
	public void setAddrees(String addrees) {
		this.addrees = addrees;
	}
	public Student() {
		
	}
	public Student(Integer id, String name, Date birthday, String addrees) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.addrees = addrees;
	}
	
}
