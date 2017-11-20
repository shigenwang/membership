package com.future.membership.enums;

public enum UserType {
	 User(1),

	 Membership(2),
	 
	 Teacher(3);

	  private UserType(int i){
	    this.i = i;
	  }
	  private int i;

	  public int toInt(){
	    return i;
	  }
}
