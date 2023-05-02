package cn.ctrlcv.eighteen.common.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class Name: DateUtil
 * Class Description: 日期时间工具类
 *
 * @author liujm
 * @date 2023-04-27
 */
public class DateUtil {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 将字符串日期转为 java.util.Date
     *
     * @param dateString 日期字符串
     * @return 转换后的 java.util.Date 对象
     * @throws ParseException 当解析异常时抛出
     */
    public static Date stringToDate(String dateString) throws ParseException {
        return stringToDate(dateString, DEFAULT_DATE_PATTERN);
    }

    /**
     * 将字符串日期转为 java.util.Date，支持自定义日期格式
     *
     * @param dateString 日期字符串
     * @param pattern    日期格式
     * @return 转换后的 java.util.Date 对象
     * @throws ParseException 当解析异常时抛出
     */
    public static Date stringToDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(dateString);
    }

    /**
     * 将字符串日期转为 int 数组，数组顺序为：年，月，日
     *
     * @param dateString 日期字符串，格式为 yyyy-MM-dd
     * @return int 数组，包含年，月，日
     * @throws ParseException 当解析异常时抛出
     */
    public static int[] dateStringToIntArray(String dateString) throws ParseException {
        Date date = stringToDate(dateString);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

        int year = Integer.parseInt(yearFormat.format(date));
        int month = Integer.parseInt(monthFormat.format(date));
        int day = Integer.parseInt(dayFormat.format(date));

        return new int[]{year, month, day};
    }

    /**
     * 获取当前时间的字符串形式
     *
     * @param pattern 日期格式
     * @return 当前时间的字符串形式
     */
    public static String getCurrentDateString(String pattern) {
        if (!StringUtils.hasLength(pattern)) {
            pattern = DEFAULT_DATE_PATTERN;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date());
    }

}
