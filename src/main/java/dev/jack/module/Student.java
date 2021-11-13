package dev.jack.module;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//@JsonDeserialize(using = StudentDeserializer.class)
public class Student {
	private int id;
	private String course;
	public Person person;
	
	public Student(int id, String course, Person person) {
		super();
		this.id = id;
		this.course = course;
		this.person = person;
	}

	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", course=" + course + ", person=" + person + "]";
	}
	
	
}
