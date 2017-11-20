package com.future.membership.enums;

public enum DownloadType {

	  /**
	   * 健身设备
	   */
	  EQUIPMENT(1,"equipment"),

	  /**
	   * 会员
	   */
	  MEMBERSHIP(2,"membership");

	  private int id;
	  private String value;
	  private DownloadType(Integer id,String value){
	    this.id = id;
	    this.value = value;
	  }

	  public int toInt(){
	    return id;
	  }

	  public String getValue(){
	    return value;
	  }

	  public static DownloadType valueOf(int id){
	    for (DownloadType downloadType : DownloadType.values()){
	      if(downloadType.toInt() == id){
	        return downloadType;
	      }
	    }
	    return null;
	  }
}
