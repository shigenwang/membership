package com.future.membership.enums;

/**
 * 
 */
public enum  ValidHelper {

  NO(0),

  YES(1);

  private ValidHelper(int i){
    this.i = i;
  }
  private int i;

  public int toInt(){
    return i;
  }

}
