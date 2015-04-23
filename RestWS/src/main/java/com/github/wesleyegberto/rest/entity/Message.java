package com.github.wesleyegberto.rest.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	private int code;
	private String message;

	public Message() {
	}

	public Message(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static Message build(int code, String message) {
		return new Message(code, message);
	}
}
