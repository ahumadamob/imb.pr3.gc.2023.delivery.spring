package imb.pr3.delivery.controller;

import java.util.List;

public class ApiResponce<T> {
	private int status;
    private List<String> messages;
    private T data;
    
    public ApiResponce(int status, List<String> messages, T data) {
		super();
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

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
