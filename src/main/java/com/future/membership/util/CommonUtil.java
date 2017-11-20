package com.future.membership.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

public class CommonUtil {

    /**
    * 逗号拼接的数字字符串,如"1,2,3,4"转化为List<Integer>
    *
    * @param inputStr
    * @return
    */
   public static List<Integer> CommaStringCovertToListInteger(String inputStr) {
       List<Integer> list = new ArrayList<Integer>();
       if (StringUtils.isBlank(inputStr))
           return null;
       inputStr = inputStr.trim();
       String[] strs = inputStr.split(",");
       for (String str : strs) {
           if (!isNum(str)) return null;//有一个字符串不是数字直接返回
           list.add(Integer.parseInt(str));
       }
       return list;
   }
   
   /**
    * 逗号拼接的字符串,如"00021,2,3,4"转化为List<String>
    *
    * @param inputStr
    * @return
    */
   public static List<String> commaStringCovertToListString(String inputStr) {
       List<String> list = new ArrayList<String>();
       if (StringUtils.isBlank(inputStr))
           return null;
       inputStr = inputStr.trim();
       String[] strs = inputStr.split(",");
       for (String str : strs) {
           list.add(str);
       }
       return list;
   }
   
   /**
    * 把List<Integer>转化为逗号拼接的字符串
    *
    * @param list
    * @return
    */
   public static String listIntegerCovertToCommaString(List<Integer> list) {
       if (!CollectionUtils.isEmpty(list)) {
           StringBuilder sb = new StringBuilder();
           for (Integer iNum : list) {
               sb.append(iNum + ",");
           }
           sb.deleteCharAt(sb.length() - 1);
           return sb.toString();
       }
       return null;
   }
   
   /**
    * 判断字符串是否为数字
    *
    * @param str
    * @return
    */
   public static boolean isNum(String str) {
       return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
   }
   
}
