package com.lx.attendance.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author zengjj  2014-3-31
 *
 */
public class CommonDate {

	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_FORMAT_TEN = "yyyyMMddhhmmss";
	public static final String DATE_FORMAT_MONTH = "yyyy-MM";
	public static final String CHAR_SET = "utf-8";

	/**
	 * 按指定格式将日期值格式化为字符串。
	 * 
	 * @param date
	 *            要进行格式化的日期值。
	 * @param format
	 *            目标格式。
	 * @return 格式化后的日期值字符串。
	 */
	public static String toString(Date date, String format) {
		return date == null ? "" : new SimpleDateFormat(format).format(date);
	}

	/**
	 * 把Date格式为“yyyy-MM-dd”。
	 * 
	 * @param date
	 *            要进行格式化的日期值。
	 * @return 格式化后的日期值字符串。
	 */
	public static String strDateToDate(Date date) {
		return toString(date, DATE_FORMAT);
	}

	/**
	 * 把Date格式为“yyyy-MM-dd HH:mm:ss”。
	 * 
	 * @param time
	 *            要进行格式化的日期值。
	 * @return 格式化后的日期值字符串。
	 */
	public static String strDateToTime(Date date) {
		return toString(date, TIME_FORMAT);
	}
	public static String strDateToYM(Date date) {
		return toString(date, DATE_FORMAT_MONTH);
	}
	public static String strDateToYMD(Date date) {
		return toString(date, DATE_FORMAT);
	}
	/**
	 * 通过日期字符串长度来判断格式化 7.yyyy-MM 8.yyyyMMdd 10.yyyy-MM-dd 19.yyyy-MM-dd HH:mm:ss
	 * 默认长度为10
	 * 
	 * @param date
	 *            要进行转化的日期字符串。
	 * @return 日期值。如果格式不匹配则返回null。
	 */
	public static Date dateStrToDate(String strDate) {
		if (strDate.length() == 7)
			return dateStrToDate(strDate, DATE_FORMAT_MONTH);

		if (strDate.length() == 8)
			return dateStrToDate(strDate, DATE_FORMAT_YYYYMMDD);

		if (strDate.length() == 10)
			return dateStrToDate(strDate, DATE_FORMAT);

		if (strDate.length() == 14)
			return dateStrToDate(strDate, TIME_FORMAT_TEN);
		
		if (strDate.length() == 19)
			return dateStrToDate(strDate, TIME_FORMAT);

		return dateStrToDate(strDate, DATE_FORMAT);
	}

	/**
	 * 将日期字符串按指定格式转化为日期值。
	 * 
	 * @param date
	 *            日期字符串。
	 * @param format
	 *            字符串格式。
	 * @return 日期值。如果格式不匹配则返回null。
	 */
	public static Date dateStrToDate(String strDate, String format) {
		return dateStrToDate(strDate, format, Locale.getDefault());
	}

	/**
	 * 将日期字符串按指定格式转化为日期值。
	 * 
	 * @param date
	 *            日期字符串。
	 * @param format
	 *            字符串格式。
	 * @param locale
	 *            语言环境。
	 * @return 日期值。如果格式不匹配则返回null。
	 */
	public static Date dateStrToDate(String strDate, String format, Locale locale) {
		if (strDate == null || strDate.trim() == "")
			return null;
		try {
			return new SimpleDateFormat(format, locale).parse(strDate);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 某个参考时间点往前推N天
	 * 
	 * @param date
	 *            参考时间(如果参考时间为空,则默认为默认时区和语言环境的当前时间)
	 * @param day
	 *            (-day往前推day天,+day往后推day天)
	 *            (-second往前推second秒,+second往后推second秒)
	 * @return 返回 yyyy-MM-dd 格式的时间字符串
	 */
	public static Date dateAddDayToDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		try {
			if (date != null) {
				calendar.setTime(date);
			}
			calendar.add(calendar.DATE, day);
			return calendar.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 某个参考时间点往前推N天N小时N分钟N秒
	 * 
	 * @param date
	 *            参考时间(如果参考时间为空,则默认为默认时区和语言环境的当前时间)
	 * @param day
	 *            (-day往前推day天,+day往后推day天)
	 * @param hours
	 *            (-hours往前推hours小时,+day往后推hours小时)
	 * @param minute
	 *            (-minute往前推minute分钟,+minute往后推minute分钟)
	 * @param second
	 *            (-second往前推second秒,+second往后推second秒)
	 * @return 返回 yyyy-MM-dd 格式的时间字符串
	 */
	public static Date dateAddToTime(Date date, int day, int hours, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		try {
			if (date != null) {
				calendar.setTime(date);
			}
			calendar.add(calendar.DATE, day);
			calendar.add(calendar.HOUR, hours);
			calendar.add(calendar.MINUTE, minute);
			calendar.add(calendar.SECOND, second);
			return calendar.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 两个日期的相差天数,比较时忽略天以下的时间
	 * 
	 * @param bigTime
	 *            较后的时间
	 * @param smallTime
	 *            较前的时间
	 * @return 相减后的天数
	 */
	public static long getDays(Date bigTime, Date smallTime) {
		try {
			Date big=resetDate(bigTime, 0, 0, 0);
			Date small=resetDate(smallTime, 0, 0, 0);
			long l = big.getTime() - small.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			return day;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 两个日期的相差天数,比较时忽略天以下的时间
	 * 
	 * @param bigTime
	 *            较后的时间
	 * @param smallTime
	 *            较前的时间
	 * @return 相减后的天数
	 */
	public static long getDays(String bigTime, String smallTime) {
		try {
			Date big = dateStrToDate(bigTime);
			Date small = dateStrToDate(smallTime);
			long l = big.getTime() - small.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			return day;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 判断两个时间是否是同年同月
	 * 
	 * @param d1
	 *            Date 日期一
	 * @param d2
	 *            Date 日期二
	 * @return false不是 true是
	 */
	public static boolean sameDay(Date d1, Date d2) {
		if ((d1 == null && d2 != null) || (d1 != null && d2 == null))
			return false;
		return strDateToDate(d1).equals(strDateToDate(d2));
	}

	/**
	 * 某个时间点往前推N周或者往后推N周的第一天
	 * 
	 * @param date
	 *            参考时间(如果参考时间为空,则默认为默认时区和语言环境的当前时间)
	 * @param n
	 *            (-i往前推i天,+i往后推n天)
	 * 
	 * @return 转化后的时间
	 */
	public static Date newWeekBegin(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.add(Calendar.WEEK_OF_MONTH, n);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		}

		return calendar.getTime();
	}

	/**
	 * 某个时间点往前推N周或者往后推N周的最后一天
	 * 
	 * @param date
	 *            参考时间(如果参考时间为空,则默认为默认时区和语言环境的当前时间)
	 * @param n
	 *            (-i往前推i天,+i往后推n天)
	 * 
	 * @return 转化后的时间
	 */
	public static Date newWeekEnd(Date date, int n) {
		Calendar calendar = Calendar.getInstance();

		if (date != null) {
			calendar.setTime(date);
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.add(Calendar.WEEK_OF_MONTH, n);
		}

		return calendar.getTime();
	}

	/**
	 * 某个时间点是本月的第几周
	 * 
	 * @param strDate
	 *            参考时间点
	 * 
	 * @return 得到第几周
	 */
	public static int weekOfMonth(String strDate) {
		try {
			Calendar calendar = Calendar.getInstance();
			if (strDate != null && strDate.trim() != "")
				calendar.setTime(dateStrToDate(strDate));

			return calendar.get(Calendar.WEEK_OF_MONTH);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 得到年
	 * 
	 * @param date
	 *            参考时间点
	 * 
	 * @return 得到年
	 */
	public static int getYEAR(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到月
	 * 
	 * @param date
	 *            参考时间点
	 * 
	 * @return 得到月
	 */
	public static int getMM(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到日
	 * 
	 * @param date
	 *            参考时间点
	 * 
	 * @return 得到日
	 */
	public static int getDAY(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 重置某个时间的时分秒
	 * 
	 * @param date
	 *            参考时间点
	 * @param hour
	 *            小时重置数,0-23,其他数忽略
	 * @param minute
	 *            分是重置,0-59,其他数忽略
	 * @param second
	 *            秒是重置,0-59,其他数忽略
	 * 
	 * @return 返回转化后的开始时间,异常返回原先的日期
	 */
	public static Date resetDate(Date date, int hour, int minute, int second) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (hour >= 0 && hour <= 23)
				cal.set(Calendar.HOUR_OF_DAY, hour);
			if (minute >= 0 && minute <= 59)
				cal.set(Calendar.MINUTE, minute);
			if (second >= 0 && second <= 59)
				cal.set(Calendar.SECOND, second);
			cal.set(Calendar.MILLISECOND, 0);
			return date = cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 获取某个日期所在月的总天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getTotalDays(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		return calendar.get(Calendar.DATE);
	}
	/**
	 * 获取本周的第一天和最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String[] getWeekDay() {
		String[] dateArray = new String[2];
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		//System.out.print("********得到本周一的日期*******"+df.format(cal.getTime()));
		dateArray[0] = df.format(cal.getTime());
		//这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		//增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		//System.out.print("********得到本周天的日期*******"+df.format(cal.getTime()));
		dateArray[1] = df.format(cal.getTime());
		return  dateArray;
	}
	/**
	 * 得到某月的第一天
	 * 
	 * @param date
	 *            参考时间
	 * 
	 * @return 返回第一天
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		}
		return calendar.getTime();
	}

	/**
	 * 得到某月的最后一天
	 * 
	 * @param date
	 *            参考时间
	 * 
	 * @return 返回最后一天
	 */
	public static Date getMonthEndDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}

		return calendar.getTime();
	}

	/**
	 * 得到下月的第一天
	 * 
	 * 
	 * @return 返回第一天
	 */
	public static Date getNextMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 得到下月的最后一天
	 * 
	 * 
	 * @return 返回最后一天
	 */
	public static Date getNextMonthEndDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	
	/**
	 * 得到上月的第一天
	 * 
	 * 
	 * @return 返回第一天
	 */
	public static Date getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 得到上月的最后一天
	 * 
	 * 
	 * @return 返回最后一天
	 */
	public static Date getLastMonthEndDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	
	/**
	 * 得到某月之前一个月的第一天
	 * 
	 * 
	 * @return 返回第一天
	 */
	public static Date getLastsMonthFirstDay(Date curDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 字符串转日期
	 * 
	 * @param strValue
	 *            字符串
	 * @return Date 返回的日期
	 */
	public static Date strToFormat(String strValue) {
		if (strValue == null || strValue.trim() == "")
			return null;
		SimpleDateFormat clsFormat = null;
		if (strValue.length() > 19)
			strValue = strValue.substring(0, 19);
		if (strValue.length() == 19)
			clsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else if (strValue.length() == 10)
			clsFormat = new SimpleDateFormat("yyyy-MM-dd");
		else if (strValue.length() == 14)
			clsFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		else if (strValue.length() == 8)
			clsFormat = new SimpleDateFormat("yyyyMMdd");

		ParsePosition pos = new ParsePosition(0);
		return clsFormat.parse(strValue, pos);
	}

	/**
	 * 将字符串进行URL编码转换。
	 * 
	 * @param string
	 *            要转化的字符串。
	 * @param charset
	 *            目标编码格式，如果为空则默认使用UTF-8编码。
	 * @return 转化后的字符串，如果转化失败将返回原字符串。
	 */
	public static String encodeUrl(String string, String charset) {
		if (string == null)
			return string;
		try {
			return URLEncoder.encode(string, charset == null || charset.trim() == "" ? CHAR_SET : charset);
		} catch (UnsupportedEncodingException e) {
			return string;
		}
	}

	/**
	 * URL解码
	 * 
	 * @param string
	 *            目标字符串
	 * @param charset
	 *            字符集
	 * @return 解码后的字符串
	 */
	public static String decodeUrl(String string, String charset) {
		if (string == null)
			return string;

		try {
			return URLDecoder.decode(string, charset == null || charset.trim() == "" ? CHAR_SET : charset);
		} catch (UnsupportedEncodingException e) {
			return string;
		}
	}
	
	/**
	 * 判断一个日期是周几
	 * @param date Date 日期
	 * @return 1-7 周一至周日
	 */
	public static int weekNumber(Date date) {
		if (date == null)
			return 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	/**
	 * 将微信传递的createtime时间转化成yyyy-MM-dd HH:mm:ss
	 * @param createtime  距离1970年后的秒数
	 * @return	字符串类型的yyyy-MM-dd HH:mm:ss格式的日期，如2014-11-08 18:08:41
	 */
	public static String formatCreateTime(String createtime){
		long msgCreateTime = Long.parseLong(createtime)*1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}
	/** 
     * 获取当年的第一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearFirst(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearFirst(currentYear);  
    }  
      
    /** 
     * 获取当年的最后一天 
     * @param year 
     * @return 
     */  
    public static Date getCurrYearLast(){  
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return getYearLast(currentYear);  
    }  
    
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
}
