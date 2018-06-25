package com.inventory.web.security.info;

public class UserThreadInfoHolder {
	private static ThreadLocal<UserThreadInfoHolder> userThreadInfoHolder = new ThreadLocal<UserThreadInfoHolder>();
	private String sessionId;

	public static UserThreadInfoHolder getUserThreadInfoHolder() {
		return userThreadInfoHolder.get();
	}

	public void setUserThreadInfoHolder(UserThreadInfoHolder userThreadInfoHolder) {
		UserThreadInfoHolder.userThreadInfoHolder.set(userThreadInfoHolder);
	}	
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void clearUserThreadInfoHolder() {
		userThreadInfoHolder.remove();
	}
}
