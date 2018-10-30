package com.sdocean.redis.dao;

public class Member {

	 private String id;
	    private String nickname;
	    
	    
	    
		public Member(String id, String nickname) {
			super();
			this.id = id;
			this.nickname = nickname;
		}
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
	    
}
