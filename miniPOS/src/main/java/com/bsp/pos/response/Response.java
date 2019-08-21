package com.bsp.pos.response;

import java.util.List;

public class Response {
	private boolean status;
	private String error;
	private String message;
	private List<?> data;
	public Response() {
		super();
	}
	public Response(boolean status, String error, String message, List<?> data) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.data = data;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
}
