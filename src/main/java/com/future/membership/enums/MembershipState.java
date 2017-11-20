package com.future.membership.enums;

public enum MembershipState {
	PLATINUM(1,3),  //铂金会员
	GOLD(2,2),  //黄金会员
	SILVER(3,1),  //白银会员
	BRONZE(4,0);   //青铜会员
	
	private MembershipState(int i,int years) {
		this.i = i;
	}

	private int i;
	
	private int years;

	public int toYears(){
		return years;
	}
	
	public int toInt() {
		return i;
	}
	public static MembershipState parse (int state) {
		for(MembershipState membershipState : MembershipState.values()){
			if(membershipState.toInt() == state){
				return membershipState;
			}
		}
		return null;
	}
}
