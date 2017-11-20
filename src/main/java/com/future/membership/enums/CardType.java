package com.future.membership.enums;

public enum CardType {

	/**
	   * 月卡
	   */
	  MONTHCARD(1,"monthCard"),

	  /**
	   * 季卡
	   */
	  SEASONCARD(2,"seasonCard"),
	/**
	 * 半年卡
	 */
	HALFYEARCARD(1,"halfyearCard"),
	
	/**
	 * 年卡
	 */
	YEARCARD(2,"yearCard");

	  private int id;
	  private String value;
	  private CardType(Integer id,String value){
	    this.id = id;
	    this.value = value;
	  }

	  public int toInt(){
	    return id;
	  }

	  public String getValue(){
	    return value;
	  }

	  public static CardType valueOf(int id){
	    for (CardType cardType : CardType.values()){
	      if(cardType.toInt() == id){
	        return cardType;
	      }
	    }
	    return null;
	  }
}
