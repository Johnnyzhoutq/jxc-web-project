package com.gms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期工具类
 * @author jxc_
 *
 */
public class DateUtil {
	
	
	/**  
	* @Title：DateUtil.java
	* @Author:zhoutianqi
	* @Description:
	* @Date：2017年2月16日下午5:07:08  
	*/    
    private static String day = "dd";
    private static String month = "MM";
    private static String year = "yyyy";
    private static String ymd = "yyyy-MM-dd";
    private static String ymdhms = "yyyyMMddHHmmss";
    public static SimpleDateFormat ymdSDF = new SimpleDateFormat(ymd);
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(ymdhms);    
    public static SimpleDateFormat yearSDF = new SimpleDateFormat(year);    
    public static SimpleDateFormat monthSDF = new SimpleDateFormat(month);    
    public static SimpleDateFormat daySDF = new SimpleDateFormat(day);
    public static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");    
    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat yyyyMMdd_NOT_ = new SimpleDateFormat("yyyyMMdd");
    public static long DATEMM = 86400L;
    /**  
     * 获得当前时间  
     * 格式：2017-02-02 10:38:53  
     * @return String  
     */  
    public static String getCurrentTime() {
        return yyyyMMddHHmmss.format(new Date());    
    }    
    /**  
     * 可以获取昨天的日期  
     * 格式：2017-02-02 10:38:53  
     * @return String  
     */  
    public static String getYesterdayYYYYMMDD() {  
    	Calendar cal=Calendar.getInstance();
    	cal.add(Calendar.DATE,-1);
    	Date date=cal.getTime();    
        String str = yyyyMMdd.format(date);    
        try {    
            date = yyyyMMddHHmmss.parse(str + " 00:00:00");    
            return yyyyMMdd.format(date);    
        } catch (ParseException e) {    
            e.printStackTrace();    
        }    
        return "";    
    } 
    /**  
     * 可以获取后退N天的日期  
     * 格式：传入2 得到2017-02-01  
     * @param backDay  
     * @return String  
     */  
    public String getStrDate(String backDay) {  
        Calendar calendar = Calendar.getInstance() ;  
        calendar.add(Calendar.DATE, Integer.parseInt("-" + backDay));  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;  
        String back = sdf.format(calendar.getTime()) ;  
        return back ;  
    }  
    /**  
     *获取当前的年、月、日  
     * @return String  
     */  
    public static String getCurrentYear() {    
        return yearSDF.format(new Date());    
    }   
    public static String getCurrentMonth() {    
        return monthSDF.format(new Date());    
    }   
    public static String getCurrentDay() {    
        return daySDF.format(new Date());    
    }    
    /**  
     * 获取年月日 也就是当前时间  
     * 格式：2017-02-02  
     * @return String  
     */  
    public static String getCurrentymd() {    
        return ymdSDF.format(new Date());    
    } 
    public static String getCurrentymd(Date t) {    
        return ymdSDF.format(t);    
    } 
    /**    
     * String To Date ---OK    
     *     
     * @param date    
     *            待转换的字符串型日期；    
     * @param format    
     *            转化的日期格式    
     * @return 返回该字符串的日期型数据；    
     */    
    public static Date stringToDate(String date, String format) {    
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {    
            return sdf.parse(date);    
        } catch (ParseException e) {    
            return null;    
        }    
    } 
    /**    
     * 得到二个日期间的间隔日期；    
     *     
     * @param endTime    
     *            结束时间    
     * @param beginTime    
     *            开始时间    
     * @param isEndTime    
     *            是否包含结束日期；    
     * @return    
     */    
    public static Map<String, String> getTwoDay(String endTime,    
            String beginTime, boolean isEndTime) {    
        Map<String, String> result = new HashMap<String, String>();    
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime    
                .equals(""))))    
            return null;    
        try {    
            java.util.Date date = ymdSDF.parse(endTime);    
            endTime = ymdSDF.format(date);    
            java.util.Date mydate = ymdSDF.parse(beginTime);    
            long day = (date.getTime() - mydate.getTime())    
                    / (24 * 60 * 60 * 1000);    
            result = getDate(endTime, Integer.parseInt(day + ""), isEndTime);    
        } catch (Exception e) {    
        }    
        return result;    
    }
    /**    
     * 得到二个日期间的间隔日期；    
     *     
     * @param endTime    
     *            结束时间    
     * @param beginTime    
     *            开始时间    
     * @param isEndTime    
     *            是否包含结束日期；    
     * @return    
     */    
    public static Integer getTwoDayInterval(String endTime, String beginTime,    
            boolean isEndTime) {    
        if ((endTime == null || endTime.equals("") || (beginTime == null || beginTime    
                .equals(""))))    
            return 0;    
        long day = 0l;    
        try {    
            java.util.Date date = ymdSDF.parse(endTime);    
            endTime = ymdSDF.format(date);    
            java.util.Date mydate = ymdSDF.parse(beginTime);    
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);    
        } catch (Exception e) {    
            return 0 ;    
        }    
        return Integer.parseInt(day + "");    
    }
    /**    
     * 根据结束时间以及间隔差值，求符合要求的日期集合；    
     *     
     * @param endTime    
     * @param interval    
     * @param isEndTime    
     * @return    
     */    
    public static Map<String, String> getDate(String endTime, Integer interval,    
            boolean isEndTime) {    
        Map<String, String> result = new HashMap<String, String>();    
        if (interval == 0 || isEndTime) {    
            if (isEndTime)    
                result.put(endTime, endTime);    
        }    
        if (interval > 0) {    
            int begin = 0;    
            for (int i = begin; i < interval; i++) {    
                endTime = givedTimeToBefer(endTime, DATEMM, ymd);    
                result.put(endTime, endTime);    
            }    
        }    
        return result;    
    }
    /**    
     *     
     * 求某一个时间向前多少秒的时间(currentTimeToBefer)---OK    
     *     
     * @param givedTime    
     *            给定的时间    
     * @param interval    
     *            间隔时间的毫秒数；计算方式 ：n(天)*24(小时)*60(分钟)*60(秒)(类型)    
     * @param format_Date_Sign    
     *            输出日期的格式；如yyyy-MM-dd、yyyyMMdd等；    
     */    
    public static String givedTimeToBefer(String givedTime, long interval,    
            String format_Date_Sign) {    
        String tomorrow = null;    
        try {    
            SimpleDateFormat sdf = new SimpleDateFormat(format_Date_Sign);    
            Date gDate = sdf.parse(givedTime);    
            long current = gDate.getTime(); // 将Calendar表示的时间转换成毫秒    
            long beforeOrAfter = current - interval * 1000L; // 将Calendar表示的时间转换成毫秒    
            Date date = new Date(beforeOrAfter); // 用timeTwo作参数构造date2    
            tomorrow = new SimpleDateFormat(format_Date_Sign).format(date);    
        } catch (ParseException e) {    
            e.printStackTrace();    
        }    
        return tomorrow;    
    }
    public static boolean isRegionDate(Date value,Date start_date,Date end_date){
    	int a = value.compareTo(start_date);
    	int b = value.compareTo(end_date);
    	if(value.compareTo(start_date)>=0 && value.compareTo(end_date)<=0){
    		return true;
    	}
    	return false;
    }
    /**    
     * 判断周六周日；    
     *     
     * @param endTime    
     * @param interval    
     * @param isEndTime    
     * @return    
     */  
    public static boolean isSaturOrSun(Date date){
    	Calendar canlen = Calendar.getInstance();
    	canlen.setTime(date);
    	if(canlen.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || canlen.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
    		return true;
    	}
    	return false;
    }
    /**    
     * 一周前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeWeek(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -7);
        //System.out.println("提前一周日期："+yyyyMMdd_NOT_.format(c.getTime()));
       return c.getTime();
    }
    /**    
     * 一月前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeMonth(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        //System.out.println("提前一月日期："+yyyyMMdd_NOT_.format(c.getTime()));
       return c.getTime();
    }
    /**    
     * 三个月前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeQuarter(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -3);
        //System.out.println("提前三个月日期："+yyyyMMdd_NOT_.format(c.getTime()));
       return c.getTime();
    }
    /**    
     * 六个月前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeHalf(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        //System.out.println("提前六个月日期："+yyyyMMdd_NOT_.format(c.getTime()));
       return c.getTime();
    }
    /**    
     * 一年前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeYear(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
       return c.getTime();
    }
    /**    
     * 两年前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeTwoYear(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -2);
       return c.getTime();
    }
    /**    
     * 昨天；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeToday(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
       return c.getTime();
    }
    /**    
     * 三年前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeThreeYear(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -3);
       return c.getTime();
    }
    /**    
     * 四年前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeFourYear(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -4);
       return c.getTime();
    }
    /**    
     * 五年前日期；          
     * @param date 当前日期    
     * @return    
     */  
    public static Date getDateBeforeFiveYear(Date date){
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -5);
       return c.getTime();
    }
    /**    
     * 比较两个日期大小          
     * @param date1，date2
     * @return    
     */  
     public static int compare_date(Date DATE1, Date DATE2) {

        try {
            if (DATE1.getTime() > DATE2.getTime()) {
                return 1;
            } else if (DATE1.getTime() <= DATE2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    } 
    /**
     * 判断是否是指定日期内
     * @param args
     */
    public static boolean isSeven(int number,Date insertTime){
    	Date nowDate=new Date();
    	Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		Calendar insert = Calendar.getInstance();
		insert.setTime(insertTime);
		insert.add(Calendar.DAY_OF_MONTH, number);
		if(now.compareTo(insert)>0){
			return false;
		}else{
			return true;
		}
    }
 
    public static boolean sameDate(Date d1, Date d2) 
    {  
        if(null == d1 || null == d2)  
            return false;  
        Calendar cal1 = Calendar.getInstance();  
        cal1.setTime(d1);  
        cal1.set(Calendar.HOUR_OF_DAY, 0);  
        cal1.set(Calendar.MINUTE, 0);  
        cal1.set(Calendar.SECOND, 0);  
        cal1.set(Calendar.MILLISECOND, 0);  
        Calendar cal2 = Calendar.getInstance();  
        cal2.setTime(d2);  
        cal2.set(Calendar.HOUR_OF_DAY, 0);  
        cal2.set(Calendar.MINUTE, 0);  
        cal2.set(Calendar.SECOND, 0);  
        cal2.set(Calendar.MILLISECOND, 0);  
        return  cal1.getTime().equals(cal2.getTime());  
    }
    public static Date getDateFromCron(String corn)
    {  
    	Calendar cal1 = null;
        if(StringUtil.isValid(corn))
        {
        	String[] spiltA = corn.split(" ");
        	if(spiltA.length == 7)
        	{
        		cal1 = Calendar.getInstance(); 
        		cal1.set(Calendar.DAY_OF_MONTH, 1);
        		cal1.set(Calendar.YEAR, Integer.valueOf(spiltA[6]));
        		cal1.set(Calendar.MONTH, Integer.valueOf(spiltA[4])-1);
        		cal1.set(Calendar.DAY_OF_MONTH, Integer.valueOf(spiltA[3]));
        		cal1.set(Calendar.HOUR_OF_DAY, Integer.valueOf(spiltA[2]));  
                cal1.set(Calendar.MINUTE, Integer.valueOf(spiltA[1]));  
                cal1.set(Calendar.SECOND, Integer.valueOf(spiltA[0]));  
                cal1.set(Calendar.MILLISECOND, 0);
        	}
        	
        }
       
        return    cal1 != null ? cal1.getTime() : null;
    } 

	/**
	 * 把日期对象根据生成指定格式的字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 把日期字符串生成指定格式的日期对象
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * 生成当前年月日字符串
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	/**
	 * 获取指定范围内的日期集合
	 * @param before
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> getRangeDates(String before,String end)throws Exception{
		List<String> datas=new ArrayList<String>();
		Calendar cb = Calendar.getInstance();
		Calendar ce = Calendar.getInstance();
		cb.setTime(formatString(before,"yyyy-MM-dd"));
		ce.setTime(formatString(end,"yyyy-MM-dd"));
		datas.add(formatDate(cb.getTime(),"yyyy-MM-dd"));
		while(cb.before(ce)){
			cb.add(Calendar.DAY_OF_MONTH, 1);
			datas.add(formatDate(cb.getTime(),"yyyy-MM-dd"));
		}
		return datas;
	}
	
	/**
	 * 获取指定范围内的月份集合
	 * @param before
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> getRangeMonth(String before,String end)throws Exception{
		List<String> months=new ArrayList<String>();
		Calendar cb = Calendar.getInstance();
		Calendar ce = Calendar.getInstance();
		cb.setTime(formatString(before,"yyyy-MM"));
		ce.setTime(formatString(end,"yyyy-MM"));
		months.add(formatDate(cb.getTime(),"yyyy-MM"));
		while(cb.before(ce)){
			cb.add(Calendar.MONTH, 1);
			months.add(formatDate(cb.getTime(),"yyyy-MM"));
		}
		return months;
	}
	
	
	public static void main(String[] args) throws Exception{
		/*List<String> datas=getRangeDatas("2017-10-28","2017-11-02");
		for(String data:datas){
			System.out.println(data);
		}*/
		List<String> months=getRangeMonth("2017-09","2018-12");
		for(String month:months){
			System.out.println(month);
		}
	}
}
