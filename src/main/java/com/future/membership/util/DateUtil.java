package com.future.membership.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 
 */
public class DateUtil {
    private DateUtil(){

    }


    public final static String YEAR = " year ";

	public final static String MONTH = " month ";

	public final static String DAY = " day ";

	public final static String WEEK = " week ";

	public final static String HOUR = " hour ";

	public final static String MINUTE = " minute ";

	public final static String SECOND = " second ";
  public static final String YYYYMMDDDETAIL = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDhhmmss = "yyyyMMddhhmmss";
	public static final String YYYYMMDDHHmmss = "yyyyMMddHHmmss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDD = "yyMMdd";
	public static final String YYYY_SLASH_MM_SLASH_DD = "yyyy/MM/dd";
	public static final String YYYY_SLASH_MM_SLASH_DD_AND_HH_ADN_MM_AND_SS = "yyyy/MM/dd HH:mm:ss";


	public static TimeZone UTC = TimeZone.getTimeZone("UTC");

	/* :TODO: let Locale/TimeZone come from init args for rounding only */

	/** TimeZone for DateMath (UTC) */
	protected static final TimeZone MATH_TZ = UTC;
	/** Locale for DateMath (Locale.US) */
	protected static final Locale MATH_LOCALE = Locale.US;

	/**
	 * Fixed TimeZone (UTC) needed for parsing/formating Dates in the canonical
	 * representation.
	 */
	protected static final TimeZone CANONICAL_TZ = UTC;
	/**
	 * Fixed Locale needed for parsing/formating Milliseconds in the canonical
	 * representation.
	 */
	protected static final Locale CANONICAL_LOCALE = Locale.US;

	public static String getDateByAddedMin(int interval) {

		// quartz format(ss mm hh dd MM ? yyyy)
		SimpleDateFormat df = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, interval);
		Date date = time.getTime();
		return df.format(date);
	}
	/**
	 * authRecords需要用到2015/12/30-2016/01/28
	 */
	public static String covertStimeAndEndTimeToyyyMMdd(Date stime,Date etime){
		if(stime==null||etime==null) {
      return null;
    }
		String pattern="yyyy/MM/dd";
		String stimeStr=DateUtil.formatByPattern(stime, pattern);
		String etimeStr=DateUtil.formatByPattern(etime, pattern);
		return stimeStr+"-"+etimeStr;
	}
	 /**
	  * 时间加上23点59分59秒
	  * @return
	  */
	 public static Date timeAdd235959(Date date){
		 if(date!=null){
			 SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
			 String _dateString = df1.format(date);
			 _dateString=_dateString+" 23:59:59";
			 return DateUtil.toDate(_dateString, DateUtil.YYYY_SLASH_MM_SLASH_DD_AND_HH_ADN_MM_AND_SS);
		 }
		 return null;
	 }

  public static Date timeDayBegin(Date date) {
    if (date != null) {
      SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");
      String _dateString = df1.format(date);
      _dateString = _dateString + " 00:00:00";
      return DateUtil.toDate(_dateString, DateUtil.YYYY_SLASH_MM_SLASH_DD_AND_HH_ADN_MM_AND_SS);
    }
    return null;
  }

	public static String getDateByAddedSecond(int interval) {

		// quartz format(ss mm hh dd MM ? yyyy)
		SimpleDateFormat df = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		Calendar time = Calendar.getInstance();
		time.add(Calendar.SECOND, interval);
		Date date = time.getTime();
		return df.format(date);
	}

	public static String formatYYMMDDHHmmss(Date date) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String formatByPattern(Date date, String pattern) {

		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	/**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }


	public static String formatStringDate(String dateStr){
		    SimpleDateFormat oldFormat  = new SimpleDateFormat("yyyyMMddHHmmss");
	        SimpleDateFormat newFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date date;
			try {
				date = oldFormat.parse(dateStr);
				return newFormat.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return "";
	}

	/**
	 * string to Date
	 *
	 * @param dateStr
	 * @return Date
	 */
	public static Date toDate(String dateStr) {

		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * string to Date as format
	 *
	 * @param dateStr
	 * @param formaterString
	 * @return
	 */
	public static Date toDate(String dateStr, String formaterString) {

		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date to string
	 *
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {

		String time;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		time = formater.format(date);
		return time;
	}

	/**
	 * date to string as format
	 *
	 * @param date
	 * @param formaterString
	 * @return
	 */
	public static String toString(Date date, String formaterString) {

		String time;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		time = formater.format(date);
		return time;
	}

	/**
	 * Get the specified date with the timeoff seconds
	 *
	 * @param date
	 * @param timeOff
	 * @return Date
	 */
	public static Date getDateWithTimeOff(Date date, long timeOff) {

		long time = date.getTime() + timeOff * 1000;
		return new Date(time);
	}

	/**
	 * method
	 *
	 * @param dateString
	 *
	 * @return dataTime timestamp
	 */
	public final static java.sql.Timestamp string2Time(String dateString)
			throws java.text.ParseException {

		DateFormat dateFormat;

		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

	/**
	 * method
	 *
	 * @param dateString
	 *
	 * @param formaterString
	 *            dateString
	 * @return
	 * @throws java.text.ParseException
	 */
	public final static java.sql.Timestamp string2Time(String dateString,
			String formaterString) throws java.text.ParseException {

		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(formaterString);
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

	public static String toExternal(Date d) {
		return fmtThreadLocal.get().format(d) + 'Z';
	}

	public static String toExternal(Timestamp d) {
		return fmtThreadLocal.get().format(d) + 'Z';
	}

	public final static Timestamp nowTimestamp() {

		return new Timestamp(System.currentTimeMillis());
	}

	public final static Date nowDate() {

		return new Date(System.currentTimeMillis());
	}

	private final static ThreadLocalDateFormat fmtThreadLocal = new ThreadLocalDateFormat(
			new ISO8601CanonicalDateFormat());

	private static class ISO8601CanonicalDateFormat extends SimpleDateFormat {

		protected NumberFormat millisParser = NumberFormat
				.getIntegerInstance(CANONICAL_LOCALE);

		protected NumberFormat millisFormat = new DecimalFormat(".###",
				new DecimalFormatSymbols(CANONICAL_LOCALE));

		public ISO8601CanonicalDateFormat() {
			super("yyyy-MM-dd'T'HH:mm:ss", CANONICAL_LOCALE);
			this.setTimeZone(CANONICAL_TZ);
		}

		@Override
		public Date parse(String i, ParsePosition p) {
			/* delegate to SimpleDateFormat for easy stuff */
			Date d = super.parse(i, p);
			int milliIndex = p.getIndex();
			/* worry aboutthe milliseconds ourselves */
			if (null != d && -1 == p.getErrorIndex()
					&& milliIndex + 1 < i.length()
					&& '.' == i.charAt(milliIndex)) {
				p.setIndex(++milliIndex); // NOTE: ++ to chomp '.'
				Number millis = millisParser.parse(i, p);
				if (-1 == p.getErrorIndex()) {
					int endIndex = p.getIndex();
					d = new Date(d.getTime()
							+ (long) (millis.doubleValue() * Math.pow(10,
									(3 - endIndex + milliIndex))));
				}
			}
			return d;
		}

		@Override
		public StringBuffer format(Date d, StringBuffer toAppendTo,
				FieldPosition pos) {
			/* delegate to SimpleDateFormat for easy stuff */
			super.format(d, toAppendTo, pos);
			/* worry aboutthe milliseconds ourselves */
			long millis = d.getTime() % 1000l;
			if (0l == millis) {
				return toAppendTo;
			}
			int posBegin = toAppendTo.length();
			toAppendTo.append(millisFormat.format(millis / 1000d));
			if (DateFormat.MILLISECOND_FIELD == pos.getField()) {
				pos.setBeginIndex(posBegin);
				pos.setEndIndex(toAppendTo.length());
			}
			return toAppendTo;
		}

		@Override
		public Object clone() {
			ISO8601CanonicalDateFormat c = (ISO8601CanonicalDateFormat) super
					.clone();
			c.millisParser = NumberFormat.getIntegerInstance(CANONICAL_LOCALE);
			c.millisFormat = new DecimalFormat(".###",
					new DecimalFormatSymbols(CANONICAL_LOCALE));
			return c;
		}
	}

	private static class ThreadLocalDateFormat extends ThreadLocal<DateFormat> {
		DateFormat proto;

		public ThreadLocalDateFormat(DateFormat d) {
			super();
			proto = d;
		}

		@Override
		protected DateFormat initialValue() {
			return (DateFormat) proto.clone();
		}
	}

	public static String dateToNoDivString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHmmss);
		return format.format(date);
	}

	public static Date noDivStringToDate(String source) {
		SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHmmss);
		Date retDate;
		try {
			retDate = format.parse(source);
		} catch (ParseException e) {
			System.out.println("Failed to parse the input date string "
					+ source + ", replace with current date");
			retDate = new Date();
		}

		return retDate;
	}

	public static Date addDate(Date date, long timescope) {
		long dateTime = date.getTime();
		return new Date(dateTime + timescope);
	}

	public static Date minusDate(Date date, long timescope) {
		long dateTime = date.getTime();
		return new Date(dateTime - timescope);
	}

	/**
	 * 按时间格式相加
	 *
	 * @param dateStr
	 *            原来的时间
	 * @param pattern
	 *            时间格式
	 * @param type
	 *            "year"年、"month"月、"day"日、"week"周、 "hour"时、"minute"分、"second"秒
	 *            通过常量来设置,e.g.DayInfoUtil.YEAR
	 * @param count
	 *            相加数量
	 * @return
	 */
	public static String addDate(String dateStr, String pattern, String type,
			int count) {
		if (checkParam(dateStr) || checkParam(pattern) || checkParam(type)) {
			return "";
		}
		if (0 == count) {
			return dateStr;
		}
		Date date = toDate(dateStr, pattern);
		Locale locale = Locale.getDefault();
		Calendar calendar = new GregorianCalendar(locale);
		calendar.setTime(date);

		if (DateUtil.YEAR.equals(type)) {
			calendar.add(Calendar.YEAR, count);
		} else if (DateUtil.MONTH.equals(type)) {
			calendar.add(Calendar.MONTH, count);
		} else if (DateUtil.DAY.equals(type)) {
			calendar.add(Calendar.DAY_OF_MONTH, count);
		} else if (DateUtil.WEEK.equals(type)) {
			calendar.add(Calendar.WEEK_OF_MONTH, count);
		} else if (DateUtil.HOUR.equals(type)) {
			calendar.add(Calendar.HOUR, count);
		} else if (DateUtil.MINUTE.equals(type)) {
			calendar.add(Calendar.MINUTE, count);
		} else if (DateUtil.SECOND.equals(type)) {
			calendar.add(Calendar.SECOND, count);
		} else {
			return "";
		}

		return toString(calendar.getTime(), pattern);
	}

	/**
	 * 判断参数是否等于null或者空
	 *
	 * @param param
	 * @return
	 */
	public static boolean checkParam(Object param) {
		if (null == param || "".equals(param)) {
			return true;
		} else {
			return false;
		}
	}



	public static String getAddDay(Date startDay,int day){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        sf.format(startDay);
		c.add(Calendar.DAY_OF_MONTH, day);
		String day1 = sf.format(c.getTime());
		return day1;
	}


	public static Date addDay(Date startDay,int day){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		sf.format(startDay);
		c.add(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}



	//昨天
		public static String getYesterday() {

			 StringBuffer dateBuffer = new StringBuffer();
		        Calendar calendar = Calendar.getInstance();
		        calendar.add(Calendar.DATE,-1);
		        int year = calendar.get(Calendar.YEAR);
		        dateBuffer.append(year);
		        int month = calendar.get(Calendar.MONTH)+1;
		        dateBuffer.append("-");
		        if(month<10){
		            dateBuffer.append("0");
		        }
		        dateBuffer.append(month);
		        int date = calendar.get(Calendar.DATE);
		        dateBuffer.append("-");
		        if(date<10){
		            dateBuffer.append("0");
		        }
		        dateBuffer.append(date);
		        return dateBuffer.toString();
	    }

	/**
	 * 得到前/后n天,返回String
	 *
	 * @param date    某一天
	 * @param nday    向前/向后推n天
	 * @param pattern pattern的格式
	 * @return
	 */
	public static String getBefornNDaysFromDate(Date date, Integer nday, String pattern) {
		if (date == null) {
			return null;
		}
		//date不为空,继续往下走
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, nday);
		Date ndayBefore = calendar.getTime();
		return DateUtil.formatByPattern(ndayBefore, pattern);
	}

	/**
	 * 增加N天或者减去N天，减去N天的话，N为负数
	 * @param date
	 * @return
	 */
	public static Date addNDays(Date date,int nday) {
		//date不为空,继续往下走
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, nday);
		return calendar.getTime();
	}

	//前天
		public static Map<String,Date> getBeforeYesterday() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DAY_OF_MONTH, -2);
	        Date date = calendar.getTime();
	        Map<String,Date> map = new HashMap<String, Date>();
	        String dateString = df.format(date);
	        String _dateString = df1.format(date);

	        try {
				map.put("startTime",df2.parse(dateString));
				map.put("endTime",df2.parse(_dateString));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return map;

	    }
		//今天
		public static Map<String,Date> getDay() {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DAY_OF_MONTH, 0);
	        Date date = calendar.getTime();
	        Map<String,Date> map = new HashMap<String, Date>();
	        String dateString = df.format(date);
	        String _dateString = df1.format(date);
	        try {
				map.put("startTime",df2.parse(dateString));
				map.put("endTime",df2.parse(_dateString));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return map;
	    }

	/**
	 * 得到上一个月的今天
	 * @return
	 */
    public static String getDayOfLastMonth(){
    	StringBuffer dateBuffer = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        int year = calendar.get(Calendar.YEAR);
        dateBuffer.append(year);
        int month = calendar.get(Calendar.MONTH)+1;
        dateBuffer.append("-");
        if(month<10){
            dateBuffer.append("0");
        }
        dateBuffer.append(month);
        int date = calendar.get(Calendar.DATE);
        dateBuffer.append("-");
        if(date<10){
            dateBuffer.append("0");
        }
        dateBuffer.append(date);
        return dateBuffer.toString();
    }

	public static String getCurrDate() {
		SimpleDateFormat objStdFormat = new SimpleDateFormat("yyyyMMdd");
		Date CurrDate = new Date(System.currentTimeMillis());
		return objStdFormat.format(CurrDate);
	}

	public static String getCurrTime() {
		SimpleDateFormat objStdFormat = new SimpleDateFormat("HHmmss");
		Date CurrDate = new Date(System.currentTimeMillis());
		return objStdFormat.format(CurrDate);
	}

	//时间戳转化为Sting或Date
	public static String getDate(Long time){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time1 =new Long(time);
		String d = format.format(time1);
		Date date= null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Format To String(Date):"+d);
		System.out.println("Format To Date:"+date);
		return d;
	}

	public static Date getDate(Date date,int n,String type){
		Date dat = new Date();
		String dateStr = DateUtil.formatYYMMDDHHmmss(dat);
		String addStr = DateUtil.addDate(dateStr, DateUtil.YYYYMMDDDETAIL, type, n);
		return DateUtil.toDate(addStr);
	}
	
	public static String formatYYMMDDHH(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

    public static void main(String[] args){
		Date date = new Date();
		Date date2 = DateUtil.getDate(date, 3, DateUtil.MONTH);
		try {
			int number = DateUtil.daysBetween(date, date2);
			System.out.println(number);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String dateStr = DateUtil.formatYYMMDDHHmmss(date2);
		System.out.println(dateStr);
	}
}
