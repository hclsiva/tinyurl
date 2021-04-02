package study.tinyurl.bean;

public class Response {

	private String statusMessage = null;
	private ResponseEnum status = null;
	
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public ResponseEnum getStatus() {
		return status;
	}
	public void setStatus(ResponseEnum status) {
		this.status = status;
	}
}
