package com.shanhh.study.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * The type Date util.
 * @author jack.zhang
 */
public abstract class DateUtils {

    private static final Pattern DATE_STRING_PATTERN = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
	public static final int SECOND_OF_DAY = 3600 * 1000;

    /**
     * Format date, remove hours, minutes, seconds.
     *
     * @param date the date
     * @return the date
     * @author jack.zhang
     */
    public static Date formatDateToDayBegin(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Format date, reset to end of the date.
     *
     * @param date the date
     * @return the date
     * @author jack.zhang
     */
    public static Date formatDateToDayEnd(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * Format date to year begin.
     *
     * @param date the date
     * @return the date
     * @author dan.shan
     */
    public static Date formatDateToYearBegin(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), 0 ,1);
        return formatDateToDayBegin(cal.getTime());
    }

    /**
     * Format date to year end.
     *
     * @param date the date
     * @return the date
     * @author dan.shan
     */
    public static Date formatDateToYearEnd(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), 11 ,31);
        return formatDateToDayEnd(cal.getTime());
    }

    /**
     * Is valid date string. YYYY-MM-DD
     *
     * @param dateString the date string
     * @return the boolean
     */
    public static boolean isValidDateString(String dateString) {
        return DATE_STRING_PATTERN.matcher(dateString).matches();
    }

    /**
     * Format date to string.
     *
     * @param time the time
     * @param pattern the pattern
     * @return the string
     */
    public static String formatDateToString(Date time, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern, Locale.US);
        return df.format(time);
    }

    /**
     * Format date to string.
     *
     * @param time the time
     * @param pattern the pattern
     * @return the string
     */
    public static Date formatStringToDate(String time, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern, Locale.US);
        try {
            return df.parse(time);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse String to Date failed.",e);
        }
    }
    
    /**
     * the date after n days
     * 
     * @author howard.huang
     * @since 2014年1月15日 下午3:05:31
     *
     * @param date
     * @param days
     * @return
     */
    public static Date dateAfter(final Date date, int days){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DATE,days);
    	return c.getTime();
    }
    
    public static void main(String[] args) {
		System.out.println(dateAfter(new Date(),-1));
	}
}
