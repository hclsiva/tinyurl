package study.tinyurl.bean;

public class TinyURLBean {
	private String hash;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TinyURLBean [hash=");
		builder.append(hash);
		builder.append(", originalURL=");
		builder.append(originalURL);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", userID=");
		builder.append(userID);
		builder.append("]");
		return builder.toString();
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getOriginalURL() {
		return originalURL;
	}
	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}
	public long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}
	public long getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	private String originalURL;
	private long creationDate;
	private long expirationDate;
	private int userID;
}
