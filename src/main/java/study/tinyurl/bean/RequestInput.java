package study.tinyurl.bean;

public class RequestInput {

	private String url = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestInput [url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}
	
}
