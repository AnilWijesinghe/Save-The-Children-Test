package org.save.the.children.practicaltest.api.response;

import org.save.the.children.practicaltest.model.User;

public class UserResponse extends Response {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
