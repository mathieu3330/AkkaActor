package demo.messages;

public class Tweet {

    private String userDescription;
    private Long userNumbTweets;
    private boolean sentFromWeb;
    
	public Tweet(String userDescription, Long userNumbTweets, boolean sentFromWeb) {
		super();
		this.userDescription = userDescription;
		this.userNumbTweets = userNumbTweets;
		this.sentFromWeb = sentFromWeb;
	}
    
	
	
	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public Long getUserNumbTweets() {
		return userNumbTweets;
	}
	public void setUserNumbTweets(Long userNumbTweets) {
		this.userNumbTweets = userNumbTweets;
	}
	public boolean isSentFromWeb() {
		return sentFromWeb;
	}
	public void setSentFromWeb(boolean sentFromWeb) {
		this.sentFromWeb = sentFromWeb;
	}

	
}
