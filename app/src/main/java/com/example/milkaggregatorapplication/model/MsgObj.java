package com.example.milkaggregatorapplication.model;

import java.util.List;

public class MsgObj{
	private String messaging_product;
	private List<MessagesItem> messages;
	private List<ContactsItem> contacts;

	public void setMessagingProduct(String messagingProduct){
		this.messaging_product = messagingProduct;
	}

	public String getMessagingProduct(){
		return messaging_product;
	}

	public void setMessages(List<MessagesItem> messages){
		this.messages = messages;
	}

	public List<MessagesItem> getMessages(){
		return messages;
	}

	public void setContacts(List<ContactsItem> contacts){
		this.contacts = contacts;
	}

	public List<ContactsItem> getContacts(){
		return contacts;
	}
}