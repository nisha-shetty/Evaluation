package com.robosoft.dto.returnValues;

import java.util.List;

public class UsersReturnValue extends BaseReturnValue{
	
	private List<UserVal> users;
	private String guid;
	

	public List<UserVal> getUsers() {
		return users;
	}

	public void setUsers(List<UserVal> users) {
		this.users = users;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	
}
