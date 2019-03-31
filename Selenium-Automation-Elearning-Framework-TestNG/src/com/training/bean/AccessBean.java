package com.training.bean;

public class AccessBean {
	private String memberName;
	private String accessLevel;
	private String commentsText;

	public AccessBean() {
	}

	public AccessBean(String memberName, String accessLevel, String commentsText) {
		super();
		this.memberName = memberName;
		this.accessLevel = accessLevel;
		this.commentsText = commentsText;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getCommentsText() {
		return commentsText;
	}

	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}

	@Override
	public String toString() {
		return "AccessBean [memberName=" + memberName + ", accessLevel=" + accessLevel + ", commentsText=" + commentsText +"]";
	}

}
