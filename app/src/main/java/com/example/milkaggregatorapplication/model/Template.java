package com.example.milkaggregatorapplication.model;

public class Template{
	private String name;
	private Language language;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLanguage(Language language){
		this.language = language;
	}

	public Language getLanguage(){
		return language;
	}
}
