package imb.pr3.delivery.controller;

import java.util.List;

public class ApiResponse<T> {

	private int status;
	private List<String> messages;
	private T data;
	
	public ApiResponse(int status, List<String> messages, T data) {
		this.status = status;
		this.messages = messages;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public T getData() {
		return data;
	}

}
